package com.qtdbp.trading.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 首页
 *
 * @author: caidchen
 * @create: 2017-04-13 13:27
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class IndexController extends BaseController {

    Logger logger = LoggerFactory.getLogger(this.getClass()) ;

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String index() {

        return "index" ;
    }

}
