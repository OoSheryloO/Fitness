package com.huban.pojo;

import java.util.Date;

@SuppressWarnings("serial")
public class Read implements java.io.Serializable{
	 private Long readId;

	    private Long readBookid;

	    private Long readUserid;

	    private Long readSpeed;

	    private Integer readStatus;

	    private Long readTime;

	    private Date readCreatetime;

	    private Date readModifytime;

	    public Long getReadId() {
	        return readId;
	    }

	    public void setReadId(Long readId) {
	        this.readId = readId;
	    }

	    public Long getReadBookid() {
	        return readBookid;
	    }

	    public void setReadBookid(Long readBookid) {
	        this.readBookid = readBookid;
	    }

	    public Long getReadUserid() {
	        return readUserid;
	    }

	    public void setReadUserid(Long readUserid) {
	        this.readUserid = readUserid;
	    }

	    public Long getReadSpeed() {
	        return readSpeed;
	    }

	    public void setReadSpeed(Long readSpeed) {
	        this.readSpeed = readSpeed;
	    }

	    public Integer getReadStatus() {
	        return readStatus;
	    }

	    public void setReadStatus(Integer readStatus) {
	        this.readStatus = readStatus;
	    }

	    public Long getReadTime() {
	        return readTime;
	    }

	    public void setReadTime(Long readTime) {
	        this.readTime = readTime;
	    }

	    public Date getReadCreatetime() {
	        return readCreatetime;
	    }

	    public void setReadCreatetime(Date readCreatetime) {
	        this.readCreatetime = readCreatetime;
	    }

	    public Date getReadModifytime() {
	        return readModifytime;
	    }

	    public void setReadModifytime(Date readModifytime) {
	        this.readModifytime = readModifytime;
	    }
	}