package com.kjyl.dao;

import java.util.List;

import com.kjyl.config.Mybaties.MyMapper;
import com.kjyl.pojo.Course;

/**
 * <p> Mapper Class</p>
 * @author sheryl
 * 
 */
public interface CourseMapper extends MyMapper<Course>{
	List<Course> SearchBySpecialRand();
}
