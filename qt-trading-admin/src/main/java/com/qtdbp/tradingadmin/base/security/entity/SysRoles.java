package com.qtdbp.tradingadmin.base.security.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * 系统角色
 * SysRoles entity.
 * @author caidchen
 */
@Entity
@Table(name = "SYS_ROLES")
public class SysRoles implements java.io.Serializable {

	// Fields

	private String roleId;
	private String roleName;
	private String roleDesc;
	private Boolean enable;
	private Boolean issys;
	private String userId;
	private String moduleId;
	@JsonIgnore
	private Set<SysRolesModules> sysRolesMoudleses = new HashSet<SysRolesModules>(
			0);
	@JsonIgnore
	private Set<SysUsersRoles> sysUsersRoleses = new HashSet<SysUsersRoles>(0);
	@JsonIgnore
	private Set<SysRolesAuthorities> sysRolesAuthoritieses = new HashSet<SysRolesAuthorities>(
			0);

	// Constructors

	/** default constructor */
	public SysRoles() {
	}

	/** minimal constructor */
	public SysRoles(String roleId) {
		this.roleId = roleId;
	}

	/** full constructor */
	public SysRoles(String roleId, String roleName, String roleDesc,
			Boolean enable, Boolean issys, String moduleId,
			Set<SysRolesModules> sysRolesMoudleses,
			Set<SysUsersRoles> sysUsersRoleses,
			Set<SysRolesAuthorities> sysRolesAuthoritieses) {
		this.roleId = roleId;
		this.roleName = roleName;
		this.roleDesc = roleDesc;
		this.enable = enable;
		this.issys = issys;
		this.moduleId = moduleId;
		this.sysRolesMoudleses = sysRolesMoudleses;
		this.sysUsersRoleses = sysUsersRoleses;
		this.sysRolesAuthoritieses = sysRolesAuthoritieses;
	}

	// Property accessors
	@Id
	@GenericGenerator(name = "uuid", strategy = "uuid")
	@GeneratedValue(generator = "uuid")
	@Column(name = "ROLE_ID", unique = true, nullable = false, length = 100)
	public String getRoleId() {
		return this.roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	@Column(name = "ROLE_NAME", length = 100)
	public String getRoleName() {
		return this.roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	@Column(name = "ROLE_DESC", length = 200)
	public String getRoleDesc() {
		return this.roleDesc;
	}

	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}

	@Column(name = "ENABLE", precision = 22, scale = 0)
	public Boolean getEnable() {
		return this.enable;
	}

	public void setEnable(Boolean enable) {
		this.enable = enable;
	}

	@Column(name = "ISSYS", precision = 22, scale = 0)
	public Boolean getIssys() {
		return this.issys;
	}

	public void setIssys(Boolean issys) {
		this.issys = issys;
	}
	@Column(name = "USER_ID", length = 200)
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Column(name = "MODULE_ID", length = 100)
	public String getModuleId() {
		return this.moduleId;
	}

	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "sysRoles")
	public Set<SysRolesModules> getSysRolesMoudleses() {
		return this.sysRolesMoudleses;
	}

	public void setSysRolesMoudleses(Set<SysRolesModules> sysRolesMoudleses) {
		this.sysRolesMoudleses = sysRolesMoudleses;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "sysRoles")
	public Set<SysUsersRoles> getSysUsersRoleses() {
		return this.sysUsersRoleses;
	}

	public void setSysUsersRoleses(Set<SysUsersRoles> sysUsersRoleses) {
		this.sysUsersRoleses = sysUsersRoleses;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "sysRoles")
	public Set<SysRolesAuthorities> getSysRolesAuthoritieses() {
		return this.sysRolesAuthoritieses;
	}

	public void setSysRolesAuthoritieses(
			Set<SysRolesAuthorities> sysRolesAuthoritieses) {
		this.sysRolesAuthoritieses = sysRolesAuthoritieses;
	}

}