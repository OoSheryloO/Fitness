package com.huban.service.imp;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.huban.dao.IntegrationMapper;
import com.huban.pojo.Integration;
import com.huban.service.IntegrationService;

@Service("integrationService")
public class IntegrationServiceImpl implements IntegrationService{
	@Resource
	private IntegrationMapper mapper;

	@Override
	public int addmessage(Integration record) {
		// TODO Auto-generated method stub
		return mapper.addmessage(record);
	}

	@Override
	public int deleteByPrimaryKey(Long integrationId) {
		// TODO Auto-generated method stub
		return mapper.deleteByPrimaryKey(integrationId);
	}

	@Override
	public int insert(Integration record) {
		// TODO Auto-generated method stub
		return mapper.insert(record);
	}

	@Override
	public int insertSelective(Integration record) {
		// TODO Auto-generated method stub
		return mapper.insertSelective(record);
	}

	@Override
	public Integration selectByPrimaryKey(Long integrationId) {
		// TODO Auto-generated method stub
		return mapper.selectByPrimaryKey(integrationId);
	}

	@Override
	public int updateByPrimaryKeySelective(Integration record) {
		// TODO Auto-generated method stub
		return mapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKeyWithBLOBs(Integration record) {
		// TODO Auto-generated method stub
		return mapper.updateByPrimaryKeyWithBLOBs(record);
	}

	@Override
	public int updateByPrimaryKey(Integration record) {
		// TODO Auto-generated method stub
		return mapper.updateByPrimaryKey(record);
	}
	
}
