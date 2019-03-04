/**
 * 
 */
package com.huban.service;

import java.util.List;
import java.util.Map;

import com.huban.pojo.Gamepart;

/**
 * @author GeJiangbo
 * @date 2017年5月26日
 */
public interface GamepartService {
   
	public List<Gamepart> queryList(Map<String,Object> map); 
	
	public Gamepart selectOne(Long gameId);
	
}
