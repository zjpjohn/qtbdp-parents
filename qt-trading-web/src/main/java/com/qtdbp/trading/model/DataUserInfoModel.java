package com.qtdbp.trading.model;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * 个人用户Model
 */
public class DataUserInfoModel {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column data_user_info.id
	 * @mbggenerated  Fri Apr 22 10:32:51 CST 2016
	 */
	private Integer id;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column data_user_info.user_type
	 * @mbggenerated  Fri Apr 22 10:32:51 CST 2016
	 */
	@ApiModelProperty(hidden = true)
	private Integer userType;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column data_user_info.email
	 * @mbggenerated  Fri Apr 22 10:32:51 CST 2016
	 */
	private String email;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column data_user_info.pwd
	 * @mbggenerated  Fri Apr 22 10:32:51 CST 2016
	 */
	@ApiModelProperty(hidden = true)
	private String pwd;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column data_user_info.company_designation
	 * @mbggenerated  Fri Apr 22 10:32:51 CST 2016
	 */
	@ApiModelProperty(hidden = true)
	private String companyDesignation;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column data_user_info.real_name
	 * @mbggenerated  Fri Apr 22 10:32:51 CST 2016
	 */
	private String realName;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column data_user_info.phone
	 * @mbggenerated  Fri Apr 22 10:32:51 CST 2016
	 */
	@ApiModelProperty(hidden = true)
	private String phone;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column data_user_info.province_id
	 * @mbggenerated  Fri Apr 22 10:32:51 CST 2016
	 */
	@ApiModelProperty(hidden = true)
	private Integer provinceId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column data_user_info.city_id
	 * @mbggenerated  Fri Apr 22 10:32:51 CST 2016
	 */
	@ApiModelProperty(hidden = true)
	private Integer cityId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column data_user_info.addtime
	 * @mbggenerated  Fri Apr 22 10:32:51 CST 2016
	 */
	@ApiModelProperty(hidden = true)
	private Date addtime;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column data_user_info.poll_code
	 * @mbggenerated  Fri Apr 22 10:32:51 CST 2016
	 */
	@ApiModelProperty(hidden = true)
	private String pollCode;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column data_user_info.balance
	 * @mbggenerated  Fri Apr 22 10:32:51 CST 2016
	 */
	@ApiModelProperty(hidden = true)
	private Double balance;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column data_user_info.vip_status
	 * @mbggenerated  Fri Apr 22 10:32:51 CST 2016
	 */
	@ApiModelProperty(hidden = true)
	private Byte vipStatus;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column data_user_info.verify_status
	 * @mbggenerated  Fri Apr 22 10:32:51 CST 2016
	 */
	@ApiModelProperty(hidden = true)
	private Byte verifyStatus;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column data_user_info.editor_id
	 * @mbggenerated  Fri Apr 22 10:32:51 CST 2016
	 */
	@ApiModelProperty(hidden = true)
	private Integer editorId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column data_user_info.editor_time
	 * @mbggenerated  Fri Apr 22 10:32:51 CST 2016
	 */
	@ApiModelProperty(hidden = true)
	private Date editorTime;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column data_user_info.is_used
	 * @mbggenerated  Fri Apr 22 10:32:51 CST 2016
	 */
	@ApiModelProperty(hidden = true)
	private Byte isUsed;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column data_user_info.share_code
	 * @mbggenerated  Fri Apr 22 10:32:51 CST 2016
	 */
	@ApiModelProperty(hidden = true)
	private String shareCode;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column data_user_info.share_code_tui
	 * @mbggenerated  Fri Apr 22 10:32:51 CST 2016
	 */
	@ApiModelProperty(hidden = true)
	private String shareCodeTui;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column data_user_info.integral
	 * @mbggenerated  Fri Apr 22 10:32:51 CST 2016
	 */
	@ApiModelProperty(hidden = true)
	private Double integral;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column data_user_info.head
	 * @mbggenerated  Fri Apr 22 10:32:51 CST 2016
	 */
	private String head;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column data_user_info.share_time
	 * @mbggenerated  Fri Apr 22 10:32:51 CST 2016
	 */
	@ApiModelProperty(hidden = true)
	private Date shareTime;

	/**
	 * 系统用户ID
	 */
	@ApiModelProperty(hidden = true)
	private String ssoUserId ;

