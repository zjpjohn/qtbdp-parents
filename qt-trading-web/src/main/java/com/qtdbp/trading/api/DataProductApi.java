package com.qtdbp.trading.api;

import com.github.pagehelper.PageInfo;
import com.qtdbp.trading.constants.ApiConstants;
import com.qtdbp.trading.model.DataProductModel;
import com.qtdbp.trading.service.DataProductService;
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
 * 提供数据包产品API接口
 *
 * @author: caidchen
 * @create: 2017-04-16 16:02
 * To change this template use File | Settings | File Templates.
 */
@Api(description = "数据商城页面 - 业务API接口")
@RestController
@RequestMapping(value = "/api/product")
public class DataProductApi {

    @Autowired
    private DataProductService productService ;

    @ApiOperation(value="数据包产品数据接口，分页获取")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "数据包产品名称", dataType = "String", paramType = ApiConstants.PARAM_TYPE_QUERY),
            @ApiImplicitParam(name = "page", value = "当前页（如：1）", defaultValue = "1", dataType = "Integer", required = true, paramType = ApiConstants.PARAM_TYPE_QUERY),
            @ApiImplicitParam(name = "rows", value = "每页显示记录数（如：12）", defaultValue = "12", dataType = "Integer", required = true, paramType = ApiConstants.PARAM_TYPE_QUERY)
    })
    @ResponseBody
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelMap loadDataProducts(DataProductModel productModel) {

        ModelMap map = new ModelMap() ;
        // 设置默认每页显示记录数
        if(productModel.getRows() == null && productModel.getRows() == 0) productModel.setRows(12);
        List<DataProductModel> productList = productService.findProductsForPage(productModel);
        map.put("pageInfo", new PageInfo<>(productList));
        map.put("queryParam", productModel);
        map.put("page", productModel.getPage());
        map.put("rows", productModel.getRows());

        return map ;
    }
}
