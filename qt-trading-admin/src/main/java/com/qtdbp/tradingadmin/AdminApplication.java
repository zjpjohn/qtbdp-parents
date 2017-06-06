package com.qtdbp.tradingadmin;

import com.github.tobato.fastdfs.FdfsClientConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


/**
 * 钱塘数据交易中心后台管理应用
 *
 * @Import(FdfsClientConfig.class) 注入fastDfs 客户端
 * @author: caidchen
 * @create: 2017-04-12 13:53
 * To change this template use File | Settings | File Templates.
 */
@SpringBootApplication
@EnableDiscoveryClient
@MapperScan(basePackages = "com.qtdbp.tradingadmin.mapper")
@EnableJpaRepositories(basePackages = "com.qtdbp.tradingadmin.base.security.repository")
@Import(FdfsClientConfig.class)
public class AdminApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(AdminApplication.class, args);
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
        return application.sources(AdminApplication.class);
    }

}
