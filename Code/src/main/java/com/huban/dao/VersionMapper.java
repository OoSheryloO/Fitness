package com.huban.dao;

import java.util.Map;

import com.huban.pojo.Version;

public interface VersionMapper {
    int deleteByPrimaryKey(Long versionId);

    int insert(Version record);

    int insertSelective(Version record);

    Version selectByPrimaryKey(Long versionId);

    int updateByPrimaryKeySelective(Version record);

    int updateByPrimaryKey(Version record);
    
    Version MAX(Map<String, Object> map);
    
    Integer querypublish(Map<String, Object> map);
}