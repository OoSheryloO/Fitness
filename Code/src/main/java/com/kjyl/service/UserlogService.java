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

import com.kjyl.pojo.Userlog;
import com.kjyl.dao.UserlogMapper;

/**
 * <p>Service class</p>
 * @author sheryl 自动生成器
 * @version 1.00
 */
@Service
@CacheConfig(cacheNames="UserlogCache") 
@Transactional(propagation=Propagation.REQUIRED, readOnly=false, rollbackFor=Exception.class)
public class UserlogService {
   
    @Autowired
	private UserlogMapper WriteMapper;

    @Autowired
	private ReadUserlogMapper ReadMapper;

	@CachePut(key="#p0.Id")  
	@CacheEvict(value = "ReadUserlogCache", allEntries = true)
	public Userlog Insert(Userlog obj){
		WriteMapper.Insert(obj);
		return ReadMapper.SearchBySpecial(obj.getId());
	}

	@CachePut(key="#p0.Id")  
	@CacheEvict(value = "ReadUserlogCache", allEntries = true)
	public Userlog Modify(Userlog obj){
		WriteMapper.Modify(obj);
		return ReadMapper.SearchBySpecial(obj.getId());
	}

	@CachePut(key="#p0")  
	@CacheEvict(value = "ReadUserlogCache", allEntries = true)
	public Userlog RemoveBySpecial(String Id){
		WriteMapper.RemoveBySpecial(Id);
		return ReadMapper.SearchBySpecial(Id);
	}

	@CachePut(key="#p0")  
	@CacheEvict(value = "ReadUserlogCache", allEntries = true)
	public Userlog RecoverBySpecial(String Id){
		WriteMapper.RecoverBySpecial(Id);
		return ReadMapper.SearchBySpecial(Id);
	}

	@CacheEvict(value = {"ReadUserlogCache", "UserlogCache"},allEntries = true)
	public int RemoveByCondition(Map<String,Object> mapSearch){
		return WriteMapper.RemoveByCondition(mapSearch);
	}

	@CacheEvict(value = {"ReadUserlogCache", "UserlogCache"},allEntries = true)
	public int RecoverByCondition(Map<String,Object> mapSearch){
		return WriteMapper.RecoverByCondition(mapSearch);
	}

}
