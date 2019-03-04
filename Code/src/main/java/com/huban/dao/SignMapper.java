package com.huban.dao;

import java.util.List;
import java.util.Map;

import com.huban.pojo.Sign;

public interface SignMapper {
    int deleteByPrimaryKey(String signId);

    int insert(Sign record);

    int insertSelective(Sign record);

    Sign selectByPrimaryKey(String signId);

    int updateByPrimaryKeySelective(Sign record);

    int updateByPrimaryKey(Sign record);
    
    int addSign(Map<String, Object> map);
    
    List<Sign> querySign(Map<String , Object> map);
    
    int weekSign(Long userId);
    
    int querynow(Map<String, Object> map);
    
    List<Sign> queryMouthSign(Map<String, Object> map);
}