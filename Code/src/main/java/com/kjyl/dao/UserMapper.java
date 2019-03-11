package com.kjyl.dao;

import java.util.Map;

import com.kjyl.bean.GymDataBean;
import com.kjyl.config.Mybaties.MyMapper;
import com.kjyl.pojo.User;

/**
 * <p> Mapper Class</p>
 * @author sheryl
 * 
 */
public interface UserMapper extends MyMapper<User>{
	
	GymDataBean SearchGymDataByCondition(Map<String, Object> map);

}
