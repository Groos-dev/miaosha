package com.cat.miaosha.common.contants;

import com.cat.miaosha.entity.UserDO;

/**
 * @author Mr.xin
 */
public class UserConstants {
    public static final String USER_PREFIX = "user_";
    public static final ThreadLocal<UserDO> USER_INFO = new ThreadLocal<>();
}
