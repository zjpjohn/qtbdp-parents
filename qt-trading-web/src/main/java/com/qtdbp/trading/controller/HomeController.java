package com.qtdbp.trading.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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

        return "login";
    }

}
