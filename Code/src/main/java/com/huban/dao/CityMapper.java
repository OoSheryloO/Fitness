package com.huban.dao;

import java.util.List;
import java.util.Map;

import com.huban.construct.areapart;
import com.huban.pojo.City;

public interface CityMapper {
    int deleteByPrimaryKey(Short id);

    int insert(City record);

    int insertSelective(City record);

    City selectByPrimaryKey(Short id);

    int updateByPrimaryKeySelective(City record);

    int updateByPrimaryKey(City record);
    
    /* V2 by Sheryl */
    List<areapart> QuerySomeMessage(Map<String, Object> map);
    
}