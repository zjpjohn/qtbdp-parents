package com.qtdbp.trading.mapper;

import com.qtdbp.trading.model.DataInstitutionInfoModel;
import com.qtdbp.trading.model.DataInstitutionTypeRelationModel;

import java.util.List;

/**
 * 数据服务商数据库CRUD操作
 *
 * Created by liyang on 2017/4/19.
 */
public interface DataInstitutionInfoMapper {

    /**
     * 分页查询服务商信息和类型
     * @param dm
     * @return
     */
    List<DataInstitutionInfoModel> selectInstitutionAndType(DataInstitutionInfoModel dm);

    /**
     * 插入服务商
     * @param institutionInfoModel
     * @return 服务商ID
     */
    int insertInstitution(DataInstitutionInfoModel institutionInfoModel);

    /**
     * 插入服务商数据类型关系数据
     * @param relationModels
     */
    void insertInstitutionTypeRelation(List<DataInstitutionTypeRelationModel> relationModels);
}