package com.huban.pojo;

import java.util.Date;
@SuppressWarnings("serial")
public class Errorlogs implements java.io.Serializable{
    private Long errorlogId;

    private Long errorlogUserid;

    private Long errorlogTypeid;

    private Date errorlogCreatetime;

    private Date errorlogModifytime;

    private Integer errorlogVersion;

    private Byte errorlogStatus;

    private Byte errorlogDeleted;

    private String errorlogDevice;

    private String errorlogNote;

    public Long getErrorlogId() {
        return errorlogId;
    }

    public void setErrorlogId(Long errorlogId) {
        this.errorlogId = errorlogId;
    }

    public Long getErrorlogUserid() {
        return errorlogUserid;
    }

    public void setErrorlogUserid(Long errorlogUserid) {
        this.errorlogUserid = errorlogUserid;
    }

    public Long getErrorlogTypeid() {
        return errorlogTypeid;
    }

    public void setErrorlogTypeid(Long errorlogTypeid) {
        this.errorlogTypeid = errorlogTypeid;
    }

    public Date getErrorlogCreatetime() {
        return errorlogCreatetime;
    }

    public void setErrorlogCreatetime(Date errorlogCreatetime) {
        this.errorlogCreatetime = errorlogCreatetime;
    }

    public Date getErrorlogModifytime() {
        return errorlogModifytime;
    }

    public void setErrorlogModifytime(Date errorlogModifytime) {
        this.errorlogModifytime = errorlogModifytime;
    }

    public Integer getErrorlogVersion() {
        return errorlogVersion;
    }

    public void setErrorlogVersion(Integer errorlogVersion) {
        this.errorlogVersion = errorlogVersion;
    }

    public Byte getErrorlogStatus() {
        return errorlogStatus;
    }

    public void setErrorlogStatus(Byte errorlogStatus) {
        this.errorlogStatus = errorlogStatus;
    }

    public Byte getErrorlogDeleted() {
        return errorlogDeleted;
    }

    public void setErrorlogDeleted(Byte errorlogDeleted) {
        this.errorlogDeleted = errorlogDeleted;
    }

    public String getErrorlogDevice() {
        return errorlogDevice;
    }

    public void setErrorlogDevice(String errorlogDevice) {
        this.errorlogDevice = errorlogDevice == null ? null : errorlogDevice.trim();
    }

    public String getErrorlogNote() {
        return errorlogNote;
    }

    public void setErrorlogNote(String errorlogNote) {
        this.errorlogNote = errorlogNote == null ? null : errorlogNote.trim();
    }
}