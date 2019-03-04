package com.huban.service;

import java.util.List;
import java.util.Map;

import com.huban.construct.JournalModel;
import com.huban.pojo.UserJournal;

public interface UserJournalService {
    
	public int AddNewMessage(UserJournal record);
	
	public int QueryNowCount(Map<String, Object> map);
	
	public List<UserJournal> QueryList(Map<String, Object> map);
	
	public List<JournalModel> QueryLstJournalModel(Map<String, Object> map);
	
	public void UpdateMessage(UserJournal record);
}
