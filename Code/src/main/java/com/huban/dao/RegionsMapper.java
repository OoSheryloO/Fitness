package com.huban.dao;

import java.util.List;
import java.util.Map;

import com.huban.pojo.Regions;

public interface RegionsMapper {
    int deleteByPrimaryKey(Long regionId);

    int insert(Regions record);

    int insertSelective(Regions record);

    Regions selectByPrimaryKey(Long regionId);

    int updateByPrimaryKeySelective(Regions record);

    int updateByPrimaryKey(Regions record);
    
    List<Regions> firstLevel(Map<String, Object> map);
    
    List<Regions> secondLevel(Long regionId);
    
    List<Regions> thirdLevel(Long regionId);
}