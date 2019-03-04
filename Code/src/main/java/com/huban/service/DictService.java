package com.huban.service;

import java.util.List;
import java.util.Map;

import com.huban.pojo.Dict;

public interface DictService {
	
	public int deleteByPrimaryKey(Long dictId);

    public int insert(Dict record);

    public int insertSelective(Dict record);

    public Dict selectByPrimaryKey(Long dictId);

    public int updateByPrimaryKeySelective(Dict record);

    public int updateByPrimaryKey(Dict record);
    
    public List<Dict> querylevel(Integer type);
    
    public List<Dict> SearchLstDict(Map<String, Object> map);
    
	public String queryvalue(Integer level);
	
	public int querycount();
	
	public int ModifyMessage(Dict object);
}
