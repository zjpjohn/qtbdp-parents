package com.qtdbp.boss.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with IntelliJ IDEA.
 * User: 浩
 * Date: 2017/4/13
 * Time: 22:58
 * To change this template use File | Settings | File Templates.
 */
@RestController
public class DemoController {

    // 上传图片
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test(@RequestParam String param) throws Exception {
        // 省略业务逻辑代码。。。
        // String imgUrl = dfsClient.uploadFile(file);
        // 。。。。
        return "请求boss 服务端方法："+param;
    }
}
