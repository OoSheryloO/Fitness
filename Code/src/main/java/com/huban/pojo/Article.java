/*
 * 2018/01/04 hubantech Creating
 *
 * (c) Copyright hubantech Inc. All rights reserved.
 */
package com.huban.pojo;


import com.alibaba.fastjson.annotation.JSONField;

/**
 * <p> Entity Class</p>
 *
 * @author DX
 * @version 1.00
 */
@SuppressWarnings("serial")
public class Article implements java.io.Serializable{

    /**  */
    public static final String COLUMN_Article_Id = "Article_Id";

    /**  */
    public static final String COLUMN_Article_UserId = "Article_UserId";

    /**  */
    public static final String COLUMN_Article_Title = "Article_Title";

    /**  */
    public static final String COLUMN_Article_Content = "Article_Content";

    /**  */
    public static final String COLUMN_Article_Afterword = "Article_Afterword";

    /**  */
    public static final String COLUMN_Article_Address = "Article_Address";
    
    /**  */
    public static final String COLUMN_Article_Auther = "Article_Auther";

    /**  */
    public static final String COLUMN_Article_Press = "Article_Press";

    /**  */
    public static final String COLUMN_Article_HeadPictureId = "Article_HeadPictureId";

    /**  */
    public static final String COLUMN_Article_HeadPictureUrl = "Article_HeadPictureUrl";
    
    /**  */
    public static final String COLUMN_Article_PictureId = "Article_PictureId";

    /**  */
    public static final String COLUMN_Article_PictureUrl = "Article_PictureUrl";

    /**  */
    public static final String COLUMN_Article_RecommendType = "Article_RecommendType";

    /**  */
    public static final String COLUMN_Article_Hotspot = "Article_Hotspot";

    /**  */
    public static final String COLUMN_Article_Type = "Article_Type";

    /**  */
    public static final String COLUMN_Article_Sort = "Article_Sort";

    /**  */
    public static final String COLUMN_Article_Purpose = "Article_Purpose";

    /**  */
    public static final String COLUMN_Article_RedirectUrl = "Article_RedirectUrl";

    /**  */
    public static final String COLUMN_Article_Status = "Article_Status";

    /**  */
    public static final String COLUMN_Article_Delete = "Article_Delete";

    /**  */
    public static final String COLUMN_Article_VoteNum = "Article_VoteNum";

    /**  */
    public static final String COLUMN_Article_ReadNum = "Article_ReadNum";

    /**  */
    public static final String COLUMN_Article_Version = "Article_Version";
    
    /**  */
    public static final String COLUMN_Article_DiscussNum = "Article_DiscussNum";

    /**  */
    public static final String COLUMN_Article_LikeNum = "Article_LikeNum";
    
    /**  */
    public static final String COLUMN_Article_ShareNum = "Article_ShareNum";
    
    /**  */
    public static final String COLUMN_Article_Belong = "Article_Belong";

    /**  */
    public static final String COLUMN_Article_CreateTime = "Article_CreateTime";

    /**  */
    private Long Article_Id;

    /**  */
    private Long Article_UserId;

    /**  */
    private String Article_Title;

    /**  */
    private String Article_Content;

    /**  */
    private String Article_Afterword;
    
    /**  */
    private String Article_Auther;

    /**  */
    private String Article_Address;

    /**  */
    private String Article_Press;

    /**  */
    private String Article_HeadPictureId;
    
    /**  */
    private String Article_HeadPictureUrl;
    
    /**  */
    private String Article_PictureId;
    
    /**  */
    private String Article_PictureUrl;

    /**  */
    private String Article_RecommendType;

    /**  */
    private Integer Article_Hotspot;

    /**  */
    private Integer Article_Type;

    /**  */
    private Integer Article_Sort;

    /**  */
    private Integer Article_Purpose;

    /**  */
    private String Article_RedirectUrl;

    /**  */
    private Integer Article_Status;

    /**  */
    private Integer Article_VoteNum;
    
    /**  */
    private Integer Article_ShareNum;
    
    /**  */
    private Integer Article_DiscussNum;
    
    /**  */
    private Integer Article_LikeNum;
    
    /**  */
    private Integer Article_Version;

    /**  */
    private Integer Article_ReadNum;
    
    /** */
    private Integer Article_Belong;

    /**  */
    private String Article_CreateTime;
    
    /**  */
    private Integer Article_Delete;
    
    /** */
    private String Article_ModifyTime;
    
    /**  */
    private boolean ReadStatus;
    
    private boolean LikeStatus;
    
    private boolean VoteStatus;
    
    /**
     * <p>default constants</p>
     */
    public Article() {

    }

	/**
	 * @return Article_Id
	 */
	
	public Long getArticle_Id() {
		return Article_Id;
	}

	/**
	 * @param Article_Id the Article_Id to set
	 */
	
