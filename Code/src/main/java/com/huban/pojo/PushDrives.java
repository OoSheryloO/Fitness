package com.huban.pojo;

import java.util.Date;

public class PushDrives {
	public static final String sPushDriveClass = "PushDrive";
	
    private Long pushdriveId;

    private Long pushdriveUserid;

    private Long pushdriveDeviceid;

    private String pushdriveChannel;

    private String pushdriveToken;

    private Date pushdriveCreatetime;

    private Date pushdriveModifytime;

    private Integer pushdriveVersion;

    private Byte pushdriveStatus;

    private Byte pushdriveDeleted;

    public Long getPushdriveId() {
        return pushdriveId;
    }

    public void setPushdriveId(Long pushdriveId) {
        this.pushdriveId = pushdriveId;
    }

    public Long getPushdriveUserid() {
        return pushdriveUserid;
    }

    public void setPushdriveUserid(Long pushdriveUserid) {
        this.pushdriveUserid = pushdriveUserid;
    }

    public Long getPushdriveDeviceid() {
        return pushdriveDeviceid;
    }

    public void setPushdriveDeviceid(Long pushdriveDeviceid) {
        this.pushdriveDeviceid = pushdriveDeviceid;
    }

    public String getPushdriveChannel() {
        return pushdriveChannel;
    }

    public void setPushdriveChannel(String pushdriveChannel) {
        this.pushdriveChannel = pushdriveChannel == null ? null : pushdriveChannel.trim();
    }

    public String getPushdriveToken() {
        return pushdriveToken;
    }

    public void setPushdriveToken(String pushdriveToken) {
        this.pushdriveToken = pushdriveToken == null ? null : pushdriveToken.trim();
    }

    public Date getPushdriveCreatetime() {
        return pushdriveCreatetime;
    }

    public void setPushdriveCreatetime(Date pushdriveCreatetime) {
        this.pushdriveCreatetime = pushdriveCreatetime;
    }

    public Date getPushdriveModifytime() {
        return pushdriveModifytime;
    }

    public void setPushdriveModifytime(Date pushdriveModifytime) {
        this.pushdriveModifytime = pushdriveModifytime;
    }

    public Integer getPushdriveVersion() {
        return pushdriveVersion;
    }

    public void setPushdriveVersion(Integer pushdriveVersion) {
        this.pushdriveVersion = pushdriveVersion;
    }

    public Byte getPushdriveStatus() {
        return pushdriveStatus;
    }

    public void setPushdriveStatus(Byte pushdriveStatus) {
        this.pushdriveStatus = pushdriveStatus;
    }

    public Byte getPushdriveDeleted() {
        return pushdriveDeleted;
    }

    public void setPushdriveDeleted(Byte pushdriveDeleted) {
        this.pushdriveDeleted = pushdriveDeleted;
    }
}