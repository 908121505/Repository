package com.honglu.quickcall.user.service.util;

import java.lang.reflect.Type;

import com.google.gson.Gson;

/**
 * Created by jinhui.li on 2016/4/1.
 */
public class JsonParseUtil {
    private final static Gson gson = new Gson();

    /**
     * 将对象转换为json字符串
     * @param obj
     * @return
     */
    public static String castToJson(Object obj) {
        return gson.toJson(obj);
    }

    public static Object castToObject(String data, Type type) {
        return gson.fromJson(data, type);
    }

    public static String castToString(Object obj) {
        return gson.toJson(obj);
    }
}
