package com.qtdbp.trading.controller;/**
 * Created by dell on 2017/4/13.
 */

import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 首页
 *
 * @author: caidchen
 * @create: 2017-04-13 13:27
 * To change this template use File | Settings | File Templates.
 */
@Api("首页业务数据处理")
//@RestController
@Controller
public class IndexController {

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String index() {

        return "index" ;
    }

}
