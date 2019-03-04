/*
 * 2018/01/04 hubantech Creating
 *
 * (c) Copyright hubantech Inc. All rights reserved.
 */
package com.huban.pojo;

import java.io.Serializable;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * <p> Entity Class</p>
 *
 * @author DX
 * @version 1.00
 */
@SuppressWarnings("serial")
public class New implements Serializable{

    /**  */
    public static final String COLUMN_News_Id = "News_Id";

    /**  */
    public static final String COLUMN_News_UserId = "News_UserId";

    /**  */
    public static final String COLUMN_News_Title = "News_Title";

    /**  */
    public static final String COLUMN_News_Content = "News_Content";

    /**  */
    public static final String COLUMN_News_Afterword = "News_Afterword";

    /**  */
    public static final String COLUMN_News_Address = "News_Address";
    
    /**  */
    public static final String COLUMN_News_Auther = "News_Auther";

    /**  */
    public static final String COLUMN_News_Press = "News_Press";

    /**  */
    public static final String COLUMN_News_HeadPictureId = "News_HeadPictureId";

    /**  */
    public static final String COLUMN_News_HeadPictureUrl = "News_HeadPictureUrl";
    
    /**  */
    public static final String COLUMN_News_PictureId = "News_PictureId";

    /**  */
    public static final String COLUMN_News_PictureUrl = "News_PictureUrl";

    /**  */
    public static final String COLUMN_News_RecommendType = "News_RecommendType";

    /**  */
    public static final String COLUMN_News_Hotspot = "News_Hotspot";

    /**  */
    public static final String COLUMN_News_Type = "News_Type";

    /**  */
    public static final String COLUMN_News_Sort = "News_Sort";

    /**  */
    public static final String COLUMN_News_Purpose = "News_Purpose";

    /**  */
    public static final String COLUMN_News_RedirectUrl = "News_RedirectUrl";

    /**  */
    public static final String COLUMN_News_Status = "News_Status";

    /**  */
    public static final String COLUMN_News_Delete = "News_Delete";

    /**  */
    public static final String COLUMN_News_VoteNum = "News_VoteNum";

    /**  */
    public static final String COLUMN_News_ReadNum = "News_ReadNum";
    
    /**  */
    public static final String COLUMN_News_Version = "News_Version";
    
    /**  */
    public static final String COLUMN_News_ShareNum = "News_ShareNum";
    
    /**  */
    public static final String COLUMN_News_LikeNum = "News_LikeNum";
    
    /**  */
    public static final String COLUMN_News_DiscussNum = "News_DiscussNUm";
    
    /**  */
    public static final String COLUMN_News_Belong = "News_Belong";

    /**  */
    public static final String COLUMN_News_CreateTime = "News_CreateTime";

    /**  */
    public static final String COLUMN_News_ModifyTime = "News_ModifyTime";

    /**  */
    private Long News_Id;

    /**  */
    private Long News_UserId;

    /**  */
    private String News_Title;

    /**  */
    private String News_Content;

    /**  */
    private String News_Afterword;
    
    /**  */
    private String News_Auther;

    /**  */
    private String News_Address;

    /**  */
    private String News_Press;

    /**  */
    private String News_HeadPictureId;
    
    /**  */
    private String News_HeadPictureUrl;
    
    /**  */
    private String News_PictureId;
    
    /** */
    private String News_PictureUrl;

    /**  */
    private String News_RecommendType;

    /**  */
    private Integer News_Hotspot;

    /**  */
    private Integer News_Type;

    /**  */
    private Integer News_Sort;

    /**  */
    private Integer News_Purpose;

    /**  */
    private String News_RedirectUrl;

    /**  */
    private Integer News_Status;

    /**  */
    private Integer News_Delete;

    /**  */
    private Integer News_VoteNum;

    /**  */
    private Integer News_ReadNum;
    
    /**  */
    private Integer News_Version;
    
    /**  */
    private Integer News_DiscussNum;
    
    /**  */
    private Integer News_LikeNum;
    
    /**  */
    private Integer News_ShareNum;
    
    /** */
    private Integer News_Belong;

    /**  */
    private String News_CreateTime;

    /**  */
    private String News_ModifyTime;
    
    /**  */
    private boolean ReadStatus;
    
    private boolean LikeStatus;
    
    private boolean VoteStatus;

    /**
     * <p>default constants</p>
     */
    public New() {

    }

	/**
	 * @return news_Id
	 */
	
	public Long getNews_Id() {
		return News_Id;
	}

	/**
	 * @param news_Id the news_Id to set
	 */
	
	public void setNews_Id(Long news_Id) {
		News_Id = news_Id;
	}

	/**
	 * @return news_UserId
	 */
	
	public Long getNews_UserId() {
		return News_UserId;
	}

	/**
	 * @param news_UserId the news_UserId to set
	 */
	
	public void setNews_UserId(Long news_UserId) {
		News_UserId = news_UserId;
	}

