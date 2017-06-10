package com.qtdbp.trading.service;

import com.qtdbp.trading.mapper.DataTypeMapper;
import com.qtdbp.trading.model.DataTypeModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dell on 2017/6/10.
 */
@Service
public class DataTypeService {

    @Autowired
    private DataTypeMapper dataTypeMapper;

    /**
     * 递归查询数据类型某一节点下所有的叶子节点
     * @param dataType
     * @return
     */
    public List getAllDataTypeIds(Integer dataType) {

        if (dataType == 0 || dataType == null) return null;

        List<Integer> dataTypeIdsList = new ArrayList<>();

        List<DataTypeModel> list = dataTypeMapper.findDataTypeByParentId(dataType);
        if (list == null || list.size() == 0) return dataTypeIdsList;
        for(DataTypeModel model : list){
            dataTypeIdsList.add(model.getId());
            List<Integer> idsList = getAllDataTypeIds(model.getId());
            if(idsList != null && idsList.size() != 0) dataTypeIdsList.addAll(idsList);
        }
        return dataTypeIdsList;
    }

    /**
     * 把所有叶子节点合成一个字符串
     * @param dataType
     * @return
     */
    public String getDataTypes(Integer dataType){
        List<Integer> list = getAllDataTypeIds(dataType);
        String dataTypes = "";
        if (list != null && list.size() != 0){
            for (int i = 0; i<list.size(); i++){
                if (i == (list.size()-1)){
                    dataTypes = dataTypes + list.get(i) ;
                }else {
                    dataTypes = dataTypes + list.get(i) + ",";
                }
            }
            return dataTypes;
        }else {
            if (dataType != 0 ) {
                return dataType + "";
            }
        }
        return null;
    }
}
