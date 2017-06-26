package com.qtdbp.trading.controller;

import com.qtdbp.trading.exception.GlobalException;
import com.qtdbp.trading.model.CrawlersRoleModel;
import com.qtdbp.trading.service.CrawlersRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * 爬虫市场
 *
 * @author: caidchen
 * @create: 2017-06-07 18:45
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class CrawlersController extends BaseController {

    public static final String PAGE_CRAWLERS = "crawlers/index";
    public static final String PAGE_CRAWLERS_DETAIL = "crawlers/detail";
    public static final String PAGE_CRAWLERS_PUBLISH = "crawlers/publish";

    @Autowired
    private CrawlersRoleService roleService;

    /**
     * 爬虫规则列表
     * @return
     */
    @RequestMapping(value = "/crawlers", method = RequestMethod.GET)
    public ModelAndView crawlers(HttpServletRequest request) {

        return getSeoSettings(PAGE_CRAWLERS, request);
    }

    /**
     * 爬虫规则详情
     * @return
     */
    @RequestMapping(value = "/crawlers/detail/{id}", method = RequestMethod.GET)
    public ModelAndView crawlersDetail(@PathVariable Integer id) throws GlobalException {

        CrawlersRoleModel roleModel = roleService.findRuleById(id);
        if (roleModel == null) throw new GlobalException("404 爬虫规则为空");

        ModelAndView result = new ModelAndView(PAGE_CRAWLERS_DETAIL);
        result.addObject("prod", roleModel);
        return result;
    }

    /**
     * 发布规则
     * @return
     */
    @RequestMapping(value = "/publish/crawlers", method = RequestMethod.GET)
    public String publish() {

        return PAGE_CRAWLERS_PUBLISH;
    }
}
