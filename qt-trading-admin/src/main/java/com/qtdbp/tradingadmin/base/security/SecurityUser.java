package com.qtdbp.tradingadmin.base.security;

import com.qtdbp.tradingadmin.base.security.entity.SysUsers;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * 系统用户
 */
public class SecurityUser extends SysUsers implements UserDetails {

    private static final long serialVersionUID = 1L;

    private Collection<GrantedAuthority>  authorities ;

    public SecurityUser(SysUsers suser) {

        if (suser != null)

        {

            this.setUserId(suser.getUserId());

            this.setName(suser.getName());

            this.setUsername(suser.getUsername());

            this.setPassword(suser.getPassword());

            this.setSysUsersRoleses(suser.getSysUsersRoleses());

            this.setEnabled(suser.isEnabled());

            this.setAccountNonExpired(suser.isAccountNonExpired());

            this.setAccountNonLocked(suser.isAccountNonExpired());

            this.setCredentialsNonExpired(suser.isCredentialsNonExpired());
        }

    }

    @Override
    public String getUserId() {
        return super.getUserId() ;
    }

    @Override
    public String getUsername() {
        return super.getUsername() ;
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public String getPassword() {
        return super.getPassword();
    }

    @Override
    public boolean isEnabled() {
        return super.isEnabled();
    }

    @Override
    public boolean isAccountNonExpired() {
        return super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return super.isCredentialsNonExpired();
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Collection<GrantedAuthority> authorities) {
        this.authorities = authorities;
    }
}