package com.huban.dao;

import java.util.List;
import java.util.Map;

import com.huban.pojo.Order;

public interface OrderMapper {
    int deleteByPrimaryKey(Long orderId);

    int insert(Order record);

    int insertSelective(Order record);

    Order selectByPrimaryKey(Long orderId);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);

    List<Order> queryorderList(Map<String, Object> map);
    
    long deleteOrder(Long orderId);
    
    int addorder(Order order);
    
    int orderStatus(Long orderId);
    
    int updateStatus(Long orderId);
    
    int updateStatu(Long orderId);
    
    String selectgoods(Long orderId);
}