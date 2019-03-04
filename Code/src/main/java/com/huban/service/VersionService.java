/**
 * 
 */
package com.huban.service;

import java.util.Map;

import com.huban.pojo.Version;

public interface VersionService {
	
	public Version MAX(Map<String, Object> map);
	
	public Integer querypublish(Map<String, Object> map);
	
}
