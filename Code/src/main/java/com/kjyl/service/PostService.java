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

import com.kjyl.pojo.Post;
import com.kjyl.dao.PostMapper;

/**
 * <p>Service class</p>
 * @author sheryl 自动生成器
 * @version 1.00
 */
@Service
@CacheConfig(cacheNames="PostCache") 
@Transactional(propagation=Propagation.REQUIRED, readOnly=false, rollbackFor=Exception.class)
public class PostService {
   
    @Autowired
	private PostMapper WriteMapper;

    @Autowired
	private ReadPostMapper ReadMapper;

	@CachePut(key="#p0.Id")  
	@CacheEvict(value = "ReadPostCache", allEntries = true)
	public Post Insert(Post obj){
		WriteMapper.Insert(obj);
		return ReadMapper.SearchBySpecial(obj.getId());
	}

	@CachePut(key="#p0.Id")  
	@CacheEvict(value = "ReadPostCache", allEntries = true)
	public Post Modify(Post obj){
		WriteMapper.Modify(obj);
		return ReadMapper.SearchBySpecial(obj.getId());
	}

	@CachePut(key="#p0")  
	@CacheEvict(value = "ReadPostCache", allEntries = true)
	public Post RemoveBySpecial(String Id){
		WriteMapper.RemoveBySpecial(Id);
		return ReadMapper.SearchBySpecial(Id);
	}

	@CachePut(key="#p0")  
	@CacheEvict(value = "ReadPostCache", allEntries = true)
	public Post RecoverBySpecial(String Id){
		WriteMapper.RecoverBySpecial(Id);
		return ReadMapper.SearchBySpecial(Id);
	}

	@CacheEvict(value = {"ReadPostCache", "PostCache"},allEntries = true)
	public int RemoveByCondition(Map<String,Object> mapSearch){
		return WriteMapper.RemoveByCondition(mapSearch);
	}

	@CacheEvict(value = {"ReadPostCache", "PostCache"},allEntries = true)
	public int RecoverByCondition(Map<String,Object> mapSearch){
		return WriteMapper.RecoverByCondition(mapSearch);
	}

}
