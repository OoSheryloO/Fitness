package com.huban.service.imp;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.huban.dao.OrderMapper;
import com.huban.pojo.Order;
import com.huban.service.OrderService;


@Service("orderService")
public class OrderServiceImpl implements OrderService{
     @Resource
     private OrderMapper mapper;
	/* (non-Javadoc)
	@see com.huban.service.ActivityService#queryList(java.util.Map)*/
	 

	@Override
	public int deleteByPrimaryKey(Long orderId) {
		// TODO Auto-generated method stub
		return mapper.deleteByPrimaryKey(orderId);
	}
	@Override
	public int insert(Order record) {
		// TODO Auto-generated method stub
		return mapper.insert(record);
	}
	@Override
	public int insertSelective(Order record) {
		// TODO Auto-generated method stub
		return mapper.insertSelective(record);
	}
	@Override
	public Order selectByPrimaryKey(Long orderId) {
		// TODO Auto-generated method stub
		return mapper.selectByPrimaryKey(orderId);
	}
	@Override
	public int updateByPrimaryKeySelective(Order record) {
		// TODO Auto-generated method stub
		return mapper.updateByPrimaryKeySelective(record);
	}
	@Override
	public int updateByPrimaryKey(Order record) {
		// TODO Auto-generated method stub
		return mapper.updateByPrimaryKey(record);
	}
	@Override
	public List<Order> queryOrder(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return mapper.queryorderList(map);
	}
	@Override
	public long deleteOrder(Long orderId) {
		// TODO Auto-generated method stub
		return mapper.deleteOrder(orderId);
	}
	@Override
	public int addorder(Order order) {
		// TODO Auto-generated method stub
		return mapper.addorder(order);
	}
	@Override
	public int orderStatus(Long orderId) {
		// TODO Auto-generated method stub
		return mapper.orderStatus(orderId);
	}
	@Override
	public int updateStatus(Long orderId) {
		// TODO Auto-generated method stub
		return mapper.updateStatus(orderId);
	}
	@Override
	public int updateStatu(Long orderId) {
		// TODO Auto-generated method stub
		return mapper.updateStatu(orderId);
	}
	@Override
	public String selectgoods(Long orderId) {
		// TODO Auto-generated method stub
		return mapper.selectgoods(orderId);
	}
	
	
}
