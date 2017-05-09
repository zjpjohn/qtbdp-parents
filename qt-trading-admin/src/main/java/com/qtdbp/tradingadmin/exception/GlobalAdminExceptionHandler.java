package com.qtdbp.tradingadmin.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * 通过使用@ControllerAdvice定义统一的异常处理类，而不是在每个Controller中逐个定义
 *
 * Created by dell on 2017/4/11.
 */
@ControllerAdvice
public class GlobalAdminExceptionHandler {

    public static final String DEFAULT_ERROR_VIEW = "error";

    @ExceptionHandler(value = Exception.class)
    public ModelAndView defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", e);
        mav.addObject("url", req.getRequestURL());
        mav.setViewName(DEFAULT_ERROR_VIEW);
        return mav;
    }

    @ExceptionHandler(value = GlobalAdminException.class)
    @ResponseBody
    public ErrorInfo<String> jsonErrorHandler(HttpServletRequest req, GlobalAdminException e) throws Exception {
        ErrorInfo<String> r = new ErrorInfo<String>();
        r.setMessage(e.getMessage());
        Integer code = e.getCode() ;
        r.setCode(code == null ? ErrorInfo.ERROR : code);
        r.setData(r.getData());
        r.setUrl(req.getRequestURL().toString());

        return r;
    }

}
