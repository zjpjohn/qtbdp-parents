package com.qtdbp.trading.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by dell on 2017/4/17.
 */
@Controller
public class UserCenterController {

    @RequestMapping(value = "/usercenter",method = RequestMethod.GET)
    public String index() {
        return "usercenter/index" ;
    }
}
