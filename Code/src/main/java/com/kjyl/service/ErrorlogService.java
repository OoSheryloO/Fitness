package com.kjyl.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import java.util.Map;

import com.kjyl.pojo.Errorlog;
import com.kjyl.dao.ErrorlogMapper;

/**
 * <p>Service class</p>
 * @author sheryl 自动生成器
 * @version 1.00
 */
@Service
@CacheConfig(cacheNames="ErrorlogCache") 
@Transactional(propagation=Propagation.REQUIRED, readOnly=false, rollbackFor=Exception.class)
public class ErrorlogService {
   
    @Autowired
	private ErrorlogMapper WriteMapper;

    @Autowired
	private ReadErrorlogMapper ReadMapper;

	@CachePut(key="#p0.Id")  
	@CacheEvict(value = "ReadErrorlogCache", allEntries = true)
	public Errorlog Insert(Errorlog obj){
		WriteMapper.Insert(obj);
		return ReadMapper.SearchBySpecial(obj.getId());
	}

	@CachePut(key="#p0.Id")  
	@CacheEvict(value = "ReadErrorlogCache", allEntries = true)
	public Errorlog Modify(Errorlog obj){
		WriteMapper.Modify(obj);
		return ReadMapper.SearchBySpecial(obj.getId());
	}

	@CachePut(key="#p0")  
	@CacheEvict(value = "ReadErrorlogCache", allEntries = true)
	public Errorlog RemoveBySpecial(String Id){
		WriteMapper.RemoveBySpecial(Id);
		return ReadMapper.SearchBySpecial(Id);
	}

	@CachePut(key="#p0")  
	@CacheEvict(value = "ReadErrorlogCache", allEntries = true)
	public Errorlog RecoverBySpecial(String Id){
		WriteMapper.RecoverBySpecial(Id);
		return ReadMapper.SearchBySpecial(Id);
	}

	@CacheEvict(value = {"ReadErrorlogCache", "ErrorlogCache"},allEntries = true)
	public int RemoveByCondition(Map<String,Object> mapSearch){
		return WriteMapper.RemoveByCondition(mapSearch);
	}

	@CacheEvict(value = {"ReadErrorlogCache", "ErrorlogCache"},allEntries = true)
	public int RecoverByCondition(Map<String,Object> mapSearch){
		return WriteMapper.RecoverByCondition(mapSearch);
	}

}
