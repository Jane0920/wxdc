package com.xyr.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * http请求返回的最外层对象
 * Created by xyr on 2017/9/20.
 */
@Data
public class ResultVO<T> implements Serializable {

    private static final long serialVersionUID = 299610669167851058L;

    /** 错误码 */
    private Integer code;

    /** 返回的信息 */
    private String msg;

    /** 返回的内容 */
    private T data;

}
