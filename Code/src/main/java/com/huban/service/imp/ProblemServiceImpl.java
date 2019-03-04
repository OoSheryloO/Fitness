/**
 * 
 */
package com.huban.service.imp;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.huban.dao.ProblemMapper;
import com.huban.pojo.Problem;
import com.huban.service.ProblemService;

@Service("problemService")
public class ProblemServiceImpl implements ProblemService{
     @Resource
     private ProblemMapper mapper;

	@Override
	public int addProblem(Problem Problem) {
		return mapper.addProblem(Problem);
	}

	@Override
	public List<Problem> getProblemList(Map<String, Object> map) {
		return mapper.getProblemList(map);
	}

	@Override
	public int getProblemCount(Map<String, Object> map) {
		return mapper.getProblemCount(map);
	}

	@Override
	public int deleteProblem(Map<String, Object> map) {
		return mapper.deleteProblem(map);
	}
     
	
}
