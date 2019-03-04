/**
 * 
 */
package com.huban.service;

import java.util.List;
import java.util.Map;

import com.huban.pojo.Evaluation;

public interface EvaluationService {

		//增
	   public int addEvaluation(Evaluation evaluation);
		
		//查
	   public List<Evaluation> getEvaluationList(Map<String, Object> map);
		
	   public int getEvaluationCount(Map<String, Object> map);
		
		//删
	   public int deleteEvaluation(Map<String, Object> map);
		
}
