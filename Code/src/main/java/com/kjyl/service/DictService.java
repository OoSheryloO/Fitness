package com.kjyl.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//import org.springframework.cache.annotation.CacheConfig;
//import org.springframework.cache.annotation.CacheEvict;
//import org.springframework.cache.annotation.CachePut;
//import org.springframework.cache.annotation.Cacheable;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

import com.kjyl.pojo.Dict;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kjyl.dao.DictMapper;

/**
 * <p>Service class</p>
 * @author sheryl 自动生成器
 * @version 1.00
 */
@Service
//@CacheConfig(cacheNames="DictCache") 
@Transactional(propagation=Propagation.REQUIRED, readOnly=false, rollbackFor=Exception.class)
public class DictService {
   
    @Autowired
	private DictMapper DictMapper;

//	@CachePut(key="#p0.Id")  
//	@CacheEvict(value = "ReadDictCache", allEntries = true)
	public Dict Insert(Dict obj){
		DictMapper.Insert(obj);
		return DictMapper.SearchBySpecial(obj.getId());
	}

//	@CachePut(key="#p0.Id")  
//	@CacheEvict(value = "ReadDictCache", allEntries = true)
	public Dict Modify(Dict obj){
		DictMapper.Modify(obj);
		return DictMapper.SearchBySpecial(obj.getId());
	}

//	@CachePut(key="#p0")  
//	@CacheEvict(value = "ReadDictCache", allEntries = true)
	public Dict RemoveBySpecial(String Id){
		DictMapper.RemoveBySpecial(Id);
		return DictMapper.SearchBySpecial(Id);
	}

//	@CachePut(key="#p0")  
//	@CacheEvict(value = "ReadDictCache", allEntries = true)
	public Dict RecoverBySpecial(String Id){
		DictMapper.RecoverBySpecial(Id);
		return DictMapper.SearchBySpecial(Id);
	}

//	@CacheEvict(value = {"ReadDictCache", "DictCache"},allEntries = true)
	public int RemoveByCondition(Map<String, Object> mapSearch){
		return DictMapper.RemoveByCondition(mapSearch);
	}

//	@CacheEvict(value = {"ReadDictCache", "DictCache"},allEntries = true)
	public int RecoverByCondition(Map<String, Object> mapSearch){
		return DictMapper.RecoverByCondition(mapSearch);
	}
	
//	@Cacheable(value = "DictCache", key="'Dict_'+#p0") 
	public Dict SearchBySpecial(String Id){
		return DictMapper.SearchBySpecial(Id);
	}

//	@Cacheable(keyGenerator = "keyGenerator")
	public List<Dict> SearchByCondition(Map<String, Object> mapSearch){
		return DictMapper.SearchByCondition(mapSearch);
	}

//	@Cacheable(keyGenerator = "keyGenerator")
	public int SearchData(Map<String, Object> mapSearch){
		return DictMapper.SearchData(mapSearch);
	}

//	@Cacheable(keyGenerator = "keyGenerator")
	public PageInfo<Dict> SearchPage(Map<String, Object> mapSearch, int pageNum, int pageSize){
		Page<Dict> page = PageHelper.startPage(pageNum, pageSize);
		page.setOrderBy("Dict_CreateTime DESC");
		DictMapper.SearchByCondition(mapSearch);
		return page.toPageInfo();
	}

}
