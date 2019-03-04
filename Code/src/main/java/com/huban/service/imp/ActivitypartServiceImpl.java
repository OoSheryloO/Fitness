/**
 * 
 */
package com.huban.service.imp;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.huban.construct.LstDeptMemberModel;
import com.huban.dao.ActivitypartMapper;
import com.huban.pojo.Activitypart;
import com.huban.service.ActivitypartService;

/**
 * @author GeJiangbo
 * @date 2017年5月26日
 */
@Service("activitypartService")
public class ActivitypartServiceImpl implements ActivitypartService{
     @Resource
     private ActivitypartMapper mapper;
	/* (non-Javadoc)
	 * @see com.huban.service.ActivitypartService#queryList(java.util.Map)
	 */
	@Override
	public List<Activitypart> queryList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return mapper.queryList(map);
	}
	
	@Override
	public int queryById(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return mapper.queryById(map);
	}
	
	@Override
	public int addActivity(Activitypart activitypart) {
		// TODO Auto-generated method stub
		return mapper.addActivity(activitypart);
	}
	
	@Override
	public List<LstDeptMemberModel> QueryLstUser(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return mapper.QueryLstUser(map);
	}

	@Override
	public int ChanceMessage(Activitypart record) {
		// TODO Auto-generated method stub
		return mapper.ChanceMessage(record);
	}
	
    
}
