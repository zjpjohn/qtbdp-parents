package com.qtdbp.tradingadmin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 数据商城Controller
 *
 * Created by dell on 2017/5/18.
 */
@Controller
public class DatamartController extends BaseController {

    public static final String PAGE_PRODUCTS = "datamart/index";
    public static final String PAGE_SAVE_PRODUCT = "datamart/save";
    public static final String PAGE_CATEGORY = "datamart/category";
//    public static final String PAGE_PRODUCTS = "shopManage/wares";

//    public static final String PAGE_ADD_PRODUCT = "shopManage/setUp";

    /**
     * 跳转到数据产品管理页面
     * @return
     */
    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public String products() {

        return PAGE_PRODUCTS;
    }

    /**
     *  跳转到数据产品添加页面
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.GET)
    public String save(){

        return PAGE_SAVE_PRODUCT ;
    }



    /**
     * 跳转到类目管理页面
     * @return
     */
    @RequestMapping(value = "/category", method = RequestMethod.GET)
    public String category() {

        return PAGE_CATEGORY;
    }

}
