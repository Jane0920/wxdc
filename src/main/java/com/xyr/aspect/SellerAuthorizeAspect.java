package com.xyr.aspect;

import com.xyr.constant.CookieConstant;
import com.xyr.constant.RedisConstant;
import com.xyr.exception.SellAuthorizeException;
import com.xyr.utils.CookieUtil;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * 卖家授权拦截器
 * Created by xyr on 2017/9/29.
 */
@Aspect
@Component
public class SellerAuthorizeAspect {

    @Autowired
    private static final Logger log = LoggerFactory.getLogger(SellerAuthorizeAspect.class);

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Pointcut("execution(public * com.xyr.controller.Seller*.*(..)) " +
        "&& !execution(public * com.xyr.controller.SellerUserController.*(..))")
    public void verify() {}

    @Before("verify()")
    public void doVerify() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        //查询cookie
        Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);
        if (cookie == null) {
            log.warn("【登录校验】Cookie中未找到token");
            throw new SellAuthorizeException();
        }

        //在redis中查找token
        String tokenValue = redisTemplate.opsForValue().get(String.format(RedisConstant.TOKEN_PREFIX, cookie.getValue()));
        if (StringUtils.isEmpty(tokenValue)) {
            log.warn("【登录校验】Redis中未找到token");
            throw new SellAuthorizeException();
        }
    }

}
