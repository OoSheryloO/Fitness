package com.huban.dao;

import java.util.List;
import java.util.Map;

import com.huban.pojo.Shop;

public interface ShopMapper {
    int deleteByPrimaryKey(Long shopId);

    int insert(Shop record);

    int insertSelective(Shop record);

    Shop selectByPrimaryKey(Long shopId);

    int updateByPrimaryKeySelective(Shop record);

    int updateByPrimaryKey(Shop record);
    
    List<Shop> queryshopcar(Map<String, Object> map);
    
    int shopcarcount(Long userId);
    
    List<Shop> shopcartype(Map<String, Object> map);
    
   
    Long BookIdToShopId(Long bookId);
    
    Long shopIdToBookId(Long shopId);
    
    int carbookcount(Map<String, Object> map);
    
    int deleteShop(Long shopId);
    
    int addShop(Shop shop);
    
    List<Shop> shoppingCar(Map<String, Object> map);
    
    int shoppingCount(Map<String, Object> map);
}