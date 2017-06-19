package com.qtdbp.trading.constants;

/**
 * SSO统一认证登录配置信息
 *
 * @author: caidchen
 * @create: 2017-04-24 11:08
 * To change this template use File | Settings | File Templates.
 */
public class SsoConstants {

    /**
     * sso统一授权cookie所在的域
     */
    public static final String SSO_COOKIE_DOMAIN = ".qtbigdata.com";

    /**
     * sso统一授权系统用户ID在Cookie中存储的name
     */
    public static final String SSO_ID = "id" ;

    /**
     * sso统一授权系统用户昵称在Cookie中存储的name
     */
    public static final String SSO_NICKNAME = "nickname" ;

    /**
     * sso统一授权系统用户头像在Cookie中存储的name
     */
    public static final String SSO_HEAD = "mobile" ;

    /**
     * sso统一授权系统账号在Cookie中存储的name
     */
    public static final String SSO_USER_NAME = "username" ;

    /**
     * 用户授权信息
     */
    public static final String SSO_USER = "user" ;

    /**
     * 加密解密key
     */
    public static final String DES_KEY = "3dbfac1abd334784860723ef2022a92f" ;

}
