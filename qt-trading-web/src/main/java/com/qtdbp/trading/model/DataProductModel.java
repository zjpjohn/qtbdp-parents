package com.qtdbp.trading.model;

import javax.persistence.Table;
import java.util.Date;

/**
 * 数据包产品Model
 *
 * @author: caidchen
 * @create: 2017-04-16 10:00
 * To change this template use File | Settings | File Templates.
 */
@Table(name="data_product")
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
    private Date addtime ;          // 添加时间
    private int editorId ;          // 编辑人id
    private Date editorTime ;       // 编辑时间
    private int sort ;              // 排序值
    private int rec ;               // 推荐值
    private int isUsed ;            // 是否可用
    private String typeChain ;      // 类型链
    private String dataTypeProps ;  // 数据类型属性串

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
}
