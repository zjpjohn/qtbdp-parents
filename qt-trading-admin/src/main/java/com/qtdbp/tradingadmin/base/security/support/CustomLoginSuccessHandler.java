/**
 * 
 */
package com.qtdbp.tradingadmin.base.security.support;

import com.qtdbp.tradingadmin.base.security.entity.SysUsers;
import com.qtdbp.tradingadmin.base.security.service.SecurityUserService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * 类功能说明：在登录成功后把用户的登录时间及登录IP记录到数据库
 * 
 * @author caidchen
 * @version v1.0
 *
 */
public class CustomLoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
	
	protected Log logger = LogFactory.getLog(getClass());

//	@Autowired
//	private SysUsersRepository sysUsersRepository;

	@Autowired
	private SecurityUserService userService ;
	
	@Override
	@Transactional(readOnly=false,propagation= Propagation.REQUIRED,rollbackFor={Exception.class})
	public void onAuthenticationSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {

		this.saveLoginInfo(request, authentication);


		super.onAuthenticationSuccess(request, response, authentication);
	}
	
	@Transactional(readOnly=false,propagation= Propagation.REQUIRED,rollbackFor={Exception.class})
	public void saveLoginInfo(HttpServletRequest request,Authentication authentication){
		SysUsers user = (SysUsers)authentication.getPrincipal();
		try {
			String ip = this.getIpAddress(request);
			user.setLastLogin(new Date());
			user.setLoginIp(ip);
//			this.sysUsersRepository.saveAndFlush(user);
			userService.update(user) ;
		} catch (DataAccessException e) {
			if(logger.isWarnEnabled()){
				logger.info("无法更新用户登录信息至数据库");
			}
		}
	}
	
	public String getIpAddress(HttpServletRequest request){  
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
