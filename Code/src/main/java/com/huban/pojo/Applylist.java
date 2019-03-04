package com.huban.pojo;

import java.util.Date;
@SuppressWarnings("serial")
public class Applylist implements java.io.Serializable{
    private Long applylistId;

    private String applylistUsername;

    private Date applylistCreatetime;

    private Date applylistModifytime;

    private Integer applylistStatus;

    private String applylistUserphone;

    private String applylistContent;

    private String applylistArea;

    private Long applylistAreaid;

    private Long applylistCheckerid;

    private String applylistCheckresult;

    private String applylistNid;

    public Long getApplylistId() {
        return applylistId;
    }

    public void setApplylistId(Long applylistId) {
        this.applylistId = applylistId;
    }

    public String getApplylistUsername() {
        return applylistUsername;
    }

    public void setApplylistUsername(String applylistUsername) {
        this.applylistUsername = applylistUsername == null ? null : applylistUsername.trim();
    }

    public Date getApplylistCreatetime() {
        return applylistCreatetime;
    }

    public void setApplylistCreatetime(Date applylistCreatetime) {
        this.applylistCreatetime = applylistCreatetime;
    }

    public Date getApplylistModifytime() {
        return applylistModifytime;
    }

    public void setApplylistModifytime(Date applylistModifytime) {
        this.applylistModifytime = applylistModifytime;
    }

    public Integer getApplylistStatus() {
        return applylistStatus;
    }

    public void setApplylistStatus(Integer applylistStatus) {
        this.applylistStatus = applylistStatus;
    }

    public String getApplylistUserphone() {
        return applylistUserphone;
    }

    public void setApplylistUserphone(String applylistUserphone) {
        this.applylistUserphone = applylistUserphone == null ? null : applylistUserphone.trim();
    }

    public String getApplylistContent() {
        return applylistContent;
    }

    public void setApplylistContent(String applylistContent) {
        this.applylistContent = applylistContent == null ? null : applylistContent.trim();
    }

    public String getApplylistArea() {
        return applylistArea;
    }

    public void setApplylistArea(String applylistArea) {
        this.applylistArea = applylistArea == null ? null : applylistArea.trim();
    }

    public Long getApplylistAreaid() {
        return applylistAreaid;
    }

    public void setApplylistAreaid(Long applylistAreaid) {
        this.applylistAreaid = applylistAreaid;
    }

    public Long getApplylistCheckerid() {
        return applylistCheckerid;
    }

    public void setApplylistCheckerid(Long applylistCheckerid) {
        this.applylistCheckerid = applylistCheckerid;
    }

    public String getApplylistCheckresult() {
        return applylistCheckresult;
    }

    public void setApplylistCheckresult(String applylistCheckresult) {
        this.applylistCheckresult = applylistCheckresult == null ? null : applylistCheckresult.trim();
    }

    public String getApplylistNid() {
        return applylistNid;
    }

    public void setApplylistNid(String applylistNid) {
        this.applylistNid = applylistNid == null ? null : applylistNid.trim();
    }
}