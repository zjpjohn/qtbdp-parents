package com.qtdbp.trading.api;

import com.github.pagehelper.PageInfo;
import com.qtdbp.trading.constants.ApiConstants;
import com.qtdbp.trading.exception.GlobalException;
import com.qtdbp.trading.mapper.DataInstitutionInfoMapper;
import com.qtdbp.trading.model.DataInstitutionInfoModel;
import com.qtdbp.trading.service.DataInstitutionInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by liyang on 2017/4/20.
 */
@Api(description = "服务商接口 - 业务API接口")
@RestController
@RequestMapping(value = "/api/institution")
public class DataInstitutionApi {
    @Autowired
    private DataInstitutionInfoService infoService;

    //===================================================================
    // 服务商API接口
    //===================================================================

    /**
     * 当有dataType参数时，根据服务商类型过滤数据
     * @param infoModel
     * @return
     */
    @ApiOperation(value = "获取服务商信息接口，分页获取")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "dataType", value = "服务商类型", defaultValue = "1", dataType = "Integer", required = false, paramType = ApiConstants.PARAM_TYPE_QUERY),
            @ApiImplicitParam(name = "page", value = "当前页（如：1）", defaultValue = "1", dataType = "Integer", required = true, paramType = ApiConstants.PARAM_TYPE_QUERY),
            @ApiImplicitParam(name = "rows", value = "每页显示记录数（如：20）", defaultValue = "20", dataType = "Integer", required = true, paramType = ApiConstants.PARAM_TYPE_QUERY)
    })
    @ResponseBody
    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public ModelMap loadDataInstitutionInfo(DataInstitutionInfoModel infoModel) {

        ModelMap map = new ModelMap();
        List<DataInstitutionInfoModel> list = infoService.findDataInstitutionInfoByCondition(infoModel);
        PageInfo pageInfo = new PageInfo<>(list);
        map.put("pageInfo", pageInfo);
        map.put("queryParam", infoModel);
        map.put("page", infoModel.getPage());
        map.put("rows", infoModel.getRows());

        return map;
    }

    @ApiOperation(value="个人用户升级为服务商API接口")
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ModelMap addDataInstitutionInfo(@RequestBody DataInstitutionInfoModel institutionInfoModel) throws GlobalException {

        ModelMap map = new ModelMap() ;
        try {
            int id = infoService.insertInstitution(institutionInfoModel) ;
            map.put("success", id>0?true:false);
            map.put("id", id);
        } catch (Exception e) {
            throw new GlobalException(e.getMessage()) ;
        }

        return map ;
    }

}
