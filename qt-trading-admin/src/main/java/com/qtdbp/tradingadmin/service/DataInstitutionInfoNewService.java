package com.qtdbp.tradingadmin.service;

import com.github.pagehelper.PageHelper;
import com.qtdbp.trading.constants.AppConstants;
import com.qtdbp.trading.model.CompanyInfoModel;
import com.qtdbp.trading.model.DataInstitutionInfoNewModel;
import com.qtdbp.trading.model.PersonalInfoModel;
import com.qtdbp.tradingadmin.exception.GlobalAdminException;
import com.qtdbp.tradingadmin.mapper.DataInstitutionInfoNewMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 新服务商Service
 *
 * @author: caidchen
 * @create: 2017-06-06 20:16
 * To change this template use File | Settings | File Templates.
 */
@Service
public class DataInstitutionInfoNewService {

    @Autowired
    private DataInstitutionInfoNewMapper infoNewMapper;

    /**
     * 分页获取服务商
     * @param infoNewModel
     * @return
     */
    public List<DataInstitutionInfoNewModel> findInstitutionNewForPage(DataInstitutionInfoNewModel infoNewModel) {
        if (infoNewModel.getPage() != null && infoNewModel.getRows() != null) {
            PageHelper.startPage(infoNewModel.getPage(), infoNewModel.getRows());
        }

        return infoNewMapper.findDataInstitutionInfoNewByCondtion(infoNewModel);
    }

    /**
     * 查询服务商明细
     * @param infoNewId
     * @return
     */
    public DataInstitutionInfoNewModel findInstitutionNewById(Integer infoNewId) {

        if(infoNewId == null || infoNewId == 0) return null ;

        return infoNewMapper.findDataInstitutionInfoNewById(infoNewId);
    }

    /**
     * 新增服务商
     * @param infoNewModel
     * @return id
     * @throws GlobalAdminException
     */
    @Transactional
    public Integer insertInstitution(DataInstitutionInfoNewModel infoNewModel) throws GlobalAdminException {

        if (infoNewModel == null) throw new GlobalAdminException("服务商数据丢失");

        Integer infoId = null ;

        if(infoNewModel.getInstitutionType() != null) {
            Integer count ;
            switch (infoNewModel.getInstitutionType().intValue()) {
                case AppConstants.INSTITUTION_TYPE_COMPANY:

                    CompanyInfoModel companyInfoModel = infoNewModel.getCompanyInfoModel();
                    if (companyInfoModel == null) throw new GlobalAdminException("服务商企业资料数据丢失");

                    count = infoNewMapper.insertCompanyInfo(companyInfoModel);
                    if (count != null && count > 0) {
                        infoId = companyInfoModel.getId() ;
                    }
                    break;
                case AppConstants.INSTITUTION_TYPE_PERSION:

                    PersonalInfoModel personalInfoModel = infoNewModel.getPersonalInfoModel() ;
                    if (personalInfoModel == null) throw new GlobalAdminException("服务商个人资料数据丢失");

                    count = infoNewMapper.insertPersonalInfo(personalInfoModel);
                    if (count != null && count > 0) {
                        infoId = personalInfoModel.getId() ;
                    }
                    break;
            }
        }

        if(infoId == null) throw new GlobalAdminException("服务商资料插入失败");

        infoNewModel.setInfoId(infoId);

        Integer count = infoNewMapper.insertDataInstitutionInfoNew(infoNewModel) ;
        if (count != null && count > 0) {
            return infoNewModel.getId();
        }

        return -1 ;
    }

    /**
     * 审核服务商
     * @param infoNewModel
     * @return true审核通过，false不通过
     * @throws GlobalAdminException
     */
    public boolean auditInstitution(DataInstitutionInfoNewModel infoNewModel) throws GlobalAdminException {

        if (infoNewModel == null) return false ;

        boolean isSuccess = false ;

        Integer count = infoNewMapper.updateDataInstitutionInfoNew(infoNewModel) ;
        if (count != null && count > 0) {
            isSuccess = true ;
        }

        return isSuccess ;
    }
}
