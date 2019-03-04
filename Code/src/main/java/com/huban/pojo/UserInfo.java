package com.huban.pojo;

import java.math.BigDecimal;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

public class UserInfo {
	
	public static final String sUserInfoClass = "UserInfo";
	
    private String userinfoNumber;
    @JSONField(serialize=false)
    private Long userinfoUserid;

    private BigDecimal userinfoReadcount;

    private Long userinfoAddressid;

    private String userinfoAddress;

    private Integer userinfoGrade;

    private Integer userinfoClass;

    private String userinfoDepartmentid;

    private String userinfoBelongdept;

    private Integer userinfoPosition;

    private Integer userinfoIdentity;
    
    private String userinfoPatriarchid;

    private Integer userinfoHonor;

    private Integer userinfoStarlevel;

    private Integer userinfoStatus;
    @JSONField(serialize=false)
    private Date userinfoCreatetime;
    @JSONField(serialize=false)
    private Date userinfoModifytime;

    private String userinfoSignature;

	public String getUserinfoNumber() {
		return userinfoNumber;
	}

	public void setUserinfoNumber(String userinfoNumber) {
		this.userinfoNumber = userinfoNumber;
	}

	public Long getUserinfoUserid() {
        return userinfoUserid;
    }

    public void setUserinfoUserid(Long userinfoUserid) {
        this.userinfoUserid = userinfoUserid;
    }

    public BigDecimal getUserinfoReadcount() {
		return userinfoReadcount;
	}

	public void setUserinfoReadcount(BigDecimal userinfoReadcount) {
		this.userinfoReadcount = userinfoReadcount;
	}

	public static String getSuserinfoclass() {
		return sUserInfoClass;
	}

	public String getUserinfoDepartmentid() {
		return userinfoDepartmentid;
	}

	public void setUserinfoDepartmentid(String userinfoDepartmentid) {
		this.userinfoDepartmentid = userinfoDepartmentid;
	}

	public String getUserinfoAddress() {
        return userinfoAddress;
    }

    public void setUserinfoAddress(String userinfoAddress) {
        this.userinfoAddress = userinfoAddress == null ? null : userinfoAddress.trim();
    }

    public Long getUserinfoAddressid() {
        return userinfoAddressid;
    }

    public void setUserinfoAddressid(Long userinfoAddressid) {
        this.userinfoAddressid = userinfoAddressid;
    }

    public Integer getUserinfoGrade() {
		return userinfoGrade;
	}

	public void setUserinfoGrade(Integer userinfoGrade) {
		this.userinfoGrade = userinfoGrade;
	}

	public Integer getUserinfoClass() {
		return userinfoClass;
	}

	public void setUserinfoClass(Integer userinfoClass) {
		this.userinfoClass = userinfoClass;
	}

    public String getUserinfoBelongdept() {
		return userinfoBelongdept;
	}

	public void setUserinfoBelongdept(String userinfoBelongdept) {
		this.userinfoBelongdept = userinfoBelongdept;
	}

	public Integer getUserinfoPosition() {
        return userinfoPosition;
    }

    public void setUserinfoPosition(Integer userinfoPosition) {
        this.userinfoPosition = userinfoPosition;
    }

    public Integer getUserinfoIdentity() {
        return userinfoIdentity;
    }

    public void setUserinfoIdentity(Integer userinfoIdentity) {
        this.userinfoIdentity = userinfoIdentity;
    }

    public String getUserinfoPatriarchid() {
		return userinfoPatriarchid;
	}

	public void setUserinfoPatriarchid(String userinfoPatriarchid) {
		this.userinfoPatriarchid = userinfoPatriarchid;
	}

	public Integer getUserinfoHonor() {
        return userinfoHonor;
    }

    public void setUserinfoHonor(Integer userinfoHonor) {
        this.userinfoHonor = userinfoHonor;
    }

    public Integer getUserinfoStarlevel() {
        return userinfoStarlevel;
    }

    public void setUserinfoStarlevel(Integer userinfoStarlevel) {
        this.userinfoStarlevel = userinfoStarlevel;
    }

    public Integer getUserinfoStatus() {
        return userinfoStatus;
    }

    public void setUserinfoStatus(Integer userinfoStatus) {
        this.userinfoStatus = userinfoStatus;
    }

    public Date getUserinfoCreatetime() {
        return userinfoCreatetime;
    }

    public void setUserinfoCreatetime(Date userinfoCreatetime) {
        this.userinfoCreatetime = userinfoCreatetime;
    }

    public Date getUserinfoModifytime() {
        return userinfoModifytime;
    }

    public void setUserinfoModifytime(Date userinfoModifytime) {
        this.userinfoModifytime = userinfoModifytime;
    }

	public String getUserinfoSignature() {
		return userinfoSignature;
	}

	public void setUserinfoSignature(String userinfoSignature) {
		this.userinfoSignature = userinfoSignature;
	}
    
}