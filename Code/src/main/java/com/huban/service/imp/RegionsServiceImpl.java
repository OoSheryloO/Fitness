/**
 * 
 */
package com.huban.service.imp;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.huban.dao.RegionsMapper;
import com.huban.pojo.Regions;
import com.huban.service.RegionsService;


@Service("regionsService")
public class RegionsServiceImpl implements RegionsService{
     
	@Resource
	private RegionsMapper mapper;
	/* (non-Javadoc)
	 * @see com.huban.service.BookService#queryBooks(java.util.Map)
	 */

	@Override
	public List<Regions> firstLevel(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return mapper.firstLevel(map);
	}

	@Override
	public List<Regions> secondLevel(Long regionId) {
		// TODO Auto-generated method stub
		return mapper.secondLevel(regionId);
	}

	@Override
	public List<Regions> thirdLevel(Long regionId) {
		// TODO Auto-generated method stub
		return mapper.thirdLevel(regionId);
	}	

	
}
