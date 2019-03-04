package com.huban.dao;

import java.util.List;
import java.util.Map;

import com.huban.pojo.Bookpart;

public interface BookpartMapper {
    int deleteByPrimaryKey(Long bookpartId);

    int insert(Bookpart record);

    int insertSelective(Bookpart record);

    Bookpart selectByPrimaryKey(Long bookpartId);

    int updateByPrimaryKeySelective(Bookpart record);

    int updateByPrimaryKey(Bookpart record);
    
    List<Bookpart> queryList(Map<String,Object> map);
}