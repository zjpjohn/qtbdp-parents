package com.qtdbp.tradingadmin.mapper;

import com.qtdbp.trading.model.CrawlersRoleModel;
import com.qtdbp.trading.utils.BaseMapper;

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

}
