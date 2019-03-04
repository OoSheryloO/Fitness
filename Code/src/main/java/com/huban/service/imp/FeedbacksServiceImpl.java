package com.huban.service.imp;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.huban.dao.FeedbacksMapper;
import com.huban.pojo.Feedbacks;
import com.huban.service.FeedbacksService;

@Service("feedbacksService")
public class FeedbacksServiceImpl implements FeedbacksService{

	@Resource
	private FeedbacksMapper mapper;

	@Override
	public int addFeedback(Feedbacks record) {
		// TODO Auto-generated method stub
		return mapper.addFeedback(record);
	}



}
