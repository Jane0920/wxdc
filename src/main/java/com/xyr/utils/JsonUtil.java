package com.xyr.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Created by xyr on 2017/9/25.
 */
public class JsonUtil {

    public static String toJson(Object object) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
        Gson gson = gsonBuilder.create();
        return gson.toJson(object);
    }

}
