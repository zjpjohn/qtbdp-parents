package com.qtdbp.trading.service;

import com.github.pagehelper.PageHelper;
import com.qtdbp.trading.exception.GlobalException;
import com.qtdbp.trading.mapper.DataAuthorizeOrderMapper;
import com.qtdbp.trading.mapper.DataTransactionOrderMapper;
import com.qtdbp.trading.model.DataAuthorizeOrderModel;
import com.qtdbp.trading.model.DataTransactionOrderModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 需求订单数据服务
 *
 * @author: caidchen
 * @create: 2017-04-19 13:24
 * To change this template use File | Settings | File Templates.
 */
@Service
public class DataAuthorizeOrderService {

    @Autowired
    private DataAuthorizeOrderMapper orderMapper ;


    /**
     * 获取最新5条需求订单信息
     * @param userId
     * @param productType 产品类型（1数据众包、2方案召集）
     * @param page 前{page}条数据
     * @return
     */
    public List<DataAuthorizeOrderModel> findDemandOrderByUserId(int userId,int productType, int page) {
        return orderMapper.findDemandOrderByUserId(userId,productType,page);
    }

    /**
     * 获取最新需求订单信息
     * @param order
     * @return
     */
    public List<DataAuthorizeOrderModel> findDemandOrdersByCondtion(DataAuthorizeOrderModel order) {
        if (order.getPage() != null && order.getRows() != null) {
            PageHelper.startPage(order.getPage(), order.getRows());
        }
        return orderMapper.findDemandOrdersByCondtion(order);
    }

    /**
     * 获取个人中心我的订单信息
     * @param order
     * @return
     */
    public Map<String, Long> findAllOrderInfo(DataAuthorizeOrderModel order) throws GlobalException {
        if(order.getUserId() == null) throw new GlobalException("用户Id为空") ;
        Map<String, Long> map = new HashMap<String, Long>();
        map = orderMapper.findAllOrderInfo(order);
        return map;
    }
}
