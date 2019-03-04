package com.huban.pojo;

import java.io.Serializable;
import java.util.Date;
@SuppressWarnings("serial")
public class Note implements Serializable{
    private Long noteId;

    private Long noteUserid;
    
    private Long noteFromUserId;

    private String noteTitle;

    private String noteContent;

    private Date noteCreatetime;

    private Date noteModifytime;

    private String noteLaudnumber;

    private String noteCommentnumber;

    private Integer noteStatus;

    private Integer noteDelete;
    
    private String noteUserName;
    
    private String noteUserImg;

    private String noteimg1;
    
    private String noteimg2;
    
    private String noteimg3;
    
    private Long noteTypeid;
    
    private boolean LikeStatus;
    
    
    public Long getNoteTypeid() {
		return noteTypeid;
	}

	public void setNoteTypeid(Long noteTypeid) {
		this.noteTypeid = noteTypeid;
	}

	/**
	 * @return the noteFromUserId
	 */
	public Long getNoteFromUserId() {
		return noteFromUserId;
	}

	/**
	 * @param noteFromUserId the noteFromUserId to set
	 */
	public void setNoteFromUserId(Long noteFromUserId) {
		this.noteFromUserId = noteFromUserId;
	}

	/**
	 * @return the noteimg1
	 */
	public String getNoteimg1() {
		return noteimg1;
	}

	/**
	 * @param noteimg1 the noteimg1 to set
	 */
	public void setNoteimg1(String noteimg1) {
		this.noteimg1 = noteimg1;
	}

	/**
	 * @return the noteimg2
	 */
	public String getNoteimg2() {
		return noteimg2;
	}

	/**
	 * @param noteimg2 the noteimg2 to set
	 */
	public void setNoteimg2(String noteimg2) {
		this.noteimg2 = noteimg2;
	}

	/**
	 * @return the noteimg3
	 */
	public String getNoteimg3() {
		return noteimg3;
	}

	/**
	 * @param noteimg3 the noteimg3 to set
	 */
	public void setNoteimg3(String noteimg3) {
		this.noteimg3 = noteimg3;
	}

	/**
	 * @return the noteUserName
	 */
	public String getNoteUserName() {
		return noteUserName;
	}

	/**
	 * @param noteUserName the noteUserName to set
	 */
	public void setNoteUserName(String noteUserName) {
		this.noteUserName = noteUserName;
	}

	/**
	 * @return the noteUserImg
	 */
	public String getNoteUserImg() {
		return noteUserImg;
	}

	/**
	 * @param noteUserImg the noteUserImg to set
	 */
	public void setNoteUserImg(String noteUserImg) {
		this.noteUserImg = noteUserImg;
	}



    /**
	 * @return the noteId
	 */
	

	public Long getNoteUserid() {
        return noteUserid;
    }

    public Long getNoteId() {
		return noteId;
	}

	public void setNoteId(Long noteId) {
		this.noteId = noteId;
	}

	public void setNoteUserid(Long noteUserid) {
        this.noteUserid = noteUserid;
    }

    public String getNoteTitle() {
        return noteTitle;
    }

    public void setNoteTitle(String noteTitle) {
        this.noteTitle = noteTitle == null ? null : noteTitle.trim();
    }

    public String getNoteContent() {
        return noteContent;
    }

    public void setNoteContent(String noteContent) {
        this.noteContent = noteContent == null ? null : noteContent.trim();
    }

    public Date getNoteCreatetime() {
        return noteCreatetime;
    }

    public void setNoteCreatetime(Date noteCreatetime) {
        this.noteCreatetime = noteCreatetime;
    }

    public Date getNoteModifytime() {
        return noteModifytime;
    }

    public void setNoteModifytime(Date noteModifytime) {
        this.noteModifytime = noteModifytime;
    }

    public String getNoteLaudnumber() {
        return noteLaudnumber;
    }

    public void setNoteLaudnumber(String noteLaudnumber) {
        this.noteLaudnumber = noteLaudnumber == null ? null : noteLaudnumber.trim();
    }

    public String getNoteCommentnumber() {
        return noteCommentnumber;
    }

    public void setNoteCommentnumber(String noteCommentnumber) {
        this.noteCommentnumber = noteCommentnumber == null ? null : noteCommentnumber.trim();
    }

    public Integer getNoteStatus() {
        return noteStatus;
    }

    public void setNoteStatus(Integer noteStatus) {
        this.noteStatus = noteStatus;
    }

    public Integer getNoteDelete() {
        return noteDelete;
    }

    public void setNoteDelete(Integer noteDelete) {
        this.noteDelete = noteDelete;
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
    
    
    
}