package com.huban.pojo;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

public class UserJournal {
	public static final String sUserJournalClass = "UserJournal";
	
    private Long journalId;
    @JSONField(serialize=false)
    private Long journalUserid;

    private Integer journalShare;

    private Integer journalVersion;

    private Integer journalStatus;

    private String journalStandby;

    private Date journalCreatetime;

    private Date journalModifytime;
    
    private String journalMood;

    private String journalWeather;

    private String journalTitle;

    private String journalContent;

    private String journalAdscript;

    public Long getJournalId() {
        return journalId;
    }

    public void setJournalId(Long journalId) {
        this.journalId = journalId;
    }

    public Long getJournalUserid() {
        return journalUserid;
    }

    public void setJournalUserid(Long journalUserid) {
        this.journalUserid = journalUserid;
    }

    public Integer getJournalShare() {
        return journalShare;
    }

    public void setJournalShare(Integer journalShare) {
        this.journalShare = journalShare;
    }

    public Integer getJournalVersion() {
        return journalVersion;
    }

    public void setJournalVersion(Integer journalVersion) {
        this.journalVersion = journalVersion;
    }

    public Integer getJournalStatus() {
        return journalStatus;
    }

    public void setJournalStatus(Integer journalStatus) {
        this.journalStatus = journalStatus;
    }

    public String getJournalStandby() {
        return journalStandby;
    }

    public void setJournalStandby(String journalStandby) {
        this.journalStandby = journalStandby == null ? null : journalStandby.trim();
    }

    public Date getJournalCreatetime() {
        return journalCreatetime;
    }

    public void setJournalCreatetime(Date journalCreatetime) {
        this.journalCreatetime = journalCreatetime;
    }

    public Date getJournalModifytime() {
        return journalModifytime;
    }

    public void setJournalModifytime(Date journalModifytime) {
        this.journalModifytime = journalModifytime;
    }

	public String getJournalMood() {
		return journalMood;
	}

	public void setJournalMood(String journalMood) {
		this.journalMood = journalMood;
	}

	public String getJournalWeather() {
		return journalWeather;
	}

	public void setJournalWeather(String journalWeather) {
		this.journalWeather = journalWeather;
	}

	public String getJournalTitle() {
		return journalTitle;
	}

	public void setJournalTitle(String journalTitle) {
		this.journalTitle = journalTitle;
	}

	public String getJournalContent() {
		return journalContent;
	}

	public void setJournalContent(String journalContent) {
		this.journalContent = journalContent;
	}

	public String getJournalAdscript() {
		return journalAdscript;
	}

	public void setJournalAdscript(String journalAdscript) {
		this.journalAdscript = journalAdscript;
	}
    
}