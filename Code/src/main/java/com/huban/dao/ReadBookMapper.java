package com.huban.dao;

import java.util.List;
import java.util.Map;

import com.huban.pojo.ReadBook;

public interface ReadBookMapper {
    int deleteByPrimaryKey(Long bookId);

    int insert(ReadBook record);

    int insertSelective(ReadBook record);

    ReadBook selectByPrimaryKey(Long bookId);

    int updateByPrimaryKeySelective(ReadBook record);

    int updateByPrimaryKeyWithBLOBs(ReadBook record);

    int updateByPrimaryKey(ReadBook record);
    
    List<ReadBook> SearchLstByCondition(Map<String, Object> map);
}