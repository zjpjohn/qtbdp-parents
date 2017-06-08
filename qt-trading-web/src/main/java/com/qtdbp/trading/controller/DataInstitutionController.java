package com.qtdbp.trading.controller;

import com.qtdbp.trading.mapper.DataTypeMapper;
import com.qtdbp.trading.model.DataTypeModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * 数据合作页面控制类
 * Created by liyang on 2017/4/20.
 */
@Controller
public class DataInstitutionController extends BaseController {

    public static final String PAGE_INSTITUTION = "institution/index";
    public static final String PAGE_INSTITUTION_DETAIL = "institution/detail";
    private static final String PAGE_INSTITUTION_ADD = "institution/add";

    /**
     * 服务商列表
     * @return
     */
    @RequestMapping(value = "/institution", method = RequestMethod.GET)
    public String institution() {

        return PAGE_INSTITUTION;
    }

    /**
     * 服务商详情
     * @return
     */
    @RequestMapping(value = "/institution/detail/{id}", method = RequestMethod.GET)
    public String institutionDetail(@PathVariable Integer id) {

        return PAGE_INSTITUTION_DETAIL;
    }

    /**
     * 个人用户升级成服务商
     * @return
     */
    @RequestMapping(value = "/institution/add",method = RequestMethod.GET)
    public String add() {

        return PAGE_INSTITUTION_ADD ;
    }
}
