/**
 * 
 */
package com.huban.service.imp;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.huban.construct.TeacherModel;
import com.huban.dao.VideoMapper;
import com.huban.pojo.Video;
import com.huban.pojo.VideoWithBLOBs;
import com.huban.service.VideoService;

/**
 * @author GeJiangbo
 * @date 2017年5月20日
 */
@Service("videoService")
public class VideoServiceImpl implements VideoService{

	@Resource
	private VideoMapper mapper;
	
	/* (non-Javadoc)
	 * @see com.huban.service.VideoService#addVideo(com.huban.pojo.Video)
	 */
	@Override
	public int addVideo(VideoWithBLOBs video) {
		// TODO Auto-generated method stub
		return mapper.insert(video);
	}

	/* (non-Javadoc)
	 * @see com.huban.service.VideoService#queryVideo(com.huban.pojo.VideoWithBLOBs)
	 */
	@Override
	public List<VideoWithBLOBs> queryVideos(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return mapper.queryvideoList(map);
	}

	/* (non-Javadoc)
	 * @see com.huban.service.VideoService#selectVideo(long)
	 */
	@Override
	public VideoWithBLOBs selectVideo(Long videoId) {
		// TODO Auto-generated method stub
		return mapper.selectVideo(videoId);
	}

	/* (non-Javadoc)
	 * @see com.huban.service.VideoService#addlike(com.huban.pojo.Video)
	 */
	@Override
	public int addlike(Video video) {
		// TODO Auto-generated method stub
		return mapper.addlike(video);
	}

	/* (non-Javadoc)
	 * @see com.huban.service.VideoService#cutlike(com.huban.pojo.Video)
	 */
	@Override
	public int cutlike(Video video) {
		// TODO Auto-generated method stub
		return mapper.cutlike(video);
	}

	/* (non-Javadoc)
	 * @see com.huban.service.VideoService#addclick(com.huban.pojo.Video)
	 */
	@Override
	public int addclick(Video video) {
		// TODO Auto-generated method stub
		return mapper.addclick(video);
	}

	@Override
	public int VideoShare(Long videoId) {
		// TODO Auto-generated method stub
		return mapper.VideoShare(videoId);
	}

	@Override
	public int addcommentcount(Long videoId) {
		// TODO Auto-generated method stub
		return mapper.addcommentcount(videoId);
	}

	@Override
	public long selectVideoUserId(Long videoId) {
		// TODO Auto-generated method stub
		return mapper.selectVideoUserId(videoId);
	}

	@Override
	public String selectusermoney(Long videoId) {
		// TODO Auto-generated method stub
		return mapper.selectusermoney(videoId);
	}

	/* (non - Javadoc)
	* @param map
	* @return
	* @see com.huban.service.VideoService#SearchLstTeacher(java.util.Map)
	*/
	@Override
	public List<TeacherModel> SearchLstTeacher(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return mapper.SearchLstTeacher(map);
	}

}
