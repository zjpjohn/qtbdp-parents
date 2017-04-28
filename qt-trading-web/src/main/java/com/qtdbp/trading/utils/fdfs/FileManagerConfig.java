package com.qtdbp.trading.utils.fdfs;

import java.io.Serializable;

/**
 * FastDFS配置信息
 *
 * @author: caidchen
 * @create: 2017-04-28 14:11
 * To change this template use File | Settings | File Templates.
 */
public interface  FileManagerConfig extends Serializable {

    public static final String FILE_DEFAULT_AUTHOR = "QTDATA";

    public static final String PROTOCOL = "http://";

    public static final String SEPARATOR = "/";

    public static final String TRACKER_NGNIX_ADDR = "192.168.36.39";

    public static final String TRACKER_NGNIX_PORT = "";

    public static final String CLIENT_CONFIG_FILE = "fdfs_client.properties";
}