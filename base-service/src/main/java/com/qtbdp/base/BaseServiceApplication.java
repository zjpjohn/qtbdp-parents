package com.qtbdp.base;

import com.github.tobato.fastdfs.FdfsClientConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Import;
import org.springframework.web.bind.annotation.RestController;

/**
 * 基础服务主函数
 *
 * @author: caidchen
 * @create: 2017-04-12 13:48
 * To change this template use File | Settings | File Templates.
 */
@EnableDiscoveryClient
@SpringBootApplication
@RestController
@Import(FdfsClientConfig.class)
public class BaseServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BaseServiceApplication.class, args) ;
    }
}
