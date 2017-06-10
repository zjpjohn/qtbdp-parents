package com.qtdbp.trading.api;

import com.github.pagehelper.PageInfo;
import com.qtdbp.trading.constants.ApiConstants;
import com.qtdbp.trading.exception.GlobalException;
import com.qtdbp.trading.mapper.CrawlersRoleMapper;
import com.qtdbp.trading.mapper.DataTypeMapper;
import com.qtdbp.trading.model.CrawlersRoleModel;
import com.qtdbp.trading.model.DataTypeModel;
import com.qtdbp.trading.service.CrawlersRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dell on 2017/6/6.
 */
@Api(description = "爬虫市场接口 - 业务API接口")
@RestController
@RequestMapping(value = "/api/crawlers/role")
public class CrawlersRoleApi {

    @Autowired
    private CrawlersRoleService crawlersRoleService;

    @Autowired
    private DataTypeMapper dataTypeMapper;

    @ApiOperation(value="添加爬虫规则数据接口")
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ModelMap insert(@RequestBody CrawlersRoleModel crawlersRoleModel) throws GlobalException {
        if (crawlersRoleModel == null) throw new GlobalException("数据不存在，请重新输入");

        ModelMap map = new ModelMap();

        try {
            Integer count = crawlersRoleService.insertRole(crawlersRoleModel);
            map.put("success", count > 0 ? true : false);
            map.put("id", crawlersRoleModel.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return map;
    }

    @ApiOperation(value = "爬虫市场数据接口，分页获取")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "webType", value = "网站类型id（如：1）", dataType = "Integer", paramType = ApiConstants.PARAM_TYPE_QUERY),
            @ApiImplicitParam(name = "dataType", value = "数据类型ID（如：1）", dataType = "Integer", paramType = ApiConstants.PARAM_TYPE_QUERY),
            @ApiImplicitParam(name = "orderBy", value = "排序字段", dataType = "String", paramType = ApiConstants.PARAM_TYPE_QUERY),
            @ApiImplicitParam(name = "page", value = "当前页（如：1）", defaultValue = "1", dataType = "Integer", required = true, paramType = ApiConstants.PARAM_TYPE_QUERY),
            @ApiImplicitParam(name = "rows", value = "每页显示记录数（如：12）", defaultValue = "12", dataType = "Integer", required = true, paramType = ApiConstants.PARAM_TYPE_QUERY)
    })
    @ResponseBody
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelMap findByCondition(CrawlersRoleModel crawlersRoleModel, Integer dataType) {

        if(crawlersRoleModel.getRows() == null || crawlersRoleModel.getRows() == 0) crawlersRoleModel.setRows(12);
        if (crawlersRoleModel.getOrderBy() == null) crawlersRoleModel.setOrderBy("create_time");
        if (dataType == null){
            dataType = 0;
        }
        List<Integer> list = getAllDataTypeIds(dataType);
        if (list != null && list.size() != 0){
            String dataTypes = "";
            for (int i = 0; i<list.size(); i++){
                if (i == (list.size()-1)){
                    dataTypes = dataTypes + list.get(i) ;
                }else {
                    dataTypes = dataTypes + list.get(i) + ",";
                }
            }
            crawlersRoleModel.setDataTypes(dataTypes);
        }else {
            if (dataType != 0 ) {
                crawlersRoleModel.setDataTypes(dataType+"");
            }
        }
        ModelMap map = new ModelMap();
        try {
            List<CrawlersRoleModel> Rolelist = crawlersRoleService.findRoleByCondition(crawlersRoleModel);
            map.put("pageInfo", new PageInfo<>(Rolelist));
            map.put("queryParam", crawlersRoleModel);
            map.put("page", crawlersRoleModel.getPage());
            map.put("rows", crawlersRoleModel.getRows());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    @ApiOperation(value="更新爬虫规则数据接口")
    @RequestMapping(value = "", method = RequestMethod.PUT)
    public ModelMap update(@RequestBody CrawlersRoleModel crawlersRoleModel) throws GlobalException {
        if (crawlersRoleModel == null) throw new GlobalException("数据不存在，请重新输入");

        ModelMap map = new ModelMap();

        try {
            Integer count = crawlersRoleService.updateRoles(crawlersRoleModel);
            map.put("success", count > 0 ? true : false);
            map.put("id", crawlersRoleModel.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return map;
    }

    @ApiOperation(value = "爬虫规则详情数据接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "爬虫规则id", required = true, dataType = "Integer", paramType = ApiConstants.PARAM_TYPE_QUERY)
    })
    @ResponseBody
    @RequestMapping(value = "findRuleById", method = RequestMethod.GET)
    public ModelMap findById(CrawlersRoleModel crawlersRoleModel) {

        ModelMap map = new ModelMap();
        try {

            map.put("pageInfo", crawlersRoleService.findRuleById(crawlersRoleModel.getId()));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 递归查询数据类型某一节点下所有的叶子节点
     * @param dataType
     * @return
     */
    private List getAllDataTypeIds(Integer dataType) {

        if (dataType == 0 || dataType == null) return null;

        List<Integer> dataTypeIdsList = new ArrayList<>();

        List<DataTypeModel> list = dataTypeMapper.findDataTypeByParentId(dataType);
        if (list == null || list.size() == 0) return dataTypeIdsList;
        for(DataTypeModel model : list){
            dataTypeIdsList.add(model.getId());
            List<Integer> idsList = getAllDataTypeIds(model.getId());
            if(idsList != null && idsList.size() != 0) dataTypeIdsList.addAll(idsList);
        }
        return dataTypeIdsList;
    }

}
