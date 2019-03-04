package com.huban.pojo;
@SuppressWarnings("serial")
public class Baseinfo implements java.io.Serializable{
    private Long baseinfoId;

    private String baseinfoName;

    private String baseinfoMemo;

    private Long baseinfoParentid;

    private Integer baseinfoStatus;

    public Long getBaseinfoId() {
        return baseinfoId;
    }

    public void setBaseinfoId(Long baseinfoId) {
        this.baseinfoId = baseinfoId;
    }

    public String getBaseinfoName() {
        return baseinfoName;
    }

    public void setBaseinfoName(String baseinfoName) {
        this.baseinfoName = baseinfoName == null ? null : baseinfoName.trim();
    }

    public String getBaseinfoMemo() {
        return baseinfoMemo;
    }

    public void setBaseinfoMemo(String baseinfoMemo) {
        this.baseinfoMemo = baseinfoMemo == null ? null : baseinfoMemo.trim();
    }

    public Long getBaseinfoParentid() {
        return baseinfoParentid;
    }

    public void setBaseinfoParentid(Long baseinfoParentid) {
        this.baseinfoParentid = baseinfoParentid;
    }

    public Integer getBaseinfoStatus() {
        return baseinfoStatus;
    }

    public void setBaseinfoStatus(Integer baseinfoStatus) {
        this.baseinfoStatus = baseinfoStatus;
    }
}