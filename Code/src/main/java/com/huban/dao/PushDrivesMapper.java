package com.huban.dao;

import java.util.List;
import java.util.Map;

import com.huban.pojo.PushDrives;

public interface PushDrivesMapper {
    int deleteByPrimaryKey(Long pushdriveId);

    int insert(PushDrives record);

    int insertSelective(PushDrives record);

    PushDrives selectByPrimaryKey(Long pushdriveId);

    int updateByPrimaryKeySelective(PushDrives record);

    int updateByPrimaryKey(PushDrives record);
    
    /* by Sheryl */
    
    int AddNewMessage(PushDrives record);
    
    List<PushDrives> QueryLstPushDrives(Map<String, Object> map);
    
    int UpdateMessage(PushDrives record);
}