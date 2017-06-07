package com.qtdbp.tradingadmin.mapper;

import com.qtdbp.trading.model.CompanyInfoModel;
import com.qtdbp.trading.model.DataInstitutionInfoNewModel;
import com.qtdbp.trading.model.PersonalInfoModel;
import com.qtdbp.tradingadmin.utils.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 新服务商Mapper
 *
 * @author: caidchen
 * @create: 2017-06-06 18:56
 * To change this template use File | Settings | File Templates.
 */
public interface DataInstitutionInfoNewMapper extends BaseMapper<DataInstitutionInfoNewModel> {

    /**
     * 分页查询定制服务数据
     * @param custom 当前页
     * @return
     */
    List<DataInstitutionInfoNewModel> findDataInstitutionInfoNewByCondtion(DataInstitutionInfoNewModel custom) ;

    /**
     * 查询服务商明细
     * @param id
     * @return
     */
    DataInstitutionInfoNewModel findDataInstitutionInfoNewById(@Param("id") Integer id) ;

    /**
     * 更新服务商
     * @param infoNewModel
     * @return 记录数，成功返回1
     */
    Integer updateDataInstitutionInfoNew(DataInstitutionInfoNewModel infoNewModel) ;

    /**
     * 添加服务商
     * @param infoNewModel
     * @return 记录数，成功返回1
     */
    Integer insertDataInstitutionInfoNew(DataInstitutionInfoNewModel infoNewModel) ;

    /**
     * 查询个人资料详情
     * @param id
     * @return
     */
    PersonalInfoModel findPersonalInfoById(@Param("id") Integer id) ;

    /**
     * 添加个人资料
     * @param personalInfoModel
     * @return 记录数，成功返回1
     */
    Integer insertPersonalInfo(PersonalInfoModel personalInfoModel) ;

    /**
     * 查询企业资料详情
     * @param id
     * @return
     */
    CompanyInfoModel findCompanyInfoById(@Param("id") Integer id) ;

    /**
     * 添加企业资料
     * @param companyInfoModel
     * @return
     */
    Integer insertCompanyInfo(CompanyInfoModel companyInfoModel) ;
}
