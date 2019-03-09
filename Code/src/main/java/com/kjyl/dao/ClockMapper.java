package com.kjyl.dao;

import java.util.List;
import java.util.Map;

import com.kjyl.bean.RankList;
import com.kjyl.config.Mybaties.MyMapper;
import com.kjyl.pojo.Clock;

/**
 * <p> Mapper Class</p>
 * @author sheryl
 * 
 */
public interface ClockMapper extends MyMapper<Clock>{
	
	List<RankList> SearchRankByCondition(Map<String, Object> map);

}
