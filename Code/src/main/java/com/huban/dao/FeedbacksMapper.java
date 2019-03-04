package com.huban.dao;

import com.huban.pojo.Feedbacks;

public interface FeedbacksMapper {
    int deleteByPrimaryKey(Long feedbackId);

    int insert(Feedbacks record);

    int insertSelective(Feedbacks record);

    Feedbacks selectByPrimaryKey(Long feedbackId);

    int updateByPrimaryKeySelective(Feedbacks record);

    int updateByPrimaryKeyWithBLOBs(Feedbacks record);

    int updateByPrimaryKey(Feedbacks record);
    
    int addFeedback(Feedbacks record);
}