package com.qtdbp.tradingadmin.service;

import com.qtdbp.trading.model.DataTypeModel;
import com.qtdbp.tradingadmin.mapper.DataTypeMapper;
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

    public List findRootNode (DataTypeModel dataType) {

        return null;
    }

}
