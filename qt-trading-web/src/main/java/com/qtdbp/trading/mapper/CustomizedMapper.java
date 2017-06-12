package com.qtdbp.trading.mapper;

import com.qtdbp.trading.model.CustomServiceModel;
import com.qtdbp.trading.utils.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 定制服务Mapper
 *
 * @author: caidchen
 * @create: 2017-06-06 13:07
 * To change this template use File | Settings | File Templates.
 */
public interface CustomizedMapper extends BaseMapper<CustomServiceModel> {

    /**
     * 分页查询定制服务数据
     * @param custom 当前页
     * @return
     */
    List<CustomServiceModel> findCustomizedDataByCondtion(CustomServiceModel custom) ;

    /**
     * 查询定制服务数据明细
     * @param id
     * @return
     */
    CustomServiceModel findCustomizedDataById(@Param("id") Integer id) ;

    /**
     * 更新定制服务数据
     * @param custom
     * @return 记录数，成功返回1
     */
    Integer updateCustomizedData(CustomServiceModel custom) ;

    /**
     * 添加定制服务数据
     * @param custom
     * @return 记录数，成功返回1
     */
    Integer insertCustomizedData(CustomServiceModel custom) ;
}
