package com.qtdbp.trading.utils;

import com.qtdbp.trading.constants.SsoConstants;
import com.qtdbp.trading.exception.GlobalException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * sso授权地址工具类
 */
public class SsoUrlUtil {

	private static final String AES_KEY = "b9deb3d37bea65156b4110dfc1c49b27" ;

	/**
	 * 获取浏览器单点登录成功后的用户id
	 * 密码默认123456
	 * @param request
	 * @return
	 * @throws GlobalException
	 */
	public static String loginParams(HttpServletRequest request) throws GlobalException {

		Cookie[] cookies = request.getCookies() ;
		if(cookies == null) throw new GlobalException("用户未登录，请先登录") ;

		String ssoUserId = null ;

		for(Cookie cookie : cookies){
			if(cookie.getName().equals(SsoConstants.SSO_COOKIE_NAME_USER_ID)){
				ssoUserId = cookie.getValue();
				break;
			}
		}

		if(ssoUserId == null) throw new GlobalException("系统用户不存在，请重新登陆") ;

		return ssoUserId ;
	}

	/**
	 * 组装SSO登录地址
	 * @return
	 */
	public static String generateUrl(String host, String callback){

		String retVal = host + callback ;

		// 目前定死
		retVal += "&sysid=msvtPqZBiqX5qeOaXUEFL5AilgBSA2KTFu0WN74T4MIHjWUMxYbTvQ%3D%3D" ;// + Cryptos.aesEncrypt(ssoKey, AES_KEY);

		return retVal;
	}
	
	
	public static void main(String[] args) {

	}
}
