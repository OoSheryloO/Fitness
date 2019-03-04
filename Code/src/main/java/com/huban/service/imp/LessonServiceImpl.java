package com.huban.service.imp;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.huban.dao.DictMapper;
import com.huban.dao.LessonMapper;
import com.huban.pojo.Dict;
import com.huban.pojo.Lesson;
import com.huban.service.DictService;
import com.huban.service.LessonService;

@Service("lessonService")
public class LessonServiceImpl implements LessonService{
	@Resource
	private LessonMapper mapper;

	/* (non - Javadoc)
	* @param map
	* @return
	* @see com.huban.service.LessonService#SearchMessageByCondition(java.util.Map)
	*/
	@Override
	public List<Lesson> SearchMessageByCondition(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return mapper.SearchMessageByCondition(map);
	}
	
}
