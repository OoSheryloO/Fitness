package com.huban.dao;

import java.util.List;
import java.util.Map;

import com.huban.construct.TeacherModel;
import com.huban.pojo.Video;
import com.huban.pojo.VideoWithBLOBs;

public interface VideoMapper {
    int deleteByPrimaryKey(Long videoId);

    int insert(VideoWithBLOBs record);
    
    List<VideoWithBLOBs> queryvideoList(Map<String, Object> map);
    
    VideoWithBLOBs selectVideo(Long videoId);
    
    int addlike(Video video); 
    
    int cutlike(Video video);
    
    int addclick(Video video);
    
    int insertSelective(VideoWithBLOBs record);

    VideoWithBLOBs selectByPrimaryKey(Long videoId);

    int updateByPrimaryKeySelective(VideoWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(VideoWithBLOBs record);

    int updateByPrimaryKey(Video record);
    
    int VideoShare(Long videoId);
    
    int addcommentcount(Long videoId);
    
    long selectVideoUserId(Long videoId);
    
    String selectusermoney(Long videoId);
    
    List<TeacherModel> SearchLstTeacher(Map<String, Object> map);
}