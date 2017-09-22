package com.xyr.converter;

import com.xyr.dto.OrderDTO;
import com.xyr.po.OrderMaster;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * OrderMaster转换为OrderDTO
 * Created by xyr on 2017/9/22.
 */
public class OrderMaster2OrderDTOConverter {

    public static OrderDTO converter(OrderMaster orderMaster) {
        OrderDTO orderDTO = new OrderDTO();

        BeanUtils.copyProperties(orderMaster, orderDTO);
        return orderDTO;
    }

    public static List<OrderDTO> converter(List<OrderMaster> orderMasterList) {
        List<OrderDTO> orderDTOList = orderMasterList.stream().map(e -> converter(e)
                                ).collect(Collectors.toList());
        return orderDTOList;
    }
}
