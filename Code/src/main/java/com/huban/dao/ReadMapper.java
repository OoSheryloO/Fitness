package com.huban.dao;

import com.huban.pojo.Read;

public interface ReadMapper {
    int deleteByPrimaryKey(Long readId);

    int insert(Read record);

    int insertSelective(Read record);

    Read selectByPrimaryKey(Long readId);

    int updateByPrimaryKeySelective(Read record);

    int updateByPrimaryKey(Read record);
}