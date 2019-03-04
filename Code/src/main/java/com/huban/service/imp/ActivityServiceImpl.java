/**
 * 
 */
package com.huban.service.imp;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.huban.dao.ActivityMapper;
import com.huban.pojo.Activity;
import com.huban.service.ActivityService;

/**
 * @author GeJiangbo
 * @date 2017年5月23日
 */
@Service("activityService")
public class ActivityServiceImpl implements ActivityService{
     @Resource
     private ActivityMapper mapper;
	/* (non-Javadoc)
	 * @see com.huban.service.ActivityService#queryList(java.util.Map)
	 */
	@Override
	public List<Activity> queryList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return mapper.queryList(map);
	}
	/* (non-Javadoc)
	 * @see com.huban.service.ActivityService#selectActivity(long)
	 */
	@Override
	public Activity selectActivity(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return mapper.selectByPrimaryKey(map);
	}
	/**查看和分享+1
	 * @author zhangchao
	 */
	@Override
	public int updateNum(Map<String, Object> map) {
		// TODO Auto-generated method stub
		 return mapper.updateNum(map);
	}
	@Override
	public Activity ApplyLimit(Long activityId) {
		// TODO Auto-generated method stub
		return mapper.ApplyLimit(activityId);
	}
	@Override
	public int modifyJoinCount(Long activityId) {
		// TODO Auto-generated method stub
		return mapper.modifyJoinCount(activityId);
	}
	
	/* (non - Javadoc)
	* @param activityId
	* @return
	* @see com.huban.service.ActivityService#ReduceJoinCount(java.lang.Long)
	*/
	@Override
	public int ReduceJoinCount(Long activityId) {
		// TODO Auto-generated method stub
		return mapper.ReduceJoinCount(activityId);
	}
	
	@Override
	public Activity selectbyid(Long activityId) {
		// TODO Auto-generated method stub
		return mapper.selectbyid(activityId);
	}
	@Override
	public String selectapplymoney(Long activityId) {
		// TODO Auto-generated method stub
		return mapper.selectapplymoney(activityId);
	}
	@Override
	public int AddNewMessage(Activity record) {
		// TODO Auto-generated method stub
		return mapper.AddNewMessage(record);
	}
	
}
