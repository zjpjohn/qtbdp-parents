package com.qtdbp.tradingadmin.mapper;

import com.qtdbp.trading.model.DataProductAttrRelationModel;
import com.qtdbp.trading.model.DataProductModel;
import com.qtdbp.trading.utils.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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
     * 更新产品信息
     * @param productModel
     * @return 记录数，成功返回1
     */
    Integer updateProduct(DataProductModel productModel) ;

    /**
     * 新增数据包产品
     * @param productModel
     * @return  成功返回新增数据的ID
     */
    Integer insertProduct(DataProductModel productModel);

    /**
     * 下架数据包产品
     * @param id
     * @return
     */
    Integer updateSoldOut(@Param("id") Integer id) ;

    /**
     * 上架数据包产品
     * @param id
     * @return
     */
    Integer updatePutaway(@Param("id") Integer id) ;

    /**
     * 新增数据包产品关联表数据
     * @param dataProductAttrRelationModel
     * @return
     */
    Integer insertProductAttrRelation(DataProductAttrRelationModel dataProductAttrRelationModel);

    /**
     * 根据productId删除数据包属性关联表数据
     * @param productId
     * @return
     */
    Integer deleteAttrByProductId(Integer productId);

    /**
     * 审核数据包产品
     * @param productModel
     * @return
     */
    Integer auditProduct(DataProductModel productModel);

    /**
     * 删除数据包产品
     * @param id
     * @return
     */
    Integer deleteProduct(@Param("id") Integer id);
}
