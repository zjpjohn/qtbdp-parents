package com.qtdbp.trading.mapper;

import com.qtdbp.trading.model.DataUserInfoModel;

/**
 * 个人用户CURD操作
 *
 * @author: caidchen
 * @create: 2017-04-19 17:27
 * To change this template use File | Settings | File Templates.
 */
public interface DataUserInfoMapper {

    /**
     * 通过用户ID查询用户基本信息
     * @param id 用户ID
     * @return
     */
    DataUserInfoModel findDataUserInfoById(int id) ;

    /**
     * 修改用户基本信息
     * @param userInfoModel
     * @return
     */
    Integer updateDataUserInfo(DataUserInfoModel userInfoModel) ;

    /**
     * 添加用户
     * @param userInfoModel
     * @return
     */
    Integer insertDataUserInfo(DataUserInfoModel userInfoModel) ;

}
