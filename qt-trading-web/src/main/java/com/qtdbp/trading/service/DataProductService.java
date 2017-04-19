package com.qtdbp.trading.service;

import com.github.pagehelper.PageHelper;
import com.qtdbp.trading.mapper.DataProductMapper;
import com.qtdbp.trading.model.DataItemModel;
import com.qtdbp.trading.model.DataProductModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 数据包产品业务服务
 *
 * @author: caidchen
 * @create: 2017-04-16 11:37
 * To change this template use File | Settings | File Templates.
 */
@Service
public class DataProductService {

    @Autowired
    private DataProductMapper productMapper ;

    /**
     * 分页获取数据包产品
     * @param product
     * @return
     */
    public List<DataProductModel> findProductsForPage(DataProductModel product) {
        if (product.getPage() != null && product.getRows() != null) {
            PageHelper.startPage(product.getPage(), product.getRows());
        }
        return productMapper.findProductsByCondtion(product);
    }

    /**
     * 单表查询数据包产品
     * @param productId
     * @return
     */
    public DataProductModel findProductById(Integer productId) {

        if(productId == null || productId == 0) return null ;

        return productMapper.findProductsById(productId);
    }

    /**
     * 分页获取数据条目
     * @param item
     * @return
     */
    public List<DataItemModel> findItemsByProductIdForPage(DataItemModel item) {
        if (item.getPage() != null && item.getRows() != null) {
            PageHelper.startPage(item.getPage(), item.getRows());
        }

        return productMapper.findItemsByProductId(item);
    }

}
