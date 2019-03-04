package com.huban.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.huban.pojo.Questionrecords;

public interface QuestionrecordsMapper {

    int deleteByPrimaryKey(Long questionrecordId);

    int insert(Questionrecords record);

    int insertSelective(Questionrecords record);

    Questionrecords selectByPrimaryKey(Long questionrecordId);

    int updateByPrimaryKeySelective(Questionrecords record);

    int updateByPrimaryKey(Questionrecords record);
    
    List<Integer> SelectPass(Long userId);
    
    List<Integer> SelectGame(Long userId);
    
    Date SelectTime(Map<String, Object> map);
    
}