package com.huban.dao;

import com.huban.pojo.LikeStatus;

public interface LikeStatusMapper {
    int deleteByPrimaryKey(Long coluId);

    int insert(LikeStatus record);
    
    int addforumlike(LikeStatus record);

    int insertSelective(LikeStatus record);

    LikeStatus selectByPrimaryKey(Long coluId);

    LikeStatus selectLike(LikeStatus record);
    
    int updateByPrimaryKeySelective(LikeStatus record);

    int updateByPrimaryKey(LikeStatus record);
    
    int findlike(LikeStatus record);
    
    int updatelikeStatus(LikeStatus record);
    
    int queryexist(Long couluId);
    
    
    int findlikenote(LikeStatus record);
    
    int updatelikeStatusnote(LikeStatus record);
    
    LikeStatus selectLikenote(LikeStatus record);
    
}