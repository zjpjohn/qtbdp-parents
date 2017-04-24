package com.qtdbp.trading.controller;

import com.qtdbp.trading.utils.HttpClientUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by dell on 2017/4/11.
 */
@Controller
public class HomeController {

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String home() {

        return "home";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
     public String login() {
        String url = "http://sso.qtbigdata.com/sso/action/goLogin?retUrl=http://locahost:8040/token&sysid=msvtPqZBiqX5qeOaXUEFL5AilgBSA2KTFu0WN74T4MIHjWUMxYbTvQ%3D%3D" ;
        return "redirect:"+ url;
    }

    private String charset = "utf-8";

    @RequestMapping(value = "/token", method = RequestMethod.GET)
    public String token() {
// HttpClient发送请求Session无法保持的问题
//        String cookie = request.getHeader("Cookie");
//        PostMethod postMethod = new PostMethod(url);
//        postMethod.setRequestHeader("Cookie",cookie);

        HttpClientUtil httpClientUtil = new HttpClientUtil();
        Map<String,String> createMap = new HashMap<String,String>();
//        createMap.put("authuser","*****");
//        createMap.put("authpass","*****");
        createMap.put("username","e06a3ddc22234cdfb05bb6058348003e");
        createMap.put("password","123456");
        String httpOrgCreateTestRtn = httpClientUtil.doPost("http://locahost:8040/login",createMap,charset);
        System.out.println("result:"+httpOrgCreateTestRtn);

        return "login";
    }



}
