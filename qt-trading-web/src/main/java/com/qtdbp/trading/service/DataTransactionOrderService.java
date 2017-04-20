package com.qtdbp.trading.service;

import com.github.pagehelper.PageHelper;
import com.qtdbp.trading.mapper.DataTransactionOrderMapper;
import com.qtdbp.trading.model.DataTransactionOrderModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
