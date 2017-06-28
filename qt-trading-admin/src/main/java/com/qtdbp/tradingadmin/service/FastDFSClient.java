package com.qtdbp.tradingadmin.service;

import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.exception.FdfsUnsupportStorePathException;
import com.github.tobato.fastdfs.proto.storage.DownloadByteArray;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.qtdbp.tradingadmin.exception.GlobalAdminException;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

/**
 * FastDFS文件上传下载包装类
 *
 * @author: caidchen
 * @create: 2017-05-24 10:54
 * To change this template use File | Settings | File Templates.
 */
@Component
public class FastDFSClient {

    private final Logger logger = LoggerFactory.getLogger(FastDFSClient.class);

    @Autowired
    private FastFileStorageClient storageClient;

    /**
     * 上传文件
     * @param file 文件对象
     * @return 文件访问地址
     * @throws IOException
     */
    public String uploadFile(MultipartFile file) throws IOException {
        return uploadFile(file.getInputStream(), file.getSize(), FilenameUtils.getExtension(file.getOriginalFilename())) ;
    }

    /**
     * 上传文件
     * @param inputStream 文件流
     * @param size  大小
     * @param ext   文件类型
     * @return
     * @throws IOException
     */
    public String uploadFile(InputStream inputStream, long size, String ext) throws IOException {
        StorePath storePath = storageClient.uploadFile(inputStream, size, ext,null);
        return getResAccessUrl(storePath);
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

    // 封装图片完整URL地址
    private String getResAccessUrl(StorePath storePath) {
        String fileUrl = storePath.getFullPath();
        return fileUrl;
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

    /**
     * 文件下载
     * @param fileUrl
     * @param fileName
     * @return
     */
    public ResponseEntity<byte[]> downloadFilePublic(String fileUrl, String fileName) throws GlobalAdminException {
        byte[] content = null;
        HttpHeaders headers = null ;
        try {
            StorePath storePath = StorePath.praseFromUrl(fileUrl);
            content = storageClient.downloadFile(storePath.getGroup(), storePath.getPath(), new DownloadByteArray()) ;

            String realName = fileName +"."+ FilenameUtils.getExtension(fileUrl) ;

            headers = new HttpHeaders();
            headers.setContentDispositionFormData("attachment",  new String(realName.getBytes("UTF-8"),"iso-8859-1"));
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new GlobalAdminException("文件："+fileName+"下载失败，错误信息："+e.getMessage()) ;
        }
        return new ResponseEntity<byte[]>(content, headers, HttpStatus.CREATED);
    }
}
