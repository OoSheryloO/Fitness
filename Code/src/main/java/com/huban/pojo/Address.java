package com.huban.pojo;

import java.util.Date;
@SuppressWarnings("serial")
public class Address implements java.io.Serializable{
    private Long addressId;

    private Long addressUserid;

    private String addressUsername;

    private String addressUserphone;

    private String addressUserregion;

    private Date addressCreatetime;

    private Date addressModifytime;

    private Integer addressStatus;

    private Integer addressDelete;

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    public Long getAddressUserid() {
        return addressUserid;
    }

    public void setAddressUserid(Long addressUserid) {
        this.addressUserid = addressUserid;
    }

    public String getAddressUsername() {
        return addressUsername;
    }

    public void setAddressUsername(String addressUsername) {
        this.addressUsername = addressUsername == null ? null : addressUsername.trim();
    }

    public String getAddressUserphone() {
        return addressUserphone;
    }

    public void setAddressUserphone(String addressUserphone) {
        this.addressUserphone = addressUserphone == null ? null : addressUserphone.trim();
    }

    public String getAddressUserregion() {
        return addressUserregion;
    }

    public void setAddressUserregion(String addressUserregion) {
        this.addressUserregion = addressUserregion == null ? null : addressUserregion.trim();
    }

    public Date getAddressCreatetime() {
        return addressCreatetime;
    }

    public void setAddressCreatetime(Date addressCreatetime) {
        this.addressCreatetime = addressCreatetime;
    }

    public Date getAddressModifytime() {
        return addressModifytime;
    }

    public void setAddressModifytime(Date addressModifytime) {
        this.addressModifytime = addressModifytime;
    }

    public Integer getAddressStatus() {
        return addressStatus;
    }

    public void setAddressStatus(Integer addressStatus) {
        this.addressStatus = addressStatus;
    }

    public Integer getAddressDelete() {
        return addressDelete;
    }

    public void setAddressDelete(Integer addressDelete) {
        this.addressDelete = addressDelete;
    }
}