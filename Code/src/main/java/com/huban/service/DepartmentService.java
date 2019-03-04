package com.huban.service;

import java.util.List;
import java.util.Map;

import com.huban.construct.LstDeptModel;
import com.huban.pojo.Department;
import com.huban.pojo.DepartmentWithBLOBs;

public interface DepartmentService {
	
	public int AddNewMessage(Department record);
	
	public String QuerySerialNumber(Map<String, Object> map);
	
	public List<LstDeptModel> QueryDeptList(Map<String, Object> map);
	
	public int UpdateMessage(DepartmentWithBLOBs record);
	
	public Map<String, Object> QuerySomeMessage(Map<String, Object> map);

}
