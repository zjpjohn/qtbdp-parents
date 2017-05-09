package com.qtdbp.tradingadmin.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 后台用户登陆页
 *
 * @author: caidchen
 * @create: 2017-05-09 15:01
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class LoginController {

    Logger logger = LoggerFactory.getLogger(this.getClass()) ;

    private static final String PAGE_LOGIN = "login" ;

    /**
     * 登陆
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {

        return PAGE_LOGIN ;
    }
}
