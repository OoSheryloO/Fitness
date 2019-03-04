package com.huban.service.imp;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.huban.dao.GoodsMapper;
import com.huban.dao.IdiomRecordsMapper;
import com.huban.pojo.Goods;
import com.huban.service.GoodsService;
import com.huban.service.IdiomService;

import java.util.List;

@Service("idiomService")
public class IdiomServiceImpl implements IdiomService{
     @Resource
     private IdiomRecordsMapper mapper;
	
}
