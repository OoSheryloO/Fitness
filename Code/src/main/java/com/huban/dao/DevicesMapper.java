package com.huban.dao;

import java.util.List;
import java.util.Map;

import com.huban.pojo.Devices;

public interface DevicesMapper {
    int deleteByPrimaryKey(Long deviceId);

    int insert(Devices record);

    int insertSelective(Devices record);

    Devices selectByPrimaryKey(Long deviceId);

    int updateByPrimaryKeySelective(Devices record);

    int updateByPrimaryKey(Devices record);
    
    int addAPPmessage(Devices devices);
    
    /* V2 by Sheryl */
    int QueryCountByUID(Map<String, Object> map);
    
    int UpdateMessage(Devices record);
    
    List<Devices> QueryLstDevices(Map<String, Object> map);
    
    Devices QueryDevice(Map<String, Object> map);
}