package com.qtdbp.tradingadmin.service;

import com.github.pagehelper.PageHelper;
import com.qtdbp.trading.constants.AuditStatusConstants;
import com.qtdbp.trading.model.DataItemModel;
import com.qtdbp.trading.model.DataProductAttrRelationModel;
import com.qtdbp.trading.model.DataProductModel;
import com.qtdbp.tradingadmin.exception.GlobalAdminException;
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
     * 新增数据包产品、数据条目表数据
     * @param productModel
     * @return
     * @throws GlobalAdminException
     */
    @Transactional
    public Integer insertProduct(DataProductModel productModel) throws GlobalAdminException {

        if (productModel == null) throw  new GlobalAdminException("数据包产品为空") ;
        String filePath = productModel.getFileUrl();
        String dataFormat = filePath.substring(filePath.lastIndexOf(".") + 1);
        productModel.setDataFormat(dataFormat);
        Integer count = productMapper.insertProduct(productModel) ;
        if (count != null && count > 0) {
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
                    if (!(id > 0)) throw new GlobalAdminException("插入数据条目失败");
                }
            }
            return productModel.getId();
        }else {
            throw new GlobalAdminException("新增数据包产品失败");
        }
    }

    /**
     * 更改数据包产品上下架
     * @param productId
     * @return
     * @throws GlobalAdminException
     */
    public Integer updateState(Integer productId) throws GlobalAdminException {
        DataProductModel productModel = productMapper.findProductsById(productId);
        if (productModel == null) throw new GlobalAdminException("该数据包已不存在") ;
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
     * @throws GlobalAdminException
     */
    @Transactional
    public Integer updateProduct(DataProductModel productModel) throws GlobalAdminException {
        Integer count = 0;
        if (productModel.getId() != null && productModel.getId() != 0) {
            count = productMapper.updateProduct(productModel);
            //当数据包产品修改时，数据条目文件也要进行修改
            if (count > 0) {
                //删除以前的数据条目文件
                Integer delCount = itemMapper.deleteByProductId(productModel.getId());
                if (delCount > 0 || delCount == 0 ) {
                    //重新插入修改后的数据文件
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
                            if (!(id > 0)) throw new GlobalAdminException("插入数据条目失败");
                        }
                    }
                }else {
                    throw new GlobalAdminException("删除数据条目失败");
                }
            }else {
                throw new GlobalAdminException("更新数据包产品失败");
            }
        } else {
            throw new GlobalAdminException("产品ID为空，请重新操作");
        }
        return count;
    }

    /**
     * 审核数据包产品
     * @param productModel
     * @return
     * @throws GlobalAdminException
     */
    public Integer auditProduct(DataProductModel productModel) throws GlobalAdminException {
        if (productModel.getId() == null || productModel.getId() == 0) throw new GlobalAdminException("id为空，请重新操作");
        Integer count = 0;
        //审核通过的时候
        if (productModel.getAuditStatus() == AuditStatusConstants.AUDIT_STATUS_PASS) {
            count = productMapper.auditProduct(productModel);
            //审核不通过的时候
        } else if (productModel.getAuditStatus() == AuditStatusConstants.AUDIT_STATUS_NO_PASS) {
            if (productModel.getAuditFailReason() == null) throw new GlobalAdminException("未填写不通过原因，请重新审核");
            count = productMapper.auditProduct(productModel);
        }
        return count > 0 ? count : -1;
    }
}
