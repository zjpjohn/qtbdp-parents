package com.qtdbp.tradingadmin.service;

import com.github.pagehelper.PageHelper;
import com.qtdbp.trading.exception.GlobalException;
import com.qtdbp.trading.model.DataProductModel;
import com.qtdbp.tradingadmin.mapper.DataProductMapper;
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
     * 新增数据包产品
     * @param productModel
     * @return
     * @throws GlobalException
     */
    public Integer insertProduct(DataProductModel productModel) throws GlobalException {

        if (productModel == null) throw  new GlobalException("数据包产品为空") ;

        Integer count = productMapper.insertProduct(productModel) ;
        if (count != null && count >0) return productModel.getId();
        return -1;
    }

    /**
     * 更改数据包产品上下架
     * @param productId
     * @return
     * @throws GlobalException
     */
    public Integer updateState(Integer productId) throws GlobalException {
        DataProductModel productModel = productMapper.findProductByIdIgnoreIsUsed(productId);
        if (productModel == null) throw new GlobalException("该数据包已不存在") ;
        Integer count = 0;
        if (productModel.getIsUsed() == 1){ // 上架时改为下架
            count = productMapper.updateSoldOut(productId) ;
        } else{                             //  下架时改为上架
            count = productMapper.updatePutaway(productId);
        }

        return count;
    }

    /**
     * 更新数据包产品信息
     * @param productModel
     * @return
     * @throws GlobalException
     */
    public Integer updateProduct(DataProductModel productModel) throws GlobalException {
        Integer count = 0;
        if (productModel.getId() != null && productModel.getId() != 0) {
            count = productMapper.updateProduct(productModel);
        } else {
            throw new GlobalException("产品ID为空，请重新操作");
        }
        return count;
    }
}