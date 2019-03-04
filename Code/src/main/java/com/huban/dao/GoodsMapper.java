package com.huban.dao;

import java.util.Map;

import com.huban.pojo.Goods;
import java.util.List;

public interface GoodsMapper {
    int deleteByPrimaryKey(Long goodsId);

    int insert(Goods record);

    int insertSelective(Goods record);

    Goods selectByPrimaryKey(Long goodsId);

    int updateByPrimaryKeySelective(Goods record);

    int updateByPrimaryKeyWithBLOBs(Goods record);

    int updateByPrimaryKey(Goods record);
    
    /* by Sheryl */
    int IncreaseMessaga(Goods record);
    
    List<Goods> LstSearchByCondition(Map<String, Object> map);
    
    Goods SearchByCondition(Map<String, Object> map);
    
    int ModifyMessage(Goods record);
}