/**
 * 
 */
package com.huban.service;

import com.huban.pojo.LikeStatus;

/**
 * @author GeJiangbo
 * @date 2017年5月22日
 */
public interface LikeStatusService {

	 public int findLike(LikeStatus likeStatus);
	
	 public LikeStatus selectLike(LikeStatus likeStatus);
	 
	 public int addLike(LikeStatus likeStatus);
	 
	 public int updateLike(LikeStatus likeStatus);
	 
	 public int queryexist(Long coluId);
	 
	 public int addforumlike(LikeStatus record);
	 
	 public int findlikenote(LikeStatus record);
	    
	 public int updatelikeStatusnote(LikeStatus record);
	    
	 public LikeStatus selectLikenote(LikeStatus record);
}
