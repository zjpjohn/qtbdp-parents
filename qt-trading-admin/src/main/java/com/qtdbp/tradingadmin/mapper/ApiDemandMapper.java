package com.qtdbp.tradingadmin.mapper;

import com.qtdbp.trading.model.ApiDemandModel;
import com.qtdbp.trading.utils.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by dell on 2017/6/22.
 */
public interface ApiDemandMapper extends BaseMapper<ApiDemandModel> {

    /**
     * 根据条件分页查询api定制
     * @param apiDemandModel
     * @return
     */
    List<ApiDemandModel> findApiDemandByCondition(ApiDemandModel apiDemandModel);

    /**
     * 查询单条api定制详情
     * @param id
     * @return
     */
    ApiDemandModel findApiDemandById(@Param("id") Integer id);
}
