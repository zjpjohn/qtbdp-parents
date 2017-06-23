package com.qtdbp.trading.api;

import com.qtdbp.trading.exception.GlobalException;
import com.qtdbp.trading.mapper.SeoSettingsMapper;
import com.qtdbp.trading.model.SeoSettingsModel;
import com.qtdbp.trading.utils.Message;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by dell on 2017/6/23.
 */
@Api(description = "SEO优化 - 业务API接口")
@RestController
@RequestMapping(value = "/api/seo/settings")
public class SeoSettingApi {

    Logger logger = LoggerFactory.getLogger(this.getClass()) ;


    @Autowired
    private SeoSettingsMapper seoSettingsMapper;

    @ApiOperation(value="根据导航地址查询seo数据接口")
    @RequestMapping(value = "/findSeoByPath", method = RequestMethod.GET)
    public ModelMap findSeoByPath(
            @ApiParam(name = "resourcesPath", value = "导航页面地址", required = true) @PathVariable String resourcesPath) throws GlobalException {

        ModelMap map = new ModelMap();
        if (resourcesPath != null) {
            try {
                SeoSettingsModel seoSettingsModel = seoSettingsMapper.findSeoByResourcePath(resourcesPath);
                map.put("pageInfo", seoSettingsModel);
            } catch (Exception e) {
                logger.error("findSeoDetilByResourcesId has error ,message:" + e.getMessage());
                throw new GlobalException(e.getMessage());
            }
        } else {
            throw new GlobalException("导航页面地址为空");
        }

        return map;
    }
}
