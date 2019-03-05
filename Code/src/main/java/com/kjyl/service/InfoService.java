package com.kjyl.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import com.kjyl.pojo.Info;
import com.kjyl.dao.InfoMapper;

/**
 * <p>Service class</p>
 * @author sheryl 自动生成器
 * @version 1.00
 */
@Service
@CacheConfig(cacheNames="InfoCache") 
@Transactional(propagation=Propagation.REQUIRED, readOnly=false, rollbackFor=Exception.class)
public class InfoService {
   
    @Autowired
	private InfoMapper mapper;

	@CachePut(key="#p0.Id")  
	@CacheEvict(value = "InfoCache", allEntries = true)
	public Info Insert(Info obj){
		mapper.Insert(obj);
		return mapper.SearchBySpecial(obj.getId());
	}

	@CachePut(key="#p0.Id")  
	@CacheEvict(value = "InfoCache", allEntries = true)
	public Info Modify(Info obj){
		mapper.Modify(obj);
		return mapper.SearchBySpecial(obj.getId());
	}

	@CachePut(key="#p0")  
	@CacheEvict(value = "InfoCache", allEntries = true)
	public Info RemoveBySpecial(String Id){
		mapper.RemoveBySpecial(Id);
		return mapper.SearchBySpecial(Id);
	}

	@CachePut(key="#p0")  
	@CacheEvict(value = "InfoCache", allEntries = true)
	public Info RecoverBySpecial(String Id){
		mapper.RecoverBySpecial(Id);
		return mapper.SearchBySpecial(Id);
	}

	@CacheEvict(value = {"InfoCache", "InfoCache"},allEntries = true)
	public int RemoveByCondition(Map<String,Object> mapSearch){
		return mapper.RemoveByCondition(mapSearch);
	}

	@CacheEvict(value = {"InfoCache", "InfoCache"},allEntries = true)
	public int RecoverByCondition(Map<String,Object> mapSearch){
		return mapper.RecoverByCondition(mapSearch);
	}
	
	@Cacheable(value = "InfoCache", key="'Info_'+#p0")
	public Info SearchBySpecial(String Id){
		return mapper.SearchBySpecial(Id);
	}

	@Cacheable(keyGenerator = "keyGenerator")
	public List<Info> SearchByCondition(Map<String,Object> mapSearch){
		return mapper.SearchByCondition(mapSearch);
	}

	@Cacheable(keyGenerator = "keyGenerator")
	public int SearchData(Map<String,Object> mapSearch){
		return mapper.SearchData(mapSearch);
	}

	@Cacheable(keyGenerator = "keyGenerator")
	public PageInfo<Info> SearchPage(Map<String,Object> mapSearch, int pageNum, int pageSize){
		Page<Info> page = PageHelper.startPage(pageNum, pageSize);
		page.setOrderBy("Info_CreateTime desc");
		mapper.SearchByCondition(mapSearch);
		return page.toPageInfo();
	}

}
