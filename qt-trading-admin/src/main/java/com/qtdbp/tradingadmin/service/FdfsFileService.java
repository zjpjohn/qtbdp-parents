package com.qtdbp.tradingadmin.service;

import com.qtdbp.trading.utils.fdfs.FastDFSFile;
import com.qtdbp.trading.utils.fdfs.FileManager;
import com.qtdbp.trading.utils.fdfs.FileManagerConfig;
import org.csource.common.NameValuePair;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * 文件服务 - 下载
 *
 * @author: caidchen
 * @create: 2017-04-28 10:06
 * To change this template use File | Settings | File Templates.
 */
@Service
public class FdfsFileService {

    /**
     * 文件上传
     * @param attach
     * @return
     */
    public String uploadFile(MultipartFile attach) {

        String filePath = null ;

        try {
            // 获取文件后缀名
            String ext = attach.getOriginalFilename().substring(attach.getOriginalFilename().lastIndexOf(".")+1);

            filePath = uploadFile(attach.getBytes(), attach.getOriginalFilename(), attach.getSize(), ext) ;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return filePath ;
    }

    public String uploadFile(byte[] content, String fileName, long fileLength, String ext) {
        FastDFSFile file = new FastDFSFile(content,ext);
        NameValuePair[] meta_list = new NameValuePair[4];
        meta_list[0] = new NameValuePair("fileName", fileName);
        meta_list[1] = new NameValuePair("fileLength", String.valueOf(fileLength));
        meta_list[2] = new NameValuePair("fileExt", ext);
        meta_list[3] = new NameValuePair("fileAuthor", FileManagerConfig.FILE_DEFAULT_AUTHOR);
        return FileManager.upload(file, meta_list);
    }

}
