package com.huban.construct;

import java.math.BigDecimal;

public class RankUserModel {
	private String userName;
	
	private String userHeadIcon;
	
	private BigDecimal userReadNumber;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserHeadIcon() {
		return userHeadIcon;
	}

	public void setUserHeadIcon(String userHeadIcon) {
		this.userHeadIcon = userHeadIcon;
	}

	/**
	 * @return userReadNumber
	 */
	
	public BigDecimal getUserReadNumber() {
		return userReadNumber;
	}

	/**
	 * @param userReadNumber the userReadNumber to set
	 */
	
	public void setUserReadNumber(BigDecimal userReadNumber) {
		this.userReadNumber = userReadNumber;
	}


}
