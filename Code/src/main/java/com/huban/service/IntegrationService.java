package com.huban.service;

import com.huban.pojo.Integration;

public interface IntegrationService {
	public int deleteByPrimaryKey(Long integrationId);

	public int insert(Integration record);

	public int insertSelective(Integration record);

	public Integration selectByPrimaryKey(Long integrationId);

	public int updateByPrimaryKeySelective(Integration record);

	public int updateByPrimaryKeyWithBLOBs(Integration record);

	public int updateByPrimaryKey(Integration record);

	public int addmessage(Integration integration);
	
}
