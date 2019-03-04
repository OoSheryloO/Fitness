package com.huban.service.imp;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.huban.dao.GoodsMapper;
import com.huban.pojo.Goods;
import com.huban.service.GoodsService;
import java.util.List;

@Service("goodsService")
public class GoodsServiceImpl implements GoodsService{
     @Resource
     private GoodsMapper mapper;

	/* (non - Javadoc)
	* @param record
	* @return
	* @see com.huban.service.GoodsService#IncreaseMessaga(com.huban.pojo.Goods)
	*/
	@Override
	public int IncreaseMessaga(Goods record) {
		// TODO Auto-generated method stub
		return mapper.IncreaseMessaga(record);
	}

	/* (non - Javadoc)
	* @param map
	* @return
	* @see com.huban.service.GoodsService#SearchByCondition(com.sun.xml.internal.xsom.impl.scd.Iterators.Map)
	*/
	@Override
	public List<Goods> LstSearchByCondition(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return mapper.LstSearchByCondition(map);
	}

	/* (non - Javadoc)
	* @param map
	* @return
	* @see com.huban.service.GoodsService#SearchByCondition(java.util.Map)
	*/
	@Override
	public Goods SearchByCondition(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return mapper.SearchByCondition(map);
	}

	/* (non - Javadoc)
	* @param record
	* @return
	* @see com.huban.service.GoodsService#ModifyMessage(com.huban.pojo.Goods)
	*/
	@Override
	public int ModifyMessage(Goods record) {
		// TODO Auto-generated method stub
		return mapper.ModifyMessage(record);
	}
	
}
