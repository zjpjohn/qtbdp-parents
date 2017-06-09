package com.qtdbp.trading.api;

import com.github.pagehelper.PageInfo;
import com.qtdbp.trading.constants.ApiConstants;
import com.qtdbp.trading.exception.GlobalException;
import com.qtdbp.trading.mapper.DataTypeMapper;
import com.qtdbp.trading.model.DataTypeModel;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

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

    @ApiOperation(value="根据父类目Id查询所有子类目数据接口")
    @ResponseBody
    @RequestMapping(value = "{pid}", method = RequestMethod.GET)
    public ModelMap findSecondDataType(@ApiParam(name = "pid", value = "父类目id", required = true) @PathVariable Integer pid) throws GlobalException {

        ModelMap map = new ModelMap();
        if (pid == null) throw new GlobalException("数据类型为空");
        List<DataTypeModel> list = dataTypeMapper.findDataTypeByParentId(pid);
        try {
            map.put("pageInfo", list);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return map;
    }
}
