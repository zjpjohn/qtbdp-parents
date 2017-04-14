package com.qtbdp.base.controller;

import com.qtbdp.base.service.FdfsUploadService;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * 文件上传服务
 * Created with IntelliJ IDEA.
 * User: 浩
 * Date: 2017/4/13
 * Time: 22:58
 * To change this template use File | Settings | File Templates.
 */
@Api(value = "file-upload-api" ,description = "文件上传服务接口")
@Controller
public class FileUploadController {

    public final Logger logger = LoggerFactory.getLogger(this.getClass()) ;

    @Autowired
    private FdfsUploadService uploadService ;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index() {
        return "index";
    }

    /**
     * 上传文件
     * @param files
     * @param model
     * @return
     */
    @RequestMapping(value = "/multiUpload", method = RequestMethod.POST)
    public String multiUpload(@RequestParam MultipartFile[] files, Model model) {

        //这里可以支持多文件上传
        List<String> fileUrls = null ;
        if (files != null && files.length >= 1) {

            String fileName = files[0].getOriginalFilename();
            //判断是否有文件
            if (fileName != null && !"".equalsIgnoreCase(fileName.trim())) {
                //拷贝文件到输出文件对象
                fileUrls = new ArrayList<>() ;
                for (MultipartFile file : files) {
                    try {
                        String fileUrl = uploadService.uploadFile(file);

                        model.addAttribute("fileUrl", fileUrl) ;
                        logger.info("文件地址："+fileUrl);

                        fileUrls.add(fileUrl);
                    } catch (Exception e) {
                        e.printStackTrace();
                        logger.error("文件："+fileName+" 上传异常："+e.getMessage());
                    }

                }
            }
        }
        return "index" ;
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String upload(@RequestParam MultipartFile file, HttpServletRequest request) {

        String fileUrl =null ;

        String fileName = file.getOriginalFilename();
        //判断是否有文件
        if (fileName != null && !"".equalsIgnoreCase(fileName.trim())) {
            //拷贝文件到输出文件对象
            try {
                fileUrl = uploadService.uploadFile(file);

                logger.info("文件地址："+fileUrl);
            } catch (Exception e) {
                e.printStackTrace();
                logger.error("文件："+fileName+" 上传异常："+e.getMessage());
            }
        }

        return "redirect:/index" ;
    }
}
