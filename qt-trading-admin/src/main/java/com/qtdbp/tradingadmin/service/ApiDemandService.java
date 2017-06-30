package com.qtdbp.tradingadmin.service;

import com.github.pagehelper.PageHelper;
import com.qtdbp.trading.model.ApiDemandModel;
import com.qtdbp.tradingadmin.mapper.ApiDemandMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by dell on 2017/6/22.
 */
@Service
public class ApiDemandService {

    @Autowired
    private ApiDemandMapper apiDemandMapper;

    /**
     * 分页查询api定制
     * @param apiDemandModel
     * @return
     */
    public List<ApiDemandModel> findApiDemand(ApiDemandModel apiDemandModel) {

        if (apiDemandModel.getPage() != null && apiDemandModel.getRows() != null) {
            PageHelper.startPage(apiDemandModel.getPage(), apiDemandModel.getRows());
        }
        return apiDemandMapper.findApiDemandByCondition(apiDemandModel);
    }

}
