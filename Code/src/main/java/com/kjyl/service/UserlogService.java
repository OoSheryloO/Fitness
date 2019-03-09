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

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import com.kjyl.pojo.Userlog;
import com.kjyl.dao.UserlogMapper;

/**
 * <p>Service class</p>
 * @author sheryl 自动生成器
 * @version 1.00
 */
@Service
//@CacheConfig(cacheNames="UserlogCache") 
@Transactional(propagation=Propagation.REQUIRED, readOnly=false, rollbackFor=Exception.class)
public class UserlogService {
   
    @Autowired
	private UserlogMapper mapper;
    
//	@Cacheable(value = "UserlogCache", key="'Userlog_'+#p0") 
	public Userlog SearchBySpecial(String Id){
		return mapper.SearchBySpecial(Id);
	}

//	@Cacheable(keyGenerator = "keyGenerator")
	public List<Userlog> SearchByCondition(Map<String, Object> mapSearch){
		return mapper.SearchByCondition(mapSearch);
	}

//	@Cacheable(keyGenerator = "keyGenerator")
	public int SearchData(Map<String, Object> mapSearch){
		return mapper.SearchData(mapSearch);
	}

//	@Cacheable(keyGenerator = "keyGenerator")
	public PageInfo<Userlog> SearchPage(Map<String, Object> mapSearch, int pageNum, int pageSize){
		Page<Userlog> page = PageHelper.startPage(pageNum, pageSize);
		page.setOrderBy("Userlog_CreateTime DESC");
		mapper.SearchByCondition(mapSearch);
		return page.toPageInfo();
	}

//	@CachePut(key="#p0.Id")  
//	@CacheEvict(value = "ReadUserlogCache", allEntries = true)
	public Userlog Insert(Userlog obj){
		mapper.Insert(obj);
		return mapper.SearchBySpecial(obj.getId());
	}

//	@CachePut(key="#p0.Id")  
//	@CacheEvict(value = "ReadUserlogCache", allEntries = true)
	public Userlog Modify(Userlog obj){
		mapper.Modify(obj);
		return mapper.SearchBySpecial(obj.getId());
	}

//	@CachePut(key="#p0")  
//	@CacheEvict(value = "ReadUserlogCache", allEntries = true)
	public Userlog RemoveBySpecial(String Id){
		mapper.RemoveBySpecial(Id);
		return mapper.SearchBySpecial(Id);
	}

//	@CachePut(key="#p0")  
//	@CacheEvict(value = "ReadUserlogCache", allEntries = true)
	public Userlog RecoverBySpecial(String Id){
		mapper.RecoverBySpecial(Id);
		return mapper.SearchBySpecial(Id);
	}

//	@CacheEvict(value = {"ReadUserlogCache", "UserlogCache"},allEntries = true)
	public int RemoveByCondition(Map<String, Object> mapSearch){
		return mapper.RemoveByCondition(mapSearch);
	}

//	@CacheEvict(value = {"ReadUserlogCache", "UserlogCache"},allEntries = true)
	public int RecoverByCondition(Map<String, Object> mapSearch){
		return mapper.RecoverByCondition(mapSearch);
	}

}
