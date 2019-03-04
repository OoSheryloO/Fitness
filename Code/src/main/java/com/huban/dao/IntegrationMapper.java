package com.huban.dao;

import com.huban.pojo.Integration;

public interface IntegrationMapper {
    int deleteByPrimaryKey(Long integrationId);

    int insert(Integration record);

    int insertSelective(Integration record);

    Integration selectByPrimaryKey(Long integrationId);

    int updateByPrimaryKeySelective(Integration record);

    int updateByPrimaryKeyWithBLOBs(Integration record);

    int updateByPrimaryKey(Integration record);
    
    int addmessage(Integration integration);
}