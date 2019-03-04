package com.huban.dao;

import java.util.List;
import java.util.Map;

import com.huban.pojo.AuctionRecords;

public interface AuctionRecordsMapper {
    int deleteByPrimaryKey(Long auctionId);

    int insert(AuctionRecords record);

    int insertSelective(AuctionRecords record);

    AuctionRecords selectByPrimaryKey(Long auctionId);

    int updateByPrimaryKeySelective(AuctionRecords record);

    int updateByPrimaryKey(AuctionRecords record);
    
    /* V2 by Sheryl */
    int IncreaseMessage(AuctionRecords record);
    
    AuctionRecords SearchMessage(Map<String, Object> map);
    
    List<AuctionRecords> LstSearchMessage(Map<String, Object> map);
}