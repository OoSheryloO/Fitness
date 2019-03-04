package com.huban.construct;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
@SuppressWarnings("serial")
public class FirstComList implements Serializable{
	
	private Long noteId;
	
	private String notename;
	
//	private String noteheadicon;
	
	private Date firstcommenttime;
	
//	List<NameAndComment> nameandcomment;
	
	List<FirstShowMore> firstShowMores;
	
	public List<FirstShowMore> getFirstShowMores() {
		return firstShowMores;
	}

	public void setFirstShowMores(List<FirstShowMore> firstShowMores) {
		this.firstShowMores = firstShowMores;
	}

	public Long getNoteId() {
		return noteId;
	}

	public void setNoteId(Long noteId) {
		this.noteId = noteId;
	}

//	public List<NameAndComment> getNameandcomment() {
//		return nameandcomment;
//	}
//
//	public void setNameandcomment(List<NameAndComment> nameandcomment) {
//		this.nameandcomment = nameandcomment;
//	}

	public String getNotename() {
		return notename;
	}

	public void setNotename(String notename) {
		this.notename = notename;
	}

//	public String getNoteheadicon() {
//		return noteheadicon;
//	}
//
//	public void setNoteheadicon(String noteheadicon) {
//		this.noteheadicon = noteheadicon;
//	}

	public Date getFirstcommenttime() {
		return firstcommenttime;
	}

	public void setFirstcommenttime(Date firstcommenttime) {
		this.firstcommenttime = firstcommenttime;
	}

}
