package com.qtdbp.trading.api;

import com.github.pagehelper.PageInfo;
import com.qtdbp.trading.constants.ApiConstants;
import com.qtdbp.trading.controller.BaseController;
import com.qtdbp.trading.exception.ErrorCode;
import com.qtdbp.trading.exception.GlobalException;
import com.qtdbp.trading.mapper.DataProductMapper;
import com.qtdbp.trading.mapper.DataTypeMapper;
import com.qtdbp.trading.model.DataItemModel;
import com.qtdbp.trading.model.DataProductModel;
import com.qtdbp.trading.model.DataTypeModel;
import com.qtdbp.trading.service.DataProductService;
import com.qtdbp.trading.service.DataTypeService;
import com.qtdbp.trading.service.security.model.SysUser;
import com.qtdbp.trading.utils.CommonUtil;
import com.qtdbp.trading.utils.Message;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 提供数据包产品API接口
 *
 * @author: caidchen
 * @create: 2017-04-16 16:02
 * To change this template use File | Settings | File Templates.
 */
@Api(description = "数据包产品 - 业务API接口")
@RestController
@RequestMapping(value = "/api/product")
public class DataProductApi extends BaseController{

    Logger logger = LoggerFactory.getLogger(this.getClass()) ;

    @Autowired
    private DataProductService productService ;

    @Autowired
    private DataProductMapper productMapper;

    @Autowired
    private DataTypeService dataTypeService;

    //===================================================================
    // 数据包产品API接口
    //===================================================================

