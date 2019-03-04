/**
 * 
 */
package com.huban.service;

import java.util.List;
import java.util.Map;

import com.huban.pojo.Activity;

/**
 * @author GeJiangbo
 * @date 2017年5月23日
 */
public interface ActivityService {
     
	   public List<Activity> queryList(Map<String, Object> map);
	   
	   public Activity selectActivity(Map<String, Object> map);
	   
	   public int updateNum(Map<String,Object> map);
	   
	   public Activity ApplyLimit(Long activityId);
	   
	   public int modifyJoinCount(Long activityId);
	   public int ReduceJoinCount(Long activityId);
	   
	   public Activity selectbyid(Long activityId);
	   
	   public String selectapplymoney(Long activityId);
	   
	   public int AddNewMessage(Activity record); 
		
}
