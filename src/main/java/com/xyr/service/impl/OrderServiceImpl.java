package com.xyr.service.impl;

import com.xyr.dao.OrderDetailRepository;
import com.xyr.dao.OrderMasterRepository;
import com.xyr.dao.ProductRepository;
import com.xyr.dto.OrderDTO;
import com.xyr.enums.ResultEnum;
import com.xyr.exception.SellException;
import com.xyr.po.OrderDetail;
import com.xyr.po.OrderMaster;
import com.xyr.po.ProductInfo;
import com.xyr.service.OderService;
import com.xyr.utils.KeyUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by xyr on 2017/9/21.
 */
@Service
@Transactional
public class OrderServiceImpl implements OderService {

    @Autowired
    private OrderMasterRepository orderMasterRepository;
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Autowired
    private ProductRepository productRepository;

    @Override
    public OrderDTO create(OrderDTO orderDTO) {
        BigDecimal orderAmount = new BigDecimal(0);
        //订单id生成
        String orderId = KeyUtil.getUniqueKey();
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
        orderMaster.setOrderId(orderId);
        orderMaster.setOrderAmount(orderAmount);
        orderMasterRepository.saveAndFlush(orderMaster);

        //下单成功要扣库存

        return null;
    }

    @Override
    public OrderDTO findOne(String orderId) {
        return null;
    }

    @Override
    public Page<OrderDTO> findList(String buyerOpenId, Pageable pageable) {
        return null;
    }

    @Override
    public OrderDTO cancel(OrderDTO orderDTO) {
        return null;
    }

    @Override
    public OrderDTO finish(OrderDTO orderDTO) {
        return null;
    }

    @Override
    public OrderDTO paid(OrderDTO orderDTO) {
        return null;
    }
}
