package com.qtdbp.trading.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * 需求大厅Controller
 *
 * @author: caidchen
 * @create: 2017-04-21 14:28
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class DemandController {

    /**
     * 需求大厅 -- 添加方案召集
     */
    private static final String PAGE_DEMAND_SOS = "demand/convene" ;

    /**
     * 需求大厅 -- 添加数据众包
     */
    private static final String PAGE_DEMAND_BUY = "demand/schemecalled" ;

    @RequestMapping(value = "/datamart/sos/add", method = RequestMethod.GET)
    public ModelAndView addSosInfo() {

        ModelAndView result = new ModelAndView(PAGE_DEMAND_SOS);

        return result ;
    }

    @RequestMapping(value = "/datamart/buy/add", method = RequestMethod.GET)
    public ModelAndView addBuyInfo() {

        ModelAndView result = new ModelAndView(PAGE_DEMAND_BUY);

        return result ;
    }

}
