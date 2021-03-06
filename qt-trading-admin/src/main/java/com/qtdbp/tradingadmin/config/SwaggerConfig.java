package com.qtdbp.tradingadmin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger配置类:
 * Created with IntelliJ IDEA.
 * User: 浩
 * Date: 2017/4/9
 * Time: 22:43
 * To change this template use File | Settings | File Templates.
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    /**
     * apis 指定暴露rest接口的路劲
     * @return
     */
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.qtdbp.tradingadmin.api"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("【钱塘数据交易平台】 - 【后台管理业务应用APIs】")
                .description("基于Spring Cloud微服务架构源码查看地址：https://github.com/caidchen1988/qtbdp-parents")
                .termsOfServiceUrl("https://github.com/caidchen1988/qtbdp-parents")
                .contact("caidchen")
                .version("v1.0")
                .build();
    }
}
