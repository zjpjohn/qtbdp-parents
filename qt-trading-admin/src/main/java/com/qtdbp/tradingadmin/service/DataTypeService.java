package com.qtdbp.tradingadmin.service;

import com.qtdbp.tradingadmin.mapper.DataTypeMapper;
import com.qtdbp.tradingadmin.model.DataTypeModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by dell on 2017/5/15.
 */
@Service
public class DataTypeService {

    @Autowired
    public DataTypeMapper dataTypeMapper;

    /**
     * 查询数据类型类目接口
     * @param dataType
     * @return
     */
    public List findNode (DataTypeModel dataType) {

        List<DataTypeModel> list = null;

        if (dataType.getId() != 0) {
            //查询子节点类目
            list = dataTypeMapper.findSecondNode(dataType);
        } else {
            //查询顶级节点类目
            list = dataTypeMapper.findRootNode(dataType);
        }
        return list;
    }

}
