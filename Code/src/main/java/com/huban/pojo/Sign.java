package com.huban.pojo;

import java.io.Serializable;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
@SuppressWarnings("serial")
public class Sign implements Serializable{
	@JSONField(serialize=false)
    private Long signId;
    @JSONField(serialize=false)
    private Long signUserid;
    
    private Date signCreatetime;
    @JSONField(serialize=false)
    private Integer signStatus;

   
    public Long getSignId() {
		return signId;
	}

	public void setSignId(Long signId) {
		this.signId = signId;
	}

	public Long getSignUserid() {
		return signUserid;
	}

	public void setSignUserid(Long signUserid) {
		this.signUserid = signUserid;
	}

	public Date getSignCreatetime() {
        return signCreatetime;
    }

    public void setSignCreatetime(Date signCreatetime) {
        this.signCreatetime = signCreatetime;
    }

    public Integer getSignStatus() {
        return signStatus;
    }

    public void setSignStatus(Integer signStatus) {
        this.signStatus = signStatus;
    }
}