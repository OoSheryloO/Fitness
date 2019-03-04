package com.huban.service;

import java.util.List;
import java.util.Map;

import com.huban.pojo.Order;

public interface OrderService {
	   
	   public int deleteByPrimaryKey(Long orderId);

	   public int insert(Order record);

	   public int insertSelective(Order record);

	   public Order selectByPrimaryKey(Long orderId);

	   public int updateByPrimaryKeySelective(Order record);

	   public int updateByPrimaryKey(Order record);
	    
	   public List<Order> queryOrder(Map<String,Object> map);
	   
	   public long deleteOrder(Long orderId);
	   
	   public int addorder(Order order);
	   
	   public int orderStatus(Long orderId);
	   
	   public int updateStatus(Long orderId);
	   
	   public int updateStatu(Long orderId);
		
	   public String selectgoods(Long orderId);
}
