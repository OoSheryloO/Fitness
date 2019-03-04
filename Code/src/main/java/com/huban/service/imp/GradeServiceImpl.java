/**
 * 
 */
package com.huban.service.imp;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.huban.dao.GradeMapper;
import com.huban.pojo.Grade;
import com.huban.service.GradeService;

@Service("gradeService")
public class GradeServiceImpl implements GradeService{
     @Resource
     private GradeMapper mapper;

	@Override
	public int addGrade(Grade Grade) {
		return mapper.addGrade(Grade);
	}

	@Override
	public List<Grade> getGradeList(Map<String, Object> map) {
		return mapper.getGradeList(map);
	}

	@Override
	public int getGradeCount(Map<String, Object> map) {
		return mapper.getGradeCount(map);
	}

	@Override
	public int deleteGrade(Map<String, Object> map) {
		return mapper.deleteGrade(map);
	}
     
	
}
