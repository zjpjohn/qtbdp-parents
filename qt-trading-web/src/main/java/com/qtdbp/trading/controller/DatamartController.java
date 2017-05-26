package com.qtdbp.trading.controller;

import com.qtdbp.trading.constants.AppConstants;
import com.qtdbp.trading.exception.ErrorCode;
import com.qtdbp.trading.exception.GlobalException;
import com.qtdbp.trading.mapper.DataTransactionOrderMapper;
import com.qtdbp.trading.mapper.DataTypeMapper;
import com.qtdbp.trading.model.DataProductModel;
import com.qtdbp.trading.model.DataTransactionOrderModel;
import com.qtdbp.trading.model.DataTypeAttrModel;
import com.qtdbp.trading.model.DataTypeModel;
import com.qtdbp.trading.service.DataProductService;
import com.qtdbp.trading.service.DataTransactionOrderService;
import com.qtdbp.trading.service.security.model.SysUser;
import com.qtdbp.trading.utils.CommonUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

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

    @Autowired
    private DataTypeMapper dataTypeMapper ;
    @Autowired
    private DataProductService productService ;

    @Autowired
    private DataTransactionOrderService orderService ;

    @Autowired
    private DataTransactionOrderMapper orderMapper ;

    @RequestMapping(value = "/datamart/{id}", method = RequestMethod.GET)
    public ModelAndView index(@PathVariable("id") int typeId) {

        ModelAndView result = new ModelAndView(PAGE_DATAMART);
        result.addObject("currentid", typeId) ;
        result.addObject("activeId", typeId) ;
        this.initData(result) ;

        return result ;
    }

    /**
     * 二级目录请求方法
     * @param typeId
     * @return
     */
    @RequestMapping(value = "/datamart/{pid}/{id}", method = RequestMethod.GET)
    public ModelAndView index(@PathVariable("pid") int pid, @PathVariable("id") int typeId) {

        ModelAndView result = new ModelAndView(PAGE_DATAMART);
        result.addObject("currentid", typeId) ;
        result.addObject("activeId", pid) ;

        this.initData(result) ;

        return result ;
    }

    @RequestMapping(value = "/datamart", method = RequestMethod.GET)
    public ModelAndView index() {

        ModelAndView result = new ModelAndView(PAGE_DATAMART);
        result.addObject("currentid", 0) ;
        result.addObject("activeId", 0) ;
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
        // 假的下载量，暂时
        resultProduct.setBuyCount(CommonUtil.randomNum(productId)+resultProduct.getBuyCount());
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

    @ResponseBody
    @RequestMapping(value = "/selectOrder/{productId}/{productType}", method = RequestMethod.GET)
    public ModelMap selectOrder(@PathVariable("productId") String productId,
                                @PathVariable("productType") String productType){

        ModelMap map = new ModelMap();
        // 未登陆请先登录
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        SysUser user = null ;
        if (principal instanceof SysUser) {
            user = (SysUser) principal;
        }
        if(user == null) {
            map = null;
            return map;
        }
        DataTransactionOrderModel orderModel = new DataTransactionOrderModel();
        int prodId = Integer.parseInt(productId) ;
        orderModel.setProductId(prodId);
        int type = productType != null ? Integer.parseInt(productType) : AppConstants.PRODUCT_TYPE_PACKAGE ;
        orderModel.setProductType((byte)type);
        orderModel.setUserId(user.getId());
        List<DataTransactionOrderModel> list = orderMapper.findOrderByUserIdAndProductIdAndType(orderModel);
        // 如果当前用户已经购买了此数据包产品，则无需购买
        if(list != null && !list.isEmpty()) {
            for(DataTransactionOrderModel order : list){
                // 只要有一条订单待支付或已支付，则不能购买
                if(order.getOrderState().intValue() == AppConstants.ORDER_STATE_PAYING
                        || order.getOrderState().intValue() == AppConstants.ORDER_STATE_PAYED) {
                    map.put("errorCode", ErrorCode.ERROR_ORDER_CREATED);
                    map.put("errorMsg", "订单已创建，请前往【个人中心】- 【我的订单】中查看");
                    break;
                }
            }
        }
        return map;
    }

}
