package com.xyr.service;

import com.xyr.po.SellerInfo;

/**
 * Created by xyr on 2017/9/29.
 */
public interface SellerService {

    /**
     * 查询卖家信息
     * @param openid
     * @return
     */
    SellerInfo findSellerInfoByOpenid(String openid);

}
