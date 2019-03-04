/**
 * 
 */
package com.huban.service;

import java.util.List;
import java.util.Map;

import com.huban.pojo.Goods;

public interface GoodsService {

	public int IncreaseMessaga(Goods record);
	
	public List<Goods> LstSearchByCondition(Map<String, Object> map);
	
	public Goods SearchByCondition(Map<String, Object> map);
	
	public int ModifyMessage(Goods record);
		
}
