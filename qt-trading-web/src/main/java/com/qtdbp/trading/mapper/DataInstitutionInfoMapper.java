package com.qtdbp.trading.mapper;

import com.qtdbp.trading.model.DataInstitutionInfoModel;
import com.qtdbp.trading.model.DataInstitutionTypeRelationModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 数据服务商数据库CRUD操作
 *
 * Created by liyang on 2017/4/19.
 */
public interface DataInstitutionInfoMapper {

    /**
     * 分页查询服务商信息和类型
     * @param infoModel
     * @return
     */
    List<DataInstitutionInfoModel> findDataInstitutionInfoByCondition(DataInstitutionInfoModel infoModel);

    /**
     * 插入服务商
     * @param institutionInfoModel
     * @return DataInstitutionInfoModel 插入成功服务商ID
     */
    Integer insertInstitution(DataInstitutionInfoModel institutionInfoModel);

    /**
     * 批量插入服务商数据类型关系数据
     * @param relationModels
     */
    void insertInstitutionTypeRelation(@Param("list") List<DataInstitutionTypeRelationModel> relationModels);
}