package com.qtdbp.trading.api;

import com.github.pagehelper.PageInfo;
import com.qtdbp.trading.constants.ApiConstants;
import com.qtdbp.trading.exception.ErrorCode;
import com.qtdbp.trading.mapper.CustomizedMapper;
import com.qtdbp.trading.mapper.DataTypeMapper;
import com.qtdbp.trading.model.CustomServiceModel;
import com.qtdbp.trading.controller.BaseController;
import com.qtdbp.trading.exception.GlobalException;
import com.qtdbp.trading.model.DataTypeModel;
import com.qtdbp.trading.service.CustomizedService;
import com.qtdbp.trading.service.DataTypeService;
import com.qtdbp.trading.service.security.model.SysUser;
import com.qtdbp.trading.utils.CommonUtil;
import com.qtdbp.trading.utils.Message;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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

    @Autowired
    private CustomizedMapper customizedMapper;

    @Autowired
    private DataTypeService dataTypeService;

    //===================================================================
    // 定制服务API接口
    //===================================================================

    @ApiOperation(value="定制服务数据接口，分页获取")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "服务名称", dataType = "String", paramType = ApiConstants.PARAM_TYPE_QUERY),
            @ApiImplicitParam(name = "isUsed", value = "上下架状态，1上架 0下架", dataType = "Integer", paramType = ApiConstants.PARAM_TYPE_QUERY),
            @ApiImplicitParam(name = "auditStatus", value = "审核状态，0未审核 1已审核 2审核不通过", dataType = "Integer", paramType = ApiConstants.PARAM_TYPE_QUERY),
            @ApiImplicitParam(name = "createId", value = "用户ID", dataType = "Integer", paramType = ApiConstants.PARAM_TYPE_QUERY),
            @ApiImplicitParam(name = "serviceType", value = "服务类型（1 定制规则，2 定制数据）", defaultValue = "1", dataType = "Integer", required = true, paramType = ApiConstants.PARAM_TYPE_QUERY),
            @ApiImplicitParam(name = "dataType", value = "数据类型ID（如：1）", dataType = "Integer", paramType = ApiConstants.PARAM_TYPE_QUERY),
            @ApiImplicitParam(name = "orderBy", value = "排序字段", dataType = "String", paramType = ApiConstants.PARAM_TYPE_QUERY),
            @ApiImplicitParam(name = "page", value = "当前页（如：1）", defaultValue = "1", dataType = "Integer", required = true, paramType = ApiConstants.PARAM_TYPE_QUERY),
            @ApiImplicitParam(name = "rows", value = "每页显示记录数（如：10）", defaultValue = "10", dataType = "Integer", required = true, paramType = ApiConstants.PARAM_TYPE_QUERY)
    })
    @ResponseBody
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelMap loadCustomizedData(CustomServiceModel custom, Integer dataType) throws GlobalException {

        if (custom.getOrderBy() == null || "".equals(custom.getOrderBy())) custom.setOrderBy("create_time");
        ModelMap map = new ModelMap() ;
        // 设置默认每页显示记录数
        try {
            if (dataType != null && dataType != 0) {
                String dataTypes = dataTypeService.getDataTypes(dataType);
                if (dataTypes != null && !"".equals(dataTypes)) custom.setDataTypes(dataTypes);
            }
            if(custom.getRows() == null || custom.getRows() == 0) custom.setRows(10);
            List<CustomServiceModel> customList = customizedService.findCustomizedDataForPage(custom);
            for (CustomServiceModel serviceModel : customList) {
                serviceModel.setApplyCount(CommonUtil.randomNum(serviceModel.getId()));
            }
            map.put("pageInfo", new PageInfo<>(customList));
            map.put("page", custom.getPage());
            map.put("rows", custom.getRows());
        } catch (Exception e) {
            logger.error("loadCustomizedData has error ,message:"+e.getMessage());
            throw new GlobalException(e.getMessage()) ;
        }
        return map ;
    }

    @ApiOperation(value = "根据ID查询单条定制服务数据接口")
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public ModelMap findCustomizedDataById(@ApiParam(name = "id", value = "定制服务Id", required = true) @PathVariable Integer id) throws GlobalException {

        ModelMap map = new ModelMap();
        CustomServiceModel custom = null ;
        if(id != null) {
            try {
                custom = customizedService.findCustomizedDataById(id);
            } catch (Exception e) {
                logger.error("findCustomizedDataById has error ,message:" + e.getMessage());
                throw new GlobalException(e.getMessage());
            }
        }
        map.put("pageInfo", custom);

        return map;
    }

    @ApiOperation(value="审核定制服务数据接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "定制服务Id", dataType = "Integer", required = true, paramType = ApiConstants.PARAM_TYPE_QUERY),
            @ApiImplicitParam(name = "status", value = "审核状态，1审核成功 2审核失败", dataType = "Integer", required = true, paramType = ApiConstants.PARAM_TYPE_QUERY),
            @ApiImplicitParam(name = "reason", value = "审核失败原因", dataType = "String", paramType = ApiConstants.PARAM_TYPE_QUERY)
    })
    @RequestMapping(value = "audit", method = RequestMethod.POST)
    public ModelMap auditCustomizedData(Integer id,Integer status,String reason) throws GlobalException {

        ModelMap map = new ModelMap();
        boolean success = false ;
        if(id != null) {
            try {
                SysUser user = getPrincipal();
                if (user == null) throw new GlobalException("授权过期，请重新登陆");

                CustomServiceModel custom = new CustomServiceModel();
                custom.setId(id);
                custom.setAuditor(user.getId()+""); // 系统用户ID
                custom.setAuditStatus(status);
                custom.setAuditFailReason(reason);
                success = customizedService.auditCustomizedData(custom);
            } catch (Exception e) {
                logger.error("auditCustomizedData has error ,message:" + e.getMessage());
                throw new GlobalException(e.getMessage());
            }
        }
        map.put("success", success);

        return map;
    }

    @ApiOperation(value="添加定制服务数据接口")
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ModelMap insertCustomizedData(CustomServiceModel custom) throws GlobalException {

        ModelMap map = new ModelMap();
        Message message = new Message() ;
        Integer id = -1 ;
        if(custom != null) {
            SysUser user = getPrincipal() ;
            if(user == null) {
                message.setSuccess(false);
                message.setErrorCode(ErrorCode.ERROR_LOGIN_NO);
                message.setMessage("用户请先登录");
            } else {
                try {
                    custom.setCreateId(user.getId());
                    id = customizedService.insertCustomizedData(custom);
                    if(id != null && id > 0) {
                        message.setSuccess(true);
                        message.setData(id);
                    }
                } catch (Exception e) {
                    logger.error("customService has error ,message:" + e.getMessage());
                    throw new GlobalException(e.getMessage());
                }
            }
        } else {
            message.setSuccess(false);
            message.setErrorCode(ErrorCode.ERROR_LOGIN_NO);
            message.setMessage("定制服务不存在，请重新输入");
        }
        map.put("result", message);

        return map;
    }

    @ApiOperation(value = "修改定制服务上下架接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "定制服务id", dataType = "Integer", required = true, paramType = ApiConstants.PARAM_TYPE_QUERY)
    })
    @RequestMapping(value = "/changeState", method = RequestMethod.GET)
    public ModelMap changeState(Integer id) throws GlobalException {
        if (id == null) throw new GlobalException("定制服务id为空，请重新输入");
        ModelMap map = new ModelMap() ;
        try {
            Integer count = customizedService.updateState(id);
            map.put("success", count>0?true:false);
        } catch (GlobalException e) {
            e.printStackTrace();
        }
        return  map;
    }

    @ApiOperation(value = "删除单条定制服务接口")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ModelMap deleteCrawlersRole(
            @ApiParam(name = "id", value = "定制服务id") @PathVariable Integer id) throws GlobalException {

        ModelMap map = new ModelMap();

        if (id != null) {
            try {
                SysUser user = getPrincipal() ;
                if(user == null) throw new GlobalException("授权过期，请重新登陆") ;
                Integer count = customizedMapper.deleteCustom(id);
                map.put("success", count > 0 ? true : false);
            } catch (Exception e) {
                e.getMessage();
            }
        } else {
            map.put("success", false);
        }

        return map;
    }
}
