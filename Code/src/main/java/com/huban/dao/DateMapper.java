package com.huban.dao;

import java.util.List;
import java.util.Map;

import com.huban.pojo.Date;

public interface DateMapper {
    int deleteByPrimaryKey(Long dateId);

    int insert(Date record);

    int insertSelective(Date record);

    Date selectByPrimaryKey(Long dateId);

    int updateByPrimaryKeySelective(Date record);

    int updateByPrimaryKey(Date record);
    
    int addmessage(Date date);
    
    int readbillcount(Map<String, Object> map);
    
    List<Date> readbill(Map<String, Object> map);
    
    List<Date> readmore(Map<String, Object> map);
    
    List<Date> allread(Map<String, Object> map);
    
    List<Date> queryList(Map<String, Object> map);
    
    int querycount(Map<String, Object> map);
    
    int QueryGameMassageCount(Map<String, Object> map);
    
    int UpdateGamePass(Map<String, Object> map);
    
    List<Long> ReadBillCountTop();
    
    List<Long> ranklist(Map<String, Object> map);
    
    List<Integer> ranklistcount(Map<String, Object> map);
    
    int QueryCountById(Long userId);
    
}