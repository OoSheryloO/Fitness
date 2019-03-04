/**
 * 
 */
package com.huban.service;


import java.util.List;
import java.util.Map;

import com.huban.construct.TeacherModel;
import com.huban.pojo.Video;
import com.huban.pojo.VideoWithBLOBs;


/**
 * @author GeJiangbo
 * @date 2017年5月20日
 */
public interface VideoService {
    public int addVideo(VideoWithBLOBs video);
    
    public VideoWithBLOBs selectVideo(Long videoId);
     
    public List<VideoWithBLOBs> queryVideos(Map<String, Object> map);
    
    public int addlike(Video video);
    
    public int cutlike(Video video);
    
    public int addclick(Video video);
    
    public int VideoShare(Long videoId);
    
    public int addcommentcount(Long videoId);
    
    public long selectVideoUserId(Long videoId);
    
    public String selectusermoney(Long videoId);
    
    public List<TeacherModel> SearchLstTeacher(Map<String, Object> map);
}
