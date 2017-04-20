package com.qtdbp.trading.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * 个人中心Controller
 *
 * Created by dell on 2017/4/17.
 */
@Controller
public class UserCenterController {

    /**
     * 数据商城首页
     */
    private static final String PAGE_USER_CENTER = "usercenter/index" ;

    /**
     * 个人中心首页
     * @return
     */
    @RequestMapping(value = "/usercenter",method = RequestMethod.GET)
    public ModelAndView index() {

        ModelAndView result = new ModelAndView(PAGE_USER_CENTER);

        return result ;
    }
}
