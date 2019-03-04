package com.huban.service.imp;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.huban.dao.PushRecordsMapper;
import com.huban.pojo.PushRecords;
import com.huban.service.PushRecordsService;

@Service("pushRecordsService")
public class PushRecordsServiceImpl implements PushRecordsService{
	@Resource
	private PushRecordsMapper mapper;

	/* (non - Javadoc)
	* @param map
	* @return
	* @see com.huban.service.PushRecordsService#QueryByCondition(java.util.Map)
	*/
	@Override
	public List<PushRecords> LstQueryByCondition(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return mapper.LstQueryByCondition(map);
	}

	/* (non - Javadoc)
	* @param record
	* @return
	* @see com.huban.service.PushRecordsService#QueryByModel(com.huban.pojo.PushRecords)
	*/
	@Override
	public PushRecords QueryByModel(PushRecords record) {
		// TODO Auto-generated method stub
		return mapper.QueryByModel(record);
	}

	/* (non - Javadoc)
	* @param record
	* @return
	* @see com.huban.service.PushRecordsService#ChangeMessage(com.huban.pojo.PushRecords)
	*/
	@Override
	public int ChangeMessage(PushRecords record) {
		// TODO Auto-generated method stub
		return mapper.ChangeMessage(record);
	}

	/* (non - Javadoc)
	* @param record
	* @return
	* @see com.huban.service.PushRecordsService#AddNewMessage(com.huban.pojo.PushRecords)
	*/
	@Override
	public int AddNewMessage(PushRecords record) {
		// TODO Auto-generated method stub
		return mapper.AddNewMessage(record);
	}

	/* (non - Javadoc)
	* @param map
	* @return
	* @see com.huban.service.PushRecordsService#QueryCountForUnRead(java.util.Map)
	*/
	@Override
	public int QueryCountByCondition(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return mapper.QueryCountByCondition(map);
	}


}
