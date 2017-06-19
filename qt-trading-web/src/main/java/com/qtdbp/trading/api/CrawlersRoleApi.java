package com.qtdbp.trading.api;

import com.github.pagehelper.PageInfo;
import com.qtdbp.trading.constants.ApiConstants;
import com.qtdbp.trading.controller.BaseController;
import com.qtdbp.trading.exception.ErrorCode;
import com.qtdbp.trading.exception.GlobalException;
import com.qtdbp.trading.mapper.CrawlersRoleMapper;
import com.qtdbp.trading.mapper.DataTypeMapper;
import com.qtdbp.trading.model.CrawlersRoleModel;
import com.qtdbp.trading.model.DataTypeModel;
import com.qtdbp.trading.service.CrawlersRoleService;
import com.qtdbp.trading.service.DataTypeService;
import com.qtdbp.trading.service.security.model.SysUser;
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
 * Created by dell on 2017/6/6.
 */
@Api(description = "爬虫市场接口 - 业务API接口")
@RestController
@RequestMapping(value = "/api/crawlers/role")
public class CrawlersRoleApi extends BaseController{

    Logger logger = LoggerFactory.getLogger(this.getClass()) ;

    @Autowired
    private CrawlersRoleService crawlersRoleService;

    @Autowired
    private DataTypeService dataTypeService;

    @Autowired
    private CrawlersRoleMapper roleMapper;

    @ApiOperation(value="添加爬虫规则数据接口")
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ModelMap insert( CrawlersRoleModel crawlersRoleModel) throws GlobalException {
        ModelMap map = new ModelMap();
        Message message = new Message() ;
        if (crawlersRoleModel != null){
            SysUser user = getPrincipal() ;
            if(user == null) {
                message.setSuccess(false);
                message.setErrorCode(ErrorCode.ERROR_LOGIN_NO);
                message.setMessage("用户请先登录");
            } else {
                try {
                    crawlersRoleModel.setCreateId(user.getId());
                    Integer id = crawlersRoleService.insertRole(crawlersRoleModel);
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

    @ApiOperation(value = "爬虫市场数据接口，分页获取")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "webType", value = "网站类型id（如：1）", dataType = "Integer", paramType = ApiConstants.PARAM_TYPE_QUERY),
            @ApiImplicitParam(name = "isUsed", value = "上下架状态，1上架 0下架", dataType = "Integer", paramType = ApiConstants.PARAM_TYPE_QUERY),
            @ApiImplicitParam(name = "auditStatus", value = "审核状态，0未审核 1已审核 2审核不通过", dataType = "Integer", paramType = ApiConstants.PARAM_TYPE_QUERY),
            @ApiImplicitParam(name = "createId", value = "用户ID", dataType = "Integer", paramType = ApiConstants.PARAM_TYPE_QUERY),
            @ApiImplicitParam(name = "dataType", value = "数据类型ID（如：1）", dataType = "Integer", paramType = ApiConstants.PARAM_TYPE_QUERY),
            @ApiImplicitParam(name = "orderBy", value = "排序字段", dataType = "String", paramType = ApiConstants.PARAM_TYPE_QUERY),
            @ApiImplicitParam(name = "page", value = "当前页（如：1）", defaultValue = "1", dataType = "Integer", required = true, paramType = ApiConstants.PARAM_TYPE_QUERY),
            @ApiImplicitParam(name = "rows", value = "每页显示记录数（如：12）", defaultValue = "12", dataType = "Integer", required = true, paramType = ApiConstants.PARAM_TYPE_QUERY)
    })
    @ResponseBody
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelMap findByCondition(CrawlersRoleModel crawlersRoleModel, Integer dataType) {

        if(crawlersRoleModel.getRows() == null || crawlersRoleModel.getRows() == 0) crawlersRoleModel.setRows(12);
        if (crawlersRoleModel.getOrderBy() == null || "".equals(crawlersRoleModel.getOrderBy())) crawlersRoleModel.setOrderBy("create_time");

        ModelMap map = new ModelMap();
        try {
            if (dataType != null && dataType != 0) {
                String dataTypes = dataTypeService.getDataTypes(dataType);
                if (dataTypes != null && !"".equals(dataTypes)) crawlersRoleModel.setDataTypes(dataTypes);
            }
            List<CrawlersRoleModel> Rolelist = crawlersRoleService.findRoleByCondition(crawlersRoleModel);
            map.put("pageInfo", new PageInfo<>(Rolelist));
            map.put("queryParam", crawlersRoleModel);
            map.put("page", crawlersRoleModel.getPage());
            map.put("rows", crawlersRoleModel.getRows());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    @ApiOperation(value="更新爬虫规则数据接口")
    @RequestMapping(value = "", method = RequestMethod.PUT)
    public ModelMap update(@RequestBody CrawlersRoleModel crawlersRoleModel) throws GlobalException {
        if (crawlersRoleModel == null) throw new GlobalException("数据不存在，请重新输入");

        ModelMap map = new ModelMap();

        try {
            Integer count = crawlersRoleService.updateRoles(crawlersRoleModel);
            map.put("success", count > 0 ? true : false);
            map.put("id", crawlersRoleModel.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return map;
    }

    @ApiOperation(value = "爬虫规则详情数据接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "爬虫规则id", required = true, dataType = "Integer", paramType = ApiConstants.PARAM_TYPE_QUERY)
    })
    @ResponseBody
    @RequestMapping(value = "findRuleById", method = RequestMethod.GET)
    public ModelMap findById(CrawlersRoleModel crawlersRoleModel) {

        ModelMap map = new ModelMap();
        try {

            map.put("pageInfo", crawlersRoleService.findRuleById(crawlersRoleModel.getId()));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    @ApiOperation(value = "修改爬虫规则上下架接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "爬虫规则id", dataType = "Integer", required = true, paramType = ApiConstants.PARAM_TYPE_QUERY)
    })
    @RequestMapping(value = "/changeState", method = RequestMethod.GET)
    public ModelMap changeState(Integer id) throws GlobalException {
        if (id == null) throw new GlobalException("爬虫规则id为空，请重新输入");
        ModelMap map = new ModelMap() ;
        try {
            Integer count = crawlersRoleService.updateState(id);
            map.put("success", count>0?true:false);
        } catch (GlobalException e) {
            e.printStackTrace();
        }
        return  map;
    }

    @ApiOperation(value = "删除单条爬虫规则接口")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ModelMap deleteCrawlersRole(
            @ApiParam(name = "id", value = "爬虫规则id") @PathVariable Integer id) throws GlobalException {

        ModelMap map = new ModelMap();

        if (id != null) {
            try {
                SysUser user = getPrincipal() ;
                if(user == null) throw new GlobalException("授权过期，请重新登陆") ;
                Integer count = roleMapper.deleteCrawlersRole(id);
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
