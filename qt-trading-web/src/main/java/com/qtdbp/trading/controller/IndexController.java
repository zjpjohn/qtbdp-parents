package com.qtdbp.trading.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * 首页
 *
 * @author: caidchen
 * @create: 2017-04-13 13:27
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class IndexController extends BaseController {

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public ModelAndView index(HttpServletRequest request) {

        return  getSeoSettings("index",request);
    }

}
