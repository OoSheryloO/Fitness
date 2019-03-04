/*
 * 2018/01/04 huban Creating 
 *
 * (c) Copyright huban Inc. All rights reserved.
 */
package com.huban.service.imp;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.huban.dao.NewMapper;
import com.huban.pojo.New;
import com.huban.service.NewService;

/**
 * <p>Service class。</p>
 *
 * @author huban
 * @version 1.00
 */
 @Service("newService")
public class NewServiceImpl implements NewService{

    /**
     * <p>default constants</p>
     */
     @Autowired
	private NewMapper mapper;
    
  /**
	 * <p>根据ID查询记录</p>
	 *
	 * @author huban
	 * @version 1.00
	 */
     @Override
    public New FindByNewsId(long id) {
		return mapper.FindByNewsId(id);
	}

	/**
	 * <p>根据条件查询记录</p>
	 *
	 * @author huban
	 * @version 1.00
	 */
     @Override
    public List<New> FindNewssByCondition(Map<String,Object> params) {
		return mapper.FindNewssByCondition(params);
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
    public int addNews(New object){
		return mapper.addNews(object);
	}
	
	/**
	 * <p>修改记录</p>
	 *
	 * @author huban
	 * @version 1.00
	 */
     @Override
    public int UpdateNews(New object){
		return mapper.UpdateNews(object);
	}
    
    /**
	 * <p>根据ID删除记录</p>
	 *
	 * @author huban
	 * @version 1.00
	 */
     @Override
    public int DeleteNewsById(Map<String,Object> params){
		return mapper.DeleteNewsById(params);
	}
	
	/**
	 * <p>根据ID恢复记录</p>
	 *
	 * @author huban
	 * @version 1.00
	 */
     @Override
    public int EnabledNewsById(Map<String,Object> params){
		return mapper.EnabledNewsById(params);
	}
}
