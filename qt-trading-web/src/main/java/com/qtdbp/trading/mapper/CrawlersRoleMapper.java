package com.qtdbp.trading.mapper;

import com.qtdbp.trading.model.CrawlersRoleModel;
import com.qtdbp.trading.utils.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by dell on 2017/6/6.
 */
public interface CrawlersRoleMapper extends BaseMapper<CrawlersRoleModel> {

    /**
     * 新增爬虫规则数据
     * @param crawlersRoleModel
     * @return
     */
    Integer insertRole(CrawlersRoleModel crawlersRoleModel);

    /**
     * 根据条件分页查询爬虫规则数据
     * @param crawlersRoleModel
     * @return
     */
    List<CrawlersRoleModel> findRulesByCondition(CrawlersRoleModel crawlersRoleModel);

    /**
     * 更新爬虫规则
     * @param crawlersRoleModel
     * @return
     */
    Integer updateRule(CrawlersRoleModel crawlersRoleModel);

    /**
     * 根据id查询单条爬虫规则
     * @param id
     * @return
     */
    CrawlersRoleModel findRuleById(@Param("id") Integer id);

}
