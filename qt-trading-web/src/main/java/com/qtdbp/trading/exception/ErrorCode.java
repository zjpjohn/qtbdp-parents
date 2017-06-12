package com.qtdbp.trading.exception;

/**
 * Created by dell on 2017/4/28.
 */
public class ErrorCode {

    /**
     * 订单重复创建
     * 1000
     */
    public static final Integer ERROR_ORDER_CREATED = 1000 ;

    /**
     * 订单创建失败
     * 1001
     */
    public static final Integer ERROR_ORDER_CREATE_FAIL = 1001 ;

    /**
     * 未登录
     */
    public static final Integer ERROR_LOGIN_NO = 2000 ;

    /**
     * 登录失败
     */
    public static final Integer ERROR_LOGIN_FAIL = 2001 ;

    /**
     * 文件下载错误
     */
    public static final Integer ERROR_FILE_DOWNLOAD_FAIL = 3000 ;

    /**
     * 产品数据为空
     */
    public static final Integer ERROR_PRODUCT_IS_NULL = 4000 ;

}
