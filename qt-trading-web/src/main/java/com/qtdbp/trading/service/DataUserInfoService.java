package com.qtdbp.trading.service;

import com.qtdbp.trading.exception.GlobalException;
import com.qtdbp.trading.mapper.DataUserInfoMapper;
import com.qtdbp.trading.model.DataUserInfoModel;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * 个人用户服务
 *
 * @author: caidchen
 * @create: 2017-04-21 16:34
 * To change this template use File | Settings | File Templates.
 */
@Service
public class DataUserInfoService {

    @Autowired
    private DataUserInfoMapper userInfoMapper ;

    /**
     * 查询用户基本信息
     * @param id 用户ID
     * @return
     */
    public DataUserInfoModel findDataUserInfoById(Integer id) throws GlobalException {

        if(id == null) throw new GlobalException("用户ID不存在") ;

        return userInfoMapper.findDataUserInfoById(id);
    }

    /**
     * 修改用户基本信息
     * @param userInfoModel
     * @return true成功，否则失败
     * @throws GlobalException
     */
    public boolean updateDataUserInfo(DataUserInfoModel userInfoModel) throws GlobalException {

        if(userInfoModel == null) throw new GlobalException("用户信息数据为空") ;

        try {
            String head = StringUtils.isEmpty(userInfoModel.getHead()) ? null : URLDecoder.decode(userInfoModel.getHead(), "UTF-8") ;
            userInfoModel.setHead(head);
        } catch (UnsupportedEncodingException e) {
            throw new GlobalException("头像解析出错，请重新操作") ;
        }
        Integer count = userInfoMapper.updateDataUserInfo(userInfoModel) ;

        if(count != null && count > 0) return true ;

        return false ;
    }
}
