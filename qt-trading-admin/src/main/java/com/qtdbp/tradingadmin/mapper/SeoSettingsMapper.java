package com.qtdbp.tradingadmin.mapper;

import com.qtdbp.trading.model.SeoSettingsModel;
import com.qtdbp.trading.model.SysResourcesModel;
import com.qtdbp.trading.model.SysResourcesSeoRelationModel;
import com.qtdbp.tradingadmin.utils.BaseMapper;

import java.util.List;

/**
 * Created by dell on 2017/6/21.
 */
public interface SeoSettingsMapper extends BaseMapper<SeoSettingsModel> {

    /**
     * 插入SEO数据
     * @param seoModel
     * @return
     */
    Integer insertSeo(SeoSettingsModel seoModel);

    /**
     * 插入SEO和资源关联表数据
     * @param relationModel
     * @return
     */
    Integer insertResourcesSeoRelation(SysResourcesSeoRelationModel relationModel);

    /**
     * 分页查询seo数据
     * @param resourcesModel
     * @return
     */
    List<SysResourcesModel> findResourcesByCondition(SysResourcesModel resourcesModel);

    /**
     * 更新seo数据
     * @param seoSettingsModel
     * @return
     */
    Integer updateSeo(SeoSettingsModel seoSettingsModel);

    /**
     * 根据resourcesID查询单条seo信息
     * @param resourcesId
     * @return
     */
    SeoSettingsModel findSeoDetilByResourcesId(String resourcesId);
}
