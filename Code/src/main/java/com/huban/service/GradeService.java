/**
 * 
 */
package com.huban.service;

import java.util.List;
import java.util.Map;

import com.huban.pojo.Grade;

public interface GradeService {

		//增
	   public int addGrade(Grade Grade);
		
		//查
	   public List<Grade> getGradeList(Map<String, Object> map);
		
	   public int getGradeCount(Map<String, Object> map);
		
		//删
	   public int deleteGrade(Map<String, Object> map);
		
}
