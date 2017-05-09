package com.qtdbp.tradingadmin.mapper;

import com.qtdbp.tradingadmin.service.security.model.SysUser;
import org.apache.ibatis.annotations.Param;

/**
 * 系统用户数据库操作
 *
 * Created by dell on 2017/4/11.
 */
public interface SysUserMapper {

    /**
     * 查询系统用户
     * @param ssoId
     * @return
     */
    SysUser findUserBySsoId(@Param("ssoId") String ssoId) ;
}
