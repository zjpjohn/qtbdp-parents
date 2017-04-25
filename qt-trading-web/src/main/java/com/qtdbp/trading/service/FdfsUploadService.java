package com.qtdbp.trading.service;

import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.exception.FdfsUnsupportStorePathException;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.qtdbp.trading.config.AppConfig;
import com.qtdbp.trading.constants.AppConstants;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.Charset;

/**
 * 文件上传服务，基于FastDFS文件系统
 * Created with IntelliJ IDEA.
 * User: 浩
 * Date: 2017/4/13
 * Time: 22:55
 * To change this template use File | Settings | File Templates.
 */
@Service
public class FdfsUploadService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private FastFileStorageClient storageClient;

    @Autowired
    private AppConfig appConfig;   // 项目参数配置

    /**
     * 上传文件
     * @param file 文件对象
     * @return 文件访问地址
     * @throws IOException
     */
    public String uploadFile(MultipartFile file) throws IOException {
        StorePath storePath = storageClient.uploadFile(file.getInputStream(),file.getSize(), FilenameUtils.getExtension(file.getOriginalFilename()),null);
        return getAccessUrl(storePath);
    }

    /**
     * 将一段字符串生成一个文件上传
     * @param content 文件内容
     * @param fileExtension
     * @return
     */
    public String uploadFile(String content, String fileExtension) {
        byte[] buff = content.getBytes(Charset.forName("UTF-8"));
        ByteArrayInputStream stream = new ByteArrayInputStream(buff);
        StorePath storePath = storageClient.uploadFile(stream,buff.length, fileExtension,null);
        return getResAccessUrl(storePath);
    }

    /**
     * 封装图片完整URL地址
     * @param storePath
     * @return
     */
    private String getResAccessUrl(StorePath storePath) {
        return AppConstants.HTTP_PRODOCOL+appConfig.getFdfsHost() + ":" + appConfig.getFdfsStoragePort() + "/" + storePath.getFullPath();
    }

    /**
     * 返回相对地址
     * @param storePath
     * @return
     */
    private String getAccessUrl(StorePath storePath) {
        return storePath.getFullPath();
    }

    /**
     * 删除文件
     * @param fileUrl 文件访问地址
     * @return
     */
    public void deleteFile(String fileUrl) {
        if (StringUtils.isEmpty(fileUrl)) {
            return;
        }
        try {
            StorePath storePath = StorePath.praseFromUrl(fileUrl);
            storageClient.deleteFile(storePath.getGroup(), storePath.getPath());
        } catch (FdfsUnsupportStorePathException e) {
            logger.warn(e.getMessage());
        }
    }

}
