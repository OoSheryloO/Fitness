package com.kjyl.dao;

import com.kjyl.config.Mybaties.MyMapper;
import com.kjyl.pojo.Dict;

/**
 * <p> Mapper Class</p>
 * @author sheryl
 * 
 */
public interface DictMapper extends MyMapper<Dict>{
	Dict SearchByModel(Dict model);
}
