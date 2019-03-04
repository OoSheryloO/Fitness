package com.huban.service;

import java.util.List;
import java.util.Map;

import com.huban.pojo.Sign;

public interface SignService {

	public int addSign(Sign sign);
	
	public List<Sign> querySign(Map<String, Object> map);
	
	public int weekSign(Long userId);
	
	public int querynow(Map<String, Object> map);
	
	public List<Sign> queryMouthSign(Map<String, Object> map);
}
