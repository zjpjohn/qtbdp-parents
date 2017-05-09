package com.qtdbp.tradingadmin.exception;

/**
 * 自定义异常处理
 *
 * Created by dell on 2017/4/11.
 */
public class GlobalAdminException extends Exception {

    private Integer code ;

    public GlobalAdminException(String message) {
        super(message);
    }

    public GlobalAdminException(Integer code, String message) {
        super(message);
        this.code = code ;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
