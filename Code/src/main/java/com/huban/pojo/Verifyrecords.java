package com.huban.pojo;

import java.util.Date;
@SuppressWarnings("serial")
public class Verifyrecords implements java.io.Serializable{
    private Long verifyrecordId;

    private Long verifyrecordUserid;

    private String verifyrecordPhone;

    private String verifyrecordChecknumber;

    private Date verifyrecordCreatetime;

    private Date verifyrecordModifytime;

    private Date verifyrecordDisabletime;

    private Date verifyrecordEnabletime;

    private Long verifyrecordPreviousid;

    private Integer verifyrecordVersion;

    private Byte verifyrecordStatus;

    private Byte verifyrecordDeleted;

    public Long getVerifyrecordId() {
        return verifyrecordId;
    }

    public void setVerifyrecordId(Long verifyrecordId) {
        this.verifyrecordId = verifyrecordId;
    }

    public Long getVerifyrecordUserid() {
        return verifyrecordUserid;
    }

    public void setVerifyrecordUserid(Long verifyrecordUserid) {
        this.verifyrecordUserid = verifyrecordUserid;
    }

    public String getVerifyrecordPhone() {
        return verifyrecordPhone;
    }

    public void setVerifyrecordPhone(String verifyrecordPhone) {
        this.verifyrecordPhone = verifyrecordPhone == null ? null : verifyrecordPhone.trim();
    }

    public String getVerifyrecordChecknumber() {
        return verifyrecordChecknumber;
    }

    public void setVerifyrecordChecknumber(String verifyrecordChecknumber) {
        this.verifyrecordChecknumber = verifyrecordChecknumber == null ? null : verifyrecordChecknumber.trim();
    }

    public Date getVerifyrecordCreatetime() {
        return verifyrecordCreatetime;
    }

    public void setVerifyrecordCreatetime(Date verifyrecordCreatetime) {
        this.verifyrecordCreatetime = verifyrecordCreatetime;
    }

    public Date getVerifyrecordModifytime() {
        return verifyrecordModifytime;
    }

    public void setVerifyrecordModifytime(Date verifyrecordModifytime) {
        this.verifyrecordModifytime = verifyrecordModifytime;
    }

    public Date getVerifyrecordDisabletime() {
        return verifyrecordDisabletime;
    }

    public void setVerifyrecordDisabletime(Date verifyrecordDisabletime) {
        this.verifyrecordDisabletime = verifyrecordDisabletime;
    }

    public Date getVerifyrecordEnabletime() {
        return verifyrecordEnabletime;
    }

    public void setVerifyrecordEnabletime(Date verifyrecordEnabletime) {
        this.verifyrecordEnabletime = verifyrecordEnabletime;
    }

    public Long getVerifyrecordPreviousid() {
        return verifyrecordPreviousid;
    }

    public void setVerifyrecordPreviousid(Long verifyrecordPreviousid) {
        this.verifyrecordPreviousid = verifyrecordPreviousid;
    }

    public Integer getVerifyrecordVersion() {
        return verifyrecordVersion;
    }

    public void setVerifyrecordVersion(Integer verifyrecordVersion) {
        this.verifyrecordVersion = verifyrecordVersion;
    }

    public Byte getVerifyrecordStatus() {
        return verifyrecordStatus;
    }

    public void setVerifyrecordStatus(Byte verifyrecordStatus) {
        this.verifyrecordStatus = verifyrecordStatus;
    }

    public Byte getVerifyrecordDeleted() {
        return verifyrecordDeleted;
    }

    public void setVerifyrecordDeleted(Byte verifyrecordDeleted) {
        this.verifyrecordDeleted = verifyrecordDeleted;
    }
}