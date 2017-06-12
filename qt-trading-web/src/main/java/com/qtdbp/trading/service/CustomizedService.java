package com.qtdbp.trading.service;

import com.github.pagehelper.PageHelper;
import com.qtdbp.trading.model.CustomServiceModel;
import com.qtdbp.trading.exception.GlobalException;
import com.qtdbp.trading.mapper.CustomizedMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 定制服务Service
 *
 * @author: caidchen
 * @create: 2017-06-06 13:37
 * To change this template use File | Settings | File Templates.
 */
@Service
public class CustomizedService {

    @Autowired
    private CustomizedMapper customizedMapper;

    /**
     * 分页获取定制服务数据
     * @param custom
     * @return
     */
    public List<CustomServiceModel> findCustomizedDataForPage(CustomServiceModel custom) {
        if (custom.getPage() != null && custom.getRows() != null) {
            PageHelper.startPage(custom.getPage(), custom.getRows());
        }

        return customizedMapper.findCustomizedDataByCondtion(custom);
    }

    /**
     * 查询定制服务数据明细
     * @param customId
     * @return
     */
    public CustomServiceModel findCustomizedDataById(Integer customId) {

        if(customId == null || customId == 0) return null ;

        return customizedMapper.findCustomizedDataById(customId);
    }

    /**
     * 新增定制服务数据
     * @param custom
     * @return id
     * @throws GlobalException
     */
    public Integer insertCustomizedData(CustomServiceModel custom) throws GlobalException {

        if (custom == null) return -1 ;

        Integer count = customizedMapper.insertCustomizedData(custom) ;
        if (count != null && count > 0) {
            return custom.getId();
        }else {
            return -1 ;
        }
    }

    /**
     * 审核定制服务数据
     * @param custom
     * @return true审核通过，false不通过
     * @throws GlobalException
     */
    public boolean auditCustomizedData(CustomServiceModel custom) throws GlobalException {

        if (custom == null) return false ;

        boolean isSuccess = false ;

        Integer count = customizedMapper.updateCustomizedData(custom) ;
        if (count != null && count > 0) {
            isSuccess = true ;
        }

        return isSuccess ;
    }
}
