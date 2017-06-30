package com.qtdbp.trading;

import com.github.tobato.fastdfs.FdfsClientConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Import;


/**
 * 钱塘数据交易中心应用
 *
 * @Import(FdfsClientConfig.class) 注入fastDfs 客户端
 * @author: caidchen
 * @create: 2017-04-12 13:53
 * To change this template use File | Settings | File Templates.
 */
@SpringBootApplication
@EnableDiscoveryClient
@MapperScan(basePackages = "com.qtdbp.trading.mapper")
@Import(FdfsClientConfig.class)
@EnableFeignClients(basePackages={"com.qtdbp.bossclient.service"})
public class WebApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebApplication.class, args);
    }

}
