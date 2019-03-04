/**
 * 
 */
package com.huban.service;

import java.util.List;
import java.util.Map;

import com.huban.construct.LstDeptMemberModel;
import com.huban.pojo.Activitypart;

/**
 * @author GeJiangbo
 * @date 2017年5月26日
 */
public interface ActivitypartService {

	public List<Activitypart> queryList(Map<String,Object> map);
	
	public int queryById(Map<String, Object> map);
	
	public int addActivity(Activitypart activitypart);
	
	public List<LstDeptMemberModel> QueryLstUser(Map<String, Object> map);
	
	public int ChanceMessage(Activitypart record);
	
}
