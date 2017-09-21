package com.xyr.dao;

import com.xyr.po.OrderDetail;
import com.xyr.utils.KeyUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by xyr on 2017/9/21.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDetailRepositoryTest {

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Test
    public void save() throws Exception {
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setDetailId(KeyUtil.getUniqueKey());
        orderDetail.setOrderId("851667");
        orderDetail.setProductIcon("src/xyr/htcb.jpg");
        orderDetail.setProductId("182034");
        orderDetail.setProductName("红糖糍粑");
        orderDetail.setProductPrice(new BigDecimal(10));
        orderDetail.setProductQuantity(1);

        OrderDetail result = orderDetailRepository.save(orderDetail);
        Assert.assertNotNull(result);
    }

    @Test
    public void findByOrderId() throws Exception {
        List<OrderDetail> orderDetailList = orderDetailRepository.findByOrderId("851667");
        Assert.assertNotEquals(0, orderDetailList.size());
    }

}