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

import com.kjyl.pojo.Admin;
import com.kjyl.dao.AdminMapper;

/**
 * <p>Service class</p>
 * @author sheryl 自动生成器
 * @version 1.00
 */
@Service
@CacheConfig(cacheNames="AdminCache") 
@Transactional(propagation=Propagation.REQUIRED, readOnly=false, rollbackFor=Exception.class)
public class AdminService {
   
    @Autowired
	private AdminMapper mapper;

	@CachePut(key="#p0.Id")  
	@CacheEvict(value = "ReadAdminCache", allEntries = true)
	public Admin Insert(Admin obj){
		mapper.Insert(obj);
		return mapper.SearchBySpecial(obj.getId());
	}

	@CachePut(key="#p0.Id")  
	@CacheEvict(value = "ReadAdminCache", allEntries = true)
	public Admin Modify(Admin obj){
		mapper.Modify(obj);
		return mapper.SearchBySpecial(obj.getId());
	}

	@CachePut(key="#p0")  
	@CacheEvict(value = "ReadAdminCache", allEntries = true)
	public Admin RemoveBySpecial(String Id){
		mapper.RemoveBySpecial(Id);
		return mapper.SearchBySpecial(Id);
	}

	@CachePut(key="#p0")  
	@CacheEvict(value = "ReadAdminCache", allEntries = true)
	public Admin RecoverBySpecial(String Id){
		mapper.RecoverBySpecial(Id);
		return mapper.SearchBySpecial(Id);
	}

	@CacheEvict(value = {"ReadAdminCache", "AdminCache"},allEntries = true)
	public int RemoveByCondition(Map<String,Object> mapSearch){
		return mapper.RemoveByCondition(mapSearch);
	}

	@CacheEvict(value = {"ReadAdminCache", "AdminCache"},allEntries = true)
	public int RecoverByCondition(Map<String,Object> mapSearch){
		return mapper.RecoverByCondition(mapSearch);
	}

}
