package com.xyr.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Created by Administrator on 2017/9/23.
 */
@RestController
@RequestMapping("/weixin")
@Slf4j
public class WeiXinController {

    /**
     * 微信授权回调方法 手工获取openid
     */
    @GetMapping("/auth")
    public void auth(@RequestParam("Code") String code) {
        log.info("进入auth方法。。。。。。。");

        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?" +
                "appid=wxd898fcb01713c658&secret=29d8a650db31472aa87800e3b0d739f2&code="
                + code + "&grant_type=authorization_code";
        // RestTemplate 是 Spring 提供的用于访问 Rest 服务的客户端
        //通过restTemplate访问url
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getForObject(url, String.class);
    }

}
