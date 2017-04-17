package com.qtdbp.trading.mapper;

import com.qtdbp.trading.model.DataProductModel;
import com.qtdbp.trading.utils.BaseMapper;

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
}
