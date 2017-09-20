package com.xyr.service;

import com.xyr.po.ProductCategory;

import java.util.List;

/**
 * Created by xyr on 2017/9/20.
 */
public interface CategoryService {

    ProductCategory findOne(Integer categoryId);

    List<ProductCategory> findAll();

    List<ProductCategory> findByCategoryType(List<Integer> types);

    ProductCategory save(ProductCategory productCategory);
}
