package com.qtdbp.tradingadmin.service;


import com.github.pagehelper.PageHelper;
import com.qtdbp.tradingadmin.mapper.DataUserFeedbackMapper;
import com.qtdbp.trading.model.DataUserFeedbackModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by dell on 2017/6/6.
 */
@Service
public class DataUserFeedbackService {

    @Autowired
    private DataUserFeedbackMapper feedbackMapper;

    /**
     * 分页查询用户反馈数据
     * @param feedbackModel
     * @return
     */
    public List<DataUserFeedbackModel> findFeedbackByCondition(DataUserFeedbackModel feedbackModel) {
        if (feedbackModel.getPage() != null && feedbackModel.getRows() != null) {
            PageHelper.startPage(feedbackModel.getPage(), feedbackModel.getRows());
        }
        return feedbackMapper.findFeedbackByCondition(feedbackModel);
    }


}
