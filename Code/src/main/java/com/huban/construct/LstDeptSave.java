package com.huban.construct;

import java.util.Date;

@SuppressWarnings("serial")
public class LstDeptSave implements java.io.Serializable {
	private String userName;
	
	private String userHeadicon;
	
    private Long rsNumber;
    
    private String rsTitle;

    private String rsReason;

    private String rsPublish;
    
    private String rsAuthor;

    private Date rsStarttime;

    private Date rsEndtime;

    private String rsStatus;
    
    private String	rsGrade;
    
//    private String rsAnswerQuiz;
//    
//    private String rsDiyIssue;
//    
    private int rsType;
    
    private Date rsCreatetime;

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

	public Long getRsNumber() {
		return rsNumber;
	}

	public void setRsNumber(Long rsNumber) {
		this.rsNumber = rsNumber;
	}

	public String getRsTitle() {
		return rsTitle;
	}

	public void setRsTitle(String rsTitle) {
		this.rsTitle = rsTitle;
	}

	public String getRsReason() {
		return rsReason;
	}

	public void setRsReason(String rsReason) {
		this.rsReason = rsReason;
	}

	public String getRsPublish() {
		return rsPublish;
	}

	public void setRsPublish(String rsPublish) {
		this.rsPublish = rsPublish;
	}

	public String getRsAuthor() {
		return rsAuthor;
	}

	public void setRsAuthor(String rsAuthor) {
		this.rsAuthor = rsAuthor;
	}

	public Date getRsStarttime() {
		return rsStarttime;
	}

	public void setRsStarttime(Date rsStarttime) {
		this.rsStarttime = rsStarttime;
	}

	public Date getRsEndtime() {
		return rsEndtime;
	}

	public void setRsEndtime(Date rsEndtime) {
		this.rsEndtime = rsEndtime;
	}

	public String getRsStatus() {
		return rsStatus;
	}

	public void setRsStatus(String rsStatus) {
		this.rsStatus = rsStatus;
	}

	public String getRsGrade() {
		return rsGrade;
	}

	public void setRsGrade(String rsGrade) {
		this.rsGrade = rsGrade;
	}

	/**
	 * @return rsType
	 */
	
	public int getRsType() {
		return rsType;
	}

	/**
	 * @param rsType the rsType to set
	 */
	
	public void setRsType(int rsType) {
		this.rsType = rsType;
	}

	public Date getRsCreatetime() {
		return rsCreatetime;
	}

	public void setRsCreatetime(Date rsCreatetime) {
		this.rsCreatetime = rsCreatetime;
	}


}