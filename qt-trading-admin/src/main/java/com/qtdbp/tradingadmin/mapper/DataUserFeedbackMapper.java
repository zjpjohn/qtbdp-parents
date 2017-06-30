package com.qtdbp.tradingadmin.mapper;

import com.qtdbp.trading.model.DataUserFeedbackModel;
import com.qtdbp.trading.utils.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by dell on 2017/6/7.
 */
public interface DataUserFeedbackMapper extends BaseMapper<DataUserFeedbackModel>{

    /**
     * 查询反馈数据，分页获取
     * @param feedbackModel
     * @return
     */
    List<DataUserFeedbackModel> findFeedbackByCondition(DataUserFeedbackModel feedbackModel);

    /**
     * 更新用户反馈表
     * @param feedbackModel
     * @return
     */
    Integer updateFeedback(DataUserFeedbackModel feedbackModel);

    /**
     * 查询单条用户反馈
     * @param id
     * @return
     */
    DataUserFeedbackModel findUserFeedbackById(@Param("id") Integer id);

}
