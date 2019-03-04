package com.huban.service.imp;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.huban.dao.UserJournalRecordMapper;
import com.huban.pojo.UserJournal;
import com.huban.pojo.UserJournalRecord;
import com.huban.service.UserJournalRecordService;

@Service("userJournalRecordService")
public class UserJournalRecordServiceImpl implements UserJournalRecordService{
    @Resource
    private UserJournalRecordMapper mapper;

	@Override
	public int AddNewMessage(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return mapper.AddNewMessage(map);
	}

	/* (non - Javadoc)
	* @param map
	* @return
	* @see com.huban.service.UserJournalRecordService#SearchPastRecord(java.util.Map)
	*/
	@Override
	public List<UserJournal> SearchPastRecord(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return mapper.SearchPastRecord(map);
	}
}
