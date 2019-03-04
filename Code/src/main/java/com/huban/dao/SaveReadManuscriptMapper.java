package com.huban.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.huban.construct.LstDeptSave;
import com.huban.construct.ShowTaskModel;
import com.huban.pojo.SaveRead;

public interface SaveReadManuscriptMapper {
    int deleteByPrimaryKey(Long rsId);

    int insert(SaveRead record);

    int insertSelective(SaveRead record);

    SaveRead selectByPrimaryKey(Long rsId);

    int updateByPrimaryKeySelective(SaveRead record);

    int updateByPrimaryKey(SaveRead record);
    
    /* V2 by Sheryl */
    int AddNewManuscriptMessage(SaveRead record);
    
    int UpdateManuscriptMessageByRsId(SaveRead record);
    
    List<SaveRead> QueryLackMessage(Map<String, Object> map);
}