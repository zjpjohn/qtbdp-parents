package com.qtdbp.tradingadmin.utils;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * 基础Mapper
 *
 * @author: caidchen
 * @create: 2017-04-16 11:31
 * To change this template use File | Settings | File Templates.
 */
public interface BaseMapper<T> extends Mapper<T>, MySqlMapper<T> {
}
