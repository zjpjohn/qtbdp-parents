package com.qtdbp.trading.mapper;

import com.qtdbp.trading.model.DataTransactionOrderModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 订单数据库CRUD操作
 *
 * @author: caidchen
 * @create: 2017-04-19 12:43
 * To change this template use File | Settings | File Templates.
 */
public interface DataTransactionOrderMapper {


    /**
     * 查询最新前几条订单记录
     * @param userID 用户ID
     * @param page 前{page}条数据
     * @return
     */
    List<DataTransactionOrderModel> findNewOrdersByUserId(@Param("uid") int userID,@Param("page") int page) ;

    /**
     * 分页获取订单数据
     * @param order
     * 其中order.order_state 订单状态（1待支付、2已撤销、3已支付）,不传表示所有
     * @return
     */
    List<DataTransactionOrderModel> findOrdersByCondtion(DataTransactionOrderModel order) ;

    /**
     * 添加新订单
     * @param orderModel
     * @return 记录数，成功返回1
     */
    Integer insertOrder(DataTransactionOrderModel orderModel) ;

    /**
     * 根据用户名和产品id和产品类型查询是否有该条记录
     * @param order
     * @return
     */
    List<DataTransactionOrderModel> findOrderByUserIdAndProductIdAndType(DataTransactionOrderModel order);

    /**
     * 更新订单状态
     * @param order
     * @return
     */
    Integer updateOrder(DataTransactionOrderModel order);

    /**
     * 查询订单信息
     * @param orderNo
     * @return
     */
    DataTransactionOrderModel findOrderByNo(String orderNo) ;

    /**
     * 获取个人中心我的订单信息
     * @param userId
     * @return
     */
    Map<String, Long> findAllOrderInfo(Integer userId);
}
