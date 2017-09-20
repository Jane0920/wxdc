package com.xyr.controller;

import com.xyr.po.ProductCategory;
import com.xyr.po.ProductInfo;
import com.xyr.service.CategoryService;
import com.xyr.service.ProductService;
import com.xyr.vo.ProductInfoVO;
import com.xyr.vo.ProductVO;
import com.xyr.vo.ResultVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 买家商品
 * Created by xyr on 2017/9/20.
 */
@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {

    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/list")
    public ResultVO list() {
        //查询所有的上架商品
        List<ProductInfo> productInfos = productService.findUpAll();
        //查询所有类目
        List<Integer> typeList = productInfos.stream()
                .map(e -> e.getCategoryType())
                .collect(Collectors.toList());
        List<ProductCategory> categoryList = categoryService.findByCategoryType(typeList);
        //数据拼装

        for (ProductCategory category: categoryList) {
            ProductVO productVO = new ProductVO();
            productVO.setCategoryName(category.getCategoryName());
            productVO.setCategoryType(category.getCategoryType());

            for (ProductInfo productInfo : productInfos) {
                if (productInfo.getCategoryType() == category.getCategoryType()) {
                    ProductInfoVO productInfoVO = new ProductInfoVO();
                    BeanUtils.copyProperties(productInfo, productInfoVO);
                }
            }

            productVO.setProductInfoVOS();
        }



        productVO.setProductInfoVOS(Arrays.asList(productInfoVO));
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(0);
        resultVO.setMsg("成功");
        resultVO.setData(Arrays.asList(productVO));

        return resultVO;
    }

}
