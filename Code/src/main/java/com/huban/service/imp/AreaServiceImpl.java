/**
 * 
 */
package com.huban.service.imp;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.huban.construct.AreaRetrunModel;
import com.huban.dao.AreaMapper;
import com.huban.service.AreaService;

/**
 * @author GeJiangbo
 * @date 2017年6月1日
 */
@Service("areaService")
public class AreaServiceImpl implements AreaService{
     
	@Resource
	private AreaMapper mapper;
	/* (non-Javadoc)
	 * @see com.huban.service.BookService#queryBooks(java.util.Map)
	 */
//	@Override
//	public List<String> QueryNum(String province) {
//		// TODO Auto-generated method stub
//		return mapper.QueryNum(province);
//	}

	@Override
	public List<AreaRetrunModel> QuerySomeMessage(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return mapper.QuerySomeMessage(map);
	}
	

	
}
