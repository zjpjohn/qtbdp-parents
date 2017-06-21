package com.qtdbp.trading.service;

import com.github.pagehelper.PageHelper;
import com.qtdbp.trading.constants.AppConstants;
import com.qtdbp.trading.exception.ErrorCode;
import com.qtdbp.trading.exception.GlobalException;
import com.qtdbp.trading.mapper.DataProductMapper;
import com.qtdbp.trading.mapper.DataTransactionOrderMapper;
import com.qtdbp.trading.model.DataItemModel;
import com.qtdbp.trading.model.DataProductModel;
import com.qtdbp.trading.model.DataTransactionOrderModel;
import com.qtdbp.trading.utils.Message;
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
    private DataTransactionOrderMapper orderMapper ;

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

    /**
     * 新增数据包产品、数据条目表数据
     * @param productModel
     * @return
     * @throws GlobalException
     */
    @Transactional
    public Integer insertProduct(DataProductModel productModel) throws GlobalException {

        if (productModel == null) throw  new GlobalException("数据包产品为空") ;

        Integer count = productMapper.insertProduct(productModel) ;
        if (count != null && count >0){
            Map<String, String> itemMap = productModel.getSubFiles();
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
        }
        return -1;
    }

    /**
     * 检查是否可以购买数据包产品
     * @param productId
     * @param productType
     * @return Message
     */
    public Message checkBuyData(Integer productId, Integer productType, Integer userId) {

        Message message = new Message() ;

        DataTransactionOrderModel orderModel = new DataTransactionOrderModel();
        orderModel.setProductId(productId);
        orderModel.setProductType(productType.byteValue());
        orderModel.setUserId(userId);

        List<DataTransactionOrderModel> list = orderMapper.findOrderByUserIdAndProductIdAndType(orderModel);
        // 如果当前用户已经购买了此数据包产品，则无需购买
        if(list != null && !list.isEmpty()) {
            for(DataTransactionOrderModel order : list) {
                // 只要有一条订单待支付或已支付，则不能购买
                if(order.getOrderState().intValue() == AppConstants.ORDER_STATE_PAYING
                        || order.getOrderState().intValue() == AppConstants.ORDER_STATE_PAYED) {

                    message.setSuccess(false);
                    message.setErrorCode(ErrorCode.ERROR_ORDER_CREATED);
                    message.setMessage("订单已创建，请前往【个人中心】- 【我的订单】中查看");
                    break;
                }
            }
        } else {
            message.setSuccess(true);
            message.setMessage("可以购买数据包");
        }

        return message ;
    }

    /**
     * 检查数据包产品文件是否存在
     * @param productId
     * @return
     * @throws GlobalException
     */
    public Message checkDownloadFile(Integer productId) throws GlobalException {

        Message message = new Message() ;

        DataProductModel productModel = productMapper.findProductsById(productId);
        if (productModel == null) {
            message.setSuccess(false);
            message.setErrorCode(ErrorCode.ERROR_PRODUCT_IS_NULL);
            message.setMessage("该数据包已不存在");
        } else {
            String filePath = productModel.getFileUrl();
            String fileName = productModel.getDesignation();

            if(filePath == null) {
                message.setSuccess(false);
                message.setErrorCode(ErrorCode.ERROR_PRODUCT_IS_NULL);
                message.setMessage("数据包产品："+fileName+" 文件地址不存在");
            } else {
                message.setSuccess(true);
                message.setData(filePath);
            }
        }

        return message ;
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
     * 更新数据包产品信息、数据条目信息
     * @param productModel
     * @return
     * @throws GlobalException
     */
    @Transactional
    public Integer updateProduct(DataProductModel productModel) throws GlobalException {
        Integer count = 0;
        if (productModel.getId() != null && productModel.getId() != 0) {
            count = productMapper.updateProduct(productModel);
            if (count > 0) {

                Map<String, String> itemMap = productModel.getSubFiles();
                //当数据包产品修改时，数据条目文件也要进行修改
                if (itemMap != null && itemMap.size() > 0) {
                    //删除数据条目文件
                    Integer delCount = productMapper.deleteByProductId(productModel.getId());
                    //重新插入修改后的数据文件
                    if (delCount > 0) {
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
}
