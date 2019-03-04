package com.huban.service.imp;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huban.dao.AccessActiveMapper;
import com.huban.pojo.AccessActive;
import com.huban.service.AccessActiveService;

/**
 * wtb_vitaes
 */
 @Service("accessActiveService")
public class AccessActiveServiceImpl implements AccessActiveService{
	/**
	 * 查询全部
	 */
	 @Autowired
	private AccessActiveMapper mapper;

	/* (non - Javadoc)
	* @param obj
	* @return
	* @see com.huban.service.AccessActiveService#addAccessActive(com.huban.pojo.AccessActive)
	*/
	@Override
	public int addAccessActive(AccessActive obj) {
		// TODO Auto-generated method stub
		return mapper.addAccessActive(obj);
	}

	/* (non - Javadoc)
	* @param obj
	* @return
	* @see com.huban.service.AccessActiveService#updateAccessActive(com.huban.pojo.AccessActive)
	*/
	@Override
	public int updateAccessActive(AccessActive obj) {
		// TODO Auto-generated method stub
		return mapper.updateAccessActive(obj);
	}

	/* (non - Javadoc)
	* @param params
	* @return
	* @see com.huban.service.AccessActiveService#deleteAccessActive(java.util.Map)
	*/
	@Override
	public int deleteAccessActive(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return mapper.deleteAccessActive(params);
	}
}