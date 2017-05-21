package com.qtdbp.tradingadmin.base.security.support;

import com.qtdbp.tradingadmin.base.security.SecurityUser;
import com.qtdbp.tradingadmin.base.security.SysConstant;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Collection;

/**
 * 自定义验证
 *
 * @author: caidchen
 * @create: 2017-05-21 15:39
 * To change this template use File | Settings | File Templates.
 */
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    protected final Log logger = LogFactory.getLog(getClass());

    @Autowired
    private CustomUserDetailsService userService;

    @Autowired
    private HttpSession session ;

    @Autowired
    private HttpServletRequest request ;

    /**
     * 自定义验证方式
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        // 服务器生成的验证码
        Object vrifyCode = session.getAttribute(SysConstant.SESSION_GENERATED_CAPTCHA_KEY);
        // 页面输入的验证码
        String code = request.getParameter(SysConstant.SESSION_GENERATED_CAPTCHA_KEY) ;
        if(vrifyCode == null)
            throw new BadCredentialsException("验证码生成失败，请重试");

        if(!code.equalsIgnoreCase(vrifyCode.toString()))
            throw new UsernameNotFoundException("验证码输入不正确");

        logger.info("***********************验证码****************************");
        logger.info("server get code："+ vrifyCode);
        logger.info("custom input code："+ code);
        logger.info("********************************************************");

        String username = authentication.getName();
        String password = (String) authentication.getCredentials();
        SecurityUser user = (SecurityUser) userService.loadUserByUsername(username);
        if(user == null)
            throw new BadCredentialsException("用户名或密码错误.");

        //加密过程在这里体现
        if (!passwordEncoder().matches(password, user.getPassword()))
            throw new BadCredentialsException("用户名或密码错误.");

        Collection<? extends GrantedAuthority> authorities = user.getAuthorities() ;
        return new UsernamePasswordAuthenticationToken(user, password, authorities);
    }

    @Override
    public boolean supports(Class<?> arg0) {
        return true;
    }

    /**
     * 对于密码做加密处理
     * @return
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(4);
    }
}
