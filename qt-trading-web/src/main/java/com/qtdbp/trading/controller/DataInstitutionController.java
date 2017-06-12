package com.qtdbp.trading.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * 数据合作页面控制类
 * Created by liyang on 2017/4/20.
 */
@Controller
public class DataInstitutionController extends BaseController {

    public static final String PAGE_INSTITUTION = "institution/index";
    private static final String PAGE_INSTITUTION_ADD = "institution/add";
    private static final String PAGE_INSTITUTION_HOME = "institution/home";

    /**
     * 服务商列表
     * @return
     */
    @RequestMapping(value = "/institution", method = RequestMethod.GET)
    public String institution() {

        return PAGE_INSTITUTION;
    }

    /**
     * 个人用户升级成服务商
     * @return
     */
    @RequestMapping(value = "/institution/add",method = RequestMethod.GET)
    public String add() {

        return PAGE_INSTITUTION_ADD ;
    }

    /**
     * 服务商主页
     * @return
     */
    @RequestMapping(value = "/institution/home/{id}",method = RequestMethod.GET)
    public ModelAndView home(@PathVariable Integer id) {

        ModelAndView result = new ModelAndView(PAGE_INSTITUTION_HOME);
        result.addObject("id", id) ;

        return result ;
    }
}
