package com.qtdbp.trading.config;

import com.qtdbp.trading.constants.SysRoleContants;
import com.qtdbp.trading.service.security.support.CustomUserDetailsService;
import com.qtdbp.trading.service.security.support.LoginSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.Filter;

/**
 * 钱塘交易中心应用权限框架配置
 *
 * @author: caidchen
 * @create: 2017-04-12 14:02
 * To change this template use File | Settings | File Templates.
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomUserDetailsService customUserDetailsService ;

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
        http
                .authorizeRequests()
                .antMatchers("/admin/**").hasAuthority(SysRoleContants.ROLE_ADMIN) //登陆后之后拥有“ADMIN”权限才可以访问/hello方法，否则系统会出现“403”权限不足的提示
                .antMatchers("/usercenter/**").hasAuthority(SysRoleContants.ROLE_USER) //登陆后之后拥有“ADMIN”权限才可以访问/hello方法，否则系统会出现“403”权限不足的提示
//                .anyRequest().authenticated() // 其他所有资源都需要认证，登陆后访问
                .and()
                .formLogin()
                    .loginPage("/login") //指定登录页是”/login”
                //.failureUrl("/error")
                .permitAll()
                .successHandler(loginSuccessHandler()) //登录成功后可使用loginSuccessHandler()存储用户信息，可选。
                .and()
                .logout().logoutSuccessUrl("/").permitAll() //退出登录后的默认网址是”/home”
                .invalidateHttpSession(true)
                .and()
                .csrf().disable() // 关闭crsf() 防止post请求405
                .rememberMe()//登录后记住用户，下次自动登录,数据库中必须存在名为persistent_logins的表
                .tokenValiditySeconds(1209600) ;
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        //指定密码加密所使用的加密器为passwordEncoder()
        //需要将密码加密后写入数据库
        auth.userDetailsService(customUserDetailsService) ;//.passwordEncoder(passwordEncoder());
        auth.eraseCredentials(false);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        //设置不拦截规则
        web.ignoring().antMatchers("classpath:resources/static/**");
    }

    /**
     * 对于密码做加密处理
     * @return
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(4);
    }

    /**
     * 存储用户信息
     * @return
     */
    @Bean
    public LoginSuccessHandler loginSuccessHandler(){
        return new LoginSuccessHandler();
    }

}
