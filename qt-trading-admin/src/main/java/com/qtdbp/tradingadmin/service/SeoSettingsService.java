package com.qtdbp.tradingadmin.service;

import com.github.pagehelper.PageHelper;
import com.qtdbp.trading.model.SeoSettingsModel;
import com.qtdbp.trading.model.SysResourcesModel;
import com.qtdbp.trading.model.SysResourcesSeoRelationModel;
import com.qtdbp.tradingadmin.exception.GlobalAdminException;
import com.qtdbp.tradingadmin.mapper.SeoSettingsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by dell on 2017/6/22.
 */
@Service
public class SeoSettingsService {


    @Autowired
    private SeoSettingsMapper seoMapper;

    /**
     * 新增Seo数据和关联表数据
     * @param seoModel
     * @return
     * @throws GlobalAdminException
     */
    @Transactional
    public Integer insertSeo(SeoSettingsModel seoModel) throws GlobalAdminException {

        if (seoModel == null) throw new GlobalAdminException("Seo数据为空");

        Integer id = null;
        id = seoMapper.insertSeo(seoModel);
        if (id != null && id > 0) {
            SysResourcesSeoRelationModel relationModel = new SysResourcesSeoRelationModel();
            relationModel.setSeoId(seoModel.getId());
            relationModel.setSysResourcesId(seoModel.getResourcesId());
            Integer relationId = seoMapper.insertResourcesSeoRelation(relationModel);
            if (relationId > 0){
                return id;
            }
            return -1;
        }
        return -1;
    }

    /**
     * 分页查询导航资源数据
     * @param resourcesModel
     * @return
     */
    public List<SysResourcesModel> findSeoByCondition(SysResourcesModel resourcesModel) {

        if (resourcesModel.getPage() != null && resourcesModel.getRows() != null) {
            PageHelper.startPage(resourcesModel.getPage(), resourcesModel.getRows());
        }
        List<SysResourcesModel> list = seoMapper.findResourcesByCondition(resourcesModel);
        return list;
    }

    /**
     * 更新seo数据
     * @param seoSettingsModel
     * @return
     * @throws GlobalAdminException
     */
    public Integer updateSeo(SeoSettingsModel seoSettingsModel) throws GlobalAdminException {
        if (seoSettingsModel.getId() != null && seoSettingsModel.getId() != 0){
            Integer count = seoMapper.updateSeo(seoSettingsModel);
            return count;
        }
        throw new GlobalAdminException("seo数据ID为空，请重新操作");
    }
}
