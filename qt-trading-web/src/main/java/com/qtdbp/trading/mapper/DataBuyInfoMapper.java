package com.qtdbp.trading.mapper;

import com.qtdbp.trading.model.DataBuyInfoModel;

import java.util.List;

/**
 * 数据众包数据库CRUD操作
 *
 * @author: caidchen
 * @create: 2017-04-20 10:16
 * To change this template use File | Settings | File Templates.
 */
public interface DataBuyInfoMapper {

    /**
     * 分页查询数据众包
     * @param buyInfoModel
     * @return
     */
    List<DataBuyInfoModel> findDataBuyInfoByCondition(DataBuyInfoModel buyInfoModel) ;
}
