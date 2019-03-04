/*
 * 2018/01/04 huban Creating
 *
 * (c) Copyright huban Inc. All rights reserved.
 */
package com.huban.dao;
import java.util.*;

import com.huban.pojo.Information;


/**
 * <p> Mapper Class</p>
 *
 * @author DX
 * @version 1.00
 */
public interface InformationMapper {


	/**
	 * <p>根据ID查询记录</p>
	 *
	 * @author huban
	 * @version 1.00
	 */
    public Information FindByInformationId(long id);

	/**
	 * <p>根据条件查询记录</p>
	 *
	 * @author huban
	 * @version 1.00
	 */
    public List<Information> FindInformationsByCondition(Map<String,Object> params);

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
    public int addInformation(Information object);
	
	/**
	 * <p>修改记录</p>
	 *
	 * @author huban
	 * @version 1.00
	 */
    public int UpdateInformation(Information object);
    
    /**
	 * <p>根据ID删除记录</p>
	 *
	 * @author huban
	 * @version 1.00
	 */
    public int DeleteInformationById(Map<String,Object> params);
    
     /**
	 * <p>根据ID恢复记录</p>
	 *
	 * @author huban
	 * @version 1.00
	 */
    public int EnabledInformationById(Map<String,Object> params);

   

}
