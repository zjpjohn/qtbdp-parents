package com.qtdbp.trading.model;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Transient;
import java.util.Date;
import java.util.HashMap;

/**
 * 新服务商Model
 *
 * @author: caidchen
 * @create: 2017-06-06 16:50
 * To change this template use File | Settings | File Templates.
 */
public class DataInstitutionInfoNewModel extends BaseModel {

    private Integer typeId;         // 所属类目ID
    private Integer institutionType ;// 服务商类型，1企业 2个人
    private String name;            // 服务商名称
    private String designation;     // 服务领域
    private String content;         // 服务说明和描述
    private String logo;            // 服务商logo
    private Integer infoId;         // 资料ID， 对应个人资料或者企业资料（通过服务商类型判断）
    private Integer isSys ;         // 是否自营 0否 1是
    private Integer isGreat ;       // 是否优质服务商 0否 1是
    private Integer createId;       // 添加人ID
    @ApiModelProperty(hidden=true)
    private Date createTime ;       // 添加时间
    @ApiModelProperty(hidden=true)
    private Integer editId ;        // 编辑人
    @ApiModelProperty(hidden=true)
    private Date editTime ;         // 编辑时间
    @ApiModelProperty(hidden=true)
    private Integer sort ;          // 排序值
    @ApiModelProperty(hidden=true)
    private String auditor ;        // 审核人
    @ApiModelProperty(hidden=true)
    private Date auditTime ;        // 审核时间
    @ApiModelProperty(hidden=true)
    private Integer auditStatus;    // 审核状态 0未审核 1审核通过 2审核不通过
    @ApiModelProperty(hidden=true)
    private Integer isUsed;         // 是否可用 0否 1是
    @ApiModelProperty(hidden=true)
    private String auditFailReason ;// 审核不通过原因

    private String orderBy ;    // 排序字段
    private String dataTypes ;  // 数据类型ID集合

    @Transient
    private PersonalInfoModel personalInfoModel ;   // 个人资料
    @Transient
    private CompanyInfoModel companyInfoModel ;     // 企业资料

    @Transient
    private HashMap<String, Long> countMap = new HashMap<String, Long>();    //数据包，数据定制，爬虫定制，爬虫规则定制数量map

    @Transient
    private String createName;      //创建人名字

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public Integer getInstitutionType() {
        return institutionType;
    }

    public void setInstitutionType(Integer institutionType) {
        this.institutionType = institutionType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public Integer getInfoId() {
        return infoId;
    }

    public void setInfoId(Integer infoId) {
        this.infoId = infoId;
    }

    public Integer getIsSys() {
        return isSys;
    }

    public void setIsSys(Integer isSys) {
        this.isSys = isSys;
    }

    public Integer getIsGreat() {
        return isGreat;
    }

    public void setIsGreat(Integer isGreat) {
        this.isGreat = isGreat;
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

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
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

    public Integer getIsUsed() {
        return isUsed;
    }

    public void setIsUsed(Integer isUsed) {
        this.isUsed = isUsed;
    }

    public String getAuditFailReason() {
        return auditFailReason;
    }

    public void setAuditFailReason(String auditFailReason) {
        this.auditFailReason = auditFailReason;
    }

    public PersonalInfoModel getPersonalInfoModel() {
        return personalInfoModel;
    }

    public void setPersonalInfoModel(PersonalInfoModel personalInfoModel) {
        this.personalInfoModel = personalInfoModel;
    }

    public CompanyInfoModel getCompanyInfoModel() {
        return companyInfoModel;
    }

    public void setCompanyInfoModel(CompanyInfoModel companyInfoModel) {
        this.companyInfoModel = companyInfoModel;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public HashMap<String, Long> getCountMap() {
        return countMap;
    }

    public void setCountMap(HashMap<String, Long> countMap) {
        this.countMap = countMap;
    }

    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }
}
