package com.qtdbp.trading.service.security.support;

import com.qtdbp.trading.service.security.model.SysRole;
import com.qtdbp.trading.service.security.model.SysUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

/**
 *
 *
 * Created by dell on 2017/4/11.
 */
public class SecurityUser extends SysUser implements UserDetails {

    private static final long serialVersionUID = 1L;

    public SecurityUser(SysUser suser) {
        if(suser != null)
        {
            this.setId(suser.getId());
            // 如果nick为空，取账户名
            this.setUserName(suser.getUserName() == null ? suser.getRealName() : suser.getUserName());
            this.setPassword(suser.getPassword());
            this.setDate(suser.getDate());
            this.setHead(suser.getHead());
            this.setPhone(suser.getPhone());
            this.setRealName(suser.getRealName());
            this.setSysRoles(suser.getSysRoles());
            this.setSysPermissions(suser.getSysPermissions());
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        Set<SysRole> userRoles = this.getSysRoles();

        if(userRoles != null)
        {
            for (SysRole role : userRoles) {
                SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role.getCode());
                authorities.add(authority);
            }
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return super.getPassword();
    }

    @Override
    public String getUsername() {
        return super.getUserName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
