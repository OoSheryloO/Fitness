package com.huban.service.imp;


import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.huban.dao.RewardMapper;
import com.huban.pojo.Reward;
import com.huban.service.RewardService;
@Service("rewardService")
public class RewardServiceImpl implements RewardService{
    @Resource
    private RewardMapper mapper;

	@Override
	public List<Reward> FindUserByRId(Long videoId) {
		// TODO Auto-generated method stub
		return mapper.FindUserByRId(videoId);
	}

	@Override
	public int insert(Reward reward) {
		// TODO Auto-generated method stub
		return mapper.insert(reward);
	}

	@Override
	public List<Long> FindUserIdByRId(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return mapper.FindUserIdByRId(map);
	}
}
