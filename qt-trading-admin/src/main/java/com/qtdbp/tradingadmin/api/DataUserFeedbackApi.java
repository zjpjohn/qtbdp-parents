package com.qtdbp.tradingadmin.api;

import com.github.pagehelper.PageInfo;
import com.qtdbp.trading.constants.ApiConstants;
import com.qtdbp.tradingadmin.exception.GlobalAdminException;
import com.qtdbp.tradingadmin.mapper.DataUserFeedbackMapper;
import com.qtdbp.trading.model.DataUserFeedbackModel;
import com.qtdbp.tradingadmin.service.DataUserFeedbackService;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    Logger logger = LoggerFactory.getLogger(this.getClass()) ;


    @Autowired
    private DataUserFeedbackMapper feedbackMapper;

    @Autowired
    private DataUserFeedbackService feedbackService;

    @ApiOperation(value = "用户反馈数据接口，分页获取")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "feedbackType", value = "反馈类型 1:数据需求，2：商务合作", dataType = "Integer", paramType = ApiConstants.PARAM_TYPE_QUERY),
            @ApiImplicitParam(name = "page", value = "当前页（如：1）", defaultValue = "1", dataType = "Integer", required = true, paramType = ApiConstants.PARAM_TYPE_QUERY),
            @ApiImplicitParam(name = "rows", value = "每页显示记录数（如：12）", defaultValue = "12", dataType = "Integer", required = true, paramType = ApiConstants.PARAM_TYPE_QUERY)
    })
    @ResponseBody
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelMap findFeedbackByCondition(DataUserFeedbackModel feedbackModel) throws GlobalAdminException {
        if(feedbackModel.getRows() == null || feedbackModel.getRows() == 0) feedbackModel.setRows(12);
        ModelMap map = new ModelMap();

        try {
            List<DataUserFeedbackModel> list = feedbackService.findFeedbackByCondition(feedbackModel);
            map.put("pageInfo", new PageInfo<>(list));
            map.put("queryParam", feedbackModel);
            map.put("page", feedbackModel.getPage());
            map.put("rows", feedbackModel.getRows());
        } catch (Exception e) {
            logger.error("findFeedbackByCondition has error ,message:" + e.getMessage());
            throw new GlobalAdminException(e.getMessage());
        }
        return map;
    }

    @ApiOperation(value = "更新用户反馈为已查看接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户反馈id", dataType = "Integer", required = true, paramType = ApiConstants.PARAM_TYPE_QUERY)
    })
    @RequestMapping(value = "/changeMark", method = RequestMethod.PUT)
    public ModelMap updateFeedback(Integer id) throws GlobalAdminException {

        ModelMap map = new ModelMap();

        if (id != null) {
            try {
                DataUserFeedbackModel feedbackModel = new DataUserFeedbackModel();
                feedbackModel.setId(id);
                feedbackModel.setMark(1);
                Integer count = feedbackMapper.updateFeedback(feedbackModel);
                map.put("success", count > 0 ? true : false);
            } catch (Exception e) {
                logger.error("updateFeedback has error ,message:" + e.getMessage());
                throw new GlobalAdminException(e.getMessage());
            }
        }

        return map;
    }

    @ApiOperation(value = "根据ID查询单条用户反馈数据接口")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ModelMap findUserFeedbackById(
            @ApiParam(name = "id", value = "用户反馈ID", required = true) @PathVariable Integer id) throws GlobalAdminException {

        ModelMap map = new ModelMap();
        DataUserFeedbackModel feedbackModel = null ;
        if(id != null) {
            try {
                feedbackModel = feedbackMapper.findUserFeedbackById(id);
            } catch (Exception e) {
                logger.error("findUserFeedbackById has error ,message:" + e.getMessage());
                throw new GlobalAdminException(e.getMessage());
            }
        }
        map.put("pageInfo", feedbackModel);

        return map;
    }

}
