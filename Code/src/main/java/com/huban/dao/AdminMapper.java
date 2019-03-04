package com.huban.dao;

import java.util.List;
import java.util.Map;

import com.huban.pojo.Admin;

/**
 * Created by MeetLucky on 16/5/17.
 */
public interface AdminMapper { // 常用地址
    //  增
    public int addAdmin(Admin admin);

    //  删
    public int removeAdmin(Admin admin);

    //  改
    public int changeAdmin(Admin admin);

    //  查
    public int findAdmin(Admin admin);

    //  搜
    public Admin searchAdmin(Admin admin);

    //  找
    public List<Admin> queryAdmin(Map<String, Object> param);

    public int queryAdminCount(Map<String, Object> param);



}
