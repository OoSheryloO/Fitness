package com.huban.pojo;

import java.io.Serializable;
import java.util.Date;
@SuppressWarnings("serial")
public class Regions implements Serializable{
    private Long regionId;

    private Long regionParentid;

    private String regionNo;

    private String regionName;

    private String regionEnglish;

    private String regionShortname;

    private Byte regionLevel;

    private Integer regionOrder;

    private Date regionCreatetime;

    private Date regionModifytime;

    private Integer regionVersion;

    private Byte regionStatus;

    private Byte regionDeleted;

    public Long getRegionId() {
        return regionId;
    }

    public void setRegionId(Long regionId) {
        this.regionId = regionId;
    }

    public Long getRegionParentid() {
        return regionParentid;
    }

    public void setRegionParentid(Long regionParentid) {
        this.regionParentid = regionParentid;
    }

    public String getRegionNo() {
        return regionNo;
    }

    public void setRegionNo(String regionNo) {
        this.regionNo = regionNo == null ? null : regionNo.trim();
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName == null ? null : regionName.trim();
    }

    public String getRegionEnglish() {
        return regionEnglish;
    }

    public void setRegionEnglish(String regionEnglish) {
        this.regionEnglish = regionEnglish == null ? null : regionEnglish.trim();
    }

    public String getRegionShortname() {
        return regionShortname;
    }

    public void setRegionShortname(String regionShortname) {
        this.regionShortname = regionShortname == null ? null : regionShortname.trim();
    }

    public Byte getRegionLevel() {
        return regionLevel;
    }

    public void setRegionLevel(Byte regionLevel) {
        this.regionLevel = regionLevel;
    }

    public Integer getRegionOrder() {
        return regionOrder;
    }

    public void setRegionOrder(Integer regionOrder) {
        this.regionOrder = regionOrder;
    }

    public Date getRegionCreatetime() {
        return regionCreatetime;
    }

    public void setRegionCreatetime(Date regionCreatetime) {
        this.regionCreatetime = regionCreatetime;
    }

    public Date getRegionModifytime() {
        return regionModifytime;
    }

    public void setRegionModifytime(Date regionModifytime) {
        this.regionModifytime = regionModifytime;
    }

    public Integer getRegionVersion() {
        return regionVersion;
    }

    public void setRegionVersion(Integer regionVersion) {
        this.regionVersion = regionVersion;
    }

    public Byte getRegionStatus() {
        return regionStatus;
    }

    public void setRegionStatus(Byte regionStatus) {
        this.regionStatus = regionStatus;
    }

    public Byte getRegionDeleted() {
        return regionDeleted;
    }

    public void setRegionDeleted(Byte regionDeleted) {
        this.regionDeleted = regionDeleted;
    }
}