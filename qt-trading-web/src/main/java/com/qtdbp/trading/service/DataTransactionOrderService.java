package com.qtdbp.trading.service;

import com.github.pagehelper.PageHelper;
import com.qtdbp.trading.constants.AppConstants;
import com.qtdbp.trading.exception.GlobalException;
import com.qtdbp.trading.mapper.DataProductMapper;
import com.qtdbp.trading.mapper.DataTransactionOrderMapper;
import com.qtdbp.trading.model.DataItemModel;
import com.qtdbp.trading.model.DataProductModel;
import com.qtdbp.trading.model.DataSosInfoModel;
import com.qtdbp.trading.model.DataTransactionOrderModel;
import org.omg.CORBA.OBJECT_NOT_EXIST;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

/**
 * 订单数据服务
 *
 * @author: caidchen
 * @create: 2017-04-19 13:24
 * To change this template use File | Settings | File Templates.
 */
@Service
public class DataTransactionOrderService {

    @Autowired
    private DataTransactionOrderMapper orderMapper ;

    @Autowired
    private DataProductService productService ;

    @Autowired
    private DataProductMapper productMapper;


    /**
     * 获取最新5条订单信息
     * @param userId
     * @param page 前{page}条数据
     * @return
     */
    public List<DataTransactionOrderModel> findNewOrdersByUserId(int userId,int page) {
        return orderMapper.findNewOrdersByUserId(userId,page);
    }

    /**
     * 获取最新用户订单信息
     * @param order
     * @return
     */
    public List<DataTransactionOrderModel> findOrdersForPage(DataTransactionOrderModel order) {
        if (order.getPage() != null && order.getRows() != null) {
            PageHelper.startPage(order.getPage(), order.getRows());
        }
        return orderMapper.findOrdersByCondtion(order);
    }

    /**
     * 添加新订单
     * @param order
     * @return 成功返回ID,失败返回-1
     * @throws GlobalException
     */
    public int insertOrder(DataTransactionOrderModel order) throws GlobalException {

        if(order == null) throw new GlobalException("订单数据为空") ;

        Integer count = orderMapper.insertOrder(order) ;

        if(count != null && count > 0) return order.getId() ;

        return -1 ;
    }

    /**
     * 根据用户名id和产品id和产品类型来判断是否插入新纪录
     * @param orderModel
     * @return
     * @throws GlobalException
     */
    @Transactional
    public DataTransactionOrderModel insertNewOrder(DataTransactionOrderModel orderModel) throws GlobalException {

        if(orderModel == null) throw new GlobalException("订单数据为空，请重新操作") ;

        if(orderModel.getProductId() == null) throw new GlobalException("此产品不存在，请选择其他产品购买") ;

        List<DataTransactionOrderModel> list = orderMapper.findOrderByUserIdAndProductIdAndType(orderModel);
        // 如果当前用户已经购买了此数据包产品，则无需购买
        boolean canBuy = true ;
        if(list != null && !list.isEmpty()) {
            for(DataTransactionOrderModel order : list){
                // 只要有一条订单待支付或已支付，则不能购买
                if(order.getOrderState().intValue() == AppConstants.ORDER_STATE_PAYING
                        || order.getOrderState().intValue() == AppConstants.ORDER_STATE_PAYED) {
                    canBuy = false;
                    break;
                }
            }
        }

        if(canBuy) {
            // 封装订单数据
            DataProductModel product = null ;
            BigDecimal amount ;
            if(orderModel.getProductType() == AppConstants.PRODUCT_TYPE_PACKAGE){ //数据包
                product = productService.findProductById(orderModel.getProductId());
                if(product == null) throw new GlobalException("此产品不存在，请选择其他产品购买") ;

                orderModel.setOrderSubject(product.getDesignation());
                amount = new BigDecimal(product.getpScore()) ;
            } else {

                DataItemModel itemModel = productMapper.findItemById(orderModel.getProductId());
                if(itemModel == null) throw new GlobalException("此产品不存在，请选择其他产品购买") ;
                //数据条目，默认数据条目1.00
                amount = new BigDecimal(1) ;
                orderModel.setOrderSubject(itemModel.getItemName());
            }
            orderModel.setOrderState((byte)AppConstants.ORDER_STATE_PAYING);
            orderModel.setOrderNo(getOrderNo());
            orderModel.setAmount(amount);
            // 调用订单插入操作
            Integer count = orderMapper.insertOrder(orderModel) ;
            if(count == null || count < 0) throw new GlobalException("订单创建失败，请重新操作") ;
        } else {
           // throw new GlobalException("订单已创建，请前往【个人中心】- 【我的订单】中查看") ;
            orderModel = list.get(0);
        }

        return orderModel ;
    }

    public Integer updateOrder(String orderNo, String tradeNo){
        DataTransactionOrderModel orderModel = new DataTransactionOrderModel();
        orderModel.setOrderState((byte)3);//改变支付状态
        orderModel.setTradeNo(tradeNo);//加入交易流水号
        orderModel.setOrderNo(orderNo);//加入订单号 根据此号来判断更改那条数据
        Integer i = orderMapper.updateOrder(orderModel);
        return i;
    }

    /**
     * 生成订单号
     * @return
     */
    public String getOrderNo() {
        String orderNo = String.valueOf(System.currentTimeMillis())+Math.round(Math.random() * 9000 + 1000);
        return  orderNo;
    }

}
