package com.huban.service;

import java.util.List;
import java.util.Map;

import com.huban.pojo.Reward;

public interface RewardService {
    public List<Reward> FindUserByRId(Long videoId);
    
    public int insert(Reward reward);
    
    public List<Long> FindUserIdByRId(Map<String, Object> map);
}
