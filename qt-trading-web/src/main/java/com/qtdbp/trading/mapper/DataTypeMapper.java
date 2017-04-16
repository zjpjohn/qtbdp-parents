package com.qtdbp.trading.mapper;

import com.qtdbp.trading.model.DataTypeAttrModel;
import com.qtdbp.trading.model.DataTypeModel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 产品类目、属性、属性值的CRUD操作
 *
 * @author: caidchen
 * @create: 2017-04-15 13:22
 * To change this template use File | Settings | File Templates.
 */
@Mapper
public interface DataTypeMapper {

    /**
     * 查询所有可用的数据类型
     * 根据排序正序排列
     * @return 返回数据类型
     */
    List<DataTypeModel> findAll() ;

    /**
     * 查询所有属性
     * @return 属性以及所有属性值
     */
    List<DataTypeAttrModel> findAttrAll() ;

    /**
     * 根据类型ID查询所有属性
     * @param typeId
     * @return 属性以及所有属性值
     */
    List<DataTypeAttrModel> findAttrAllByTypeId(int typeId) ;

}
