/**
 * 
 */
package com.qtdbp.tradingadmin.base.security.support;

import com.qtdbp.tradingadmin.base.security.SecurityUser;
import com.qtdbp.tradingadmin.base.security.SysConstant;
import com.qtdbp.tradingadmin.base.security.entity.SysUsers;
import com.qtdbp.tradingadmin.base.security.repository.SysUsersRepository;
import com.qtdbp.tradingadmin.base.security.service.SecurityUserService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 类功能说明：根据用户名获取用户信息及权限信息
 * 
 * @author caidchen
 * @version v1.0
 *
 */
@Component
public class CustomUserDetailsService implements UserDetailsService {

	protected final Log logger = LogFactory.getLog(getClass());

	@Autowired
	private HttpSession session ;

	@Autowired
	private HttpServletRequest request ;

	@Autowired
	private SysUsersRepository sysUsersRepository;

	@Autowired
	private SecurityUserService securityUserService ;

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {

		// 服务器生成的验证码
		Object vrifyCode = session.getAttribute(SysConstant.SESSION_GENERATED_CAPTCHA_KEY);

		// 页面输入的验证码
		String code = request.getParameter(SysConstant.SESSION_GENERATED_CAPTCHA_KEY) ;

		if(vrifyCode == null)
			throw new UsernameNotFoundException("验证码生成失败，请重试");

		if(!code.equalsIgnoreCase(vrifyCode.toString()))
			throw new UsernameNotFoundException("验证码输入不正确");

		logger.info("***********************验证码****************************");
		logger.info("server get code："+ vrifyCode);
		logger.info("custom input code："+ code);
		logger.info("********************************************************");

		SysUsers user = sysUsersRepository.findByUsername(username);
		if(user == null)
			throw new UsernameNotFoundException("用户 " + username + " 不存在");

		SecurityUser securityUser = new SecurityUser(user) ;
		// 得到用户的权限
		securityUser.setAuthorities(securityUserService.loadUserAuthorities( username ));

		logger.info("*********************"+username+"***********************");
		logger.info(securityUser.getAuthorities());
		logger.info("********************************************************");
		
		return securityUser ;
	}

}
