package com.qtdbp.trading.controller;

import com.qtdbp.trading.constants.SsoConstants;
import com.qtdbp.trading.exception.ErrorCode;
import com.qtdbp.trading.exception.GlobalException;
import com.qtdbp.trading.mapper.SysUserMapper;
import com.qtdbp.trading.model.DataUserInfoModel;
import com.qtdbp.trading.service.DataUserInfoService;
import com.qtdbp.trading.service.security.model.SysUser;
import com.qtdbp.trading.utils.Des3;
import com.qtdbp.trading.utils.Message;
import com.qtdbp.trading.utils.SsoUrlUtil;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.net.URLEncoder;

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
    private SysUserMapper userService;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * sso登陆
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login() {
        ModelAndView view = new ModelAndView(PAGE_LOGIN);

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
                if(ssoUser != null && ssoUser.has(SsoConstants.SSO_ID)) {
                    sysUserId = ssoUser.getString(SsoConstants.SSO_ID);

                    // 首次登陆交易平台，添加本地用户信息
                    SysUser user = userService.findUserBySsoId(sysUserId) ;
                    if(user == null) {
                        DataUserInfoModel localUser = new DataUserInfoModel() ;
                        // ssoId
                        localUser.setSsoUserId(sysUserId);
                        // 账号
                        if(ssoUser.has(SsoConstants.SSO_USER_NAME))
                            localUser.setPhone(ssoUser.getString(SsoConstants.SSO_USER_NAME));
                        // 头像
                        if(ssoUser.has(SsoConstants.SSO_HEAD))
                            localUser.setHead(ssoUser.getString(SsoConstants.SSO_HEAD));
                        // 昵称
                        if(ssoUser.has(SsoConstants.SSO_NICKNAME))
                            localUser.setNick(ssoUser.getString(SsoConstants.SSO_NICKNAME));
                        int id = userInfoService.insertDataUserInfo(localUser) ;
                        if(id < 0) throw new GlobalException("用户授权失败，请重新授权") ;
                    }
                }
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
}
