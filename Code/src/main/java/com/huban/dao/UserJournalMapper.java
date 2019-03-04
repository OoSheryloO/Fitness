package com.huban.dao;

import java.util.List;
import java.util.Map;

import com.huban.construct.JournalModel;
import com.huban.pojo.UserJournal;

public interface UserJournalMapper {

    int updateByPrimaryKey(UserJournal record);
    
    int AddNewMessage(UserJournal record);
    
    int QueryNowCount(Map<String, Object> map);
    
    List<UserJournal> QueryList(Map<String, Object> map);
    
    List<JournalModel> QueryLstJournalModel(Map<String, Object> map);
    
    void UpdateMessage(UserJournal record);
    
}