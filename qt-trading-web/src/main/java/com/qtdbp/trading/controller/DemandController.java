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
 * 需求大厅Controller
 *
 * @author: caidchen
 * @create: 2017-04-21 14:28
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class DemandController {

    //需求大厅页面
    private static final String PAGE_DEMAND = "demand/demand" ;
    /**
     * 需求大厅 -- 添加方案召集
     */
    private static final String PAGE_DEMAND_SOS = "demand/convene" ;

    /**
     * 需求大厅 -- 添加数据众包
     */
    private static final String PAGE_DEMAND_BUY = "demand/schemecalled" ;

    @Autowired
    private DataTypeMapper dataTypeMapper ;

    @RequestMapping(value = "/demand/sos/add", method = RequestMethod.GET)
    public ModelAndView addSosInfo() {

        ModelAndView result = new ModelAndView(PAGE_DEMAND_SOS);

        return result ;
    }

    @RequestMapping(value = "/demand/buy/add", method = RequestMethod.GET)
    public ModelAndView addBuyInfo() {

        ModelAndView result = new ModelAndView(PAGE_DEMAND_BUY);

        return result ;
    }

    /**
     * 跳转到需求大厅页面
     * @return
     */
    @RequestMapping(value = "/demand", method = RequestMethod.GET)
    public ModelAndView demandIndex(){
        ModelAndView modelAndView = new ModelAndView(PAGE_DEMAND);
        this.initData(modelAndView) ;

        return modelAndView;
    }

    /**
     * 跳转到需求大厅页面
     */
    @RequestMapping(value = "/demand/{dataType}", method = RequestMethod.GET)
    public ModelAndView toFedrationByDataType(@PathVariable Integer dataType){
        ModelAndView modelAndView = new ModelAndView(PAGE_DEMAND);
        modelAndView.addObject("currentid", dataType) ;
        this.initData(modelAndView) ;

        return modelAndView;
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
