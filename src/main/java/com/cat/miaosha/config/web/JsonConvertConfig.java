package com.cat.miaosha.config.web;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.text.SimpleDateFormat;
import java.util.List;


/**
 * 这个bean 或覆盖mvc默认的配置
 * @author Mr.xin
 */
@Configuration
public class JsonConvertConfig  {

    @Bean("jackson2HttpMessageConverter")
    public MappingJackson2HttpMessageConverter converter(){
        // long类型字段的转化可能导致精度丢失,转成string就能解决这个问题
        MappingJackson2HttpMessageConverter converter =
                new MappingJackson2HttpMessageConverter();

        ObjectMapper objectMapper = converter.getObjectMapper();
        SimpleModule simpleModule = new SimpleModule();
        // long 类型转 string
        simpleModule.addSerializer(Long.class, ToStringSerializer.instance);
        simpleModule.addSerializer(Long.TYPE, ToStringSerializer.instance);
        objectMapper.registerModule(simpleModule);

        // 时间格式化
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));

        converter.setObjectMapper(objectMapper);
       return converter;
    }

}
