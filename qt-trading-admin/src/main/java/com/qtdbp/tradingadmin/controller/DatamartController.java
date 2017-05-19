package com.qtdbp.tradingadmin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by dell on 2017/5/18.
 */
@Controller
public class DatamartController {

    public static final String PAGE_PRODUCTS = "shopManage/waresNew";

    public static final String PAGE_ADD_PRODUCT = "shopManage/setUp";

    /**
     * 跳转到数据产品管理页面
     * @return
     */
    @RequestMapping(value = "/wares", method = RequestMethod.GET)
    public String dataProduct() {

        return PAGE_PRODUCTS;
    }

    /**
     *  跳转到数据产品添加页面
     * @return
     */
    @RequestMapping(value = "/setUp", method = RequestMethod.GET)
    public String addDataProduct(){

        return PAGE_ADD_PRODUCT;
    }

}
