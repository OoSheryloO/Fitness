package com.huban.pojo;

import java.util.Date;

import com.huban.util.IdWorker;

public class PushRecords {
    private Long pushrecordId;

    private Long pushrecordUserid;

    private Long pushrecordPushid;
    
    private Long pushrecordPushuserid;

    private Integer pushrecordTypeid;

    private String pushrecordTitle;

    private String pushrecordContent;

    private Date pushrecordCreatetime;

    private Date pushrecordModifytime;

    private Integer pushrecordVersion;

    private Byte pushrecordStatus;

    private Byte pushrecordDeleted;
    
    private String ByName;
    
    public PushRecords() {
        super();
        this.pushrecordId = IdWorker.CreateNewID();
    }

    public Long getPushrecordId() {
        return pushrecordId;
    }

    public void setPushrecordId(Long pushrecordId) {
        this.pushrecordId = pushrecordId;
    }

    public Long getPushrecordUserid() {
        return pushrecordUserid;
    }

    public void setPushrecordUserid(Long pushrecordUserid) {
        this.pushrecordUserid = pushrecordUserid;
    }

    public Long getPushrecordPushid() {
        return pushrecordPushid;
    }

    public void setPushrecordPushid(Long pushrecordPushid) {
        this.pushrecordPushid = pushrecordPushid;
    }

    /**
	 * @return pushrecordPushuserid
	 */
	
	public Long getPushrecordPushuserid() {
		return pushrecordPushuserid;
	}

	/**
	 * @param pushrecordPushuserid the pushrecordPushuserid to set
	 */
	
	public void setPushrecordPushuserid(Long pushrecordPushuserid) {
		this.pushrecordPushuserid = pushrecordPushuserid;
	}

	public Integer getPushrecordTypeid() {
        return pushrecordTypeid;
    }

    public void setPushrecordTypeid(Integer pushrecordTypeid) {
        this.pushrecordTypeid = pushrecordTypeid;
    }

    public String getPushrecordTitle() {
        return pushrecordTitle;
    }

    public void setPushrecordTitle(String pushrecordTitle) {
        this.pushrecordTitle = pushrecordTitle == null ? null : pushrecordTitle.trim();
    }

    public String getPushrecordContent() {
        return pushrecordContent;
    }

    public void setPushrecordContent(String pushrecordContent) {
        this.pushrecordContent = pushrecordContent == null ? null : pushrecordContent.trim();
    }

    public Date getPushrecordCreatetime() {
        return pushrecordCreatetime;
    }

    public void setPushrecordCreatetime(Date pushrecordCreatetime) {
        this.pushrecordCreatetime = pushrecordCreatetime;
    }

    public Date getPushrecordModifytime() {
        return pushrecordModifytime;
    }

    public void setPushrecordModifytime(Date pushrecordModifytime) {
        this.pushrecordModifytime = pushrecordModifytime;
    }

    public Integer getPushrecordVersion() {
        return pushrecordVersion;
    }

    public void setPushrecordVersion(Integer pushrecordVersion) {
        this.pushrecordVersion = pushrecordVersion;
    }

    public Byte getPushrecordStatus() {
        return pushrecordStatus;
    }

    public void setPushrecordStatus(Byte pushrecordStatus) {
        this.pushrecordStatus = pushrecordStatus;
    }

    public Byte getPushrecordDeleted() {
        return pushrecordDeleted;
    }

    public void setPushrecordDeleted(Byte pushrecordDeleted) {
        this.pushrecordDeleted = pushrecordDeleted;
    }

	/**
	 * @return byName
	 */
	
	public String getByName() {
		if (ByName == null || "".equals(ByName)) {
			ByName = "-";
		}
		return ByName;
	}

	/**
	 * @param byName the byName to set
	 */
	
	public void setByName(String byName) {
		ByName = byName;
	}
    
}