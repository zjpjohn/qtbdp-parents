package com.qtdbp.trading.controller;

import com.qtdbp.trading.mapper.SeoSettingsMapper;
import com.qtdbp.trading.model.DataInstitutionInfoNewModel;
import com.qtdbp.trading.model.SeoSettingsModel;
import com.qtdbp.trading.service.DataInstitutionInfoNewService;
import com.qtdbp.trading.service.security.model.SysUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * 基础Controller
 *
 * @author: caidchen
 * @create: 2017-04-22 10:47
 * To change this template use File | Settings | File Templates.
 */
public class BaseController {

    Logger logger = LoggerFactory.getLogger(this.getClass()) ;

    @Autowired
    private DataInstitutionInfoNewService institutionInfoNewService ;

    @Autowired
    private SeoSettingsMapper seoSettingsMapper;

    @ModelAttribute("user")
    public SysUser getPrincipal() {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        SysUser user = null ;
        if (principal instanceof SysUser) {
            user = (SysUser) principal;

            // 服务商
            user.setInfoNewModel(institutionInfoNewService.findInstitutionInfoIgnoreAuditStatusByCreateId(user.getId()));

            logger.info("nick:"+ user.getUserName());
            logger.info("head:"+ user.getHead());
            logger.info("id: "+ user.getId());
            logger.info("phone: "+ user.getPhone());
        }
        return user;
    }

    /**
     * 获取seo的相关信息
     * @param page
     * @param request
     * @return
     */
    public ModelAndView getSeoSettings(String page, HttpServletRequest request) {
        ModelAndView result = new ModelAndView(page);
        String url = request.getRequestURI();
        SeoSettingsModel seoSettingsModel = null;
        if (url != null) {
            try {
                seoSettingsModel = seoSettingsMapper.findSeoByResourcePath(url);
            } catch (Exception e) {
                e.printStackTrace();
                logger.error("findSeoByResourcePath has error ,message:" + e.getMessage());
            }
        }
        result.addObject("seo", seoSettingsModel);
        return  result;
    }

}
