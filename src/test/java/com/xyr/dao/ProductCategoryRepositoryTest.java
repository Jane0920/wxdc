package com.xyr.dao;

import com.xyr.po.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;


/**
 * Created by Administrator on 2017/9/19.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryRepositoryTest {

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Test
    public void findOne() {
        ProductCategory productCategory = productCategoryRepository.findOne(1);
        System.out.println(productCategory.toString());
    }

    @Test
    @Transactional //与service里的事务不同，测视里的事务是在做完更新和修改操作后就直接回滚，不在数据库中保留痕迹
    public void saveTest() {
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryName("女生最爱");
        productCategory.setCategoryType(3);
        ProductCategory result = productCategoryRepository.save(productCategory);
        Assert.assertNotEquals(null, result);
    }

    @Test
    @Transactional
    public void updateTest() {
        ProductCategory productCategory = productCategoryRepository.findOne(2);
        productCategory.setCategoryType(10);
        productCategoryRepository.save(productCategory);
    }

    @Test
    public void findByCategoryTypeInTest() {
        List<Integer> list = Arrays.asList(2, 3, 4);
        List<ProductCategory> categoryList = productCategoryRepository.findByCategoryTypeIn(list);
        Assert.assertNotEquals(0, categoryList.size());
    }

}