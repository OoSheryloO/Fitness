package com.huban.pojo;

public class Grade {
	private long id;
	private String pkid;
	private long userid;
	private int type; //评测系统 1 心里测试  2 阅读素养  3 学科/项目
	private int level;
	private String createTime;
	private String modifyTime;
	private int status;
	private String grade;
	private int online;//0线上  1线下
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getPkid() {
		return pkid;
	}
	public void setPkid(String pkid) {
		this.pkid = pkid;
	}
	public long getUserid() {
		return userid;
	}
	public void setUserid(long userid) {
		this.userid = userid;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(String modifyTime) {
		this.modifyTime = modifyTime;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public int getOnline() {
		return online;
	}
	public void setOnline(int online) {
		this.online = online;
	}

	
}
