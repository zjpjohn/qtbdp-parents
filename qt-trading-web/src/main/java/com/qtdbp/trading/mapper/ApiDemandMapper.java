package com.qtdbp.trading.mapper;

import com.qtdbp.trading.model.ApiDemandModel;
import com.qtdbp.trading.utils.BaseMapper;

/**
 * Created by dell on 2017/6/22.
 */
public interface ApiDemandMapper extends BaseMapper<ApiDemandModel> {


    /**
     * 新增api定制数据
     * @param apiDemandModel
     * @return
     */
    Integer insertApiDemand(ApiDemandModel apiDemandModel);
}
