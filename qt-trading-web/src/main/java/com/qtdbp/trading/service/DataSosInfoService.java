package com.qtdbp.trading.service;

import com.github.pagehelper.PageHelper;
import com.qtdbp.trading.mapper.DataSosInfoMapper;
import com.qtdbp.trading.model.DataSosInfoModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 方案召集服务
 *
 * @author: caidchen
 * @create: 2017-04-19 13:24
 * To change this template use File | Settings | File Templates.
 */
@Service
public class DataSosInfoService {

    @Autowired
    private DataSosInfoMapper sosInfoMapper ;


    /**
     * 分页获取方案召集
     * @param sosInfo
     * @return
     */
    public List<DataSosInfoModel> findDataSosInfoByCondition(DataSosInfoModel sosInfo) {
        if (sosInfo.getPage() != null && sosInfo.getRows() != null) {
            PageHelper.startPage(sosInfo.getPage(), sosInfo.getRows());
        }

        return sosInfoMapper.findDataSosInfoByCondition(sosInfo);
    }

}
