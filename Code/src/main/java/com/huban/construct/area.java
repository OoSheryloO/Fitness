package com.huban.construct;

import java.util.List;

public class area {
	
	private Long regionsId;
	
	private Long regionsParentId;
	
	private String regions;

	private List<area> areas;
	
	public List<area> getAreas() {
		return areas;
	}

	public void setAreas(List<area> areas) {
		this.areas = areas;
	}

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
	
	
}
