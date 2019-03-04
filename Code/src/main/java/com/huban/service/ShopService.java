package com.huban.service;

import java.util.List;
import java.util.Map;

import com.huban.pojo.Shop;

public interface ShopService {
	
	public long shopIdToBookId(Long shopId);
	
	public int carbookcount(Map<String, Object> map);
	
	public int deleteShop(Long shopId);
	
	public int addShop(Shop shop);
	
	public List<Shop> shoppingCar(Map<String, Object> map);
    
    public int shoppingCount(Map<String, Object> map);
    
    
    public long BookIdToShopId(Long bookId);
    
	public List<Shop> queryshopcar(Map<String, Object> map);
	
	public int shopcarcount(Long userId);
	
	public List<Shop> shopcartype(Map<String, Object> map);
}
