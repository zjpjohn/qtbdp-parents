package com.qtdbp.tradingadmin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 爬虫规则市场Controller
 *
 * @author: caidchen
 * @create: 2017-06-05 20:23
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class CrawlersController extends BaseController {

    public static final String PAGE_CRAWLERS = "crawlers/index";

    /**
     * 爬虫规则列表
     * @return
     */
    @RequestMapping(value = "/crawlers", method = RequestMethod.GET)
    public String crawlers() {

        return PAGE_CRAWLERS;
    }
}
