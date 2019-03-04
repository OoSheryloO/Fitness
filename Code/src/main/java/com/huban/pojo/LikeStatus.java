package com.huban.pojo;

import java.io.Serializable;
import java.util.Date;
@SuppressWarnings("serial")
public class LikeStatus implements Serializable{
    private Long coluId;

    private Long fromUserid;

    private Integer likeStatus;

    private Date createtime;

    private Date modifytime;

    private Integer likeDelete;
    
    private Integer likeType;

    public Integer getLikeType() {
		return likeType;
	}

	public void setLikeType(Integer likeType) {
		this.likeType = likeType;
	}

	public Long getColuId() {
        return coluId;
    }

    public void setColuId(Long coluId) {
        this.coluId = coluId;
    }

    public Long getFromUserid() {
        return fromUserid;
    }

    public void setFromUserid(Long fromUserid) {
        this.fromUserid = fromUserid;
    }

    public Integer getLikeStatus() {
        return likeStatus;
    }

    public void setLikeStatus(Integer likeStatus) {
        this.likeStatus = likeStatus;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getModifytime() {
        return modifytime;
    }

    public void setModifytime(Date modifytime) {
        this.modifytime = modifytime;
    }

    public Integer getLikeDelete() {
        return likeDelete;
    }

    public void setLikeDelete(Integer likeDelete) {
        this.likeDelete = likeDelete;
    }
}