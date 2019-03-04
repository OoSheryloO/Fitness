package com.huban.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.huban.construct.LstDeptSave;
import com.huban.construct.ShowTaskModel;
import com.huban.pojo.SaveRead;

public interface SaveReadMapper {
    int deleteByPrimaryKey(Long rsId);

    int insert(SaveRead record);

    int insertSelective(SaveRead record);

    SaveRead selectByPrimaryKey(Long rsId);

    int updateByPrimaryKeySelective(SaveRead record);

    int updateByPrimaryKey(SaveRead record);
    
    /* V2 by Sheryl */
    int AddNewMessage(SaveRead record);
    
    int UpdateMessageByRsId(SaveRead record);
    
    List<SaveRead> QueryLackMessage(Map<String, Object> map);
    
    Map<String, Object> QuerySaveReadNumber(@Param(value="rsID")long rsID);
    
    List<SaveRead> QuerySaveReadRecord(Map<String, Object> map);
    
    long BaseQueryCount(Map<String, Object> map);
    
    List<LstDeptSave> QueryLstSaveReadDetail(Map<String, Object> map);
    List<LstDeptSave> QueryLstSaveReadForAgent(Map<String, Object> map);
    
    long QueryReadNumberByOneMonth(Map<String, Object> map);
    
    ShowTaskModel QueryShowTaskModel(Map<String, Object> map);
    
    long QueryReadCountByMonth(Map<String, Object> map);
}