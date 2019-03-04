package com.huban.pojo;

import java.io.Serializable;
import java.util.Date;
@SuppressWarnings("serial")
public class Dict implements Serializable{

    private Long dictId;
    
    private Long dictNumber;

    private String dictValue;

    private Date dictCreatetime;

    private Date dictModifytime;

    private Integer dictDelete;

    private Integer dictType;
    
    private Integer dictStatus;

	public Long getDictId() {
		return dictId;
	}

	public void setDictId(Long dictId) {
		this.dictId = dictId;
	}

	public Long getDictNumber() {
		return dictNumber;
	}

	public void setDictNumber(Long dictNumber) {
		this.dictNumber = dictNumber;
	}

	public String getDictValue() {
		return dictValue;
	}

	public void setDictValue(String dictValue) {
		this.dictValue = dictValue;
	}

	public Date getDictCreatetime() {
		return dictCreatetime;
	}

	public void setDictCreatetime(Date dictCreatetime) {
		this.dictCreatetime = dictCreatetime;
	}

	public Date getDictModifytime() {
		return dictModifytime;
	}

	public void setDictModifytime(Date dictModifytime) {
		this.dictModifytime = dictModifytime;
	}

	public Integer getDictDelete() {
		return dictDelete;
	}

	public void setDictDelete(Integer dictDelete) {
		this.dictDelete = dictDelete;
	}

	public Integer getDictType() {
		return dictType;
	}

	public void setDictType(Integer dictType) {
		this.dictType = dictType;
	}

	public Integer getDictStatus() {
		return dictStatus;
	}

	public void setDictStatus(Integer dictStatus) {
		this.dictStatus = dictStatus;
	}

  
}