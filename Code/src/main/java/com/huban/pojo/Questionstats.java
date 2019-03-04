package com.huban.pojo;

import java.io.Serializable;
import java.util.Date;
@SuppressWarnings("serial")
public class Questionstats implements Serializable{
    private Long questionstatId;

    private Long questionstatStudentid;

    private Integer questionstatStatus;

    private String questionstatMemo;

    private Date questionstatModifytime;

    private Date questionstatCreatetime;

    private Integer questionstatVersion;

    private Integer questionstatRightcount;

    private Integer questionstatFailcount;

    private Integer questionstatType;

    private Integer questionstatIntegral;

    public Long getQuestionstatId() {
        return questionstatId;
    }

    public void setQuestionstatId(Long questionstatId) {
        this.questionstatId = questionstatId;
    }

    public Long getQuestionstatStudentid() {
        return questionstatStudentid;
    }

    public void setQuestionstatStudentid(Long questionstatStudentid) {
        this.questionstatStudentid = questionstatStudentid;
    }

    public Integer getQuestionstatStatus() {
        return questionstatStatus;
    }

    public void setQuestionstatStatus(Integer questionstatStatus) {
        this.questionstatStatus = questionstatStatus;
    }

    public String getQuestionstatMemo() {
        return questionstatMemo;
    }

    public void setQuestionstatMemo(String questionstatMemo) {
        this.questionstatMemo = questionstatMemo == null ? null : questionstatMemo.trim();
    }

    public Date getQuestionstatModifytime() {
        return questionstatModifytime;
    }

    public void setQuestionstatModifytime(Date questionstatModifytime) {
        this.questionstatModifytime = questionstatModifytime;
    }

    public Date getQuestionstatCreatetime() {
        return questionstatCreatetime;
    }

    public void setQuestionstatCreatetime(Date questionstatCreatetime) {
        this.questionstatCreatetime = questionstatCreatetime;
    }

    public Integer getQuestionstatVersion() {
        return questionstatVersion;
    }

    public void setQuestionstatVersion(Integer questionstatVersion) {
        this.questionstatVersion = questionstatVersion;
    }

    public Integer getQuestionstatRightcount() {
        return questionstatRightcount;
    }

    public void setQuestionstatRightcount(Integer questionstatRightcount) {
        this.questionstatRightcount = questionstatRightcount;
    }

    public Integer getQuestionstatFailcount() {
        return questionstatFailcount;
    }

    public void setQuestionstatFailcount(Integer questionstatFailcount) {
        this.questionstatFailcount = questionstatFailcount;
    }

    public Integer getQuestionstatType() {
        return questionstatType;
    }

    public void setQuestionstatType(Integer questionstatType) {
        this.questionstatType = questionstatType;
    }

    public Integer getQuestionstatIntegral() {
        return questionstatIntegral;
    }

    public void setQuestionstatIntegral(Integer questionstatIntegral) {
        this.questionstatIntegral = questionstatIntegral;
    }
}