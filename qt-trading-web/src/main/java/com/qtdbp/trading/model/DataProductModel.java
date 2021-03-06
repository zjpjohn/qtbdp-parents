package com.qtdbp.trading.model;

import com.google.common.collect.Lists;

import javax.persistence.Transient;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 数据包产品Model
 *
 * @author: caidchen
 * @create: 2017-04-16 10:00
 * To change this template use File | Settings | File Templates.
 */
//@Table(name="data_product")
public class DataProductModel extends BaseModel {

    private String designation ;    // 产品名称
    private String introduce ;      // 产品介绍
    private String pic ;            // 产品图片
    private int dataScale ;         // 数据规模（条）
    private int dataType ;          // 数据大类别
    private int dataTypeSub ;       // 数据小类别
    private int score ;             // 积分数量（每一条数据对应积分）
    private int pScore ;            // 积分数量（产品包积分
    private String fileUrl ;        // 数据包路径
    private int provinceId ;        // 省份id
    private int isHuadong ;         // 是否华东
    private Integer userId ;            // 添加人
    private Date addtime ;          // 添加时间
    private int editorId ;          // 编辑人id
    private Date editorTime ;       // 编辑时间
    private int sort ;              // 排序值
    private int rec ;               // 推荐值
    private Integer isUsed ;            // 是否可用
    private String typeChain ;      // 类型链
    private int downloadCount ;     // 下载次数
    private String dataTypeProps ;  // 数据类型属性串pname:vname:pid:vid;pname1:vname1:pid1:vid1
    private double price;
    private double itemPrice;
    private String dataTypePath;    //数据类目路径
    private String dataProfile;     //产品简介
    private Double marketPrice;     //市场价
    private String dataFormat;      //数据格式
    private Integer dataStatus;     //数据类型
    private Integer dataSrc;        //数据来源
    private String auditor;         //审核人
    private Date auditTime;         //审核时间
    private Integer auditStatus;    //审核状态
    private String auditFailReason; //审核失败原因


    private int attrId ;        // 属性ID
    private String attrName ;   // 属性名称
    private int valId ;         // 属性值Id
    private String valName ;    // 属性值名称
    private int buyCount ;      // 购买次数
    private String orderBy ;    //排序字段

    private String valIds ;     // 请求参数：属性值ID列表，如：1

    private String valSqls ;    // 动态sql

    private String dataTypes ;  // 数据类型ID集合

    @Transient
    private List<DataProductAttrRelationModel> attrRelationModels = Lists.newArrayList();

    @Transient
    private DataTypeModel dataTypeModel;

    @Transient
    private String username;    //登录用户的名称

    @Transient
    private Map<String, String> subFiles = new HashMap<>();  //拆分后的数据条目

    private Integer dataSize;   //文件大小

    private Integer isFree;     //是否免费

    private String typeName;    //类目名字

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public int getDataScale() {
        return dataScale;
    }

    public void setDataScale(int dataScale) {
        this.dataScale = dataScale;
    }

    public int getDataType() {
        return dataType;
    }

    public void setDataType(int dataType) {
        this.dataType = dataType;
    }

    public int getDataTypeSub() {
        return dataTypeSub;
    }

