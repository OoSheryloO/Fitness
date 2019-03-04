/**
 * 
 */
package com.huban.service;

import java.util.List;
import java.util.Map;

import com.huban.pojo.Comment;

/**
 * @author GeJiangbo
 * @date 2017年5月22日
 */
public interface CommentService {
    
	public int addcomment(Comment comment);
	
	public List<Comment> videocomment(Map<String,Object> map);
	
	public List<Comment> bookComment(Map<String, Object> map);
	
	public int commentNum(Long forumId);
	
	public List<Long> selectnoteid(Map<String, Object> map);
	
	public List<Comment> ComToMe(Map<String, Object> map);
	
	public List<Comment> forumcomment(Map<String, Object> map);
	
	public long RedateIdToUserId(Long replyDataId);
	
	public List<Comment> selectByReplyDateId(Long replyDataId);

}
