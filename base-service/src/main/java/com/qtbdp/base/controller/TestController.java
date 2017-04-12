package com.qtbdp.base.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试配置中心
 *
 * @author: caidchen
 * @create: 2017-04-12 15:23
 * To change this template use File | Settings | File Templates.
 */
@RefreshScope
@RestController
public class TestController {

    private Logger logger = LoggerFactory.getLogger(this.getClass()) ;

    @Value("${rabbitmq.host}")
    private String host ;

    @RequestMapping("/")
    public String from() {

        logger.info("请求配置信息【rabbitmq.host】："+ host);

        return host ;
    }

}
