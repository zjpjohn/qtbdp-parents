package com.qtdbp.trading.service;

import com.github.pagehelper.PageHelper;
import com.qtdbp.trading.mapper.DataBuyInfoMapper;
import com.qtdbp.trading.model.DataBuyInfoModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 数据众包服务
 *
 * @author: caidchen
 * @create: 2017-04-20 10:17
 * To change this template use File | Settings | File Templates.
 */
@Service
public class DataBuyInfoService {

    @Autowired
    private DataBuyInfoMapper buyInfoMapper ;

    /**
     * 分页获取方案召集
     * @param buyInfoModel
     * @return
     */
    public List<DataBuyInfoModel> findDataBuyInfoByCondition(DataBuyInfoModel buyInfoModel) {
        if (buyInfoModel.getPage() != null && buyInfoModel.getRows() != null) {
            PageHelper.startPage(buyInfoModel.getPage(), buyInfoModel.getRows());
        }

        return buyInfoMapper.findDataBuyInfoByCondition(buyInfoModel);
    }
}
