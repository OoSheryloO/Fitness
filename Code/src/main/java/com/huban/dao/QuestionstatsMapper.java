package com.huban.dao;

import com.huban.pojo.Questionstats;

public interface QuestionstatsMapper {
    int deleteByPrimaryKey(Long questionstatId);

    int insert(Questionstats record);

    int insertSelective(Questionstats record);

    Questionstats selectByPrimaryKey(Long questionstatId);

    int updateByPrimaryKeySelective(Questionstats record);

    int updateByPrimaryKey(Questionstats record);
}