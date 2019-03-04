package com.huban.service.imp;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.huban.dao.PayrecordsMapper;
import com.huban.pojo.Payrecords;
import com.huban.service.PayrecordsService;


@Service("payrecordsService")
public class PayrecordsServiceImpl implements PayrecordsService{

	@Resource
	private PayrecordsMapper mapper;

	@Override
	public List<Payrecords> payMessage(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return mapper.payMessage(map);
	}

	@Override
	public int addPayMessage(Payrecords payrecords) {
		// TODO Auto-generated method stub
		return mapper.addPayMessage(payrecords);
	}

	@Override
	public int payVideo(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return mapper.payVideo(map);
	}

	@Override
	public int embodyquery(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return mapper.embodyquery(map);
	}

}
