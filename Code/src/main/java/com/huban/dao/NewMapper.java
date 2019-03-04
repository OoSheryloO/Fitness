/*
 * 2018/01/04 huban Creating
 *
 * (c) Copyright huban Inc. All rights reserved.
 */
package com.huban.dao;
import java.util.*;

import com.huban.pojo.New;

/**
 * <p> Mapper Class</p>
 *
 * @author DX
 * @version 1.00
 */
public interface NewMapper {


	/**
	 * <p>根据ID查询记录</p>
	 *
	 * @author huban
	 * @version 1.00
	 */
    public New FindByNewsId(long id);

	/**
	 * <p>根据条件查询记录</p>
	 *
	 * @author huban
	 * @version 1.00
	 */
    public List<New> FindNewssByCondition(Map<String,Object> params);

	/**
	 * <p>根据条件查询记录数</p>
	 *
	 * @author huban
	 * @version 1.00
	 */
    public int getCountByCondition(Map<String,Object> params) ;

	/**
	 * <p>添加记录</p>
	 *
	 * @author huban
	 * @version 1.00
	 */
    public int addNews(New object);
	
	/**
	 * <p>修改记录</p>
	 *
	 * @author huban
	 * @version 1.00
	 */
    public int UpdateNews(New object);
    
    /**
	 * <p>根据ID删除记录</p>
	 *
	 * @author huban
	 * @version 1.00
	 */
    public int DeleteNewsById(Map<String,Object> params);
    
     /**
	 * <p>根据ID恢复记录</p>
	 *
	 * @author huban
	 * @version 1.00
	 */
    public int EnabledNewsById(Map<String,Object> params);

   

}
