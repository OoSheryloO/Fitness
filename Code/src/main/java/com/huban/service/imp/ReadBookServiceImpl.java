package com.huban.service.imp;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.huban.dao.ReadBookMapper;
import com.huban.pojo.ReadBook;
import com.huban.service.ReadBookService;

/**
 * @ClassName: ReadBookServiceImpl
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author Sheryl
 * @date 2017年12月1日 下午4:17:18
 */
@Service("readBookService")
public class ReadBookServiceImpl implements ReadBookService{
     
	@Resource
	private ReadBookMapper mapper;

	/* (non - Javadoc)
	* @param map
	* @return
	* @see com.huban.service.ReadBookService#SearchLstByCondition(java.util.Map)
	*/
	@Override
	public List<ReadBook> SearchLstByCondition(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return mapper.SearchLstByCondition(map);
	}

	
}
