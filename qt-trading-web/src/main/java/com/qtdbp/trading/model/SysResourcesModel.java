package com.qtdbp.trading.model;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Transient;

/**
 * 资源model
 * Created by dell on 2017/6/21.
 */
public class SysResourcesModel {

    private String resourceId;
    private Integer enable;
    private String httpMethod;
    private Integer issys;
    private String moduleId;
    private int priority;
    private String resourceDesc;
    private String resourceName;
    private String resourcePath;
    private String resourceType;

    @ApiModelProperty(hidden=true)
    @Transient
    private Integer page = 1;

    @ApiModelProperty(hidden=true)
    @Transient
    private Integer rows ;

    private Integer seoId;  //seo数据ID

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    public Integer getEnable() {
        return enable;
    }

    public void setEnable(Integer enable) {
        this.enable = enable;
    }

    public String getHttpMethod() {
        return httpMethod;
    }

    public void setHttpMethod(String httpMethod) {
        this.httpMethod = httpMethod;
    }

    public Integer getIssys() {
        return issys;
    }

    public void setIssys(Integer issys) {
        this.issys = issys;
    }

    public String getModuleId() {
        return moduleId;
    }

    public void setModuleId(String moduleId) {
        this.moduleId = moduleId;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getResourceDesc() {
        return resourceDesc;
    }

    public void setResourceDesc(String resourceDesc) {
        this.resourceDesc = resourceDesc;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public String getResourcePath() {
        return resourcePath;
    }

    public void setResourcePath(String resourcePath) {
        this.resourcePath = resourcePath;
    }

    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }

    public Integer getSeoId() {
        return seoId;
    }

    public void setSeoId(Integer seoId) {
        this.seoId = seoId;
    }
}
