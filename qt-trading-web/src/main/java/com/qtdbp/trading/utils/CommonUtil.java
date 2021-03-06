package com.qtdbp.trading.utils;

import javax.servlet.http.HttpServletRequest;

/**
 * 公共工具类
 * Created by dell on 2017/4/11.
 */
public class CommonUtil {

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

    /**
     * 生成10~100之间的随机数
     * @return
     */
    public static int randomNum(long seed) {

        int max=10;
        int min=0;
        java.util.Random r=new java.util.Random(seed);

        return r.nextInt(max)%(max-min+1) + min ;
    }

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
}
