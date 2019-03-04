package com.huban.dao;

import java.util.List;
import java.util.Map;

import com.huban.construct.areapart;
import com.huban.pojo.School;

public interface SchoolMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(School record);

    int insertSelective(School record);

    School selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(School record);

    int updateByPrimaryKey(School record);
    
    /* V2 by Sheryl */
    List<areapart> QuerySomeMessage(Map<String, Object> map);
}