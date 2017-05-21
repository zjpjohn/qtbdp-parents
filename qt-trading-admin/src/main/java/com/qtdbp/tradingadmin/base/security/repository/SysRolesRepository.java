package com.qtdbp.tradingadmin.base.security.repository;

import com.qtdbp.tradingadmin.base.security.entity.SysRoles;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 系统角色数据操作集
 *
 * @author: caidchen
 * @create: 2017-05-17 14:49
 * To change this template use File | Settings | File Templates.
 */
public interface SysRolesRepository  extends JpaRepository<SysRoles, String> {
}
