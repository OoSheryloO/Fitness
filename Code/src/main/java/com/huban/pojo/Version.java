package com.huban.pojo;

import java.util.Date;
@SuppressWarnings("serial")
public class Version implements java.io.Serializable{
    private Long versionId;

    private Long versionTypeid;

    private int versionBuild;

    private String versionNumber;

    private String versionReleasenote;

    private Date versionReleasetime;

    private Date versionCreatetime;

    private Date versionModifytime;

    private Integer versionVersion;

    private Byte versionStatus;

    private Byte versionDeleted;

    private String versionFilepath;

    private Integer versionUpdatetype;
    
    private Integer versionIspublish;

    public Long getVersionId() {
        return versionId;
    }

    public void setVersionId(Long versionId) {
        this.versionId = versionId;
    }

    public Long getVersionTypeid() {
        return versionTypeid;
    }

    public void setVersionTypeid(Long versionTypeid) {
        this.versionTypeid = versionTypeid;
    }

    /**
	 * @return versionBuild
	 */
	
	public int getVersionBuild() {
		return versionBuild;
	}

	/**
	 * @param versionBuild the versionBuild to set
	 */
	
	public void setVersionBuild(int versionBuild) {
		this.versionBuild = versionBuild;
	}

	public String getVersionNumber() {
        return versionNumber;
    }

    public void setVersionNumber(String versionNumber) {
        this.versionNumber = versionNumber == null ? null : versionNumber.trim();
    }

    public String getVersionReleasenote() {
        return versionReleasenote;
    }

    public void setVersionReleasenote(String versionReleasenote) {
        this.versionReleasenote = versionReleasenote == null ? null : versionReleasenote.trim();
    }

    public Date getVersionReleasetime() {
        return versionReleasetime;
    }

    public void setVersionReleasetime(Date versionReleasetime) {
        this.versionReleasetime = versionReleasetime;
    }

    public Date getVersionCreatetime() {
        return versionCreatetime;
    }

    public void setVersionCreatetime(Date versionCreatetime) {
        this.versionCreatetime = versionCreatetime;
    }

    public Date getVersionModifytime() {
        return versionModifytime;
    }

    public void setVersionModifytime(Date versionModifytime) {
        this.versionModifytime = versionModifytime;
    }

    public Integer getVersionVersion() {
        return versionVersion;
    }

    public void setVersionVersion(Integer versionVersion) {
        this.versionVersion = versionVersion;
    }

    public Byte getVersionStatus() {
        return versionStatus;
    }

    public void setVersionStatus(Byte versionStatus) {
        this.versionStatus = versionStatus;
    }

    public Byte getVersionDeleted() {
        return versionDeleted;
    }

    public void setVersionDeleted(Byte versionDeleted) {
        this.versionDeleted = versionDeleted;
    }

    public String getVersionFilepath() {
        return versionFilepath;
    }

    public void setVersionFilepath(String versionFilepath) {
        this.versionFilepath = versionFilepath == null ? null : versionFilepath.trim();
    }

    public Integer getVersionUpdatetype() {
        return versionUpdatetype;
    }

    public void setVersionUpdatetype(Integer versionUpdatetype) {
        this.versionUpdatetype = versionUpdatetype;
    }

	public Integer getVersionIspublish() {
		return versionIspublish;
	}

	public void setVersionIspublish(Integer versionIspublish) {
		this.versionIspublish = versionIspublish;
	}
    
}