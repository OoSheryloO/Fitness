package com.huban.construct;

import java.math.BigDecimal;

public class LstDeptMemberModel {
	private long userId;
	
	private String userName;
	
	private String userHeadicon;
	
	private Integer userinfoPosition;
	
	private BigDecimal userinfoReadcount;
	
	private int joinStatus;
	
	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserHeadicon() {
		return userHeadicon;
	}

	public void setUserHeadicon(String userHeadicon) {
		this.userHeadicon = userHeadicon;
	}

	public Integer getUserinfoPosition() {
		return userinfoPosition;
	}

	public void setUserinfoPosition(Integer userinfoPosition) {
		this.userinfoPosition = userinfoPosition;
	}


	/**
	 * @return userinfoReadcount
	 */
	
	public BigDecimal getUserinfoReadcount() {
		return userinfoReadcount;
	}

	/**
	 * @param userinfoReadcount the userinfoReadcount to set
	 */
	
	public void setUserinfoReadcount(BigDecimal userinfoReadcount) {
		this.userinfoReadcount = userinfoReadcount;
	}

	public int getJoinStatus() {
		return joinStatus;
	}

	public void setJoinStatus(int joinStatus) {
		this.joinStatus = joinStatus;
	}

	
}
