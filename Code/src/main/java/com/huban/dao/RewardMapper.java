package com.huban.dao;

import java.util.List;
import java.util.Map;

import com.huban.pojo.Reward;

public interface RewardMapper {
    int deleteByPrimaryKey(Long rewardId);

    int insert(Reward record);

    int insertSelective(Reward record);

    Reward selectByPrimaryKey(Long rewardId);

    int updateByPrimaryKeySelective(Reward record);

    int updateByPrimaryKey(Reward record);
    
    List<Reward> FindUserByRId(Long videoId);
    
    List<Long> FindUserIdByRId(Map<String, Object> map);
}