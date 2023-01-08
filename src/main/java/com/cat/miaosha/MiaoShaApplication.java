package com.cat.miaosha;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Mr.xin
 */
@MapperScan("com.cat.miaosha.dao")
@SpringBootApplication
public class MiaoShaApplication {
    public static void main(String[] args) {
        SpringApplication.run(MiaoShaApplication.class, args);
    }
}
