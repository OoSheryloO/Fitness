package com.huban.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
@SuppressWarnings("serial")
public class Payrecords implements Serializable{
    private Long payrecordId;

    private Long payrecordUserid;
    
    private Long payrecordBelongid;

    private Long payrecordOrderid;

    private String payrecordPaymethod;

    private String payrecordPayreason;

    private BigDecimal payrecordMoney;

    private Byte payrecordTarde;

    private Date payrecordCreatetime;

    private Date payrecordModifytime;

    private Integer payrecordVersion;

    private Byte payrecordStatus;

    private Byte payrecordDeleted;

    private Long payrecordFromuserid;

    private Byte pictureId;

    private Byte pictureDeleted;
    
    private Long payrecordTypeid;
    
    

    public Long getPayrecordBelongid() {
		return payrecordBelongid;
	}

	public void setPayrecordBelongid(Long payrecordBelongid) {
		this.payrecordBelongid = payrecordBelongid;
	}

	public Long getPayrecordTypeid() {
		return payrecordTypeid;
	}

	public void setPayrecordTypeid(Long payrecordTypeid) {
		this.payrecordTypeid = payrecordTypeid;
	}

	public Long getPayrecordId() {
        return payrecordId;
    }

    public void setPayrecordId(Long payrecordId) {
        this.payrecordId = payrecordId;
    }

    public Long getPayrecordUserid() {
        return payrecordUserid;
    }

    public void setPayrecordUserid(Long payrecordUserid) {
        this.payrecordUserid = payrecordUserid;
    }

    public Long getPayrecordOrderid() {
        return payrecordOrderid;
    }

    public void setPayrecordOrderid(Long payrecordOrderid) {
        this.payrecordOrderid = payrecordOrderid;
    }

    public String getPayrecordPaymethod() {
        return payrecordPaymethod;
    }

    public void setPayrecordPaymethod(String payrecordPaymethod) {
        this.payrecordPaymethod = payrecordPaymethod == null ? null : payrecordPaymethod.trim();
    }

    public String getPayrecordPayreason() {
        return payrecordPayreason;
    }

    public void setPayrecordPayreason(String payrecordPayreason) {
        this.payrecordPayreason = payrecordPayreason == null ? null : payrecordPayreason.trim();
    }

    public BigDecimal getPayrecordMoney() {
        return payrecordMoney;
    }

    public void setPayrecordMoney(BigDecimal payrecordMoney) {
        this.payrecordMoney = payrecordMoney;
    }

    public Byte getPayrecordTarde() {
        return payrecordTarde;
    }

    public void setPayrecordTarde(Byte payrecordTarde) {
        this.payrecordTarde = payrecordTarde;
    }

    public Date getPayrecordCreatetime() {
        return payrecordCreatetime;
    }

    public void setPayrecordCreatetime(Date payrecordCreatetime) {
        this.payrecordCreatetime = payrecordCreatetime;
    }

    public Date getPayrecordModifytime() {
        return payrecordModifytime;
    }

    public void setPayrecordModifytime(Date payrecordModifytime) {
        this.payrecordModifytime = payrecordModifytime;
    }

    public Integer getPayrecordVersion() {
        return payrecordVersion;
    }

    public void setPayrecordVersion(Integer payrecordVersion) {
        this.payrecordVersion = payrecordVersion;
    }

    public Byte getPayrecordStatus() {
        return payrecordStatus;
    }

    public void setPayrecordStatus(Byte payrecordStatus) {
        this.payrecordStatus = payrecordStatus;
    }

    public Byte getPayrecordDeleted() {
        return payrecordDeleted;
    }

    public void setPayrecordDeleted(Byte payrecordDeleted) {
        this.payrecordDeleted = payrecordDeleted;
    }

    public Long getPayrecordFromuserid() {
        return payrecordFromuserid;
    }

    public void setPayrecordFromuserid(Long payrecordFromuserid) {
        this.payrecordFromuserid = payrecordFromuserid;
    }

    public Byte getPictureId() {
        return pictureId;
    }

    public void setPictureId(Byte pictureId) {
        this.pictureId = pictureId;
    }

    public Byte getPictureDeleted() {
        return pictureDeleted;
    }

    public void setPictureDeleted(Byte pictureDeleted) {
        this.pictureDeleted = pictureDeleted;
    }
}