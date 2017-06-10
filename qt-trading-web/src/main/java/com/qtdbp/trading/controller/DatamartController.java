package com.qtdbp.trading.controller;

import com.qtdbp.trading.exception.GlobalException;
import com.qtdbp.trading.service.FdfsFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * 数据交易平台-数据商城
 *
 * @author: caidchen
 * @create: 2017-04-15 12:48
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class DatamartController extends BaseController {

    private static final String PAGE_DATAMART = "datamart/index" ;
    private static final String PAGE_DATAMART_DETAIL = "datamart/detail" ;

    @Autowired
    private FdfsFileService fileService;

    /**
     * 数据市场
     * @return
     */
    @RequestMapping(value = "/datamart", method = RequestMethod.GET)
    public String index() {
        return PAGE_DATAMART ;
    }

    /**
     * 数据产品详情
     * @param id
     * @return
     * @throws GlobalException
     */
    @RequestMapping(value = "/datamart/detail/{id}", method = RequestMethod.GET)
    public ModelAndView detail(@PathVariable("id") int id) throws GlobalException {
        ModelAndView result = new ModelAndView(PAGE_DATAMART_DETAIL);
        result.addObject("id", id) ;

        return result ;
    }

    /**
     * 下载免费数据包文件
     * @param id 产品ID
     * @return
     * @throws GlobalException
     */
    @RequestMapping(value = "/download/file/{id}", method = RequestMethod.GET)
    public ResponseEntity<byte[]> downloadFreeProduct(@PathVariable Integer id) throws Exception {

        ResponseEntity<byte[]> file ;
        try {
            file = fileService.downloadFreeFile(id);
        } catch (Exception e) {
            throw new Exception("下载出错："+e.getMessage()) ;
        }

        return file;
    }

}
