package com.qtdbp.tradingadmin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 服务商Controller
 *
 * @author: caidchen
 * @create: 2017-06-05 20:21
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class InstitutionController extends BaseController {

    public static final String PAGE_INSTITUTION = "institution/index";
    public static final String PAGE_INSTITUTION_ROLE = "institution/role";
    public static final String PAGE_INSTITUTION_DATA = "institution/data";

    /**
     * 服务商列表
     * @return
     */
    @RequestMapping(value = "/institution", method = RequestMethod.GET)
    public String institution() {

        return PAGE_INSTITUTION;
    }

    /**
     * 服务商数据审核
     * @return
     */
    @RequestMapping(value = "/institution/data", method = RequestMethod.GET)
    public String institutionData() {

        return PAGE_INSTITUTION_DATA;
    }

    /**
     * 服务商规则审核
     * @return
     */
    @RequestMapping(value = "/institution/role", method = RequestMethod.GET)
    public String institutionRole() {

        return PAGE_INSTITUTION_ROLE;
    }

}
