package com.huban.construct;

import java.util.Date;
import java.util.List;

public class LstDeptModel {
	private long deptId;

	private String deptNumber;

	private String deptLogourl;

	private Float deptLevel;
	
	private String deptName;
	
	private String deptNotice;
	
	private String deptPresident;

	private String deptFounder;
	
	private int deptTotaluser;
	
	private long deptTotalRead;

	private int deptStatus;

	private Date deptCreatetime;
	
	private List<String> deptUserHeadIcon;

	public long getDeptId() {
		return deptId;
	}

	public void setDeptId(long deptId) {
		this.deptId = deptId;
	}

	public String getDeptNumber() {
		return deptNumber;
	}

	public void setDeptNumber(String deptNumber) {
		this.deptNumber = deptNumber;
	}

	public String getDeptLogourl() {
		return deptLogourl;
	}

	public void setDeptLogourl(String deptLogourl) {
		this.deptLogourl = deptLogourl;
	}

	public Float getDeptLevel() {
		return deptLevel;
	}

	public void setDeptLevel(Float deptLevel) {
		this.deptLevel = deptLevel;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getDeptNotice() {
		return deptNotice;
	}

	public void setDeptNotice(String deptNotice) {
		this.deptNotice = deptNotice;
	}

	public String getDeptPresident() {
		return deptPresident;
	}

	public void setDeptPresident(String deptPresident) {
		this.deptPresident = deptPresident;
	}

	public String getDeptFounder() {
		return deptFounder;
	}

	public void setDeptFounder(String deptFounder) {
		this.deptFounder = deptFounder;
	}

	public int getDeptTotaluser() {
		return deptTotaluser;
	}

	public void setDeptTotaluser(int deptTotaluser) {
		this.deptTotaluser = deptTotaluser;
	}

	public long getDeptTotalRead() {
		return deptTotalRead;
	}

	public void setDeptTotalRead(long deptTotalRead) {
		this.deptTotalRead = deptTotalRead;
	}

	public int getDeptStatus() {
		return deptStatus;
	}

	public void setDeptStatus(int deptStatus) {
		this.deptStatus = deptStatus;
	}

	public Date getDeptCreatetime() {
		return deptCreatetime;
	}

	public void setDeptCreatetime(Date deptCreatetime) {
		this.deptCreatetime = deptCreatetime;
	}

	public List<String> getDeptUserHeadIcon() {
		return deptUserHeadIcon;
	}

	public void setDeptUserHeadIcon(List<String> deptUserHeadIcon) {
		this.deptUserHeadIcon = deptUserHeadIcon;
	}

}
