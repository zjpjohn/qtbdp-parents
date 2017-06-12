package com.qtdbp.trading.model;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * 定制服务Model
 *
 * @author: caidchen
 * @create: 2017-06-06 11:20
 * To change this template use File | Settings | File Templates.
 */
public class CustomServiceModel extends BaseModel {

    private Integer typeId ;            // 所属类目ID
    private String name ;           // 名称
    private String introduction ;   // 简介
    private String logo ;           // logo
    private Double price ;          // 预算
    private Integer bargaining;         // 是否可议价 0否 1是
    private Integer serviceType ;       // 服务类型 1 规则定制 2 数据定制
    private String desc ;           // 需求描述
    @ApiModelProperty(hidden=true)
    private Integer status ;            // 需求状态 0发布需求1需求审核2服务商交稿3雇主选稿4中标公示5验收并付款6评价
    private Date beginTime ;        // 征集开始时间
    private Date endTime ;          // 征集截止时间
    private String dimension ;      // 维度/字段
    private Integer scale ;             // 规模
    private String website ;        // 网站
    private String phone ;          // 联系手机
    private String qq ;             // 联系QQ
    private Integer createId ;          // 添加人
    @ApiModelProperty(hidden=true)
    private Date createTime ;       // 添加时间
    @ApiModelProperty(hidden=true)
    private Integer editId ;            // 编辑人
    @ApiModelProperty(hidden=true)
    private Date editTime ;         // 编辑时间
    @ApiModelProperty(hidden=true)
    private String auditor ;        // 审核人
    @ApiModelProperty(hidden=true)
    private Date auditTime ;        // 审核时间
    @ApiModelProperty(hidden=true)
    private Integer auditStatus ;       // 审核状态 0未审核1审核成功2审核失败
    @ApiModelProperty(hidden=true)
    private String auditFailReason ;// 审核不通过原因
    @ApiModelProperty(hidden=true)
    private Integer isUsed;             // 是否可用

    private String orderBy;             //排序字段

    private String dataTypes;           //数据类目过滤集合

    private String typeName;            //数据类目名称

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getBargaining() {
        return bargaining;
    }

    public void setBargaining(Integer bargaining) {
        this.bargaining = bargaining;
    }

    public Integer getServiceType() {
        return serviceType;
    }

    public void setServiceType(Integer serviceType) {
        this.serviceType = serviceType;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getDimension() {
        return dimension;
    }

    public void setDimension(String dimension) {
        this.dimension = dimension;
    }

    public Integer getScale() {
        return scale;
    }

    public void setScale(Integer scale) {
        this.scale = scale;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public Integer getCreateId() {
        return createId;
    }

    public void setCreateId(Integer createId) {
        this.createId = createId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getEditId() {
        return editId;
    }

    public void setEditId(Integer editId) {
        this.editId = editId;
    }

    public Date getEditTime() {
        return editTime;
    }

    public void setEditTime(Date editTime) {
        this.editTime = editTime;
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

    public Integer getIsUsed() {
        return isUsed;
    }

    public void setIsUsed(Integer isUsed) {
        this.isUsed = isUsed;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public String getDataTypes() {
        return dataTypes;
    }

    public void setDataTypes(String dataTypes) {
        this.dataTypes = dataTypes;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
