package com.huban.dao;

import java.util.List;
import java.util.Map;

import com.huban.pojo.Comment;

public interface CommentMapper {
    int deleteByPrimaryKey(Long commentId);

    int insert(Comment record);

    int insertSelective(Comment record);

    Comment selectByPrimaryKey(Long commentId);
    
    List<Comment> videocomment(Map<String,Object> map);

    int updateByPrimaryKeySelective(Comment record);

    int updateByPrimaryKeyWithBLOBs(Comment record);

    int updateByPrimaryKey(Comment record);
    
    List<Comment> bookComment(Map<String, Object> map);
    
    int commentNum(Long forumId);
    
    Comment tome(Long userId);
    
    List<Long> selectnoteid(Map<String, Object> map);
    
    List<Comment> ComToMe(Map<String, Object> map);
    
    List<Comment> forumcomment(Map<String, Object> map);
    
    long RedateIdToUserId(Long replyDataId);
    
    List<Comment> selectByReplyDateId(Long replyDataId);
    
}