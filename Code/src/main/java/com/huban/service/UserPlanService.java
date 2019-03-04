package com.huban.service;

import java.util.List;
import java.util.Map;

import com.huban.pojo.UserJournal;
import com.huban.pojo.UserPlan;

public interface UserPlanService {
    
	public int AddNewMessage(UserPlan record);
	
	public List<UserPlan> QueryNowList(Map<String, Object> map);

}