    @ApiOperation(value="数据包产品数据接口，分页获取")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "数据包产品名称", dataType = "String", paramType = ApiConstants.PARAM_TYPE_QUERY),
            @ApiImplicitParam(name = "isUsed", value = "上下架状态，1上架 0下架", dataType = "Integer", paramType = ApiConstants.PARAM_TYPE_QUERY),
            @ApiImplicitParam(name = "auditStatus", value = "审核状态，0未审核 1已审核 2审核不通过", dataType = "Integer", paramType = ApiConstants.PARAM_TYPE_QUERY),
            @ApiImplicitParam(name = "userId", value = "用户ID", dataType = "Integer", paramType = ApiConstants.PARAM_TYPE_QUERY),
            @ApiImplicitParam(name = "dataType", value = "数据类型ID（如：1）", dataType = "Integer", paramType = ApiConstants.PARAM_TYPE_QUERY),
            @ApiImplicitParam(name = "valIds", value = "属性值Id列表vid1,vid2; 如：1,2", dataType = "String", paramType = ApiConstants.PARAM_TYPE_QUERY),
            @ApiImplicitParam(name = "dataStatus", value = "数据类型（如：1）", dataType = "Integer", paramType = ApiConstants.PARAM_TYPE_QUERY),
            @ApiImplicitParam(name = "dataSrc", value = "数据来源（如：1）", dataType = "Integer", paramType = ApiConstants.PARAM_TYPE_QUERY),
            @ApiImplicitParam(name = "isFree", value = "是否免费 0：否，1是", dataType = "Integer", paramType = ApiConstants.PARAM_TYPE_QUERY),
            @ApiImplicitParam(name = "orderBy", value = "排序字段值; 如：addtime", dataType = "String", paramType = ApiConstants.PARAM_TYPE_QUERY),
            @ApiImplicitParam(name = "page", value = "当前页（如：1）", defaultValue = "1", dataType = "Integer", required = true, paramType = ApiConstants.PARAM_TYPE_QUERY),
            @ApiImplicitParam(name = "rows", value = "每页显示记录数（如：12）", defaultValue = "12", dataType = "Integer", required = true, paramType = ApiConstants.PARAM_TYPE_QUERY)
    })
    @ResponseBody
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelMap loadDataProducts(DataProductModel productModel) throws GlobalException {

        ModelMap map = new ModelMap() ;
        if(productModel.getOrderBy() == null || "".equals(productModel.getOrderBy())) productModel.setOrderBy("addtime");

        // 设置默认每页显示记录数
        try {
            int dataType = productModel.getDataType();
            if (dataType != 0) {
                String dataTypes = dataTypeService.getDataTypes(dataType);
                if (dataTypes != null && !"".equals(dataTypes)) productModel.setDataTypes(dataTypes);
            }
            if(productModel.getRows() == null || productModel.getRows() == 0) productModel.setRows(12);
            List<DataProductModel> productList = productService.findProductsForPage(productModel);
            map.put("pageInfo", new PageInfo<>(productList));
            map.put("queryParam", productModel);
            map.put("page", productModel.getPage());
            map.put("rows", productModel.getRows());
        } catch (Exception e) {
            throw new GlobalException(e.getMessage()) ;
        }
        return map ;
    }

    @ApiOperation(value="添加数据包产品数据接口")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelMap addProduct(DataProductModel productModel) throws GlobalException {

        ModelMap map = new ModelMap();
        Message message = new Message() ;
        Integer id = -1 ;
        if(productModel != null) {
            SysUser user = getPrincipal() ;
            if(user == null) {
                message.setSuccess(false);
                message.setErrorCode(ErrorCode.ERROR_LOGIN_NO);
                message.setMessage("用户请先登录");
            } else {
                try {
                    productModel.setUserId(user.getId());
                    id = productService.insertProduct(productModel);
                    if(id != null && id > 0) {
                        message.setSuccess(true);
                        message.setData(id);
                    }
                } catch (Exception e) {
                    logger.error("productService has error ,message:" + e.getMessage());
                    throw new GlobalException(e.getMessage());
                }
            }
        } else {
            message.setSuccess(false);
            message.setErrorCode(ErrorCode.ERROR_LOGIN_NO);
            message.setMessage("数据包产品不存在，请重新输入");
        }
        map.put("result", message);
        return map;
    }

    @ApiOperation(value = "删除单条数据包产品接口")
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public ModelMap deleteProduct(
            @ApiParam(name = "id", value = "数据包产品id") @PathVariable Integer id) throws GlobalException {

        ModelMap map = new ModelMap();

        if (id != null) {
            try {
                SysUser user = getPrincipal() ;
                if(user == null) throw new GlobalException("授权过期，请重新登陆") ;
                Integer count = productMapper.deleteProduct(id);
                map.put("success", count > 0 ? true : false);
            } catch (Exception e) {
                e.getMessage();
            }
        } else {
            map.put("success", false);
        }

        return map;
    }

    @ApiOperation(value = "根据ID查询单条数据包产品接口")
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public ModelMap findProductById(
            @ApiParam(name = "id", value = "数据包产品Id", required = true) @PathVariable Integer id) throws GlobalException {

        ModelMap map = new ModelMap() ;
        try {
            DataProductModel productModel = productService.findProductById(id);
            map.put("pageInfo", productModel);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return map;
    }

    @ApiOperation(value = "检查是否可以购买数据包产品")
    @RequestMapping(value = "/check/{id}/{type}", method = RequestMethod.GET)
    public ModelMap buyData(
            @ApiParam(name = "id", value = "数据包产品Id", required = true) @PathVariable("id") Integer productId,
            @ApiParam(name = "type", value = "产品类型，1数据条目 2数据包", required = true) @PathVariable("type") Integer productType){

        ModelMap map = new ModelMap();
        Message message = new Message() ;
        // 未登陆请先登录
        SysUser user = getPrincipal() ;
        if(user == null) {
            message.setSuccess(false);
            message.setErrorCode(ErrorCode.ERROR_LOGIN_NO);
            message.setMessage("用户请先登录");
        } else {
            message = productService.checkBuyData(productId, productType, user.getId());
        }
        map.put("result", message) ;

        return map;
    }

    @ApiOperation(value = "修改数据包产品的上下架接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "数据包产品Id", dataType = "Integer", required = true, paramType = ApiConstants.PARAM_TYPE_QUERY)
    })
    @RequestMapping(value = "/changeState", method = RequestMethod.GET)
    public ModelMap changeState(Integer id) throws GlobalException {
        if (id == null) throw new GlobalException("数据包产品id为空，请重新输入");
        ModelMap map = new ModelMap() ;
        try {
            Integer count = productService.updateState(id);
            map.put("success", count>0?true:false);
        } catch (GlobalException e) {
            e.printStackTrace();
        }
        return  map;
    }

    //===================================================================
    // 数据条目API接口
    //===================================================================

    @ApiOperation(value="数据条目接口，分页获取")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "productId", value = "产品ID（如：1）", dataType = "Integer", required = true, paramType = ApiConstants.PARAM_TYPE_QUERY),
            @ApiImplicitParam(name = "page", value = "当前页（如：1）", defaultValue = "1", dataType = "Integer", required = true, paramType = ApiConstants.PARAM_TYPE_QUERY),
            @ApiImplicitParam(name = "rows", value = "每页显示记录数（如：20）", defaultValue = "20", dataType = "Integer", required = true, paramType = ApiConstants.PARAM_TYPE_QUERY)
    })
    @ResponseBody
    @RequestMapping(value = "/item", method = RequestMethod.GET)
    public ModelMap loadDataItems(DataItemModel item) throws GlobalException {
        ModelMap map = new ModelMap() ;
        // 设置默认每页显示记录数
        try {
            if(item.getRows() == null || item.getRows() == 0) item.setRows(20);
            List<DataItemModel> itemList = productService.findItemsByProductIdForPage(item);
            if (itemList != null && itemList.size() > 0) {
                for (DataItemModel itemModel : itemList) {
                    itemModel.setDownloadCount(itemModel.getDownloadCount() + CommonUtil.randomNum(itemModel.getId()));
                    itemModel.setViewCount(itemModel.getViewCount() + CommonUtil.randomNum(itemModel.getId()));
                }
            }
            map.put("pageInfo", new PageInfo<>(itemList));
            map.put("queryParam", item);
            map.put("page", item.getPage());
            map.put("rows", item.getRows());
        } catch (Exception e) {
            throw new GlobalException(e.getMessage()) ;
        }
        return map ;
    }

}
