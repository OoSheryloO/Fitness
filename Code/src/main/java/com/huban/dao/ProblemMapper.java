package com.huban.dao;

import java.util.List;
import java.util.Map;

import com.huban.pojo.Problem;

public interface ProblemMapper {
	//增
	int addProblem(Problem Problem);
	
	//查
	List<Problem> getProblemList(Map<String, Object> map);
	
	int getProblemCount(Map<String, Object> map);
	
	//删
	int deleteProblem(Map<String, Object> map);
}