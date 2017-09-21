package com.xyr.utils;

import org.apache.commons.lang3.RandomStringUtils;

/**
 * Created by xyr on 2017/9/21.
 */
public class KeyUtil {

    public static synchronized String getUniqueKey() {
        String key = RandomStringUtils.randomNumeric(6);
        return key;
    }

}
