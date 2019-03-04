/**
 * 
 */
package com.huban.service.imp;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.huban.dao.EvaluationMapper;
import com.huban.pojo.Evaluation;
import com.huban.service.EvaluationService;

@Service("evaluationService")
public class EvaluationServiceImpl implements EvaluationService{
     @Resource
     private EvaluationMapper mapper;

	@Override
	public int addEvaluation(Evaluation evaluation) {
		return mapper.addEvaluation(evaluation);
	}

	@Override
	public List<Evaluation> getEvaluationList(Map<String, Object> map) {
		return mapper.getEvaluationList(map);
	}

	@Override
	public int getEvaluationCount(Map<String, Object> map) {
		return mapper.getEvaluationCount(map);
	}

	@Override
	public int deleteEvaluation(Map<String, Object> map) {
		return mapper.deleteEvaluation(map);
	}
     
	
}
