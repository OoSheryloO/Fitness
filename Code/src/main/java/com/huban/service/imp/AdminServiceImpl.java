package com.huban.service.imp;

import com.huban.dao.AdminMapper;
import com.huban.pojo.Admin;
import com.huban.service.AdminService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * Created by MeetLucky on 16/5/17.
 */
@Service("adminService")
public class AdminServiceImpl implements AdminService { // 常用地址
    @Autowired
    private AdminMapper mapper;

    //  增
    @Override
    @Transactional
    public int addAdmin(Admin admin) {
        return mapper.addAdmin(admin);
    }

    //  删
    @Override
    @Transactional
    public int removeAdmin(Admin admin) {
        return mapper.removeAdmin(admin);
    }

    //  改
    @Override
    @Transactional
    public int changeAdmin(Admin admin) {
        return mapper.changeAdmin(admin);
    }

    //  查
    @Override
    @Transactional(readOnly = true)
    public int findAdmin(Admin admin) {
        return mapper.findAdmin(admin);
    }

    //  搜
    @Override
    @Transactional(readOnly = true)
    public Admin searchAdmin(Admin admin) {
        return mapper.searchAdmin(admin);
    }

    //  找
    @Override
    @Transactional(readOnly = true)
    public List<Admin> queryAdmin(Map<String, Object> param) {
        return mapper.queryAdmin(param);
    }

    //  找
    @Override
    @Transactional(readOnly = true)
    public int queryAdminCount(Map<String, Object> param) {
        return mapper.queryAdminCount(param);
    }

}
