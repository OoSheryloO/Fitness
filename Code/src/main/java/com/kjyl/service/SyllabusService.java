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

import com.kjyl.pojo.Syllabus;
import com.kjyl.dao.SyllabusMapper;

/**
 * <p>Service class</p>
 * @author sheryl 自动生成器
 * @version 1.00
 */
@Service
//@CacheConfig(cacheNames="SyllabusCache") 
@Transactional(propagation=Propagation.REQUIRED, readOnly=false, rollbackFor=Exception.class)
public class SyllabusService {
   
    @Autowired
	private SyllabusMapper mapper;
    
//	@Cacheable(value = "SyllabusCache", key="'Syllabus_'+#p0") 
	public Syllabus SearchBySpecial(String Id){
		return mapper.SearchBySpecial(Id);
	}

//	@Cacheable(keyGenerator = "keyGenerator")
	public List<Syllabus> SearchByCondition(Map<String, Object> mapSearch){
		return mapper.SearchByCondition(mapSearch);
	}

//	@Cacheable(keyGenerator = "keyGenerator")
	public int SearchData(Map<String, Object> mapSearch){
		return mapper.SearchData(mapSearch);
	}

//	@Cacheable(keyGenerator = "keyGenerator")
	public PageInfo<Syllabus> SearchPage(Map<String, Object> mapSearch, int pageNum, int pageSize){
		Page<Syllabus> page = PageHelper.startPage(pageNum, pageSize);
		page.setOrderBy("Syllabus_CreateTime DESC");
		mapper.SearchByCondition(mapSearch);
		return page.toPageInfo();
	}

//	@CachePut(key="#p0.Id")  
//	@CacheEvict(value = "ReadSyllabusCache", allEntries = true)
	public Syllabus Insert(Syllabus obj){
		mapper.Insert(obj);
		return mapper.SearchBySpecial(obj.getId());
	}

//	@CachePut(key="#p0.Id")  
//	@CacheEvict(value = "ReadSyllabusCache", allEntries = true)
	public Syllabus Modify(Syllabus obj){
		mapper.Modify(obj);
		return mapper.SearchBySpecial(obj.getId());
	}

//	@CachePut(key="#p0")  
//	@CacheEvict(value = "ReadSyllabusCache", allEntries = true)
	public Syllabus RemoveBySpecial(String Id){
		mapper.RemoveBySpecial(Id);
		return mapper.SearchBySpecial(Id);
	}

//	@CachePut(key="#p0")  
//	@CacheEvict(value = "ReadSyllabusCache", allEntries = true)
	public Syllabus RecoverBySpecial(String Id){
		mapper.RecoverBySpecial(Id);
		return mapper.SearchBySpecial(Id);
	}

//	@CacheEvict(value = {"ReadSyllabusCache", "SyllabusCache"},allEntries = true)
	public int RemoveByCondition(Map<String, Object> mapSearch){
		return mapper.RemoveByCondition(mapSearch);
	}

//	@CacheEvict(value = {"ReadSyllabusCache", "SyllabusCache"},allEntries = true)
	public int RecoverByCondition(Map<String, Object> mapSearch){
		return mapper.RecoverByCondition(mapSearch);
	}

}
