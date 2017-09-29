package com.xyr.service;

import com.xyr.dto.OrderDTO;

/**
 * Created by xyr on 2017/9/29.
 */
public interface PushMessageService {

    /**
     * 订单状态变更消息
     * @param orderDTO
     */
    void orderStatus(OrderDTO orderDTO);

}
