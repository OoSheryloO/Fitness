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

import com.kjyl.pojo.Picture;
import com.kjyl.dao.PictureMapper;

/**
 * <p>Service class</p>
 * @author sheryl 自动生成器
 * @version 1.00
 */
@Service
//@CacheConfig(cacheNames="PictureCache") 
@Transactional(propagation=Propagation.REQUIRED, readOnly=false, rollbackFor=Exception.class)
public class PictureService {
   
    @Autowired
	private PictureMapper mapper;
    
    public String SearchUrlBySpecial(String Id){
		return mapper.SearchUrlBySpecial(Id);
	}
    
    public int RemoveBySpecialLogicId(String id) {
    	return mapper.RemoveBySpecialLogicId(id);
    }
    
//	@Cacheable(value = "PictureCache", key="'Picture_'+#p0") 
	public Picture SearchBySpecial(String Id){
		return mapper.SearchBySpecial(Id);
	}
	
	public Picture SearchByModel(Picture model){
		return mapper.SearchByModel(model);
	}

//	@Cacheable(keyGenerator = "keyGenerator")
	public List<Picture> SearchByCondition(Map<String, Object> mapSearch){
		return mapper.SearchByCondition(mapSearch);
	}

//	@Cacheable(keyGenerator = "keyGenerator")
	public int SearchData(Map<String, Object> mapSearch){
		return mapper.SearchData(mapSearch);
	}

//	@Cacheable(keyGenerator = "keyGenerator")
	public PageInfo<Picture> SearchPage(Map<String, Object> mapSearch, int pageNum, int pageSize){
		Page<Picture> page = PageHelper.startPage(pageNum, pageSize);
		page.setOrderBy("Picture_CreateTime DESC");
		mapper.SearchByCondition(mapSearch);
		return page.toPageInfo();
	}

//	@CachePut(key="#p0.Id")  
//	@CacheEvict(value = "ReadPictureCache", allEntries = true)
	public Picture Insert(Picture obj){
		mapper.Insert(obj);
		return mapper.SearchBySpecial(obj.getId());
	}

//	@CachePut(key="#p0.Id")  
//	@CacheEvict(value = "ReadPictureCache", allEntries = true)
	public Picture Modify(Picture obj){
		mapper.Modify(obj);
		return mapper.SearchBySpecial(obj.getId());
	}

//	@CachePut(key="#p0")  
//	@CacheEvict(value = "ReadPictureCache", allEntries = true)
	public Picture RemoveBySpecial(String Id){
		mapper.RemoveBySpecial(Id);
		return mapper.SearchBySpecial(Id);
	}

//	@CachePut(key="#p0")  
//	@CacheEvict(value = "ReadPictureCache", allEntries = true)
	public Picture RecoverBySpecial(String Id){
		mapper.RecoverBySpecial(Id);
		return mapper.SearchBySpecial(Id);
	}

//	@CacheEvict(value = {"ReadPictureCache", "PictureCache"},allEntries = true)
	public int RemoveByCondition(Map<String, Object> mapSearch){
		return mapper.RemoveByCondition(mapSearch);
	}

//	@CacheEvict(value = {"ReadPictureCache", "PictureCache"},allEntries = true)
	public int RecoverByCondition(Map<String, Object> mapSearch){
		return mapper.RecoverByCondition(mapSearch);
	}

}
