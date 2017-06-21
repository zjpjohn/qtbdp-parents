package com.qtdbp.trading.controller;

import com.qtdbp.trading.model.DataInstitutionInfoNewModel;
import com.qtdbp.trading.service.DataInstitutionInfoNewService;
import com.qtdbp.trading.service.security.model.SysUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ModelAttribute;

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

}
