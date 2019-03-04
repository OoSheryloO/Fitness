package com.huban.pojo;

import java.util.Date;

@SuppressWarnings("serial")
public class Activitypart implements java.io.Serializable{
	
	private String activityowner;
	
	private String activityImgurl;
	
    private Long activitypartId;

    private Date activitypartCreatetime;

    private Long activitypartStudentid;

    private Long activitypartActivityid;

    private Date activitypartModifytime;

    private String activitypartContent;

    private Integer activitypartStatus;

    
  

	/**
	 * @return the activityowner
	 */
	public String getActivityowner() {
		return activityowner;
	}

	/**
	 * @param activityowner the activityowner to set
	 */
	public void setActivityowner(String activityowner) {
		this.activityowner = activityowner;
	}

	/**
	 * @return the activityImgurl
	 */
	public String getActivityImgurl() {
		return activityImgurl;
	}

	/**
	 * @param activityImgurl the activityImgurl to set
	 */
	public void setActivityImgurl(String activityImgurl) {
		this.activityImgurl = activityImgurl;
	}

	public Long getActivitypartId() {
        return activitypartId;
    }

    public void setActivitypartId(Long activitypartId) {
        this.activitypartId = activitypartId;
    }

    public Date getActivitypartCreatetime() {
        return activitypartCreatetime;
    }

    public void setActivitypartCreatetime(Date activitypartCreatetime) {
        this.activitypartCreatetime = activitypartCreatetime;
    }

    public Long getActivitypartStudentid() {
        return activitypartStudentid;
    }

    public void setActivitypartStudentid(Long activitypartStudentid) {
        this.activitypartStudentid = activitypartStudentid;
    }

    public Long getActivitypartActivityid() {
        return activitypartActivityid;
    }

    public void setActivitypartActivityid(Long activitypartActivityid) {
        this.activitypartActivityid = activitypartActivityid;
    }

    public Date getActivitypartModifytime() {
        return activitypartModifytime;
    }

    public void setActivitypartModifytime(Date activitypartModifytime) {
        this.activitypartModifytime = activitypartModifytime;
    }

    public String getActivitypartContent() {
        return activitypartContent;
    }

    public void setActivitypartContent(String activitypartContent) {
        this.activitypartContent = activitypartContent == null ? null : activitypartContent.trim();
    }

    public Integer getActivitypartStatus() {
        return activitypartStatus;
    }

    public void setActivitypartStatus(Integer activitypartStatus) {
        this.activitypartStatus = activitypartStatus;
    }
}