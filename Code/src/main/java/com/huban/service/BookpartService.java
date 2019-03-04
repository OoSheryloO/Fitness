/**
 * 
 */
package com.huban.service;

import java.util.List;
import java.util.Map;

import com.huban.pojo.Bookpart;

/**
 * @author GeJiangbo
 * @date 2017年5月26日
 */
public interface BookpartService {
 
	public List<Bookpart> queryList(Map<String,Object> map); 
}
