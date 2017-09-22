package com.xyr.service;

import com.xyr.dto.CartDTO;
import com.xyr.po.ProductInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 商品
 * Created by xyr on 2017/9/20.
 */
public interface ProductService {

    ProductInfo findOne(String productId) throws Exception;

    /**
     * 查询在架的商品列表
     * @return
     */
    List<ProductInfo> findUpAll();

    Page<ProductInfo> findAll(Pageable pageable) throws Exception;

    ProductInfo save(ProductInfo productInfo) throws Exception;

    /**
     * 加库存
     */
    void increaseStock(List<CartDTO> cartDTOList);

    /**
     * 减库存
     */
    void decreaseStock(List<CartDTO> cartDTOList);

}
