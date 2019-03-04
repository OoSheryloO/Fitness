/*
 * 2018/01/04 huban Creating 
 *
 * (c) Copyright huban Inc. All rights reserved.
 */
package com.huban.service;
import java.util.*;

import com.huban.pojo.Voice;

/**
 * <p>Service class。</p>
 *
 * @author huban
 * @version 1.00
 */
public interface VoiceService {
	
    public Voice FindByVoiceId(long id);
    
    public List<Voice> FindVoicesByCondition(Map<String,Object> params);
    
    public int getCountByCondition(Map<String,Object> params);
    
    public int addVoice(Voice object);
    
    public int UpdateVoice(Voice object);
    
    public int DeleteVoiceById(Map<String,Object> params);
    
    public int EnabledVoiceById(Map<String,Object> params);
    
}
