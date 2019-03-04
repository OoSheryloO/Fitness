package com.huban.pojo;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.huban.util.IdWorker;

public class AuctionRecords {
	public static final String sAuctionRecordsClass = "Auction";
	@JSONField(serialize=false)
    private Long auctionId;
	@JSONField(serialize=false)
    private Long auctionUserid;
	private String auctionUserName;
	private String auctionHeadIcon;
	@JSONField(serialize=false)
    private Long auctionGoodsid;

    private Integer auctionPrize;

    private Integer auctionStatus;
    @JSONField(serialize=false)
    private Integer auctionDelete;

    private String auctionExplain;

    private Date auctionCreatetime;
    @JSONField(serialize=false)
    private Date auctionModifytime;
    
    public AuctionRecords() {
    		super();
    		this.auctionId = IdWorker.CreateNewID();
    }

    public Long getAuctionId() {
        return auctionId;
    }

    public void setAuctionId(Long auctionId) {
        this.auctionId = auctionId;
    }

    public Long getAuctionUserid() {
        return auctionUserid;
    }

    public void setAuctionUserid(Long auctionUserid) {
        this.auctionUserid = auctionUserid;
    }

    /**
	 * @return auctionUserName
	 */
	
	public String getAuctionUserName() {
		return auctionUserName;
	}

	/**
	 * @param auctionUserName the auctionUserName to set
	 */
	
	public void setAuctionUserName(String auctionUserName) {
		this.auctionUserName = auctionUserName;
	}

	/**
	 * @return auctionHeadIcon
	 */
	
	public String getAuctionHeadIcon() {
		return auctionHeadIcon;
	}

	/**
	 * @param auctionHeadIcon the auctionHeadIcon to set
	 */
	
	public void setAuctionHeadIcon(String auctionHeadIcon) {
		this.auctionHeadIcon = auctionHeadIcon;
	}

	public Long getAuctionGoodsid() {
        return auctionGoodsid;
    }

    public void setAuctionGoodsid(Long auctionGoodsid) {
        this.auctionGoodsid = auctionGoodsid;
    }

    public Integer getAuctionPrize() {
        return auctionPrize;
    }

    public void setAuctionPrize(Integer auctionPrize) {
        this.auctionPrize = auctionPrize;
    }

	public Integer getAuctionStatus() {
		if (null == auctionStatus) {
			auctionStatus = 0;
		}
		return auctionStatus;
	}

    public void setAuctionStatus(Integer auctionStatus) {
        this.auctionStatus = auctionStatus;
    }

    public Integer getAuctionDelete() {
        return auctionDelete;
    }

    public void setAuctionDelete(Integer auctionDelete) {
        this.auctionDelete = auctionDelete;
    }

    public String getAuctionExplain() {
        return auctionExplain;
    }

    public void setAuctionExplain(String auctionExplain) {
        this.auctionExplain = auctionExplain == null ? null : auctionExplain.trim();
    }

    public Date getAuctionCreatetime() {
        return auctionCreatetime;
    }

    public void setAuctionCreatetime(Date auctionCreatetime) {
        this.auctionCreatetime = auctionCreatetime;
    }

    public Date getAuctionModifytime() {
        return auctionModifytime;
    }

    public void setAuctionModifytime(Date auctionModifytime) {
        this.auctionModifytime = auctionModifytime;
    }
}