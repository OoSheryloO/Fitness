package com.huban.Utils;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.huban.construct.NameAndComment;
@SuppressWarnings("serial")
public class ForumComment implements Serializable{
	
//	private Long noteId;
	
	private String username;
	
	private String userheadicon;
	
		
	private Date firstcommenttime;
	
	private String comment;
	
	private Long Replydateid;
	
//	private Date time;
	
	private List<NameAndComment> nameandcomment;

//	public Long getNoteId() {
//		return noteId;
//	}
//
//	public void setNoteId(Long noteId) {
//		this.noteId = noteId;
//	}
//
//	public String getNotename() {
//		return notename;
//	}
//
//	public void setNotename(String notename) {
//		this.notename = notename;
//	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserheadicon() {
		return userheadicon;
	}

	public void setUserheadicon(String userheadicon) {
		this.userheadicon = userheadicon;
	}

	public Date getFirstcommenttime() {
		return firstcommenttime;
	}

	public void setFirstcommenttime(Date firstcommenttime) {
		this.firstcommenttime = firstcommenttime;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Long getReplydateid() {
		return Replydateid;
	}

	public void setReplydateid(Long replydateid) {
		Replydateid = replydateid;
	}

	public List<NameAndComment> getNameandcomment() {
		return nameandcomment;
	}

	public void setNameandcomment(List<NameAndComment> nameandcomment) {
		this.nameandcomment = nameandcomment;
	}
	
	
	
}
