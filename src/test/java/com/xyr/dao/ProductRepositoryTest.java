package com.xyr.dao;

import com.xyr.enums.ProductStatusEnum;
import com.xyr.po.ProductInfo;
import com.xyr.utils.KeyUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * Created by xyr on 2017/9/20.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void saveTest() throws Exception {
        /*ProductInfo productInfo1 = new ProductInfo();
        productInfo1.setProductId(KeyUtil.getUniqueKey());
        productInfo1.setProductName("红糖糍粑");
        productInfo1.setProductPrice(new BigDecimal(10));
        productInfo1.setCategoryType(3);
        productInfo1.setProductDescription("糯糯甜甜，好吃不贵");
        productInfo1.setProductStatus(ProductStatusEnum.UP.getCode());
        productInfo1.setProductStock(50);
        productInfo1.setProductIcon("src/xyr/htcb.jpg");

        productRepository.saveAndFlush(productInfo1);*/

        ProductInfo productInfo2 = new ProductInfo();
        productInfo2.setProductId(KeyUtil.getUniqueKey());
        productInfo2.setProductName("辣子鸡丁");
        productInfo2.setProductPrice(new BigDecimal(32));
        productInfo2.setCategoryType(2);
        productInfo2.setProductDescription("四川人的味道");
        productInfo2.setProductStatus(ProductStatusEnum.UP.getCode());
        productInfo2.setProductStock(100);
        productInfo2.setProductIcon("https://g-search1.alicdn.com/img/bao/uploaded/i4/i1/2184754624/TB18uz.fiAKL1JjSZFoXXagCFXa_!!0-item_pic.jpg_250x250.jpg_.webp");

        productRepository.saveAndFlush(productInfo2);
    }

    @Test
    public void findByProductStatus() throws Exception {

    }

}