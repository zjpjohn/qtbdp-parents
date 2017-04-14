package com.qtbdp.base.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 * User: 浩
 * Date: 2017/4/13
 * Time: 23:16
 * To change this template use File | Settings | File Templates.
 */
@Component
public class AppConfig {

    @Value("${fdfs-upload.host}")
    private String host ;

    @Value("${fdfs-upload.port}")
    private String port ;

    /**
     * fdfs 访问的host
     */
    public String getFdfsHost() {
        return this.host ;
    }

    /**
     * fdfs 访问的port
     */
    public String getFdfsStoragePort() {
        return this.port ;
    }

}
