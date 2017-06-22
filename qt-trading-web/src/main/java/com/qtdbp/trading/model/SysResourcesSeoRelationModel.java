package com.qtdbp.trading.model;

/**
 * Created by dell on 2017/6/21.
 */
public class SysResourcesSeoRelationModel {

    private int id;
    private int seoId;  //网站SEOid
    private String sysResourcesId; //网站资源id

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSeoId() {
        return seoId;
    }

    public void setSeoId(int seoId) {
        this.seoId = seoId;
    }

    public String getSysResourcesId() {
        return sysResourcesId;
    }

    public void setSysResourcesId(String sysResourcesId) {
        this.sysResourcesId = sysResourcesId;
    }
}
