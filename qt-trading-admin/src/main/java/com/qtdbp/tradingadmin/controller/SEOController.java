package com.qtdbp.tradingadmin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by dell on 2017/6/20.
 */
@Controller
public class SEOController extends BaseController{

    private static final String SEO_PAGE = "settings/SEO";

    /**
     * 跳转到seo优化页面
     * @return
     */
    @RequestMapping(value = "/seo", method = RequestMethod.GET)
    public String SEOs() {
        return SEO_PAGE;
    }
}
