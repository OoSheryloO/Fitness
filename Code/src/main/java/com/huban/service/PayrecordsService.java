package com.huban.service;

import java.util.List;
import java.util.Map;

import com.huban.pojo.Payrecords;

public interface PayrecordsService {
	
	public List<Payrecords> payMessage(Map<String, Object> map);
	
	public int addPayMessage(Payrecords payrecords);
	
	public int payVideo(Map<String, Object> map);
	
	public int embodyquery(Map<String, Object> map);
	
}
