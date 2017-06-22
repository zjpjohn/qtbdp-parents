package com.qtdbp.trading.model;/**
 * Created by dell on 2017/4/15.
 */

import javax.persistence.Transient;
import java.util.Date;

/**
 * 网站seoModel
 *
 * @author: 李扬
 * @create: 2017-06-21 13:24
 * To change this template use File | Settings | File Templates.
 */
public class SeoSettingsModel extends BaseModel{

    private int seoType ;       // 网页类型
    private String seoTitle ;   // 网页标题
    private String seoDescription;  // 网页描述
    private String seoKeywords;  // 网页关键字
    private Date createTime;   //创建时间

    @Transient
    private String resourcesId;     //资源ID

    public int getSeoType() {
        return seoType;
    }

    public void setSeoType(int seoType) {
        this.seoType = seoType;
    }

    public String getSeoTitle() {
        return seoTitle;
    }

    public void setSeoTitle(String seoTitle) {
        this.seoTitle = seoTitle;
    }

    public String getSeoDescription() {
        return seoDescription;
    }

    public void setSeoDescription(String seoDescription) {
        this.seoDescription = seoDescription;
    }

    public String getSeoKeywords() {
        return seoKeywords;
    }

    public void setSeoKeywords(String seoKeywords) {
        this.seoKeywords = seoKeywords;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getResourcesId() {
        return resourcesId;
    }

    public void setResourcesId(String resourcesId) {
        this.resourcesId = resourcesId;
    }
}
