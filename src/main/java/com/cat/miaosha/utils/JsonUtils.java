package com.cat.miaosha.utils;


import cn.hutool.json.JSONUtil;

/**
 * @author Mr.xin
 */
public class JsonUtils {
    public static String objectToJson(Object o){
        return JSONUtil.toJsonStr(o);
    }
    public static <T> T jsonToObject(String json, Class<T> beanClass){
        return JSONUtil.toBean(json, beanClass);
    }
}
