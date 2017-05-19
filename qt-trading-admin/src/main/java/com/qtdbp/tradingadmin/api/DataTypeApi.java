package com.qtdbp.tradingadmin.api;

import com.github.pagehelper.PageInfo;
import com.qtdbp.trading.constants.ApiConstants;
import com.qtdbp.trading.exception.GlobalException;
import com.qtdbp.trading.model.DataTypeAttrModel;
import com.qtdbp.tradingadmin.mapper.DataTypeMapper;
import com.qtdbp.tradingadmin.model.DataTypeModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dell on 2017/5/15.
 */
@Api(description = "数据类型 - 业务API接口")
@RestController
@RequestMapping(value = "/api/dataType")
public class DataTypeApi {

    @Autowired
    public DataTypeMapper dataTypeMapper;

    @ApiOperation(value="获取数据类型一级类目接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "isUsed", value = "是否可用(1.是 2.否)", dataType = "Integer", paramType = ApiConstants.PARAM_TYPE_QUERY)
    })
    @ResponseBody
    @RequestMapping(value = "/findRootNode", method = RequestMethod.GET)
    public ModelMap findRootNode (DataTypeModel dataTypeModel) {

        ModelMap map = new ModelMap() ;
        try {
            List<DataTypeModel> list = dataTypeMapper.findRootNode(dataTypeModel);
            map.put("pageInfo", new PageInfo<>(list));
            map.put("queryParam", dataTypeModel);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    @ApiOperation(value="获取数据类型二级类目接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "节点id", required = true, dataType = "Integer", paramType = ApiConstants.PARAM_TYPE_QUERY),
            @ApiImplicitParam(name = "isUsed", value = "是否可用(1.是 2.否)", dataType = "Integer", paramType = ApiConstants.PARAM_TYPE_QUERY)
    })
    @ResponseBody
    @RequestMapping(value = "/findSecondNode", method = RequestMethod.GET)
    public ModelMap findSecondNode (DataTypeModel dataTypeModel) {

        ModelMap map = new ModelMap() ;
        try {
            List<DataTypeModel> list = dataTypeMapper.findSecondNode(dataTypeModel);
            map.put("pageInfo", new PageInfo<>(list));
            map.put("queryParam", dataTypeModel);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    @ApiOperation(value = "获取所有属性、属性值列表")
    @ResponseBody
    @RequestMapping(value = "/findAttrAll", method = RequestMethod.GET)
    public List findAttrAll (){
        // 属性、属性值列表
        List<DataTypeAttrModel> attrModels = dataTypeMapper.findAttrAll(null) ;
        return attrModels;
    }

    @ApiOperation(value = "新增数据类型接口")
    @RequestMapping(value = "/insertDataType", method = RequestMethod.POST)
    public ModelMap insertDataType (@RequestBody DataTypeModel dataType) throws GlobalException {
        if (dataType == null) throw new GlobalException("数据类型为空，请重新操作");
        ModelMap map = new ModelMap();
        try {
            Integer count = dataTypeMapper.insertType(dataType);
            map.put("success", count>0?true:false);
            map.put("id", dataType.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return map;
    }

    @ApiOperation(value = "修改数据类型接口")
    @RequestMapping(value = "/insertDataType", method = RequestMethod.PUT)
    public ModelMap updateDataType (@RequestBody DataTypeModel dataType) throws GlobalException {
        if (dataType == null) throw new GlobalException("数据类型为空，请重新操作");
        ModelMap map = new ModelMap();
        try {
            Integer count = dataTypeMapper.updateType(dataType);
            map.put("success", count > 0 ? true : false);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return map;
    }

    @ApiOperation(value = "根据二级节点查询对应的类型属性接口")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "节点id", required = true, dataType = "Integer", paramType = ApiConstants.PARAM_TYPE_QUERY)
    })
    @ResponseBody
    @RequestMapping(value = "findTypeAttr", method = RequestMethod.GET)
    public ModelMap findTypeAttr (DataTypeModel dataType) throws GlobalException {
        if (dataType == null) throw new GlobalException("数据类型为空，请重新操作");
        ModelMap map = new ModelMap();
        try {
            List<DataTypeAttrModel> list = dataTypeMapper.findAttrAll(dataType.getId());
            map.put("pageInfo", list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    @ApiOperation(value = "获取所有数据类型接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "isUsed", value = "是否可用(1.是 2.否)", required = false, dataType = "Integer", paramType = ApiConstants.PARAM_TYPE_QUERY)
    })
    @ResponseBody
    @RequestMapping(value = "findAll", method = RequestMethod.GET)
    public ModelMap findAll(DataTypeModel dataTypeModel){

        ModelMap map = new ModelMap();
        List<DataTypeModel> list = dataTypeMapper.findAll(dataTypeModel.getIsUsed());
        Map<Integer, String> data = new HashMap<>();
        if (list.size() > 0) {
            for (DataTypeModel model : list) {
                data.put(model.getId(),model.getName());
            }
        }
        map.put("pageInfo", data);
        return map;
    }

}
