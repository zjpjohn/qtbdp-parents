package com.qtdbp.tradingadmin.base.security.repository;

import com.qtdbp.tradingadmin.base.security.entity.SysResources;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 系统资源数据操作集
 *
 * @author: caidchen
 * @create: 2017-05-17 16:42
 * To change this template use File | Settings | File Templates.
 */
public interface SysResourcesRepository extends JpaRepository<SysResources, String> {
}
