package com.cat.miaosha.config.swagger;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.List;

import static java.util.Collections.singletonList;

/**
 * @author Mr.xin
 */
@Configuration
@EnableSwagger2
@Slf4j
@Profile({"dev", "test"})
public class SwaggerConfig {


    /**
     * apiInfo() 增加API相关信息
     * 所有的注解
     * .apis(RequestHandlerSelectors.any())
     * 指定部分注解1.Api.class(@APi),2.ApiOperation.class(@ApiOperation),3.ApiImplicitParam.class(@ApiImplicitParam)
     * .apis(RequestHandlerSelectors.withMethodAnnotation(Api.class))
     * 指定包路径
     * .apis(RequestHandlerSelectors.basePackage("这里填写需要的路径"))
     * .paths() 这个是包路径下的路径,PathSelectors.any()是包下所有路径
     */
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .useDefaultResponseMessages(false)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                // 全局token配置
                .securityContexts(Arrays.asList(securityContext()))
                .securitySchemes(Arrays.asList(new ApiKey("token", "token", "header")));


    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("秒杀项目")
                .description("springboot | swagger")
                // 作者信息
                .contact(new Contact("cat", "localhost:8080", "3117383592@qq.com"))
                .version("0.0.1")
                .build();
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(defaultAuth())
                .build();
    }

    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope
                = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return singletonList(
                new SecurityReference("token", authorizationScopes));
    }

    /**
     * 资源路径映射
     *
     * @param registry
     */


}
