package com.huban.pojo;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

public class UserPlan {
	@JSONField(serialize=false)
    private Long planId;
	@JSONField(serialize=false)
    private Long planUserid;
    @JSONField(serialize=false)
    private String planTitle;

    private Date planStarttime;

    private Date planEndtime;
    @JSONField(serialize=false)
    private Date planCreatetime;
    @JSONField(serialize=false)
    private Date planModifytime;

    private String planContent;

	public Long getPlanId() {
		return planId;
	}

	public void setPlanId(Long planId) {
		this.planId = planId;
	}

	public Long getPlanUserid() {
		return planUserid;
	}

	public void setPlanUserid(Long planUserid) {
		this.planUserid = planUserid;
	}

	public String getPlanTitle() {
		return planTitle;
	}

	public void setPlanTitle(String planTitle) {
		this.planTitle = planTitle;
	}

	public Date getPlanStarttime() {
		return planStarttime;
	}

	public void setPlanStarttime(Date planStarttime) {
		this.planStarttime = planStarttime;
	}

	public Date getPlanEndtime() {
		return planEndtime;
	}

	public void setPlanEndtime(Date planEndtime) {
		this.planEndtime = planEndtime;
	}

	public Date getPlanCreatetime() {
		return planCreatetime;
	}

	public void setPlanCreatetime(Date planCreatetime) {
		this.planCreatetime = planCreatetime;
	}

	public Date getPlanModifytime() {
		return planModifytime;
	}

	public void setPlanModifytime(Date planModifytime) {
		this.planModifytime = planModifytime;
	}

	public String getPlanContent() {
		return planContent;
	}

	public void setPlanContent(String planContent) {
		this.planContent = planContent;
	}

}