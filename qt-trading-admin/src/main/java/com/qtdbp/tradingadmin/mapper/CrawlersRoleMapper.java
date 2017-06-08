package com.qtdbp.tradingadmin.mapper;

import com.qtdbp.trading.model.CrawlersRoleModel;
import com.qtdbp.trading.utils.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by dell on 2017/6/6.
 */
public interface CrawlersRoleMapper extends BaseMapper<CrawlersRoleModel> {

    /**
     * 根据条件分页查询爬虫规则数据
     * @param crawlersRoleModel
     * @return
     */
    List<CrawlersRoleModel> findRulesByCondition(CrawlersRoleModel crawlersRoleModel);

    /**
     * 审核爬虫规则
     * @param crawlersRoleModel
     * @return
     */
    Integer updateRule(CrawlersRoleModel crawlersRoleModel);

    /**
     * 根据ID查询单条爬虫规则数据
     * @param id
     * @return
     */
    CrawlersRoleModel findRoleById(@Param("id") Integer id);

}