	public void setArticle_Id(Long Article_Id) {
		Article_Id = Article_Id;
	}

	/**
	 * @return Article_UserId
	 */
	
	public Long getArticle_UserId() {
		return Article_UserId;
	}

	/**
	 * @param Article_UserId the Article_UserId to set
	 */
	
	public void setArticle_UserId(Long Article_UserId) {
		Article_UserId = Article_UserId;
	}

	/**
	 * @return Article_Title
	 */
	
	public String getArticle_Title() {
		return Article_Title;
	}

	/**
	 * @param Article_Title the Article_Title to set
	 */
	
	public void setArticle_Title(String Article_Title) {
		Article_Title = Article_Title;
	}

	/**
	 * @return Article_Content
	 */
	
	public String getArticle_Content() {
		return Article_Content;
	}

	/**
	 * @param Article_Content the Article_Content to set
	 */
	
	public void setArticle_Content(String Article_Content) {
		Article_Content = Article_Content;
	}

	/**
	 * @return Article_Afterword
	 */
	
	public String getArticle_Afterword() {
		return Article_Afterword;
	}

	/**
	 * @param Article_Afterword the Article_Afterword to set
	 */
	
	public void setArticle_Afterword(String Article_Afterword) {
		Article_Afterword = Article_Afterword;
	}

	/**
	 * @return Article_Address
	 */
	
	public String getArticle_Address() {
		return Article_Address;
	}

	/**
	 * @param Article_Address the Article_Address to set
	 */
	
	public void setArticle_Address(String Article_Address) {
		Article_Address = Article_Address;
	}

	/**
	 * @return article_Auther
	 */
	
	public String getArticle_Auther() {
		return Article_Auther;
	}

	/**
	 * @param article_Auther the article_Auther to set
	 */
	
	public void setArticle_Auther(String article_Auther) {
		Article_Auther = article_Auther;
	}

	/**
	 * @return Article_Press
	 */
	
	public String getArticle_Press() {
		return Article_Press;
	}

	/**
	 * @param Article_Press the Article_Press to set
	 */
	
	public void setArticle_Press(String Article_Press) {
		Article_Press = Article_Press;
	}

	/**
	 * @return Article_HeadPictureId
	 */
	@JSONField(serialize=false)
	public String getArticle_HeadPictureId() {
		return Article_HeadPictureId;
	}

	/**
	 * @param Article_HeadPictureId the Article_HeadPictureId to set
	 */
	
	public void setArticle_HeadPictureId(String Article_HeadPictureId) {
		Article_HeadPictureId = Article_HeadPictureId;
	}

	/**
	 * @return Article_HeadPictureUrl
	 */
	
	public String getArticle_HeadPictureUrl() {
		return Article_HeadPictureUrl;
	}

	/**
	 * @param Article_HeadPictureUrl the Article_HeadPictureUrl to set
	 */
	
	public void setArticle_HeadPictureUrl(String Article_HeadPictureUrl) {
		Article_HeadPictureUrl = Article_HeadPictureUrl;
	}

	/**
	 * @return Article_PictureId
	 */
	@JSONField(serialize=false)
	public String getArticle_PictureId() {
		return Article_PictureId;
	}

	/**
	 * @param Article_PictureId the Article_PictureId to set
	 */
	
	public void setArticle_PictureId(String Article_PictureId) {
		Article_PictureId = Article_PictureId;
	}

	/**
	 * @return Article_PictureUrl
	 */
	
	public String getArticle_PictureUrl() {
		return Article_PictureUrl;
	}

	/**
	 * @param Article_PictureUrl the Article_PictureUrl to set
	 */
	
	public void setArticle_PictureUrl(String Article_PictureUrl) {
		Article_PictureUrl = Article_PictureUrl;
	}

	/**
	 * @return Article_RecommendType
	 */
	
	public String getArticle_RecommendType() {
		return Article_RecommendType;
	}

	/**
	 * @param Article_RecommendType the Article_RecommendType to set
	 */
	
	public void setArticle_RecommendType(String Article_RecommendType) {
		Article_RecommendType = Article_RecommendType;
	}

	/**
	 * @return Article_Hotspot
	 */
	
	public Integer getArticle_Hotspot() {
		return Article_Hotspot;
	}

	/**
	 * @param Article_Hotspot the Article_Hotspot to set
	 */
	
	public void setArticle_Hotspot(Integer Article_Hotspot) {
		Article_Hotspot = Article_Hotspot;
	}

	/**
	 * @return Article_Type
	 */
	
	public Integer getArticle_Type() {
		return Article_Type;
	}

	/**
	 * @param Article_Type the Article_Type to set
	 */
	
	public void setArticle_Type(Integer Article_Type) {
		Article_Type = Article_Type;
	}

	/**
	 * @return Article_Sort
	 */
	