	/**
	 * @return news_Title
	 */
	
	public String getNews_Title() {
		return News_Title;
	}

	/**
	 * @param news_Title the news_Title to set
	 */
	
	public void setNews_Title(String news_Title) {
		News_Title = news_Title;
	}

	/**
	 * @return news_Content
	 */
	
	public String getNews_Content() {
		return News_Content;
	}

	/**
	 * @param news_Content the news_Content to set
	 */
	
	public void setNews_Content(String news_Content) {
		News_Content = news_Content;
	}

	/**
	 * @return news_Afterword
	 */
	
	public String getNews_Afterword() {
		return News_Afterword;
	}

	/**
	 * @param news_Afterword the news_Afterword to set
	 */
	
	public void setNews_Afterword(String news_Afterword) {
		News_Afterword = news_Afterword;
	}

	/**
	 * @return news_Address
	 */
	
	public String getNews_Address() {
		return News_Address;
	}

	/**
	 * @param news_Address the news_Address to set
	 */
	
	public void setNews_Address(String news_Address) {
		News_Address = news_Address;
	}

	/**
	 * @return news_Auther
	 */
	
	public String getNews_Auther() {
		return News_Auther;
	}

	/**
	 * @param news_Auther the news_Auther to set
	 */
	
	public void setNews_Auther(String news_Auther) {
		News_Auther = news_Auther;
	}

	/**
	 * @return news_Press
	 */
	
	public String getNews_Press() {
		return News_Press;
	}

	/**
	 * @param news_Press the news_Press to set
	 */
	
	public void setNews_Press(String news_Press) {
		News_Press = news_Press;
	}

	/**
	 * @return news_HeadPictureId
	 */
	@JSONField(serialize=false)
	public String getNews_HeadPictureId() {
		return News_HeadPictureId;
	}

	/**
	 * @param news_HeadPictureId the news_HeadPictureId to set
	 */
	
	public void setNews_HeadPictureId(String news_HeadPictureId) {
		News_HeadPictureId = news_HeadPictureId;
	}

	/**
	 * @return news_HeadPictureUrl
	 */
	
	public String getNews_HeadPictureUrl() {
		return News_HeadPictureUrl;
	}

	/**
	 * @param news_HeadPictureUrl the news_HeadPictureUrl to set
	 */
	
	public void setNews_HeadPictureUrl(String news_HeadPictureUrl) {
		News_HeadPictureUrl = news_HeadPictureUrl;
	}

	/**
	 * @return news_PictureId
	 */
	@JSONField(serialize=false)
	public String getNews_PictureId() {
		return News_PictureId;
	}

	/**
	 * @param news_PictureId the news_PictureId to set
	 */
	
	public void setNews_PictureId(String news_PictureId) {
		News_PictureId = news_PictureId;
	}

	/**
	 * @return news_PictureUrl
	 */
	
	public String getNews_PictureUrl() {
		return News_PictureUrl;
	}

	/**
	 * @param news_PictureUrl the news_PictureUrl to set
	 */
	
	public void setNews_PictureUrl(String news_PictureUrl) {
		News_PictureUrl = news_PictureUrl;
	}

	/**
	 * @return news_RecommendType
	 */
	
	public String getNews_RecommendType() {
		return News_RecommendType;
	}

	/**
	 * @param news_RecommendType the news_RecommendType to set
	 */
	
	public void setNews_RecommendType(String news_RecommendType) {
		News_RecommendType = news_RecommendType;
	}

	/**
	 * @return news_Hotspot
	 */
	
	public Integer getNews_Hotspot() {
		return News_Hotspot;
	}

	/**
	 * @param news_Hotspot the news_Hotspot to set
	 */
	
	public void setNews_Hotspot(Integer news_Hotspot) {
		News_Hotspot = news_Hotspot;
	}

	/**
	 * @return news_Type
	 */
	
	public Integer getNews_Type() {
		return News_Type;
	}

	/**
	 * @param news_Type the news_Type to set
	 */
	
	public void setNews_Type(Integer news_Type) {
		News_Type = news_Type;
	}

	/**
	 * @return news_Sort
	 */
	
	public Integer getNews_Sort() {
		return News_Sort;
	}

	/**
	 * @param news_Sort the news_Sort to set
	 */
	
	public void setNews_Sort(Integer news_Sort) {
		News_Sort = news_Sort;
	}

	/**
	 * @return news_Purpose
	 */
	
	public Integer getNews_Purpose() {
		return News_Purpose;
	}

	/**
	 * @param news_Purpose the news_Purpose to set
	 */
	
	public void setNews_Purpose(Integer news_Purpose) {
		News_Purpose = news_Purpose;
	}

	/**
	 * @return news_RedirectUrl
	 */
	
	public String getNews_RedirectUrl() {
		return News_RedirectUrl;
	}

	/**
	 * @param news_RedirectUrl the news_RedirectUrl to set
	 */
	
