package com.qtdbp.trading.model;

import java.util.Date;

/**
 * 数据条目Model
 *
 * @author: caidchen
 * @create: 2017-04-18 12:47
 * To change this template use File | Settings | File Templates.
 */
public class DataItemModel extends BaseModel {

    private int productId;          // 产品ID
    private int classificationId;   // 类别id
    private String itemName ;       // 条目名称
    private String itemContent ;    // 条目内容json格式
    private int viewCount ;         // 浏览次数
    private int downloadCount ;     // 下载次数
    private int editorId ;          // 编辑人id
    private Date editorTime ;       // 编辑时间
    private int sort ;              // 排序值
    private int rec ;               // 推荐值
    private int isUsed ;            // 是否可用
    private Double price ;          // 价格
    private String fileUrl;         // 地址

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getClassificationId() {
        return classificationId;
    }

    public void setClassificationId(int classificationId) {
        this.classificationId = classificationId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemContent() {
        return itemContent;
    }

    public void setItemContent(String itemContent) {
        this.itemContent = itemContent;
    }

    public int getViewCount() {
        return viewCount;
    }

    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
    }

    public int getDownloadCount() {
        return downloadCount;
    }

    public void setDownloadCount(int downloadCount) {
        this.downloadCount = downloadCount;
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

}
