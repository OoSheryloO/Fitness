package com.huban.pojo;

@SuppressWarnings("serial")
public class VideoWithBLOBs extends Video implements java.io.Serializable{
    private String videoContent;

    private String videoClickcount;
    
    public String getVideoUseMoney() {
		return videoUseMoney;
	}

	public void setVideoUseMoney(String videoUseMoney) {
		this.videoUseMoney = videoUseMoney;
	}

	private String videoUseMoney;

    public String getVideoContent() {
        return videoContent;
    }

    public void setVideoContent(String videoContent) {
        this.videoContent = videoContent == null ? null : videoContent.trim();
    }

    public String getVideoClickcount() {
        return videoClickcount;
    }

    public void setVideoClickcount(String videoClickcount) {
        this.videoClickcount = videoClickcount == null ? null : videoClickcount.trim();
    }
}