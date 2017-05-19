package com.qtdbp.tradingadmin.mapper;

import com.qtdbp.trading.model.DataTypeAttrModel;
import com.qtdbp.trading.utils.BaseMapper;
import com.qtdbp.tradingadmin.model.DataTypeModel;

import java.util.List;

/**
 * Created by liyang on 2017/5/15.
 */
public interface DataTypeMapper {

    /**
     * 查询一级分类数据类型
     * @param dataType
     * @return
     */
    List<DataTypeModel> findRootNode(DataTypeModel dataType);

    /**
     * 查询二级分类数据类型
     * @param dataType
     * @return
     */
    List<DataTypeModel> findSecondNode(DataTypeModel dataType);

    /**
     * 查询所有属性
     * @return 属性以及所有属性值
     */
    List<DataTypeAttrModel> findAttrAll(Integer dataTypeId) ;

    /**
     * 新增数据类型
     */
    Integer insertType(DataTypeModel dataType);

    /**
     * 修改数据类型
     * @param dataType
     * @return
     */
    Integer updateType(DataTypeModel dataType);

    /**
     * 根据二级节点查询对应的类型属性
     * @param id
     * @return
     */
    List<DataTypeAttrModel> findTypeAttr(Integer id);

    /**
     * 查询所有的数据类型
     * @param isUsed
     * @return
     */
    List<DataTypeModel> findAll(Integer isUsed);
}
