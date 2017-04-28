package com.qtdbp.trading.mapper;

import com.qtdbp.trading.model.DataItemModel;
import com.qtdbp.trading.model.DataProductModel;
import com.qtdbp.trading.utils.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 数据包产品数据的CRUD操作
 *
 * @author: caidchen
 * @create: 2017-04-16 9:58
 * To change this template use File | Settings | File Templates.
 */
public interface DataProductMapper extends BaseMapper<DataProductModel> {

    /**
     * 分页查询数据包产品
     * @param productModel 当前页
     * @return
     */
    List<DataProductModel> findProductsByCondtion(DataProductModel productModel) ;

    /**
     * 单表查询数据包产品
     * @param id
     * @return
     */
    DataProductModel findProductsById(@Param("id") Integer id) ;

    /**
     * 分页查询数据条目
     * @param item
     * @return
     */
    List<DataItemModel> findItemsByProductId(DataItemModel item) ;

    /**
     * 单表查询数据条目
     * @param id
     * @return
     */
    DataItemModel findItemById(@Param("id") Integer id) ;

    /**
     * 更新产品信息
     * @param productModel
     * @return 记录数，成功返回1
     */
    Integer updateProduct(DataProductModel productModel) ;
}
