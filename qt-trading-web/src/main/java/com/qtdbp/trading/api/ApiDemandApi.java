package com.qtdbp.trading.api;

import com.qtdbp.trading.exception.ErrorCode;
import com.qtdbp.trading.exception.GlobalException;
import com.qtdbp.trading.mapper.ApiDemandMapper;
import com.qtdbp.trading.model.ApiDemandModel;
import com.qtdbp.trading.utils.Message;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by dell on 2017/6/22.
 */
@Api(description = "Api定制 - 业务API接口")
@RestController
@RequestMapping(value = "/api/demand")
public class ApiDemandApi {

    Logger logger = LoggerFactory.getLogger(this.getClass()) ;

    @Autowired
    private ApiDemandMapper apiDemandMapper;

    @ApiOperation(value="添加Api定制数据接口")
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ModelMap insertApiDemand( ApiDemandModel apiDemandModel) throws GlobalException {

        ModelMap map = new ModelMap();
        Message message = new Message();
        Integer id = -1;

        if (apiDemandModel != null) {
            try {
                id = apiDemandMapper.insertApiDemand(apiDemandModel);
                if(id != null && id > 0) {
                    message.setSuccess(true);
                    message.setData(id);
                }
            } catch (Exception e) {
                logger.error("insertApiDemand has error ,message:" + e.getMessage());
                throw new GlobalException(e.getMessage());
            }

        } else {
            message.setSuccess(false);
            message.setErrorCode(ErrorCode.ERROR_LOGIN_NO);
            message.setMessage("数据包产品不存在，请重新输入");
        }
        map.put("result", message);
        return map;
    }


}
