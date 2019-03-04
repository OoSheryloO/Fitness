package com.huban.dao;

import java.util.List;
import java.util.Map;

import com.huban.pojo.UserJournal;

public interface UserJournalRecordMapper {

    int AddNewMessage(Map<String, Object> map);
    
    List<UserJournal> SearchPastRecord(Map<String, Object> map);
    
}