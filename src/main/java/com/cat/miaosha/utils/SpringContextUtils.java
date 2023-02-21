package com.cat.miaosha.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author Mr.xin
 */
@Component
@Slf4j
public class SpringContextUtils implements ApplicationContextAware {
    private static ApplicationContext CONTEXT;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        CONTEXT = applicationContext;
    }

    public static <T> T getBean(String beanName, Class<T> beanClass) {
        return CONTEXT.getBean(beanName, beanClass);
    }
}
