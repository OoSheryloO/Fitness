package com.huban.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
@SuppressWarnings("serial")
public class Integration implements Serializable{
    private Long integrationId;

    private Date integrationCreatetime;

    private Integer integrationStatus;

    private BigDecimal integrationNum;

    private Long integrationStudentid;

    private Long integrationSrcid;

    private Integer integrationType;

    private String integrationReason;

    public Long getIntegrationId() {
        return integrationId;
    }

    public void setIntegrationId(Long integrationId) {
        this.integrationId = integrationId;
    }

    public Date getIntegrationCreatetime() {
        return integrationCreatetime;
    }

    public void setIntegrationCreatetime(Date integrationCreatetime) {
        this.integrationCreatetime = integrationCreatetime;
    }

    public Integer getIntegrationStatus() {
        return integrationStatus;
    }

    public void setIntegrationStatus(Integer integrationStatus) {
        this.integrationStatus = integrationStatus;
    }

    public BigDecimal getIntegrationNum() {
		return integrationNum;
	}

	public void setIntegrationNum(BigDecimal integrationNum) {
		this.integrationNum = integrationNum;
	}

	public Long getIntegrationStudentid() {
        return integrationStudentid;
    }

    public void setIntegrationStudentid(Long integrationStudentid) {
        this.integrationStudentid = integrationStudentid;
    }

    public Long getIntegrationSrcid() {
        return integrationSrcid;
    }

    public void setIntegrationSrcid(Long integrationSrcid) {
        this.integrationSrcid = integrationSrcid;
    }

    public Integer getIntegrationType() {
        return integrationType;
    }

    public void setIntegrationType(Integer integrationType) {
        this.integrationType = integrationType;
    }

    public String getIntegrationReason() {
        return integrationReason;
    }

    public void setIntegrationReason(String integrationReason) {
        this.integrationReason = integrationReason == null ? null : integrationReason.trim();
    }
}