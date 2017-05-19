package com.qtdbp.tradingadmin.api;

import com.github.pagehelper.PageInfo;
import com.qtdbp.trading.constants.ApiConstants;
import com.qtdbp.trading.exception.GlobalException;
import com.qtdbp.trading.model.DataItemModel;
import com.qtdbp.trading.model.DataProductModel;
import com.qtdbp.trading.service.FdfsFileService;
import com.qtdbp.tradingadmin.service.DataProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
public class DataProductApi {

    @Autowired
    private DataProductService productService ;

    //===================================================================
    // 数据包产品API接口
    //===================================================================

    @ApiOperation(value="数据包产品数据接口，分页获取")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "数据包产品名称", dataType = "String", paramType = ApiConstants.PARAM_TYPE_QUERY),
            @ApiImplicitParam(name = "dataType", value = "数据类型ID（如：1）", dataType = "Integer", paramType = ApiConstants.PARAM_TYPE_QUERY),
            @ApiImplicitParam(name = "valIds", value = "属性值Id列表vid1,vid2; 如：1,2", dataType = "String", paramType = ApiConstants.PARAM_TYPE_QUERY),
            @ApiImplicitParam(name = "orderBy", value = "排序字段值; 如：addtime", dataType = "String", required = true, paramType = ApiConstants.PARAM_TYPE_QUERY),
            @ApiImplicitParam(name = "page", value = "当前页（如：1）", defaultValue = "1", dataType = "Integer", required = true, paramType = ApiConstants.PARAM_TYPE_QUERY),
            @ApiImplicitParam(name = "rows", value = "每页显示记录数（如：12）", defaultValue = "12", dataType = "Integer", required = true, paramType = ApiConstants.PARAM_TYPE_QUERY)
    })
    @ResponseBody
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelMap loadDataProducts(DataProductModel productModel) throws GlobalException {

        ModelMap map = new ModelMap() ;
        if(productModel.getOrderBy() == null){
            productModel.setOrderBy("addtime");
        }
        // 设置默认每页显示记录数
        try {
            if(productModel.getRows() == null || productModel.getRows() == 0) productModel.setRows(12);
            List<DataProductModel> productList = productService.findProductsForPage(productModel);
            map.put("pageInfo", new PageInfo<>(productList));
            map.put("page", productModel.getPage());
            map.put("rows", productModel.getRows());
        } catch (Exception e) {
            throw new GlobalException(e.getMessage()) ;
        }
        return map ;
    }

    @ApiOperation(value="添加数据包产品数据接口")
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ModelMap addProduct(@RequestBody DataProductModel productModel) throws GlobalException {
        if(productModel == null) throw new GlobalException("数据不存在，请重新填入") ;
        ModelMap map = new ModelMap() ;
        try {
            Integer id = productService.insertProduct(productModel);
            map.put("success", id>0?true:false);
            map.put("id", id);

        } catch (Exception e) {
            e.printStackTrace();
            throw new GlobalException(e.getMessage()) ;
        }
        return map;
    }

    @ApiOperation(value = "修改数据包产品的上下架接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "数据包产品Id", dataType = "Integer", required = true, paramType = ApiConstants.PARAM_TYPE_QUERY)
    })
    @RequestMapping(value = "/changeState", method = RequestMethod.GET)
    public ModelMap changeState(DataProductModel productModel) throws GlobalException {
        if (productModel.getId() == null) throw new GlobalException("数据包产品不存在，请重新输入");
        ModelMap map = new ModelMap() ;
        try {
            Integer count = productService.updateState(productModel.getId());
            map.put("success", count>0?true:false);
        } catch (GlobalException e) {
            e.printStackTrace();
        }
        return  map;
    }

    @ApiOperation(value="修改数据包产品数据接口")
    @RequestMapping(value = "", method = RequestMethod.PUT)
    public ModelMap updateProduct(@RequestBody DataProductModel productModel) throws GlobalException {
        if(productModel == null) throw new GlobalException("数据不存在，请重新填入") ;
        ModelMap map = new ModelMap() ;
        try {
            Integer id = productService.insertProduct(productModel);
            map.put("success", id>0?true:false);
            map.put("id", id);

        } catch (Exception e) {
            e.printStackTrace();
            throw new GlobalException(e.getMessage()) ;
        }
        return map;
    }


}
