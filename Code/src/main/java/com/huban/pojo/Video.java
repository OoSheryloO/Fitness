package com.huban.pojo;

import java.util.Date;
@SuppressWarnings("serial")
public class Video implements java.io.Serializable{
    private Long videoId;

    private String videoTitle;

    private String videoImageid;
    
    private Long videoClicknumber;

	private Date videoCreatetime;

    private Date videoModifytime;

    private Integer videoStatus;

    private Integer videoWeight;

    private Long videoWechatid;

    private Long videoPraise;

    private String videoUrl;

    private String videoVid;

    private Long videoAreaid;

    private String videoAreaname;

    private Long videoUserid;

    private String videoUsertype;

    private Long videoVotecount;

    private Long videoVoteid;

    private Long videoLikecount;

    private Integer videoIsactivity;

    private Integer videoHot;

    private Long videoCommentcount;

    private Integer videoType;

    private Double videoFilesize;

    private Long videoFiletime;

    private String videoUserName;
    
    private String videoUserHeadIcon;
    
    private Integer belongType;
    
    //是否付款
    private Integer Pay;
    //是否点赞
    private Integer Like;
    
	public String getVideoUserHeadIcon() {
		return videoUserHeadIcon;
	}

	public void setVideoUserHeadIcon(String videoUserHeadIcon) {
		this.videoUserHeadIcon = videoUserHeadIcon;
	}

	/**
	 * @return the videoId
	 */
	public Long getVideoId() {
		return videoId;
	}

	/**
	 * @param videoId the videoId to set
	 */
	public void setVideoId(Long videoId) {
		this.videoId = videoId;
	}

	/**
	 * @return the videoUserName
	 */
	public String getVideoUserName() {
		return videoUserName;
	}

	/**
	 * @param videoUserName the videoUserName to set
	 */
	public void setVideoUserName(String videoUserName) {
		this.videoUserName = videoUserName;
	}

	/**
   	 * @return the videoClicknumber
   	 */
   	public Long getVideoClicknumber() {
   		return videoClicknumber;
   	}

   	/**
   	 * @param videoClicknumber the videoClicknumber to set
   	 */
   	public void setVideoClicknumber(Long videoClicknumber) {
   		this.videoClicknumber = videoClicknumber;
   	}
    
   


	
	public String getVideoTitle() {
        return videoTitle;
    }

    public void setVideoTitle(String videoTitle) {
        this.videoTitle = videoTitle == null ? null : videoTitle.trim();
    }

 

    /**
	 * @return the videoImageid
	 */
	public String getVideoImageid() {
		return videoImageid;
	}

	/**
	 * @param videoImageid the videoImageid to set
	 */
	public void setVideoImageid(String videoImageid) {
		this.videoImageid = videoImageid;
	}

	public Date getVideoCreatetime() {
        return videoCreatetime;
    }

    public void setVideoCreatetime(Date videoCreatetime) {
        this.videoCreatetime = videoCreatetime;
    }

    public Date getVideoModifytime() {
        return videoModifytime;
    }

    public void setVideoModifytime(Date videoModifytime) {
        this.videoModifytime = videoModifytime;
    }

    public Integer getVideoStatus() {
        return videoStatus;
    }

    public void setVideoStatus(Integer videoStatus) {
        this.videoStatus = videoStatus;
    }

    public Integer getVideoWeight() {
        return videoWeight;
    }

    public void setVideoWeight(Integer videoWeight) {
        this.videoWeight = videoWeight;
    }

    public Long getVideoWechatid() {
        return videoWechatid;
    }

    public void setVideoWechatid(Long videoWechatid) {
        this.videoWechatid = videoWechatid;
    }

    public Long getVideoPraise() {
        return videoPraise;
    }

    public void setVideoPraise(Long videoPraise) {
        this.videoPraise = videoPraise;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl == null ? null : videoUrl.trim();
    }

    public String getVideoVid() {
        return videoVid;
    }

    public void setVideoVid(String videoVid) {
        this.videoVid = videoVid == null ? null : videoVid.trim();
    }

    public Long getVideoAreaid() {
        return videoAreaid;
    }

    public void setVideoAreaid(Long videoAreaid) {
        this.videoAreaid = videoAreaid;
    }

    public String getVideoAreaname() {
        return videoAreaname;
    }

    public void setVideoAreaname(String videoAreaname) {
        this.videoAreaname = videoAreaname == null ? null : videoAreaname.trim();
    }

    public Long getVideoUserid() {
        return videoUserid;
    }

    public void setVideoUserid(Long videoUserid) {
        this.videoUserid = videoUserid;
    }


    /**
	 * @return the videoUsertype
	 */
	public String getVideoUsertype() {
		return videoUsertype;
	}

	/**
	 * @param videoUsertype the videoUsertype to set
	 */
	public void setVideoUsertype(String videoUsertype) {
		this.videoUsertype = videoUsertype;
	}

	public Long getVideoVotecount() {
        return videoVotecount;
    }

    public void setVideoVotecount(Long videoVotecount) {
        this.videoVotecount = videoVotecount;
    }

    public Long getVideoVoteid() {
        return videoVoteid;
    }

    public void setVideoVoteid(Long videoVoteid) {
        this.videoVoteid = videoVoteid;
    }

    public Long getVideoLikecount() {
        return videoLikecount;
    }

    public void setVideoLikecount(Long videoLikecount) {
        this.videoLikecount = videoLikecount;
    }

    public Integer getVideoIsactivity() {
        return videoIsactivity;
    }

    public void setVideoIsactivity(Integer videoIsactivity) {
        this.videoIsactivity = videoIsactivity;
    }

    public Integer getVideoHot() {
        return videoHot;
    }

    public void setVideoHot(Integer videoHot) {
        this.videoHot = videoHot;
    }

    public Long getVideoCommentcount() {
        return videoCommentcount;
    }

    public void setVideoCommentcount(Long videoCommentcount) {
        this.videoCommentcount = videoCommentcount;
    }

    public Integer getVideoType() {
        return videoType;
    }

    public void setVideoType(Integer videoType) {
        this.videoType = videoType;
    }

    public Double getVideoFilesize() {
        return videoFilesize;
    }

    public void setVideoFilesize(Double videoFilesize) {
        this.videoFilesize = videoFilesize;
    }

    public Long getVideoFiletime() {
        return videoFiletime;
    }

    public void setVideoFiletime(Long videoFiletime) {
        this.videoFiletime = videoFiletime;
    }

	/**
	 * @return pay
	 */
	
	public boolean getPay() {
		boolean flag = false;
		if (null != Pay &&Pay > 0) {
			flag = true;
		}
		return flag;
	}

	/**
	 * @param pay the pay to set
	 */
	
	public void setPay(Integer pay) {
		Pay = pay;
	}

	/**
	 * @return like
	 */
	
	public boolean getLike() {
		boolean flag = false;
		if (null != Like && Like > 0) {
			flag = true;
		}
		return flag;
	}

	/**
	 * @param like the like to set
	 */
	
	public void setLike(Integer like) {
		Like = like;
	}

	/**
	 * @return belongType
	 */
	
	public Integer getBelongType() {
		return belongType;
	}

	/**
	 * @param belongType the belongType to set
	 */
	
	public void setBelongType(Integer belongType) {
		this.belongType = belongType;
	}

}