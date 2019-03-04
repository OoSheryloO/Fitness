package com.huban.dao;

import java.util.List;
import java.util.Map;

import com.huban.construct.LstDeptModel;
import com.huban.pojo.Department;
import com.huban.pojo.DepartmentWithBLOBs;;

public interface DepartmentMapper {
    int deleteByPrimaryKey(Long departmentId);

    int insert(DepartmentWithBLOBs record);

    int insertSelective(DepartmentWithBLOBs record);

    DepartmentWithBLOBs selectByPrimaryKey(Long departmentId);

    int updateByPrimaryKeySelective(DepartmentWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(DepartmentWithBLOBs record);

    int updateByPrimaryKey(Department record);
    
    /* V2 by Sheryl */
    int AddNewMessage(Department record);
    
    String QuerySerialNumber(Map<String, Object> map);
    
    String BaseQueryDeptNameMap(Map<String, Object> map);
    
    List<LstDeptModel> QueryDeptList(Map<String, Object> map);
    
    int UpdateMessage(DepartmentWithBLOBs record);
    
    Map<String, Object> QuerySomeMessage(Map<String, Object> map);
}