package com.qtdbp.trading.controller;

import com.qtdbp.trading.mapper.DataTypeMapper;
import com.qtdbp.trading.model.DataTypeModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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

    @Autowired
    private DataTypeMapper dataTypeMapper ;

    @RequestMapping(value = "/datamart", method = RequestMethod.GET)
    public String index(Model model) {

        List<DataTypeModel> typeModels = dataTypeMapper.findAll() ;
        model.addAttribute("typelist", typeModels) ;

        return "datamart/index" ;
    }

    @RequestMapping(value = "/datamart/{id}", method = RequestMethod.GET)
    public String index(@PathVariable("id") int typeId, Model model) {

        List<DataTypeModel> typeModels = dataTypeMapper.findAll() ;
        model.addAttribute("typelist", typeModels) ;
        model.addAttribute("currentid", typeId) ;

        return "datamart/index" ;
    }

}
