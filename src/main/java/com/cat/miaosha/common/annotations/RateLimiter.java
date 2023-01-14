package com.cat.miaosha.common.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author Mr.xin
 */
@Retention(RUNTIME)
@Target({TYPE, METHOD})
public @interface RateLimiter {
//    /**
//     * 时间窗口
//     */
//    int seconds() default 5;
//
//    /**
//     * 访问次数
//     */
//    int maxCount() default 10;
}
