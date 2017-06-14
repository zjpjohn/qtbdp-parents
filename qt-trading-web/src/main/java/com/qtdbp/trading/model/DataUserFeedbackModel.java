package com.qtdbp.trading.model;

import java.util.Date;

/**
 * 用户反馈model
 * Created by dell on 2017/6/7.
 */
public class DataUserFeedbackModel extends BaseModel {

    private Integer feedbackType;  //反馈类型
    private String email ;          //邮箱
    private String phone ;          //手机号
    private String content ;        //反馈内容
    private Date addtime ;          //反馈时间
    private String ip ;             //IP地址
    private Integer mark ;          //是否查看
    private String qq;              // 联系QQ

    public Integer getFeedbackType() {
        return feedbackType;
    }

    public void setFeedbackType(Integer feedbackType) {
        this.feedbackType = feedbackType;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getMark() {
        return mark;
    }

    public void setMark(Integer mark) {
        this.mark = mark;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }
}
