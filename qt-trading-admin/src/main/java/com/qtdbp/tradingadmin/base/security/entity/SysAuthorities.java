package com.qtdbp.tradingadmin.base.security.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * 系统权限
 *
 * SysAuthorities entity.
 * @author caidchen
 */
@Entity
@Table(name = "SYS_AUTHORITIES")
public class SysAuthorities implements java.io.Serializable {

	// Fields

	private String authorityId;
	private String authorityMark;
	private String authorityName;
	private String authorityDesc;
	private String message;
	private Boolean enable;
	private Boolean issys;
	private String moduleId;
	private String remark;
	@JsonIgnore
	private Set<SysRolesAuthorities> sysRolesAuthoritieses = new HashSet<SysRolesAuthorities>(
			0);
	@JsonIgnore
	private Set<SysAuthoritiesResources> sysAuthoritiesResourceses = new HashSet<SysAuthoritiesResources>(
			0);

	// Constructors

	/** default constructor */
	public SysAuthorities() {
	}

	/** minimal constructor */
	public SysAuthorities(String authorityId) {
		this.authorityId = authorityId;
	}

	/** minimal constructor */
	public SysAuthorities(String authorityId, String authorityName) {
		this.authorityId = authorityId;
		this.authorityName = authorityName;
	}

	/** full constructor */
	public SysAuthorities(String authorityId, String authorityName,
			String authorityDesc, String message, Boolean enable,
			Boolean issys, String moduleId,
			Set<SysRolesAuthorities> sysRolesAuthoritieses,
			Set<SysAuthoritiesResources> sysAuthoritiesResourceses) {
		this.authorityId = authorityId;
		this.authorityName = authorityName;
		this.authorityDesc = authorityDesc;
		this.message = message;
		this.enable = enable;
		this.issys = issys;
		this.moduleId = moduleId;
		this.sysRolesAuthoritieses = sysRolesAuthoritieses;
		this.sysAuthoritiesResourceses = sysAuthoritiesResourceses;
	}

	// Property accessors
	@Id
	@GenericGenerator(name = "uuid", strategy = "uuid")
	@GeneratedValue(generator = "uuid")
	@Column(name = "AUTHORITY_ID", unique = true, nullable = false, length = 100)
	public String getAuthorityId() {
		return this.authorityId;
	}

	public void setAuthorityId(String authorityId) {
		this.authorityId = authorityId;
	}

	@Column(name = "AUTHORITY_MARK", nullable = false, length = 100)
	public String getAuthorityMark() {
		return authorityMark;
	}

	public void setAuthorityMark(String authorityMark) {
		this.authorityMark = authorityMark;
	}

	@Column(name = "AUTHORITY_NAME", nullable = false, length = 100)
	public String getAuthorityName() {
		return this.authorityName;
	}

	public void setAuthorityName(String authorityName) {
		this.authorityName = authorityName;
	}

	@Column(name = "AUTHORITY_DESC", length = 200)
	public String getAuthorityDesc() {
		return this.authorityDesc;
	}

	public void setAuthorityDesc(String authorityDesc) {
		this.authorityDesc = authorityDesc;
	}

	@Column(name = "MESSAGE", length = 100)
	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
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

	@Column(name = "MODULE_ID", length = 100)
	public String getModuleId() {
		return this.moduleId;
	}

	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}

	@Column(name = "REMARK", length = 100)
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "sysAuthorities")
	public Set<SysRolesAuthorities> getSysRolesAuthoritieses() {
		return this.sysRolesAuthoritieses;
	}

	public void setSysRolesAuthoritieses(
			Set<SysRolesAuthorities> sysRolesAuthoritieses) {
		this.sysRolesAuthoritieses = sysRolesAuthoritieses;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "sysAuthorities")
	public Set<SysAuthoritiesResources> getSysAuthoritiesResourceses() {
		return this.sysAuthoritiesResourceses;
	}

	public void setSysAuthoritiesResourceses(
			Set<SysAuthoritiesResources> sysAuthoritiesResourceses) {
		this.sysAuthoritiesResourceses = sysAuthoritiesResourceses;
	}

}