package com.xyr.dao;

import com.xyr.enums.OrderStatusEnum;
import com.xyr.po.OrderMaster;
import com.xyr.utils.KeyUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * Created by xyr on 2017/9/21.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMasterRepositoryTest {

    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Test
    public void saveTest() throws Exception {
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId(KeyUtil.getUniqueKey());
        orderMaster.setBuyerName("xyr");
        orderMaster.setBuyerAddress("家里");
        orderMaster.setBuyerPhone("110");
        orderMaster.setBuyerOpenid("119");
        orderMaster.setOrderAmount(new BigDecimal(1));

        orderMasterRepository.saveAndFlush(orderMaster);
    }
    @Test
    public void findByBuyerOpenid() throws Exception {
        PageRequest pageRequest = new PageRequest(0, 10);

        Page<OrderMaster> result = orderMasterRepository.findByBuyerOpenid("119", pageRequest);
        System.out.println(result.getTotalElements());
    }

}