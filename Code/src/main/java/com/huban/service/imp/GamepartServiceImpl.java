/**
 * 
 */
package com.huban.service.imp;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.huban.dao.GamepartMapper;
import com.huban.pojo.Gamepart;
import com.huban.service.GamepartService;

/**
 * @author GeJiangbo
 * @date 2017年5月26日
 */
@Service("gamepartService")
public class GamepartServiceImpl implements GamepartService{
     
	@Resource
	private GamepartMapper mapper;
	/* (non-Javadoc)
	 * @see com.huban.service.GamepartService#queryList(java.util.Map)
	 */
	@Override
	public List<Gamepart> queryList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return mapper.queryList(map);
	}
	@Override
	public Gamepart selectOne(Long gameId) {
		// TODO Auto-generated method stub
		return mapper.selectOne(gameId);
	}
}
