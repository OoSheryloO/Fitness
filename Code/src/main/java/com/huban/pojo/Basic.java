/**
 * 
 */
package com.huban.pojo;

/**
 * @author GeJiangbo
 * @date 2017年5月15日
 */
@SuppressWarnings("serial")
public class Basic implements java.io.Serializable{
    //验证验证码接口
	private String phone;
	private String code;
	//微信登录接口
	private String wechatId;
	private String wechatSex;
	private String wechatName;
	private String wechatHeadIcon;
	private String wechatCity;
	private String identity;
	//各种列表所需参数
	private String page;
	private String size;
	private String start;
	/**
	 * @return the page
	 */
	public String getPage() {
		return page;
	}
	/**
	 * @param page the page to set
	 */
	public void setPage(String page) {
		this.page = page;
	}
	/**
	 * @return the size
	 */
	public String getSize() {
		return size;
	}
	/**
	 * @param size the size to set
	 */
	public void setSize(String size) {
		this.size = size;
	}
	/**
	 * @return the start
	 */
	public String getStart() {
		return start;
	}
	/**
	 * @param start the start to set
	 */
	public void setStart(String start) {
		this.start = start;
	}
	
	/**
	 * @return the identity
	 */
	public String getIdentity() {
		return identity;
	}
	/**
	 * @param identity the identity to set
	 */
	public void setIdentity(String identity) {
		this.identity = identity;
	}
	/**
	 * @return the wechatCity
	 */
	public String getWechatCity() {
		return wechatCity;
	}
	/**
	 * @param wechatCity the wechatCity to set
	 */
	public void setWechatCity(String wechatCity) {
		this.wechatCity = wechatCity;
	}
	/**
	 * @return the wechatHeadIcon
	 */
	public String getWechatHeadIcon() {
		return wechatHeadIcon;
	}
	/**
	 * @param wechatHeadIcon the wechatHeadIcon to set
	 */
	public void setWechatHeadIcon(String wechatHeadIcon) {
		this.wechatHeadIcon = wechatHeadIcon;
	}
	/**
	 * @return the wechatId
	 */
	public String getWechatId() {
		return wechatId;
	}
	/**
	 * @param wechatId the wechatId to set
	 */
	public void setWechatId(String wechatId) {
		this.wechatId = wechatId;
	}
	/**
	 * @return the wechatSex
	 */
	public String getWechatSex() {
		return wechatSex;
	}
	/**
	 * @param wechatSex the wechatSex to set
	 */
	public void setWechatSex(String wechatSex) {
		this.wechatSex = wechatSex;
	}
	/**
	 * @return the wechatName
	 */
	public String getWechatName() {
		return wechatName;
	}
	/**
	 * @param wechatName the wechatName to set
	 */
	public void setWechatName(String wechatName) {
		this.wechatName = wechatName;
	}
	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}
	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}
	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}
	
}
