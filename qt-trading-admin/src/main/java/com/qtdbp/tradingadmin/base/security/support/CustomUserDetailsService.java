/**
 * 
 */
package com.qtdbp.tradingadmin.base.security.support;

import com.qtdbp.tradingadmin.base.security.SecurityUser;
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
	private SysUsersRepository sysUsersRepository;

	@Autowired
	private SecurityUserService securityUserService ;

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {


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
