package com.huban.dao;

import com.huban.pojo.Game;

public interface GameMapper {
    int deleteByPrimaryKey(Long gameId);

    int insert(Game record);

    int insertSelective(Game record);

    Game selectByPrimaryKey(Long gameId);

    int updateByPrimaryKeySelective(Game record);

    int updateByPrimaryKey(Game record);
}