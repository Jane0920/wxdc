package com.xyr;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.xyr.po.mapper")
public class WxdcApplication {

	public static void main(String[] args) {
		SpringApplication.run(WxdcApplication.class, args);
	}
}
