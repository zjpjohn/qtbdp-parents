package com.qtdbp.tradingadmin.api;

import com.github.pagehelper.PageInfo;
import com.qtdbp.trading.constants.ApiConstants;
import com.qtdbp.trading.model.CustomServiceModel;
import com.qtdbp.tradingadmin.base.security.SecurityUser;
import com.qtdbp.tradingadmin.controller.BaseController;
import com.qtdbp.tradingadmin.exception.GlobalAdminException;
import com.qtdbp.tradingadmin.service.CustomizedService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * 定制服务API
 *
 * @author: caidchen
 * @create: 2017-06-06 13:35
 * To change this template use File | Settings | File Templates.
 */
@Api(description = "定制服务 - 业务API接口")
@RestController
@RequestMapping(value = "/api/customized")
public class CustomizedApi extends BaseController {

    Logger logger = LoggerFactory.getLogger(this.getClass()) ;

    @Autowired
    private CustomizedService customizedService ;

    //===================================================================
    // 定制服务API接口
    //===================================================================

    @ApiOperation(value="定制服务数据接口，分页获取")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "服务名称", dataType = "String", paramType = ApiConstants.PARAM_TYPE_QUERY),
            @ApiImplicitParam(name = "serviceType", value = "服务类型（1 定制规则，2 定制数据）", defaultValue = "1", dataType = "Integer", required = true, paramType = ApiConstants.PARAM_TYPE_QUERY),
            @ApiImplicitParam(name = "page", value = "当前页（如：1）", defaultValue = "1", dataType = "Integer", required = true, paramType = ApiConstants.PARAM_TYPE_QUERY),
            @ApiImplicitParam(name = "rows", value = "每页显示记录数（如：10）", defaultValue = "10", dataType = "Integer", required = true, paramType = ApiConstants.PARAM_TYPE_QUERY)
    })
    @ResponseBody
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelMap loadCustomizedData(CustomServiceModel custom) throws GlobalAdminException {

        ModelMap map = new ModelMap() ;
        // 设置默认每页显示记录数
        try {
            if(custom.getRows() == null || custom.getRows() == 0) custom.setRows(10);
            List<CustomServiceModel> customList = customizedService.findCustomizedDataForPage(custom);
            map.put("pageInfo", new PageInfo<>(customList));
            map.put("page", custom.getPage());
            map.put("rows", custom.getRows());
        } catch (Exception e) {
            logger.error("loadCustomizedData has error ,message:"+e.getMessage());
            throw new GlobalAdminException(e.getMessage()) ;
        }
        return map ;
    }

    @ApiOperation(value = "根据ID查询单条定制服务数据接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "定制服务Id", dataType = "Integer", required = true, paramType = ApiConstants.PARAM_TYPE_QUERY)
    })
    @RequestMapping(value = "id", method = RequestMethod.GET)
    public ModelMap findCustomizedData(Integer id) throws GlobalAdminException {

        if(id == null) throw new GlobalAdminException("数据不存在，请重新填入") ;

        ModelMap map = new ModelMap() ;
        try {
            CustomServiceModel custom = customizedService.findCustomizedDataById(id);
            if(custom == null) custom = new CustomServiceModel() ;
            map.put("pageInfo", custom);
        } catch (Exception e) {
            logger.error("findCustomizedData has error ,message:"+e.getMessage());
            throw new GlobalAdminException(e.getMessage()) ;
        }

        return map;
    }

    @ApiOperation(value="审核定制服务数据接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "定制服务Id", dataType = "Integer", required = true, paramType = ApiConstants.PARAM_TYPE_QUERY),
            @ApiImplicitParam(name = "status", value = "审核状态，1审核成功 2审核失败", dataType = "Integer", required = true, paramType = ApiConstants.PARAM_TYPE_QUERY),
            @ApiImplicitParam(name = "reason", value = "审核失败原因", dataType = "String", paramType = ApiConstants.PARAM_TYPE_QUERY)
    })
    @RequestMapping(value = "audit", method = RequestMethod.POST)
    public ModelMap auditCustomizedData(Integer id,Integer status,String reason) throws GlobalAdminException {

        ModelMap map = new ModelMap();
        boolean success = false ;
        if(id != null) {
            try {
                SecurityUser user = getPrincipal();
                if (user == null) throw new GlobalAdminException("授权过期，请重新登陆");

                CustomServiceModel custom = new CustomServiceModel();
                custom.setId(id);
                custom.setAuditor(user.getUserId()); // 系统用户ID
                custom.setAuditTime(new Date());
                custom.setAuditStatus(status);
                custom.setAuditFailReason(reason);
                success = customizedService.auditCustomizedData(custom);
                map.put("success", success);
            } catch (Exception e) {
                logger.error("auditCustomizedData has error ,message:" + e.getMessage());
                throw new GlobalAdminException(e.getMessage());
            }
        }
        map.put("success", success);

        return map;
    }

    @ApiOperation(value="添加定制服务数据接口")
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ModelMap insertCustomizedData(@RequestBody CustomServiceModel custom) throws GlobalAdminException {

        ModelMap map = new ModelMap();
        Integer id = -1 ;
        if(custom != null) {
            try {
                id = customizedService.insertCustomizedData(custom);
            } catch (Exception e) {
                logger.error("insertCustomizedData has error ,message:" + e.getMessage());
                throw new GlobalAdminException(e.getMessage());
            }
        }
        map.put("success", id != null ? true : false);
        map.put("id", id);

        return map;
    }
}
