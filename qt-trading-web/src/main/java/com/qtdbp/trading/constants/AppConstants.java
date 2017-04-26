package com.qtdbp.trading.constants;

/**
 * Created with IntelliJ IDEA.
 * User: 浩
 * Date: 2017/4/13
 * Time: 23:15
 * To change this template use File | Settings | File Templates.
 */
public class AppConstants {

    /**
     * http协议
     */
    public static final String HTTP_PRODOCOL = "http://" ;


    /**
     * 用户类型
     * -- 政府
     */
    public static final int USER_TYPE_GOVERNMENT = 1 ;

    /**
     * 用户类型
     * -- 机构
     */
    public static final int USER_TYPE_INSTITUTION = 2 ;

    /**
     * 用户类型
     * -- 企业
     */
    public static final int USER_TYPE_ENTERPRISE = 3 ;

    /**
     * 用户类型
     * -- 个人
     */
    public static final int USER_TYPE_PERSONAL = 4 ;

    /**
     * 用户类型
     * -- 其他
     */
    public static final int USER_TYPE_OTHER = 5 ;


    /**
     * 订单状态
     * -- 待支付
     */
    public static final int ORDER_STATE_PAYING = 1 ;

    /**
     * 订单状态
     * -- 已撤销
     */
    public static final int ORDER_STATE_RESCINDED = 2 ;

    /**
     * 订单状态
     * -- 已支付
     */
    public static final int ORDER_STATE_PAYED = 3 ;


    /**
     * 产品类型
     * -- 条目
     */
    public static final int PRODUCT_TYPE_ITEM = 1 ;

    /**
     * 产品类型
     * -- 数据包
     */
    public static final int PRODUCT_TYPE_PACKAGE = 2 ;

}
