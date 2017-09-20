package com.xyr.enums;

import lombok.Getter;

/**
 * Created by xyr on 2017/9/20.
 */
@Getter
public enum  ProductStatusEnum {
    UP(0, "在架"),
    DOWN(1, "下架")
    ;

    private Integer code;

    private String message;

    ProductStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }


}
