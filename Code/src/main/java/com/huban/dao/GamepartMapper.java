package com.huban.dao;

import java.util.List;
import java.util.Map;

import com.huban.pojo.Gamepart;

public interface GamepartMapper {
    int deleteByPrimaryKey(Long gameLinkid);

    int insert(Gamepart record);

    int insertSelective(Gamepart record);

    Gamepart selectByPrimaryKey(Long gameLinkid);

    int updateByPrimaryKeySelective(Gamepart record);

    int updateByPrimaryKey(Gamepart record);
    
    List<Gamepart> queryList(Map<String,Object> map);
    
    Gamepart selectOne(Long gameId);
   
}