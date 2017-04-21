package com.qtdbp.trading.model;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

/**
 * 基础信息
 *
 * @author: caidchen
 * @create: 2017-04-16 11:29
 * To change this template use File | Settings | File Templates.
 */
public class BaseModel {

    @ApiModelProperty(hidden=true)
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ApiModelProperty(hidden=true)
    @Transient
    private Integer page = 1;

    @ApiModelProperty(hidden=true)
    @Transient
    private Integer rows ;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }
}
