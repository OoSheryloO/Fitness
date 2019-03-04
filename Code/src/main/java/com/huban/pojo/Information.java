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
public class Information implements java.io.Serializable{

    /**  */
    public static final String COLUMN_Information_Id = "Information_Id";

    /**  */
    public static final String COLUMN_Information_UserId = "Information_UserId";

    /**  */
    public static final String COLUMN_Information_Title = "Information_Title";

    /**  */
    public static final String COLUMN_Information_Content = "Information_Content";

    /**  */
    public static final String COLUMN_Information_Afterword = "Information_Afterword";
    
    /**  */
    public static final String COLUMN_Information_Auther = "Information_Auther";

    /**  */
    public static final String COLUMN_Information_Address = "Information_Address";

    /**  */
    public static final String COLUMN_Information_Press = "Information_Press";

    /**  */
    public static final String COLUMN_Information_HeadPictureId = "Information_HeadPictureId";

    /**  */
    public static final String COLUMN_Information_HeadPictureUrl = "Information_HeadPictureUrl";
    
    /**  */
    public static final String COLUMN_Information_PictureId = "Information_PictureId";

    /**  */
    public static final String COLUMN_Information_PictureUrl = "Information_PictureUrl";

    /**  */
    public static final String COLUMN_Information_RecommendType = "Information_RecommendType";

    /**  */
    public static final String COLUMN_Information_Hotspot = "Information_Hotspot";

    /**  */
    public static final String COLUMN_Information_Type = "Information_Type";

    /**  */
    public static final String COLUMN_Information_Sort = "Information_Sort";

    /**  */
    public static final String COLUMN_Information_Purpose = "Information_Purpose";

    /**  */
    public static final String COLUMN_Information_RedirectUrl = "Information_RedirectUrl";

    /**  */
    public static final String COLUMN_Information_Status = "Information_Status";

    /**  */
    public static final String COLUMN_Information_Delete = "Information_Delete";

    /**  */
    public static final String COLUMN_Information_VoteNum = "Information_VoteNum";

    /**  */
    public static final String COLUMN_Information_ReadNum = "Information_ReadNum";

    /**  */
    public static final String COLUMN_Information_Version = "Information_Version";
    
    /**  */
    public static final String COLUMN_Information_DiscussNum = "Information_DiscussNum";

    /**  */
    public static final String COLUMN_Information_LikeNum = "Information_LikeNum";
    
    /**  */
    public static final String COLUMN_Information_ShareNum = "Information_ShareNum";
    
    /**  */
    public static final String COLUMN_Information_Belong = "Information_Belong";

    /**  */
    public static final String COLUMN_Information_CreateTime = "Information_CreateTime";

    /**  */
    private Long Information_Id;

    /**  */
    private Long Information_UserId;

    /**  */
    private String Information_Title;

    /**  */
    private String Information_Content;

    /**  */
    private String Information_Afterword;

    /**  */
    private String Information_Address;
    
    /**  */
    private String Information_Auther;

    /**  */
    private String Information_Press;

    /**  */
    private String Information_HeadPictureId;
    
    /**  */
    private String Information_HeadPictureUrl;
    
    /**  */
    private String Information_PictureId;
    
    /**  */
    private String Information_PictureUrl;

    /**  */
    private String Information_RecommendType;

    /**  */
    private Integer Information_Hotspot;

    /**  */
    private Integer Information_Type;

    /**  */
    private Integer Information_Sort;

    /**  */
    private Integer Information_Purpose;

    /**  */
    private String Information_RedirectUrl;

    /**  */
    private Integer Information_Status;

    /**  */
    private Integer Information_VoteNum;
    
    /**  */
    private Integer Information_ShareNum;
    
    /**  */
    private Integer Information_DiscussNum;
    
    /**  */
    private Integer Information_LikeNum;
    
    /**  */
    private Integer Information_Version;

    /**  */
    private Integer Information_ReadNum;
    
    /** */
    private Integer Information_Belong;

    /**  */
    private String Information_CreateTime;
    
    /**  */
    private Integer Information_Delete;
    
    /** */
    private String Information_ModifyTime;
    
    /**  */
    private boolean ReadStatus;
    
    private boolean LikeStatus;
    
    private boolean VoteStatus;
    
    /**
     * <p>default constants</p>
     */
    public Information() {

    }

	/**
	 * @return information_Id
	 */
	
	public Long getInformation_Id() {
		return Information_Id;
	}

	/**
	 * @param information_Id the information_Id to set
	 */
	
	public void setInformation_Id(Long information_Id) {
		Information_Id = information_Id;
	}

