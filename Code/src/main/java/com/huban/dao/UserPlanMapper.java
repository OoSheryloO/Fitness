package com.huban.dao;

import java.util.List;
import java.util.Map;

import com.huban.pojo.UserPlan;

public interface UserPlanMapper {
    int deleteByPrimaryKey(Long planId);

    int insert(UserPlan record);

    int insertSelective(UserPlan record);

    UserPlan selectByPrimaryKey(Long planId);

    int updateByPrimaryKeySelective(UserPlan record);

    int updateByPrimaryKeyWithBLOBs(UserPlan record);

    int updateByPrimaryKey(UserPlan record);
    
    /* by Sheryl */
    int AddNewMessage(UserPlan record);
    
    List<UserPlan> QueryNowList(Map<String, Object> map);
}