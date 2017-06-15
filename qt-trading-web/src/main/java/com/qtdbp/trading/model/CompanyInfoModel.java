package com.qtdbp.trading.model;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * 企业资料Model
 *
 * @author: caidchen
 * @create: 2017-06-06 17:15
 * To change this template use File | Settings | File Templates.
 */
public class CompanyInfoModel extends BaseModel {

    private String companyName;                 // 公司名称
    private String companyLogo;                 // 公司logo
    private String companyIntro;                // 公司介绍
    private Integer industryId ;                // 所属行业
    private String license;                     // 营业执照
    private String licenseNumber;               // 营业执照注册号
    private String provinceId ;            // 所在省
    private String cityId ;                // 所在市
    private String districtId ;            // 所在地区
    private String address ;                // 详细地址
    private String phone ;                  // 手机号
    private String corporator;              // 企业法人
    private Integer createId;               // 添加人
    @ApiModelProperty(hidden=true)
    private Date createTime ;               // 添加时间
    @ApiModelProperty(hidden=true)
    private Integer editId ;                // 编辑人
    @ApiModelProperty(hidden=true)
    private Date editTime ;                 // 编辑时间
    @ApiModelProperty(hidden=true)
    private String auditor ;                // 审核人
    @ApiModelProperty(hidden=true)
    private Date auditTime;                 // 审核时间
    @ApiModelProperty(hidden=true)
    private Integer auditStatus ;           // 审核状态 0未审核 1审核通过 2审核不通过
    @ApiModelProperty(hidden=true)
    private Integer isUsed ;                // 是否可用 0否 1是

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

    public String getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(String provinceId) {
        this.provinceId = provinceId;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getDistrictId() {
        return districtId;
    }

    public void setDistrictId(String districtId) {
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
