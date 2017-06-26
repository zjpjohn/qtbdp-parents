package com.qtdbp.trading.model;

import java.util.Date;

/**
 * Created by dell on 2017/6/22.
 */
public class ApiDemandModel extends BaseModel{

    private String apiName;         //接口名称
    private Integer categoryId;     //所在api接口类目
    private String apiDomain;       //所属行业
    private String apiDesc;         //接口需求描述
    private Double apiPrice;        //需求预算
    private String apiField;        //返回字段
    private String contacts;        //联系人
    private String phone;           //联系电话
    private Date createTime;        //联系电话
    private String auditor;         //审核人
    private Date auditTime;         //审核时间
    private Integer auditStatus;    //审核状态
    private String auditFailReason;//审核不通过原因

    public String getApiName() {
        return apiName;
    }

    public void setApiName(String apiName) {
        this.apiName = apiName;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getApiDomain() {
        return apiDomain;
    }

    public void setApiDomain(String apiDomain) {
        this.apiDomain = apiDomain;
    }

    public String getApiDesc() {
        return apiDesc;
    }

    public void setApiDesc(String apiDesc) {
        this.apiDesc = apiDesc;
    }

    public Double getApiPrice() {
        return apiPrice;
    }

    public void setApiPrice(Double apiPrice) {
        this.apiPrice = apiPrice;
    }

    public String getApiField() {
        return apiField;
    }

    public void setApiField(String apiField) {
        this.apiField = apiField;
    }

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getAuditor() {
        return auditor;
    }

    public void setAuditor(String auditor) {
        this.auditor = auditor;
    }

    public Date getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(Date auditTime) {
        this.auditTime = auditTime;
    }

    public Integer getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(Integer auditStatus) {
        this.auditStatus = auditStatus;
    }

    public String getAuditFailReason() {
        return auditFailReason;
    }

    public void setAuditFailReason(String auditFailReason) {
        this.auditFailReason = auditFailReason;
    }
}
