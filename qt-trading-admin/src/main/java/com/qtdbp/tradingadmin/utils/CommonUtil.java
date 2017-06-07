package com.qtdbp.tradingadmin.utils;

import javax.servlet.http.HttpServletRequest;

/**
 * 公共工具类
 * Created by dell on 2017/4/11.
 */
public class CommonUtil {

    /**
     * excel2003扩展名
     */
    public static final String EXCEL03_EXTENSION = "xls";

    /**
     * excel2007扩展名
     */
    public static final String EXCEL07_EXTENSION = "xlsx";

    /**
     * zip扩展名
     */
    public static final String ZIP_EXTENSION = "zip";

    /**
     * 获取当前登录IP
     * @param request
     * @return
     */
    public static String getIpAddress(HttpServletRequest request){
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}
