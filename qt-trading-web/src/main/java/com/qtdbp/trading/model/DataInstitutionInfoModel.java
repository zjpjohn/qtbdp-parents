package com.qtdbp.trading.model;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.web.bind.annotation.RequestAttribute;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Date;
import java.util.List;

/**
 * Created by dell on 2017/4/19.
 */
public class DataInstitutionInfoModel extends BaseModel{

    @ApiModelProperty(value = "身份类型（1政府、2机构、3企业、4个人、5其他）")
    private Integer type;//类型(1.企业 2.个人)
    @ApiModelProperty(value = "企业名称")
    private String designation;//企业名称
    @ApiModelProperty(hidden=true)
    private Integer greaterRegionId;//大区ID
    @ApiModelProperty(value = "省ID",hidden=true)
    private Integer provinceId;//省ID
    @ApiModelProperty(value = "市ID",hidden=true)
    private Integer cityId;//市ID
    @ApiModelProperty(value = "省")
    private String province;//省
    @ApiModelProperty(value = "市")
    private String city;//市
    @ApiModelProperty(hidden=true)
    private String area;//地区
    @ApiModelProperty(hidden=true)
    private String address;//地址
    @ApiModelProperty(value = "服务类型（1：数据预处理 2：数据建模 3：技术服务）")
    private Integer serviceType;//服务类型（1：数据预处理 2：数据建模 3：技术服务）
    @ApiModelProperty(value = "公司介绍")
    private String abstracts;//公司介绍
    @ApiModelProperty(hidden=true)
    private String content;//服务说明和描述
    @ApiModelProperty(hidden=true)
    private String records;//交易记录
    @ApiModelProperty(hidden=true)
    private String evaluate;//交易评价
    @ApiModelProperty(hidden=true)
    private String ensure;//交易保障
    @ApiModelProperty(hidden=true)
    private String interview;//官方访谈
    @ApiModelProperty(value = "公司图片")
    private String picture;//公司图片
    @ApiModelProperty(value = "联系人姓名")
    private String contactsName;//联系人姓名
    @ApiModelProperty(value = "联系人手机")
    private String contactsPhone;//联系人手机
    @ApiModelProperty(value = "联系人邮箱")
    private String contactsEmail;//联系人邮箱
    @ApiModelProperty(value = "联系人QQ")
    private String contactsQq;//联系人QQ
    @ApiModelProperty(value = "添加人ID")
    private Integer userId;//添加人ID
    @ApiModelProperty(hidden=true)
    private Date addtime;//添加时间
    @ApiModelProperty(hidden=true)
    private Integer editorId;//
    @ApiModelProperty(hidden=true)
    private Date editorTime;
    @ApiModelProperty(hidden=true)
    private Integer sort;//排序值
    @ApiModelProperty(hidden=true)
    private Integer isUsed;//服务商状态（0待审核、1审核通过、2审核失败）

    @ApiModelProperty(hidden=true)
    private Integer dataType;//数据类型id

    @ApiModelProperty(hidden=true)
    private List<DataTypeModel> typeName;//类型名称集合
    private List<DataInstitutionTypeRelationModel> relationModels ; // 服务商类型集合

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public Integer getGreaterRegionId() {
        return greaterRegionId;
    }

    public void setGreaterRegionId(Integer greaterRegionId) {
        this.greaterRegionId = greaterRegionId;
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

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getServiceType() {
        return serviceType;
    }

    public void setServiceType(Integer serviceType) {
        this.serviceType = serviceType;
    }

    public String getAbstracts() {
        return abstracts;
    }

    public void setAbstracts(String abstracts) {
        this.abstracts = abstracts;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getRecords() {
        return records;
    }

    public void setRecords(String records) {
        this.records = records;
    }

    public String getEvaluate() {
        return evaluate;
    }

    public void setEvaluate(String evaluate) {
        this.evaluate = evaluate;
    }

    public String getEnsure() {
        return ensure;
    }

    public void setEnsure(String ensure) {
        this.ensure = ensure;
    }

    public String getInterview() {
        return interview;
    }

    public void setInterview(String interview) {
        this.interview = interview;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getContactsName() {
        return contactsName;
    }

    public void setContactsName(String contactsName) {
        this.contactsName = contactsName;
    }

    public String getContactsPhone() {
        return contactsPhone;
    }

    public void setContactsPhone(String contactsPhone) {
        this.contactsPhone = contactsPhone;
    }

    public String getContactsEmail() {
        return contactsEmail;
    }

    public void setContactsEmail(String contactsEmail) {
        this.contactsEmail = contactsEmail;
    }

    public String getContactsQq() {
        return contactsQq;
    }

    public void setContactsQq(String contactsQq) {
        this.contactsQq = contactsQq;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }

    public Integer getEditorId() {
        return editorId;
    }

    public void setEditorId(Integer editorId) {
        this.editorId = editorId;
    }

    public Date getEditorTime() {
        return editorTime;
    }

    public void setEditorTime(Date editorTime) {
        this.editorTime = editorTime;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getIsUsed() {
        return isUsed;
    }

    public void setIsUsed(Integer isUsed) {
        this.isUsed = isUsed;
    }

    public Integer getDataType() {
        return dataType;
    }

    public void setDataType(Integer dataType) {
        this.dataType = dataType;
    }

    public List<DataTypeModel> getTypeName() {
        return typeName;
    }

    public void setTypeName(List<DataTypeModel> typeName) {
        this.typeName = typeName;
    }

    public List<DataInstitutionTypeRelationModel> getRelationModels() {
        return relationModels;
    }

    public void setRelationModels(List<DataInstitutionTypeRelationModel> relationModels) {
        this.relationModels = relationModels;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
