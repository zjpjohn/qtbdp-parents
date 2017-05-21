package com.qtdbp.tradingadmin.base.security.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * 系统权限资源
 *
 * SysAuthoritiesResources entity.
 * @author caidchen
 */
@Entity
@Table(name = "SYS_AUTHORITIES_RESOURCES")
public class SysAuthoritiesResources implements java.io.Serializable {

	// Fields

	private String id;
	private String authorityId;
	private String resourceId;
	@JsonIgnore
	private SysAuthorities sysAuthorities;
	@JsonIgnore
	private SysResources sysResources;

	// Constructors

	/** default constructor */
	public SysAuthoritiesResources() {
	}

	/** full constructor */
	public SysAuthoritiesResources(String id, SysAuthorities sysAuthorities,
			SysResources sysResources) {
		this.id = id;
		this.sysAuthorities = sysAuthorities;
		this.sysResources = sysResources;
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

	@Column(name = "AUTHORITY_ID" ,insertable = false , updatable = false)
	public String getAuthorityId() {
		return authorityId;
	}

	public void setAuthorityId(String authorityId) {
		this.authorityId = authorityId;
	}

	@Column(name = "RESOURCE_ID" ,insertable = false , updatable = false)
	public String getResourceId() {
		return resourceId;
	}

	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
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
	@JoinColumn(name = "RESOURCE_ID", nullable = false)
	public SysResources getSysResources() {
		return this.sysResources;
	}

	public void setSysResources(SysResources sysResources) {
		this.sysResources = sysResources;
	}

}