package com.qtbdp.eureka.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
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
public class EureKaServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(EureKaServerApplication.class, args) ;
    }
}
