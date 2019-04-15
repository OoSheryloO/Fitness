package com.kjyl.dao;

import java.util.List;

import com.kjyl.config.Mybaties.MyMapper;
import com.kjyl.pojo.Card;

/**
 * <p> Mapper Class</p>
 * @author sheryl
 * 
 */
public interface CardMapper extends MyMapper<Card>{
	List<Card> SearchBySpecialType(Integer type);

}
