package com.qtdbp.trading.service;

import com.qtdbp.trading.mapper.DataInstitutionInfoMapper;
import com.qtdbp.trading.model.DataInstitutionInfoModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public List<DataInstitutionInfoModel> selectInstitutionAndType(DataInstitutionInfoModel infoModel){

        List<DataInstitutionInfoModel> infoModels = infoMapper.selectInstitutionAndType(infoModel);
        return infoModels;
    }


}
