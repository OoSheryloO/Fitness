package com.huban.service;

import java.util.List;
import java.util.Map;

import com.huban.pojo.Devices;

public interface DevicesService {
	  
	public int addAPPmessage(Devices devices);
	
	public int QueryCountByUID(Map<String, Object> map);
    
    public int UpdateMessage(Devices record);
    
    public List<Devices> QueryLstDevices(Map<String, Object> map);
		
    public Devices QueryDevice(Map<String, Object> map);
}
