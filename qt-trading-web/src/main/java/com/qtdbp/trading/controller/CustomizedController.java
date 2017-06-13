package com.qtdbp.trading.controller;

import com.qtdbp.trading.exception.GlobalException;
import com.qtdbp.trading.model.CustomServiceModel;
import com.qtdbp.trading.service.CustomizedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

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

    public static final String PAGE_CUSTOMIZED_PUBLISH = "customized/publish";

    @Autowired
    private CustomizedService customizedService;
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
    public ModelAndView customizedDataDetail(@PathVariable Integer id) throws GlobalException {

        ModelAndView result = new ModelAndView(PAGE_CUSTOMIZED_DATA_DETAIL);
        CustomServiceModel serviceModel = customizedService.findCustomizedDataById(id);
        if (serviceModel == null) throw new GlobalException("404 数据定制为空");
        result.addObject("prod", serviceModel);
        return result;
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
    public ModelAndView customizedRuleDetail(@PathVariable Integer id) throws GlobalException {

        ModelAndView result = new ModelAndView(PAGE_CUSTOMIZED_ROLE_DETAIL);
        CustomServiceModel serviceModel = customizedService.findCustomizedDataById(id);
        if (serviceModel == null) throw new GlobalException("404 数据定制为空");
        result.addObject("prod", serviceModel);
        return result;
    }

    /**
     * 添加定制服务
     * @param type 0 数据定制 1规则定制
     * @return
     */
    @RequestMapping(value = "/publish/customized/{type}", method = RequestMethod.GET)
    public ModelAndView publish(@PathVariable Integer type) {

        ModelAndView result = new ModelAndView(PAGE_CUSTOMIZED_PUBLISH);
        result.addObject("type", type);

        return result;
    }
}
