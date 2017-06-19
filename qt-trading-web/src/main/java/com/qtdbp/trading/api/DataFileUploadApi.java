package com.qtdbp.trading.api;


import com.qtd.utils.OssUpload;
import com.qtdbp.poi.excel.ExcelReaderUtil;
import com.qtdbp.poi.zip.ZipUtil;
import com.qtdbp.trading.controller.BaseController;
import com.qtdbp.trading.exception.ErrorCode;
import com.qtdbp.trading.exception.GlobalException;
import com.qtdbp.trading.service.DataProductService;
import com.qtdbp.trading.service.FdfsFileService;
import com.qtdbp.trading.service.PoiParserService;
import com.qtdbp.trading.service.security.model.SysUser;
import com.qtdbp.trading.utils.CommonUtil;
import com.qtdbp.trading.utils.Message;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 文件上传API接口
 *
 * @author: caidchen
 * @create: 2017-04-25 17:03
 * To change this template use File | Settings | File Templates.
 */
@Api(description = "文件上传 - 业务API接口")
@RestController
public class DataFileUploadApi extends BaseController {

    @Autowired
    private FdfsFileService uploadService ;

    @Autowired
    private DataProductService productService ;

    @Autowired
    private PoiParserService poiParserService ;

    //===================================================================
    // 图片上传接口-支持阿里云Oss服务
    //===================================================================

    @ApiOperation(value = "上传图片接口，支持jpg/jpeg、gif、png")
    @RequestMapping(value = "/api/upload/img", method = RequestMethod.POST)
    public ModelMap imgUpload(@RequestParam MultipartFile img) throws GlobalException {

        ModelMap map = new ModelMap();
        boolean isSuccess = false ;
        String imgUrl ;
        try {

            if(img == null) throw new GlobalException("图片不存在，请先上传图片") ;

            imgUrl = OssUpload.uploadFileBytes(img.getBytes(), img.getContentType()) ;
            if(imgUrl != null) {
                imgUrl = OssUpload.imagepath +"/"+ imgUrl ;

                isSuccess = true ;
            }
        } catch (Exception e) {
            throw new GlobalException(e.getMessage()) ;
        }

        map.put("success", isSuccess) ;
        map.put("img", imgUrl) ;

        return map;
    }

    //===================================================================
    // 文件上传接口-FastDFS
    //===================================================================

    @ApiOperation(value = "上传文件接口，格式：xsl、doc、pdf等")
    @RequestMapping(value = "/api/upload/file", method = RequestMethod.POST)
    public ModelMap fileUpload(@RequestParam MultipartFile file) throws GlobalException {

        ModelMap map = new ModelMap();
        boolean isSuccess = false ;
        String fileUrl ;
        Map<String, String> subFiles = null ;
        int fileSize = 0;
        try {

            if(file == null) throw new GlobalException("文件不存在，请先上传文件") ;
            fileSize = (int)file.getSize()/1024;

            fileUrl = uploadService.uploadFile(file) ;

            if(fileUrl != null) {
                isSuccess = true ;
                subFiles = resolve(file) ;
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new GlobalException(e.getMessage()) ;
        }

        map.put("success", isSuccess) ;
        map.put("file", fileUrl) ;
        map.put("subFiles", subFiles) ;
        map.put("dataSize", fileSize);

        return map;
    }

    @ApiOperation(value = "文件是否有效")
    @RequestMapping(value = "/api/upload/file/exist", method = RequestMethod.GET)
    public ModelMap fileExist(String orderNo) throws GlobalException {

        ModelMap map = new ModelMap();

        SysUser user = getPrincipal() ;
        if(user == null) throw new GlobalException("授权过期，请重新登陆") ;

        try {
            ResponseEntity<byte[]> file = uploadService.downloadFile(orderNo, user.getId(), true) ;
            if(file != null) map.put("success", true) ;
        } catch (Exception e) {
            throw new GlobalException("下载出错："+e.getMessage()) ;
        }

        return map;
    }

    @ApiOperation(value = "检查是否可以直接下载数据包文件")
    @RequestMapping(value = "/api/download/file/check/{id}", method = RequestMethod.GET)
    public ModelMap fileExistFree(@PathVariable Integer id) throws GlobalException {

        ModelMap map = new ModelMap();

        Message message = new Message() ;
        SysUser user = getPrincipal() ;
        if(user == null) {
            message.setSuccess(false);
            message.setErrorCode(ErrorCode.ERROR_LOGIN_NO);
            message.setMessage("用户请先登录");
        } else {
            try {
                message = productService.checkDownloadFile(id);
            } catch (Exception e) {
                message.setSuccess(false);
                message.setErrorCode(ErrorCode.ERROR_FILE_DOWNLOAD_FAIL);
                message.setMessage("文件下载异常");
                message.setException(true);
                message.setExDetails(e.getMessage());
            }
        }

        map.put("result", message) ;
        return map;
    }

    /**
     * 不同格式文件拆分
     * 目前支持xls/xlsx、zip的拆分
     * @param file
     * @return
     */
    private Map<String, String> resolve(MultipartFile file) {

        if(file == null) return null;

        Map<String, String> subFiles = null ;

        switch (FilenameUtils.getExtension(file.getOriginalFilename())) {

            case CommonUtil.EXCEL03_EXTENSION:
            case CommonUtil.EXCEL07_EXTENSION:
                // 解析excel文件，并按sheet拆分多个excel文件
                try {
                    ExcelReaderUtil.readExcel(poiParserService, file.getOriginalFilename(), file.getInputStream());
                    // 赋值
                    subFiles = poiParserService.getFiles() ;
                    // 清空数据
                    poiParserService.setFiles(null);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;

            case CommonUtil.ZIP_EXTENSION:
                // 解析zip文件，并按压缩包内容拆分多个文件
                try {
                    Map<String, ByteArrayOutputStream> map = ZipUtil.unzipStream(file.getInputStream()) ;
                    if(map != null && !map.isEmpty()) {
                        subFiles = new HashMap<>() ;
                        for(String name : map.keySet()) {
                            ByteArrayOutputStream baos = map.get(name) ;
                            if(baos == null) continue;

                            String fileUrl = uploadService.uploadFile(new ByteArrayInputStream(baos.toByteArray()), baos.toByteArray().length,FilenameUtils.getExtension(name)) ;

                            subFiles.put(name.substring(0, name.lastIndexOf(".")), fileUrl) ;
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;

        }

        return subFiles ;
    }

}
