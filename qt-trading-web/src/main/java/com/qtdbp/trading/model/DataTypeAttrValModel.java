package com.qtdbp.trading.model;

/**
 * 数据类型属性值Model
 *
 * @author: caidchen
 * @create: 2017-04-15 14:26
 * To change this template use File | Settings | File Templates.
 */
public class DataTypeAttrValModel {

    private int id ;            // 属性值ID
    private int attrId ;        // 属性ID
    private String attrName ;   // 属性名称
    private String name ;       // 属性值名称
    private int sort ;          // 排序

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }
}
