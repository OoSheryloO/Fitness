package com.huban.pojo;

import java.util.Date;
@SuppressWarnings("serial")
public class Onlines implements java.io.Serializable{
	
	    public static final String onLineClass = "onLine";
	    public static final String attributeOnLineID = "iD";
	    public static final String attributeOnLineUserID = "userID";
	    public static final String attributeOnLineSession = "session";
	    public static final String attributeOnLineCreateTime = "createTime";
	    public static final String attributeOnLineModifyTime = "modifyTime";
	    public static final String attributeOnLineVersion = "version";
	    public static final String attributeOnLineStatus = "status";
	    public static final String attributeOnLineDeleted = "deleted";
	
	
    private Long onlineId;

    private Long onlineUserid;

    private String onlineSession;

    private Date onlineCreatetime;

    private Date onlineModifytime;

    private Integer onlineVersion;

    private Byte onlineStatus;

    private Byte onlineDeleted;

    public Long getOnlineId() {
        return onlineId;
    }

    public void setOnlineId(Long onlineId) {
        this.onlineId = onlineId;
    }

    public Long getOnlineUserid() {
        return onlineUserid;
    }

    public void setOnlineUserid(Long onlineUserid) {
        this.onlineUserid = onlineUserid;
    }

    public String getOnlineSession() {
        return onlineSession;
    }

    public void setOnlineSession(String onlineSession) {
        this.onlineSession = onlineSession == null ? null : onlineSession.trim();
    }

    public Date getOnlineCreatetime() {
        return onlineCreatetime;
    }

    public void setOnlineCreatetime(Date onlineCreatetime) {
        this.onlineCreatetime = onlineCreatetime;
    }

    public Date getOnlineModifytime() {
        return onlineModifytime;
    }

    public void setOnlineModifytime(Date onlineModifytime) {
        this.onlineModifytime = onlineModifytime;
    }

    public Integer getOnlineVersion() {
        return onlineVersion;
    }

    public void setOnlineVersion(Integer onlineVersion) {
        this.onlineVersion = onlineVersion;
    }

    public Byte getOnlineStatus() {
        return onlineStatus;
    }

    public void setOnlineStatus(Byte onlineStatus) {
        this.onlineStatus = onlineStatus;
    }

    public Byte getOnlineDeleted() {
        return onlineDeleted;
    }

    public void setOnlineDeleted(Byte onlineDeleted) {
        this.onlineDeleted = onlineDeleted;
    }
}