package com.huban.dao;

import java.util.List;

import com.huban.pojo.Verifyrecords;

public interface VerifyrecordsMapper {
    int deleteByPrimaryKey(Long verifyrecordId);

    int insert(Verifyrecords record);

    int insertSelective(Verifyrecords record);

    Verifyrecords selectByPrimaryKey(Long verifyrecordId);

    int updateByPrimaryKeySelective(Verifyrecords record);

    int updateByPrimaryKey(Verifyrecords record);
    
    List<Verifyrecords> queryList(Verifyrecords record);
}