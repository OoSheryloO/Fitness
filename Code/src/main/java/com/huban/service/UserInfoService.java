package com.huban.service;

import java.util.List;
import java.util.Map;

import org.apache.hadoop.classification.InterfaceAudience.Public;
import org.springframework.stereotype.Service;

import com.huban.construct.LstDeptMemberModel;
import com.huban.pojo.User;
import com.huban.pojo.UserInfo;

public interface UserInfoService {
    
	public int AddNewMessage(UserInfo record);
	
	public int QueryExist(Map<String, Object> map);
	
	public int UpdateBindingMessage(UserInfo record);
	
	public Map<String, Object> QuerySomeMessage(Map<String, Object> map);
	/**
	 * @return 查询编号
	 */
	public String QuerySerialNumber(Map<String, Object> map);
	
	public List<LstDeptMemberModel> QueryLstDeptMember(Map<String, Object> map);
	
	public long QueryInheritSequence(long DeptId);
	
	public List<UserInfo> SearchLstUserInfoByCondition(Map<String, Object> map);

}
