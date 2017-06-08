package com.qtdbp.tradingadmin.service;

import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.proto.storage.DownloadByteArray;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.qtdbp.trading.exception.GlobalException;
import com.qtdbp.trading.model.CrawlersRoleModel;
import com.qtdbp.tradingadmin.mapper.CrawlersRoleMapper;
import com.qtdbp.tradingadmin.mapper.DataProductMapper;
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
    private DataProductMapper productMapper ;

    @Autowired
    private CrawlersRoleMapper roleMapper;

    @Autowired
    private FastDFSClient client;

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
            fileEntity = client.downloadFilePublic(filePath, fileName);
/*            //更新产品下载次数
            productModel = new DataProductModel();
            productModel.setId(productId);
            productModel.setDownloadCount(1);
            int i = productMapper.updateProduct(productModel);
            if (!(i > 0)) throw new GlobalException("更新下载次数失败");*/
        } catch (GlobalException e) {
            e.printStackTrace();
            throw new GlobalException(e.getMessage());
        }

        return fileEntity;
    }



    /**
     * 下载爬虫规则文件
     * @param roleId
     * @return
     * @throws GlobalException
     */
    public ResponseEntity<byte[]> downloadFreeRoleFile(Integer roleId) throws GlobalException {

        if (roleId == null) throw new GlobalException("获取订单的ID为空");
        CrawlersRoleModel roleModel = roleMapper.findRoleById(roleId);
        if (roleModel == null) throw new GlobalException("该爬虫规则已不存在");
        String filePath = roleModel.getFilePath();
        String fileName = roleModel.getName();
        ResponseEntity<byte[]> fileEntity = null ;

        try {
            fileEntity = client.downloadFilePublic(filePath, fileName);
        } catch (GlobalException e) {
            e.printStackTrace();
            throw new GlobalException(e.getMessage());
        }

        return fileEntity;
    }

}
