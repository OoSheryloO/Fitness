package com.huban.dao;

import java.util.List;
import java.util.Map;

import com.huban.pojo.Dict;

public interface DictMapper {
    int deleteByPrimaryKey(Long dictId);

    int insert(Dict record);

    int insertSelective(Dict record);

    Dict selectByPrimaryKey(Long dictId);

    int updateByPrimaryKeySelective(Dict record);

    int updateByPrimaryKey(Dict record);
    
    List<Dict> querylevel(Integer type);
    
    List<Dict> SearchLstDict(Map<String, Object> map);
    
    String queryvalue(Integer level);
    
    int querycount();
    
    int ModifyMessage(Dict object);
}