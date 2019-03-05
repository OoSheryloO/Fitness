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

import com.kjyl.pojo.Event;
import com.kjyl.dao.EventMapper;

/**
 * <p>Service class</p>
 * @author sheryl 自动生成器
 * @version 1.00
 */
@Service
@CacheConfig(cacheNames="EventCache") 
@Transactional(propagation=Propagation.REQUIRED, readOnly=false, rollbackFor=Exception.class)
public class EventService {
   
    @Autowired
	private EventMapper mapper;

	@CachePut(key="#p0.Id")  
	@CacheEvict(value = "EventCache", allEntries = true)
	public Event Insert(Event obj){
		mapper.Insert(obj);
		return mapper.SearchBySpecial(obj.getId());
	}

	@CachePut(key="#p0.Id")  
	@CacheEvict(value = "EventCache", allEntries = true)
	public Event Modify(Event obj){
		mapper.Modify(obj);
		return mapper.SearchBySpecial(obj.getId());
	}

	@CachePut(key="#p0")  
	@CacheEvict(value = "EventCache", allEntries = true)
	public Event RemoveBySpecial(String Id){
		mapper.RemoveBySpecial(Id);
		return mapper.SearchBySpecial(Id);
	}

	@CachePut(key="#p0")  
	@CacheEvict(value = "EventCache", allEntries = true)
	public Event RecoverBySpecial(String Id){
		mapper.RecoverBySpecial(Id);
		return mapper.SearchBySpecial(Id);
	}

	@CacheEvict(value = {"EventCache", "EventCache"},allEntries = true)
	public int RemoveByCondition(Map<String,Object> mapSearch){
		return mapper.RemoveByCondition(mapSearch);
	}

	@CacheEvict(value = {"EventCache", "EventCache"},allEntries = true)
	public int RecoverByCondition(Map<String,Object> mapSearch){
		return mapper.RecoverByCondition(mapSearch);
	}

	@Cacheable(value = "EventCache", key="'Event_'+#p0") 
	public Event SearchBySpecial(String Id){
		return mapper.SearchBySpecial(Id);
	}

	@Cacheable(keyGenerator = "keyGenerator")
	public List<Event> SearchByCondition(Map<String,Object> mapSearch){
		return mapper.SearchByCondition(mapSearch);
	}

	@Cacheable(keyGenerator = "keyGenerator")
	public int SearchData(Map<String,Object> mapSearch){
		return mapper.SearchData(mapSearch);
	}

	@Cacheable(keyGenerator = "keyGenerator")
	public PageInfo<Event> SearchPage(Map<String,Object> mapSearch, int pageNum, int pageSize){
		Page<Event> page = PageHelper.startPage(pageNum, pageSize);
		page.setOrderBy("Event_CreateTime desc");
		mapper.SearchByCondition(mapSearch);
		return page.toPageInfo();
	}
	
}
