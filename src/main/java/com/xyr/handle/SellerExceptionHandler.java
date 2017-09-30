package com.xyr.handle;

import com.xyr.config.ProjectURL;
import com.xyr.exception.SellAuthorizeException;
import com.xyr.exception.SellException;
import com.xyr.utils.ResultVOUtil;
import com.xyr.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
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
    @ResponseStatus(HttpStatus.FORBIDDEN)  //返回403相应
    public ModelAndView handleSellerAuthorizeException() {
        //http://sell.natapp4.cc/sell/wechat/qrAuthorize?returnUrl=http://sell.natapp4.cc/sell/seller/login
        return new ModelAndView("redirect:"
                .concat(projectURL.getWechatOpenAuthorize())
                .concat("/sell/wechat/qrAuthorize")
                .concat("?returnUrl=")
                .concat(projectURL.getSell())
                .concat("/sell/seller/login"));
    }

    @ExceptionHandler(value = SellException.class)
    @ResponseBody
    public ResultVO handleSellerException(SellException e) {
        return ResultVOUtil.error(e.getCode(), e.getMessage());
    }

}
