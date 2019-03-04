package com.huban.dao;

import com.huban.pojo.Baseinfo;

public interface BaseinfoMapper {
    int deleteByPrimaryKey(Long baseinfoId);

    int insert(Baseinfo record);

    int insertSelective(Baseinfo record);

    Baseinfo selectByPrimaryKey(Long baseinfoId);

    int updateByPrimaryKeySelective(Baseinfo record);

    int updateByPrimaryKey(Baseinfo record);
}