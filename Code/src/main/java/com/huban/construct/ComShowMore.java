package com.huban.construct;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
@SuppressWarnings("serial")
public class ComShowMore implements Serializable{
	
	private String username;
	
	private String headicon;
	
	private Date firstcommenttime;
	
	List<NameAndComment> nameandcomment;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getheadicon() {
		return headicon;
	}

	public void setheadicon(String headicon) {
		this.headicon = headicon;
	}

	public Date getFirstcommenttime() {
		return firstcommenttime;
	}

	public void setFirstcommenttime(Date firstcommenttime) {
		this.firstcommenttime = firstcommenttime;
	}

	public List<NameAndComment> getNameandcomment() {
		return nameandcomment;
	}

	public void setNameandcomment(List<NameAndComment> nameandcomment) {
		this.nameandcomment = nameandcomment;
	}
	
}
