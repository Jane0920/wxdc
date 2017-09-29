package com.xyr.handle;

import com.xyr.config.ProjectURL;
import com.xyr.exception.SellAuthorizeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by xyr on 2017/9/29.
 */
@ControllerAdvice
public class SellerExceptionHandler {

    @Autowired
    private ProjectURL projectURL;

    /**
     * 拦截登录异常
     */

    @ExceptionHandler(value = SellAuthorizeException.class)
    public ModelAndView handleSellerAuthorizeException() {
        //http://sell.natapp4.cc/sell/wechat/qrAuthorize?returnUrl=http://sell.natapp4.cc/sell/seller/login
        return new ModelAndView("redirect:"
                .concat(projectURL.getWechatOpenAuthorize())
                .concat("/sell/wechat/qrAuthorize")
                .concat("?returnUrl=")
                .concat(projectURL.getSell())
                .concat("/sell/seller/login"));
    }

}
