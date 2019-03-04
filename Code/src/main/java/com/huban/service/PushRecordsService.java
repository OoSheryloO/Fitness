package com.huban.service;

import java.util.List;
import java.util.Map;

import com.huban.pojo.PushRecords;

/**
 * @ClassName: PushRecordsService
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author Sheryl
 * @date 2017年10月30日
 */
public interface PushRecordsService {
	public List<PushRecords> LstQueryByCondition(Map<String, Object> map);
	
	public PushRecords QueryByModel(PushRecords record);
	
	public int ChangeMessage(PushRecords record);
	
	public int AddNewMessage(PushRecords record);
	
	public int QueryCountByCondition(Map<String, Object> map);
	
}
