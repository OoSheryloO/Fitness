package com.huban.service;

import java.util.List;
import java.util.Map;

import com.huban.pojo.PushDrives;

public interface PushDrivesService {
	
	public int AddNewMessage(PushDrives record);
	
	public List<PushDrives> QueryLstPushDrives(Map<String, Object> map);
	
	public int UpdateMessage(PushDrives record);
	
}
