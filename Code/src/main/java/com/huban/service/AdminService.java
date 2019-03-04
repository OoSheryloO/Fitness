/**
 * 
 */
package com.huban.service;

import java.util.List;
import java.util.Map;

import com.huban.pojo.Admin;

public interface AdminService {
	
    public int addAdmin(Admin admin);
    
    public int removeAdmin(Admin admin);
    
    public int changeAdmin(Admin admin);
    
    public int findAdmin(Admin admin);
    
    public Admin searchAdmin(Admin admin) ;
    
    public List<Admin> queryAdmin(Map<String, Object> param) ;
    
    public int queryAdminCount(Map<String, Object> param);
}
