package com.huban.dao;

import java.util.List;
import java.util.Map;

import com.huban.pojo.Address;

public interface AddressMapper {
    int insert(Address record);

    int insertSelective(Address record);
    
    List<Address> queryAll(Map<String, Object> map);
    
    int updateByAddressId(Address address);
    
    int deleteAddress(long addressId);
    
    int addDefault(long addressId);
    
    long cleanDefault(long userId);
    
}