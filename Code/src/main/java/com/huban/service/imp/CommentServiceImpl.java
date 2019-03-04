/**
 * 
 */
package com.huban.service.imp;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.huban.dao.CommentMapper;
import com.huban.pojo.Comment;
import com.huban.service.CommentService;

/**
 * @author GeJiangbo
 * @date 2017年5月22日
 */
@Service("commentService")
public class CommentServiceImpl implements CommentService{
     @Resource
     private CommentMapper mapper;
	/* (non-Javadoc)
	 * @see com.huban.service.CommentService#addcomment(com.huban.pojo.Comment)
	 */
	@Override
	public int addcomment(Comment comment) {
		// TODO Auto-generated method stub
		return mapper.insert(comment);
	}
	/* (non-Javadoc)
	 * @see com.huban.service.CommentService#querycomment(com.huban.pojo.Comment)
	 */
	@Override
	public List<Comment> videocomment(Map<String,Object> map) {
		// TODO Auto-generated method stub
		return mapper.videocomment(map);
	}
	@Override
	public List<Comment> bookComment(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return mapper.bookComment(map);
	}
	@Override
	public int commentNum(Long forumId) {
		// TODO Auto-generated method stub
		return mapper.commentNum(forumId);
	}
	@Override
	public List<Long> selectnoteid(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return mapper.selectnoteid(map);
	}
	@Override
	public List<Comment> ComToMe(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return mapper.ComToMe(map);
	}
	@Override
	public List<Comment> forumcomment(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return mapper.forumcomment(map);
	}
	@Override
	public long RedateIdToUserId(Long replyDataId) {
		// TODO Auto-generated method stub
		return mapper.RedateIdToUserId(replyDataId);
	}
	@Override
	public List<Comment> selectByReplyDateId(Long replyDataId) {
		// TODO Auto-generated method stub
		return mapper.selectByReplyDateId(replyDataId);
	}

}
