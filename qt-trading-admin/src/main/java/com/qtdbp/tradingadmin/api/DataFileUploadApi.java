package com.qtdbp.tradingadmin.api;


import com.qtd.utils.OssUpload;
import com.qtdbp.poi.excel.ExcelReaderUtil;
import com.qtdbp.trading.exception.GlobalException;
import com.qtdbp.tradingadmin.service.FdfsFileService;
import com.qtdbp.tradingadmin.service.PoiParserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件上传API接口
 *
 * @author: caidchen
 * @create: 2017-04-25 17:03
 * To change this template use File | Settings | File Templates.
 */
@Api(description = "文件上传 - 业务API接口")
@RestController
@RequestMapping(value = "/api/upload")
public class DataFileUploadApi {

    @Autowired
    private FdfsFileService uploadService ;

    @Autowired
    private PoiParserService poiParserService ;

    //===================================================================
    // 图片上传接口-支持阿里云Oss服务
    //===================================================================

    @ApiOperation(value = "上传图片接口，支持jpg/jpeg、gif、png")
    @RequestMapping(value = "/img", method = RequestMethod.POST)
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
    @RequestMapping(value = "/file", method = RequestMethod.POST)
    public ModelMap fileUpload(@RequestParam MultipartFile file) throws GlobalException {

        ModelMap map = new ModelMap();
        boolean isSuccess = false ;
        String fileUrl = null;

        try {
            if(file == null) throw new GlobalException("文件不存在，请先上传文件") ;

            fileUrl = uploadService.uploadFile(file) ;
            if(fileUrl != null) {
                isSuccess = true ;

                // 解析Excel文件，按照sheet多个拆分子文件，并上传文件系统
                ExcelReaderUtil.readExcel(poiParserService, file.getOriginalFilename(), file.getInputStream());
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new GlobalException(e.getMessage()) ;
        }

        map.put("success", isSuccess) ;
        map.put("file", fileUrl) ;
        map.put("subFiles", poiParserService.getFiles()) ;

        return map;
    }

}
