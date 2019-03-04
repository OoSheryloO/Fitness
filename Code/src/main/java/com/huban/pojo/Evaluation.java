package com.huban.pojo;

/**
 * 
 * @author MaJian
 * 评测系统
 */
public class Evaluation {
	
	private long id;
	private String pkid;
	private String url;
	private String name;
	private String createTime;
	private String modifyTime;
	private int status;
	private int level;
	private long parent;
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getPkid() {
		return pkid;
	}
	public void setPkid(String pkid) {
		this.pkid = pkid;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCreateTime() {
		return createTime.substring(0, 19);
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getModifyTime() {
		return modifyTime.substring(0, 19);
	}
	public void setModifyTime(String modifyTime) {
		this.modifyTime = modifyTime;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public long getParent() {
		return parent;
	}
	public void setParent(long parent) {
		this.parent = parent;
	}
	
	
}
