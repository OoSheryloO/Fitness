package com.huban.service.imp;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.huban.construct.LstDeptMemberModel;
import com.huban.dao.UserInfoMapper;
import com.huban.pojo.UserInfo;
import com.huban.service.UserInfoService;

@Service("userInfoService")
public class UserInfoServiceImpl implements UserInfoService{
    @Resource
    private UserInfoMapper mapper;

	@Override
	public int AddNewMessage(UserInfo record) {
		// TOD Auto-generated method stub
		return mapper.AddNewMessage(record);
	}

	@Override
	public int QueryExist(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return mapper.QueryExist(map);
	}

	@Override
	public int UpdateBindingMessage(UserInfo record) {
		// TODO Auto-generated method stub
		return mapper.UpdateBindingMessage(record);
	}

	@Override
	public Map<String, Object> QuerySomeMessage(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return mapper.QuerySomeMessage(map);
	}

	@Override
	public String QuerySerialNumber(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return mapper.QuerySerialNumber(map);
	}

	@Override
	public List<LstDeptMemberModel> QueryLstDeptMember(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return mapper.QueryLstDeptMember(map);
	}

	@Override
	public long QueryInheritSequence(long DeptId) {
		// TODO Auto-generated method stub
		return mapper.QueryInheritSequence(DeptId);
	}

	/* (non - Javadoc)
	* @param map
	* @return
	* @see com.huban.service.UserInfoService#SearchLstUserInfoByCondition(java.util.Map)
	*/
	@Override
	public List<UserInfo> SearchLstUserInfoByCondition(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return mapper.SearchLstUserInfoByCondition(map);
	}
   
}
