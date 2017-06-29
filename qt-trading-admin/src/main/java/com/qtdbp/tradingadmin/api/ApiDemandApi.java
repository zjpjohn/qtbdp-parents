package com.qtdbp.tradingadmin.api;

import com.github.pagehelper.PageInfo;
import com.qtdbp.trading.constants.ApiConstants;
import com.qtdbp.trading.model.ApiDemandModel;
import com.qtdbp.tradingadmin.exception.GlobalAdminException;
import com.qtdbp.tradingadmin.mapper.ApiDemandMapper;
import com.qtdbp.tradingadmin.service.ApiDemandService;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by dell on 2017/6/22.
 */
@Api(description = "Api定制 - 业务API接口")
@RestController
@RequestMapping(value = "/api/demand")
public class ApiDemandApi {

    Logger logger = LoggerFactory.getLogger(this.getClass()) ;

    @Autowired
    private ApiDemandService apiDemandService;

    @Autowired
    private ApiDemandMapper apiDemandMapper;

    @ApiOperation(value="分页查询api定制数据接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "当前页（如：1）", defaultValue = "1", dataType = "Integer", required = true, paramType = ApiConstants.PARAM_TYPE_QUERY),
            @ApiImplicitParam(name = "rows", value = "每页显示记录数（如：12）", defaultValue = "12", dataType = "Integer", required = true, paramType = ApiConstants.PARAM_TYPE_QUERY)
    })
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelMap findApiDemand( ApiDemandModel apiDemandModel) throws GlobalAdminException {

        ModelMap map = new ModelMap() ;
        // 设置默认每页显示记录数
        try {
            if(apiDemandModel.getRows() == null || apiDemandModel.getRows() == 0) apiDemandModel.setRows(10);
            List<ApiDemandModel> apiList = apiDemandService.findApiDemand(apiDemandModel) ;
            map.put("pageInfo", new PageInfo<>(apiList));
            map.put("page", apiDemandModel.getPage());
            map.put("rows", apiDemandModel.getRows());
        } catch (Exception e) {
            logger.error("findApiDemand has error ,message:"+e.getMessage());
            throw new GlobalAdminException(e.getMessage()) ;
        }
        return map ;
    }

    @ApiOperation(value = "根据ID查询单条API定制数据接口")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ModelMap findApiDemandById(
            @ApiParam(name = "id", value = "API定制ID", required = true) @PathVariable Integer id) throws GlobalAdminException {

        ModelMap map = new ModelMap();
        ApiDemandModel apiDemandModel = null ;
        if(id != null) {
            try {
                apiDemandModel = apiDemandMapper.findApiDemandById(id);
            } catch (Exception e) {
                logger.error("findApiDemandById has error ,message:" + e.getMessage());
                throw new GlobalAdminException(e.getMessage());
            }
        }
        map.put("pageInfo", apiDemandModel);

        return map;
    }

}
