package com.huban.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class SaveReadManuscript {
    private Long rsId;

    private Long rsBelongid;

    private Long rsBelongdept;

    private BigDecimal rsNumber;

    private String rsTitle;

    private String rsAuthor;

    private String rsPublish;

    private String rsReason;

    private String rsReadterrace;

    private Date rsStarttime;

    private Date rsEndtime;

    private String rsStatus;

    private Integer rsDelete;

    private String rsGrade;

    private Integer rsType;

    private Integer rsMold;

    private Date rsCreatetime;

    private Date rsModifytime;

    private String rsAnswerquiz;

    private String rsDiyissue;
    
    public Long getRsId() {
        return rsId;
    }

    public void setRsId(Long rsId) {
        this.rsId = rsId;
    }

    public Long getRsBelongid() {
        return rsBelongid;
    }

    public void setRsBelongid(Long rsBelongid) {
        this.rsBelongid = rsBelongid;
    }

    public Long getRsBelongdept() {
        return rsBelongdept;
    }

    public void setRsBelongdept(Long rsBelongdept) {
        this.rsBelongdept = rsBelongdept;
    }

    public BigDecimal getRsNumber() {
        return rsNumber;
    }

    public void setRsNumber(BigDecimal rsNumber) {
        this.rsNumber = rsNumber;
    }

    public String getRsTitle() {
        return rsTitle;
    }

    public void setRsTitle(String rsTitle) {
        this.rsTitle = rsTitle == null ? null : rsTitle.trim();
    }

    public String getRsAuthor() {
        return rsAuthor;
    }

    public void setRsAuthor(String rsAuthor) {
        this.rsAuthor = rsAuthor == null ? null : rsAuthor.trim();
    }

    public String getRsPublish() {
        return rsPublish;
    }

    public void setRsPublish(String rsPublish) {
        this.rsPublish = rsPublish == null ? null : rsPublish.trim();
    }

    public String getRsReason() {
        return rsReason;
    }

    public void setRsReason(String rsReason) {
        this.rsReason = rsReason == null ? null : rsReason.trim();
    }

    public String getRsReadterrace() {
        return rsReadterrace;
    }

    public void setRsReadterrace(String rsReadterrace) {
        this.rsReadterrace = rsReadterrace == null ? null : rsReadterrace.trim();
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
        this.rsStatus = rsStatus == null ? null : rsStatus.trim();
    }

    public Integer getRsDelete() {
        return rsDelete;
    }

    public void setRsDelete(Integer rsDelete) {
        this.rsDelete = rsDelete;
    }

    public String getRsGrade() {
        return rsGrade;
    }

    public void setRsGrade(String rsGrade) {
        this.rsGrade = rsGrade == null ? null : rsGrade.trim();
    }

    public Integer getRsType() {
        return rsType;
    }

    public void setRsType(Integer rsType) {
        this.rsType = rsType;
    }

    public Integer getRsMold() {
        return rsMold;
    }

    public void setRsMold(Integer rsMold) {
        this.rsMold = rsMold;
    }

    public Date getRsCreatetime() {
        return rsCreatetime;
    }

    public void setRsCreatetime(Date rsCreatetime) {
        this.rsCreatetime = rsCreatetime;
    }

    public Date getRsModifytime() {
        return rsModifytime;
    }

    public void setRsModifytime(Date rsModifytime) {
        this.rsModifytime = rsModifytime;
    }

	public String getRsAnswerquiz() {
		return rsAnswerquiz;
	}

	public void setRsAnswerquiz(String rsAnswerquiz) {
		this.rsAnswerquiz = rsAnswerquiz;
	}

	public String getRsDiyissue() {
		return rsDiyissue;
	}

	public void setRsDiyissue(String rsDiyissue) {
		this.rsDiyissue = rsDiyissue;
	}
}