package com.qtdbp.trading.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * 关于我们页面控制类
 * Created by liyang on 2017/4/24.
 */
@Controller
public class AboutController extends BaseController {

    private static final String  PAGE_ABOUT = "about";

    @RequestMapping(value = "/about", method = RequestMethod.GET)
    public String loadAboutUs(){
  //      ModelAndView modelAndView = new ModelAndView(PAGE_ABOUT);
        return PAGE_ABOUT;
    }
}
