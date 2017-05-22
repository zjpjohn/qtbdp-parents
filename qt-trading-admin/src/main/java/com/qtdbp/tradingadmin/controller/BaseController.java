package com.qtdbp.tradingadmin.controller;

import com.qtdbp.tradingadmin.base.security.SecurityUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    @ModelAttribute("user")
    public SecurityUser getPrincipal() {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        SecurityUser user = null ;
        if (principal instanceof SecurityUser) {
            user = (SecurityUser) principal;
        }
        return user;
    }
}
