package com.qtdbp.tradingadmin.base.security.repository;

import com.qtdbp.tradingadmin.base.security.entity.SysRolesAuthorities;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 系统角色权限关系数据库操作集
 *
 * @author: caidchen
 * @create: 2017-05-17 15:36
 * To change this template use File | Settings | File Templates.
 */
public interface SysRolesAuthoritiesRepository extends JpaRepository<SysRolesAuthorities, String>{
}
