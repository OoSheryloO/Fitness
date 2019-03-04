package com.huban.pojo;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.huban.util.IdWorker;

public class Goods {
	public static final String sGoodsClass = "Goods";
	
    private Long goodsId;

    private String goodsHeadicon;

    private Long goodsPublisher;

    private Integer goodsPrize;

    private Integer goodsMinprize;

    private Integer goodsMaxprize;

    private String goodsRemind;

    private Date goodsAuctionstart;

    private Date goodsAuctionend;

    private String goodsUrl;

    private Integer goodsStatus;

    private Integer goodsType;
    @JSONField(serialize=false)
    private Integer goodsDelete;

    private Date goodsCreatetime;

    private Date goodsModifytime;
    
    private String goodsName;

    private String goodsInformation;
    
    public Goods() {
        super();
        this.goodsId = IdWorker.CreateNewID();
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsHeadicon() {
        return goodsHeadicon;
    }

    public void setGoodsHeadicon(String goodsHeadicon) {
        this.goodsHeadicon = goodsHeadicon == null ? null : goodsHeadicon.trim();
    }

    public Long getGoodsPublisher() {
        return goodsPublisher;
    }

    public void setGoodsPublisher(Long goodsPublisher) {
        this.goodsPublisher = goodsPublisher;
    }

    public Integer getGoodsPrize() {
        return goodsPrize;
    }

    public void setGoodsPrize(Integer goodsPrize) {
        this.goodsPrize = goodsPrize;
    }

    public Integer getGoodsMinprize() {
        return goodsMinprize;
    }

    public void setGoodsMinprize(Integer goodsMinprize) {
        this.goodsMinprize = goodsMinprize;
    }

    public Integer getGoodsMaxprize() {
        return goodsMaxprize;
    }

    public void setGoodsMaxprize(Integer goodsMaxprize) {
        this.goodsMaxprize = goodsMaxprize;
    }

    public String getGoodsRemind() {
        return goodsRemind;
    }

    public void setGoodsRemind(String goodsRemind) {
        this.goodsRemind = goodsRemind == null ? null : goodsRemind.trim();
    }

    public Date getGoodsAuctionstart() {
        return goodsAuctionstart;
    }

    public void setGoodsAuctionstart(Date goodsAuctionstart) {
        this.goodsAuctionstart = goodsAuctionstart;
    }

    public Date getGoodsAuctionend() {
        return goodsAuctionend;
    }

    public void setGoodsAuctionend(Date goodsAuctionend) {
        this.goodsAuctionend = goodsAuctionend;
    }

    public String getGoodsUrl() {
        return goodsUrl;
    }

    public void setGoodsUrl(String goodsUrl) {
        this.goodsUrl = goodsUrl == null ? null : goodsUrl.trim();
    }

    public Integer getGoodsStatus() {
    	if ( null == goodsStatus ) {
			goodsStatus = 0;
		}
        return goodsStatus;
    }

    public void setGoodsStatus(Integer goodsStatus) {
        this.goodsStatus = goodsStatus;
    }

    public Integer getGoodsType() {
    	if ( null == goodsType ) {
    			goodsType = 0;
		}
        return goodsType;
    }

    public void setGoodsType(Integer goodsType) {
        this.goodsType = goodsType;
    }

    public Integer getGoodsDelete() {
        return goodsDelete;
    }

    public void setGoodsDelete(Integer goodsDelete) {
        this.goodsDelete = goodsDelete;
    }

    public Date getGoodsCreatetime() {
        return goodsCreatetime;
    }

    public void setGoodsCreatetime(Date goodsCreatetime) {
        this.goodsCreatetime = goodsCreatetime;
    }

    public Date getGoodsModifytime() {
        return goodsModifytime;
    }

    public void setGoodsModifytime(Date goodsModifytime) {
        this.goodsModifytime = goodsModifytime;
    }

	/**
	 * @return goodsName
	 */
	
	public String getGoodsName() {
		return goodsName;
	}

	/**
	 * @param goodsName the goodsName to set
	 */
	
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	/**
	 * @return goodsInformation
	 */
	
	public String getGoodsInformation() {
		return goodsInformation;
	}

	/**
	 * @param goodsInformation the goodsInformation to set
	 */
	
	public void setGoodsInformation(String goodsInformation) {
		this.goodsInformation = goodsInformation;
	}
}