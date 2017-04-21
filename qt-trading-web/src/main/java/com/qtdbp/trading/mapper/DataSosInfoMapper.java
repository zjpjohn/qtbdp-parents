package com.qtdbp.trading.mapper;

import com.qtdbp.trading.model.DataSosInfoModel;

import java.util.List;

/**
 * 方案召集数据库CURD操作
 *
 * @author: caidchen
 * @create: 2017-04-19 17:27
 * To change this template use File | Settings | File Templates.
 */
public interface DataSosInfoMapper {

    /**
     * 分页查询方案召集
     * @param sosInfoModel
     * @return
     */
    List<DataSosInfoModel> findDataSosInfoByCondition(DataSosInfoModel sosInfoModel) ;

    /**
     * 添加方案召集
     * @param sosInfoModel
     * @return 记录数，成功返回1
     */
    Integer insertDataSosInfo(DataSosInfoModel sosInfoModel) ;
}
