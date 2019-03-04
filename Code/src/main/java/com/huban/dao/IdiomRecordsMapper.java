package com.huban.dao;

import com.huban.pojo.IdiomRecords;

public interface IdiomRecordsMapper {
    int deleteByPrimaryKey(Long idiomId);

    int insert(IdiomRecords record);

    int insertSelective(IdiomRecords record);

    IdiomRecords selectByPrimaryKey(Long idiomId);

    int updateByPrimaryKeySelective(IdiomRecords record);

    int updateByPrimaryKey(IdiomRecords record);
}