package com.huban.pojo;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Date implements Serializable{
    private Long dateId;
    
    private Long dateUserid;
    
    private Long dateBelongid;

    private String dateValue;

    private Integer dateType;

    private Integer dateStatus;
    
    private java.util.Date dateCreatetime;

    private java.util.Date dateModifytime;

    private Integer dateVersion;

    private Integer dateDelete;

    private Long dateNumber;

    private String dateUrl;

    public Long getDateId() {
        return dateId;
    }

    public void setDateId(Long dateId) {
        this.dateId = dateId;
    }
    
    public Long getDateUserid() {
		return dateUserid;
	}

	public void setDateUserid(Long dateUserid) {
		this.dateUserid = dateUserid;
	}

	public Long getDateBelongid() {
		return dateBelongid;
	}

	public void setDateBelongid(Long dateBelongid) {
		this.dateBelongid = dateBelongid;
	}

	public String getDateValue() {
        return dateValue;
    }

    public void setDateValue(String dateValue) {
        this.dateValue = dateValue == null ? null : dateValue.trim();
    }

    public Integer getDateType() {
        return dateType;
    }

    public void setDateType(Integer dateType) {
        this.dateType = dateType;
    }

    public Integer getDateStatus() {
        return dateStatus;
    }

    public void setDateStatus(Integer dateStatus) {
        this.dateStatus = dateStatus;
    }

    public java.util.Date getDateCreatetime() {
        return dateCreatetime;
    }

    public void setDateCreatetime(java.util.Date dateCreatetime) {
        this.dateCreatetime = dateCreatetime;
    }

    public java.util.Date getDateModifytime() {
        return dateModifytime;
    }

    public void setDateModifytime(java.util.Date dateModifytime) {
        this.dateModifytime = dateModifytime;
    }

    public Integer getDateVersion() {
        return dateVersion;
    }

    public void setDateVersion(Integer dateVersion) {
        this.dateVersion = dateVersion;
    }

    public Integer getDateDelete() {
        return dateDelete;
    }

    public void setDateDelete(Integer dateDelete) {
        this.dateDelete = dateDelete;
    }

    public Long getDateNumber() {
        return dateNumber;
    }

    public void setDateNumber(Long dateNumber) {
        this.dateNumber = dateNumber;
    }

    public String getDateUrl() {
        return dateUrl;
    }

    public void setDateUrl(String dateUrl) {
        this.dateUrl = dateUrl == null ? null : dateUrl.trim();
    }
}