package com.huban.service;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: LessonService
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author Sheryl
 * @date 2017年11月13日 下午5:45:42
 *
 */
import com.huban.pojo.Lesson;
public interface LessonService {
	
	public List<Lesson> SearchMessageByCondition(Map<String, Object> map);
	
}
