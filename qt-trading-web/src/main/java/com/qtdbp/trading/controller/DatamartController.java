package com.qtdbp.trading.controller;

import com.qtdbp.trading.mapper.DataTypeMapper;
import com.qtdbp.trading.model.DataProductModel;
import com.qtdbp.trading.model.DataTypeAttrModel;
import com.qtdbp.trading.model.DataTypeModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * 数据交易平台-数据商城
 *
 * @author: caidchen
 * @create: 2017-04-15 12:48
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class DatamartController {

    /**
     * 数据商城首页
     */
    private static final String CURRENT_PAGE = "datamart/index" ;

    @Autowired
    private DataTypeMapper dataTypeMapper ;

    @RequestMapping(value = "/datamart/{id}", method = RequestMethod.GET)
    public ModelAndView index(@PathVariable("id") int typeId) {

        ModelAndView result = new ModelAndView(CURRENT_PAGE);
        result.addObject("currentid", typeId) ;

        this.initData(result) ;

        return result ;
    }

    @RequestMapping(value = "/datamart", method = RequestMethod.GET)
    public ModelAndView index(DataProductModel productModel) {

        ModelAndView result = new ModelAndView(CURRENT_PAGE);
        productModel.setRows(12); // 每页12条记录

        this.initData(result) ;

        return result ;
    }


    /**
     * 初始化加载数据
     * @param result
     */
    private void initData(ModelAndView result) {

        if(result == null) return;

        // 类型列表
        List<DataTypeModel> typeModels = dataTypeMapper.findAll() ;

        // 属性、属性值列表
        List<DataTypeAttrModel> attrModels = dataTypeMapper.findAttrAll() ;

        result.addObject("typeModels", typeModels);
        result.addObject("attrModels", attrModels) ;
    }
}
