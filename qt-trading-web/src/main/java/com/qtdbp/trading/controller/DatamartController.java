package com.qtdbp.trading.controller;

import com.qtdbp.trading.exception.GlobalException;
import com.qtdbp.trading.mapper.DataProductMapper;
import com.qtdbp.trading.mapper.DataTypeMapper;
import com.qtdbp.trading.model.DataProductModel;
import com.qtdbp.trading.model.DataTransactionOrderModel;
import com.qtdbp.trading.model.DataTypeAttrModel;
import com.qtdbp.trading.model.DataTypeModel;
import com.qtdbp.trading.service.DataProductService;
import com.qtdbp.trading.service.DataTransactionOrderService;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 数据交易平台-数据商城
 *
 * @author: caidchen
 * @create: 2017-04-15 12:48
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class DatamartController extends BaseController {

    /**
     * 数据商城首页
     */
    private static final String PAGE_DATAMART = "datamart/index" ;

    private static final String PAGE_DATAMART_DETAIL = "datamart/detail" ;

    private static final String PAGE_USERCENTER = "usercenter/index";

    @Autowired
    private DataTypeMapper dataTypeMapper ;
    @Autowired
    private DataProductService productService ;

    @Autowired
    private DataTransactionOrderService orderService ;

    @RequestMapping(value = "/datamart/{id}", method = RequestMethod.GET)
    public ModelAndView index(@PathVariable("id") int typeId) {

        ModelAndView result = new ModelAndView(PAGE_DATAMART);
        result.addObject("currentid", typeId) ;

        this.initData(result) ;

        return result ;
    }

    @RequestMapping(value = "/datamart", method = RequestMethod.GET)
    public ModelAndView index() {

        ModelAndView result = new ModelAndView(PAGE_DATAMART);
        result.addObject("currentid", 0) ;

        this.initData(result) ;

        return result ;
    }

    @RequestMapping(value = "/datamart/detail/{id}", method = RequestMethod.GET)
    public ModelAndView detail(@PathVariable("id") int productId) throws GlobalException {

        DataProductModel resultProduct = productService.findProductById(productId) ;
        if(resultProduct == null) throw new GlobalException("404 数据包产品不存在或已下架") ;
        // 属性列表
        List<DataTypeAttrModel> attrModels = dataTypeMapper.findAttrAllByTypeId(resultProduct.getDataType()) ;

        ModelAndView result = new ModelAndView(PAGE_DATAMART_DETAIL);
        result.addObject("prod", resultProduct) ;

        /*
            属性值串，格式：pName:vname:pid:vid;
         */
        String typeProps = resultProduct.getDataTypeProps() ;
        if(!StringUtils.isEmpty(typeProps)) {
            Map<String,String> valMap = new HashMap<>() ;
            String[] prop = typeProps.split(";") ;
            for (String ids : prop) {
                valMap.put(ids.split(":") [2], ids.split(":") [1]) ;
            }

            for (DataTypeAttrModel attr: attrModels) {
                if(valMap.containsKey(""+attr.getId())) {
                    attr.setValName(valMap.get(""+attr.getId()));
                }
            }
        }
        result.addObject("attrModels", attrModels);

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

    @RequestMapping(value = "/getOrderInfoAndGoto/{userId}/{productId}/{productType}", method = RequestMethod.GET)
    public ModelAndView getOrderInfoAndGoto(@PathVariable("userId") Integer userId,
                                            @PathVariable("productId") Integer productId,
                                            @PathVariable("productType") Integer productType, HttpServletRequest request){
        String order = request.getParameter("order");
        ModelAndView modelAndView = new ModelAndView(PAGE_USERCENTER);
        DataTransactionOrderModel orderModel = new DataTransactionOrderModel();
        if(userId != null && productId != null && productType != null){
            orderModel.setUserId(userId);
            orderModel.setProductId(productId);
            int type = productType;
            orderModel.setProductType((byte)type);
            try {
                Map<String, Object> map = orderService.insertNewOrder(orderModel);
                if( map != null){
                    modelAndView.addObject("order",order);
                    modelAndView.addObject("orderState",map.get("orderState"));
                    modelAndView.addObject("pojo",map.get("orderState"));
                }
            } catch (GlobalException e) {
                e.printStackTrace();
            }
        }
        return modelAndView;
    }
}
