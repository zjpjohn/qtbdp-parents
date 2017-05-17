package com.qtdbp.trading.api;

import com.github.pagehelper.PageInfo;
import com.qtdbp.trading.constants.ApiConstants;
import com.qtdbp.trading.exception.GlobalException;
import com.qtdbp.trading.mapper.DataTypeMapper;
import com.qtdbp.trading.model.DataTypeModel;
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
 * Created by dell on 2017/5/17.
 */
@Api(description = "数据类型产品 - 业务API接口")
@RestController
@RequestMapping(value = "/api/type")
public class DataTypeApi {

    @Autowired
    private DataTypeMapper dataTypeMapper;

    @ApiOperation(value="数据类型数据接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "数据类型的一级目录id", dataType = "Integer",required = true, paramType = ApiConstants.PARAM_TYPE_QUERY)
    })
    @ResponseBody
    @RequestMapping(value = "/secondDataType", method = RequestMethod.GET)
    public ModelMap findSecondDataType(DataTypeModel dataTypeModel) throws GlobalException {
        ModelMap map = new ModelMap();
        if (dataTypeModel == null) throw new GlobalException("数据类型为空");
        List<DataTypeModel> list = dataTypeMapper.findSecondNode(dataTypeModel.getId());
        try {
            map.put("pageInfo", new PageInfo<>(list));
            map.put("queryParam", dataTypeModel);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }
}
