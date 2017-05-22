package com.qtdbp.tradingadmin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by dell on 2017/5/17.
 */
@Controller
public class DataTypeController extends BaseController {

    public static final String PAGE_ITEM = "dataclassify";

    /**
     * 跳转到类目管理页面
     * @return
     */
    @RequestMapping(value = "/items", method = RequestMethod.GET)
    public String gotoItemPage() {

        return PAGE_ITEM;
    }
}
