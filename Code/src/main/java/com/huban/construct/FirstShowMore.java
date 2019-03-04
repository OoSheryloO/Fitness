package com.huban.construct;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
@SuppressWarnings("serial")
public class FirstShowMore implements Serializable{
	
	String ownerContent ;
	
	List<NameAndComment> nameandcomment;
	



	public String getOwnerContent() {
		return ownerContent;
	}

	public void setOwnerContent(String ownerContent) {
		this.ownerContent = ownerContent;
	}

	public List<NameAndComment> getNameandcomment() {
		return nameandcomment;
	}

	public void setNameandcomment(List<NameAndComment> nameandcomment) {
		this.nameandcomment = nameandcomment;
	}


}
