package com.huban.dao;

import java.util.List;
import java.util.Map;

import com.huban.construct.LstDeptMemberModel;
import com.huban.pojo.Activitypart;

public interface ActivitypartMapper {
    int deleteByPrimaryKey(Long activitypartId);

    int insert(Activitypart record);

    int insertSelective(Activitypart record);

    Activitypart selectByPrimaryKey(Long activitypartId);

    int updateByPrimaryKeySelective(Activitypart record);

    int updateByPrimaryKey(Activitypart record);
    
    List<Activitypart> queryList(Map<String, Object> map);
    
    int queryById(Map<String, Object> map);
    
    int addActivity(Activitypart activitypart);
    
    List<LstDeptMemberModel> QueryLstUser(Map<String, Object> map);
    
    int ChanceMessage(Activitypart record);
}