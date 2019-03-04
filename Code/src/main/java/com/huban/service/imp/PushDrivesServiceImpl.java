package com.huban.service.imp;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.huban.dao.PushDrivesMapper;
import com.huban.pojo.PushDrives;
import com.huban.service.PushDrivesService;

@Service("pushDrivesService")
public class PushDrivesServiceImpl implements PushDrivesService{
	@Resource
	private PushDrivesMapper mapper;

	@Override
	public int AddNewMessage(PushDrives record) {
		// TODO Auto-generated method stub
		return mapper.AddNewMessage(record);
	}

	@Override
	public List<PushDrives> QueryLstPushDrives(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return mapper.QueryLstPushDrives(map);
	}

	@Override
	public int UpdateMessage(PushDrives record) {
		// TODO Auto-generated method stub
		return mapper.UpdateMessage(record);
	}

}
