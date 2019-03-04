package com.huban.pojo;

import java.util.Date;
@SuppressWarnings("serial")
public class Clicklist implements java.io.Serializable{
    private Long clicklistId;

    private Date clicklistModifytime;

    private Date clicklistCreatetime;

    private Integer clicklistStatus;

    private Integer clicklistType;

    private Long clicklistBelongid;

    private String clicklistMacaddress;

    private String clicklistIpaddress;

    public Long getClicklistId() {
        return clicklistId;
    }

    public void setClicklistId(Long clicklistId) {
        this.clicklistId = clicklistId;
    }

    public Date getClicklistModifytime() {
        return clicklistModifytime;
    }

    public void setClicklistModifytime(Date clicklistModifytime) {
        this.clicklistModifytime = clicklistModifytime;
    }

    public Date getClicklistCreatetime() {
        return clicklistCreatetime;
    }

    public void setClicklistCreatetime(Date clicklistCreatetime) {
        this.clicklistCreatetime = clicklistCreatetime;
    }

    public Integer getClicklistStatus() {
        return clicklistStatus;
    }

    public void setClicklistStatus(Integer clicklistStatus) {
        this.clicklistStatus = clicklistStatus;
    }

    public Integer getClicklistType() {
        return clicklistType;
    }

    public void setClicklistType(Integer clicklistType) {
        this.clicklistType = clicklistType;
    }

    public Long getClicklistBelongid() {
        return clicklistBelongid;
    }

    public void setClicklistBelongid(Long clicklistBelongid) {
        this.clicklistBelongid = clicklistBelongid;
    }

    public String getClicklistMacaddress() {
        return clicklistMacaddress;
    }

    public void setClicklistMacaddress(String clicklistMacaddress) {
        this.clicklistMacaddress = clicklistMacaddress == null ? null : clicklistMacaddress.trim();
    }

    public String getClicklistIpaddress() {
        return clicklistIpaddress;
    }

    public void setClicklistIpaddress(String clicklistIpaddress) {
        this.clicklistIpaddress = clicklistIpaddress == null ? null : clicklistIpaddress.trim();
    }
}