	/**
	 * 昵称 length <= 50
	 */
	private String nick ;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column data_user_info.id
	 * @return  the value of data_user_info.id
	 * @mbggenerated  Fri Apr 22 10:32:51 CST 2016
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column data_user_info.id
	 * @param id  the value for data_user_info.id
	 * @mbggenerated  Fri Apr 22 10:32:51 CST 2016
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column data_user_info.email
	 * @return  the value of data_user_info.email
	 * @mbggenerated  Fri Apr 22 10:32:51 CST 2016
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column data_user_info.email
	 * @param email  the value for data_user_info.email
	 * @mbggenerated  Fri Apr 22 10:32:51 CST 2016
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column data_user_info.pwd
	 * @return  the value of data_user_info.pwd
	 * @mbggenerated  Fri Apr 22 10:32:51 CST 2016
	 */
	public String getPwd() {
		return pwd;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column data_user_info.pwd
	 * @param pwd  the value for data_user_info.pwd
	 * @mbggenerated  Fri Apr 22 10:32:51 CST 2016
	 */
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column data_user_info.company_designation
	 * @return  the value of data_user_info.company_designation
	 * @mbggenerated  Fri Apr 22 10:32:51 CST 2016
	 */
	public String getCompanyDesignation() {
		return companyDesignation;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column data_user_info.company_designation
	 * @param companyDesignation  the value for data_user_info.company_designation
	 * @mbggenerated  Fri Apr 22 10:32:51 CST 2016
	 */
	public void setCompanyDesignation(String companyDesignation) {
		this.companyDesignation = companyDesignation;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column data_user_info.real_name
	 * @return  the value of data_user_info.real_name
	 * @mbggenerated  Fri Apr 22 10:32:51 CST 2016
	 */
	public String getRealName() {
		return realName;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column data_user_info.real_name
	 * @param realName  the value for data_user_info.real_name
	 * @mbggenerated  Fri Apr 22 10:32:51 CST 2016
	 */
	public void setRealName(String realName) {
		this.realName = realName;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column data_user_info.phone
	 * @return  the value of data_user_info.phone
	 * @mbggenerated  Fri Apr 22 10:32:51 CST 2016
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column data_user_info.phone
	 * @param phone  the value for data_user_info.phone
	 * @mbggenerated  Fri Apr 22 10:32:51 CST 2016
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column data_user_info.province_id
	 * @return  the value of data_user_info.province_id
	 * @mbggenerated  Fri Apr 22 10:32:51 CST 2016
	 */
	public Integer getProvinceId() {
		return provinceId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column data_user_info.province_id
	 * @param provinceId  the value for data_user_info.province_id
	 * @mbggenerated  Fri Apr 22 10:32:51 CST 2016
	 */
	public void setProvinceId(Integer provinceId) {
		this.provinceId = provinceId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column data_user_info.city_id
	 * @return  the value of data_user_info.city_id
	 * @mbggenerated  Fri Apr 22 10:32:51 CST 2016
	 */
	public Integer getCityId() {
		return cityId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column data_user_info.city_id
	 * @param cityId  the value for data_user_info.city_id
	 * @mbggenerated  Fri Apr 22 10:32:51 CST 2016
	 */
	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column data_user_info.addtime
	 * @return  the value of data_user_info.addtime
	 * @mbggenerated  Fri Apr 22 10:32:51 CST 2016
	 */
	public Date getAddtime() {
		return addtime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column data_user_info.addtime
	 * @param addtime  the value for data_user_info.addtime
	 * @mbggenerated  Fri Apr 22 10:32:51 CST 2016
	 */
	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column data_user_info.poll_code
	 * @return  the value of data_user_info.poll_code
	 * @mbggenerated  Fri Apr 22 10:32:51 CST 2016
	 */
	public String getPollCode() {
		return pollCode;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column data_user_info.poll_code
	 * @param pollCode  the value for data_user_info.poll_code
	 * @mbggenerated  Fri Apr 22 10:32:51 CST 2016
	 */
	public void setPollCode(String pollCode) {
		this.pollCode = pollCode;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column data_user_info.balance
	 * @return  the value of data_user_info.balance
	 * @mbggenerated  Fri Apr 22 10:32:51 CST 2016
	 */
	public Double getBalance() {
		return balance;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column data_user_info.balance
	 * @param balance  the value for data_user_info.balance
	 * @mbggenerated  Fri Apr 22 10:32:51 CST 2016
	 */
	public void setBalance(Double balance) {
		this.balance = balance;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column data_user_info.vip_status
	 * @return  the value of data_user_info.vip_status
	 * @mbggenerated  Fri Apr 22 10:32:51 CST 2016
	 */
	public Byte getVipStatus() {
		return vipStatus;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column data_user_info.vip_status
	 * @param vipStatus  the value for data_user_info.vip_status
	 * @mbggenerated  Fri Apr 22 10:32:51 CST 2016
	 */
	public void setVipStatus(Byte vipStatus) {
		this.vipStatus = vipStatus;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column data_user_info.verify_status
	 * @return  the value of data_user_info.verify_status
	 * @mbggenerated  Fri Apr 22 10:32:51 CST 2016
	 */
	public Byte getVerifyStatus() {
		return verifyStatus;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column data_user_info.verify_status
	 * @param verifyStatus  the value for data_user_info.verify_status
	 * @mbggenerated  Fri Apr 22 10:32:51 CST 2016
	 */
	public void setVerifyStatus(Byte verifyStatus) {
		this.verifyStatus = verifyStatus;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column data_user_info.editor_id
	 * @return  the value of data_user_info.editor_id
	 * @mbggenerated  Fri Apr 22 10:32:51 CST 2016
	 */
	public Integer getEditorId() {
		return editorId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column data_user_info.editor_id
	 * @param editorId  the value for data_user_info.editor_id
	 * @mbggenerated  Fri Apr 22 10:32:51 CST 2016
	 */
	public void setEditorId(Integer editorId) {
		this.editorId = editorId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column data_user_info.editor_time
	 * @return  the value of data_user_info.editor_time
	 * @mbggenerated  Fri Apr 22 10:32:51 CST 2016
	 */
	public Date getEditorTime() {
		return editorTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column data_user_info.editor_time
	 * @param editorTime  the value for data_user_info.editor_time
	 * @mbggenerated  Fri Apr 22 10:32:51 CST 2016
	 */
	public void setEditorTime(Date editorTime) {
		this.editorTime = editorTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column data_user_info.is_used
	 * @return  the value of data_user_info.is_used
	 * @mbggenerated  Fri Apr 22 10:32:51 CST 2016
	 */
	public Byte getIsUsed() {
		return isUsed;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column data_user_info.is_used
	 * @param isUsed  the value for data_user_info.is_used
	 * @mbggenerated  Fri Apr 22 10:32:51 CST 2016
	 */
	public void setIsUsed(Byte isUsed) {
		this.isUsed = isUsed;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column data_user_info.share_code
	 * @return  the value of data_user_info.share_code
	 * @mbggenerated  Fri Apr 22 10:32:51 CST 2016
	 */
	public String getShareCode() {
		return shareCode;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column data_user_info.share_code
	 * @param shareCode  the value for data_user_info.share_code
	 * @mbggenerated  Fri Apr 22 10:32:51 CST 2016
	 */
	public void setShareCode(String shareCode) {
		this.shareCode = shareCode;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column data_user_info.share_code_tui
	 * @return  the value of data_user_info.share_code_tui
	 * @mbggenerated  Fri Apr 22 10:32:51 CST 2016
	 */
	public String getShareCodeTui() {
		return shareCodeTui;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column data_user_info.share_code_tui
	 * @param shareCodeTui  the value for data_user_info.share_code_tui
	 * @mbggenerated  Fri Apr 22 10:32:51 CST 2016
	 */
	public void setShareCodeTui(String shareCodeTui) {
		this.shareCodeTui = shareCodeTui;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column data_user_info.integral
	 * @return  the value of data_user_info.integral
	 * @mbggenerated  Fri Apr 22 10:32:51 CST 2016
	 */
	public Double getIntegral() {
		return integral;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column data_user_info.integral
	 * @param integral  the value for data_user_info.integral
	 * @mbggenerated  Fri Apr 22 10:32:51 CST 2016
	 */
	public void setIntegral(Double integral) {
		this.integral = integral;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column data_user_info.head
	 * @return  the value of data_user_info.head
	 * @mbggenerated  Fri Apr 22 10:32:51 CST 2016
	 */
	public String getHead() {
		return head;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column data_user_info.head
	 * @param head  the value for data_user_info.head
	 * @mbggenerated  Fri Apr 22 10:32:51 CST 2016
	 */
	public void setHead(String head) {
		this.head = head;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column data_user_info.share_time
	 * @return  the value of data_user_info.share_time
	 * @mbggenerated  Fri Apr 22 10:32:51 CST 2016
	 */
	public Date getShareTime() {
		return shareTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column data_user_info.share_time
	 * @param shareTime  the value for data_user_info.share_time
	 * @mbggenerated  Fri Apr 22 10:32:51 CST 2016
	 */
	public void setShareTime(Date shareTime) {
		this.shareTime = shareTime;
	}

	public String getSsoUserId() {
		return ssoUserId;
	}

	public void setSsoUserId(String ssoUserId) {
		this.ssoUserId = ssoUserId;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}
}