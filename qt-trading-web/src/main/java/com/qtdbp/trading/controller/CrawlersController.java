package com.qtdbp.trading.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 爬虫市场
 *
 * @author: caidchen
 * @create: 2017-06-07 18:45
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class CrawlersController {

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
