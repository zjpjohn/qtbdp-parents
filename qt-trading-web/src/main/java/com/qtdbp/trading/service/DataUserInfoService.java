package com.qtdbp.trading.service;

import com.qtdbp.trading.exception.GlobalException;
import com.qtdbp.trading.mapper.DataUserInfoMapper;
import com.qtdbp.trading.mapper.SysUserMapper;
import com.qtdbp.trading.model.DataUserInfoModel;
import com.qtdbp.trading.service.security.model.SysUser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Autowired
    private SysUserMapper sysUserMapper ;

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


    /**
     * 添加新用户
     * @param userInfoModel
     * @return
     * @throws GlobalException
     */
    @Transactional
    public int insertDataUserInfo(DataUserInfoModel userInfoModel) throws GlobalException {

        if(userInfoModel == null) throw new GlobalException("用户信息数据为空") ;

        SysUser sysUser = sysUserMapper.findUserBySsoId(userInfoModel.getSsoUserId()) ;

        if(sysUser != null) throw new GlobalException("用户已存在无需添加") ;

        // 系统用户在交易平台中没有此用户，则插入新纪录
        Integer count = userInfoMapper.insertDataUserInfo(userInfoModel);

        int id  = -1 ;
        if(count != null && count > 0) id = userInfoModel.getId() ;

        return id ;
    }
}
