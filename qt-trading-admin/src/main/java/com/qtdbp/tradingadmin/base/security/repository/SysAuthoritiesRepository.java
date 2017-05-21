package com.qtdbp.tradingadmin.base.security.repository;

import com.qtdbp.tradingadmin.base.security.entity.SysAuthorities;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 系统权限数据操作集
 *
 * @author: caidchen
 * @create: 2017-05-17 15:31
 * To change this template use File | Settings | File Templates.
 */
public interface SysAuthoritiesRepository extends JpaRepository<SysAuthorities, String> {
}
