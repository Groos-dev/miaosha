package com.cat.miaosha.common.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 这个注解不需要校验登录转态
 * @author Mr.xin
 */
@Retention(RUNTIME)
@Target({TYPE,METHOD})
public @interface Access {
}
