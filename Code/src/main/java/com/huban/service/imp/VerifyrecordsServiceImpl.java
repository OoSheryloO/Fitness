/**
 * 
 */
package com.huban.service.imp;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.huban.dao.VerifyrecordsMapper;
import com.huban.pojo.Verifyrecords;
import com.huban.service.VerifyrecordsService;

/**
 * @author GeJiangbo
 * @date 2017年5月12日
 */
@Service("verifyRecordsService")
public class VerifyrecordsServiceImpl implements VerifyrecordsService{
    @Resource
    VerifyrecordsMapper mapper;

	@Override
	public List<Verifyrecords> queryList(Verifyrecords record) {
		
		return mapper.queryList(record);
	}

	
	@Override
	public int change(Verifyrecords record) {
		// TODO Auto-generated method stub
		return mapper.updateByPrimaryKey(record);
	}


	@Override
	public int insert(Verifyrecords record) {
		// TODO Auto-generated method stub
		return mapper.insert(record);
	}
	
	
}
