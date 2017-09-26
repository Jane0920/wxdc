package com.xyr.service.impl;

import com.xyr.dto.OrderDTO;
import com.xyr.po.OrderDetail;
import com.xyr.utils.KeyUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by xyr on 2017/9/22.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderServiceImplImplTest {

    @Autowired
    private OrderServiceImpl orderService;

    @Test
    public void create() throws Exception {
        OrderDTO orderDTO = new OrderDTO();
        String orderId = KeyUtil.getUniqueKey();
        orderDTO.setOrderId(orderId);
        orderDTO.setBuyerName("xyr");
        orderDTO.setBuyerAddress("迎春南苑");
        orderDTO.setBuyerOpenid("119");
        orderDTO.setBuyerPhone("18368493532");

        List<OrderDetail> orderDetails = new ArrayList<>();

        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setDetailId(KeyUtil.getUniqueKey());
        orderDetail.setOrderId(orderId);
        orderDetail.setProductIcon("src/xyr/htcb.jpg");
        orderDetail.setProductId("182034");
        orderDetail.setProductName("红糖糍粑");
        orderDetail.setProductPrice(new BigDecimal(10));
        orderDetail.setProductQuantity(2);
        orderDetails.add(orderDetail);
        orderDTO.setOrderDetailList(orderDetails);

        orderService.create(orderDTO);
    }

    @Test
    public void findOne() throws Exception {
        OrderDTO orderDTO = orderService.findOne("118326");
        Assert.assertEquals("118326", orderDTO.getOrderId());
    }

    @Test
    public void findList() throws Exception {
        PageRequest pageRequest = new PageRequest(0, 2);
        Page<OrderDTO> orderDTOPage = orderService.findList("119", pageRequest);
        Assert.assertEquals(2, orderDTOPage.getSize());
    }

    @Test
    public void list() throws Exception {
        PageRequest pageRequest = new PageRequest(0, 2);
        Page<OrderDTO> orderDTOPage = orderService.findList(pageRequest);
        Assert.assertNotEquals(0, orderDTOPage.getSize());
    }

    @Test
    public void cancel() throws Exception {
        OrderDTO orderDTO = orderService.findOne("118326");
        orderService.cancel(orderDTO);
    }

    @Test
    public void finish() throws Exception {
        OrderDTO orderDTO = orderService.findOne("135861");
        orderService.finish(orderDTO);
    }

    @Test
    public void paid() throws Exception {
        OrderDTO orderDTO = orderService.findOne("118326");
        orderService.paid(orderDTO);
    }

}