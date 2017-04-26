package com.qtdbp.trading.hystrix;

import com.qtdbp.trading.controller.FileUploadController;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 创建回调类ComputeClientHystrix，实现@FeignClient的接口。
 * Created by dell on 2017/4/7.
 */
@Deprecated
@Component
public class FileUploadHystrix implements FileUploadController {

    /**
     * 实现的方法就是对应@FeignClient接口中映射的fallback函数。
     * @param file
     * @param request
     * @return
     */
    @Override
    public String upload(@RequestParam MultipartFile file, @RequestParam HttpServletRequest request, @RequestParam HttpServletResponse response) throws Exception {
        return "-1";
    }
}
