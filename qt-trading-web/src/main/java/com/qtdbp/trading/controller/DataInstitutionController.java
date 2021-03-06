package com.qtdbp.trading.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * 数据合作页面控制类
 * Created by liyang on 2017/4/20.
 */
@Controller
public class DataInstitutionController extends BaseController {

    public static final String PAGE_INSTITUTION = "institution/index";
    private static final String PAGE_INSTITUTION_HOME = "institution/home";

    private static final String PAGE_INSTITUTION_ADD_PRODUCT = "institution/add-product";
    private static final String PAGE_INSTITUTION_ADD_PERSON = "usercenter/add-person";
    private static final String PAGE_INSTITUTION_ADD_COMPANY = "usercenter/add-company";


    /**
     * 服务商列表
     * @return
     */
    @RequestMapping(value = "/institution", method = RequestMethod.GET)
    public ModelAndView institution(HttpServletRequest request) {

        return getSeoSettings(PAGE_INSTITUTION, request);
    }

    /**
     * 服务商主页
     * @return
     */
    @RequestMapping(value = "/institution/home/{id}",method = RequestMethod.GET)
    public ModelAndView home(@PathVariable Integer id) {

        ModelAndView result = new ModelAndView(PAGE_INSTITUTION_HOME);
        result.addObject("id", id) ;
        result.addObject("type", 0) ;
//        result.addObject("prod", infoNewService.findCount(id));
        return result ;
    }

    @RequestMapping(value = "/institution/home/{id}/{type}",method = RequestMethod.GET)
    public ModelAndView home1(@PathVariable Integer id,@PathVariable Integer type) {

        ModelAndView result = new ModelAndView(PAGE_INSTITUTION_HOME);
        result.addObject("id", id) ;
        result.addObject("type", type) ;
//        result.addObject("prod", infoNewService.findCount(id));
        return result ;
    }

    /**
     * 跳转到数据服务商添加数据包产品页面
     * @return
     */
    @RequestMapping(value = "/institution/add/product", method = RequestMethod.GET)
    public String addProduct() {

        return PAGE_INSTITUTION_ADD_PRODUCT;
    }


    /**
     * 个人用户升级成服务商(新)
     * @return
     */
    @RequestMapping(value = "/usercenter/add/person",method = RequestMethod.GET)
    public String addPerson() {

        return PAGE_INSTITUTION_ADD_PERSON ;
    }

    @RequestMapping(value = "/usercenter/add/company",method = RequestMethod.GET)
    public String addCompany() {

        return PAGE_INSTITUTION_ADD_COMPANY ;
    }
}
