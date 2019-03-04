package com.huban.construct;

import java.math.BigDecimal;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

@SuppressWarnings("serial")
public class UserInfoModel implements java.io.Serializable{
	private long userId;
	
	private Long numericalOrder;
	
	private String userHeadIcon;
	
    private String userName;

    private Integer userSex;

    private Date userBirthday;

    private String userPhone;
    
    private BigDecimal userMoney;
    
    private BigDecimal userIntgral;
    
    private BigDecimal userRemainIntgral;
    @JSONField(serialize=false)
    private int userStatus;
    
    private int userInvited;
    
    private boolean payPasswordExist;
    
    private boolean restsPasswordExist;
    
    private UserInfoUserStatuModel infoModel;
    
    
	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	/**
	 * @return numericalOrder
	 */
	
	public Long getNumericalOrder() {
		return numericalOrder;
	}

	/**
	 * @param numericalOrder the numericalOrder to set
	 */
	
	public void setNumericalOrder(Long numericalOrder) {
		this.numericalOrder = numericalOrder;
	}

	public String getUserHeadIcon() {
		return userHeadIcon;
	}

	public void setUserHeadIcon(String userHeadIcon) {
		this.userHeadIcon = userHeadIcon;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getUserSex() {
		return userSex;
	}

	public void setUserSex(Integer userSex) {
		this.userSex = userSex;
	}

	public Date getUserBirthday() {
		return userBirthday;
	}

	public void setUserBirthday(Date userBirthday) {
		this.userBirthday = userBirthday;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public BigDecimal getUserMoney() {
		return userMoney;
	}

	public void setUserMoney(BigDecimal userMoney) {
		this.userMoney = userMoney;
	}

	public BigDecimal getUserRemainIntgral() {
		return userRemainIntgral;
	}

	public void setUserRemainIntgral(BigDecimal userRemainIntgral) {
		this.userRemainIntgral = userRemainIntgral;
	}

	public BigDecimal getUserIntgral() {
		return userIntgral;
	}

	public void setUserIntgral(BigDecimal userIntgral) {
		this.userIntgral = userIntgral;
	}

	public UserInfoUserStatuModel getInfoModel() {
		return infoModel;
	}

	public void setInfoModel(UserInfoUserStatuModel infoModel) {
		this.infoModel = infoModel;
	}

	public int getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(int userStatus) {
		this.userStatus = userStatus;
	}

	public int getUserInvited() {
		return userInvited;
	}

	public void setUserInvited(int userInvited) {
		this.userInvited = userInvited;
	}

	public boolean getPayPasswordExist() {
		return payPasswordExist;
	}

	public void setPayPasswordExist(int payPasswordExist) {
		boolean flag = false;
		if (payPasswordExist == 1) {
			flag = true;
		}
		this.payPasswordExist = flag;
	}

	public boolean getRestsPasswordExist() {
		return restsPasswordExist;
	}

	public void setRestsPasswordExist(int restsPasswordExist) {
		boolean flag = false;
		if (restsPasswordExist == 1) {
			flag = true;
		}
		this.restsPasswordExist = flag;
	}

}