package com.qtdbp.tradingadmin.base.security.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * 系统模块
 *
 * SysModules entity.
 * @author caidchen
 */
@Entity
@Table(name = "SYS_MODULES")
public class SysModules implements java.io.Serializable {

	// Fields

	private String moduleId;
	private String moduleName;
	private String moduleDesc;
	private String moduleType;
	private String parent;
	private String moduleUrl;
	private Integer ILevel;
	private Boolean leaf;
	private String application;
	private String controller;
	private Boolean enable;
	private Boolean issys;
	private Integer priority;
	@JsonIgnore
	private Set<SysResources> sysResourceses = new HashSet<SysResources>(0);
	@JsonIgnore
	private Set<SysRolesModules> sysRolesMoudleses = new HashSet<SysRolesModules>(
			0);

	// Constructors

	/** default constructor */
	public SysModules() {
	}
	/** minimal constructor */
	public SysModules(String moduleId) {
		this.moduleId = moduleId;
	}

	/** minimal constructor */
	public SysModules(String moduleId, String moduleName) {
		this.moduleId = moduleId;
		this.moduleName = moduleName;
	}

	/** full constructor */
	public SysModules(String moduleId, String moduleName, String moduleDesc,
			String moduleType, String parent, String moduleUrl,
			Integer ILevel, Boolean leaf, String application,
			String controller, Set<SysResources> sysResourceses,
			Set<SysRolesModules> sysRolesMoudleses) {
		this.moduleId = moduleId;
		this.moduleName = moduleName;
		this.moduleDesc = moduleDesc;
		this.moduleType = moduleType;
		this.parent = parent;
		this.moduleUrl = moduleUrl;
		this.ILevel = ILevel;
		this.leaf = leaf;
		this.application = application;
		this.controller = controller;
		this.sysResourceses = sysResourceses;
		this.sysRolesMoudleses = sysRolesMoudleses;
	}

	// Property accessors
	@Id
	@GenericGenerator(name = "uuid", strategy = "uuid")
	@GeneratedValue(generator = "uuid")
	@Column(name = "MODULE_ID", unique = true, nullable = false, length = 100)
	public String getModuleId() {
		return this.moduleId;
	}

	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}

	@Column(name = "MODULE_NAME", nullable = false, length = 100)
	public String getModuleName() {
		return this.moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	@Column(name = "MODULE_DESC", length = 200)
	public String getModuleDesc() {
		return this.moduleDesc;
	}

	public void setModuleDesc(String moduleDesc) {
		this.moduleDesc = moduleDesc;
	}

	@Column(name = "MODULE_TYPE", length = 100)
	public String getModuleType() {
		return this.moduleType;
	}

	public void setModuleType(String moduleType) {
		this.moduleType = moduleType;
	}

	@Column(name = "PARENT", length = 100)
	public String getParent() {
		return this.parent;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}

	@Column(name = "MODULE_URL", length = 100)
	public String getModuleUrl() {
		return this.moduleUrl;
	}

	public void setModuleUrl(String moduleUrl) {
		this.moduleUrl = moduleUrl;
	}

	@Column(name = "I_LEVEL", precision = 22, scale = 0)
	public Integer getILevel() {
		return this.ILevel;
	}

	public void setILevel(Integer ILevel) {
		this.ILevel = ILevel;
	}

	@Column(name = "LEAF", precision = 22, scale = 0)
	public Boolean getLeaf() {
		return this.leaf;
	}

	public void setLeaf(Boolean leaf) {
		this.leaf = leaf;
	}

	@Column(name = "ISSYS")
	public Boolean getIssys() {
		return issys;
	}
	public void setIssys(Boolean issys) {
		this.issys = issys;
	}

	@Column(name = "APPLICATION", length = 100)
	public String getApplication() {
		return this.application;
	}

	public void setApplication(String application) {
		this.application = application;
	}

	@Column(name = "CONTROLLER", length = 100)
	public String getController() {
		return this.controller;
	}

	public void setController(String controller) {
		this.controller = controller;
	}

	@Column(name = "ENABLE")
	public Boolean getEnable() {
		return enable;
	}

	public void setEnable(Boolean enable) {
		this.enable = enable;
	}

	@Column(name = "PRIORITY")
	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "sysModules")
	public Set<SysResources> getSysResourceses() {
		return this.sysResourceses;
	}

	public void setSysResourceses(Set<SysResources> sysResourceses) {
		this.sysResourceses = sysResourceses;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "sysModules")
	public Set<SysRolesModules> getSysRolesMoudleses() {
		return this.sysRolesMoudleses;
	}

	public void setSysRolesMoudleses(Set<SysRolesModules> sysRolesMoudleses) {
		this.sysRolesMoudleses = sysRolesMoudleses;
	}

}