package com.huban.construct;

@SuppressWarnings("serial")
public class UserInfoUserStatuModel implements java.io.Serializable{
	private String number;
	
	private String signature;
	
	private String areaDept;
	
	private long userDeptId;
	
	private String userBelongdept;
	
	private String userAddress;
	 
    private Integer userGrade;

    private Integer userClass;
    
    private int userPosition;

    private Integer userHonor;

    private Integer userStarlevel;

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public String getAreaDept() {
		return areaDept;
	}

	public void setAreaDept(String areaDept) {
		this.areaDept = areaDept;
	}

	public long getUserDeptId() {
		return userDeptId;
	}

	public void setUserDeptId(long userDeptId) {
		this.userDeptId = userDeptId;
	}

	public String getUserBelongdept() {
		return userBelongdept;
	}

	public void setUserBelongdept(String userBelongdept) {
		this.userBelongdept = userBelongdept;
	}

	public String getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}

	public Integer getUserGrade() {
		return userGrade;
	}

	public void setUserGrade(Integer userGrade) {
		this.userGrade = userGrade;
	}

	public Integer getUserClass() {
		return userClass;
	}

	public void setUserClass(Integer userClass) {
		this.userClass = userClass;
	}

	public int getUserPosition() {
		return userPosition;
	}

	public void setUserPosition(int userPosition) {
		this.userPosition = userPosition;
	}

	public Integer getUserHonor() {
		return userHonor < 0 ? 0 : userHonor;
	}

	public void setUserHonor(Integer userHonor) {
		this.userHonor = userHonor;
	}

	public Integer getUserStarlevel() {
		return userStarlevel < 3 ? 3 : userStarlevel;
	}

	public void setUserStarlevel(Integer userStarlevel) {
		this.userStarlevel = userStarlevel;
	}

    
}