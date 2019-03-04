package com.huban.dao;

import java.util.List;
import java.util.Map;

import com.huban.pojo.Grade;

public interface GradeMapper {
	//增
	int addGrade(Grade Grade);
	
	//查
	List<Grade> getGradeList(Map<String, Object> map);
	
	int getGradeCount(Map<String, Object> map);
	
	//删
	int deleteGrade(Map<String, Object> map);
}