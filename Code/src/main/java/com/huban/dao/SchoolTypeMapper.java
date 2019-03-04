package com.huban.dao;

import com.huban.pojo.SchoolType;

public interface SchoolTypeMapper {
    int deleteByPrimaryKey(Short id);

    int insert(SchoolType record);

    int insertSelective(SchoolType record);

    SchoolType selectByPrimaryKey(Short id);

    int updateByPrimaryKeySelective(SchoolType record);

    int updateByPrimaryKey(SchoolType record);
}