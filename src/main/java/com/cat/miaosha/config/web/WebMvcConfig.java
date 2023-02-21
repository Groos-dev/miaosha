package com.cat.miaosha.config.web;

import com.cat.miaosha.interceptor.LoginInterceptor;
import com.cat.miaosha.interceptor.RateLimiterInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Mr.xin
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {


    @Resource(name = "loginInterceptor")
    private LoginInterceptor loginInterceptor;


    @Resource(name = "rateLimiterInterceptor")
    private RateLimiterInterceptor rateLimiterInterceptor;
    @Resource(name = "jackson2HttpMessageConverter")
    private MappingJackson2HttpMessageConverter converter;
    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor).addPathPatterns("/**");
        registry.addInterceptor(rateLimiterInterceptor).addPathPatterns("/**");
    }

    /**
     * swagger 的配置
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/js/**").addResourceLocations("classpath:/js/");
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler(    "/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    /**
     * 添加序列化配置
     * @param converters json序列化
     */
    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(0, converter);
    }
}
