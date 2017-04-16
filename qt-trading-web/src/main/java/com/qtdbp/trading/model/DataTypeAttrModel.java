package com.qtdbp.trading.model;

import java.util.List;

/**
 * 数据类型属性Model
 *
 * @author: caidchen
 * @create: 2017-04-15 14:26
 * To change this template use File | Settings | File Templates.
 */
public class DataTypeAttrModel {

    private int id ;    // 属性ID
    private int tid ;   // 数据类型ID
    private String name ;   // 属性名称
    private int isMust ;    // 是否必填
    private int isMulti ;   // 是否多选
    private int sort ;      // 排序

    private List<DataTypeAttrValModel> attrVals ; // 属性值列表

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIsMust() {
        return isMust;
    }

    public void setIsMust(int isMust) {
        this.isMust = isMust;
    }

    public int getIsMulti() {
        return isMulti;
    }

    public void setIsMulti(int isMulti) {
        this.isMulti = isMulti;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public List<DataTypeAttrValModel> getAttrVals() {
        return attrVals;
    }

    public void setAttrVals(List<DataTypeAttrValModel> attrVals) {
        this.attrVals = attrVals;
    }
}
