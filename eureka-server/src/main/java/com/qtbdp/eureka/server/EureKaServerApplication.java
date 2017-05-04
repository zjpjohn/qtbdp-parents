package com.qtbdp.eureka.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * 注册中心主函数
 *
 * @author: caidchen
 * @create: 2017-04-12 13:43
 * To change this template use File | Settings | File Templates.
 */
@EnableEurekaServer
@SpringBootApplication
public class EureKaServerApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(EureKaServerApplication.class, args) ;
    }

    /**
     * 提供一个 SpringBootServletInitializer 子类，并覆盖它的 configure 方法。
     * 我们可以把应用的主类改为继承 SpringBootServletInitializer
     *
     * @param application
     * @return
     */
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(EureKaServerApplication.class);
    }
}
