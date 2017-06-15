package com.qtdbp.trading.controller;

import com.qtdbp.trading.constants.AppConstants;
import com.qtdbp.trading.constants.SsoConstants;
import com.qtdbp.trading.exception.ErrorCode;
import com.qtdbp.trading.exception.GlobalException;
import com.qtdbp.trading.model.DataUserInfoModel;
import com.qtdbp.trading.service.DataUserInfoService;
import com.qtdbp.trading.utils.Des3;
import com.qtdbp.trading.utils.Message;
import com.qtdbp.trading.utils.SsoUrlUtil;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.net.URLEncoder;
import java.util.Map;

/**
 * 用户登录Controller
 *
 * Created by dell on 2017/4/11.
 */
@Controller
public class LoginController {

    Logger logger = LoggerFactory.getLogger(this.getClass()) ;

    private static final String PAGE_LOGIN = "login" ;

    @Value("${sso.loginUrl}")
    private String ssoLoginUrl ;
    @Value("${sso.registerUrl}")
    private String ssoRegisterUrl ;
    @Value("${sso.callback}")
    private String ssoCallback ;
    @Value("${sso.key}")
    private String ssoKey ;

    @Autowired
    private DataUserInfoService userInfoService ;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

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
     * 用户单点登录后，自动授权登录交易平台
     * @param request
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "/callback", method = RequestMethod.GET)
    public ModelMap callback(HttpServletRequest request) throws Exception {

        Message message = new Message() ;

        try {
            String token = request.getParameter("token") ;
            String descToken = URLEncoder.encode(Des3.DESEncode(token, SsoConstants.DES_KEY), "UTF-8");

            String sysUserId = null ;
            String tokenObject = stringRedisTemplate.opsForValue().get(descToken) ;
            if(tokenObject != null) {
                JSONObject ssoUser = JSONObject.fromObject(tokenObject) ;
                if(ssoUser != null && ssoUser.has("id")) sysUserId = ssoUser.getString("id");
            }

            message.setSuccess(true);
            message.setData(sysUserId);

        } catch (Exception e) {
            logger.error("登录回调地址，错误："+e.getMessage());

            message.setMessage("登录回调地址，错误："+e.getMessage());
            message.setSuccess(false);
            message.setException(true);
            message.setErrorCode(ErrorCode.ERROR_LOGIN_FAIL);
        }


        ModelMap map = new ModelMap();
        map.put("result", message) ;

        return map ;
    }

    /**
     * 用户首次登陆交易平台，跳转至授权页面
     * @return
     */
    @RequestMapping(value = "/token", method = RequestMethod.GET)
    public ModelAndView token(HttpServletRequest request) throws GlobalException {

        Map<String, String> paramsMap = SsoUrlUtil.tokenParams(request) ;

        DataUserInfoModel user = new DataUserInfoModel() ;
        if(paramsMap != null && !paramsMap.isEmpty()) {
            // ssoId
            if(paramsMap.containsKey(SsoConstants.SSO_COOKIE_NAME_USER_ID))
                user.setSsoUserId(paramsMap.get(SsoConstants.SSO_COOKIE_NAME_USER_ID));
            // 账号
            if(paramsMap.containsKey(SsoConstants.SSO_COOKIE_NAME_USER_NAME))
                user.setPhone(paramsMap.get(SsoConstants.SSO_COOKIE_NAME_USER_NAME));
            // 头像
            if(paramsMap.containsKey(SsoConstants.SSO_COOKIE_NAME_HEAD))
                user.setHead(paramsMap.get(SsoConstants.SSO_COOKIE_NAME_HEAD));
            // 昵称
            if(paramsMap.containsKey(SsoConstants.SSO_COOKIE_NAME_NICKNAME))
                user.setNick(paramsMap.get(SsoConstants.SSO_COOKIE_NAME_NICKNAME));
        }

        ModelAndView view = new ModelAndView(PAGE_LOGIN);
        view.addObject(SsoConstants.SSO_USER, user) ;

        return view;
    }

    /**
     * 用户首次登陆交易平台，添加用户信息
     * @param user
     * @return
     * @throws GlobalException
     */
    @RequestMapping(value = "/token", method = RequestMethod.POST)
    public ModelAndView addUser(@ModelAttribute(SsoConstants.SSO_USER) DataUserInfoModel user) throws GlobalException {

        if(user == null) throw new GlobalException("用户授权信息不存在，请重新授权") ;
        // 用户类型 - 个人
        user.setUserType(AppConstants.USER_TYPE_PERSONAL);
        if(StringUtils.isEmpty(user.getHead())) user.setHead(null);

        int id = userInfoService.insertDataUserInfo(user) ;
        if(id < 0) throw new GlobalException("用户授权失败，请重新授权") ;

        ModelAndView view = new ModelAndView();
        view.setViewName("redirect:/callback");
        return view;
    }

    /*@RequestMapping(value = "/register", method = RequestMethod.POST)
    public ModelMap registerUser(@ModelAttribute(SsoConstants.SSO_USER) DataUserInfoModel user) throws GlobalException {

        Message message = new Message() ;
        if(user == null) {
            logger.error("用户注册失败，信息丢失");

            message.setSuccess(false);
            message.setErrorCode(ErrorCode.ERROR_REGISTER_FAIL);
            message.setMessage("用户注册失败，信息丢失");
        } else {
            // 用户类型 - 个人
            user.setUserType(AppConstants.USER_TYPE_PERSONAL);
            if (StringUtils.isEmpty(user.getHead())) user.setHead(null);

            int id = userInfoService.insertDataUserInfo(user);
            if (id < 0) {
                message.setSuccess(false);
                message.setErrorCode(ErrorCode.ERROR_REGISTER_FAIL);
                message.setMessage("用户注册失败，请重新注册");
            } else {
                message.setSuccess(true);
                message.setData(user.getId());
            }
        }
        ModelMap map = new ModelMap();
        map.put("result", message) ;

        return map ;
    }*/

}
