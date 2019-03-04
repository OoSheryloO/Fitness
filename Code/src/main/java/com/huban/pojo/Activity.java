package com.huban.pojo;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

@SuppressWarnings("serial")
public class Activity implements java.io.Serializable{

	private Long activityId;

	/**
	 * @return the activityId
	 */
	public Long getActivityId() {
		return activityId;
	}
	@JSONField(serialize=false)
	public long activityAgentID;
	
	public long getActivityAgentID() {
		return activityAgentID;
	}

	public void setActivityAgentID(long activityAgentID) {
		this.activityAgentID = activityAgentID;
	}

	/**
	 * @param activityId the activityId to set
	 */
	public void setActivityId(Long activityId) {
		this.activityId = activityId;
	}

	private String activityTitle;

    private String activityImageid;

    private Date activityCreatetime;
    @JSONField(serialize=false)
    private Date activityModifytime;
    @JSONField(serialize=false)
    private Integer activityStatus;

    private Integer activityHot;

    private Integer activityWeight;
    @JSONField(serialize=false)
    private Long activityWechatid;

    private Long activityPraise;
    
    private Date activityStarttime;

    private Date activityEndtime;

    private String activitySponsor;

    private String activitySbrief;

    private String activitySimage;

    private Integer activityApplycount;
    
    private Integer activityApplylimit;

    private String activityApplymoney;

    private Byte activityPaytype;

    private String activityContent;
    
    private String activityAreacode;

    private String activityPlace;
    
    private Long activityMoney;
    
    private Integer activityType;
    @JSONField(serialize=false)
    private Integer activityDelete;
    
    public Date activityLaunchtime;
    
    public Boolean qualified;//是否可以继续参加
    
    public Date getActivityLaunchtime() {
		return activityLaunchtime;
	}

	public void setActivityLaunchtime(Date activityLaunchtime) {
		this.activityLaunchtime = activityLaunchtime;
	}

	public Integer getActivityType() {
		return activityType;
	}

	public void setActivityType(Integer activityType) {
		this.activityType = activityType;
	}

	public Integer getActivityDelete() {
		return activityDelete;
	}

	public void setActivityDelete(Integer activityDelete) {
		this.activityDelete = activityDelete;
	}

	/**
	 * @return activityAreacode
	 */
	
	public String getActivityAreacode() {
		return activityAreacode;
	}

	/**
	 * @param activityAreacode the activityAreacode to set
	 */
	
	public void setActivityAreacode(String activityAreacode) {
		this.activityAreacode = activityAreacode;
	}

	/**
	 * @return the activityPlace
	 */
	public String getActivityPlace() {
		return activityPlace;
	}

	/**
	 * @param activityPlace the activityPlace to set
	 */
	public void setActivityPlace(String activityPlace) {
		this.activityPlace = activityPlace;
	}

	/**
	 * @return the activityMoney
	 */
	public Long getActivityMoney() {
		return activityMoney;
	}

	/**
	 * @param activityMoney the activityMoney to set
	 */
	public void setActivityMoney(Long activityMoney) {
		this.activityMoney = activityMoney;
	}


    public String getActivityTitle() {
        return activityTitle;
    }

    public void setActivityTitle(String activityTitle) {
        this.activityTitle = activityTitle == null ? null : activityTitle.trim();
    }

  

    public Date getActivityCreatetime() {
        return activityCreatetime;
    }

    public void setActivityCreatetime(Date activityCreatetime) {
        this.activityCreatetime = activityCreatetime;
    }

    public Date getActivityModifytime() {
        return activityModifytime;
    }

    public void setActivityModifytime(Date activityModifytime) {
        this.activityModifytime = activityModifytime;
    }

    public Integer getActivityStatus() {
        return activityStatus;
    }

    public void setActivityStatus(Integer activityStatus) {
        this.activityStatus = activityStatus;
    }

    public Integer getActivityHot() {
        return activityHot;
    }

    public void setActivityHot(Integer activityHot) {
        this.activityHot = activityHot;
    }

    public Integer getActivityWeight() {
        return activityWeight;
    }

    public void setActivityWeight(Integer activityWeight) {
        this.activityWeight = activityWeight;
    }

    public Long getActivityWechatid() {
        return activityWechatid;
    }

    public void setActivityWechatid(Long activityWechatid) {
        this.activityWechatid = activityWechatid;
    }

    public Long getActivityPraise() {
        return activityPraise;
    }

    public void setActivityPraise(Long activityPraise) {
        this.activityPraise = activityPraise;
    }
    
    public Date getActivityStarttime() {
		return activityStarttime;
	}

	public void setActivityStarttime(Date activityStarttime) {
		this.activityStarttime = activityStarttime;
	}

	public Date getActivityEndtime() {
        return activityEndtime;
    }

    public void setActivityEndtime(Date activityEndtime) {
        this.activityEndtime = activityEndtime;
    }

    public String getActivitySponsor() {
        return activitySponsor;
    }

    public void setActivitySponsor(String activitySponsor) {
        this.activitySponsor = activitySponsor == null ? null : activitySponsor.trim();
    }

    public String getActivitySbrief() {
        return activitySbrief;
    }

    public void setActivitySbrief(String activitySbrief) {
        this.activitySbrief = activitySbrief == null ? null : activitySbrief.trim();
    }
   
    /**
	 * @return the activityImageid
	 */
	public String getActivityImageid() {
		return activityImageid;
	}

	/**
	 * @param activityImageid the activityImageid to set
	 */
	public void setActivityImageid(String activityImageid) {
		this.activityImageid = activityImageid;
	}

	/**
	 * @return the activitySimage
	 */
	public String getActivitySimage() {
		return activitySimage;
	}

	/**
	 * @param activitySimage the activitySimage to set
	 */
	public void setActivitySimage(String activitySimage) {
		this.activitySimage = activitySimage;
	}

	public Integer getActivityApplycount() {
        return activityApplycount;
    }

    public void setActivityApplycount(Integer activityApplycount) {
        this.activityApplycount = activityApplycount;
    }

    public Integer getActivityApplylimit() {
        return activityApplylimit;
    }

    public void setActivityApplylimit(Integer activityApplylimit) {
        this.activityApplylimit = activityApplylimit;
    }

    public String getActivityApplymoney() {
        return activityApplymoney;
    }

    public void setActivityApplymoney(String activityApplymoney) {
        this.activityApplymoney = activityApplymoney == null ? null : activityApplymoney.trim();
    }

    public Byte getActivityPaytype() {
        return activityPaytype;
    }

    public void setActivityPaytype(Byte activityPaytype) {
        this.activityPaytype = activityPaytype;
    }

    public String getActivityContent() {
        return activityContent;
    }

    public void setActivityContent(String activityContent) {
        this.activityContent = activityContent == null ? null : activityContent.trim();
    }

	/**
	 * @return qualified
	 */
	
	public Boolean getQualified() {
		return qualified;
	}

	/**
	 * @param qualified the qualified to set
	 */
	
	public void setQualified(Integer qualified) {
		if (null == qualified) {
			qualified = 0;
		}
		this.qualified = qualified > 1 ? false : true;
	}

	
}