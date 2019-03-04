package com.huban.service.imp;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.huban.dao.DictMapper;
import com.huban.pojo.Dict;
import com.huban.service.DictService;

@Service("dictService")
public class DictServiceImpl implements DictService{
	@Resource
	private DictMapper mapper;

	@Override
	public int deleteByPrimaryKey(Long dictId) {
		// TODO Auto-generated method stub
		return mapper.deleteByPrimaryKey(dictId);
	}

	@Override
	public int insert(Dict record) {
		// TODO Auto-generated method stub
		return mapper.insert(record);
	}

	@Override
	public int insertSelective(Dict record) {
		// TODO Auto-generated method stub
		return mapper.insertSelective(record);
	}

	@Override
	public Dict selectByPrimaryKey(Long dictId) {
		// TODO Auto-generated method stub
		return mapper.selectByPrimaryKey(dictId);
	}

	@Override
	public int updateByPrimaryKeySelective(Dict record) {
		// TODO Auto-generated method stub
		return mapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(Dict record) {
		// TODO Auto-generated method stub
		return mapper.updateByPrimaryKey(record);
	}

	@Override
	public List<Dict> querylevel(Integer type) {
		// TODO Auto-generated method stub
		return mapper.querylevel(type);
	}

	@Override
	public String queryvalue(Integer level) {
		// TODO Auto-generated method stub
		return mapper.queryvalue(level);
	}

	@Override
	public int querycount() {
		// TODO Auto-generated method stub
		return mapper.querycount();
	}

	/* (non - Javadoc)
	* @param map
	* @return
	* @see com.huban.service.DictService#SearchLstDict(java.util.Map)
	*/
	@Override
	public List<Dict> SearchLstDict(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return mapper.SearchLstDict(map);
	}

	/* (non - Javadoc)
	* @param object
	* @return
	* @see com.huban.service.DictService#ModifyMessage(com.huban.pojo.Dict)
	*/
	@Override
	public int ModifyMessage(Dict object) {
		// TODO Auto-generated method stub
		return mapper.ModifyMessage(object);
	}

	
	
}
