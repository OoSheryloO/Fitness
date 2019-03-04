package com.huban.pojo;

import java.io.Serializable;
import java.util.Date;
@SuppressWarnings("serial")
public class Shop implements Serializable{
    private Long shopId;

    private Long shopBookid;

    private Long shopAddressid;

    private Integer shopStatus;

    private Integer shopDelete;

    private Date shopCreatetime;

    private Date shopModifytime;

    private Long shopUserid;

    public Long getShopId() {
        return shopId;
    }
    
    public Long getShopBookid() {
		return shopBookid;
	}
    
	public void setShopBookid(Long shopBookid) {
		this.shopBookid = shopBookid;
	}
	
	public Long getShopAddressid() {
		return shopAddressid;
	}
	
	public void setShopAddressid(Long shopAddressid) {
		this.shopAddressid = shopAddressid;
	}
	
	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}

	public Integer getShopStatus() {
        return shopStatus;
    }

    public void setShopStatus(Integer shopStatus) {
        this.shopStatus = shopStatus;
    }

    public Integer getShopDelete() {
        return shopDelete;
    }

    public void setShopDelete(Integer shopDelete) {
        this.shopDelete = shopDelete;
    }

    public Date getShopCreatetime() {
        return shopCreatetime;
    }

    public void setShopCreatetime(Date shopCreatetime) {
        this.shopCreatetime = shopCreatetime;
    }

    public Date getShopModifytime() {
        return shopModifytime;
    }

    public void setShopModifytime(Date shopModifytime) {
        this.shopModifytime = shopModifytime;
    }

	public Long getShopUserid() {
		return shopUserid;
	}
	
	public void setShopUserid(Long shopUserid) {
		this.shopUserid = shopUserid;
	}

   
}