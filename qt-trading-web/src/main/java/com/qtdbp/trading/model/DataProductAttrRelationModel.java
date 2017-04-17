package com.qtdbp.trading.model;

/**
 * 数据类型属性Model
 *
 * @author: caidchen
 * @create: 2017-04-15 14:26
 * To change this template use File | Settings | File Templates.
 */
public class DataProductAttrRelationModel {

    private int id ;
    private int productId ;     // 产品ID
    private int typeId ;        // 类型ID
    private int attrId ;        // 属性ID
    private String attrName ;   // 属性名称
    private int valId ;         // 属性值Id
    private String valName ;    // 属性值名称

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
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
}
