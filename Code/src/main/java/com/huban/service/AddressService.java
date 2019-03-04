/**
 * 
 */
package com.huban.service;

import java.util.List;
import java.util.Map;

import com.huban.pojo.Address;

/**
 * @author GeJiangbo
 * @date 2017年6月1日
 */
public interface AddressService {
	
	public int insert(Address record);
	
	public List<Address> queryAll(Map<String, Object> map);
	
	public int updateByAddressId(Address address);
    
	public int deleteAddress(long addressId);
	
	public int addDefault(long addressId);
	
	public long cleanDefault(long userId);
}
