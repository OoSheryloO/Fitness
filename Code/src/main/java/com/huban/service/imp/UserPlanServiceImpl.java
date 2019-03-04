package com.huban.service.imp;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.huban.dao.UserJournalMapper;
import com.huban.dao.UserPlanMapper;
import com.huban.pojo.UserJournal;
import com.huban.pojo.UserPlan;
import com.huban.service.UserJournalService;
import com.huban.service.UserPlanService;

@Service("userPlanService")
public class UserPlanServiceImpl implements UserPlanService{
    @Resource
    private UserPlanMapper mapper;

	@Override
	public int AddNewMessage(UserPlan record) {
		// TODO Auto-generated method stub
		return mapper.AddNewMessage(record);
	}

	@Override
	public List<UserPlan> QueryNowList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return mapper.QueryNowList(map);
	}

	
}
