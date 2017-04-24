package com.qtdbp.trading.mapper;

import com.qtdbp.trading.model.DataBuyInfoModel;

import java.util.List;
import java.util.Map;

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

    /**
     * 添加数据众包
     * @param buyInfoModel
     * @return 记录数，成功返回1
     */
    Integer insertDataBuyInfo(DataBuyInfoModel buyInfoModel) ;

    /**
     * 获取个人中心我的发布信息
     * @param buyInfoModel
     * @return
     */
    Map<String,Integer> findBuyInfoAndSosInfo(DataBuyInfoModel buyInfoModel);
}
