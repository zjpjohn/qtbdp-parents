package com.qtdbp.tradingadmin.service;

import com.github.pagehelper.PageHelper;
import com.qtdbp.trading.constants.AuditStatusConstants;
import com.qtdbp.trading.exception.GlobalException;
import com.qtdbp.trading.model.CrawlersRoleModel;
import com.qtdbp.tradingadmin.mapper.CrawlersRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by dell on 2017/6/6.
 */
@Service
public class CrawlersRoleService {

    @Autowired
    private CrawlersRoleMapper crawlersRoleMapper;

    /**
     * 根据条件分页查询爬虫市场数据
     * @param crawlersRoleModel
     * @return
     */
    public List<CrawlersRoleModel> findRoleByCondition(CrawlersRoleModel crawlersRoleModel) {
        if (crawlersRoleModel.getPage() != null && crawlersRoleModel.getRows() != null) {
            PageHelper.startPage(crawlersRoleModel.getPage(), crawlersRoleModel.getRows());
        }
        return crawlersRoleMapper.findRulesByCondition(crawlersRoleModel);
    }

    /**
     * 审核爬虫规则
     * @param crawlersRoleModel
     * @return
     * @throws GlobalException
     */
    public Integer auditRole(CrawlersRoleModel crawlersRoleModel) throws GlobalException {
        if (crawlersRoleModel.getId() == null || crawlersRoleModel.getId() == 0) throw new GlobalException("id为空，请重新操作");
        Integer count = 0;
        //审核通过的时候
        if (crawlersRoleModel.getAuditStatus() == AuditStatusConstants.AUDIT_STATUS_PASS) {
            count = crawlersRoleMapper.updateRule(crawlersRoleModel);
        //审核不通过的时候
        } else if (crawlersRoleModel.getAuditStatus() == AuditStatusConstants.AUDIT_STATUS_NO_PASS) {
            if (crawlersRoleModel.getAuditFailReason() == null) throw new GlobalException("未填写不通过原因，请重新审核");
            count = crawlersRoleMapper.updateRule(crawlersRoleModel);
        }
        return count > 0 ? count : -1;
    }

}
