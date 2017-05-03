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

    String FILE_DEFAULT_AUTHOR = "QTDATA";

    String SEPARATOR = "/";

    String CLIENT_CONFIG_FILE = "fdfs_client.properties";
}