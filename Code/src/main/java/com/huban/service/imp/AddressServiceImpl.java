/**
 * 
 */
package com.huban.service.imp;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.huban.dao.AddressMapper;
import com.huban.pojo.Address;
import com.huban.service.AddressService;

/**
 * @author GeJiangbo
 * @date 2017年6月1日
 */
@Service("addressService")
public class AddressServiceImpl implements AddressService{
     
	@Resource
	private AddressMapper mapper;
	/* (non-Javadoc)
	 * @see com.huban.service.BookService#queryBooks(java.util.Map)
	 */
	@Override
	public int insert(Address record) {
		// TODO Auto-generated method stub
		return mapper.insert(record);
	}
	@Override
	public List<Address> queryAll(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return mapper.queryAll(map);
	}
	@Override
	public int updateByAddressId(Address address) {
		// TODO Auto-generated method stub
		return mapper.updateByAddressId(address);
	}
	@Override
	public int deleteAddress(long addressId) {
		// TODO Auto-generated method stub
		return mapper.deleteAddress(addressId);
	}
	@Override
	public int addDefault(long addressId) {
		// TODO Auto-generated method stub
		return mapper.addDefault(addressId);
	}
	@Override
	public long cleanDefault(long userId) {
		// TODO Auto-generated method stub
		return mapper.cleanDefault(userId);
	}
	

	
}
