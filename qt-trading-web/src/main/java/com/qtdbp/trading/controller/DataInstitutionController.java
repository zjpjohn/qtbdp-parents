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
public class DataInstitutionController {

    private static final String PAGE_DATAMART_FEDRATION = "institution/fredation";

    /**
     * 升级为服务商
     */
    private static final String PAGE_DATAMART_FEDRATION_ADD = "institution/add";

    @Autowired
    private DataTypeMapper dataTypeMapper ;

    /**
     * 跳转到数据合作页面
     */
    @RequestMapping(value = "/fedration", method = RequestMethod.GET)
    public ModelAndView toFedration(){
        ModelAndView mv = new ModelAndView(PAGE_DATAMART_FEDRATION);
        mv.addObject("currentid", 0) ;
        this.initData(mv) ;
        return mv;
    }

    /**
     * 跳转到数据合作页面
     */
    @RequestMapping(value = "/fedration/{dataType}", method = RequestMethod.GET)
    public ModelAndView toFedrationByDataType(@PathVariable Integer dataType){
        ModelAndView mv = new ModelAndView(PAGE_DATAMART_FEDRATION);
        mv.addObject("currentid", dataType) ;
        this.initData(mv) ;
        return mv;
    }

    /**
     * 个人用户升级成服务商
     * @return
     */
    @RequestMapping(value = "/institution/add",method = RequestMethod.GET)
    public ModelAndView add() {
        ModelAndView mv = new ModelAndView(PAGE_DATAMART_FEDRATION_ADD);

        return mv ;
    }

    /**
     * 初始化加载数据
     * @param result
     */
    private void initData(ModelAndView result) {

        if(result == null) return;

        // 类型列表
        List<DataTypeModel> typeModels = dataTypeMapper.findAll() ;

        result.addObject("typeModels", typeModels);
    }
}
