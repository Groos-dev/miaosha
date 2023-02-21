package com.cat.miaosha.utils;

import cn.hutool.core.util.IdUtil;

/**
 * @author Mr.xin
 */
public class IDUtils {
    public static long generateId(){
        return IdUtil.getSnowflake().nextId();
    }
}
