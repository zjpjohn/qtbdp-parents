package com.qtdbp.tradingadmin.utils;

import com.qtdbp.tradingadmin.exception.GlobalAdminException;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

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


    /**
     * 修改图片信息，增加图片水印，限制大图片宽度
     * @param imgpath
     * @return
     * @throws GlobalAdminException
     */
    public static String changeImgInfo(String imgpath) throws GlobalAdminException {
        StringBuilder imgUrl = new StringBuilder(imgpath);
        try {
            BufferedImage sourceImg=ImageIO.read(new URL(imgpath).openStream());
            if (sourceImg.getWidth() > 1200) {
                imgUrl.append("?x-oss-process=image/resize,m_mfit,w_1200,color_ffffff/format,png/watermark,size_50,text_6ZKx5aGY5aSn5pWw5o2u,t_50,g_center,color_ffffff");
            } else {
                imgUrl.append("?x-oss-process=image/watermark,size_50,text_6ZKx5aGY5aSn5pWw5o2u,t_50,g_center,color_ffffff");
            }
        } catch (MalformedURLException e) {
            throw new GlobalAdminException("图片地址错误");
        } catch (IOException e) {
            throw new GlobalAdminException("计算图片尺寸出错");
        }
        return imgUrl.toString();
    }

}
