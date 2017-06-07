package com.qtdbp.tradingadmin.api;

import com.github.pagehelper.PageInfo;
import com.qtdbp.trading.constants.ApiConstants;
import com.qtdbp.trading.exception.GlobalException;
import com.qtdbp.tradingadmin.mapper.DataUserFeedbackMapper;
import com.qtdbp.trading.model.DataUserFeedbackModel;
import com.qtdbp.tradingadmin.service.DataUserFeedbackService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by dell on 2017/6/7.
 */
@Api(description = "用户反馈接口 - 业务API接口")
@RestController
@RequestMapping(value = "/api/feedback")
public class DataUserFeedbackApi {

    @Autowired
    private DataUserFeedbackMapper feedbackMapper;

    @Autowired
    private DataUserFeedbackService feedbackService;

    @ApiOperation(value = "用户反馈数据接口，分页获取")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "typeId", value = "类目类型id（如：1）", dataType = "Integer", paramType = ApiConstants.PARAM_TYPE_QUERY),
            @ApiImplicitParam(name = "webType", value = "网站类型id（如：1）", dataType = "Integer", paramType = ApiConstants.PARAM_TYPE_QUERY),
            @ApiImplicitParam(name = "orderBy", value = "排序字段", required = true, dataType = "String", paramType = ApiConstants.PARAM_TYPE_QUERY)
    })
    @ResponseBody
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelMap findByCondition(DataUserFeedbackModel feedbackModel) {
        if(feedbackModel.getRows() == null || feedbackModel.getRows() == 0) feedbackModel.setRows(12);
        ModelMap map = new ModelMap();

        try {
            List<DataUserFeedbackModel> list = feedbackService.findFeedbackByCondition(feedbackModel);
            map.put("pageInfo", new PageInfo<>(list));
            map.put("queryParam", feedbackModel);
            map.put("page", feedbackModel.getPage());
            map.put("rows", feedbackModel.getRows());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    @ApiOperation(value = "更新用户反馈数据接口")
    @RequestMapping(value = "", method = RequestMethod.PUT)
    public ModelMap updateFeedback(@RequestBody DataUserFeedbackModel feedbackModel) throws GlobalException {

        if (feedbackModel == null || feedbackModel.getId() == null) throw new GlobalException("数据不存在，请重新操作");

        ModelMap map = new ModelMap();

        try {
            Integer count = feedbackMapper.updateFeedback(feedbackModel);
            map.put("success", count > 0 ? true : false);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return map;

    }

}