    public void setDataTypeSub(int dataTypeSub) {
        this.dataTypeSub = dataTypeSub;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getpScore() {
        return pScore;
    }

    public void setpScore(int pScore) {
        this.pScore = pScore;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public int getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(int provinceId) {
        this.provinceId = provinceId;
    }

    public int getIsHuadong() {
        return isHuadong;
    }

    public void setIsHuadong(int isHuadong) {
        this.isHuadong = isHuadong;
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

    public int getEditorId() {
        return editorId;
    }

    public void setEditorId(int editorId) {
        this.editorId = editorId;
    }

    public Date getEditorTime() {
        return editorTime;
    }

    public void setEditorTime(Date editorTime) {
        this.editorTime = editorTime;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public int getRec() {
        return rec;
    }

    public void setRec(int rec) {
        this.rec = rec;
    }

    public Integer getIsUsed() {
        return isUsed;
    }

    public void setIsUsed(Integer isUsed) {
        this.isUsed = isUsed;
    }

    public String getTypeChain() {
        return typeChain;
    }

    public void setTypeChain(String typeChain) {
        this.typeChain = typeChain;
    }

    public String getDataTypeProps() {
        return dataTypeProps;
    }

    public void setDataTypeProps(String dataTypeProps) {
        this.dataTypeProps = dataTypeProps;
    }

    public int getAttrId() {
        return attrId;
    }

    public void setAttrId(int attrId) {
        this.attrId = attrId;
    }

    public String getAttrName() {
        return attrName;
    }

    public void setAttrName(String attrName) {
        this.attrName = attrName;
    }

    public int getValId() {
        return valId;
    }

    public void setValId(int valId) {
        this.valId = valId;
    }

    public String getValName() {
        return valName;
    }

    public void setValName(String valName) {
        this.valName = valName;
    }

    public int getBuyCount() {
        return buyCount;
    }

    public void setBuyCount(int buyCount) {
        this.buyCount = buyCount;
    }

    public String getValIds() {
        return valIds;
    }

    public void setValIds(String valIds) {
        this.valIds = valIds;
    }

    public int getDownloadCount() {
        return downloadCount;
    }

    public void setDownloadCount(int downloadCount) {
        this.downloadCount = downloadCount;
    }

    public String getValSqls() {

        String val = null ;
        if(valIds != null) {
            String[] valIdArray =  valIds.split(",") ;
            StringBuilder sqls = new StringBuilder() ;

            boolean first = false;
            for (String valId : valIdArray) {

                if(sqls.length() > 0) {
                    first = true ;
                    sqls.append(" AND product_id IN (") ;
                }
                sqls.append("SELECT product_id FROM data_product_attr_relation WHERE val_id = "+valId) ;

                if(first) sqls.append(")") ;
            }

            val = sqls.toString() ;
        }

        return val;
    }

    public void setValSqls(String valSqls) {
        this.valSqls = valSqls;
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

    public List<DataProductAttrRelationModel> getAttrRelationModels() {
        return attrRelationModels;
    }

    public void setAttrRelationModels(List<DataProductAttrRelationModel> attrRelationModels) {
        this.attrRelationModels = attrRelationModels;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public DataTypeModel getDataTypeModel() {
        return dataTypeModel;
    }

    public void setDataTypeModel(DataTypeModel dataTypeModel) {
        this.dataTypeModel = dataTypeModel;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Map<String, String> getSubFiles() {
        return subFiles;
    }

    public void setSubFiles(Map<String, String> subFiles) {
        this.subFiles = subFiles;
    }

    public Integer getDataSize() {
        return dataSize;
    }

    public void setDataSize(Integer dataSize) {
        this.dataSize = dataSize;
    }

    public String getDataTypePath() {
        return dataTypePath;
    }

    public void setDataTypePath(String dataTypePath) {
        this.dataTypePath = dataTypePath;
    }

    public String getDataProfile() {
        return dataProfile;
    }

    public void setDataProfile(String dataProfile) {
        this.dataProfile = dataProfile;
    }

    public Double getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(Double marketPrice) {
        this.marketPrice = marketPrice;
    }

    public String getDataFormat() {
        return dataFormat;
    }

    public void setDataFormat(String dataFormat) {
        this.dataFormat = dataFormat;
    }

    public Integer getDataStatus() {
        return dataStatus;
    }

    public void setDataStatus(Integer dataStatus) {
        this.dataStatus = dataStatus;
    }

    public Integer getDataSrc() {
        return dataSrc;
    }

    public void setDataSrc(Integer dataSrc) {
        this.dataSrc = dataSrc;
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

    public Integer getIsFree() {
        return isFree;
    }

    public void setIsFree(Integer isFree) {
        this.isFree = isFree;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}


