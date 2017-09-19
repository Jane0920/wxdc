package com.xyr;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by xyr on 2017/9/19.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class LoggerTest {

    private final Logger logger = LoggerFactory.getLogger(LoggerTest.class);

    @Test
    public void test1() {
        String name = "xyr";
        String password = "123456";

        logger.debug("name:{},password:{}debug..........", name, password);
        logger.info("name:{},password:{}info................", name, password);
        logger.error("name:{},password:{}error............", name, password);
    }

}
