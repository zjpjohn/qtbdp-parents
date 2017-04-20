package com.qtdbp.trading.mapper;

import com.qtdbp.trading.model.DataInstitutionInfoModel;
import com.qtdbp.trading.utils.BaseMapper;

import java.util.List;

/**
 * Created by liyang on 2017/4/19.
 */
public interface DataInstitutionInfoMapper extends BaseMapper<DataInstitutionInfoModel> {

    //分页查询服务商信息和类型
    List<DataInstitutionInfoModel> selectInstitutionAndType(DataInstitutionInfoModel dm);
}
