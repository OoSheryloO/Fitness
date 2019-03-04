package com.huban.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
@SuppressWarnings("serial")
public class Reward implements Serializable{
    private Long rewardId;

    private Long rewardUserid;

    private Long rewardFromuserid;

    private BigDecimal rewardMoney;

    private Date rewardCreatetime;

    private Date rewardModifytime;

    private Integer rewardStatus;

    private Integer rewardType;

    public Long getRewardId() {
        return rewardId;
    }

    public void setRewardId(Long rewardId) {
        this.rewardId = rewardId;
    }

    public Long getRewardUserid() {
        return rewardUserid;
    }

    public void setRewardUserid(Long rewardUserid) {
        this.rewardUserid = rewardUserid;
    }

    public Long getRewardFromuserid() {
        return rewardFromuserid;
    }

    public void setRewardFromuserid(Long rewardFromuserid) {
        this.rewardFromuserid = rewardFromuserid;
    }

    public BigDecimal getRewardMoney() {
        return rewardMoney;
    }

    public void setRewardMoney(BigDecimal rewardMoney) {
        this.rewardMoney = rewardMoney;
    }

    public Date getRewardCreatetime() {
        return rewardCreatetime;
    }

    public void setRewardCreatetime(Date rewardCreatetime) {
        this.rewardCreatetime = rewardCreatetime;
    }

    public Date getRewardModifytime() {
        return rewardModifytime;
    }

    public void setRewardModifytime(Date rewardModifytime) {
        this.rewardModifytime = rewardModifytime;
    }

    public Integer getRewardStatus() {
        return rewardStatus;
    }

    public void setRewardStatus(Integer rewardStatus) {
        this.rewardStatus = rewardStatus;
    }

    public Integer getRewardType() {
        return rewardType;
    }

    public void setRewardType(Integer rewardType) {
        this.rewardType = rewardType;
    }
}