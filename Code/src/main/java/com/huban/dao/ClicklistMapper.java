package com.huban.dao;

import com.huban.pojo.Clicklist;

public interface ClicklistMapper {
    int deleteByPrimaryKey(Long clicklistId);

    int insert(Clicklist record);

    int insertSelective(Clicklist record);

    Clicklist selectByPrimaryKey(Long clicklistId);

    int updateByPrimaryKeySelective(Clicklist record);

    int updateByPrimaryKeyWithBLOBs(Clicklist record);

    int updateByPrimaryKey(Clicklist record);
}