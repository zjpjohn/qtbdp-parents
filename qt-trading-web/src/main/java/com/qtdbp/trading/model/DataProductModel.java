package com.qtdbp.trading.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.Lists;
import jdk.nashorn.internal.ir.annotations.Ignore;

import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    private int userId ;            // 添加人
    private Date addTime ;          // 添加时间
    private int editorId ;          // 编辑人id
    private Date editorTime ;       // 编辑时间
    private int sort ;              // 排序值
    private int rec ;               // 推荐值
    private int isUsed ;            // 是否可用
    private String typeChain ;      // 类型链
    private int downloadCount ;     // 下载次数
    private String dataTypeProps ;  // 数据类型属性串pname:vname:pid:vid;pname1:vname1:pid1:vid1
    private double price;
    private double itemPrice;

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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
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

    public int getIsUsed() {
        return isUsed;
    }

    public void setIsUsed(int isUsed) {
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
}


