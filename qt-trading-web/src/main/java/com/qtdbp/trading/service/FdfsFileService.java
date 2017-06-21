package com.qtdbp.trading.service;

import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.proto.storage.DownloadByteArray;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.qtdbp.trading.exception.GlobalException;
import com.qtdbp.trading.mapper.DataProductMapper;
import com.qtdbp.trading.mapper.DataTransactionOrderMapper;
import com.qtdbp.trading.model.DataProductModel;
import com.qtdbp.trading.model.DataTransactionOrderModel;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

/**
 * 文件服务 - 下载
 *
 * @author: caidchen
 * @create: 2017-04-28 10:06
 * To change this template use File | Settings | File Templates.
 */
@Service
public class FdfsFileService {

    @Autowired
    private DataTransactionOrderMapper orderMapper ;
    @Autowired
    private DataProductMapper productMapper ;
    @Autowired
    private FastFileStorageClient storageClient;

    /**
     * 下载文件
     * @param orderNo 订单号
     * @param currUserId 当前用户ID
     * @param check true检查文件,false下载文件
     * @return
     * @throws GlobalException
     */
    @Transactional
    public ResponseEntity<byte[]> downloadFile(String orderNo, Integer currUserId, boolean check) throws GlobalException {

        DataTransactionOrderModel order = orderMapper.findOrderByNo(orderNo) ;
        if(order == null) throw new GlobalException("此订单已不存在") ;

        // 对比当前登录用户id与订单买方用户id
        if(currUserId == null || currUserId.intValue() != order.getUserId().intValue()) throw new GlobalException("非法操作") ;

        ResponseEntity<byte[]> fileEntity = null ;
        try {
            // 下载文档
            String filePath = order.getDownloadUrl() ;
            String fileName = order.getOrderSubject() ;
            fileEntity = downloadFilePublic(filePath,fileName);
            // 更新产品下载次数
            if(order.getProductId() != null && !check){
                DataProductModel productModel = new DataProductModel() ;
                productModel.setEditorId(order.getUserId());
                productModel.setDownloadCount(1);
                productModel.setId(order.getProductId());
                productMapper.updateProduct(productModel);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new GlobalException(e.getMessage()) ;
        }
        return fileEntity ;
    }

    /**
     * 下载免费数据包文件
     * @param productId
     * @return
     * @throws GlobalException
     */
    public ResponseEntity<byte[]> downloadFreeFile(Integer productId) throws GlobalException {

        if (productId == null) throw new GlobalException("获取订单的ID为空");
        DataProductModel productModel = productMapper.findProductsById(productId);
        if (productModel == null) throw new GlobalException("该数据包已不存在");
        String filePath = productModel.getFileUrl();
        String fileName = productModel.getDesignation();
        ResponseEntity<byte[]> fileEntity = null ;

        try {
            fileEntity = downloadFilePublic(filePath, fileName);
            //更新产品下载次数
            productModel = new DataProductModel();
            productModel.setId(productId);
            productModel.setDownloadCount(1);
            int i = productMapper.updateProduct(productModel);
            if (!(i > 0)) throw new GlobalException("更新下载次数失败");
        } catch (GlobalException e) {
            e.printStackTrace();
            throw new GlobalException(e.getMessage());
        }

        return fileEntity;
    }


    /**
     * 文件上传
     * @param attach
     * @return
     */
    public String uploadFile(MultipartFile attach) throws GlobalException {

        StorePath storePath ;
        try {
            storePath = storageClient.uploadFile(attach.getInputStream(),attach.getSize(), FilenameUtils.getExtension(attach.getOriginalFilename()),null);
        } catch (IOException e) {
            e.printStackTrace();
            throw new GlobalException("文件："+attach.getName()+"上传失败，错误信息："+e.getMessage()) ;
        }
        return storePath.getFullPath() ;
    }

    /**
     * 文件下载
     * @param fileUrl
     * @param fileName
     * @return
     */
    public ResponseEntity<byte[]> downloadFilePublic(String fileUrl, String fileName) throws GlobalException {
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
            throw new GlobalException("文件："+fileName+"下载失败，错误信息："+e.getMessage()) ;
        }
        return new ResponseEntity<byte[]>(content, headers, HttpStatus.CREATED);
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

    // 封装图片完整URL地址
    private String getResAccessUrl(StorePath storePath) {
        String fileUrl = storePath.getFullPath();
        return fileUrl;
    }
}
