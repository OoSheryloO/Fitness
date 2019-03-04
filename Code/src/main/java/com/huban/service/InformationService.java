/*
 * 2018/01/04 huban Creating 
 *
 * (c) Copyright huban Inc. All rights reserved.
 */
package com.huban.service;
import java.util.*;

import com.huban.pojo.Information;

/**
 * <p>Service class。</p>
 *
 * @author huban
 * @version 1.00
 */
public interface InformationService {

    public Information FindByInformationId(long id);

    public List<Information> FindInformationsByCondition(Map<String,Object> params);
    
    public int getCountByCondition(Map<String,Object> params) ;
    
    public int addInformation(Information object);
    
    public int UpdateInformation(Information object);
    
    public int DeleteInformationById(Map<String,Object> params);
    
    public int EnabledInformationById(Map<String,Object> params);
    
}
