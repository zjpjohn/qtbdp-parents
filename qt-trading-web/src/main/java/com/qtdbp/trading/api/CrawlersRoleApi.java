package com.qtdbp.trading.api;

import com.github.pagehelper.PageInfo;
import com.qtdbp.trading.constants.ApiConstants;
import com.qtdbp.trading.exception.GlobalException;
import com.qtdbp.trading.mapper.CrawlersRoleMapper;
import com.qtdbp.trading.model.CrawlersRoleModel;
import com.qtdbp.trading.service.CrawlersRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by dell on 2017/6/6.
 */
@Api(description = "爬虫市场接口 - 业务API接口")
@RestController
@RequestMapping(value = "/api/crawlers/role")
public class CrawlersRoleApi {

    @Autowired
    private CrawlersRoleService crawlersRoleService;

    @ApiOperation(value="添加爬虫规则数据接口")
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ModelMap insert(@RequestBody CrawlersRoleModel crawlersRoleModel) throws GlobalException {
        if (crawlersRoleModel == null) throw new GlobalException("数据不存在，请重新输入");

        ModelMap map = new ModelMap();

        try {
            Integer count = crawlersRoleService.insertRole(crawlersRoleModel);
            map.put("success", count > 0 ? true : false);
            map.put("id", crawlersRoleModel.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return map;
    }

    @ApiOperation(value = "爬虫市场数据接口，分页获取")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "typeId", value = "类目类型id（如：1）", dataType = "Integer", paramType = ApiConstants.PARAM_TYPE_QUERY),
            @ApiImplicitParam(name = "webType", value = "网站类型id（如：1）", dataType = "Integer", paramType = ApiConstants.PARAM_TYPE_QUERY),
            @ApiImplicitParam(name = "orderBy", value = "排序字段", required = true, dataType = "String", paramType = ApiConstants.PARAM_TYPE_QUERY)
    })
    @ResponseBody
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelMap findByCondition(CrawlersRoleModel crawlersRoleModel) {
        if(crawlersRoleModel.getRows() == null || crawlersRoleModel.getRows() == 0) crawlersRoleModel.setRows(12);
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


}
