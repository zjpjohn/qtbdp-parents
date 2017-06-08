package com.qtdbp.tradingadmin.controller;

import com.qtdbp.trading.exception.GlobalException;
import com.qtdbp.tradingadmin.base.security.SecurityUser;
import com.qtdbp.tradingadmin.service.FdfsFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 爬虫规则市场Controller
 *
 * @author: caidchen
 * @create: 2017-06-05 20:23
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class CrawlersController extends BaseController {

    public static final String PAGE_CRAWLERS = "crawlers/index";

    @Autowired
    private FdfsFileService fileService;

    /**
     * 爬虫规则列表
     * @return
     */
    @RequestMapping(value = "/crawlers", method = RequestMethod.GET)
    public String crawlers() {

        return PAGE_CRAWLERS;
    }

    /**
     * 下载免费爬虫规则文件
     * @param roleId
     * @return
     * @throws GlobalException
     */
    @RequestMapping(value = "/downloadFreeRole/{roleId}", method = RequestMethod.GET)
    public ResponseEntity<byte[]> updateFreeProduct(@PathVariable Integer roleId) throws GlobalException {

        SecurityUser user = getPrincipal() ;

        if(user == null) throw new GlobalException("授权过期，请重新登陆") ;

        ResponseEntity<byte[]> file ;
        try {
            file = fileService.downloadFreeRoleFile(roleId);
        } catch (Exception e) {
            throw new GlobalException("下载出错："+e.getMessage()) ;
        }

        return file;
    }
}
