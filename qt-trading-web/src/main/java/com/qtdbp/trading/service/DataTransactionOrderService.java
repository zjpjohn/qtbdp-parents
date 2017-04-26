package com.qtdbp.trading.service;

import com.github.pagehelper.PageHelper;
import com.qtdbp.trading.exception.GlobalException;
import com.qtdbp.trading.mapper.DataTransactionOrderMapper;
import com.qtdbp.trading.model.DataSosInfoModel;
import com.qtdbp.trading.model.DataTransactionOrderModel;
import org.omg.CORBA.OBJECT_NOT_EXIST;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public Map<String, Object> insertNewOrder(DataTransactionOrderModel orderModel) throws GlobalException {

        Map<String, Object> map = new HashMap<String, Object>();
        List<DataTransactionOrderModel> list = new ArrayList<>();
        if(orderModel.getUserId() != null && orderModel.getProductId() != null && orderModel.getProductType() != null){
            list = orderMapper.findOrderByUserIdAndProductIdAndType(orderModel);
            if(list != null && list.size()>0){
                for(DataTransactionOrderModel order : list){
                    Byte orderState = order.getOrderState();
                    if(orderState == 1){//待支付
                        map.put("orderState","1");
                        map.put("pojo", order);
                    }else if(orderState == 2){//已撤销
                        int id = orderMapper.insertOrder(orderModel) ;
                        orderModel.setId(id);
                        map.put("orderState", "2");
                        map.put("pojo", orderModel);
                    }else{//已支付
                        map.put("orderState","3");
                        map.put("pojo", order);
                    }
                }
            }else{
                int id = orderMapper.insertOrder(orderModel) ;
                orderModel.setId(id);
                map.put("orderState", "4");
                map.put("pojo", orderModel);
            }
        }else{
            throw new GlobalException("订单数据的userId或productId或productType为空") ;
        }
        return map;
    }

    public Integer updateOrder(String orderNo, String tradeNo){
        DataTransactionOrderModel orderModel = new DataTransactionOrderModel();
        orderModel.setOrderState((byte)3);//改变支付状态
        orderModel.setPayTime(new Date());//加入付款时间
        orderModel.setFinishTime(new Date());//加入订单完成时间
        orderModel.setTradeNo(tradeNo);//加入交易流水号
        orderModel.setOrderNo(orderNo);//订单号
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
