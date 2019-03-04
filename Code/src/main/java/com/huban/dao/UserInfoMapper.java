package com.huban.dao;

import java.util.List;
import java.util.Map;

import com.huban.construct.LstDeptMemberModel;
import com.huban.construct.UserInfoUserStatuModel;
import com.huban.pojo.UserInfo;

public interface UserInfoMapper {
//    int deleteByPrimaryKey(Long userinfoId);
//
//    int insert(UserInfo record);
//
//    int insertSelective(UserInfo record);
//
//    UserInfo selectByPrimaryKey(Long userinfoId);
//
//    int updateByPrimaryKeySelective(UserInfo record);
//
//    int updateByPrimaryKeyWithBLOBs(UserInfo record);
//
//    int updateByPrimaryKey(UserInfo record);
    
    /* by Sheryl */
    int AddNewMessage(UserInfo record);
    
    int QueryExist(Map<String, Object> map);
    
    int UpdateBindingMessage(UserInfo record);
    
    Map<String, Object> QuerySomeMessage(Map<String, Object> map);
    
    public UserInfoUserStatuModel BaseQueryUserInfoMessage(long params);
    
    String QuerySerialNumber(Map<String, Object> map);
    
    int BaseQueryUserCount(Map<String, Object> map);
    
    List<String> BaseQueryLstHeadIcon(Map<String, Object> map);
    
    List<LstDeptMemberModel> QueryLstDeptMember(Map<String, Object> map);
    
    long QueryInheritSequence(long DeptId);
    
    List<UserInfo> SearchLstUserInfoByCondition(Map<String, Object> map);
}