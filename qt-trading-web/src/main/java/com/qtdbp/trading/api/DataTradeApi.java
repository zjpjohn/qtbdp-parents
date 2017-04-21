package com.qtdbp.trading.api;

import com.github.pagehelper.PageInfo;
import com.qtdbp.trading.constants.ApiConstants;
import com.qtdbp.trading.exception.GlobalException;
import com.qtdbp.trading.model.DataAuthorizeOrderModel;
import com.qtdbp.trading.model.DataBuyInfoModel;
import com.qtdbp.trading.model.DataSosInfoModel;
import com.qtdbp.trading.model.DataTransactionOrderModel;
import com.qtdbp.trading.service.DataAuthorizeOrderService;
import com.qtdbp.trading.service.DataBuyInfoService;
import com.qtdbp.trading.service.DataSosInfoService;
import com.qtdbp.trading.service.DataTransactionOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
    private DataTransactionOrderService orderService ;

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
}
