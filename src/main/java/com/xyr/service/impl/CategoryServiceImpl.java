package com.xyr.service.impl;

import com.xyr.dao.ProductCategoryRepository;
import com.xyr.po.ProductCategory;
import com.xyr.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by xyr on 2017/9/20.
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Override
    public ProductCategory findOne(Integer categoryId) {

        return productCategoryRepository.findOne(categoryId);
    }

    @Override
    public List<ProductCategory> findAll() {
        return productCategoryRepository.findAll();
    }

    @Override
    public List<ProductCategory> findByCategoryType(List<Integer> types) {
        return productCategoryRepository.findByCategoryTypeIn(types);
    }

    @Override
    public ProductCategory save(ProductCategory productCategory) {
        return productCategoryRepository.saveAndFlush(productCategory);
    }
}
