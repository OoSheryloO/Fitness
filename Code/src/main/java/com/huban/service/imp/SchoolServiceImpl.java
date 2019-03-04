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
import com.huban.dao.SchoolMapper;
import com.huban.service.AreaService;
import com.huban.service.SchoolService;

/**
 * @author Sheryl
 * @created 2017年10月27日 下午4:44:57
 */
@Service("schoolService")
public class SchoolServiceImpl implements SchoolService{
     
	@Resource
	private SchoolMapper mapper;
	/* (non-Javadoc)
	 * @see com.huban.service.BookService#queryBooks(java.util.Map)
	 */

	@Override
	public List<areapart> QuerySomeMessage(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return mapper.QuerySomeMessage(map);
	}

	
}
