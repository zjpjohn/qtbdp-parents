package com.qtdbp.tradingadmin.base.security;

/**
 * 系统常量
 *
 * @author: caidchen
 * @create: 2017-05-11 11:15
 * To change this template use File | Settings | File Templates.
 */
public class SysConstant {

    /**===================================================================
     *        security权限管理常量
     **===================================================================*/

    /**
     * 应用编码
     * - 交易平台后台应用
     */
    public static final String APP_TRADING_ADMIN = "APP_TRADING_ADMIN" ;

    /**
     * 模块类型
     * - 所有
     */
    public static final String MODULE_TYPE_ALL = "ALL" ;

    /**
     * 模块类型
     * - post
     */
    public static final String MODULE_TYPE_POST = "POST" ;

    /**
     * 模块类型
     * - get
     */
    public static final String MODULE_TYPE_GET = "GET" ;

    /**
     * 模块类型
     * - delete
     */
    public static final String MODULE_TYPE_DELETE = "DELETE" ;

    /**
     * 模块类型
     * - put
     */
    public static final String MODULE_TYPE_PUT = "PUT" ;

    /**
     * 资源类型
     * - METHOD
     */
    public static final String RESOURCE_TYPE_METHOD = "METHOD" ;

    /**
     * 资源类型
     * - URL
     */
    public static final String RESOURCE_TYPE_URL = "URL" ;

    /**
     * 数据库管理资源key
     */
    public final static String RESOURCE_KEY = "resourcePath";

    /**
     * 数据库管理权限key
     */
    public final static String AUTH_KEY = "authorityMark";

    /**
     * 验证码key
     */
    public static final String SESSION_GENERATED_CAPTCHA_KEY = "vrifyCode" ;
}
