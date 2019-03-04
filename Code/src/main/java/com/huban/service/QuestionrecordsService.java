/**
 * 
 */
package com.huban.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author GeJiangbo
 * @date 2017年5月26日
 */
public interface QuestionrecordsService {
	
	public List<Integer> SelectPass(Long userId);
    
    public List<Integer> SelectGame(Long userId);
	
    public Date SelectTime(Map<String, Object> map);
	
}
