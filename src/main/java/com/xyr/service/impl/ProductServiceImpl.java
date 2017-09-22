package com.xyr.service.impl;

import com.xyr.dao.ProductRepository;
import com.xyr.dto.CartDTO;
import com.xyr.enums.ProductStatusEnum;
import com.xyr.enums.ResultEnum;
import com.xyr.exception.SellException;
import com.xyr.po.ProductInfo;
import com.xyr.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by xyr on 2017/9/20.
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public ProductInfo findOne(String productId) throws Exception{
        return productRepository.findOne(productId);
    }

    @Override
    public List<ProductInfo> findUpAll(){
        return productRepository.findByProductStatus(ProductStatusEnum.UP.getCode());
    }

    @Override
    public Page<ProductInfo> findAll(Pageable pageable) throws Exception{
        return productRepository.findAll(pageable);
    }

    @Override
    public ProductInfo save(ProductInfo productInfo) throws Exception{
        return productRepository.saveAndFlush(productInfo);
    }

    @Override
    public void increaseStock(List<CartDTO> cartDTOList) {
        for (CartDTO cartDTO : cartDTOList) {
            ProductInfo productInfo = productRepository.findOne(cartDTO.getProductId());
            if (productInfo == null)
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);

            int stock = productInfo.getProductStock() + cartDTO.getProductQuantity();

            productInfo.setProductStock(stock);
            productRepository.save(productInfo);
        }
    }

    @Override
    @Transactional
    public void decreaseStock(List<CartDTO> cartDTOList) {
        for (CartDTO cartDTO : cartDTOList) {
            ProductInfo productInfo = productRepository.findOne(cartDTO.getProductId());
            if (productInfo == null)
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);

            int stock = productInfo.getProductStock() - cartDTO.getProductQuantity();
            if (stock < 0)
                throw new SellException(ResultEnum.PRODUCT_STOCK_ERROR);

            productInfo.setProductStock(stock);
            productRepository.save(productInfo);
        }
    }
}
