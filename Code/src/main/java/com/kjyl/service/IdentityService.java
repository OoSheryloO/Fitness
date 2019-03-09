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

import com.kjyl.pojo.Identity;
import com.kjyl.dao.IdentityMapper;

/**
 * <p>Service class</p>
 * @author sheryl 自动生成器
 * @version 1.00
 */
@Service
//@CacheConfig(cacheNames="IdentityCache") 
@Transactional(propagation=Propagation.REQUIRED, readOnly=false, rollbackFor=Exception.class)
public class IdentityService {
   
    @Autowired
	private IdentityMapper mapper;
    
//	@Cacheable(value = "IdentityCache", key="'Identity_'+#p0") 
	public Identity SearchBySpecial(String Id){
		return mapper.SearchBySpecial(Id);
	}

//	@Cacheable(keyGenerator = "keyGenerator")
	public List<Identity> SearchByCondition(Map<String, Object> mapSearch){
		return mapper.SearchByCondition(mapSearch);
	}

//	@Cacheable(keyGenerator = "keyGenerator")
	public int SearchData(Map<String, Object> mapSearch){
		return mapper.SearchData(mapSearch);
	}

//	@Cacheable(keyGenerator = "keyGenerator")
	public PageInfo<Identity> SearchPage(Map<String, Object> mapSearch, int pageNum, int pageSize){
		Page<Identity> page = PageHelper.startPage(pageNum, pageSize);
		page.setOrderBy("Identity_CreateTime DESC");
		mapper.SearchByCondition(mapSearch);
		return page.toPageInfo();
	}

//	@CachePut(key="#p0.Id")  
//	@CacheEvict(value = "ReadIdentityCache", allEntries = true)
	public Identity Insert(Identity obj){
		mapper.Insert(obj);
		return mapper.SearchBySpecial(obj.getId());
	}

//	@CachePut(key="#p0.Id")  
//	@CacheEvict(value = "ReadIdentityCache", allEntries = true)
	public Identity Modify(Identity obj){
		mapper.Modify(obj);
		return mapper.SearchBySpecial(obj.getId());
	}

//	@CachePut(key="#p0")  
//	@CacheEvict(value = "ReadIdentityCache", allEntries = true)
	public Identity RemoveBySpecial(String Id){
		mapper.RemoveBySpecial(Id);
		return mapper.SearchBySpecial(Id);
	}

//	@CachePut(key="#p0")  
//	@CacheEvict(value = "ReadIdentityCache", allEntries = true)
	public Identity RecoverBySpecial(String Id){
		mapper.RecoverBySpecial(Id);
		return mapper.SearchBySpecial(Id);
	}

//	@CacheEvict(value = {"ReadIdentityCache", "IdentityCache"},allEntries = true)
	public int RemoveByCondition(Map<String, Object> mapSearch){
		return mapper.RemoveByCondition(mapSearch);
	}

//	@CacheEvict(value = {"ReadIdentityCache", "IdentityCache"},allEntries = true)
	public int RecoverByCondition(Map<String, Object> mapSearch){
		return mapper.RecoverByCondition(mapSearch);
	}

}
