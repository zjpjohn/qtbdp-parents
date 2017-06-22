package com.qtdbp.tradingadmin.service;

import com.github.pagehelper.PageHelper;
import com.qtdbp.trading.model.SeoSettingsModel;
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
            relationModel.setSeoId(id);
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
     * 分页查询seo数据
     * @param seoModel
     * @return
     */
    public List<SeoSettingsModel> findSeoByCondition(SeoSettingsModel seoModel) {

        if (seoModel.getPage() != null && seoModel.getRows() != null) {
            PageHelper.startPage(seoModel.getPage(), seoModel.getRows());
        }
        List<SeoSettingsModel> list = seoMapper.findSeoByCondition(seoModel);
        return list;
    }

    public Integer updateSeo(SeoSettingsModel seoSettingsModel) throws GlobalAdminException {
        if (seoSettingsModel.getId() != null && seoSettingsModel.getId() != 0){
            Integer count = seoMapper.updateSeo(seoSettingsModel);
            return count;
        }
        throw new GlobalAdminException("seo数据ID为空，请重新操作");
    }
}
