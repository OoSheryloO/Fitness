/**
 * 
 */
package com.huban.service;

import java.util.List;
import java.util.Map;

import com.huban.pojo.Problem;

public interface ProblemService {

		//增
	   public int addProblem(Problem Problem);
		
		//查
	   public List<Problem> getProblemList(Map<String, Object> map);
		
	   public int getProblemCount(Map<String, Object> map);
		
		//删
	   public int deleteProblem(Map<String, Object> map);
		
}