	public void setNews_RedirectUrl(String news_RedirectUrl) {
		News_RedirectUrl = news_RedirectUrl;
	}

	/**
	 * @return news_Status
	 */
	
	public Integer getNews_Status() {
		return News_Status;
	}

	/**
	 * @param news_Status the news_Status to set
	 */
	
	public void setNews_Status(Integer news_Status) {
		News_Status = news_Status;
	}

	/**
	 * @return news_Delete
	 */
	@JSONField(serialize=false)
	public Integer getNews_Delete() {
		return News_Delete;
	}

	/**
	 * @param news_Delete the news_Delete to set
	 */
	
	public void setNews_Delete(Integer news_Delete) {
		News_Delete = news_Delete;
	}

	/**
	 * @return news_VoteNum
	 */
	
	public Integer getNews_VoteNum() {
		return News_VoteNum;
	}

	/**
	 * @param news_VoteNum the news_VoteNum to set
	 */
	
	public void setNews_VoteNum(Integer news_VoteNum) {
		News_VoteNum = news_VoteNum;
	}

	/**
	 * @return news_ReadNum
	 */
	
	public Integer getNews_ReadNum() {
		return News_ReadNum;
	}

	/**
	 * @param news_ReadNum the news_ReadNum to set
	 */
	
	public void setNews_ReadNum(Integer news_ReadNum) {
		News_ReadNum = news_ReadNum;
	}

	/**
	 * @return news_Version
	 */
	
	public Integer getNews_Version() {
		return News_Version;
	}

	/**
	 * @param news_Version the news_Version to set
	 */
	
	public void setNews_Version(Integer news_Version) {
		News_Version = news_Version;
	}

	/**
	 * @return news_DiscussNum
	 */
	
	public Integer getNews_DiscussNum() {
		return News_DiscussNum;
	}

	/**
	 * @param news_DiscussNum the news_DiscussNum to set
	 */
	
	public void setNews_DiscussNum(Integer news_DiscussNum) {
		News_DiscussNum = news_DiscussNum;
	}

	/**
	 * @return news_LikeNum
	 */
	
	public Integer getNews_LikeNum() {
		return News_LikeNum;
	}

	/**
	 * @param news_LikeNum the news_LikeNum to set
	 */
	
	public void setNews_LikeNum(Integer news_LikeNum) {
		News_LikeNum = news_LikeNum;
	}

	/**
	 * @return news_ShareNum
	 */
	
	public Integer getNews_ShareNum() {
		return News_ShareNum;
	}

	/**
	 * @param news_ShareNum the news_ShareNum to set
	 */
	
	public void setNews_ShareNum(Integer news_ShareNum) {
		News_ShareNum = news_ShareNum;
	}

	/**
	 * @return news_Belong
	 */
	
	public Integer getNews_Belong() {
		return News_Belong;
	}

	/**
	 * @param news_Belong the news_Belong to set
	 */
	
	public void setNews_Belong(Integer news_Belong) {
		News_Belong = news_Belong;
	}

	/**
	 * @return news_CreateTime
	 */
	
	public String getNews_CreateTime() {
		return News_CreateTime;
	}

	/**
	 * @param news_CreateTime the news_CreateTime to set
	 */
	
	public void setNews_CreateTime(String news_CreateTime) {
		News_CreateTime = news_CreateTime;
	}

	/**
	 * @return news_ModifyTime
	 */
	@JSONField(serialize=false)
	public String getNews_ModifyTime() {
		return News_ModifyTime;
	}

	/**
	 * @param news_ModifyTime the news_ModifyTime to set
	 */
	
	public void setNews_ModifyTime(String news_ModifyTime) {
		News_ModifyTime = news_ModifyTime;
	}

	/**
	 * @return readStatus
	 */
	
	public boolean isReadStatus() {
		return ReadStatus;
	}

	/**
	 * @param readStatus the readStatus to set
	 */
	
	public void setReadStatus(Integer readStatus) {
		boolean flag = false;
		if (readStatus != null && readStatus >= 1) {
			flag = true;
		}
		ReadStatus = flag;
	}

	/**
	 * @return likeStatus
	 */
	
	public boolean isLikeStatus() {
		return LikeStatus;
	}

	/**
	 * @param likeStatus the likeStatus to set
	 */
	
	public void setLikeStatus(Integer likeStatus) {
		boolean flag = false;
		if (likeStatus != null && likeStatus >= 1) {
			flag = true;
		}
		LikeStatus = flag;
	}

	/**
	 * @return voteStatus
	 */
	
	public boolean isVoteStatus() {
		return VoteStatus;
	}

	/**
	 * @param voteStatus the voteStatus to set
	 */
	
	public void setVoteStatus(Integer voteStatus) {
		boolean flag = false;
		if (voteStatus != null && voteStatus >= 1) {
			flag = true;
		}
		VoteStatus = flag;
	}

}
