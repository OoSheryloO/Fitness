package com.huban.dao;

import com.huban.pojo.Errorlogs;

public interface ErrorlogsMapper {
    int deleteByPrimaryKey(Long errorlogId);

    int insert(Errorlogs record);

    int insertSelective(Errorlogs record);

    Errorlogs selectByPrimaryKey(Long errorlogId);

    int updateByPrimaryKeySelective(Errorlogs record);

    int updateByPrimaryKeyWithBLOBs(Errorlogs record);

    int updateByPrimaryKey(Errorlogs record);
}