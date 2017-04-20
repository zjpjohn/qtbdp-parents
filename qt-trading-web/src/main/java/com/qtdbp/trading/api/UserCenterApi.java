package com.qtdbp.trading.api;

import com.github.pagehelper.PageInfo;
import com.qtdbp.trading.constants.ApiConstants;
import com.qtdbp.trading.exception.GlobalException;
import com.qtdbp.trading.model.DataAuthorizeOrderModel;
import com.qtdbp.trading.model.DataSosInfoModel;
import com.qtdbp.trading.model.DataTransactionOrderModel;
import com.qtdbp.trading.service.DataAuthorizeOrderService;
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
 * 个人中心API
 *
 * @author: caidchen
 * @create: 2017-04-19 13:21
 * To change this template use File | Settings | File Templates.
 */
@Api(description = "个人中心页面 - 业务API接口")
@RestController
@RequestMapping(value = "/api/usercenter")
public class UserCenterApi {

    @Autowired
    private DataTransactionOrderService orderService ;

    @Autowired
    private DataAuthorizeOrderService demandOrderService ;

    @Autowired
    private DataSosInfoService sosInfoService ;

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

    //===================================================================
    // 需求订单API接口
    //===================================================================

    @ApiOperation(value="最新前5条需求订单数据接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户ID（如：1）", dataType = "Integer", required = true, paramType = ApiConstants.PARAM_TYPE_QUERY),
            @ApiImplicitParam(name = "productType", value = "产品类型（1数据众包、2方案召集）", dataType = "Integer", required = true, paramType = ApiConstants.PARAM_TYPE_QUERY),
            @ApiImplicitParam(name = "page", value = "前几条数据（如：5）", defaultValue = "5", dataType = "Integer", required = true, paramType = ApiConstants.PARAM_TYPE_QUERY)
    })
    @ResponseBody
    @RequestMapping(value = "/newdemandorders", method = RequestMethod.GET)
    public ModelMap loadDataNewDemandOrders(int userId, int productType, int page) throws GlobalException {

        ModelMap map = new ModelMap() ;
        // 设置默认每页显示记录数
        try {
            List<DataAuthorizeOrderModel> orderModelList = demandOrderService.findDemandOrderByUserId(userId, productType, page);
            map.put("pageInfo", new PageInfo<>(orderModelList));
        } catch (Exception e) {
            throw new GlobalException(e.getMessage()) ;
        }

        return map ;
    }

    @ApiOperation(value="需求订单数据接口，分页获取")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户ID（如：1）", dataType = "Integer", required = true, paramType = ApiConstants.PARAM_TYPE_QUERY),
            @ApiImplicitParam(name = "productType", value = "产品类型（1数据众包、2方案召集）", dataType = "Integer", required = true, paramType = ApiConstants.PARAM_TYPE_QUERY),
            @ApiImplicitParam(name = "orderState", value = "订单状态（1待支付、2已撤销、3已支付）,不传表示所有", dataType = "Integer", paramType = ApiConstants.PARAM_TYPE_QUERY),
            @ApiImplicitParam(name = "page", value = "当前页（如：1）", defaultValue = "1", dataType = "Integer", required = true, paramType = ApiConstants.PARAM_TYPE_QUERY),
            @ApiImplicitParam(name = "rows", value = "每页显示记录数（如：10）", defaultValue = "10", dataType = "Integer", required = true, paramType = ApiConstants.PARAM_TYPE_QUERY)
    })
    @ResponseBody
    @RequestMapping(value = "/demandorders", method = RequestMethod.GET)
    public ModelMap loadDataDemandOrders(DataAuthorizeOrderModel orderModel) throws GlobalException {

        ModelMap map = new ModelMap() ;
        // 设置默认每页显示记录数
        try {
            if (orderModel.getRows() == null || orderModel.getRows() == 0) orderModel.setRows(10);
            List<DataAuthorizeOrderModel> orderModelList = demandOrderService.findDemandOrdersByCondtion(orderModel);
            map.put("pageInfo", new PageInfo<>(orderModelList));
            map.put("queryParam", orderModel);
            map.put("page", orderModel.getPage());
            map.put("rows", orderModel.getRows());
        } catch (Exception e) {
            throw new GlobalException(e.getMessage()) ;
        }

        return map ;
    }

    //===================================================================
    // 方案召集API接口
    //===================================================================

    @ApiOperation(value="方案召集API接口，分页获取")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户ID（如：1）", dataType = "Integer", required = true, paramType = ApiConstants.PARAM_TYPE_QUERY),
            @ApiImplicitParam(name = "dataType", value = "数据类型ID（如：1），不传查询所有类型", dataType = "Integer", paramType = ApiConstants.PARAM_TYPE_QUERY),
            @ApiImplicitParam(name = "page", value = "当前页（如：1）", defaultValue = "1", dataType = "Integer", required = true, paramType = ApiConstants.PARAM_TYPE_QUERY),
            @ApiImplicitParam(name = "rows", value = "每页显示记录数（如：10）", defaultValue = "10", dataType = "Integer", required = true, paramType = ApiConstants.PARAM_TYPE_QUERY)
    })
    @ResponseBody
    @RequestMapping(value = "/sosInfos", method = RequestMethod.GET)
    public ModelMap loadDataSosInfos(DataSosInfoModel sosInfoModel) throws GlobalException {

        ModelMap map = new ModelMap() ;
        // 设置默认每页显示记录数
        try {
            if (sosInfoModel.getRows() == null || sosInfoModel.getRows() == 0) sosInfoModel.setRows(10);
            List<DataSosInfoModel> orderModelList = sosInfoService.findDataSosInfoByCondition(sosInfoModel);
            map.put("pageInfo", new PageInfo<>(orderModelList));
            map.put("queryParam", sosInfoModel);
            map.put("page", sosInfoModel.getPage());
            map.put("rows", sosInfoModel.getRows());
        } catch (Exception e) {
            throw new GlobalException(e.getMessage()) ;
        }

        return map ;
    }
}
