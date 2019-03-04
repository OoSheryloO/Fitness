package com.huban.dao;

import java.util.List;
import java.util.Map;

import com.huban.pojo.Onlines;

public interface OnlinesMapper {
    int deleteByPrimaryKey(Long onlineId);

    int insert(Onlines record);

    int insertSelective(Onlines record);

    Onlines selectByPrimaryKey(Long onlineId);
    
    List<Onlines> queryList(Map<String, Object> map);

    int updateOnline(Onlines record);

    int updateByPrimaryKey(Onlines record);
}