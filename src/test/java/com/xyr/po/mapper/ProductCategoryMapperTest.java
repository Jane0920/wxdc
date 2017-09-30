package com.xyr.po.mapper;

import com.google.common.collect.Maps;
import com.xyr.po.ProductCategory;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/9/30.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ProductCategoryMapperTest {

    @Autowired
    private ProductCategoryMapper productCategoryMapper;

    @Test
    public void insertByMap() throws Exception {
        Map<String, Object> map = Maps.newHashMap();
        map.put("category_name", "男生最爱");
        map.put("category_type", 5);
        int result = productCategoryMapper.insertByMap(map);
        Assert.assertEquals(1, result);
    }

    @Test
    public void insertByObject() throws Exception {
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryName("经典时蔬");
        productCategory.setCategoryType(6);
        int result = productCategoryMapper.insertByObject(productCategory);
        Assert.assertEquals(1, result);
    }

    @Test
    public void findByCategoryType() throws Exception {
        ProductCategory result = productCategoryMapper.findByCategoryType(4);
        Assert.assertNotNull(result);
    }

    @Test
    public void findByCategoryName() throws Exception {
        List<ProductCategory> result = productCategoryMapper.findByCategoryName("女生最爱");
        Assert.assertNotEquals(0, result.size());
    }

    @Test
    public void updateByCategoryType() throws Exception {
        int result = productCategoryMapper.updateByCategoryType("女生最爱榜", 2);
        Assert.assertEquals(1, result);
    }

    @Test
    public void updateByObject() throws Exception {
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryName("经典时蔬榜");
        productCategory.setCategoryType(6);
        int result = productCategoryMapper.updateByObject(productCategory);
        Assert.assertEquals(1, result);
    }

    @Test
    public void deleteByCategoryType() throws Exception {
        int result = productCategoryMapper.deleteByCategoryType(6);
        Assert.assertEquals(1, result);
    }

    @Test
    public void selectByCategoryType() throws Exception {
        ProductCategory productCategory = productCategoryMapper.selectByCategoryType(4);
        Assert.assertNotNull(productCategory);
    }

}