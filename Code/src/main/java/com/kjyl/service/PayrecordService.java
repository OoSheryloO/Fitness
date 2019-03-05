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

import com.kjyl.pojo.Payrecord;
import com.kjyl.dao.PayrecordMapper;

/**
 * <p>Service class</p>
 * @author sheryl 自动生成器
 * @version 1.00
 */
@Service
@CacheConfig(cacheNames="PayrecordCache") 
@Transactional(propagation=Propagation.REQUIRED, readOnly=false, rollbackFor=Exception.class)
public class PayrecordService {
   
    @Autowired
	private PayrecordMapper mapper;

	@CachePut(key="#p0.Id")  
	@CacheEvict(value = "PayrecordCache", allEntries = true)
	public Payrecord Insert(Payrecord obj){
		mapper.Insert(obj);
		return mapper.SearchBySpecial(obj.getId());
	}

	@CachePut(key="#p0.Id")  
	@CacheEvict(value = "PayrecordCache", allEntries = true)
	public Payrecord Modify(Payrecord obj){
		mapper.Modify(obj);
		return mapper.SearchBySpecial(obj.getId());
	}

	@CachePut(key="#p0")  
	@CacheEvict(value = "PayrecordCache", allEntries = true)
	public Payrecord RemoveBySpecial(String Id){
		mapper.RemoveBySpecial(Id);
		return mapper.SearchBySpecial(Id);
	}

	@CachePut(key="#p0")  
	@CacheEvict(value = "PayrecordCache", allEntries = true)
	public Payrecord RecoverBySpecial(String Id){
		mapper.RecoverBySpecial(Id);
		return mapper.SearchBySpecial(Id);
	}

	@CacheEvict(value = {"PayrecordCache", "PayrecordCache"},allEntries = true)
	public int RemoveByCondition(Map<String,Object> mapSearch){
		return mapper.RemoveByCondition(mapSearch);
	}

	@CacheEvict(value = {"PayrecordCache", "PayrecordCache"},allEntries = true)
	public int RecoverByCondition(Map<String,Object> mapSearch){
		return mapper.RecoverByCondition(mapSearch);
	}

	@Cacheable(value = "PayrecordCache", key="'Payrecord_'+#p0") 
	public Payrecord SearchBySpecial(String Id){
		return mapper.SearchBySpecial(Id);
	}

	@Cacheable(keyGenerator = "keyGenerator")
	public List<Payrecord> SearchByCondition(Map<String,Object> mapSearch){
		return mapper.SearchByCondition(mapSearch);
	}

	@Cacheable(keyGenerator = "keyGenerator")
	public int SearchData(Map<String,Object> mapSearch){
		return mapper.SearchData(mapSearch);
	}

	@Cacheable(keyGenerator = "keyGenerator")
	public PageInfo<Payrecord> SearchPage(Map<String,Object> mapSearch, int pageNum, int pageSize){
		Page<Payrecord> page = PageHelper.startPage(pageNum, pageSize);
		page.setOrderBy("Payrecord_CreateTime desc");
		mapper.SearchByCondition(mapSearch);
		return page.toPageInfo();
	}
	
}
