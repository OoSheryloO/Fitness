/**
 * 
 */
package com.huban.service.imp;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.huban.dao.BookpartMapper;
import com.huban.pojo.Bookpart;
import com.huban.service.BookpartService;

/**
 * @author GeJiangbo
 * @date 2017年5月26日
 */
@Service("bookpartService")
public class BookpartServiceImpl implements BookpartService{

	@Resource
	private BookpartMapper mapper;
	
	/* (non-Javadoc)
	 * @see com.huban.service.BookpartService#queryList(java.util.Map)
	 */
	@Override
	public List<Bookpart> queryList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return mapper.queryList(map);
	}

}
