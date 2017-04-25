package com.qtdbp.trading.service.security.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * 系统用户
 *
 * Created by dell on 2017/4/11.
 */
public class SysUser implements java.io.Serializable {

    private Integer id;
    private String userName; //用户名
    @JsonIgnore
    private String password; //用户密码
    private String head ;    // 头像
    private Date date;       //时间

    @JsonIgnore
    private Set<SysRole> sysRoles = new HashSet<>(0);// 所对应的角色集合
    @JsonIgnore
    private Set<SysPermission> sysPermissions = new HashSet<>(0);// 所对应的权限集合

    public SysUser(String userName, String password, Date date, String head, Set<SysRole> sysRoles, Set<SysPermission> sysPermissions) {
        this.userName = userName;
        this.password = password;
        this.date = date;
        this.head = head ;
        this.sysRoles = sysRoles;
        this.sysPermissions = sysPermissions ;
    }

    public SysUser() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Set<SysRole> getSysRoles() {
        return this.sysRoles;
    }

    public void setSysRoles(Set<SysRole> sysRoles) {
        this.sysRoles = sysRoles;
    }

    public Set<SysPermission> getSysPermissions() {
        return sysPermissions;
    }

    public void setSysPermissions(Set<SysPermission> sysPermissions) {
        this.sysPermissions = sysPermissions;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }
}
