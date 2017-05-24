package com.qtdbp.tradingadmin.service;

import com.github.pagehelper.PageHelper;
import com.qtdbp.trading.exception.GlobalException;
import com.qtdbp.trading.model.DataItemModel;
import com.qtdbp.trading.model.DataProductAttrRelationModel;
import com.qtdbp.trading.model.DataProductModel;
import com.qtdbp.tradingadmin.mapper.DataProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

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
     * 新增数据包产品、数据条目产品以及关联数据表数据
     * @param productModel
     * @return
     * @throws GlobalException
     */
    @Transactional
    public Integer insertProduct(DataProductModel productModel) throws GlobalException {

        if (productModel == null) throw  new GlobalException("数据包产品为空") ;

        Integer count = productMapper.insertProduct(productModel) ;
        if (count != null && count > 0) {
            List<DataProductAttrRelationModel> list = productModel.getAttrRelationModels();
            for (DataProductAttrRelationModel model : list) {
                model.setProductId(productModel.getId());
                model.setTypeId(productModel.getDataType());
                Integer id = productMapper.insertProductAttrRelation(model);
                if (!(id > 0)) throw new GlobalException("插入关联表数据失败");
            }
            Map<String, String> itemMap = productModel.getItemMap();
            if (itemMap != null && itemMap.size() > 0) {
                for (String key : itemMap.keySet()) {
                    DataItemModel itemModel = new DataItemModel();
                    itemModel.setProductId(productModel.getId());
                    itemModel.setIsUsed(1);
                    itemModel.setPrice(productModel.getItemPrice());
                    itemModel.setItemName(key);
                    itemModel.setFileUrl(itemMap.get(key));
                    int id = productMapper.insertItem(itemModel);
                    if (!(id > 0)) throw new GlobalException("插入数据条目失败");
                }
            }
            return productModel.getId();
        }else {
            throw new GlobalException("新增数据包产品失败");
        }
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
