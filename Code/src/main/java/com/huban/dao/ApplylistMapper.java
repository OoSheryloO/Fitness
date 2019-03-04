package com.huban.dao;

import com.huban.pojo.Applylist;

public interface ApplylistMapper {
    int deleteByPrimaryKey(Long applylistId);

    int insert(Applylist record);

    int insertSelective(Applylist record);

    Applylist selectByPrimaryKey(Long applylistId);

    int updateByPrimaryKeySelective(Applylist record);

    int updateByPrimaryKey(Applylist record);
}