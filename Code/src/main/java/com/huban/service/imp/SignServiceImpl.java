package com.huban.service.imp;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.huban.dao.SignMapper;
import com.huban.pojo.Sign;
import com.huban.service.SignService;

@Service("signService")
public class SignServiceImpl implements SignService{

	@Resource
	private SignMapper mapper;

	@Override
	public int addSign(Sign sign) {
		// TODO Auto-generated method stub
		return mapper.insert(sign);
	}

	@Override
	public List<Sign> querySign(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return mapper.querySign(map);
	}

	@Override
	public int weekSign(Long userId) {
		// TODO Auto-generated method stub
		return mapper.weekSign(userId);
	}

	@Override
	public int querynow(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return mapper.querynow(map);
	}

	@Override
	public List<Sign> queryMouthSign(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return mapper.queryMouthSign(map);
	}

}
