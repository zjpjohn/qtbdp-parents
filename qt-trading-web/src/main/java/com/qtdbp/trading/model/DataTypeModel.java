package com.qtdbp.trading.model;/**
 * Created by dell on 2017/4/15.
 */

/**
 * 数据类型Model
 *
 * @author: caidchen
 * @create: 2017-04-15 13:24
 * To change this template use File | Settings | File Templates.
 */
public class DataTypeModel {

    private int id ;        // 主键
    private int pid ;       // 父ID
    private String name ;   // 类型名称
    private int isUsed ;   // 是否可用 0 不可用，1可用
    private int sort ;      // 排序
    private String remark;  // 备注
    private Integer isParent;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIsUsed() {
        return isUsed;
    }

    public void setIsUsed(int isUsed) {
        this.isUsed = isUsed;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getIsParent() {
        return isParent;
    }

    public void setIsParent(Integer isParent) {
        this.isParent = isParent;
    }
}
