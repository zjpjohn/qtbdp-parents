package com.qtdbp.tradingadmin.base.security.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * 系统角色权限
 * SysRolesAuthorities entity.
 * @author caidchen
 */
@Entity
@Table(name = "SYS_ROLES_AUTHORITIES")
public class SysRolesAuthorities implements java.io.Serializable {

	// Fields

	private String id;
	private String authorityId;
	private String roleId;
	@JsonIgnore
	private SysAuthorities sysAuthorities;
	@JsonIgnore
	private SysRoles sysRoles;

	// Constructors

	/** default constructor */
	public SysRolesAuthorities() {
	}

	/** full constructor */
	public SysRolesAuthorities(String id, SysAuthorities sysAuthorities,
			SysRoles sysRoles) {
		this.id = id;
		this.sysAuthorities = sysAuthorities;
		this.sysRoles = sysRoles;
	}

	// Property accessors
	@Id
	@GenericGenerator(name = "uuid", strategy = "uuid")
	@GeneratedValue(generator = "uuid")
	@Column(name = "ID", unique = true, nullable = false, length = 100)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name="AUTHORITY_ID" ,length=200,insertable=false,updatable=false)
	public String getAuthorityId() {
		return authorityId;
	}

	public void setAuthorityId(String authorityId) {
		this.authorityId = authorityId;
	}

	@Column(name="ROLE_ID" ,length=200,insertable=false,updatable=false)
	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "AUTHORITY_ID", nullable = false)
	public SysAuthorities getSysAuthorities() {
		return this.sysAuthorities;
	}

	public void setSysAuthorities(SysAuthorities sysAuthorities) {
		this.sysAuthorities = sysAuthorities;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ROLE_ID", nullable = false)
	public SysRoles getSysRoles() {
		return this.sysRoles;
	}

	public void setSysRoles(SysRoles sysRoles) {
		this.sysRoles = sysRoles;
	}

}