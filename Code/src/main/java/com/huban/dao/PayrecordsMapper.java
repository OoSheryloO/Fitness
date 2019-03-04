package com.huban.dao;

import java.util.List;
import java.util.Map;

import com.huban.pojo.Payrecords;

public interface PayrecordsMapper {
    int deleteByPrimaryKey(Long payrecordId);

    int insert(Payrecords record);

    int insertSelective(Payrecords record);

    Payrecords selectByPrimaryKey(Long payrecordId);

    int updateByPrimaryKeySelective(Payrecords record);

    int updateByPrimaryKey(Payrecords record);
    
    List<Payrecords> payMessage(Map<String, Object> map);
    
    int addPayMessage(Payrecords payrecords);
    
    int payVideo(Map<String, Object> map);
    
    int embodyquery(Map<String, Object> map);
}