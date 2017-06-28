package com.qtdbp.tradingadmin.controller;

import com.qtdbp.trading.service.security.model.SysUser;
import com.qtdbp.tradingadmin.base.security.SecurityUser;
import com.qtdbp.tradingadmin.exception.GlobalAdminException;
import com.qtdbp.tradingadmin.service.FdfsFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 数据商城Controller
 *
 * Created by dell on 2017/5/18.
 */
@Controller
public class DatamartController extends BaseController {

    public static final String PAGE_PRODUCTS = "datamart/index";
    public static final String PAGE_SAVE_PRODUCT = "datamart/save";
    public static final String PAGE_CATEGORY = "datamart/category";

    @Autowired
    private FdfsFileService fileService;
    /**
     * 跳转到数据产品管理页面
     * @return
     */
    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public String products() {

        return PAGE_PRODUCTS;
    }

    /**
     *  跳转到数据产品添加页面
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.GET)
    public String save(){

        return PAGE_SAVE_PRODUCT ;
    }



    /**
     * 跳转到类目管理页面
     * @return
     */
    @RequestMapping(value = "/category", method = RequestMethod.GET)
    public String category() {

        return PAGE_CATEGORY;
    }

    /**
     * 下载免费数据包文件
     * @param productId
     * @return
     * @throws GlobalAdminException
     */
    @RequestMapping(value = "/downloadFreeProduct/{productId}", method = RequestMethod.GET)
    public ResponseEntity<byte[]> updateFreeProduct(@PathVariable Integer productId) throws GlobalAdminException {

        SecurityUser user = getPrincipal() ;

        if(user == null) throw new GlobalAdminException("授权过期，请重新登陆") ;

        ResponseEntity<byte[]> file ;
        try {
            file = fileService.downloadFreeFile(productId);
        } catch (Exception e) {
            throw new GlobalAdminException("下载出错："+e.getMessage()) ;
        }

        return file;
    }

}
