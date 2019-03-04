package com.huban.dao;

import java.util.List;
import java.util.Map;

import com.huban.pojo.Activity;

public interface ActivityMapper {
    int deleteByPrimaryKey(Long activityId);

    int insert(Activity record);

    int insertSelective(Activity record);
    
    int updateByPrimaryKeySelective(Activity record);

    int updateByPrimaryKeyWithBLOBs(Activity record);

    int updateByPrimaryKey(Activity record);
    
    
    Activity selectByPrimaryKey(Map<String, Object> map);
    
    List<Activity> queryList(Map<String, Object> map);
    
    int updateNum(Map<String, Object> map);
    
    Activity ApplyLimit(Long activityId);
    
    int modifyJoinCount(Long activityId);
    int ReduceJoinCount(Long activityId);
    
    Activity selectbyid(Long activityId);
    
    String selectapplymoney(Long activityId);
    
    int AddNewMessage(Activity record);
    
    int ChanceMessage(Activity record);
}