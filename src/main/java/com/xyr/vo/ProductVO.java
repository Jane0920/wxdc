package com.xyr.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Created by xyr on 2017/9/20.
 */
@Data
public class ProductVO implements Serializable {
    private static final long serialVersionUID = -3744551571573565486L;
    /** 类目名 */
    @JsonProperty("name") //返回在json中的名字
    private String categoryName;

    /** 类目类别 */
    @JsonProperty("type")
    private Integer categoryType;

    /**  */
    @JsonProperty("foods")
    private List<ProductInfoVO> productInfoVOS;

}
