package com.huban.construct;

import java.io.Serializable;
import java.util.Date;
/**
 * <p>Title: JournalModel.java</p>
 * <p>Description: 日志返回model </p>
 * <p>Company: </p>
 * @author Sheryl
 * @created 2017年10月12日 下午1:34:50
 */
@SuppressWarnings("serial")
public class JournalModel implements Serializable{
	
	private String Name;
	
	private String IconUrl;
	
	private String Mood;
	
	private String Weather;
	
	private String Title;

    private String Content;
    
    private String Adscript;
    
    private int Status;
    
    private int ShareCount;

    private int Version;
    
    private Date Createtime;

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getIconUrl() {
		return IconUrl;
	}

	public void setIconUrl(String iconUrl) {
		IconUrl = iconUrl;
	}

	public String getMood() {
		return Mood;
	}

	public void setMood(String mood) {
		Mood = mood;
	}

	public String getWeather() {
		return Weather;
	}

	public void setWeather(String weather) {
		Weather = weather;
	}

	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}

	public String getAdscript() {
		return Adscript;
	}

	public void setAdscript(String adscript) {
		Adscript = adscript;
	}

	public int getStatus() {
		return Status;
	}

	public void setStatus(int status) {
		Status = status;
	}

	public int getShareCount() {
		return ShareCount;
	}

	public void setShareCount(int shareCount) {
		ShareCount = shareCount;
	}

	public int getVersion() {
		return Version;
	}

	public void setVersion(int version) {
		Version = version;
	}

	public Date getCreatetime() {
		return Createtime;
	}

	public void setCreatetime(Date createtime) {
		Createtime = createtime;
	}

}
