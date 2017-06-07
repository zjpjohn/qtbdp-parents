package com.qtdbp.trading.model;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * 个人资料Model
 *
 * @author: caidchen
 * @create: 2017-06-06 17:05
 * To change this template use File | Settings | File Templates.
 */
public class PersonalInfoModel extends BaseModel {

    private String realName ;               // 真实姓名
    private String idNumber ;               // 身份证号码
    private String gender ;                 // 性别 男male，女female
    private String fullFacePhoto ;          // 身份证正面照
    private String negativeSidePhoto ;      // 身份证反面照
    private Date expTime ;                  // 身份证到期时间 null表示长期
    private Integer typeId ;                // 所属类目ID
    private Integer provinceId ;            // 所在省
    private Integer cityId ;                // 所在市
    private Integer districtId ;            // 所在地区
    private String address ;                // 详细地址
    private String phone ;                  // 手机号
    private Integer createId ;              // 添加人
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
    private String auditFailReason ;        // 审核不通过原因
    @ApiModelProperty(hidden=true)
    private Integer isUsed ;                // 是否可用 0否 1是

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getFullFacePhoto() {
        return fullFacePhoto;
    }

    public void setFullFacePhoto(String fullFacePhoto) {
        this.fullFacePhoto = fullFacePhoto;
    }

    public String getNegativeSidePhoto() {
        return negativeSidePhoto;
    }

    public void setNegativeSidePhoto(String negativeSidePhoto) {
        this.negativeSidePhoto = negativeSidePhoto;
    }

    public Date getExpTime() {
        return expTime;
    }

    public void setExpTime(Date expTime) {
        this.expTime = expTime;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
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
}
