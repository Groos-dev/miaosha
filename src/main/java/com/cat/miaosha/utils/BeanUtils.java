package com.cat.miaosha.utils;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * @author Mr.xin
 */
public class BeanUtils {

    public static void copyProperties(Object source, Object target){
        org.springframework.beans.BeanUtils.copyProperties(source, target);
    }
}
