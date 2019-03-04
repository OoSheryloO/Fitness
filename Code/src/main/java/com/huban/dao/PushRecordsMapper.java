package com.huban.dao;

import java.util.List;
import java.util.Map;

import com.huban.pojo.PushRecords;

public interface PushRecordsMapper {
    int deleteByPrimaryKey(Long pushrecordId);

    int insert(PushRecords record);

    int insertSelective(PushRecords record);

    PushRecords selectByPrimaryKey(Long pushrecordId);

    int updateByPrimaryKeySelective(PushRecords record);

    int updateByPrimaryKey(PushRecords record);
    
    /* by Sheryl */
    List<PushRecords> LstQueryByCondition(Map<String, Object> map);
    
    PushRecords QueryByModel(PushRecords record);
    
    int ChangeMessage(PushRecords record);
    
    int AddNewMessage(PushRecords record);
    
    int QueryCountByCondition(Map<String, Object> map);
}