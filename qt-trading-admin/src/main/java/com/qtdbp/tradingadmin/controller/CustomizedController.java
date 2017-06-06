package com.qtdbp.tradingadmin.controller;

import org.springframework.stereotype.Controller;
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

    public static final String PAGE_CUSTOMIZED_ROLE = "customized/index";
    public static final String PAGE_CUSTOMIZED_DATA = "customized/data";

    /**
     * 定制数据列表
     * @return
     */
    @RequestMapping(value = "/customized/data", method = RequestMethod.GET)
    public String customizedData() {

        return PAGE_CUSTOMIZED_DATA;
    }

    /**
     * 定制规则列表
     * @return
     */
    @RequestMapping(value = "/customized/role", method = RequestMethod.GET)
    public String customizedRole() {

        return PAGE_CUSTOMIZED_ROLE;
    }
}
