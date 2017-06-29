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

    public static final String PAGE_CUSTOMIZED_DATA_DETAIL = "customized/data-info";
    public static final String PAGE_CUSTOMIZED_ROLE_DETAIL = "customized/role-info";

    public static final String PAGE_API_DEMAND = "customized/API";
    public static final String PAGE_FEEDBACK = "customized/feedback";

    public static final String PAGE_API_DEMAND_INFO = "customized/APIInfo";
    public static final String PAGE_FEEDBACK_INFO = "customized/feedbackInfo";

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
    @RequestMapping(value = "/customized/dataInfo", method = RequestMethod.GET)
    public String customizedDataInfo() {

        return PAGE_CUSTOMIZED_DATA_DETAIL;
    }

    /**
     * 定制规则列表
     * @return
     */
    @RequestMapping(value = "/customized/role", method = RequestMethod.GET)
    public String customizedRole() {

        return PAGE_CUSTOMIZED_ROLE;
    }

    /**
     * 定制规则详情
     * @return
     */
    @RequestMapping(value = "/customized/roleInfo", method = RequestMethod.GET)
    public String customizedRoleInfo() {

        return PAGE_CUSTOMIZED_ROLE_DETAIL;
    }

    /**
     *  api定制列表页面
     * @return
     */
    @RequestMapping(value = "/customized/apiDemand", method = RequestMethod.GET)
    public String ApiDemandList(){

        return PAGE_API_DEMAND;
    }

    /**
     *  用户反馈列表页面
     * @return
     */
    @RequestMapping(value = "/customized/feedback", method = RequestMethod.GET)
    public String feedbackList(){

        return PAGE_FEEDBACK;
    }

    /**
     *  api定制详情页面
     * @return
     */
    @RequestMapping(value = "/customized/apiDemandInfo", method = RequestMethod.GET)
    public String ApiDemandById(){

        return PAGE_API_DEMAND_INFO;
    }

    /**
     *  用户反馈详情页面
     * @return
     */
    @RequestMapping(value = "/customized/feedbackInfo", method = RequestMethod.GET)
    public String feedbackById(){

        return PAGE_FEEDBACK_INFO;
    }



}
