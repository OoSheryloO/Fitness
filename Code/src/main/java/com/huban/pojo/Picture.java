package com.huban.pojo;

import java.util.Date;
@SuppressWarnings("serial")
public class Picture implements java.io.Serializable{
    private Long pictureId;

    private Long pictureUserid;

    private Long pictureTypeid;

    private String pictureIdentity;

    private String pictureProtocol;

    private String pictureDomain;

    private String pictureIpaddress;

    private String picturePort;

    private String pictureFolder;

    private String picturePath;

    private String pictureName;

    private Date pictureCreatetime;

    private Date pictureModifytime;

    private Integer pictureVersion;

    private Byte pictureStatus;
    
    private Byte picturePurpose;

    private Byte pictureDeleted;

    private Long pictureBelongid;

    private String pictureRedirecturl;
    
    private String pictureUrl;
    
    private String pictureSort;

    public String getPictureUrl() {
		return pictureUrl;
	}

	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}

	public Long getPictureId() {
        return pictureId;
    }

    public void setPictureId(Long pictureId) {
        this.pictureId = pictureId;
    }

    public Long getPictureUserid() {
        return pictureUserid;
    }

    public void setPictureUserid(Long pictureUserid) {
        this.pictureUserid = pictureUserid;
    }

    public Long getPictureTypeid() {
        return pictureTypeid;
    }

    public void setPictureTypeid(Long pictureTypeid) {
        this.pictureTypeid = pictureTypeid;
    }

    public String getPictureIdentity() {
        return pictureIdentity;
    }

    public void setPictureIdentity(String pictureIdentity) {
        this.pictureIdentity = pictureIdentity == null ? null : pictureIdentity.trim();
    }

    public String getPictureProtocol() {
        return pictureProtocol;
    }

    public void setPictureProtocol(String pictureProtocol) {
        this.pictureProtocol = pictureProtocol == null ? null : pictureProtocol.trim();
    }

    public String getPictureDomain() {
        return pictureDomain;
    }

    public void setPictureDomain(String pictureDomain) {
        this.pictureDomain = pictureDomain == null ? null : pictureDomain.trim();
    }

    public String getPictureIpaddress() {
        return pictureIpaddress;
    }

    public void setPictureIpaddress(String pictureIpaddress) {
        this.pictureIpaddress = pictureIpaddress == null ? null : pictureIpaddress.trim();
    }

    public String getPicturePort() {
        return picturePort;
    }

    public void setPicturePort(String picturePort) {
        this.picturePort = picturePort == null ? null : picturePort.trim();
    }

    public String getPictureFolder() {
        return pictureFolder;
    }

    public void setPictureFolder(String pictureFolder) {
        this.pictureFolder = pictureFolder == null ? null : pictureFolder.trim();
    }

    public String getPicturePath() {
        return picturePath;
    }

    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath == null ? null : picturePath.trim();
    }

    public String getPictureName() {
        return pictureName;
    }

    public void setPictureName(String pictureName) {
        this.pictureName = pictureName == null ? null : pictureName.trim();
    }

    public Date getPictureCreatetime() {
        return pictureCreatetime;
    }

    public void setPictureCreatetime(Date pictureCreatetime) {
        this.pictureCreatetime = pictureCreatetime;
    }

    public Date getPictureModifytime() {
        return pictureModifytime;
    }

    public void setPictureModifytime(Date pictureModifytime) {
        this.pictureModifytime = pictureModifytime;
    }

    public Integer getPictureVersion() {
        return pictureVersion;
    }

    public void setPictureVersion(Integer pictureVersion) {
        this.pictureVersion = pictureVersion;
    }

    public Byte getPictureStatus() {
        return pictureStatus;
    }

    public void setPictureStatus(Byte pictureStatus) {
        this.pictureStatus = pictureStatus;
    }

    /**
	 * @return picturePurpose
	 */
	
	public Byte getPicturePurpose() {
		return picturePurpose;
	}

	/**
	 * @param picturePurpose the picturePurpose to set
	 */
	
	public void setPicturePurpose(Byte picturePurpose) {
		this.picturePurpose = picturePurpose;
	}

	public Byte getPictureDeleted() {
        return pictureDeleted;
    }

    public void setPictureDeleted(Byte pictureDeleted) {
        this.pictureDeleted = pictureDeleted;
    }

    public Long getPictureBelongid() {
        return pictureBelongid;
    }

    public void setPictureBelongid(Long pictureBelongid) {
        this.pictureBelongid = pictureBelongid;
    }

    public String getPictureRedirecturl() {
        return pictureRedirecturl;
    }

    public void setPictureRedirecturl(String pictureRedirecturl) {
        this.pictureRedirecturl = pictureRedirecturl == null ? null : pictureRedirecturl.trim();
    }
	
	public String getPictureSort() {
		return pictureSort;
	}

	public void setPictureSort(String pictureSort) {
		this.pictureSort = pictureSort;
	}
}