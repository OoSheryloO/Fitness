/**
 * 
 */
package com.huban.service.imp;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.huban.dao.VersionMapper;
import com.huban.pojo.Version;
import com.huban.service.VersionService;

@Service("versionService")
public class VersionServiceImpl implements VersionService{

	@Resource
	private VersionMapper mapper;

	/* (non-Javadoc)
	 * @see com.huban.service.VideoService#addVideo(com.huban.pojo.Video)
	 */
	@Override
	public Version MAX(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return mapper.MAX(map);
	}

	@Override
	public Integer querypublish(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return mapper.querypublish(map);
	}
}
