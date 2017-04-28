package com.qtdbp.trading.utils.fdfs;

import com.qtdbp.trading.exception.GlobalException;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.io.File;

/**
 * FastDFS Java客户端工具类
 *
 * @author: caidchen
 * @create: 2017-04-28 14:12
 * To change this template use File | Settings | File Templates.
 */
@Component
public class FileManager implements FileManagerConfig {

    private static final long serialVersionUID = 1L;
    private static TrackerClient trackerClient;
    private static TrackerServer trackerServer;
    private static StorageServer storageServer;
    private static StorageClient storageClient;

    static {
        try {
            String classPath = new File(FileManager.class.getResource("/").getFile()).getCanonicalPath();

            String fdfsClientConfigFilePath = classPath + File.separator + CLIENT_CONFIG_FILE;
            ClientGlobal.init(fdfsClientConfigFilePath);

            trackerClient = new TrackerClient();
            trackerServer = trackerClient.getConnection();

            storageClient = new StorageClient(trackerServer, storageServer);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * <strong>方法概要： 文件上传</strong> <br>
     * <strong>创建时间： 2016-9-26 上午10:26:11</strong> <br>
     *
     * @param file
     * @return fileAbsolutePath
     */
    public static String upload(FastDFSFile file,NameValuePair[] valuePairs) {
        String[] uploadResults = null;
        try {
            uploadResults = storageClient.upload_file(file.getContent(),file.getExt(), valuePairs);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String groupName = uploadResults[0];
        String remoteFileName = uploadResults[1];

        String fileAbsolutePath = groupName + SEPARATOR + remoteFileName;
                //PROTOCOL
                //+ TRACKER_NGNIX_ADDR
                //+ trackerServer.getInetSocketAddress().getHostName()
                //+ SEPARATOR + TRACKER_NGNIX_PORT
                //+ SEPARATOR +

        return fileAbsolutePath;
    }

    /**
     * <strong>方法概要： 文件下载</strong> <br>
     * <strong>创建时间： 2016-9-26 上午10:28:21</strong> <br>
     *
     * @param groupName
     * @param remoteFileName
     * @return returned value comment here
     */
    public static ResponseEntity<byte[]> download(String groupName,
                                                  String remoteFileName, String specFileName) throws GlobalException {
        byte[] content = null;
        HttpHeaders headers = new HttpHeaders();
        try {
            content = storageClient.download_file(groupName, remoteFileName);
            headers.setContentDispositionFormData("attachment",  new String(specFileName.getBytes("UTF-8"),"iso-8859-1"));
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new GlobalException(e.getMessage()) ;
        }
        return new ResponseEntity<>(content, headers, HttpStatus.CREATED);
    }
}