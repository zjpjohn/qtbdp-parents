package com.qtdbp.trading.mapper;

import com.qtdbp.trading.model.DataProductModel;
import com.qtdbp.trading.utils.BaseMapper;

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
     * @param page 当前页
     * @param row 每页记录数
     * @return
     */
//    List<DataProductModel> findProductsForPage(int page, int row) ;
}
