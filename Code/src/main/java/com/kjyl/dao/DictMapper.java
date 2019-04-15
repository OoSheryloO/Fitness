package com.kjyl.dao;

import java.util.List;
import java.util.Map;

import com.kjyl.bean.ClassificationRetrieval;
import com.kjyl.config.Mybaties.MyMapper;
import com.kjyl.pojo.Dict;

/**
 * <p> Mapper Class</p>
 * @author sheryl
 * 
 */
public interface DictMapper extends MyMapper<Dict>{
	Dict SearchByModel(Dict model);
	
	List<ClassificationRetrieval> SearchClassificationByCondition(Map<String, Object> map);
}
