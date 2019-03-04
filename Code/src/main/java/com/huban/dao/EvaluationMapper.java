package com.huban.dao;

import java.util.List;
import java.util.Map;

import com.huban.pojo.Evaluation;

public interface EvaluationMapper {
	//增
	int addEvaluation(Evaluation evaluation);
	
	//查
	List<Evaluation> getEvaluationList(Map<String, Object> map);
	
	int getEvaluationCount(Map<String, Object> map);
	
	//删
	int deleteEvaluation(Map<String, Object> map);
}