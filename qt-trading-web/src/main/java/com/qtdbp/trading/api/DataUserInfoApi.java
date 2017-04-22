package com.qtdbp.trading.api;

import com.qtdbp.trading.constants.ApiConstants;
import com.qtdbp.trading.exception.GlobalException;
import com.qtdbp.trading.model.DataUserInfoModel;
import com.qtdbp.trading.service.DataUserInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
    @ApiImplicitParam(name = "id", value = "用户ID", dataType = "Integer", required = true, paramType = ApiConstants.PARAM_TYPE_QUERY)
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelMap loadDataNewOrders(Integer id) throws GlobalException {

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
    @RequestMapping(value = "", method = RequestMethod.PUT)
    public ModelMap modifyDataNewOrders(@RequestBody DataUserInfoModel user) throws GlobalException {

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
