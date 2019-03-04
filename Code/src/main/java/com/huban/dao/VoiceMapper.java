/*
 * 2018/01/08 huban Creating
 *
 * (c) Copyright huban Inc. All rights reserved.
 */
package com.huban.dao;
import java.util.*;

import com.huban.pojo.Voice;

/**
 * <p> Mapper Class</p>
 *
 * @author DX
 * @version 1.00
 */
public interface VoiceMapper {


	/**
	 * <p>根据ID查询记录</p>
	 *
	 * @author huban
	 * @version 1.00
	 */
    public Voice FindByVoiceId(long id);

	/**
	 * <p>根据条件查询记录</p>
	 *
	 * @author huban
	 * @version 1.00
	 */
    public List<Voice> FindVoicesByCondition(Map<String,Object> params);

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
    public int addVoice(Voice object);
	
	/**
	 * <p>修改记录</p>
	 *
	 * @author huban
	 * @version 1.00
	 */
    public int UpdateVoice(Voice object);
    
    /**
	 * <p>根据ID删除记录</p>
	 *
	 * @author huban
	 * @version 1.00
	 */
    public int DeleteVoiceById(Map<String,Object> params);
    
     /**
	 * <p>根据ID恢复记录</p>
	 *
	 * @author huban
	 * @version 1.00
	 */
    public int EnabledVoiceById(Map<String,Object> params);

   

}
