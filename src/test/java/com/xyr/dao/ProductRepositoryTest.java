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
        productInfo2.setProductName("菠萝排骨");
        productInfo2.setProductPrice(new BigDecimal(25));
        productInfo2.setCategoryType(2);
        productInfo2.setProductDescription("菠萝的酸甜加排骨肉香");
        productInfo2.setProductStatus(ProductStatusEnum.UP.getCode());
        productInfo2.setProductStock(60);
        productInfo2.setProductIcon("src/xyr/pg.jpg");

        productRepository.saveAndFlush(productInfo2);
    }

    @Test
    public void findByProductStatus() throws Exception {

    }

}