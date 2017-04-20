package com.qtdbp.trading.model;


import java.util.Date;
import java.util.List;

/**
 * Created by dell on 2017/4/19.
 */
public class DataInstitutionInfoModel extends BaseModel{

    private Integer type;//类型(1.企业 2.个人)
    private String designation;//企业名称
    private Integer greaterRegionId;//大区ID
    private Integer provinceId;//省ID
    private Integer cityId;//市ID
    private String area;//地区
    private String address;//地址
    private Integer serviceType;//服务类型（1：数据预处理 2：数据建模 3：技术服务）
    private String abstracts;//公司介绍
    private String content;//服务说明和描述
    private String records;//交易记录
    private String evaluate;//交易评价
    private String ensure;//交易保障
    private String interview;//官方访谈
    private String picture;//公司图片
    private String contactsName;//联系人姓名
    private String contactsPhone;//联系人手机
    private String contactsEmail;//联系人邮箱
    private String contactsQQ;//联系人QQ
    private Integer userId;//添加人ID
    private Date addtime;//添加时间
    private Integer editorId;//
    private Date editorTime;
    private Integer sort;//排序值
    private Integer isUsed;//是否可用（0：不可用，1：可用）

    private Integer dataType;//数据类型id

    private List<DataTypeModel> typeName;//类型名称集合

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

    public String getContactsQQ() {
        return contactsQQ;
    }

    public void setContactsQQ(String contactsQQ) {
        this.contactsQQ = contactsQQ;
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
}
