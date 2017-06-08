package com.qtdbp.tradingadmin.api;

import com.github.pagehelper.PageInfo;
import com.qtdbp.trading.constants.ApiConstants;
import com.qtdbp.trading.exception.GlobalException;
import com.qtdbp.tradingadmin.mapper.CrawlersRoleMapper;
import com.qtdbp.trading.model.CrawlersRoleModel;
import com.qtdbp.tradingadmin.base.security.SecurityUser;
import com.qtdbp.tradingadmin.controller.BaseController;
import com.qtdbp.tradingadmin.exception.GlobalAdminException;
import com.qtdbp.tradingadmin.service.CrawlersRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 爬虫市场服务Api
 * Created by dell on 2017/6/6.
 */
@Api(description = "爬虫市场 - 业务API接口")
@RestController
@RequestMapping(value = "/api/crawlers/role")
public class CrawlersRoleApi extends BaseController {

    @Autowired
    private CrawlersRoleService crawlersRoleService;

    @Autowired
    private CrawlersRoleMapper roleMapper;

    @ApiOperation(value = "爬虫市场数据接口，分页获取")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "typeId", value = "类目类型id（如：1）", dataType = "Integer", paramType = ApiConstants.PARAM_TYPE_QUERY),
            @ApiImplicitParam(name = "webType", value = "网站类型id（如：1）", dataType = "Integer", paramType = ApiConstants.PARAM_TYPE_QUERY),
            @ApiImplicitParam(name = "isUsed", value = "是否启用（0：否，1：是）", dataType = "Integer", paramType = ApiConstants.PARAM_TYPE_QUERY),
            @ApiImplicitParam(name = "orderBy", value = "排序字段", required = false, dataType = "String", paramType = ApiConstants.PARAM_TYPE_QUERY),
            @ApiImplicitParam(name = "page", value = "当前页（如：1）", defaultValue = "1", dataType = "Integer", required = true, paramType = ApiConstants.PARAM_TYPE_QUERY),
            @ApiImplicitParam(name = "rows", value = "每页显示记录数（如：12）", defaultValue = "12", dataType = "Integer", required = true, paramType = ApiConstants.PARAM_TYPE_QUERY)
    })
    @ResponseBody
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelMap findByCondition(CrawlersRoleModel crawlersRoleModel) {
        if(crawlersRoleModel.getRows() == null || crawlersRoleModel.getRows() == 0) crawlersRoleModel.setRows(12);
        if (crawlersRoleModel.getOrderBy() == null) crawlersRoleModel.setOrderBy("create_time");
        ModelMap map = new ModelMap();

        try {
            List<CrawlersRoleModel> list = crawlersRoleService.findRoleByCondition(crawlersRoleModel);
            map.put("pageInfo", new PageInfo<>(list));
            map.put("queryParam", crawlersRoleModel);
            map.put("page", crawlersRoleModel.getPage());
            map.put("rows", crawlersRoleModel.getRows());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    @ApiOperation(value="爬虫规则审核数据接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "爬虫规则id", dataType = "Integer", required = true, paramType = ApiConstants.PARAM_TYPE_QUERY),
            @ApiImplicitParam(name = "status", value = "审核状态，1审核成功 2审核失败", dataType = "Integer", required = true, paramType = ApiConstants.PARAM_TYPE_QUERY),
            @ApiImplicitParam(name = "reason", value = "审核失败原因", dataType = "String", paramType = ApiConstants.PARAM_TYPE_QUERY)
    })
    @RequestMapping(value = "/auditCrawlersRole", method = RequestMethod.POST)
    public ModelMap update(Integer id,Integer status,String reason) throws GlobalException {

        ModelMap map = new ModelMap();
        if (id != null){
            try {
                SecurityUser user = getPrincipal();
                if (user == null) throw new GlobalAdminException("授权过期，请重新登陆");
                CrawlersRoleModel crawlersRoleModel = new CrawlersRoleModel();
                crawlersRoleModel.setId(id);
                crawlersRoleModel.setAuditor(user.getUserId()); // 系统用户ID
                crawlersRoleModel.setAuditStatus(status);
                crawlersRoleModel.setAuditFailReason(reason);
                Integer count = crawlersRoleService.auditRole(crawlersRoleModel);
                map.put("success", count > 0 ? true : false);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else {
            map.put("success", false);
        }

        return map;
    }

    @ApiOperation(value="根据id查询单条爬虫规则数据接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "爬虫规则id", dataType = "Integer", required = true, paramType = ApiConstants.PARAM_TYPE_QUERY)
    })
    @RequestMapping(value = "/findCrawlersRoleById", method = RequestMethod.GET)
    public ModelMap findById(Integer id) {

        ModelMap map = new ModelMap();
        if (id != null) {
            try {
                CrawlersRoleModel roleModel = roleMapper.findRoleById(id);
                map.put("pageInfo", roleModel);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return map;
    }

}