	public Integer getArticle_Sort() {
		return Article_Sort;
	}

	/**
	 * @param Article_Sort the Article_Sort to set
	 */
	
	public void setArticle_Sort(Integer Article_Sort) {
		Article_Sort = Article_Sort;
	}

	/**
	 * @return Article_Purpose
	 */
	
	public Integer getArticle_Purpose() {
		return Article_Purpose;
	}

	/**
	 * @param Article_Purpose the Article_Purpose to set
	 */
	
	public void setArticle_Purpose(Integer Article_Purpose) {
		Article_Purpose = Article_Purpose;
	}

	/**
	 * @return Article_RedirectUrl
	 */
	
	public String getArticle_RedirectUrl() {
		return Article_RedirectUrl;
	}

	/**
	 * @param Article_RedirectUrl the Article_RedirectUrl to set
	 */
	
	public void setArticle_RedirectUrl(String Article_RedirectUrl) {
		Article_RedirectUrl = Article_RedirectUrl;
	}

	/**
	 * @return Article_Status
	 */
	
	public Integer getArticle_Status() {
		return Article_Status;
	}

	/**
	 * @param Article_Status the Article_Status to set
	 */
	
	public void setArticle_Status(Integer Article_Status) {
		Article_Status = Article_Status;
	}

	/**
	 * @return Article_Delete
	 */
	
	public Integer getArticle_Delete() {
		return Article_Delete;
	}

	/**
	 * @param Article_Delete the Article_Delete to set
	 */
	
	public void setArticle_Delete(Integer Article_Delete) {
		Article_Delete = Article_Delete;
	}

	/**
	 * @return Article_VoteNum
	 */
	
	public Integer getArticle_VoteNum() {
		return Article_VoteNum;
	}

	/**
	 * @param Article_VoteNum the Article_VoteNum to set
	 */
	
	public void setArticle_VoteNum(Integer Article_VoteNum) {
		Article_VoteNum = Article_VoteNum;
	}

	/**
	 * @return Article_ReadNum
	 */
	
	public Integer getArticle_ReadNum() {
		return Article_ReadNum;
	}

	/**
	 * @param Article_ReadNum the Article_ReadNum to set
	 */
	
	public void setArticle_ReadNum(Integer Article_ReadNum) {
		Article_ReadNum = Article_ReadNum;
	}

	/**
	 * @return Article_ShareNum
	 */
	
	public Integer getArticle_ShareNum() {
		return Article_ShareNum;
	}

	/**
	 * @param Article_ShareNum the Article_ShareNum to set
	 */
	
	public void setArticle_ShareNum(Integer Article_ShareNum) {
		Article_ShareNum = Article_ShareNum;
	}

	/**
	 * @return Article_DiscussNum
	 */
	
	public Integer getArticle_DiscussNum() {
		return Article_DiscussNum;
	}

	/**
	 * @param Article_DiscussNum the Article_DiscussNum to set
	 */
	
	public void setArticle_DiscussNum(Integer Article_DiscussNum) {
		Article_DiscussNum = Article_DiscussNum;
	}

	/**
	 * @return Article_LikeNum
	 */
	
	public Integer getArticle_LikeNum() {
		return Article_LikeNum;
	}

	/**
	 * @param Article_LikeNum the Article_LikeNum to set
	 */
	
	public void setArticle_LikeNum(Integer Article_LikeNum) {
		Article_LikeNum = Article_LikeNum;
	}

	/**
	 * @return Article_Version
	 */
	
	public Integer getArticle_Version() {
		return Article_Version;
	}

	/**
	 * @param Article_Version the Article_Version to set
	 */
	
	public void setArticle_Version(Integer Article_Version) {
		Article_Version = Article_Version;
	}

	/**
	 * @return Article_Belong
	 */
	
	public Integer getArticle_Belong() {
		return Article_Belong;
	}

	/**
	 * @param Article_Belong the Article_Belong to set
	 */
	
	public void setArticle_Belong(Integer Article_Belong) {
		Article_Belong = Article_Belong;
	}

	/**
	 * @return Article_CreateTime
	 */
	
	public String getArticle_CreateTime() {
		return Article_CreateTime;
	}

	/**
	 * @param Article_CreateTime the Article_CreateTime to set
	 */
	
	public void setArticle_CreateTime(String Article_CreateTime) {
		Article_CreateTime = Article_CreateTime;
	}

	/**
	 * @return Article_ModifyTime
	 */
	@JSONField(serialize=false)
	public String getArticle_ModifyTime() {
		return Article_ModifyTime;
	}

	/**
	 * @param Article_ModifyTime the Article_ModifyTime to set
	 */
	
	public void setArticle_ModifyTime(String Article_ModifyTime) {
		this.Article_ModifyTime = Article_ModifyTime;
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
