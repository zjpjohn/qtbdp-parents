package com.qtdbp.trading.mapper;

import com.qtdbp.trading.model.DataUserFeedbackModel;
import com.qtdbp.trading.utils.BaseMapper;

/**
 * Created by dell on 2017/6/7.
 */
public interface DataUserFeedbackMapper extends BaseMapper<DataUserFeedbackModel>{

    /**
     * 新增用户反馈
     * @return
     */
    Integer insertFeedback(DataUserFeedbackModel feedbackModel);
}
