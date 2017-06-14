package com.qtdbp.trading.api;

import com.github.pagehelper.PageInfo;
import com.qtdbp.trading.constants.ApiConstants;
import com.qtdbp.trading.exception.GlobalException;
import com.qtdbp.trading.mapper.DataUserFeedbackMapper;
import com.qtdbp.trading.model.DataUserFeedbackModel;
import com.qtdbp.trading.utils.CommonUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by dell on 2017/6/6.
 */
@Api(description = "用户反馈接口 - 业务API接口")
@RestController
@RequestMapping(value = "/api/feedback")
public class DataUserFeedbackApi {

    @Autowired
    private DataUserFeedbackMapper feedbackMapper;
    @Autowired
    private HttpServletRequest request ;

    @ApiOperation(value="添加用户反馈数据接口")
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ModelMap insert(DataUserFeedbackModel feedbackModel) throws GlobalException {
        if (feedbackModel == null) throw new GlobalException("数据不存在，请重新输入");

        ModelMap map = new ModelMap();

        try {
            feedbackModel.setIp(CommonUtil.getIpAddress(request));
            Integer count = feedbackMapper.insertFeedback(feedbackModel);
            map.put("success", count > 0 ? true : false);
            map.put("id", feedbackModel.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return map;
    }

}
