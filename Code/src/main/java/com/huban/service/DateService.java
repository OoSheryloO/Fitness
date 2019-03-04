package com.huban.service;

import java.util.List;
import java.util.Map;

import com.huban.pojo.Date;

public interface DateService {
	
	public int addmessage(Date date);
	
	public int readbillcount(Map<String, Object> map);
    
    public List<Date> readbill(Map<String, Object> map);
    
    public List<Date> readmore(Map<String, Object> map);
    
    public List<Date> allread(Map<String, Object> map);
    
    public List<Date> queryList(Map<String, Object> map);
    
    public int querycount(Map<String, Object> map);
    
    public List<Long> ReadBillCountTop();
    
    public List<Long> ranklist(Map<String, Object> map);
    
    public  List<Integer> ranklistcount(Map<String, Object> map);
    
    public int QueryCountById(Long userId);
}
