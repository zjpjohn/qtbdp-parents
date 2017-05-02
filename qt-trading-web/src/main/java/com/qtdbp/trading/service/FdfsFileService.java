package com.qtdbp.trading.service;

import com.qtdbp.trading.exception.GlobalException;
import com.qtdbp.trading.mapper.DataProductMapper;
import com.qtdbp.trading.mapper.DataTransactionOrderMapper;
import com.qtdbp.trading.model.DataProductModel;
import com.qtdbp.trading.model.DataTransactionOrderModel;
import com.qtdbp.trading.utils.fdfs.FastDFSFile;
import com.qtdbp.trading.utils.fdfs.FileManager;
import com.qtdbp.trading.utils.fdfs.FileManagerConfig;
import org.csource.common.NameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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

    @Autowired
    private DataTransactionOrderMapper orderMapper ;
    @Autowired
    private DataProductMapper productMapper ;

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
            if(filePath == null) throw new GlobalException("文件不存在") ;
            String fileName = order.getOrderSubject() ;

            String substr = filePath.substring(filePath.indexOf("group"));
            String group = substr.split("/")[0];
            String remoteFileName = substr.substring(substr.indexOf("/")+1);
            String specFileName = fileName + substr.substring(substr.indexOf("."));
            fileEntity = FileManager.download(group, remoteFileName,specFileName);

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
     * 文件上传
     * @param attach
     * @return
     */
    public String uploadFile(MultipartFile attach) {

        String filePath = null ;

        try {
            // 获取文件后缀名
            String ext = attach.getOriginalFilename().substring(attach.getOriginalFilename().lastIndexOf(".")+1);

            FastDFSFile file = new FastDFSFile(attach.getBytes(),ext);
            NameValuePair[] meta_list = new NameValuePair[4];
            meta_list[0] = new NameValuePair("fileName", attach.getOriginalFilename());
            meta_list[1] = new NameValuePair("fileLength", String.valueOf(attach.getSize()));
            meta_list[2] = new NameValuePair("fileExt", ext);
            meta_list[3] = new NameValuePair("fileAuthor", FileManagerConfig.FILE_DEFAULT_AUTHOR);
            filePath = FileManager.upload(file, meta_list);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return filePath ;
    }

}
