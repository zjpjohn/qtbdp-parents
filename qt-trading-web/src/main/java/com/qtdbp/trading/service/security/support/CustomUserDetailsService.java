package com.qtdbp.trading.service.security.support;

import com.qtdbp.trading.mapper.SysUserMapper;
import com.qtdbp.trading.service.security.model.SysRole;
import com.qtdbp.trading.service.security.model.SysUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.Set;

/**
 * 自定义用户登录策略
 * Created by dell on 2017/4/11.
 */
@Component
public class CustomUserDetailsService implements UserDetailsService {

    private Logger logger = LoggerFactory.getLogger(this.getClass()) ;

    @Autowired
    private SysUserMapper userService;
    @Autowired
    private HttpServletRequest request ;

    /**
     * 参考地址: http://blog.csdn.net/u012373815/article/details/54633046
     * @param userName
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        /*
          SysUser对应数据库中的用户表，是最终存储用户和密码的表，可自定义
          本例使用SysUser中的name作为用户名:
         */

        logger.info("系统用户ID: " + userName);

        SysUser user = userService.findUserBySsoId(userName) ;
        if (user == null) {
            throw new UsernameNotFoundException("用户 " + userName + " 不存在");
        }
        buildSysUser(user) ;

        // SecurityUser实现UserDetails并将SysUser的name映射为username
        return new SecurityUser(user);
    }

    /**
     * 组装权限数据
     * @param user
     * @return
     */
    private SysUser buildSysUser(SysUser user) {
        user.setPassword("123456"); //123456
        Set<SysRole> sysRoles = new HashSet<SysRole>();
        SysRole sysRole = new SysRole() ;
        sysRole.setName("USER"); // ADMIN
        sysRole.setCode("USER"); // ADMIN
        sysRoles.add(sysRole) ;
        user.setSysRoles(sysRoles);

        return user ;
    }

    /**
     * 测试数据
     * @return
     */
    private static SysUser testSysUser() {
        SysUser user = new SysUser() ;
        user.setPassword("123456"); //123456
        user.setUserName("admin");
        Set<SysRole> sysRoles = new HashSet<SysRole>();
        SysRole sysRole = new SysRole() ;
        sysRole.setName("USER"); // ADMIN
        sysRole.setCode("USER"); // ADMIN
        sysRoles.add(sysRole) ;
        user.setSysRoles(sysRoles);

        return user ;
    }
}
