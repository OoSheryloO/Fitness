package com.huban.service.imp;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.huban.dao.AuctionRecordsMapper;
import com.huban.dao.DevicesMapper;
import com.huban.pojo.AuctionRecords;
import com.huban.pojo.Devices;
import com.huban.service.AuctionRecordsService;
import com.huban.service.DevicesService;

@Service("auctionService")
public class AuctionRecordsServiceImpl implements AuctionRecordsService{
     @Resource
     private AuctionRecordsMapper mapper;
	/* (non - Javadoc)
	* @param records
	* @return
	* @see com.huban.service.AuctionRecordsService#IncreaseMessage(com.huban.pojo.AuctionRecords)
	*/
	@Override
	public int IncreaseMessage(AuctionRecords record) {
		// TODO Auto-generated method stub
		return mapper.IncreaseMessage(record);
	}
	
	/* (non - Javadoc)
	* @param map
	* @return
	* @see com.huban.service.AuctionRecordsService#SearchMessage(java.util.Map)
	*/
	@Override
	public AuctionRecords SearchMessage(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return mapper.SearchMessage(map);
	}

	/* (non - Javadoc)
	* @param map
	* @return
	* @see com.huban.service.AuctionRecordsService#LstSearchMessage(java.util.Map)
	*/
	@Override
	public List<AuctionRecords> LstSearchMessage(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return mapper.LstSearchMessage(map);
	}

	 

}
