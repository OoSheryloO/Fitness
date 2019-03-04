/**
 * 
 */
package com.huban.service;

import java.util.List;

import com.huban.pojo.Verifyrecords;


/**
 * @author GeJiangbo
 * @date 2017年5月12日
 */
public interface VerifyrecordsService {
 
	public List<Verifyrecords> queryList(Verifyrecords record);
	
	public int change(Verifyrecords record);
	
	public int insert(Verifyrecords record);
}
