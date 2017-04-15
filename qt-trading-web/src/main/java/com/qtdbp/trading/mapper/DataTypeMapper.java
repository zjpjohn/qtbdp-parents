package com.qtdbp.trading.mapper;

import com.qtdbp.trading.model.DataTypeAttrModel;
import com.qtdbp.trading.model.DataTypeModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 处理数据产品类目、属性、属性值的数据操作
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
     * @return
     */
    @Select("select id,name from data_type where is_used = 1 order by sort asc")
//    @Results({
//            @Result(property="id",column="id"),
//            @Result(property="name",column="name")
//    })
    List<DataTypeModel> findAll() ;


    @Select("select * from data_type_attr where type_id = #{tid} order by sort asc")
    List<DataTypeAttrModel> findAttrAllByTypeId(String tid) ;

}
