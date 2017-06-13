package com.qtdbp.trading.controller;

import com.qtdbp.trading.mapper.DataInstitutionInfoNewMapper;
import com.qtdbp.trading.service.DataInstitutionInfoNewService;
import com.qtdbp.trading.service.security.model.SysUser;
import org.docx4j.wml.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;

/**
 * 数据合作页面控制类
 * Created by liyang on 2017/4/20.
 */
@Controller
public class DataInstitutionController extends BaseController {

    public static final String PAGE_INSTITUTION = "institution/index";
    private static final String PAGE_INSTITUTION_ADD = "institution/add";
    private static final String PAGE_INSTITUTION_HOME = "institution/home";

    private static final String PAGE_INSTITUTION_ADD_PRODUCT = "institution/add-product";
    private static final String PAGE_INSTITUTION_ADD_NEW = "institution/add-new";


    @Autowired
    private DataInstitutionInfoNewService infoNewService;

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
        result.addObject("prod", infoNewService.findCount(id));
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
    @RequestMapping(value = "/institution/add/new",method = RequestMethod.GET)
    public String addInstitution() {

        return PAGE_INSTITUTION_ADD_NEW ;
    }
}
