package com.huban.service.imp;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.huban.construct.JournalModel;
import com.huban.dao.UserJournalMapper;
import com.huban.pojo.UserJournal;
import com.huban.service.UserJournalService;

@Service("userJournalService")
public class UserJournalServiceImpl implements UserJournalService{
    @Resource
    private UserJournalMapper mapper;

	@Override
	public int AddNewMessage(UserJournal record) {
		// TODO Auto-generated method stub
		return mapper.AddNewMessage(record);
	}

	@Override
	public int QueryNowCount(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return mapper.QueryNowCount(map);
	}

	@Override
	public List<UserJournal> QueryList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return mapper.QueryList(map);
	}

	@Override
	public List<JournalModel> QueryLstJournalModel(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return mapper.QueryLstJournalModel(map);
	}

	@Override
	public void UpdateMessage(UserJournal record) {
		// TODO Auto-generated method stub
		mapper.UpdateMessage(record);
	}

   
}
