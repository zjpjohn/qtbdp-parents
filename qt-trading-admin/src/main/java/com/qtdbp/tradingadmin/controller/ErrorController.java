package com.qtdbp.tradingadmin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 自定义错误页面
 *
 * @author: caidchen
 * @create: 2017-05-09 17:18
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class ErrorController {

    private static final String PAGE_FORBIDDEN = "403" ;
    private static final String PAGE_NOT_FOUND = "404" ;
    private static final String PAGE_INTERNAL_SERVER_ERROR = "500" ;

    /**
     * 403授权失效
     * @return
     */
    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public String forbidden() {
        return PAGE_FORBIDDEN ;
    }

    /**
     * 404页面不存在
     * @return
     */
    @RequestMapping(value = "/404", method = RequestMethod.GET)
    public String notFound() {
        return PAGE_NOT_FOUND ;
    }

    /**
     * 500服务后台错误
     * @return
     */
    @RequestMapping(value = "/500", method = RequestMethod.GET)
    public String internalServerError() {
        return PAGE_INTERNAL_SERVER_ERROR ;
    }

}
