/*
 * 2018/01/04 huban Creating 
 *
 * (c) Copyright huban Inc. All rights reserved.
 */
package com.huban.service;
import java.util.*;

import com.huban.pojo.New;

/**
 * <p>Service class。</p>
 *
 * @author huban
 * @version 1.00
 */
public interface NewService {

    public New FindByNewsId(long id);

    public List<New> FindNewssByCondition(Map<String,Object> params);
    
    public int getCountByCondition(Map<String,Object> params) ;
    
    public int addNews(New object);
    
    public int UpdateNews(New object);
    
    public int DeleteNewsById(Map<String,Object> params);
    
    public int EnabledNewsById(Map<String,Object> params);
    
}
