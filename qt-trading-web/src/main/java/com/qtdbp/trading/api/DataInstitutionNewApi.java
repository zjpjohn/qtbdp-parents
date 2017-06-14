package com.qtdbp.trading.api;

import com.github.pagehelper.PageInfo;
import com.qtdbp.trading.constants.ApiConstants;
import com.qtdbp.trading.controller.BaseController;
import com.qtdbp.trading.exception.GlobalException;
import com.qtdbp.trading.model.DataInstitutionInfoNewModel;
import com.qtdbp.trading.service.DataInstitutionInfoNewService;
import com.qtdbp.trading.service.DataTypeService;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * 新服务商API
 *
 * @author: caidchen
 * @create: 2017-06-06 20:20
 * To change this template use File | Settings | File Templates.
 */
@Api(description = "新服务商 - 业务API接口")
@RestController
@RequestMapping(value = "/api/institutionV2")
public class DataInstitutionNewApi extends BaseController {

    Logger logger = LoggerFactory.getLogger(this.getClass()) ;

    @Autowired
    protected DataInstitutionInfoNewService institutionInfoNewService ;
    @Autowired
    private DataTypeService dataTypeService;

    //===================================================================
    // 定制服务API接口
    //===================================================================

    @ApiOperation(value="服务商数据接口，分页获取")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "typeId", value = "类目ID", dataType = "Integer", paramType = ApiConstants.PARAM_TYPE_QUERY),
            @ApiImplicitParam(name = "name", value = "服务商名称", dataType = "String", paramType = ApiConstants.PARAM_TYPE_QUERY),
            @ApiImplicitParam(name = "institutionType", value = "服务商类型（1 企业，2 个人）", dataType = "Integer", paramType = ApiConstants.PARAM_TYPE_QUERY),
            @ApiImplicitParam(name = "orderBy", value = "排序字段，名称和表中字段一致", dataType = "String", paramType = ApiConstants.PARAM_TYPE_QUERY),
            @ApiImplicitParam(name = "page", value = "当前页（如：1）", defaultValue = "1", dataType = "Integer", required = true, paramType = ApiConstants.PARAM_TYPE_QUERY),
            @ApiImplicitParam(name = "rows", value = "每页显示记录数（如：10）", defaultValue = "10", dataType = "Integer", required = true, paramType = ApiConstants.PARAM_TYPE_QUERY)
    })
    @ResponseBody
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelMap loadInstitutionV2(DataInstitutionInfoNewModel infoNewModel) throws GlobalException {

        ModelMap map = new ModelMap() ;
        // 设置默认每页显示记录数
        try {

            // 默认时间排序
            if(infoNewModel.getOrderBy() == null) infoNewModel.setOrderBy("create_time");

            Integer dataType = infoNewModel.getTypeId();
            if (dataType != null && dataType != 0) {
                String dataTypes = dataTypeService.getDataTypes(dataType);
                if (dataTypes != null && !"".equals(dataTypes)) infoNewModel.setDataTypes(dataTypes);
            }

            List<DataInstitutionInfoNewModel> infoNewModels = institutionInfoNewService.findInstitutionNewForPage(infoNewModel);
            map.put("pageInfo", new PageInfo<>(infoNewModels));
            map.put("page", infoNewModel.getPage());
            map.put("rows", infoNewModel.getRows());
        } catch (Exception e) {
            logger.error("loadInstitutionV2 has error ,message:"+e.getMessage());
            throw new GlobalException(e.getMessage()) ;
        }
        return map ;
    }

    @ApiOperation(value = "根据ID查询单条服务商数据接口")
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public ModelMap findInstitutionV2ById(@ApiParam(name = "id", value = "服务商Id", required = true) @PathVariable Integer id) throws GlobalException {

        ModelMap map = new ModelMap();
        DataInstitutionInfoNewModel infoNewModel = null ;
        HashMap<String, Long> hashMap = null;
        if(id != null) {
            try {
                infoNewModel = institutionInfoNewService.findInstitutionNewById(id);
                hashMap = institutionInfoNewService.findCount(infoNewModel.getCreateId());
                infoNewModel.setCountMap(hashMap);
                map.put("pageInfo", infoNewModel);
            } catch (Exception e) {
                logger.error("findInstitutionV2ById has error ,message:" + e.getMessage());
                throw new GlobalException(e.getMessage());
            }
        }
        map.put("pageInfo", infoNewModel);

        return map;
    }

    @ApiOperation(value="添加服务商数据接口")
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ModelMap insertInstitutionV2(@RequestBody DataInstitutionInfoNewModel infoNewModel) throws GlobalException {

        ModelMap map = new ModelMap();
        Integer id = -1 ;
        if(infoNewModel != null) {
            try {
                id = institutionInfoNewService.insertInstitution(infoNewModel);
            } catch (Exception e) {
                logger.error("insertInstitutionV2 has error ,message:" + e.getMessage());
                throw new GlobalException(e.getMessage());
            }
        }
        map.put("success", id != null ? true : false);
        map.put("id", id);

        return map;
    }
}
