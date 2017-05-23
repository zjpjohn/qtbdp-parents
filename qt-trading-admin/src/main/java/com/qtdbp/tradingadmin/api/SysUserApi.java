package com.qtdbp.tradingadmin.api;

import com.qtdbp.trading.constants.ApiConstants;
import com.qtdbp.tradingadmin.base.security.entity.SysUsers;
import com.qtdbp.tradingadmin.base.security.service.SecurityUserService;
import com.qtdbp.tradingadmin.base.security.utils.PageUtils;
import com.qtdbp.tradingadmin.exception.GlobalAdminException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户管理API
 *
 * @author: caidchen
 * @create: 2017-05-21 17:24
 * To change this template use File | Settings | File Templates.
 */
@Api(description = "系统管理 - 用户管理业务API接口")
@RestController
@RequestMapping(value = "/api/users")
public class SysUserApi {

    @Autowired
    private SecurityUserService userService ;

    @ApiOperation(value="用户管理数据接口，分页获取")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "用户名", dataType = "String", paramType = ApiConstants.PARAM_TYPE_QUERY),
            @ApiImplicitParam(name = "page", value = "当前页（如：1）", defaultValue = "1", dataType = "Integer", required = true, paramType = ApiConstants.PARAM_TYPE_QUERY),
            @ApiImplicitParam(name = "rows", value = "每页显示记录数（如：10）", defaultValue = "10", dataType = "Integer", required = true, paramType = ApiConstants.PARAM_TYPE_QUERY)
    })
    @ResponseBody
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelMap loadUsers(Integer page, Integer rows) throws GlobalAdminException {

        ModelMap map = new ModelMap() ;
        Page<SysUsers> users = userService.findUsersForPage(page, rows);
        map.put("pageInfo", PageUtils.getPageMap(users));

        return map ;
    }


}