	/**
	 * @return information_UserId
	 */
	
	public Long getInformation_UserId() {
		return Information_UserId;
	}

	/**
	 * @param information_UserId the information_UserId to set
	 */
	
	public void setInformation_UserId(Long information_UserId) {
		Information_UserId = information_UserId;
	}

	/**
	 * @return information_Title
	 */
	
	public String getInformation_Title() {
		return Information_Title;
	}

	/**
	 * @param information_Title the information_Title to set
	 */
	
	public void setInformation_Title(String information_Title) {
		Information_Title = information_Title;
	}

	/**
	 * @return information_Content
	 */
	
	public String getInformation_Content() {
		return Information_Content;
	}

	/**
	 * @param information_Content the information_Content to set
	 */
	
	public void setInformation_Content(String information_Content) {
		Information_Content = information_Content;
	}

	/**
	 * @return information_Afterword
	 */
	
	public String getInformation_Afterword() {
		return Information_Afterword;
	}

	/**
	 * @param information_Afterword the information_Afterword to set
	 */
	
	public void setInformation_Afterword(String information_Afterword) {
		Information_Afterword = information_Afterword;
	}

	/**
	 * @return information_Auther
	 */
	
	public String getInformation_Auther() {
		return Information_Auther;
	}

	/**
	 * @param information_Auther the information_Auther to set
	 */
	
	public void setInformation_Auther(String information_Auther) {
		Information_Auther = information_Auther;
	}

	/**
	 * @return information_Address
	 */
	
	public String getInformation_Address() {
		return Information_Address;
	}

	/**
	 * @param information_Address the information_Address to set
	 */
	
	public void setInformation_Address(String information_Address) {
		Information_Address = information_Address;
	}

	/**
	 * @return information_Press
	 */
	
	public String getInformation_Press() {
		return Information_Press;
	}

	/**
	 * @param information_Press the information_Press to set
	 */
	
	public void setInformation_Press(String information_Press) {
		Information_Press = information_Press;
	}

	/**
	 * @return information_HeadPictureId
	 */
	@JSONField(serialize=false)
	public String getInformation_HeadPictureId() {
		return Information_HeadPictureId;
	}

	/**
	 * @param information_HeadPictureId the information_HeadPictureId to set
	 */
	
	public void setInformation_HeadPictureId(String information_HeadPictureId) {
		Information_HeadPictureId = information_HeadPictureId;
	}

	/**
	 * @return information_HeadPictureUrl
	 */
	
	public String getInformation_HeadPictureUrl() {
		return Information_HeadPictureUrl;
	}

	/**
	 * @param information_HeadPictureUrl the information_HeadPictureUrl to set
	 */
	
	public void setInformation_HeadPictureUrl(String information_HeadPictureUrl) {
		Information_HeadPictureUrl = information_HeadPictureUrl;
	}

	/**
	 * @return information_PictureId
	 */
	@JSONField(serialize=false)
	public String getInformation_PictureId() {
		return Information_PictureId;
	}

	/**
	 * @param information_PictureId the information_PictureId to set
	 */
	
	public void setInformation_PictureId(String information_PictureId) {
		Information_PictureId = information_PictureId;
	}

	/**
	 * @return information_PictureUrl
	 */
	
	public String getInformation_PictureUrl() {
		return Information_PictureUrl;
	}

	/**
	 * @param information_PictureUrl the information_PictureUrl to set
	 */
	
	public void setInformation_PictureUrl(String information_PictureUrl) {
		Information_PictureUrl = information_PictureUrl;
	}

	/**
	 * @return information_RecommendType
	 */
	
	public String getInformation_RecommendType() {
		return Information_RecommendType;
	}

	/**
	 * @param information_RecommendType the information_RecommendType to set
	 */
	
	public void setInformation_RecommendType(String information_RecommendType) {
		Information_RecommendType = information_RecommendType;
	}

	/**
	 * @return information_Hotspot
	 */
	
	public Integer getInformation_Hotspot() {
		return Information_Hotspot;
	}

	/**
	 * @param information_Hotspot the information_Hotspot to set
	 */
	
	public void setInformation_Hotspot(Integer information_Hotspot) {
		Information_Hotspot = information_Hotspot;
	}

	/**
	 * @return information_Type
	 */
	
	public Integer getInformation_Type() {
		return Information_Type;
	}

	/**
	 * @param information_Type the information_Type to set
	 */
	
	public void setInformation_Type(Integer information_Type) {
		Information_Type = information_Type;
	}

	/**
	 * @return information_Sort
	 */
	
