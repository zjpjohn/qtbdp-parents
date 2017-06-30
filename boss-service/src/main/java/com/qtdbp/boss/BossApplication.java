package com.qtdbp.boss;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 计费系统主函数
 *
 * @author: caidchen
 * @create: 2017-06-28 17:07
 * To change this template use File | Settings | File Templates.
 */
@SpringBootApplication
@EnableEurekaClient
@RestController
public class BossApplication {

    public static void main(String[] args) {
        SpringApplication.run(BossApplication.class, args) ;
    }


    @Value("${server.port}")
    String port;

    @RequestMapping("/hi")
    public String home(@RequestParam String name) {
        return "hi "+name+",i am from port:" +port;
    }
}
