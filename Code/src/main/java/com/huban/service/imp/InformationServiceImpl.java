/*
 * 2018/01/04 huban Creating 
 *
 * (c) Copyright huban Inc. All rights reserved.
 */
package com.huban.service.imp;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huban.dao.InformationMapper;
import com.huban.pojo.Information;
import com.huban.service.InformationService;

/**
 * <p>Service class。</p>
 *
 * @author huban
 * @version 1.00
 */
 @Service("informationService")
public class InformationServiceImpl implements InformationService{

    /**
     * <p>default constants</p>
     */
     @Autowired
	private InformationMapper mapper;
    
  /**
	 * <p>根据ID查询记录</p>
	 *
	 * @author huban
	 * @version 1.00
	 */
     @Override
    public Information FindByInformationId(long id) {
		return mapper.FindByInformationId(id);
	}

	/**
	 * <p>根据条件查询记录</p>
	 *
	 * @author huban
	 * @version 1.00
	 */
     @Override
    public List<Information> FindInformationsByCondition(Map<String,Object> params) {
		return mapper.FindInformationsByCondition(params);
	}

	/**
	 * <p>根据条件查询记录数</p>
	 *
	 * @author huban
	 * @version 1.00
	 */
     @Override
    public int getCountByCondition(Map<String,Object> params) {
		return mapper.getCountByCondition(params);
	}

	/**
	 * <p>添加记录</p>
	 *
	 * @author huban
	 * @version 1.00
	 */
     @Override
    public int addInformation(Information object){
		return mapper.addInformation(object);
	}
	
	/**
	 * <p>修改记录</p>
	 *
	 * @author huban
	 * @version 1.00
	 */
     @Override
    public int UpdateInformation(Information object){
		return mapper.UpdateInformation(object);
	}
    
    /**
	 * <p>根据ID删除记录</p>
	 *
	 * @author huban
	 * @version 1.00
	 */
     @Override
    public int DeleteInformationById(Map<String,Object> params){
		return mapper.DeleteInformationById(params);
	}
	
	/**
	 * <p>根据ID恢复记录</p>
	 *
	 * @author huban
	 * @version 1.00
	 */
     @Override
    public int EnabledInformationById(Map<String,Object> params){
		return mapper.EnabledInformationById(params);
	}
}
