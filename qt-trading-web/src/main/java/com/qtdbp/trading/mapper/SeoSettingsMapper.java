package com.qtdbp.trading.mapper;

import com.qtdbp.trading.model.SeoSettingsModel;

/**
 * Created by dell on 2017/6/23.
 */
public interface SeoSettingsMapper {

    /**
     * 根据当前网页地址获取seo优化信息
     * @param path
     * @return
     */
    SeoSettingsModel findSeoByResourcePath(String path);
}
