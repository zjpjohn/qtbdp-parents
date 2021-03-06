package com.qtdbp.trading.service;

import com.github.pagehelper.PageHelper;
import com.qtdbp.trading.exception.GlobalException;
import com.qtdbp.trading.mapper.CrawlersRoleMapper;
import com.qtdbp.trading.model.CrawlersRoleModel;
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
     * 新增爬虫规则
     * @param crawlersRoleModel
     * @return
     */
    public Integer insertRole(CrawlersRoleModel crawlersRoleModel) {
        String filePath = crawlersRoleModel.getFilePath();
        String roleType = filePath.substring(filePath.lastIndexOf(".") + 1);
        crawlersRoleModel.setRoleType(roleType);
        Integer count = crawlersRoleMapper.insertRole(crawlersRoleModel);

        return count > 0 ? count : -1;
    }

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
     * 更新爬虫规则
     * @param crawlersRoleModel
     * @return
     */
    public Integer updateRoles(CrawlersRoleModel crawlersRoleModel) throws GlobalException {

        if (crawlersRoleModel.getId() == null || crawlersRoleModel.getId() == 0) throw new GlobalException("数据id为空，请重新操作");
        Integer count = crawlersRoleMapper.updateRule(crawlersRoleModel);

        return count > 0 ? count : -1;
    }

    /**
     * 根据id查询单条爬虫规则数据
     * @param id
     * @return
     */
    public CrawlersRoleModel findRuleById(Integer id) {

        return crawlersRoleMapper.findRuleById(id);
    }

    /**
     * 更改数据包产品上下架
     * @param id
     * @return
     * @throws GlobalException
     */
    public Integer updateState(Integer id) throws GlobalException {
        CrawlersRoleModel roleModel = crawlersRoleMapper.findRuleById(id);
        if (roleModel == null) throw new GlobalException("该数据包已不存在") ;
        Integer count = 0;
        if (roleModel.getIsUsed() == 1){ // 上架时改为下架
            count = crawlersRoleMapper.updateSoldOut(id) ;
        } else{                             //  下架时改为上架
            count = crawlersRoleMapper.updatePutaway(id);
        }

        return count;
    }
}