	public Integer getInformation_Sort() {
		return Information_Sort;
	}

	/**
	 * @param information_Sort the information_Sort to set
	 */
	
	public void setInformation_Sort(Integer information_Sort) {
		Information_Sort = information_Sort;
	}

	/**
	 * @return information_Purpose
	 */
	
	public Integer getInformation_Purpose() {
		return Information_Purpose;
	}

	/**
	 * @param information_Purpose the information_Purpose to set
	 */
	
	public void setInformation_Purpose(Integer information_Purpose) {
		Information_Purpose = information_Purpose;
	}

	/**
	 * @return information_RedirectUrl
	 */
	
	public String getInformation_RedirectUrl() {
		return Information_RedirectUrl;
	}

	/**
	 * @param information_RedirectUrl the information_RedirectUrl to set
	 */
	
	public void setInformation_RedirectUrl(String information_RedirectUrl) {
		Information_RedirectUrl = information_RedirectUrl;
	}

	/**
	 * @return information_Status
	 */
	
	public Integer getInformation_Status() {
		return Information_Status;
	}

	/**
	 * @param information_Status the information_Status to set
	 */
	
	public void setInformation_Status(Integer information_Status) {
		Information_Status = information_Status;
	}

	/**
	 * @return information_Delete
	 */
	
	public Integer getInformation_Delete() {
		return Information_Delete;
	}

	/**
	 * @param information_Delete the information_Delete to set
	 */
	
	public void setInformation_Delete(Integer information_Delete) {
		Information_Delete = information_Delete;
	}

	/**
	 * @return information_VoteNum
	 */
	
	public Integer getInformation_VoteNum() {
		return Information_VoteNum;
	}

	/**
	 * @param information_VoteNum the information_VoteNum to set
	 */
	
	public void setInformation_VoteNum(Integer information_VoteNum) {
		Information_VoteNum = information_VoteNum;
	}

	/**
	 * @return information_ReadNum
	 */
	
	public Integer getInformation_ReadNum() {
		return Information_ReadNum;
	}

	/**
	 * @param information_ReadNum the information_ReadNum to set
	 */
	
	public void setInformation_ReadNum(Integer information_ReadNum) {
		Information_ReadNum = information_ReadNum;
	}

	/**
	 * @return information_ShareNum
	 */
	
	public Integer getInformation_ShareNum() {
		return Information_ShareNum;
	}

	/**
	 * @param information_ShareNum the information_ShareNum to set
	 */
	
	public void setInformation_ShareNum(Integer information_ShareNum) {
		Information_ShareNum = information_ShareNum;
	}

	/**
	 * @return information_DiscussNum
	 */
	
	public Integer getInformation_DiscussNum() {
		return Information_DiscussNum;
	}

	/**
	 * @param information_DiscussNum the information_DiscussNum to set
	 */
	
	public void setInformation_DiscussNum(Integer information_DiscussNum) {
		Information_DiscussNum = information_DiscussNum;
	}

	/**
	 * @return information_LikeNum
	 */
	
	public Integer getInformation_LikeNum() {
		return Information_LikeNum;
	}

	/**
	 * @param information_LikeNum the information_LikeNum to set
	 */
	
	public void setInformation_LikeNum(Integer information_LikeNum) {
		Information_LikeNum = information_LikeNum;
	}

	/**
	 * @return information_Version
	 */
	
	public Integer getInformation_Version() {
		return Information_Version;
	}

	/**
	 * @param information_Version the information_Version to set
	 */
	
	public void setInformation_Version(Integer information_Version) {
		Information_Version = information_Version;
	}

	/**
	 * @return information_Belong
	 */
	
	public Integer getInformation_Belong() {
		return Information_Belong;
	}

	/**
	 * @param information_Belong the information_Belong to set
	 */
	
	public void setInformation_Belong(Integer information_Belong) {
		Information_Belong = information_Belong;
	}

	/**
	 * @return information_CreateTime
	 */
	
	public String getInformation_CreateTime() {
		return Information_CreateTime;
	}

	/**
	 * @param information_CreateTime the information_CreateTime to set
	 */
	
	public void setInformation_CreateTime(String information_CreateTime) {
		Information_CreateTime = information_CreateTime;
	}

	/**
	 * @return information_ModifyTime
	 */
	@JSONField(serialize=false)
	public String getInformation_ModifyTime() {
		return Information_ModifyTime;
	}

	/**
	 * @param information_ModifyTime the information_ModifyTime to set
	 */
	
	public void setInformation_ModifyTime(String information_ModifyTime) {
		this.Information_ModifyTime = information_ModifyTime;
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
