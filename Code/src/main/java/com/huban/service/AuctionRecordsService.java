package com.huban.service;

import java.util.List;
import java.util.Map;

import com.huban.pojo.AuctionRecords;

public interface AuctionRecordsService {
	
	public int IncreaseMessage(AuctionRecords record);
	
	public AuctionRecords SearchMessage(Map<String, Object> map);
	
	public List<AuctionRecords> LstSearchMessage(Map<String, Object> map);
	
}
