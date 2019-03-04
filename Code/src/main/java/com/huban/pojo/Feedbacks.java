package com.huban.pojo;

import java.io.Serializable;
import java.util.Date;
@SuppressWarnings("serial")
public class Feedbacks implements Serializable{
    private Long feedbackId;

    private Long feedbackUserid;

    private Long feedbackDriveid;

    private Date feedbackCreatetime;

    private Date feedbackModifytime;

    private Integer feedbackVersion;

    private Byte feedbackStatus;

    private Byte feedbackDeleted;

    private Byte feedbackTypeid;

    private byte[] feedbackContent;

    public Long getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(Long feedbackId) {
        this.feedbackId = feedbackId;
    }

    public Long getFeedbackUserid() {
        return feedbackUserid;
    }

    public void setFeedbackUserid(Long feedbackUserid) {
        this.feedbackUserid = feedbackUserid;
    }

    public Long getFeedbackDriveid() {
        return feedbackDriveid;
    }

    public void setFeedbackDriveid(Long feedbackDriveid) {
        this.feedbackDriveid = feedbackDriveid;
    }

    public Date getFeedbackCreatetime() {
        return feedbackCreatetime;
    }

    public void setFeedbackCreatetime(Date feedbackCreatetime) {
        this.feedbackCreatetime = feedbackCreatetime;
    }

    public Date getFeedbackModifytime() {
        return feedbackModifytime;
    }

    public void setFeedbackModifytime(Date feedbackModifytime) {
        this.feedbackModifytime = feedbackModifytime;
    }

    public Integer getFeedbackVersion() {
        return feedbackVersion;
    }

    public void setFeedbackVersion(Integer feedbackVersion) {
        this.feedbackVersion = feedbackVersion;
    }

    public Byte getFeedbackStatus() {
        return feedbackStatus;
    }

    public void setFeedbackStatus(Byte feedbackStatus) {
        this.feedbackStatus = feedbackStatus;
    }

    public Byte getFeedbackDeleted() {
        return feedbackDeleted;
    }

    public void setFeedbackDeleted(Byte feedbackDeleted) {
        this.feedbackDeleted = feedbackDeleted;
    }

    public Byte getFeedbackTypeid() {
        return feedbackTypeid;
    }

    public void setFeedbackTypeid(Byte feedbackTypeid) {
        this.feedbackTypeid = feedbackTypeid;
    }

    public byte[] getFeedbackContent() {
        return feedbackContent;
    }

    public void setFeedbackContent(byte[] feedbackContent) {
        this.feedbackContent = feedbackContent;
    }
}