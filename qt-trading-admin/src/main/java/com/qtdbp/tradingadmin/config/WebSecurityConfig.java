package com.qtdbp.tradingadmin.config;

import com.qtdbp.tradingadmin.base.security.support.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

/**
 * 钱塘交易中心应用权限框架配置
 *
 * @author: caidchen
 * @create: 2017-04-12 14:02
 * To change this template use File | Settings | File Templates.
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)//允许进入页面方法前检验
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomUserDetailsService defaultUserDetailsService ;

    @Autowired
    private CustomFilterSecurityInterceptor customFilterSecurityInterceptor;

    @Autowired
    private CustomAuthenticationProvider provider;//自定义验证

    private static final String KEY = "admin_key" ;

    /**
     * http://localhost:8080/login 输入正确的用户名密码 并且选中remember-me 则登陆成功，转到 index页面
     * 再次访问index页面无需登录直接访问
     * 访问http://localhost:8080/home 不拦截，直接访问，
     * 访问http://localhost:8080/hello 需要登录验证后，且具备 “ADMIN”权限hasAuthority("ADMIN")才可以访问
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable(); // 关闭crsf() 防止post请求405

        http.authorizeRequests()
                .antMatchers("/login/**","/captcha/**").anonymous()
                .anyRequest().authenticated();

        http.formLogin()
                .loginPage("/login")
                .failureUrl("/login?error=true")
                .defaultSuccessUrl("/")
                .permitAll()
                .successHandler(loginSuccessHandler()) ;

        // 配置登出
        http.logout()
                .logoutSuccessUrl("/login")
                .permitAll();

        // 登录后记住用户，下次自动登录,数据库中必须存在名为persistent_logins的表
        http.rememberMe()
                .rememberMeServices(new SimpleRememberMeServices(KEY, defaultUserDetailsService))
                .tokenValiditySeconds(1209600) ;

        // 检测失效的sessionId,超时时定位到另外一个URL
        http.sessionManagement().invalidSessionUrl("/timeout") ;

        // 数据库管理资源，权限校验
        http.addFilterBefore(customFilterSecurityInterceptor, FilterSecurityInterceptor.class);
    }


    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        //需要将密码加密后写入数据库
        auth.eraseCredentials(false);
        //将验证过程交给自定义验证工具
        auth.authenticationProvider(provider) ;
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        //设置不拦截规则
        web.ignoring().antMatchers("/css/**","/fonts/**","/images/**","/img/**","/js/**","/lang/**","/plugins/**","/pages/**","/assets/**"); //.anyRequest() ;
    }

    /**
     * 存储用户信息
     * @return
     */
    @Bean
    public CustomLoginSuccessHandler loginSuccessHandler(){
        return new CustomLoginSuccessHandler();
    }

}
