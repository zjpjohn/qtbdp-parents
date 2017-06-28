package com.qtdbp.tradingadmin.api;

import com.github.pagehelper.PageInfo;
import com.qtdbp.trading.constants.ApiConstants;
import com.qtdbp.trading.service.security.model.SysUser;
import com.qtdbp.tradingadmin.mapper.DataProductMapper;
import com.qtdbp.trading.model.DataProductModel;
import com.qtdbp.tradingadmin.base.security.SecurityUser;
import com.qtdbp.tradingadmin.controller.BaseController;
import com.qtdbp.tradingadmin.exception.GlobalAdminException;
import com.qtdbp.tradingadmin.service.DataProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 提供数据包产品API接口
 *
 * @author: liyang
 * @create: 2017-05-12 16:02
 * To change this template use File | Settings | File Templates.
 */
@Api(description = "数据包产品 - 业务API接口")
@RestController
@RequestMapping(value = "/api/product")
public class DataProductApi extends BaseController {

    @Autowired
    private DataProductService productService ;

    @Autowired
    private DataProductMapper productMapper;

    //===================================================================
    // 数据包产品API接口
    //===================================================================

    @ApiOperation(value="数据包产品数据接口，分页获取")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "designation", value = "数据包产品名称", dataType = "String", paramType = ApiConstants.PARAM_TYPE_QUERY),
            @ApiImplicitParam(name = "dataType", value = "数据类型ID（如：1）", dataType = "Integer", paramType = ApiConstants.PARAM_TYPE_QUERY),
            @ApiImplicitParam(name = "userId", value = "用户id（0：后台系统用户）", dataType = "Integer", paramType = ApiConstants.PARAM_TYPE_QUERY),
            @ApiImplicitParam(name = "page", value = "当前页（如：1）", defaultValue = "1", dataType = "Integer", required = true, paramType = ApiConstants.PARAM_TYPE_QUERY),
            @ApiImplicitParam(name = "rows", value = "每页显示记录数（如：12）", defaultValue = "12", dataType = "Integer", required = true, paramType = ApiConstants.PARAM_TYPE_QUERY)
    })
    @ResponseBody
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelMap loadDataProducts(DataProductModel productModel) throws GlobalAdminException {

        ModelMap map = new ModelMap() ;
        // 设置默认每页显示记录数
        try {
            if(productModel.getRows() == null || productModel.getRows() == 0) productModel.setRows(12);
            List<DataProductModel> productList = productService.findProductsForPage(productModel);
            map.put("pageInfo", new PageInfo<>(productList));
            map.put("page", productModel.getPage());
            map.put("rows", productModel.getRows());
        } catch (Exception e) {
            throw new GlobalAdminException(e.getMessage()) ;
        }
        return map ;
    }

    @ApiOperation(value="添加数据包产品数据接口")
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ModelMap addProduct(@RequestBody DataProductModel productModel) throws GlobalAdminException {
        if(productModel == null) throw new GlobalAdminException("数据不存在，请重新填入") ;
        productModel.setUserId(0);
        ModelMap map = new ModelMap() ;

        try {
            Integer id = productService.insertProduct(productModel);
            map.put("success", id>0?true:false);
            map.put("id", id);

        } catch (Exception e) {
            e.printStackTrace();
            throw new GlobalAdminException(e.getMessage()) ;
        }
        return map;
    }

    @ApiOperation(value = "修改数据包产品的上下架接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "数据包产品Id", dataType = "Integer", required = true, paramType = ApiConstants.PARAM_TYPE_QUERY)
    })
    @RequestMapping(value = "/changeState", method = RequestMethod.GET)
    public ModelMap changeState(Integer id) throws GlobalAdminException {
        if (id == null) throw new GlobalAdminException("数据包产品id为空，请重新输入");
        ModelMap map = new ModelMap() ;
        try {
            Integer count = productService.updateState(id);
            map.put("success", count>0?true:false);
        } catch (GlobalAdminException e) {
            e.printStackTrace();
        }
        return  map;
    }

    @ApiOperation(value="修改数据包产品数据接口")
    @RequestMapping(value = "", method = RequestMethod.PUT)
    public ModelMap updateProduct(@RequestBody DataProductModel productModel) throws GlobalAdminException {
        if(productModel == null) throw new GlobalAdminException("数据不存在，请重新填入") ;
        ModelMap map = new ModelMap() ;
        try {
            Integer count = productService.updateProduct(productModel);
            map.put("success", count>0?true:false);
        } catch (Exception e) {
            e.printStackTrace();
            throw new GlobalAdminException(e.getMessage()) ;
        }
        return map;
    }

    @ApiOperation(value = "根据ID查询单条数据包产品接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "数据包产品Id", dataType = "Integer", required = true, paramType = ApiConstants.PARAM_TYPE_QUERY)
    })
    @RequestMapping(value = "findProductById", method = RequestMethod.GET)
    public ModelMap findProductById(Integer id) throws GlobalAdminException {
        if(id == null) throw new GlobalAdminException("id为空，请重新填入") ;
        ModelMap map = new ModelMap() ;
        DataProductModel productModel = null;
        try {
            productModel = productService.findProductById(id);
            map.put("pageInfo", productModel);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return map;
    }

    @ApiOperation(value="数据包产品审核接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "数据包产品id", dataType = "Integer", required = true, paramType = ApiConstants.PARAM_TYPE_QUERY),
            @ApiImplicitParam(name = "status", value = "审核状态，1审核成功 2审核失败", dataType = "Integer", required = true, paramType = ApiConstants.PARAM_TYPE_QUERY),
            @ApiImplicitParam(name = "reason", value = "审核失败原因", dataType = "String", paramType = ApiConstants.PARAM_TYPE_QUERY)
    })
    @RequestMapping(value = "/auditProduct", method = RequestMethod.PUT)
    public ModelMap auditProduct(Integer id,Integer status,String reason ) throws GlobalAdminException {

        ModelMap map = new ModelMap();

        if (id != null) {
            try {
                SecurityUser user = getPrincipal();
                if (user == null) throw new GlobalAdminException("授权过期，请重新登陆");
                DataProductModel productModel = new DataProductModel();
                productModel.setId(id);
                productModel.setAuditor(user.getUserId()); // 系统用户ID
                productModel.setAuditStatus(status);
                productModel.setAuditFailReason(reason);
                Integer count = productService.auditProduct(productModel);
                map.put("success", count > 0 ? true : false);
                map.put("id", productModel.getId());
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            map.put("success", false);
        }

        return map;
    }

    @ApiOperation(value = "删除单条数据包产品接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "数据包产品id", dataType = "Integer", required = true, paramType = ApiConstants.PARAM_TYPE_QUERY)
    })
    @RequestMapping(value = "", method = RequestMethod.DELETE)
    public ModelMap deleteProduct(Integer id) throws GlobalAdminException {

        ModelMap map = new ModelMap();

        if (id != null) {
            SecurityUser user = getPrincipal() ;
            if(user == null) throw new GlobalAdminException("授权过期，请重新登陆") ;
            Integer count = productMapper.deleteProduct(id);
            map.put("success", count > 0 ? true : false);
        } else {
            map.put("success", false);
        }

        return map;
    }

}
