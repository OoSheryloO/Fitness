package com.huban.dao;

import java.util.List;
import java.util.Map;

import com.huban.construct.AreaRetrunModel;
import com.huban.pojo.Area;

public interface AreaMapper {
//    int deleteByPrimaryKey(Integer id);
//
//    int insert(Area record);
//
//    int insertSelective(Area record);
//
//    Area selectByPrimaryKey(Integer id);
//
//    int updateByPrimaryKeySelective(Area record);
//
//    int updateByPrimaryKey(Area record);
    
    
    List<AreaRetrunModel> QuerySomeMessage(Map<String, Object> map);
}