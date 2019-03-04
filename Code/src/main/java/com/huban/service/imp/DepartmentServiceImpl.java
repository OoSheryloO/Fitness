package com.huban.service.imp;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.huban.construct.LstDeptModel;
import com.huban.dao.DepartmentMapper;
import com.huban.pojo.Department;
import com.huban.pojo.DepartmentWithBLOBs;
import com.huban.service.DepartmentService;

@Service("departmentService")
public class DepartmentServiceImpl implements DepartmentService{
     @Resource
     private DepartmentMapper mapper;

	@Override
	public int AddNewMessage(Department record) {
		// TODO Auto-generated method stub
		return mapper.AddNewMessage(record);
	}

	@Override
	public String QuerySerialNumber(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return mapper.QuerySerialNumber(map);
	}

	@Override
	public List<LstDeptModel> QueryDeptList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return mapper.QueryDeptList(map);
	}

	@Override
	public int UpdateMessage(DepartmentWithBLOBs record) {
		// TODO Auto-generated method stub
		return mapper.UpdateMessage(record);
	}

	@Override
	public Map<String, Object> QuerySomeMessage(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return mapper.QuerySomeMessage(map);
	}

}
