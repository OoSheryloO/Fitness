package com.huban.service;

import java.util.List;
import java.util.Map;

import com.huban.pojo.ReadBook;

/**
 * @ClassName: ReadBookService
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author Sheryl
 * @date 2017年12月1日 下午4:16:24
 */
public interface ReadBookService {
	
	public List<ReadBook> SearchLstByCondition(Map<String, Object> map);
	
}
