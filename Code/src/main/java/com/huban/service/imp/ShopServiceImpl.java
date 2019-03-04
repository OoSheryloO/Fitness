package com.huban.service.imp;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.huban.dao.ShopMapper;
import com.huban.pojo.Shop;
import com.huban.service.ShopService;

@Service("shopService")
public class ShopServiceImpl implements ShopService{

	@Resource
	private ShopMapper mapper;

	@Override
	public List<Shop> queryshopcar(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return mapper.queryshopcar(map);
	}

	@Override
	public int shopcarcount(Long userId) {
		// TODO Auto-generated method stub
		return mapper.shopcarcount(userId);
	}

	@Override
	public List<Shop> shopcartype(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return mapper.shopcartype(map);
	}

	@Override
	public long shopIdToBookId(Long shopId) {
		// TODO Auto-generated method stub
		return mapper.shopIdToBookId(shopId);
	}

	@Override
	public int carbookcount(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return mapper.carbookcount(map);
	}

	@Override
	public int deleteShop(Long shopId) {
		// TODO Auto-generated method stub
		return mapper.deleteShop(shopId);
	}

	@Override
	public int addShop(Shop shop) {
		// TODO Auto-generated method stub
		return mapper.addShop(shop);
	}

	@Override
	public List<Shop> shoppingCar(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return mapper.shoppingCar(map);
	}

	@Override
	public int shoppingCount(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return mapper.carbookcount(map);
	}

	@Override
	public long BookIdToShopId(Long bookId) {
		// TODO Auto-generated method stub
		return mapper.BookIdToShopId(bookId);
	}


}
