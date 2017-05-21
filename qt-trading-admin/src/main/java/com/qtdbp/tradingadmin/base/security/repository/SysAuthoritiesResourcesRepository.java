package com.qtdbp.tradingadmin.base.security.repository;

import com.qtdbp.tradingadmin.base.security.entity.SysAuthoritiesResources;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 系统权限资源关系数据操作集
 *
 * @author: caidchen
 * @create: 2017-05-17 17:03
 * To change this template use File | Settings | File Templates.
 */
public interface SysAuthoritiesResourcesRepository extends JpaRepository<SysAuthoritiesResources, String> {
}
