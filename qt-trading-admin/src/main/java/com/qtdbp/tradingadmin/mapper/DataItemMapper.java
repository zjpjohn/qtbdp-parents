package com.qtdbp.tradingadmin.mapper;

import com.qtdbp.trading.model.DataItemModel;
import com.qtdbp.trading.utils.BaseMapper;

/**
 * Created by dell on 2017/5/25.
 */
public interface DataItemMapper extends BaseMapper<DataItemModel>{

    /**
     * 插入数据条目
     * @param itemModel
     * @return
     */
    Integer insertItem(DataItemModel itemModel);

    /**
     * 根据productId删除数据条目信息
     * @param productId
     * @return
     */
    Integer deleteByProductId(Integer productId);
}
