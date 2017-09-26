package com.xyr.utils;

import com.xyr.enums.CodeEnum;

/**
 * Created by xyr on 2017/9/26.
 */
public class EnumUtil {

    public static <T extends CodeEnum> T getByCode(Integer code, Class<T> enumClass) {
        for (T each: enumClass.getEnumConstants()) {
            if (code.equals(each.getCode())) {
                return each;
            }
        }
        return null;
    }

}
