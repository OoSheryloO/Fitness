package com.huban.service.imp;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.huban.dao.DevicesMapper;
import com.huban.pojo.Devices;
import com.huban.service.DevicesService;

@Service("devicesService")
public class DevicesServiceImpl implements DevicesService{
     @Resource
     private DevicesMapper mapper;
	/* (non-Javadoc)
	@see com.huban.service.ActivityService#queryList(java.util.Map)*/

	@Override
	public int addAPPmessage(Devices devices) {
		// TODO Auto-generated method stub
		return mapper.addAPPmessage(devices);
	}

	@Override
	public int QueryCountByUID(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return mapper.QueryCountByUID(map);
	}

	@Override
	public int UpdateMessage(Devices record) {
		// TODO Auto-generated method stub
		return mapper.UpdateMessage(record);
	}

	@Override
	public List<Devices> QueryLstDevices(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return mapper.QueryLstDevices(map);
	}

	/* (non - Javadoc)
	* @param map
	* @return
	* @see com.huban.service.DevicesService#QueryDevice(java.util.Map)
	*/
	@Override
	public Devices QueryDevice(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return mapper.QueryDevice(map);
	}
	 

}
