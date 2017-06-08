package com.qtdbp.trading.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 定制服务
 *
 * @author: caidchen
 * @create: 2017-06-05 20:18
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class CustomizedController extends BaseController {

    public static final String PAGE_CUSTOMIZED_RULE = "customized/rule";
    public static final String PAGE_CUSTOMIZED_DATA = "customized/data";

    public static final String PAGE_CUSTOMIZED_DATA_DETAIL = "customized/data-detail";
    public static final String PAGE_CUSTOMIZED_ROLE_DETAIL = "customized/rule-detail";

    /**
     * 定制数据列表
     * @return
     */
    @RequestMapping(value = "/customized/data", method = RequestMethod.GET)
    public String customizedData() {

        return PAGE_CUSTOMIZED_DATA;
    }

    /**
     * 定制数据详情
     * @return
     */
    @RequestMapping(value = "/customized/data/detail/{id}", method = RequestMethod.GET)
    public String customizedDataDetail(@PathVariable Integer id) {

        return PAGE_CUSTOMIZED_DATA_DETAIL;
    }

    /**
     * 定制规则列表
     * @return
     */
    @RequestMapping(value = "/customized/rule", method = RequestMethod.GET)
    public String customizedRule() {

        return PAGE_CUSTOMIZED_RULE;
    }

    /**
     * 定制规则详情
     * @return
     */
    @RequestMapping(value = "/customized/rule/detail/{id}", method = RequestMethod.GET)
    public String customizedRuleDetail(@PathVariable Integer id) {

        return PAGE_CUSTOMIZED_ROLE_DETAIL;
    }
}
