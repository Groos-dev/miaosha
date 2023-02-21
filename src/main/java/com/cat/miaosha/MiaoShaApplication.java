package com.cat.miaosha;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author Mr.xin
 */
@MapperScan("com.cat.miaosha.dao")
@SpringBootApplication
@EnableTransactionManagement
@EnableAspectJAutoProxy
public class MiaoShaApplication {
    public static void main(String[] args) {
        SpringApplication.run(MiaoShaApplication.class, args);
    }
}
