/**
 * 
 */
package com.huban.service.imp;


import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.huban.dao.QuestionrecordsMapper;
import com.huban.service.QuestionrecordsService;

/**
 * @author GeJiangbo
 * @date 2017年5月26日
 */
@Service("questionrecordsService")
public class QuestionrecordsServiceImpl implements QuestionrecordsService{
     
	@Resource
	private QuestionrecordsMapper mapper;
	/* (non-Javadoc)
	 * @see com.huban.service.GamepartService#queryList(java.util.Map)
	 */
	
	@Override
	public List<Integer> SelectPass(Long userId) {
		// TODO Auto-generated method stub
		return mapper.SelectPass(userId);
	}

	@Override
	public List<Integer> SelectGame(Long userId) {
		// TODO Auto-generated method stub
		return mapper.SelectGame(userId);
	}

	@Override
	public Date SelectTime(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return mapper.SelectTime(map);
	}
	
	
	
}
