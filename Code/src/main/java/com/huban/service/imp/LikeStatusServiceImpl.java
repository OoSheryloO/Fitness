/**
 * 
 */
package com.huban.service.imp;


import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.huban.dao.LikeStatusMapper;
import com.huban.pojo.LikeStatus;
import com.huban.service.LikeStatusService;

/**
 * @author GeJiangbo
 * @date 2017年5月22日
 */
@Service("likeStatusService")
public class LikeStatusServiceImpl implements LikeStatusService{
    @Resource
    private LikeStatusMapper mapper;
	
	/* (non-Javadoc)
	 * @see com.huban.service.LikeStatusService#findLike(java.lang.Long)
	 */
	@Override
	public int findLike(LikeStatus likeStatus) {
		// TODO Auto-generated method stub
		return mapper.findlike(likeStatus);
	}

	/* (non-Javadoc)
	 * @see com.huban.service.LikeStatusService#addLike(com.huban.pojo.LikeStatus)
	 */
	@Override
	public int addLike(LikeStatus likeStatus) {
		// TODO Auto-generated method stub
		return mapper.insert(likeStatus);
	}

	/* (non-Javadoc)
	 * @see com.huban.service.LikeStatusService#selectLike(java.lang.Long)
	 */
	@Override
	public LikeStatus selectLike(LikeStatus likeStatus) {
		// TODO Auto-generated method stub
		return mapper.selectLike(likeStatus);
	}

	/* (non-Javadoc)
	 * @see com.huban.service.LikeStatusService#updateLike(com.huban.pojo.LikeStatus)
	 */
	@Override
	public int updateLike(LikeStatus likeStatus) {
		// TODO Auto-generated method stub
		return mapper.updatelikeStatus(likeStatus);
	}

	@Override
	public int queryexist(Long coluId) {
		// TODO Auto-generated method stub
		return mapper.queryexist(coluId);
	}

	@Override
	public int addforumlike(LikeStatus record) {
		// TODO Auto-generated method stub
		return mapper.addforumlike(record);
	}

	@Override
	public int findlikenote(LikeStatus record) {
		// TODO Auto-generated method stub
		return mapper.findlikenote(record);
	}

	@Override
	public int updatelikeStatusnote(LikeStatus record) {
		// TODO Auto-generated method stub
		return mapper.updatelikeStatusnote(record);
	}

	@Override
	public LikeStatus selectLikenote(LikeStatus record) {
		// TODO Auto-generated method stub
		return mapper.selectLikenote(record);
	}

}
