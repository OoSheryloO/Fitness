package com.huban.pojo;

import java.math.BigDecimal;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
@SuppressWarnings("serial")
public class SaveRead implements java.io.Serializable {
	
	public static final String sSaveReadClass = "SaveRead";
	
    private Long rsId;
	@JSONField(serialize=false)
    private Long rsBelongid;
	@JSONField(serialize=false)
    private long rsBelongdept;

    private BigDecimal rsNumber;

    private String rsTitle;

    private String rsPublish;
    
    private String rsAuthor;
    
    private String rsReason;
    @JSONField(serialize=false)
    private String rsReadterrace;

    private Date rsStarttime;

    private Date rsEndtime;

    private String rsStatus;
    @JSONField(serialize=false)
    private Integer rsDelete;
    
    private String	rsGrade;
    
    private String rsAnswerQuiz;
    
    private String rsDiyIssue;
    
    private int rsType;
    @JSONField(serialize=false)
    private int rsMold;
    
    private Date rsCreatetime;
    @JSONField(serialize=false)
    private Date rsModifytime;
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
	public long getRsBelongdept() {
		return rsBelongdept;
	}
	public void setRsBelongdept(long rsBelongdept) {
		this.rsBelongdept = rsBelongdept;
	}
	public BigDecimal getRsNumber() {
		return rsNumber;
	}
	public void setRsNumber(BigDecimal rsNumber) {
		this.rsNumber = rsNumber;
	}
	public String getRsReason() {
		return rsReason;
	}
	public void setRsReason(String rsReason) {
		this.rsReason = rsReason;
	}
	public String getRsTitle() {
		return rsTitle;
	}
	public void setRsTitle(String rsTitle) {
		this.rsTitle = rsTitle;
	}
	public static String getSsavereadclass() {
		return sSaveReadClass;
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
	public String getRsReadterrace() {
		return rsReadterrace;
	}
	public void setRsReadterrace(String rsReadterrace) {
		this.rsReadterrace = rsReadterrace;
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
		this.rsGrade = rsGrade;
	}
	public String getRsAnswerQuiz() {
		return rsAnswerQuiz;
	}
	public void setRsAnswerQuiz(String rsAnswerQuiz) {
		this.rsAnswerQuiz = rsAnswerQuiz;
	}
	public String getRsDiyIssue() {
		return rsDiyIssue;
	}
	public void setRsDiyIssue(String rsDiyIssue) {
		this.rsDiyIssue = rsDiyIssue;
	}
	public int getRsType() {
		return rsType;
	}
	public void setRsType(int rsType) {
		this.rsType = rsType;
	}
	public int getRsMold() {
		return rsMold;
	}
	public void setRsMold(int rsMold) {
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
    
    
}