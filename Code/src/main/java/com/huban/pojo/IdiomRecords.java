package com.huban.pojo;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

public class IdiomRecords {
	public static final String sIdiomClass = "Idiom";
	
    private Long idiomId;

    private Long idiomOriginid;
    
    private Long idiomParentid;

    private Long idiomUserid;

    private String idiomContent;

    private Integer idiomType;

    private Integer idiomDelete;

    private Date idiomCreatetime;
    
    private Date idiomModifytime;
    @JSONField(serialize=false)
    private int type;

    public Long getIdiomId() {
        return idiomId;
    }

    public void setIdiomId(Long idiomId) {
        this.idiomId = idiomId;
    }

    public Long getIdiomOriginid() {
        return idiomOriginid;
    }

    public void setIdiomOriginid(Long idiomOriginid) {
        this.idiomOriginid = idiomOriginid;
    }

    /**
	 * @return idiomParentid
	 */
	
	public Long getIdiomParentid() {
		return idiomParentid;
	}

	/**
	 * @param idiomParentid the idiomParentid to set
	 */
	
	public void setIdiomParentid(Long idiomParentid) {
		this.idiomParentid = idiomParentid;
	}

	public Long getIdiomUserid() {
        return idiomUserid;
    }

    public void setIdiomUserid(Long idiomUserid) {
        this.idiomUserid = idiomUserid;
    }

    public String getIdiomContent() {
        return idiomContent;
    }

    public void setIdiomContent(String idiomContent) {
        this.idiomContent = idiomContent == null ? null : idiomContent.trim();
    }

    public Integer getIdiomType() {
        return idiomType;
    }

    public void setIdiomType(Integer idiomType) {
        this.idiomType = idiomType;
    }

    public Integer getIdiomDelete() {
        return idiomDelete;
    }

    public void setIdiomDelete(Integer idiomDelete) {
        this.idiomDelete = idiomDelete;
    }

    public Date getIdiomCreatetime() {
        return idiomCreatetime;
    }

    public void setIdiomCreatetime(Date idiomCreatetime) {
        this.idiomCreatetime = idiomCreatetime;
    }

    public Date getIdiomModifytime() {
        return idiomModifytime;
    }

    public void setIdiomModifytime(Date idiomModifytime) {
        this.idiomModifytime = idiomModifytime;
    }

	/**
	 * @return type
	 */
	
	public int getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	
	public void setType(int type) {
		this.type = type;
	}
    
}