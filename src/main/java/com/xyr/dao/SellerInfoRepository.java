package com.xyr.dao;

import com.xyr.po.SellerInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by xyr on 2017/9/29.
 */
public interface SellerInfoRepository extends JpaRepository<SellerInfo, String> {

    SellerInfo findByOpenid(String openid);

}
