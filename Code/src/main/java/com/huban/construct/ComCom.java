package com.huban.construct;

import java.util.Date;
import java.util.List;

public class ComCom {
	
	private List<ComCom> comcom;
	
	private String comment;
	
	private Long Replydateid;
	
	private Date time;

	public List<ComCom> getComcom() {
		return comcom;
	}

	public void setComcom(List<ComCom> comcom) {
		this.comcom = comcom;
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

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}
	

}
