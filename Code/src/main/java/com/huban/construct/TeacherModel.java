package com.huban.construct;

import java.util.Date;

@SuppressWarnings("serial")
public class TeacherModel implements java.io.Serializable{
	private long teacherId;
	private String teacherName;
	private String teacherHeadIcon;
	private String teacherSignature;
	private Date issueTime;
	/**
	 * @return teacherId
	 */
	
	public long getTeacherId() {
		return teacherId;
	}
	/**
	 * @param teacherId the teacherId to set
	 */
	
	public void setTeacherId(long teacherId) {
		this.teacherId = teacherId;
	}
	/**
	 * @return teacherName
	 */
	
	public String getTeacherName() {
		return teacherName;
	}
	/**
	 * @param teacherName the teacherName to set
	 */
	
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
	/**
	 * @return teacherHeadIcon
	 */
	
	public String getTeacherHeadIcon() {
		return teacherHeadIcon;
	}
	/**
	 * @param teacherHeadIcon the teacherHeadIcon to set
	 */
	
	public void setTeacherHeadIcon(String teacherHeadIcon) {
		this.teacherHeadIcon = teacherHeadIcon;
	}
	/**
	 * @return teacherSignature
	 */
	
	public String getTeacherSignature() {
		return teacherSignature;
	}
	/**
	 * @param teacherSignature the teacherSignature to set
	 */
	
	public void setTeacherSignature(String teacherSignature) {
		this.teacherSignature = teacherSignature;
	}
	/**
	 * @return issueTime
	 */
	
	public Date getIssueTime() {
		return issueTime;
	}
	/**
	 * @param issueTime the issueTime to set
	 */
	
	public void setIssueTime(Date issueTime) {
		this.issueTime = issueTime;
	}
	
	
}