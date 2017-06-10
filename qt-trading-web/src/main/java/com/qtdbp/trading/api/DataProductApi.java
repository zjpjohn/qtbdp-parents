package com.qtdbp.trading.api;

import com.github.pagehelper.PageInfo;
import com.qtdbp.trading.constants.ApiConstants;
import com.qtdbp.trading.controller.BaseController;
import com.qtdbp.trading.exception.GlobalException;
import com.qtdbp.trading.mapper.DataProductMapper;
import com.qtdbp.trading.mapper.DataTypeMapper;
import com.qtdbp.trading.model.DataItemModel;
import com.qtdbp.trading.model.DataProductModel;
import com.qtdbp.trading.model.DataTypeModel;
import com.qtdbp.trading.service.DataProductService;
import com.qtdbp.trading.service.FdfsFileService;
import com.qtdbp.trading.service.security.model.SysUser;
import com.qtdbp.trading.utils.CommonUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    @Autowired
    private DataProductService productService ;

    @Autowired
    private DataProductMapper productMapper;

    @Autowired
    private DataTypeMapper dataTypeMapper;

    //===================================================================
    // 数据包产品API接口
    //===================================================================

    @ApiOperation(value="数据包产品数据接口，分页获取")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "数据包产品名称", dataType = "String", paramType = ApiConstants.PARAM_TYPE_QUERY),
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
        if(productModel.getOrderBy() == null){
            productModel.setOrderBy("addtime");
        }

        List<Integer> list = getAllDataTypeIds(productModel.getDataType());
        if (list != null && list.size() != 0){
            String dataTypes = "";
            for (int i = 0; i<list.size(); i++){
                if (i == (list.size()-1)){
                    dataTypes = dataTypes + list.get(i) ;
                }else {
                    dataTypes = dataTypes + list.get(i) + ",";
                }
            }
            productModel.setDataTypes(dataTypes);
        }else {
            if (productModel.getDataType() != 0) {
                productModel.setDataTypes(productModel.getDataType()+"");
            }
        }

        // 设置默认每页显示记录数
        try {
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

    @ApiOperation(value = "删除单条数据包产品接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "数据包产品id", dataType = "Integer", required = true, paramType = ApiConstants.PARAM_TYPE_QUERY)
    })
    @RequestMapping(value = "", method = RequestMethod.DELETE)
    public ModelMap deleteProduct(Integer id) throws GlobalException {

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



    /**
     * 递归查询数据类型某一节点下所有的叶子节点
     * @param dataType
     * @return
     */
    private List getAllDataTypeIds(Integer dataType) {

        if (dataType == 0 || dataType == null) return null;

        List<Integer> dataTypeIdsList = new ArrayList<>();

        List<DataTypeModel> list = dataTypeMapper.findDataTypeByParentId(dataType);
        if (list == null || list.size() == 0) return dataTypeIdsList;
        for(DataTypeModel model : list){
            dataTypeIdsList.add(model.getId());
            List<Integer> idsList = getAllDataTypeIds(model.getId());
            if(idsList != null && idsList.size() != 0) dataTypeIdsList.addAll(idsList);
        }
        return dataTypeIdsList;
    }


}
