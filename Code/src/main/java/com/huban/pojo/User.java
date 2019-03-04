package com.huban.pojo;

import java.math.BigDecimal;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
@SuppressWarnings("serial")
public class User implements java.io.Serializable{
	
	public static final String sUserClass = "User";
	public static final String sUserIDClass = "UserId";
    private Long userId;
    
    private Long userNumericalOrder;

    private String userName;

    private Integer userSex;

    private Date userBirthday;

    private String userPhone;
 
    private String userWechatopenid;
    
	private String userHeadicon;

    private String userQrcode;

    private Integer userLevel;
    
    @JSONField(serialize=false)
    private String userPassword;
    @JSONField(serialize=false)
    private String userPaypassword;
    @JSONField(serialize=false)
    private String userRestspassword;
    @JSONField(serialize=false)
    private Date userCreatetime;
    @JSONField(serialize=false)
    private Date userModifytime;
    @JSONField(serialize=false)
    private Integer userDelete;
    @JSONField(serialize=false)
    private Integer userStatus;

    private Long userMoney;
 
    private String userReadtime;
    @JSONField(serialize=false)
    private Date userLimittime;

	private String userCity;
    
    private Long userBanlance;
    
    private BigDecimal userIntgral;
    
    private BigDecimal userRemainIntgral;
    
    public Long getUserBanlance() {
		return userBanlance;
	}

	public void setUserBanlance(Long userBanlance) {
		this.userBanlance = userBanlance;
	}

	public void setUserIntgral(BigDecimal userIntgral) {
		this.userIntgral = userIntgral;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	/**
	 * @return the userCity
	 */
	public String getUserCity() {
		return userCity;
	}

	/**
	 * @param userCity the userCity to set
	 */
	public void setUserCity(String userCity) {
		this.userCity = userCity;
	}

	/**
	 * @return the userLimittime
	 */
	public Date getUserLimittime() {
		return userLimittime;
	}

	/**
	 * @param userLimittime the userLimittime to set
	 */
	public void setUserLimittime(Date userLimittime) {
		this.userLimittime = userLimittime;
	}

	public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

	/**
	 * @return userNumericalOrder
	 */
	
	public Long getUserNumericalOrder() {
		return userNumericalOrder;
	}

	/**
	 * @param userNumericalOrder the userNumericalOrder to set
	 */
	
	public void setUserNumericalOrder(Long userNumericalOrder) {
		this.userNumericalOrder = userNumericalOrder;
	}

	public Integer getUserSex() {
        return userSex;
    }

    public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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
        this.userPhone = userPhone == null ? null : userPhone.trim();
    }

    public String getUserWechatopenid() {
		return userWechatopenid;
	}

	public void setUserWechatopenid(String userWechatopenid) {
		this.userWechatopenid = userWechatopenid;
	}

	public String getUserHeadicon() {
        return userHeadicon;
    }

    public void setUserHeadicon(String userHeadicon) {
        this.userHeadicon = userHeadicon == null ? null : userHeadicon.trim();
    }

    public String getUserQrcode() {
        return userQrcode;
    }

    public void setUserQrcode(String userQrcode) {
        this.userQrcode = userQrcode == null ? null : userQrcode.trim();
    }

    public Integer getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(Integer userLevel) {
        this.userLevel = userLevel;
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

	public String getUserPaypassword() {
        return userPaypassword;
    }

    public void setUserPaypassword(String userPaypassword) {
        this.userPaypassword = userPaypassword;
    }

	public String getUserRestspassword() {
		return userRestspassword;
	}

	public void setUserRestspassword(String userRestspassword) {
		this.userRestspassword = userRestspassword;
	}

	public static String getSuserclass() {
		return sUserClass;
	}

	public Date getUserCreatetime() {
        return userCreatetime;
    }

    public void setUserCreatetime(Date userCreatetime) {
        this.userCreatetime = userCreatetime;
    }

    public Date getUserModifytime() {
        return userModifytime;
    }

    public void setUserModifytime(Date userModifytime) {
        this.userModifytime = userModifytime;
    }

    public Integer getUserDelete() {
        return userDelete;
    }

    public void setUserDelete(Integer userDelete) {
        this.userDelete = userDelete;
    }

    public Integer getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(Integer userStatus) {
        this.userStatus = userStatus;
    }

    public Long getUserMoney() {
        return userMoney;
    }

    public void setUserMoney(Long userMoney) {
        this.userMoney = userMoney;
    }

    public String getUserReadtime() {
        return userReadtime;
    }

    public void setUserReadtime(String userReadtime) {
        this.userReadtime = userReadtime == null ? null : userReadtime.trim();
    }
}