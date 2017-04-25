package com.qtdbp.trading.mapper;

import com.qtdbp.trading.model.DataAuthorizeOrderModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 需求订单数据库CURD操作
 *
 * @author: caidchen
 * @create: 2017-04-19 14:40
 * To change this template use File | Settings | File Templates.
 */
public interface DataAuthorizeOrderMapper {

    /**
     * 查询最新前几条需求订单记录
     * @param userID 用户ID
     * @param productType 产品类型（1数据众包、2方案召集）
     * @param page 前{page}条数据
     * @return
     */
    List<DataAuthorizeOrderModel> findDemandOrderByUserId(@Param("uid") int userID,@Param("type") int productType, @Param("page") int page) ;

    /**
     * 分页获取需求订单数据
     * @param demandOrder
     * @return
     */
    List<DataAuthorizeOrderModel> findDemandOrdersByCondtion(DataAuthorizeOrderModel demandOrder) ;

    /**
     * 获取个人中心我的订单信息
     * @param demandOrder
     * @return
     */
    Map<String, Integer> findAllOrderInfo(DataAuthorizeOrderModel demandOrder);

}
