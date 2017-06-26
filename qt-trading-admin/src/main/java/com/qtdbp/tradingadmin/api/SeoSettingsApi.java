package com.qtdbp.tradingadmin.api;

import com.github.pagehelper.PageInfo;
import com.qtdbp.trading.constants.ApiConstants;
import com.qtdbp.trading.exception.ErrorCode;
import com.qtdbp.trading.model.SeoSettingsModel;
import com.qtdbp.trading.model.SysResourcesModel;
import com.qtdbp.trading.utils.Message;
import com.qtdbp.tradingadmin.exception.GlobalAdminException;
import com.qtdbp.tradingadmin.mapper.SeoSettingsMapper;
import com.qtdbp.tradingadmin.service.SeoSettingsService;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by dell on 2017/6/22.
 */
@Api(description = "SEO优化 - 业务API接口")
@RestController
@RequestMapping(value = "/api/seo/settings")
public class SeoSettingsApi {

    Logger logger = LoggerFactory.getLogger(this.getClass()) ;

    @Autowired
    private SeoSettingsService seoSettingsService;

    @Autowired
    private SeoSettingsMapper seoSettingsMapper;

    @ApiOperation(value="添加SEO优化数据接口")
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ModelMap insertSeo(@RequestBody SeoSettingsModel seoModel) throws GlobalAdminException {

        ModelMap map = new ModelMap();
        Message message = new Message() ;
        Integer id = -1 ;
        if(seoModel != null) {
            try {
                id = seoSettingsService.insertSeo(seoModel);
                if(id != null && id > 0) {
                    message.setSuccess(true);
                    message.setData(id);
                }
            } catch (Exception e) {
                logger.error("insertSeo has error ,message:" + e.getMessage());
                throw new GlobalAdminException(e.getMessage());
            }
        } else {
            message.setSuccess(false);
            message.setErrorCode(ErrorCode.ERROR_LOGIN_NO);
            message.setMessage("SEO优化数据不存在，请重新输入");
        }
        map.put("result", message);

        return map;
    }


    @ApiOperation(value="分页查询导航数据接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "resourceName", value = "seo网页标题", dataType = "String", paramType = ApiConstants.PARAM_TYPE_QUERY),
            @ApiImplicitParam(name = "moduleId", value = "模块ID", dataType = "String", required = true, paramType = ApiConstants.PARAM_TYPE_QUERY),
            @ApiImplicitParam(name = "page", value = "当前页（如：1）", defaultValue = "1", dataType = "Integer", required = true, paramType = ApiConstants.PARAM_TYPE_QUERY),
            @ApiImplicitParam(name = "rows", value = "每页显示记录数（如：10）", defaultValue = "10", dataType = "Integer", required = true, paramType = ApiConstants.PARAM_TYPE_QUERY)
    })
    @RequestMapping(value = "/resources", method = RequestMethod.GET)
    public ModelMap findSeoByCondition(SysResourcesModel resourcesModel) throws GlobalAdminException {

        ModelMap map = new ModelMap();
        if(resourcesModel.getRows() == null || resourcesModel.getRows() == 0) resourcesModel.setRows(10);
        try {
            List<SysResourcesModel> list = seoSettingsService.findSeoByCondition(resourcesModel);
            map.put("pageInfo", new PageInfo<>(list));
            map.put("page", resourcesModel.getPage());
            map.put("rows", resourcesModel.getRows());
        } catch (Exception e) {
            logger.error("findResources has error ,message:" + e.getMessage());
            throw new GlobalAdminException(e.getMessage());
        }

        return map;
    }

    @ApiOperation(value = "更新SEO优化数据接口")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ModelMap updateSeo(@RequestBody SeoSettingsModel seoModel) throws GlobalAdminException {
        ModelMap map = new ModelMap();
        Message message = new Message() ;
        Integer id = -1 ;
        if(seoModel != null) {
            try {
                id = seoSettingsService.updateSeo(seoModel);
                if(id != null && id > 0) {
                    message.setSuccess(true);
                    message.setData(id);
                }
            } catch (Exception e) {
                logger.error("updateSeo has error ,message:" + e.getMessage());
                throw new GlobalAdminException(e.getMessage());
            }
        } else {
            message.setSuccess(false);
            message.setErrorCode(ErrorCode.ERROR_LOGIN_NO);
            message.setMessage("SEO优化数据不存在，请重新输入");
        }
        map.put("result", message);

        return map;
    }

    @ApiOperation(value="根据导航id查询seo数据接口")
    @RequestMapping(value = "/{resourcesId}", method = RequestMethod.GET)
    public ModelMap findSeoDetilByResourcesId(
            @ApiParam(name = "resourcesId", value = "导航Id", required = true) @PathVariable String resourcesId) throws GlobalAdminException {

        ModelMap map = new ModelMap();
        try {
            SeoSettingsModel seoSettingsModel = seoSettingsMapper.findSeoDetilByResourcesId(resourcesId);
            map.put("pageInfo", seoSettingsModel);
        } catch (Exception e) {
            logger.error("findSeoDetilByResourcesId has error ,message:" + e.getMessage());
            throw new GlobalAdminException(e.getMessage());
        }

        return map;
    }

}
