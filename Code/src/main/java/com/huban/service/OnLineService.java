/**
 * 
 */
package com.huban.service;

import java.util.List;
import java.util.Map;

import com.huban.pojo.Onlines;

/**
 * @author GeJiangbo
 * @date 2017年5月16日
 */
public interface OnLineService {

	 List<Onlines> queryList(Map<String,Object> map);
	 
	 int changeOnline(Onlines onlines);
	 
	 int addOnline(Onlines onlines);
}
