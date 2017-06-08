package com.qtdbp.trading.model;

import javax.persistence.Transient;
import java.util.Date;

/**
 *  爬虫规则model
 * Created by dell on 2017/6/5.
 */
public class CrawlersRoleModel extends BaseModel{

    private Integer typeId;            //所属类目ID
    private String roleType;           //规则类型
    private String name;                //规则名称
    private String introduction;        //规则简介
    private String logo;                //logo
    private Double marketPrice;        //市场价
    private Double price;               //价格
    private String desc;                //详情
    private Integer userRating;        //用户评分
    private Integer buyCount;          //购买数
    private Integer viewCount;         //浏览数
    private String filePath;           //文件路径
    private Integer webType;           //网站类型
    private String webSite;            //采集网站
    private String collectionField;    //采集字段
    private Integer isSelfSupport;    //是否自营
    private Integer isInstitution;     //是否服务商
    private Integer isGuarantee;       //交易过程担保
    private Integer isFullRefund;     //不满意全额退款
    private Integer isServiceSupervision;//服务全程监管
    private Integer isGreatInstitution;//优质服务商
    private int createId;          //添加人
    private Date createTime;           //添加时间
    private Integer editId;            //编辑人
    private Date editTime;             //编辑时间
    private String auditor;             //审核人
    private Date auditTime;            //审核时间
    private Integer auditStatus;       //审核状态
    private String auditFailReason;   //审核不通过原因
    private Integer isUsed;            //是否可用

    @Transient
    private String orderBy;            //排序字段

    @Transient
    private String username;               //创建规则的用户名

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getRoleType() {
        return roleType;
    }

    public void setRoleType(String roleType) {
        this.roleType = roleType;
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

    public Double getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(Double marketPrice) {
        this.marketPrice = marketPrice;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Integer getUserRating() {
        return userRating;
    }

    public void setUserRating(Integer userRating) {
        this.userRating = userRating;
    }

    public Integer getBuyCount() {
        return buyCount;
    }

    public void setBuyCount(Integer buyCount) {
        this.buyCount = buyCount;
    }

    public Integer getViewCount() {
        return viewCount;
    }

    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public Integer getWebType() {
        return webType;
    }

    public void setWebType(Integer webType) {
        this.webType = webType;
    }

    public String getWebSite() {
        return webSite;
    }

    public void setWebSite(String webSite) {
        this.webSite = webSite;
    }

    public String getCollectionField() {
        return collectionField;
    }

    public void setCollectionField(String collectionField) {
        this.collectionField = collectionField;
    }

    public Integer getIsSelfSupport() {
        return isSelfSupport;
    }

    public void setIsSelfSupport(Integer isSelfSupport) {
        this.isSelfSupport = isSelfSupport;
    }

    public Integer getIsInstitution() {
        return isInstitution;
    }

    public void setIsInstitution(Integer isInstitution) {
        this.isInstitution = isInstitution;
    }

    public Integer getIsGuarantee() {
        return isGuarantee;
    }

    public void setIsGuarantee(Integer isGuarantee) {
        this.isGuarantee = isGuarantee;
    }

    public Integer getIsFullRefund() {
        return isFullRefund;
    }

    public void setIsFullRefund(Integer isFullRefund) {
        this.isFullRefund = isFullRefund;
    }

    public Integer getIsServiceSupervision() {
        return isServiceSupervision;
    }

    public void setIsServiceSupervision(Integer isServiceSupervision) {
        this.isServiceSupervision = isServiceSupervision;
    }

    public Integer getIsGreatInstitution() {
        return isGreatInstitution;
    }

    public void setIsGreatInstitution(Integer isGreatInstitution) {
        this.isGreatInstitution = isGreatInstitution;
    }

    public int getCreateId() {
        return createId;
    }

    public void setCreateId(int createId) {
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
