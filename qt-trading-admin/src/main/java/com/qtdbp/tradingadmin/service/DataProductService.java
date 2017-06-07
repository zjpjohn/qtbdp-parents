package com.qtdbp.tradingadmin.service;

import com.github.pagehelper.PageHelper;
import com.qtdbp.trading.constants.AuditStatusConstants;
import com.qtdbp.trading.exception.GlobalException;
import com.qtdbp.trading.model.DataItemModel;
import com.qtdbp.trading.model.DataProductAttrRelationModel;
import com.qtdbp.trading.model.DataProductModel;
import com.qtdbp.tradingadmin.mapper.DataItemMapper;
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

    @Autowired
    private DataItemMapper itemMapper;

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
            if (list != null && list.size() > 0) {
                for (DataProductAttrRelationModel model : list) {
                    model.setProductId(productModel.getId());
                    model.setTypeId(productModel.getDataType());
                    Integer id = productMapper.insertProductAttrRelation(model);
                    if (!(id > 0)) throw new GlobalException("插入关联表数据失败");
                }
            }
            Map<String, String> itemMap = productModel.getSubFiles();
            if (itemMap != null && itemMap.size() > 0) {
                for (String key : itemMap.keySet()) {
                    DataItemModel itemModel = new DataItemModel();
                    itemModel.setProductId(productModel.getId());
                    itemModel.setIsUsed(1);
                    itemModel.setPrice(productModel.getItemPrice());
                    itemModel.setItemName(key);
                    itemModel.setFileUrl(itemMap.get(key));
                    int id = itemMapper.insertItem(itemModel);
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
        DataProductModel productModel = productMapper.findProductsById(productId);
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
     * 更新数据包产品信息、数据条目信息、数据属性信息
     * @param productModel
     * @return
     * @throws GlobalException
     */
    public Integer updateProduct(DataProductModel productModel) throws GlobalException {
        Integer count = 0;
        if (productModel.getId() != null && productModel.getId() != 0) {
            count = productMapper.updateProduct(productModel);
            if (count > 0) {
                List<DataProductAttrRelationModel> list = productModel.getAttrRelationModels();
                if (list != null && list.size() > 0) {
                    //删除数据包属性关联表数据
                    Integer delCount = productMapper.deleteAttrByProductId(productModel.getId());
                    if (delCount > 0) {
                        //重新插入数据
                        for (DataProductAttrRelationModel model : list) {
                            model.setProductId(productModel.getId());
                            model.setTypeId(productModel.getDataType());
                            Integer id = productMapper.insertProductAttrRelation(model);
                            if (!(id > 0)) throw new GlobalException("插入关联表数据失败");
                        }
                    } else {
                        throw new GlobalException("删除数据包属性关联表数据失败");
                    }
                }
                Map<String, String> itemMap = productModel.getSubFiles();
                //当数据包产品修改时，数据条目文件也要进行修改
                if (itemMap != null && itemMap.size() > 0) {
                    //删除数据条目文件
                    Integer delCount = itemMapper.deleteByProductId(productModel.getId());
                    //重新插入修改后的数据文件
                    if (delCount > 0) {
                        for (String key : itemMap.keySet()) {
                            DataItemModel itemModel = new DataItemModel();
                            itemModel.setProductId(productModel.getId());
                            itemModel.setIsUsed(1);
                            itemModel.setPrice(productModel.getItemPrice());
                            itemModel.setItemName(key);
                            itemModel.setFileUrl(itemMap.get(key));
                            int id = itemMapper.insertItem(itemModel);
                            if (!(id > 0)) throw new GlobalException("插入数据条目失败");
                        }
                    }else {
                        throw new GlobalException("删除数据条目失败");
                    }
                }
            }
        } else {
            throw new GlobalException("产品ID为空，请重新操作");
        }
        return count;
    }

    /**
     * 审核爬虫规则
     * @param productModel
     * @return
     * @throws GlobalException
     */
    public Integer auditProduct(DataProductModel productModel) throws GlobalException {
        if (productModel.getId() == null || productModel.getId() == 0) throw new GlobalException("id为空，请重新操作");
        Integer count = 0;
        //审核通过的时候
        if (productModel.getAuditStatus() == AuditStatusConstants.AUDIT_STATUS_PASS) {
            count = productMapper.auditProduct(productModel);
            //审核不通过的时候
        } else if (productModel.getAuditStatus() == AuditStatusConstants.AUDIT_STATUS_NO_PASS) {
            if (productModel.getAuditFailReason() == null) throw new GlobalException("未填写不通过原因，请重新审核");
            count = productMapper.auditProduct(productModel);
        }
        return count > 0 ? count : -1;
    }
}
