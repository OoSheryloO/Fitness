package com.kjyl.dao;

import java.util.List;

import com.kjyl.config.Mybaties.MyMapper;
import com.kjyl.pojo.Goods;

/**
 * <p> Mapper Class</p>
 * @author sheryl
 * 
 */
public interface GoodsMapper extends MyMapper<Goods>{
	List<Goods> SearchBySpecialType(Integer type);

}
