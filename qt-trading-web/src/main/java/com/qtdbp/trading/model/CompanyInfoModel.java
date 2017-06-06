package com.qtdbp.trading.model;

import java.util.Date;

/**
 * 企业资料Model
 *
 * @author: caidchen
 * @create: 2017-06-06 17:15
 * To change this template use File | Settings | File Templates.
 */
public class CompanyInfoModel extends BaseModel {

    private Integer institutionId ;             // 服务商ID
    private String companyName;                 // 公司名称
    private String companyLogo;                 // 公司logo
    private String companyIntro;                // 公司介绍
    private Integer industryId ;                // 所属行业
    private String license;                     // 营业执照
    private String licenseNumber;               // 营业执照注册号
    private Integer provinceId ;            // 所在省
    private Integer cityId ;                // 所在市
    private Integer districtId ;            // 所在地区
    private String address ;                // 详细地址
    private String phone ;                  // 手机号
    private String corporator;              // 企业法人
    private Integer createId;               // 添加人
    private Date createTime ;               // 添加时间
    private Integer editId ;                // 编辑人
    private Date editTime ;                 // 编辑时间
    private String auditor ;                // 审核人
    private Date auditTime;                 // 审核时间
    private Integer auditStatus ;           // 审核状态 0未审核 1审核通过 2审核不通过
    private Integer isUsed ;                // 是否可用 0否 1是

    public Integer getInstitutionId() {
        return institutionId;
    }

    public void setInstitutionId(Integer institutionId) {
        this.institutionId = institutionId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyLogo() {
        return companyLogo;
    }

    public void setCompanyLogo(String companyLogo) {
        this.companyLogo = companyLogo;
    }

    public String getCompanyIntro() {
        return companyIntro;
    }

    public void setCompanyIntro(String companyIntro) {
        this.companyIntro = companyIntro;
    }

    public Integer getIndustryId() {
        return industryId;
    }

    public void setIndustryId(Integer industryId) {
        this.industryId = industryId;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public Integer getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public Integer getDistrictId() {
        return districtId;
    }

    public void setDistrictId(Integer districtId) {
        this.districtId = districtId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCorporator() {
        return corporator;
    }

    public void setCorporator(String corporator) {
        this.corporator = corporator;
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

    public Integer getIsUsed() {
        return isUsed;
    }

    public void setIsUsed(Integer isUsed) {
        this.isUsed = isUsed;
    }
}
