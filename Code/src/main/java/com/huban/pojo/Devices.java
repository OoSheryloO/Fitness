package com.huban.pojo;

import java.io.Serializable;
import java.util.Date;

import com.huban.util.IdWorker;
@SuppressWarnings("serial")
public class Devices implements Serializable{
	
	public static final String sDeviceClass = "Device";
	
    private Long deviceId;

    private Long deviceUserid;

    private Long deviceTypeid;

    private String deviceUdid;

    private String deviceName;

    private String deviceModel;

    private String deviceSystem;

    private Date deviceCreatetime;

    private Date deviceModifytime;

    private Integer deviceVersion;

    private Byte deviceStatus;

    private Byte deviceDeleted;
    
    public Devices() {
        super();
        this.deviceId = IdWorker.CreateNewID();
    }
    
    public Devices(long DeviceUserid) {
        super();
        this.deviceUserid = DeviceUserid;
    }

    public Long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }

    public Long getDeviceUserid() {
        return deviceUserid;
    }

    public void setDeviceUserid(Long deviceUserid) {
        this.deviceUserid = deviceUserid;
    }

    public Long getDeviceTypeid() {
        return deviceTypeid;
    }

    public void setDeviceTypeid(Long deviceTypeid) {
        this.deviceTypeid = deviceTypeid;
    }

    public String getDeviceUdid() {
        return deviceUdid;
    }

    public void setDeviceUdid(String deviceUdid) {
        this.deviceUdid = deviceUdid == null ? null : deviceUdid.trim();
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName == null ? null : deviceName.trim();
    }

    public String getDeviceModel() {
        return deviceModel;
    }

    public void setDeviceModel(String deviceModel) {
        this.deviceModel = deviceModel == null ? null : deviceModel.trim();
    }

    public String getDeviceSystem() {
        return deviceSystem;
    }

    public void setDeviceSystem(String deviceSystem) {
        this.deviceSystem = deviceSystem == null ? null : deviceSystem.trim();
    }

    public Date getDeviceCreatetime() {
        return deviceCreatetime;
    }

    public void setDeviceCreatetime(Date deviceCreatetime) {
        this.deviceCreatetime = deviceCreatetime;
    }

    public Date getDeviceModifytime() {
        return deviceModifytime;
    }

    public void setDeviceModifytime(Date deviceModifytime) {
        this.deviceModifytime = deviceModifytime;
    }

    public Integer getDeviceVersion() {
        return deviceVersion;
    }

    public void setDeviceVersion(Integer deviceVersion) {
        this.deviceVersion = deviceVersion;
    }

    public Byte getDeviceStatus() {
        return deviceStatus;
    }

    public void setDeviceStatus(Byte deviceStatus) {
        this.deviceStatus = deviceStatus;
    }

    public Byte getDeviceDeleted() {
        return deviceDeleted;
    }

    public void setDeviceDeleted(Byte deviceDeleted) {
        this.deviceDeleted = deviceDeleted;
    }
}