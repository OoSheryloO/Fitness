package com.huban.pojo;

public class DepartmentWithBLOBs extends Department {
	private String departmentName;

    private String departmentNotice;

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getDepartmentNotice() {
		return departmentNotice;
	}

	public void setDepartmentNotice(String departmentNotice) {
		this.departmentNotice = departmentNotice;
	}

}