package com.xyr.dto;

import lombok.Data;
import lombok.Getter;

/**
 * Created by xyr on 2017/9/22.
 */
@Data
public class CartDTO {

    /**
     * 商品id
     */
    private String productId;

    /**
     * 订单商品数量
     */
    private Integer productQuantity;

    public CartDTO(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}
