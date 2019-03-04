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
import com.huban.dao.VoiceMapper;
import com.huban.pojo.Voice;
import com.huban.service.VoiceService;

/**
 * <p>Service class。</p>
 *
 * @author huban
 * @version 1.00
 */
 @Service("voiceService")
public class VoiceServiceImpl implements VoiceService{

	 /**
     * <p>default constants</p>
     */
     @Autowired
	private VoiceMapper mapper;
    
  /**
	 * <p>根据ID查询记录</p>
	 *
	 * @author huban
	 * @version 1.00
	 */
	 @Transactional(readOnly = true)
    public Voice FindByVoiceId(long id) {
		return mapper.FindByVoiceId(id);
	}
	 
	/**
	 * <p>根据条件查询记录</p>
	 *
	 * @author huban
	 * @version 1.00
	 */
	 @Transactional(readOnly = true)
    public List<Voice> FindVoicesByCondition(Map<String,Object> params) {
		return mapper.FindVoicesByCondition(params);
	}

	/**
	 * <p>根据条件查询记录数</p>
	 *
	 * @author huban
	 * @version 1.00
	 */
	 @Transactional(readOnly = true)
    public int getCountByCondition(Map<String,Object> params) {
		return mapper.getCountByCondition(params);
	}

	/**
	 * <p>添加记录</p>
	 *
	 * @author huban
	 * @version 1.00
	 */
	 @Transactional
    public int addVoice(Voice object){
		return mapper.addVoice(object);
	}
	
	/**
	 * <p>修改记录</p>
	 *
	 * @author huban
	 * @version 1.00
	 */
	 @Transactional
    public int UpdateVoice(Voice object){
		return mapper.UpdateVoice(object);
	}
    
    /**
	 * <p>根据ID删除记录</p>
	 *
	 * @author huban
	 * @version 1.00
	 */
	 @Transactional
    public int DeleteVoiceById(Map<String,Object> params){
		return mapper.DeleteVoiceById(params);
	}
	
	/**
	 * <p>根据ID恢复记录</p>
	 *
	 * @author huban
	 * @version 1.00
	 */
	 @Transactional
    public int EnabledVoiceById(Map<String,Object> params){
		return mapper.EnabledVoiceById(params);
	}
}
