package com.xyr.service.impl;

import com.xyr.converter.OrderMaster2OrderDTOConverter;
import com.xyr.dao.OrderDetailRepository;
import com.xyr.dao.OrderMasterRepository;
import com.xyr.dao.ProductRepository;
import com.xyr.dto.CartDTO;
import com.xyr.dto.OrderDTO;
import com.xyr.enums.OrderStatusEnum;
import com.xyr.enums.PayStatusEnum;
import com.xyr.enums.ResultEnum;
import com.xyr.exception.SellException;
import com.xyr.po.OrderDetail;
import com.xyr.po.OrderMaster;
import com.xyr.po.ProductInfo;
import com.xyr.service.OrderService;
import com.xyr.service.PayService;
import com.xyr.service.ProductService;
import com.xyr.utils.KeyUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by xyr on 2017/9/21.
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMasterRepository orderMasterRepository;
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductService productService;
    @Autowired
    private PayService payService;

    @Override
    @Transactional
    public OrderDTO create(OrderDTO orderDTO) {
        BigDecimal orderAmount = new BigDecimal(0);
        //订单id生成
        String orderId = KeyUtil.getUniqueKey();
        orderDTO.setOrderId(orderId);
        //查询商品数量价格等信息
        for (OrderDetail detail: orderDTO.getOrderDetailList()) {
            ProductInfo productInfo = productRepository.findOne(detail.getProductId());
            if (productInfo == null)
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);

            //计算总价
            orderAmount = productInfo.getProductPrice()
                    .multiply(BigDecimal.valueOf(detail.getProductQuantity()))
                    .add(orderAmount);
            BeanUtils.copyProperties(productInfo, detail);
            detail.setDetailId(KeyUtil.getUniqueKey());
            detail.setOrderId(orderId);
            orderDetailRepository.saveAndFlush(detail);
        }

        //写入订单数据库 orderMaster
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO, orderMaster);
        orderMaster.setOrderAmount(orderAmount);
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
        orderMasterRepository.saveAndFlush(orderMaster);

        //下单成功要扣库存
        List<CartDTO> cartDTOList = orderDTO.getOrderDetailList().stream()
                .map(e -> new CartDTO(e.getProductId(), e.getProductQuantity()))
                .collect(Collectors.toList());
        productService.decreaseStock(cartDTOList);
        return orderDTO;
    }

    @Override
    public OrderDTO findOne(String orderId) {
        OrderMaster orderMaster = orderMasterRepository.findOne(orderId);
        if (orderMaster == null)
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);

        List<OrderDetail> orderDetails = orderDetailRepository.findByOrderId(orderId);
        if (CollectionUtils.isEmpty(orderDetails))
            throw new SellException(ResultEnum.ORDER_DETAIL_EMPTY);

        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster, orderDTO);
        orderDTO.setOrderDetailList(orderDetails);
        return orderDTO;
    }

    @Override
    public Page<OrderDTO> findList(String buyerOpenId, Pageable pageable) {
        Page<OrderMaster> orderMasterPage = orderMasterRepository.findByBuyerOpenid(buyerOpenId, pageable);

        List<OrderDTO> orderDTOList = OrderMaster2OrderDTOConverter.converter(orderMasterPage.getContent());
        Page<OrderDTO> orderDTOPage = new PageImpl<OrderDTO>(orderDTOList, pageable, orderMasterPage.getTotalElements());
        return orderDTOPage;
    }

    @Override
    public Page<OrderDTO> findList(Pageable pageable) {
        Page<OrderMaster> orderMasterPage = orderMasterRepository.findAll(pageable);
        List<OrderDTO> orderDTOList = OrderMaster2OrderDTOConverter.converter(orderMasterPage.getContent());

        return new PageImpl<OrderDTO>(orderDTOList, pageable, orderMasterPage.getTotalElements());
    }

    @Override
    @Transactional
    public OrderDTO cancel(OrderDTO orderDTO) {
        OrderMaster orderMaster = orderMasterRepository.findOne(orderDTO.getOrderId());
        //判断订单状态
        if (!orderMaster.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())) {
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        //修改订单状态
        orderMaster.setOrderStatus(OrderStatusEnum.CANCEL.getCode());
        orderDTO.setOrderStatus(OrderStatusEnum.CANCEL.getCode());
        OrderMaster orderResult = orderMasterRepository.save(orderMaster);
        if (orderResult == null)
            throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);

        //返回库存
        if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList()))
            throw new SellException(ResultEnum.ORDER_DETAIL_EMPTY);
        List<CartDTO> cartDTOList = orderDTO.getOrderDetailList().stream()
                .map(e -> new CartDTO(e.getProductId(), e.getProductQuantity()))
                .collect(Collectors.toList());
        productService.increaseStock(cartDTOList);
        //如果以支付，需要给用户退款
        if (orderResult.getPayStatus().equals(PayStatusEnum.SUCCESS.getCode())) {
            //payService.refund(orderDTO);
        }
        return orderDTO;
    }

    @Override
    @Transactional
    public OrderDTO finish(OrderDTO orderDTO) {
        OrderMaster orderMaster = orderMasterRepository.findOne(orderDTO.getOrderId());
        //判断订单状态
        if (!orderMaster.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())) {
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        //修改状态
        orderMaster.setOrderStatus(OrderStatusEnum.FINISHED.getCode());
        orderDTO.setOrderStatus(OrderStatusEnum.FINISHED.getCode());
        OrderMaster orderResult = orderMasterRepository.save(orderMaster);
        if (orderResult == null)
            throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
        return orderDTO;
    }

    @Override
    @Transactional
    public OrderDTO paid(OrderDTO orderDTO) {
        OrderMaster orderMaster = orderMasterRepository.findOne(orderDTO.getOrderId());
        //判断订单状态
        if (!orderMaster.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())) {
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        //判断支付状态
        if (!orderMaster.getPayStatus().equals(PayStatusEnum.WAIT.getCode()))
            throw new SellException(ResultEnum.ORDER_PAY_STATUS_ERROR);
        //修改支付状态
        orderMaster.setPayStatus(PayStatusEnum.SUCCESS.getCode());
        orderDTO.setPayStatus(PayStatusEnum.SUCCESS.getCode());
        OrderMaster orderResult = orderMasterRepository.save(orderMaster);
        if (orderResult == null)
            throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
        return orderDTO;
    }
}
