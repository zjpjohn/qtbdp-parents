package com.qtdbp.trading.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by dell on 2017/6/23.
 */
@Controller
public class ApiDemandController extends BaseController{

    private static final String ADD_API_PAGE = "apicustom/index";

    @RequestMapping(value = "/add/api", method = RequestMethod.GET)
    public String addApi() {
        return ADD_API_PAGE;
    }
}
