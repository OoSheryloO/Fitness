package com.huban.dao;

import java.util.List;
import java.util.Map;

import com.huban.pojo.Lesson;

public interface LessonMapper {
    int deleteByPrimaryKey(Short lid);

    int insert(Lesson record);

    int insertSelective(Lesson record);

    Lesson selectByPrimaryKey(Short lid);

    int updateByPrimaryKeySelective(Lesson record);

    int updateByPrimaryKey(Lesson record);
    
    /* V2 by Sheryl */
    
    List<Lesson> SearchMessageByCondition(Map<String, Object> map);
}