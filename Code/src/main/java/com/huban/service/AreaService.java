/**
 * 
 */
package com.huban.service;

import java.util.List;
import java.util.Map;

import com.huban.construct.AreaRetrunModel;

/**
 * <p>Title: AreaService.java</p>
 * <p>Description: </p>
 * <p>Company: </p>
 * @author Sheryl
 * @created 2017年9月22日 上午9:26:18
 */
public interface AreaService {
	
//	public List<String> QueryNum(String province);
	
	public List<AreaRetrunModel> QuerySomeMessage(Map<String, Object> map);
	
}
