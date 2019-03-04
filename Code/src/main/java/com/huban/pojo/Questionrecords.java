package com.huban.pojo;

import java.io.Serializable;
import java.util.Date;
@SuppressWarnings("serial")
public class Questionrecords implements Serializable{
    private Long questionrecordId;

    private Integer questionrecordStatus;

    private String questionrecordMemo;

    private Date questionrecordCreatetime;

    private Date questionrecordModifytime;

    private Integer questionrecordVersion;

    private Long questionrecordStudentid;

    private Long questionrecordQuestionid;

    private Integer questionrecordAnswerresult;

    private Integer questionrecordType;

    private Integer questionrecordContinuedtime;

    public Long getQuestionrecordId() {
        return questionrecordId;
    }

    public void setQuestionrecordId(Long questionrecordId) {
        this.questionrecordId = questionrecordId;
    }

    public Integer getQuestionrecordStatus() {
        return questionrecordStatus;
    }

    public void setQuestionrecordStatus(Integer questionrecordStatus) {
        this.questionrecordStatus = questionrecordStatus;
    }

    public String getQuestionrecordMemo() {
        return questionrecordMemo;
    }

    public void setQuestionrecordMemo(String questionrecordMemo) {
        this.questionrecordMemo = questionrecordMemo;
    }

    public Date getQuestionrecordCreatetime() {
        return questionrecordCreatetime;
    }

    public void setQuestionrecordCreatetime(Date questionrecordCreatetime) {
        this.questionrecordCreatetime = questionrecordCreatetime;
    }

    public Date getQuestionrecordModifytime() {
        return questionrecordModifytime;
    }

    public void setQuestionrecordModifytime(Date questionrecordModifytime) {
        this.questionrecordModifytime = questionrecordModifytime;
    }

    public Integer getQuestionrecordVersion() {
        return questionrecordVersion;
    }

    public void setQuestionrecordVersion(Integer questionrecordVersion) {
        this.questionrecordVersion = questionrecordVersion;
    }

    public Long getQuestionrecordStudentid() {
        return questionrecordStudentid;
    }

    public void setQuestionrecordStudentid(Long questionrecordStudentid) {
        this.questionrecordStudentid = questionrecordStudentid;
    }

    public Long getQuestionrecordQuestionid() {
        return questionrecordQuestionid;
    }

    public void setQuestionrecordQuestionid(Long questionrecordQuestionid) {
        this.questionrecordQuestionid = questionrecordQuestionid;
    }

    public Integer getQuestionrecordAnswerresult() {
        return questionrecordAnswerresult;
    }

    public void setQuestionrecordAnswerresult(Integer questionrecordAnswerresult) {
        this.questionrecordAnswerresult = questionrecordAnswerresult;
    }

    public Integer getQuestionrecordType() {
        return questionrecordType;
    }

    public void setQuestionrecordType(Integer questionrecordType) {
        this.questionrecordType = questionrecordType;
    }

    public Integer getQuestionrecordContinuedtime() {
        return questionrecordContinuedtime;
    }

    public void setQuestionrecordContinuedtime(Integer questionrecordContinuedtime) {
        this.questionrecordContinuedtime = questionrecordContinuedtime;
    }
}