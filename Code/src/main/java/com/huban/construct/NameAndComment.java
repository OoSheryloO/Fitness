package com.huban.construct;

import java.io.Serializable;

@SuppressWarnings("serial")
public class NameAndComment implements Serializable{
	private String userName;
	
	private String comment;
	
	private Long replyDateId;
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Long getReplyDateId() {
		return replyDateId;
	}

	public void setReplyDateId(Long replyDateId) {
		this.replyDateId = replyDateId;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	
	
	
}
