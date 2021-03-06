package com.qtdbp.trading.service;

import com.github.pagehelper.PageHelper;
import com.qtdbp.trading.constants.AppConstants;
import com.qtdbp.trading.exception.GlobalException;
import com.qtdbp.trading.mapper.DataInstitutionInfoNewMapper;
import com.qtdbp.trading.model.CompanyInfoModel;
import com.qtdbp.trading.model.DataInstitutionInfoNewModel;
import com.qtdbp.trading.model.PersonalInfoModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
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
     * 根据用户ID查询服务商明细
     * @param createId
     * @return
     */
    public DataInstitutionInfoNewModel findInstitutionNewByCreateId(Integer createId) {

        if(createId == null || createId == 0) return null ;

        return infoNewMapper.findDataInstitutionInfoNewByCreateId(createId);
    }

    /**
     * 根据用户ID查询服务商明细忽略是否通过审核
     * @param createId
     * @return
     */
    public DataInstitutionInfoNewModel findInstitutionInfoIgnoreAuditStatusByCreateId(Integer createId) {

        if(createId == null || createId == 0) return null ;

        return infoNewMapper.findInstitutionInfoIgnoreAuditStatusByCreateId(createId);
    }

    /**
     * 新增服务商
     * @param infoNewModel
     * @return id
     * @throws GlobalException
     */
    @Transactional
    public Integer insertInstitution(DataInstitutionInfoNewModel infoNewModel) throws GlobalException {

        DataInstitutionInfoNewModel infoNewModelOld = null;
        //先查询该用户是否申请过服务商，如果有申请过则抛出异常
        infoNewModelOld = infoNewMapper.findInstitutionInfoIgnoreAuditStatusByCreateId(infoNewModel.getCreateId());
        if (infoNewModelOld != null) throw new GlobalException("该用户已进行过添加服务商操作");

        if (infoNewModel == null) throw new GlobalException("服务商数据丢失");

        Integer infoId = null ;

        if(infoNewModel.getInstitutionType() != null) {
            Integer count ;
            switch (infoNewModel.getInstitutionType().intValue()) {
                case AppConstants.INSTITUTION_TYPE_COMPANY:

                    CompanyInfoModel companyInfoModel = infoNewModel.getCompanyInfoModel();
                    if (companyInfoModel == null) throw new GlobalException("服务商企业资料数据丢失");

                    companyInfoModel.setCreateId(infoNewModel.getCreateId());
                    count = infoNewMapper.insertCompanyInfo(companyInfoModel);
                    if (count != null && count > 0) {
                        infoId = companyInfoModel.getId() ;
                    }
                    break;
                case AppConstants.INSTITUTION_TYPE_PERSION:

                    PersonalInfoModel personalInfoModel = infoNewModel.getPersonalInfoModel() ;
                    if (personalInfoModel == null) throw new GlobalException("服务商个人资料数据丢失");

                    personalInfoModel.setCreateId(infoNewModel.getCreateId());
                    count = infoNewMapper.insertPersonalInfo(personalInfoModel);
                    if (count != null && count > 0) {
                        infoId = personalInfoModel.getId() ;
                    }
                    break;
            }
        }

        if(infoId == null) throw new GlobalException("服务商资料插入失败");

        infoNewModel.setInfoId(infoId);

        Integer count = infoNewMapper.insertDataInstitutionInfoNew(infoNewModel) ;
        if (count != null && count > 0) {
            return infoNewModel.getId();
        }

        return -1 ;
    }

    /**
     * 查询数据服务商数据包商品，爬虫规则商品，数据定制接单数，爬虫规则定制接单数的数量
     * @param userId
     * @return
     */
    public HashMap<String,Long> findCount(Integer userId){
        return infoNewMapper.findInstitutionInfoCount(userId);
    }

    /**
     * 更新服务商信息以及关联的企业或个人资料
     * @param infoNewModel
     * @return
     */
    @Transactional
    public Integer updateInstitutionExtend(DataInstitutionInfoNewModel infoNewModel) throws GlobalException {
        if (infoNewModel == null) throw new GlobalException("服务商信息为空，请重新操作");

        infoNewModel.setAuditStatus(0);
        Integer count = infoNewMapper.updateDataInstitutionInfoNew(infoNewModel);
        if (count > 0){
            PersonalInfoModel personalInfoModel = null;
            personalInfoModel = infoNewModel.getPersonalInfoModel();
            if (personalInfoModel != null) {
                personalInfoModel.setEditId(infoNewModel.getEditId());
                Integer personalCount = infoNewMapper.updatePersonalInfo(personalInfoModel);
                if (!(personalCount > 0)) throw new GlobalException("更新个人资料失败");
            }
            CompanyInfoModel companyInfoModel = null;
            companyInfoModel = infoNewModel.getCompanyInfoModel();
            if (companyInfoModel != null) {
                companyInfoModel.setEditId(infoNewModel.getEditId());
                Integer companyCount = infoNewMapper.updateCompanyInfo(companyInfoModel);
                if (!(companyCount > 0)) throw new GlobalException("更新企业资料失败");
            }
        }
        return count;
    }

}
