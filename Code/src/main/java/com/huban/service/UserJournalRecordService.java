package com.huban.service;

import java.util.List;
import java.util.Map;

import com.huban.pojo.UserJournal;

public interface UserJournalRecordService {
    
	public int AddNewMessage(Map<String, Object> map);
	
	public List<UserJournal> SearchPastRecord(Map<String, Object> map);
	
}
