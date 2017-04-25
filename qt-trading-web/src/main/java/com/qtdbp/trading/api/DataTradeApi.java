package com.qtdbp.trading.api;

import com.github.pagehelper.PageInfo;
import com.qtdbp.trading.constants.ApiConstants;
import com.qtdbp.trading.exception.GlobalException;
import com.qtdbp.trading.model.AlipayModel;
import com.qtdbp.trading.model.DataAuthorizeOrderModel;
import com.qtdbp.trading.model.DataItemModel;
import com.qtdbp.trading.model.DataTransactionOrderModel;
import com.qtdbp.trading.service.DataAuthorizeOrderService;
import com.qtdbp.trading.service.DataProductService;
import com.qtdbp.trading.service.DataTransactionOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import net.sinofool.alipay.AlipayConfig;
import org.bouncycastle.jcajce.provider.asymmetric.RSA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 订单接口
 *
 * @author: caidchen
 * @create: 2017-04-19 13:21
 * To change this template use File | Settings | File Templates.
 */
@Api(description = "订单接口 - 业务API接口")
@RestController
@RequestMapping(value = "/api/trade")
public class DataTradeApi {

    @Autowired
    private DataProductService productService;

    @Autowired
    private DataTransactionOrderService orderService ;

    @Autowired
    private DataAuthorizeOrderService demandOrderService ;

    //===================================================================
    // 订单API接口
    //===================================================================

    @ApiOperation(value="最新前5条订单数据接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户ID（如：1）", dataType = "Integer", required = true, paramType = ApiConstants.PARAM_TYPE_QUERY),
            @ApiImplicitParam(name = "page", value = "前几条数据（如：5）", defaultValue = "5", dataType = "Integer", required = true, paramType = ApiConstants.PARAM_TYPE_QUERY)
    })
    @ResponseBody
    @RequestMapping(value = "/neworders", method = RequestMethod.GET)
    public ModelMap loadDataNewOrders(int userId, int page) throws GlobalException {

        ModelMap map = new ModelMap() ;
        // 设置默认每页显示记录数
        try {
            List<DataTransactionOrderModel> orderModelList = orderService.findNewOrdersByUserId(userId, page);
            map.put("pageInfo", new PageInfo<>(orderModelList));
        } catch (Exception e) {
            throw new GlobalException(e.getMessage()) ;
        }

        return map ;
    }

    @ApiOperation(value="订单数据接口，分页获取")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户ID（如：1）", dataType = "Integer", required = true, paramType = ApiConstants.PARAM_TYPE_QUERY),
            @ApiImplicitParam(name = "orderState", value = "订单状态（1待支付、2已撤销、3已支付）,不传表示所有", dataType = "Integer", paramType = ApiConstants.PARAM_TYPE_QUERY),
            @ApiImplicitParam(name = "page", value = "当前页（如：1）", defaultValue = "1", dataType = "Integer", required = true, paramType = ApiConstants.PARAM_TYPE_QUERY),
            @ApiImplicitParam(name = "rows", value = "每页显示记录数（如：10）", defaultValue = "10", dataType = "Integer", required = true, paramType = ApiConstants.PARAM_TYPE_QUERY)
    })
    @ResponseBody
    @RequestMapping(value = "/orders", method = RequestMethod.GET)
    public ModelMap loadDataOrders(DataTransactionOrderModel orderModel) throws GlobalException {

        ModelMap map = new ModelMap() ;
        // 设置默认每页显示记录数
        try {
            if (orderModel.getRows() == null || orderModel.getRows() == 0) orderModel.setRows(10);
            List<DataTransactionOrderModel> orderModelList = orderService.findOrdersForPage(orderModel);
            map.put("pageInfo", new PageInfo<>(orderModelList));
            map.put("queryParam", orderModel);
            map.put("page", orderModel.getPage());
            map.put("rows", orderModel.getRows());
        } catch (Exception e) {
            throw new GlobalException(e.getMessage()) ;
        }

        return map ;
    }

    @ApiOperation(value="添加新订单API接口")
    @RequestMapping(value = "/orders", method = RequestMethod.POST)
    public ModelMap addDataOrders(@RequestBody DataTransactionOrderModel orderModel) throws GlobalException {

        ModelMap map = new ModelMap() ;
        try {
            int id = orderService.insertOrder(orderModel) ;
            map.put("success", id>0?true:false);
            map.put("id", id);
        } catch (Exception e) {
            throw new GlobalException(e.getMessage()) ;
        }
        return map ;
    }


    @ApiOperation(value = "统计订单数量数据接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户ID（如：1）", dataType = "Integer", required = true, paramType = ApiConstants.PARAM_TYPE_QUERY),
    })
    @ResponseBody
    @RequestMapping(value = "/count", method = RequestMethod.GET)
    public ModelMap loadAllOrderInfo(DataAuthorizeOrderModel orderModel) throws GlobalException {

        ModelMap map = new ModelMap() ;
        try {
            Map<String, Integer> orderMap = demandOrderService.findAllOrderInfo(orderModel) ;
            map.put("pageInfo", orderMap);
        } catch (GlobalException e) {
            throw new GlobalException(e.getMessage()) ;
        }
        return map;
    }

    @ApiOperation(value =  "根据用户信息增加新订单数据接口")
    @RequestMapping(value = "/addNewOrders", method = RequestMethod.POST)
    public ModelMap addNewDataOrders(@RequestBody DataTransactionOrderModel orderModel) throws GlobalException {

        ModelMap map = new ModelMap() ;
        //获取订单号
        String orderNo = orderService.getOrderNo();
        orderModel.setOrderNo(orderNo);
        Map<String, Object> resultMap = orderService.insertNewOrder(orderModel);
        map.put("pageInfor",resultMap);
        return map ;
    }

}
