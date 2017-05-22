package com.qtdbp.tradingadmin.base.security.utils;

import org.apache.commons.lang.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

import java.util.HashMap;
import java.util.Map;

/**
 * 分页组件
 *
 * @author: caidchen
 * @create: 2017-05-21 17:58
 * To change this template use File | Settings | File Templates.
 */
public class PageUtils {

    /**
     * @param pageNum 当前页
     * @param pageSize 每页条数
     * @param sortType 排序字段
     * @param direction 排序方向
     */
    public static PageRequest buildPageRequest(int pageNum, int pageSize, String sortType, String direction) {
        Sort sort = null;

        if (!StringUtils.isNotBlank(sortType)) {
            return new PageRequest(pageNum - 1, pageSize);
        } else if (StringUtils.isNotBlank(direction)) {
            if (Direction.ASC.equals(direction)) {
                sort = new Sort(Direction.ASC, sortType);
            } else {
                sort = new Sort(Direction.DESC, sortType);
            }
            return new PageRequest(pageNum - 1, pageSize, sort);
        } else {
            sort = new Sort(Direction.ASC, sortType);
            return new PageRequest(pageNum - 1, pageSize, sort);
        }
    }

    /**
     * 构建PageRequest
     */
    public static PageRequest buildPageRequest(int pageNum, int pageSize, String sortType) {
        return buildPageRequest(pageNum, pageSize, sortType, null);
    }

    /**
     * 封装分页数据到Map中。
     */
    public static Map<String, Object> getPageMap(Page<?> objPage) {
        Map<String, Object> resultMap = new HashMap<String, Object>();

        resultMap.put(PageConstants.PAGE_RESULT_LIST, objPage.getContent()); // 数据集合，符合查询条件的所有记录数据
        resultMap.put(PageConstants.PAGE_TOTAL_NUM, objPage.getTotalElements()); // 总记录数
        resultMap.put(PageConstants.PAGE_TOTAL_PAGE, objPage.getTotalPages()); // 总页数
        resultMap.put(PageConstants.PAGE_NUM, objPage.getNumber()); // 当前页码
        resultMap.put(PageConstants.PAGE_SIZE, objPage.getSize()); // 每页显示数量

        return resultMap;
    }
}
