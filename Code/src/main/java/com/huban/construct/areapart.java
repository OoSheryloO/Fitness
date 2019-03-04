package com.huban.construct;


public class areapart {
	
	private Long regionsId;
	
	private Long regionsParentId;
	
	private String regions;
	
	private String code;
	
	public Long getRegionsId() {
		return regionsId;
	}

	public void setRegionsId(Long regionsId) {
		this.regionsId = regionsId;
	}

	public Long getRegionsParentId() {
		return regionsParentId;
	}

	public void setRegionsParentId(Long regionsParentId) {
		this.regionsParentId = regionsParentId;
	}

	public String getRegions() {
		return regions;
	}

	public void setRegions(String regions) {
		this.regions = regions;
	}

	/**
	 * @return code
	 */
	
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	
	public void setCode(String code) {
		this.code = code;
	}
	
}
