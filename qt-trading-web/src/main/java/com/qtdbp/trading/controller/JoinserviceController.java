package com.qtdbp.trading.controller;

import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by dell on 2017/4/22.
 */
@Api(description = "加盟数据服务商业务数据处理")
@Controller
public class JoinserviceController {
    @RequestMapping(value = "/joinservice",method = RequestMethod.GET)
    public String index() {

        return "joinservice" ;
    }
}
