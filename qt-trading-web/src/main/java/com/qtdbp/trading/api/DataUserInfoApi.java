package com.qtdbp.trading.api;

import com.qtdbp.trading.exception.GlobalException;
import com.qtdbp.trading.model.DataUserInfoModel;
import com.qtdbp.trading.service.DataUserInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

/**
 * 个人用户API接口
 *
 * @author: caidchen
 * @create: 2017-04-21 16:30
 * To change this template use File | Settings | File Templates.
 */
@Api(description = "用户接口 - 业务API接口")
@RestController
@RequestMapping(value = "/api/user")
public class DataUserInfoApi {

    @Autowired
    private DataUserInfoService userInfoService ;

    //===================================================================
    // 用户API接口
    //===================================================================

    @ApiOperation(value="查询用户基本信息")
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public ModelMap loadUserData(
            @ApiParam(name = "id", value = "用户ID", required = true) @PathVariable Integer id) throws GlobalException {

        ModelMap map = new ModelMap() ;
        try {
            DataUserInfoModel user = userInfoService.findDataUserInfoById(id);
            map.put("pageInfo", user);
        } catch (Exception e) {
            throw new GlobalException(e.getMessage()) ;
        }

        return map ;
    }

    @ApiOperation(value="修改用户基本信息")
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ModelMap modifyDataNewOrders(DataUserInfoModel user) throws GlobalException {

        ModelMap map = new ModelMap() ;
        try {
            boolean result = userInfoService.updateDataUserInfo(user);
            map.put("success", result);
        } catch (Exception e) {
            throw new GlobalException(e.getMessage()) ;
        }

        return map ;
    }

}
