package com.qtdbp.trading.service;

import com.github.pagehelper.PageHelper;
import com.qtdbp.trading.exception.GlobalException;
import com.qtdbp.trading.mapper.DataInstitutionInfoMapper;
import com.qtdbp.trading.model.DataInstitutionInfoModel;
import com.qtdbp.trading.model.DataInstitutionTypeRelationModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by dell on 2017/4/19.
 */
@Service
public class DataInstitutionInfoService {

    @Autowired
    private DataInstitutionInfoMapper infoMapper;

    /**
     * 分页查询服务商信息和类型
     * @param infoModel
     * @return
     */
    public List<DataInstitutionInfoModel> findDataInstitutionInfoByCondition(DataInstitutionInfoModel infoModel){

        if (infoModel.getPage() != null && infoModel.getRows() != null) {
            PageHelper.startPage(infoModel.getPage(), infoModel.getRows());
        }
        List<DataInstitutionInfoModel> infoModels = infoMapper.findDataInstitutionInfoByCondition(infoModel);
        return infoModels;
    }

    /**
     * 个人用户升级为服务商
     *
     * 支持事务机制
     * @param infoModel
     * @return
     * @throws GlobalException
     */
    @Transactional
    public int insertInstitution(DataInstitutionInfoModel infoModel) throws GlobalException {

        if(infoModel == null) throw new GlobalException("服务商数据为空");
        int id = -1 ;

        Integer count = infoMapper.insertInstitution(infoModel) ;
        if(count != null && count > 0) {
            id = infoModel.getId() ; // 返回服务商ID
            List<DataInstitutionTypeRelationModel> relationModels = infoModel.getRelationModels() ;
            if(relationModels != null && !relationModels.isEmpty()) {
                // 通过jdk1.8特性，给关联表赋值
                relationModels.forEach(rel -> rel.setInstitutionId(infoModel.getId()));

                infoMapper.insertInstitutionTypeRelation(relationModels);
            }
        }

        return id ;
    }

    /**
     * 用户是否服务商
     * @param userId
     * @return 服务商信息
     * @throws GlobalException
     */
    public DataInstitutionInfoModel findDataInstitutionByUserId(Integer userId) throws GlobalException {

        if(userId == null) throw new GlobalException("用户ID为空");

        return infoMapper.findDataInstitutionInfoByUserId(userId) ;
    }
}
