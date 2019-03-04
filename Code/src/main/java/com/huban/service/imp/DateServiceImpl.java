package com.huban.service.imp;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.huban.dao.DateMapper;
import com.huban.pojo.Date;
import com.huban.service.DateService;

@Service("dateService")
public class DateServiceImpl implements DateService{
	@Resource
	private DateMapper mapper;

	@Override
	public int addmessage(Date date) {
		// TODO Auto-generated method stub
		return mapper.addmessage(date);
	}

	@Override
	public int readbillcount(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return mapper.readbillcount(map);
	}

	@Override
	public List<Date> readbill(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return mapper.readbill(map);
	}

	@Override
	public List<Date> readmore(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return mapper.readmore(map);
	}

	@Override
	public List<Date> allread(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return mapper.allread(map);
	}

	@Override
	public List<Date> queryList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return mapper.queryList(map);
	}

	@Override
	public int querycount(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return mapper.querycount(map);
	}

	@Override
	public List<Long> ReadBillCountTop() {
		// TODO Auto-generated method stub
		return mapper.ReadBillCountTop();
	}

	@Override
	public List<Long> ranklist(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return mapper.ranklist(map);
	}

	@Override
	public List<Integer> ranklistcount(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return mapper.ranklistcount(map);
	}

	@Override
	public int QueryCountById(Long userId) {
		// TODO Auto-generated method stub
		return mapper.QueryCountById(userId);
	}

}
