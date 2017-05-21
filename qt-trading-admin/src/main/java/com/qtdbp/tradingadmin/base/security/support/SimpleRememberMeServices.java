/**
 * 
 */
package com.qtdbp.tradingadmin.base.security.support;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.rememberme.AbstractRememberMeServices;
import org.springframework.security.web.authentication.rememberme.RememberMeAuthenticationException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 类功能说明：
 * 
 * <p>Copyright: Copyright © 2012-2013 zrhis.com Inc.</p>
 * @author caidchen
 * @version v1.0
 *
 */
public class SimpleRememberMeServices extends AbstractRememberMeServices {

	private static final String CURRENT_NAME = "current_name";

	public SimpleRememberMeServices(String key, UserDetailsService userDetailsService) {
		super(key, userDetailsService);
	}

	/* (non-Javadoc)
         * @see org.springframework.security.web.authentication.rememberme.AbstractRememberMeServices#onLoginSuccess(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, org.springframework.security.core.Authentication)
         */
	@Override
	protected void onLoginSuccess(HttpServletRequest request,
			HttpServletResponse response,
			Authentication successfulAuthentication) {
		// TODO Auto-generated method stub

		SecurityContextHolder.getContext().setAuthentication(successfulAuthentication);

		this.afterOnLoginSuccess(request, response, successfulAuthentication);

	}

	@Override
	protected UserDetails processAutoLoginCookie(String[] cookieTokens,
			HttpServletRequest request, HttpServletResponse response)
			throws RememberMeAuthenticationException, UsernameNotFoundException {
		// TODO Auto-generated method stub

		return null ;
	}


	private void afterOnLoginSuccess(HttpServletRequest request,
									 HttpServletResponse response,
									 Authentication successfulAuthentication) {

		HttpSession session = request.getSession(false); // 该方法，返回的session 一定不为null
		System.out.println("login success-----------------session id = " + session.getId());

		WebAuthenticationDetails WebAuthenticationDetails = (WebAuthenticationDetails) successfulAuthentication.getDetails();

		System.out.println("WebAuthenticationDetails.getSessionId()-----------------session id = "
						+ WebAuthenticationDetails.getSessionId());

		// 登陆成功时，为新创建出来的空session设置properties,
		session.setAttribute(CURRENT_NAME, successfulAuthentication.getName());
	}
}
