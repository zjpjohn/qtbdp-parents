package com.qtdbp.trading.controller;

import com.qtdbp.trading.utils.SsoUrlUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * 用户登录Controller
 *
 * Created by dell on 2017/4/11.
 */
@Controller
public class LoginController {

    Logger logger = LoggerFactory.getLogger(this.getClass()) ;

    @Value("${sso.loginUrl}")
    private String ssoLoginUrl ;
    @Value("${sso.registerUrl}")
    private String ssoRegisterUrl ;
    @Value("${sso.callback}")
    private String ssoCallback ;
    @Value("${sso.key}")
    private String ssoKey ;

    /**
     * sso登陆
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login() {
        String url = SsoUrlUtil.generateUrl(ssoLoginUrl, ssoCallback) ;
        logger.info("url: "+url);

        ModelAndView view = new ModelAndView();
        view.setViewName("redirect:" + url);

        return view;
    }

    /**
     * 注册
     * @return
     */
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView register() {
        String registerUrl = SsoUrlUtil.generateUrl(ssoRegisterUrl, ssoCallback) ;
        logger.info("registerUrl: "+registerUrl);

        ModelAndView view = new ModelAndView();
        view.setViewName("redirect:" + registerUrl);

        return view;
    }

    /**
     * 自动登录授权
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/token", method = RequestMethod.GET)
    public ModelAndView ssoLogin(HttpServletRequest request) throws Exception {

        String sysUserId = SsoUrlUtil.loginParams(request) ;

        ModelAndView view = new ModelAndView("login");
        view.addObject("ssoId", sysUserId) ; // 系统用户ID
        view.addObject("auto", true) ;     // 自动登录

        return view ;
    }

}
