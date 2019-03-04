package com.huban.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class Department {
    private Long departmentId;

    private String departmentNumber;

    private String departmentLogourl;
    
    private BigDecimal departmentAmount;

    private Float departmentLevel;

    private Long departmentRegionid;

    private String departmentRegion;

    private Long departmentBranchid;

    private String departmentRedundancy;

    private Long departmentFounder;

    private Integer departmentStatus;

    private Integer departmentDelete;

    private Date departmentCreatetime;

    private Date departmentModifytime;

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentNumber() {
        return departmentNumber;
    }

    public void setDepartmentNumber(String departmentNumber) {
        this.departmentNumber = departmentNumber == null ? null : departmentNumber.trim();
    }

    public String getDepartmentLogourl() {
        return departmentLogourl;
    }

    public void setDepartmentLogourl(String departmentLogourl) {
        this.departmentLogourl = departmentLogourl == null ? null : departmentLogourl.trim();
    }

    /**
	 * @return departmentAmount
	 */
	
	public BigDecimal getDepartmentAmount() {
		return departmentAmount;
	}

	/**
	 * @param departmentAmount the departmentAmount to set
	 */
	
	public void setDepartmentAmount(BigDecimal departmentAmount) {
		this.departmentAmount = departmentAmount;
	}

	public Float getDepartmentLevel() {
        return departmentLevel;
    }

    public void setDepartmentLevel(Float departmentLevel) {
        this.departmentLevel = departmentLevel;
    }

    public Long getDepartmentRegionid() {
        return departmentRegionid;
    }

    public void setDepartmentRegionid(Long departmentRegionid) {
        this.departmentRegionid = departmentRegionid;
    }

    public String getDepartmentRegion() {
        return departmentRegion;
    }

    public void setDepartmentRegion(String departmentRegion) {
        this.departmentRegion = departmentRegion == null ? null : departmentRegion.trim();
    }

    public Long getDepartmentBranchid() {
        return departmentBranchid;
    }

    public void setDepartmentBranchid(Long departmentBranchid) {
        this.departmentBranchid = departmentBranchid;
    }

    public String getDepartmentRedundancy() {
        return departmentRedundancy;
    }

    public void setDepartmentRedundancy(String departmentRedundancy) {
        this.departmentRedundancy = departmentRedundancy == null ? null : departmentRedundancy.trim();
    }

    public Long getDepartmentFounder() {
        return departmentFounder;
    }

    public void setDepartmentFounder(Long departmentFounder) {
        this.departmentFounder = departmentFounder;
    }

    public Integer getDepartmentStatus() {
        return departmentStatus;
    }

    public void setDepartmentStatus(Integer departmentStatus) {
        this.departmentStatus = departmentStatus;
    }

    public Integer getDepartmentDelete() {
        return departmentDelete;
    }

    public void setDepartmentDelete(Integer departmentDelete) {
        this.departmentDelete = departmentDelete;
    }

    public Date getDepartmentCreatetime() {
        return departmentCreatetime;
    }

    public void setDepartmentCreatetime(Date departmentCreatetime) {
        this.departmentCreatetime = departmentCreatetime;
    }

    public Date getDepartmentModifytime() {
        return departmentModifytime;
    }

    public void setDepartmentModifytime(Date departmentModifytime) {
        this.departmentModifytime = departmentModifytime;
    }
}