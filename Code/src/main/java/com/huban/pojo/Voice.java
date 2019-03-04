/*
 * 2018/01/08 hubantech Creating
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
public class Voice implements Serializable{

    /**  */
    public static final String COLUMN_Voice_Id = "Voice_Id";

    /**  */
    public static final String COLUMN_Voice_UserId = "Voice_UserId";

    /**  */
    public static final String COLUMN_Voice_PictureId = "Voice_PictureId";

    /**  */
    public static final String COLUMN_Voice_PictureUrl = "Voice_PictureUrl";

    /**  */
    public static final String COLUMN_Voice_HeadPictureId = "Voice_HeadPictureId";

    /**  */
    public static final String COLUMN_Voice_HeadPictureUrl = "Voice_HeadPictureUrl";
    
    /**  */
    public static final String COLUMN_Voice_Press = "Voice_Press";

    /**  */
    public static final String COLUMN_Voice_Author = "Voice_Author";

    /**  */
    public static final String COLUMN_Voice_Title = "Voice_Title";

    /**  */
    public static final String COLUMN_Voice_Content = "Voice_Content";

    /**  */
    public static final String COLUMN_Voice_Url = "Voice_Url";

    /**  */
    public static final String COLUMN_Voice_FileTime = "Voice_FileTime";

    /**  */
    public static final String COLUMN_Voice_ShareNum = "Voice_ShareNum";

    /**  */
    public static final String COLUMN_Voice_LikeNum = "Voice_LikeNum";
    
    /**  */
    public static final String COLUMN_Voice_ReadNum = "Voice_ReadNum";
    
    /**  */
    public static final String COLUMN_Voice_VoteNum = "Voice_VoteNum";
    
    /**  */
    public static final String COLUMN_Voice_DiscussNum = "Voice_DiscussNum";
    
    /**  */
    public static final String COLUMN_Voice_Version = "Voice_Version";

    /**  */
    public static final String COLUMN_Voice_Status = "Voice_Status";

    /**  */
    public static final String COLUMN_Voice_Delete = "Voice_Delete";

    /**  */
    public static final String COLUMN_Voice_Type = "Voice_Type";

    /**  */
    public static final String COLUMN_Voice_CreateTime = "Voice_CreateTime";

    /**  */
    public static final String COLUMN_Voice_ModifyTime = "Voice_ModifyTime";

    /**  */
    private Long Voice_Id;

    /**  */
    private Long Voice_UserId;

    /**  */
    private Long Voice_PictureId;

    /**  */
    private String Voice_PictureUrl;

    /**  */
    private Long Voice_HeadPictureId;

    /**  */
    private String Voice_HeadPictureUrl;
    
    private String Voice_Press;

    /**  */
    private String Voice_Author;

    /**  */
    private String Voice_Title;

    /**  */
    private String Voice_Content;

    /**  */
    private String Voice_Url;

    /**  */
    private String Voice_FileTime;

    /**  */
    private Integer Voice_ShareNum;

    /**  */
    private Integer Voice_LikeNum;
    
    /**  */
    private Integer Voice_ReadNum;
    
    /**  */
    private Integer Voice_VoteNum;
    
    /**  */
    private Integer Voice_DiscussNum;
    
    /**  */
    private Integer Voice_Version;

    /**  */
    @JSONField(serialize=false)
    private Integer Voice_Status;

    /**  */
    @JSONField(serialize=false)
    private Integer Voice_Delete;

    /**  */
    private Integer Voice_Type;
    
    /**  */
    private Integer Voice_Purpose;
    
    /**  */
    private String Voice_RedirectUrl;

    /**  */
    private String Voice_CreateTime;

    /**  */
    @JSONField(serialize=false)
    private String Voice_ModifyTime;
    
    /**  */
    private boolean ReadStatus;
    
    private boolean LikeStatus;
    
    private boolean VoteStatus;

    /**
     * <p>default constants</p>
     */
    public Voice() {

    }

	/**
	 * @return voice_Id
	 */
	
	public Long getVoice_Id() {
		return Voice_Id;
	}

	/**
	 * @param voice_Id the voice_Id to set
	 */
	
	public void setVoice_Id(Long voice_Id) {
		Voice_Id = voice_Id;
	}

	/**
	 * @return voice_UserId
	 */
	
	public Long getVoice_UserId() {
		return Voice_UserId;
	}

	/**
	 * @param voice_UserId the voice_UserId to set
	 */
	
	public void setVoice_UserId(Long voice_UserId) {
		Voice_UserId = voice_UserId;
	}

	/**
	 * @return voice_PictureId
	 */
	 @JSONField(serialize=false)
	public Long getVoice_PictureId() {
		return Voice_PictureId;
	}

	/**
	 * @param voice_PictureId the voice_PictureId to set
	 */
	
	public void setVoice_PictureId(Long voice_PictureId) {
		Voice_PictureId = voice_PictureId;
	}

	/**
	 * @return voice_PictureUrl
	 */
	
	public String getVoice_PictureUrl() {
		return Voice_PictureUrl;
	}

	/**
	 * @param voice_PictureUrl the voice_PictureUrl to set
	 */
	
	public void setVoice_PictureUrl(String voice_PictureUrl) {
		Voice_PictureUrl = voice_PictureUrl;
	}

	/**
	 * @return voice_HeadPictureId
	 */
	 @JSONField(serialize=false)
	public Long getVoice_HeadPictureId() {
		return Voice_HeadPictureId;
	}

	/**
	 * @param voice_HeadPictureId the voice_HeadPictureId to set
	 */
	
	public void setVoice_HeadPictureId(Long voice_HeadPictureId) {
		Voice_HeadPictureId = voice_HeadPictureId;
	}

	/**
	 * @return voice_HeadPictureUrl
	 */
	
	public String getVoice_HeadPictureUrl() {
		return Voice_HeadPictureUrl;
	}

	/**
	 * @param voice_HeadPictureUrl the voice_HeadPictureUrl to set
	 */
	
	public void setVoice_HeadPictureUrl(String voice_HeadPictureUrl) {
		Voice_HeadPictureUrl = voice_HeadPictureUrl;
	}

	/**
	 * @return voice_Press
	 */
	
	public String getVoice_Press() {
		return Voice_Press;
	}

	/**
	 * @param voice_Press the voice_Press to set
	 */
	
	public void setVoice_Press(String voice_Press) {
		Voice_Press = voice_Press;
	}

	/**
	 * @return voice_Author
	 */
	
	public String getVoice_Author() {
		return Voice_Author;
	}

	/**
	 * @param voice_Author the voice_Author to set
	 */
	
	public void setVoice_Author(String voice_Author) {
		Voice_Author = voice_Author;
	}

	/**
	 * @return voice_Title
	 */
	
	public String getVoice_Title() {
		return Voice_Title;
	}

	/**
	 * @param voice_Title the voice_Title to set
	 */
	
	public void setVoice_Title(String voice_Title) {
		Voice_Title = voice_Title;
	}

	/**
	 * @return voice_Content
	 */
	
	public String getVoice_Content() {
		return Voice_Content;
	}

	/**
	 * @param voice_Content the voice_Content to set
	 */
	
	public void setVoice_Content(String voice_Content) {
		Voice_Content = voice_Content;
	}

	/**
	 * @return voice_Url
	 */
	
	public String getVoice_Url() {
		return Voice_Url;
	}

	/**
	 * @param voice_Url the voice_Url to set
	 */
	
	public void setVoice_Url(String voice_Url) {
		Voice_Url = voice_Url;
	}

	/**
	 * @return voice_FileTime
	 */
	
	public String getVoice_FileTime() {
		return Voice_FileTime;
	}

	/**
	 * @param voice_FileTime the voice_FileTime to set
	 */
	
	public void setVoice_FileTime(String voice_FileTime) {
		Voice_FileTime = voice_FileTime;
	}

	/**
	 * @return voice_ShareNum
	 */
	
	public Integer getVoice_ShareNum() {
		return Voice_ShareNum;
	}

	/**
	 * @param voice_ShareNum the voice_ShareNum to set
	 */
	
	public void setVoice_ShareNum(Integer voice_ShareNum) {
		Voice_ShareNum = voice_ShareNum;
	}

	/**
	 * @return voice_LikeNum
	 */
	
	public Integer getVoice_LikeNum() {
		return Voice_LikeNum;
	}

	/**
	 * @param voice_LikeNum the voice_LikeNum to set
	 */
	
	public void setVoice_LikeNum(Integer voice_LikeNum) {
		Voice_LikeNum = voice_LikeNum;
	}

	/**
	 * @return voice_Status
	 */
	
	public Integer getVoice_Status() {
		return Voice_Status;
	}

	/**
	 * @return voice_ReadNum
	 */
	
	public Integer getVoice_ReadNum() {
		return Voice_ReadNum;
	}

	/**
	 * @param voice_ReadNum the voice_ReadNum to set
	 */
	
	public void setVoice_ReadNum(Integer voice_ReadNum) {
		Voice_ReadNum = voice_ReadNum;
	}

	/**
	 * @return voice_VoteNum
	 */
	
	public Integer getVoice_VoteNum() {
		return Voice_VoteNum;
	}

	/**
	 * @param voice_VoteNum the voice_VoteNum to set
	 */
	
	public void setVoice_VoteNum(Integer voice_VoteNum) {
		Voice_VoteNum = voice_VoteNum;
	}

	/**
	 * @return voice_DiscussNum
	 */
	
	public Integer getVoice_DiscussNum() {
		return Voice_DiscussNum;
	}

	/**
	 * @param voice_DiscussNum the voice_DiscussNum to set
	 */
	
	public void setVoice_DiscussNum(Integer voice_DiscussNum) {
		Voice_DiscussNum = voice_DiscussNum;
	}

	/**
	 * @return voice_Version
	 */
	
	public Integer getVoice_Version() {
		return Voice_Version;
	}

	/**
	 * @param voice_Version the voice_Version to set
	 */
	
	public void setVoice_Version(Integer voice_Version) {
		Voice_Version = voice_Version;
	}

	/**
	 * @param voice_Status the voice_Status to set
	 */
	
	public void setVoice_Status(Integer voice_Status) {
		Voice_Status = voice_Status;
	}

	/**
	 * @return voice_Delete
	 */
	 @JSONField(serialize=false)
	public Integer getVoice_Delete() {
		return Voice_Delete;
	}

	/**
	 * @param voice_Delete the voice_Delete to set
	 */
	
	public void setVoice_Delete(Integer voice_Delete) {
		Voice_Delete = voice_Delete;
	}

	/**
	 * @return voice_Type
	 */
	
	public Integer getVoice_Type() {
		return Voice_Type;
	}

	/**
	 * @param voice_Type the voice_Type to set
	 */
	
	public void setVoice_Type(Integer voice_Type) {
		Voice_Type = voice_Type;
	}

	/**
	 * @return voice_Purpose
	 */
	
	public Integer getVoice_Purpose() {
		return Voice_Purpose;
	}

	/**
	 * @param voice_Purpose the voice_Purpose to set
	 */
	
	public void setVoice_Purpose(Integer voice_Purpose) {
		Voice_Purpose = voice_Purpose;
	}

	/**
	 * @return voice_RedirectUrl
	 */
	
	public String getVoice_RedirectUrl() {
		return Voice_RedirectUrl;
	}

	/**
	 * @param voice_RedirectUrl the voice_RedirectUrl to set
	 */
	
	public void setVoice_RedirectUrl(String voice_RedirectUrl) {
		Voice_RedirectUrl = voice_RedirectUrl;
	}

	/**
	 * @return voice_CreateTime
	 */
	
	public String getVoice_CreateTime() {
		return Voice_CreateTime;
	}

	/**
	 * @param voice_CreateTime the voice_CreateTime to set
	 */
	
	public void setVoice_CreateTime(String voice_CreateTime) {
		Voice_CreateTime = voice_CreateTime;
	}

	/**
	 * @return voice_ModifyTime
	 */
	 @JSONField(serialize=false)
	public String getVoice_ModifyTime() {
		return Voice_ModifyTime;
	}

	/**
	 * @param voice_ModifyTime the voice_ModifyTime to set
	 */
	
	public void setVoice_ModifyTime(String voice_ModifyTime) {
		Voice_ModifyTime = voice_ModifyTime;
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
