package com.huban.pojo;

import java.util.Date;

public class Bookpart {
	
	private String bookpartImgurl;
	
    private Long bookpartId;

    private Long bookpartUserid;

    private Long bookpartBookid;

    private Date bookpartCreattime;

    private Date bookpartModifytime;

    private Integer bookpartStatus;

    private String bookpartComment;

  
	/**
	 * @return the bookImgurl
	 */
	public String getBookpartImgurl() {
		return bookpartImgurl;
	}

	

	/**
	 * @param bookImgurl the bookImgurl to set
	 */
	public void setBookpartImgurl(String bookpartImgurl) {
		this.bookpartImgurl = bookpartImgurl;
	}
	

	public Long getBookpartId() {
        return bookpartId;
    }

    public void setBookpartId(Long bookpartId) {
        this.bookpartId = bookpartId;
    }

    public Long getBookpartUserid() {
        return bookpartUserid;
    }

    public void setBookpartUserid(Long bookpartUserid) {
        this.bookpartUserid = bookpartUserid;
    }

    public Long getBookpartBookid() {
        return bookpartBookid;
    }

    public void setBookpartBookid(Long bookpartBookid) {
        this.bookpartBookid = bookpartBookid;
    }

    public Date getBookpartCreattime() {
        return bookpartCreattime;
    }

    public void setBookpartCreattime(Date bookpartCreattime) {
        this.bookpartCreattime = bookpartCreattime;
    }

    public Date getBookpartModifytime() {
        return bookpartModifytime;
    }

    public void setBookpartModifytime(Date bookpartModifytime) {
        this.bookpartModifytime = bookpartModifytime;
    }

    public Integer getBookpartStatus() {
        return bookpartStatus;
    }

    public void setBookpartStatus(Integer bookpartStatus) {
        this.bookpartStatus = bookpartStatus;
    }

    public String getBookpartComment() {
        return bookpartComment;
    }

    public void setBookpartComment(String bookpartComment) {
        this.bookpartComment = bookpartComment == null ? null : bookpartComment.trim();
    }
}