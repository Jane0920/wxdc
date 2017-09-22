package com.xyr.service;

import com.xyr.dto.OrderDTO;

/**
 * Created by xyr on 2017/9/22.
 */
public interface BuyerService {

    /**
     * 查询单个订单
     */
    OrderDTO findOrderOne(String openid, String orderId);

    /**
     * 取消订单
     */
    OrderDTO cancelOrder(String openid, String orderId);
}
