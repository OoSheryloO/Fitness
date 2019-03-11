package com.kjyl.dao;

import java.util.List;
import java.util.Map;

import com.kjyl.bean.ClockRankBean;
import com.kjyl.config.Mybaties.MyMapper;
import com.kjyl.pojo.Clock;

/**
 * <p> Mapper Class</p>
 * @author sheryl
 * 
 */
public interface ClockMapper extends MyMapper<Clock>{
	
	List<ClockRankBean> SearchRankByCondition(Map<String, Object> map);

}
