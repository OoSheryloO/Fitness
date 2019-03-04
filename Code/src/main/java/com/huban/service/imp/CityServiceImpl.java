/**
 * 
 */
package com.huban.service.imp;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.huban.construct.AreaRetrunModel;
import com.huban.construct.areapart;
import com.huban.dao.AreaMapper;
import com.huban.dao.CityMapper;
import com.huban.service.AreaService;
import com.huban.service.CityService;

/**
 * @author Sheryl
 * @date 2017年10月27日
 */
@Service("cityService")
public class CityServiceImpl implements CityService{
     
	@Resource
	private CityMapper mapper;
	/* (non-Javadoc)
	 * @see com.huban.service.BookService#queryBooks(java.util.Map)
	 */

	@Override
	public List<areapart> QuerySomeMessage(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return mapper.QuerySomeMessage(map);
	}

	
}
