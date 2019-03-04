package com.huban.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.huban.Utils.ArgumentsUtils;
import com.huban.Utils.ConstantUtils;
import com.huban.Utils.RandomUtil;
import com.huban.Utils.ReturnCodeUtils;
import com.huban.Utils.ReturnMessageUtils;
import com.huban.pojo.Address;
import com.huban.pojo.AuctionRecords;
import com.huban.pojo.Book;
import com.huban.pojo.Goods;
import com.huban.pojo.Order;
import com.huban.pojo.Payrecords;
import com.huban.pojo.Shop;
import com.huban.pojo.User;
import com.huban.util.BaseUtil;
import com.huban.util.IdWorker;
import com.huban.util.ResultUtil;

/**
* <p>Title: MallController</p>
* <p>Description: </p>
* <p>Company: </p>
* @author Administrator
* @date 2017-6-26 下午1:53:07
*/
@Controller
@RequestMapping(value="/mall")
public class MallController extends BaseController{
	/**0已完成；1待付款；2待收货
	 *我的账单
	 */
	@RequestMapping(value="/orderlist",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> orderList(@RequestParam String page,String size,String userId,String status,HttpServletRequest request){
		Map<String, Object> result = new HashMap<String, Object>();
		int pageCount=Integer.parseInt(size);	
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("start", (Integer.parseInt(page)-1)*pageCount);
		map.put("size", pageCount);
		map.put("userId", userId);
		map.put("status", status);
		try {
		List<Object> orderStatus = new ArrayList<Object>();
		List<Object> orderId = new ArrayList<Object>();
		List<Order> orderList = orderService.queryOrder(map);
		List<List<Book>> bookListlList = new ArrayList<List<Book>>();
		List<List<String>> numberlist = new ArrayList<List<String>>();
		for (int i = 0; i < orderList.size(); i++) {//循环订单
			List<Book> bookList = new ArrayList<Book>();
			String str = orderList.get(i).getOrderGoodsid().toString();
			String[] goodid = str.split("\\[\'|\',\'|\'\\]");
			List<String> number = new ArrayList<String>();
			for (int j = 1; j < goodid.length-1; j=j+2) {//第一个字符串是空//循环购物车id，查bookid，再查book信息
				//String bookId = shopService.shopIdToBookId(goodid[j]);
				Book book = bookService.queryBookById(Long.parseLong(goodid[j]));
				number.add(goodid[j+1]);
				bookList.add(book);
			}
			numberlist.add(number);
			orderId.add(orderList.get(i).getOrderId());
			orderStatus.add(orderList.get(i).getOrderStatus());
			bookListlList.add(bookList);
		}
		result.put("bookcount", JSON.toJSON(numberlist));
		result.put("orderId", JSON.toJSON(orderId));
		result.put("orderStatus", JSON.toJSON(orderStatus));
		result.put("bookLists",JSON.toJSON(bookListlList));
		result.put("message", "获取列表成功");
		result.put("status", true);
		} catch (Exception e) {
			// TODO: handle exception
			result.put("message", "数据异常，请联系客服");
			result.put("status", false);
		}
		return result;
	}
			/*for (int j = 0; j < goodid.length; j++) {
				
		List<Order> orderList = orderService.queryOrder(map);
		//List<List<Book>> bookListlList = new ArrayList<List<Book>>();
		for (int i = 0; i < orderList.size(); i++) {//循环订单
			//List<Book> bookList = new ArrayList<Book>();
			String str = orderList.get(i).getOrderGoodsid().toString();
			String[] goodid = str.split("\\[\"|\",\"|\"\\]");
			for (int j = 1; j < goodid.length; j++) {//第一个字符串是空//循环购物车id，查bookid，再查book信息
				//String bookId = shopService.shopIdToBookId(goodid[j]);
				//Book book = bookService.queryBook(Integer.valueOf(bookId));
				//bookList.add(book);
			}
			//bookListlList.add(bookList);
		}
		//result.put("bookLists",JSON.toJSON(bookListlList));
		result.put("message", "获取列表成功");
		result.put("status", true);
		return result;
	
		//["873453334675066880","874108196834250752"]
		//		\\[\"|\",\"|\"//]
		//    \\u005B \"|\",\"|\"//]    \u0022是"的ASCII
						
			}*/
		//["873453334675066880","874108196834250752"]
		//		\\[\"|\",\"|\"//]
		//    \\u005B \"|\",\"|\"//]    \u0022=="
		/*List<Shop> finishShop = shopService.shopcartype(map);
		List<Book> finishBook = new ArrayList<Book>();
		for (int i = 0; i < finishShop.size(); i++) {
			Book book = bookService.queryBook(Integer.parseInt(finishShop.get(i).getShopBookid()));
			finishBook.add(book);
		}
		
		map.put("type", 1);
		List<Shop> waitShop = shopService.shopcartype(map);
		List<Book> waitBook = new ArrayList<Book>();
		for (int i = 0; i < waitShop.size(); i++) {
			Book book = bookService.queryBook(Integer.parseInt(waitShop.get(i).getShopBookid()));
			waitBook.add(book);
		}
		
		map.put("type", 0);
		List<Shop> noPayShop = shopService.shopcartype(map);
		List<Book> noPayBook = new ArrayList<Book>();
		for (int i = 0; i < noPayShop.size(); i++) {
			Book book = bookService.queryBook(Integer.parseInt(noPayShop.get(i).getShopBookid()));
			noPayBook.add(book);
		}
		
		result.put("finishShop", JSON.toJSON(finishBook));
		result.put("waitShop", JSON.toJSON(waitBook));
		result.put("noPayShop", JSON.toJSON(noPayBook));
		for (int i = 0; i < 3; i++) {
			map.put("type", i);
			List<Shop> finishShop = shopService.shopcartype(map);
			result.put("", value)
		}*/
	/**
	 * 删除订单(没有写判断订单的状态(未付款账单不能删除的情况))
	 */
	@RequestMapping(value="/deleteorder",method={RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public Map<String, Object> deleteOrder(HttpServletRequest request){
		Map<String, Object> result = new HashMap<String,Object>();
		String param;
        param = (String)request.getAttribute(BaseUtil.paramKey);
        if(param==null || param.equals("")){
            param=request.getParameter(BaseUtil.paramKey);
        }
        JSONObject jsonObject=JSON.parseObject(param);
        String orderId = jsonObject.getString("orderId");
        String status = jsonObject.getString("status");
        try {
        int statu = orderService.orderStatus(Long.parseLong(orderId));
        if (2 == statu) {
        	result.put("message", "订单未收货，请先确认收货");
    		result.put("status", false);
    		return result;
		}
//        if (2 == Long.parseLong(status)) {
//        	result.put("message", "订单未收货，请先确认收货");
//    		result.put("status", false);
//    		return result;
//		}
		orderService.deleteOrder(Long.parseLong(orderId));
		result.put("message", "删除成功");
		result.put("status", true);
        } catch (Exception e) {
			// TODO: handle exception
			result.put("message", "数据异常，请联系客服");
			result.put("status", false);
		}
		return result;
	}
	
	/**
	 * 收货地址列表
	 */
	@RequestMapping(value="/shopaddress",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> shoppingAddress(@RequestParam String page,String size,String userId,HttpServletRequest request){
		Map<String, Object> result = new HashMap<String,Object>();
		int pageCount=Integer.parseInt(size);	
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("start", (Integer.parseInt(page)-1)*pageCount);
		map.put("size", pageCount);
		map.put("userId", userId);
		try {
		List<Address> addresses = addressService.queryAll(map);
		result.put("shopAddress", JSON.toJSON(addresses));
		result.put("message", "列表获取成功");
		result.put("status", true);
		 } catch (Exception e) {
				// TODO: handle exception
				result.put("message", "数据异常，请联系客服");
				result.put("status", false);
			}
		return result;
	}
	
	/**
	 * 设置默认收获地址
	 */
	@RequestMapping(value="/setdefault",method={RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public Map<String, Object> setDefaule(HttpServletRequest request){
		Map<String, Object> result = new HashMap<String,Object>();
		String param;
        param = (String)request.getAttribute(BaseUtil.paramKey);
        if(param==null || param.equals("")){
            param=request.getParameter(BaseUtil.paramKey);
        }
        JSONObject jsonObject=JSON.parseObject(param);
        String addressId = jsonObject.getString("addressId");
        String userId = jsonObject.getString("userId");
        try {
        addressService.cleanDefault(Long.parseLong(userId));
		addressService.addDefault(Long.parseLong(addressId));
		result.put("message", "设置成功");
		result.put("status", true);
        } catch (Exception e) {
			// TODO: handle exception
			result.put("message", "数据异常，请联系客服");
			result.put("status", false);
		}
		return result;
	}
	
	/**
	 * 添加收货地址
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/addaddress",method={RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public Map<String, Object> addShopAddress(HttpServletRequest request){
		Map<String, Object> result = new HashMap<String,Object>();
		String param;
        param = (String)request.getAttribute(BaseUtil.paramKey);
        if(param==null || param.equals("")){
            param=request.getParameter(BaseUtil.paramKey);
        }
        JSONObject jsonObject=JSON.parseObject(param);
        String userId = jsonObject.getString("userId");
        String addressName = jsonObject.getString("addressName");
        String telNum = jsonObject.getString("telnum");
        String addre = jsonObject.getString("address");
		Address address = new Address();
		address.setAddressId(IdWorker.CreateNewID());
		address.setAddressUserid(Long.parseLong(userId));
		address.setAddressUsername(addressName);
		address.setAddressUserphone(telNum);
		address.setAddressUserregion(addre);
		address.setAddressStatus(0);
		try {
		addressService.insert(address);
		result.put("message", "添加成功");
		result.put("status", true);
		 } catch (Exception e) {
				// TODO: handle exception
				result.put("message", "数据异常，请联系客服");
				result.put("status", false);
			}
		return result;
	}
	
	/**
	 * 修改收货地址
	 */
	@RequestMapping(value="/modifyaddress",method={RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public Map<String, Object> modifyAddress(HttpServletRequest request) {
		Map<String, Object> result = new HashMap<String, Object>();
		String param;
		param = (String) request.getAttribute(BaseUtil.paramKey);
		if (param == null || param.equals("")) {
			param = request.getParameter(BaseUtil.paramKey);
		}
		JSONObject jsonObject = JSON.parseObject(param);
		String addressId = jsonObject.getString("addressId");
		String addressName = jsonObject.getString("addressName");
		String telNum = jsonObject.getString("telnum");
		String addre = jsonObject.getString("address");
		Address address = new Address();
		address.setAddressId((Long.parseLong(addressId)));
		if (null != addressName || !"".equals(addressName)) {
			address.setAddressUsername(addressName);
		}
		if (null != telNum || !"".equals(telNum)) {
			address.setAddressUserphone(telNum);
		}
		if (null != addre || !"".equals(addre)) {
			address.setAddressUserregion(addre);
		}
		try {
			addressService.updateByAddressId(address);
			result.put("message", "修改成功");
			result.put("status", true);
		} catch (Exception e) {
			// TODO: handle exception
			result.put("message", "数据异常，请联系客服");
			result.put("status", false);
		}
		return result;
	}
	
	/**
	 * 删除收货地址
	 */
	@RequestMapping(value="/deladdress",method={RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public Map<String, Object> delAddress(HttpServletRequest request){
		Map<String, Object> result = new HashMap<String,Object>();
		String param;
        param = (String)request.getAttribute(BaseUtil.paramKey);
        if(param==null || param.equals("")){
            param=request.getParameter(BaseUtil.paramKey);
        }
        JSONObject jsonObject=JSON.parseObject(param);
        String addressId = jsonObject.getString("addressId");
        try {
        addressService.deleteAddress(Long.parseLong(addressId));
        result.put("message", "删除成功");
		result.put("status", true);
        } catch (Exception e) {
			// TODO: handle exception
			result.put("message", "数据异常，请联系客服");
			result.put("status", false);
		}
		return result;
	}
	
	/*
	 * 增加到购物车 
	 */
	@RequestMapping(value="/addshopcar",method={RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public Map<String, Object> addShopCar(HttpServletRequest request){
		Map<String, Object> result = new HashMap<String,Object>();
		String param;
        param = (String)request.getAttribute(BaseUtil.paramKey);
        if(param==null || param.equals("")){
            param=request.getParameter(BaseUtil.paramKey);
        }
        JSONObject jsonObject=JSON.parseObject(param);
        String userId = jsonObject.getString("userId");
        String bookId = jsonObject.getString("bookId");
        //String num = jsonObject.getString("number");
        Shop shop = new Shop();
        shop.setShopId(IdWorker.CreateNewID());//shop.setShopId(String.valueOf(IdWorker.CreateNewID()));
        shop.setShopBookid(Long.parseLong(bookId));
        shop.setShopUserid(Long.parseLong(userId));
        shop.setShopAddressid(null);
		shop.setShopStatus(0);
		try {
        shopService.addShop(shop);
		} catch (Exception e) {
			// TODO: handle exception
			result.put("message", "数据异常，请联系客服");
			result.put("status", false);
		}
        result.put("message", "添加成功");
		result.put("status", true);
		return result;
	}
	
	
	/**
	 * 删除购物车商品 
	 */
	@RequestMapping(value="/delshop",method={RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public Map<String, Object> delShop(HttpServletRequest request){
		Map<String, Object> result = new HashMap<String,Object>();
		String param;
        param = (String)request.getAttribute(BaseUtil.paramKey);
        if(param==null || param.equals("")){
            param=request.getParameter(BaseUtil.paramKey);
        }
        JSONObject jsonObject=JSON.parseObject(param);
        String bookId = jsonObject.getString("bookId");
        String userId = jsonObject.getString("userId");
        String num = jsonObject.getString("number");
        if (Integer.parseInt(num) > 376) {
			result.put("message", "宝贝你有那么多钱吗");
			result.put("status", false);
			return result;
		}
        Map<String, Object> map = new HashMap<String,Object>();
        map.put("bookId", bookId);
        map.put("userId", userId);
        try {
        List<Shop> shopList = shopService.queryshopcar(map);
	        int count = shopService.carbookcount(map);
	        if (count > Integer.parseInt(num)) {
	        	for (int i = 0; i < count - Integer.parseInt(num); i++) {
					shopService.deleteShop(shopList.get(i).getShopId());
				}
	        	 result.put("message", "删除成功");
			}else {
				for (int i = 0; i < Integer.parseInt(num) - count; i++) {
					Shop shop = new Shop();
					shop.setShopId(IdWorker.CreateNewID());
					shop.setShopBookid(Long.parseLong(bookId));
					shop.setShopAddressid(null);
					shop.setShopStatus(0);
					shop.setShopUserid(Long.parseLong(userId));
					shopService.addShop(shop);
					result.put("message", "增加成功");
				}
			}
		result.put("status", true);
        } catch (Exception e) {
			// TODO: handle exception
			result.put("message", "数据异常，请联系客服");
			result.put("status", false);
		}
		return result;
	}
	
	/**
	 * 购物车列表(列表的排序问题是按照bookId的顺序排序的，好像没办法按照修改时间排序
	 * ，要想按照其他字段排序(比如说修改时间)应该要在shop表里面加或者改某个字段为某用户某书的数量) 
	 */
	@RequestMapping(value="/shopcarlist",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> shopCatList(@RequestParam String userId,String page,String size,HttpServletRequest request){
		Map<String, Object> result = new HashMap<String,Object>();
		Map<String, Object> map = new HashMap<String,Object>();
		Map<String, Object> mapshop = new HashMap<String,Object>();
		int pageCount=Integer.parseInt(size);	
		mapshop.put("start", (Integer.parseInt(page)-1)*pageCount);
		mapshop.put("size", pageCount);
		mapshop.put("userId", Long.parseLong(userId));
		mapshop.put("random", RandomUtil.generateString(10));
		try {
		List<Shop> list = shopService.shoppingCar(mapshop);
		List<Book> books = new ArrayList<Book>();
		List<Object> number = new ArrayList<Object>();
		map.put("userId", userId);
		for (int i = 0; i < list.size(); i++) {//generateString(10)
			Book book = bookService.queryBook(list.get(i).getShopBookid());
			books.add(book);
			map.put("bookId", book.getBookId());
			int num = shopService.shoppingCount(map);
			number.add(num);
		}
		result.put("bookList", JSON.toJSON(books));
		result.put("number", JSON.toJSON(number));
        result.put("message", "操作成功");
		result.put("status", true);
		} catch (Exception e) {
			// TODO: handle exception
			result.put("message", "数据异常，请联系客服");
			result.put("status", false);
		}
		return result;
	}
	
	/*--------------------------------------------- V2 Method ---------------------------------------------*/
	
	/**
	 * @Title: IncreaseAuctionMethod
	 * @Description: TODO( 物品上架 )
	 * @param @param request
	 * @param @return    参数
	 * @return Map<String,Object>    返回类型
	 * @throws
	 */
	@RequestMapping(value="/Interface/V2/IncreaseAuction",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> IncreaseAuctionMethod(HttpServletRequest request){
		String param;
        param = (String)request.getAttribute(BaseUtil.paramKey);
        if(param==null || param.equals("")){
            param=request.getParameter(BaseUtil.paramKey);
        }
        JSONObject jsonObject = JSON.parseObject(param);
        User pjUser = JSON.parseObject(jsonObject.getString(User.sUserClass), User.class);
        Goods pjGoods = JSON.parseObject(jsonObject.getString(Goods.sGoodsClass), Goods.class);
        pjGoods.setGoodsPublisher(pjUser.getUserId());
        goodsService.IncreaseMessaga(pjGoods);
        return ResultUtil.sharedInstance().TrueData(null, ReturnMessageUtils.trueReturnMessageKey, ReturnCodeUtils.Code.OK.getCode());
	}
	
	/**
	 * @Title: SearchAuctionMethod
	 * @Description: TODO( 获取带条件的拍品列表 )
	 * @param userId
	 * @param page
	 * @param size
	 * @param keyWord
	 * @param type
	 * @param request
	 * @return  参数
	 * @return Map<String,Object>    返回类型
	 * @throws
	 */
	@RequestMapping(value="/Interface/V2/SearchAuction",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> SearchAuctionMethod(@RequestParam String userId, @RequestParam(required=false) Integer page, @RequestParam(required=false) Integer size, @RequestParam(required=false) String keyWord, @RequestParam(required=false) Integer type, HttpServletRequest request){
		Map<String, Object> mapQuery = new HashMap<String, Object>();
		if (null != page && null != size && 0 != page && 0 != size) {
			mapQuery.put(ArgumentsUtils.sStartKey, (page-1)*size);
			mapQuery.put(ArgumentsUtils.sSizeKey, size);
		}
		if ( null != keyWord && "" != keyWord ) {
			mapQuery.put(ArgumentsUtils.sKeyWordKey, keyWord);
		}
		if ( null != type && 0 != type ) {
			mapQuery.put(ArgumentsUtils.STypeKey, type);
		}
		List<Goods> LstGoods = goodsService.LstSearchByCondition(mapQuery);
        return ResultUtil.sharedInstance().TrueData(LstGoods, ReturnMessageUtils.trueReturnMessageKey, ReturnCodeUtils.Code.OK.getCode());
	}
	
	/**
	 * @Title: AuctionBidMethod
	 * @Description: TODO( 竞价 )
	 * @param request
	 * @return  参数
	 * @return Map<String,Object>  返回类型
	 * @throws
	 */
	@RequestMapping(value="/Interface/V2/AuctionBid",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> AuctionBidMethod(HttpServletRequest request){
		Map<String, Object> mapQuery = new HashMap<String, Object>();
		String param;
        param = (String)request.getAttribute(BaseUtil.paramKey);
        if(param==null || param.equals("")){
            param=request.getParameter(BaseUtil.paramKey);
        }
        JSONObject jsonObject = JSON.parseObject(param);
        User pjUser = JSON.parseObject(jsonObject.getString(User.sUserClass), User.class);
        AuctionRecords pjAuctionRecords = JSON.parseObject(jsonObject.getString(AuctionRecords.sAuctionRecordsClass), AuctionRecords.class);
        pjAuctionRecords.setAuctionUserid(pjUser.getUserId());
        
        mapQuery.put(ArgumentsUtils.sIDKey, pjAuctionRecords.getAuctionGoodsid());
        mapQuery.put(ArgumentsUtils.sRandomKey, RandomUtil.generateString(6));
        Goods pjGood = goodsService.SearchByCondition(mapQuery);
        
        if ((new java.util.Date()).getTime() > pjGood.getGoodsAuctionend().getTime()) {
        		return ResultUtil.sharedInstance().FalseData("拍卖已结束", ReturnCodeUtils.Code.NO.getCode());
		}
        if ((new java.util.Date()).getTime() < pjGood.getGoodsAuctionstart().getTime()) {
    			return ResultUtil.sharedInstance().FalseData("拍卖未开始", ReturnCodeUtils.Code.NO.getCode());
        }
        if (pjAuctionRecords.getAuctionPrize() < pjGood.getGoodsMinprize()) {
			return ResultUtil.sharedInstance().FalseData("您的出价低于最低价", ReturnCodeUtils.Code.NO.getCode());
        }
        int RemainInteger = userService.integral(pjUser.getUserId());
        if (pjAuctionRecords.getAuctionPrize() <= 0 || RemainInteger < pjAuctionRecords.getAuctionPrize()) {
        		return ResultUtil.sharedInstance().FalseData("余额不足", ReturnCodeUtils.Code.NO.getCode());
		}
        
        mapQuery.clear();
        mapQuery.put(ArgumentsUtils.sDBIDKey, pjAuctionRecords.getAuctionGoodsid());
        mapQuery.put(ArgumentsUtils.sLimitKey, 1);
        AuctionRecords jdbcAuctionRecord = auctionService.SearchMessage(mapQuery);
        if (pjAuctionRecords.getAuctionPrize() <= jdbcAuctionRecord.getAuctionPrize()) {
        		return ResultUtil.sharedInstance().FalseData("您的出价必须大于前一位", ReturnCodeUtils.Code.NO.getCode());
		}
        
        if (0 == pjGood.getGoodsMaxprize() || -1 == pjGood.getGoodsMaxprize()) {//不限制最大交易额
			
		}else {//限制
			if (pjAuctionRecords.getAuctionPrize() >= pjGood.getGoodsMaxprize()) {//>=最大一口价
				pjAuctionRecords.setAuctionPrize(pjGood.getGoodsMaxprize());
				pjAuctionRecords.setAuctionStatus(ConstantUtils.iGetAuction);
				
				Payrecords payrecords = new Payrecords();
				payrecords.setPayrecordUserid(pjUser.getUserId());
				payrecords.setPayrecordOrderid((long)0);
				payrecords.setPayrecordBelongid(pjGood.getGoodsId());
				payrecords.setPayrecordPaymethod(ConstantUtils.sPayForGoldCoin);
				payrecords.setPayrecordPayreason(ConstantUtils.sAuction);
				payrecords.setPayrecordMoney(BigDecimal.valueOf(pjGood.getGoodsMaxprize()));
				payrecords.setPayrecordTarde((byte) ConstantUtils.ShopOut);
				payrecords.setPayrecordVersion(0);
				payrecords.setPayrecordTypeid((long) ConstantUtils.iGetAuction);// 8 拍卖
				payrecords.setPayrecordStatus((byte) 0);
				payrecordsService.addPayMessage(payrecords);// 添加消费记录
				
				mapQuery.clear();//拍用户减少积分
				mapQuery.put("userId", pjUser.getUserId());
				mapQuery.put("userRemainIntgral", pjGood.getGoodsMaxprize());
				mapQuery.put("moneyNum", 0);
				userService.conversion(mapQuery);
				
				
				payrecords.setPayrecordUserid(pjGood.getGoodsPublisher());
				payrecords.setPayrecordFromuserid(pjUser.getUserId());
				payrecords.setPayrecordPayreason(ConstantUtils.sAuctionReturn);
				payrecords.setPayrecordTarde((byte) ConstantUtils.ShopIn);
				payrecordsService.addPayMessage(payrecords);// 添加消费记录
				
				mapQuery.clear();//上架用户增加积分
				mapQuery.put("userId", pjGood.getGoodsPublisher());
				mapQuery.put("userIntegral",  pjGood.getGoodsMaxprize());
				userService.addIntegral(mapQuery);
				
				pjGood = new Goods();
				pjGood.setGoodsId(pjAuctionRecords.getAuctionGoodsid());
				pjGood.setGoodsDelete(1);
				pjGood.setGoodsStatus(ConstantUtils.iGetAuction);//8: 已拍出
				goodsService.ModifyMessage(pjGood);
			}
		}
//     , #{auctionGoodsid,=}, #{auctionPrize,=}, #{auctionExplain,=}, 
        auctionService.IncreaseMessage(pjAuctionRecords);
        
        return ResultUtil.sharedInstance().TrueData(null, ReturnMessageUtils.trueReturnMessageKey, ReturnCodeUtils.Code.OK.getCode());
	}
	
	/**
	 * @Title: OverAuctionMethod
	 * @Description: TODO( 终止拍 AND 结束拍 )
	 * @param request
	 * @return  参数
	 * @return Map<String,Object>  返回类型
	 * @throws
	 */
	@RequestMapping(value="/Interface/V2/OverAuction",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> OverAuctionMethod(HttpServletRequest request){
		Map<String, Object> mapQuery = new HashMap<String, Object>();
		String param;
        param = (String)request.getAttribute(BaseUtil.paramKey);
        if(param==null || param.equals("")){
            param=request.getParameter(BaseUtil.paramKey);
        }
        JSONObject jsonObject = JSON.parseObject(param);
        User pjUser = JSON.parseObject(jsonObject.getString(User.sUserClass), User.class);
        Goods pjGoods = JSON.parseObject(jsonObject.getString(Goods.sGoodsClass), Goods.class);//在这里goodsDelete=1 表示终止拍卖 =2 表示结束拍卖
        
        mapQuery.put(ArgumentsUtils.sIDKey, pjGoods.getGoodsId());
		mapQuery.put(ArgumentsUtils.sRandomKey, RandomUtil.generateString(6));
		Goods jdbcGood = goodsService.SearchByCondition(mapQuery);
        if (1 == pjGoods.getGoodsDelete()) {
			if (jdbcGood.getGoodsAuctionend().getTime() <= jdbcGood.getGoodsAuctionend().getTime()) {
				pjGoods.setGoodsStatus(ConstantUtils.iOverAuction);//7: 已终止
				goodsService.ModifyMessage(pjGoods);
			} else {
				return ResultUtil.sharedInstance().FalseData("由于您的拍品设置了结束时间，不能终止拍卖", ReturnCodeUtils.Code.NO.getCode());
			}
		}
        if (2 == pjGoods.getGoodsDelete()) {
        		pjGoods.setGoodsDelete(1);
        		pjGoods.setGoodsStatus(ConstantUtils.iGetAuction);//8: 已拍出
			if (jdbcGood.getGoodsAuctionend().getTime() <= jdbcGood.getGoodsAuctionend().getTime()) {
				goodsService.ModifyMessage(pjGoods);
				
				mapQuery.clear();
		        mapQuery.put(ArgumentsUtils.sDBIDKey, pjGoods.getGoodsId());
		        mapQuery.put(ArgumentsUtils.sLimitKey, 1);
		        AuctionRecords jdbcAuctionRecord = auctionService.SearchMessage(mapQuery);
				
				Payrecords payrecords = new Payrecords();
				payrecords.setPayrecordUserid(jdbcAuctionRecord.getAuctionUserid());
				payrecords.setPayrecordOrderid((long)0);
				payrecords.setPayrecordBelongid(pjGoods.getGoodsId());
				payrecords.setPayrecordPaymethod(ConstantUtils.sPayForGoldCoin);
				payrecords.setPayrecordPayreason(ConstantUtils.sAuction);
				payrecords.setPayrecordMoney(BigDecimal.valueOf(jdbcAuctionRecord.getAuctionPrize()));
				payrecords.setPayrecordTarde((byte) ConstantUtils.ShopOut);
				payrecords.setPayrecordVersion(0);
				payrecords.setPayrecordTypeid((long) ConstantUtils.iGetAuction);// 8 拍卖
				payrecords.setPayrecordStatus((byte) 0);
				payrecordsService.addPayMessage(payrecords);// 添加消费记录
				
				mapQuery.clear();//拍用户减少积分
				mapQuery.put("userId", jdbcAuctionRecord.getAuctionUserid());
				mapQuery.put("userRemainIntgral",jdbcAuctionRecord.getAuctionPrize());
				mapQuery.put("moneyNum", 0);
				userService.conversion(mapQuery);
				
				
				payrecords.setPayrecordUserid(jdbcGood.getGoodsPublisher());
				payrecords.setPayrecordFromuserid(pjUser.getUserId());
				payrecords.setPayrecordPayreason(ConstantUtils.sAuctionReturn);
				payrecords.setPayrecordTarde((byte) ConstantUtils.ShopIn);
				payrecordsService.addPayMessage(payrecords);// 添加消费记录
				
				mapQuery.clear();//上架用户增加积分
				mapQuery.put("userId", jdbcGood.getGoodsPublisher());
				mapQuery.put("userIntegral",  jdbcAuctionRecord.getAuctionPrize());
				userService.addIntegral(mapQuery);
			}else {
				return ResultUtil.sharedInstance().FalseData("由于您的拍品设置了结束时间，不能结束拍卖", ReturnCodeUtils.Code.NO.getCode());
			}
		}
        return null;
	}
	
	/**
	 * @Title: BidRecordMethod
	 * @Description: TODO( 出价表 )
	 * @param userId
	 * @param page
	 * @param size
	 * @param goodId
	 * @param request
	 * @return  参数
	 * @return Map<String,Object>  返回类型
	 * @throws
	 */
	@RequestMapping(value="/Interface/V2/BidRecord",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> BidRecordMethod(@RequestParam String userId, @RequestParam(required=false) Integer page, @RequestParam(required=false) Integer size, @RequestParam Long goodId, HttpServletRequest request){
		Map<String, Object> mapQuery = new HashMap<String, Object>();
		if (null != page && null != size && 0 != page && 0 != size) {
			mapQuery.put(ArgumentsUtils.sStartKey, (page-1)*size);
			mapQuery.put(ArgumentsUtils.sSizeKey, size);
		}
		if ( null == goodId || 0 ==  goodId) {
			return ResultUtil.sharedInstance().FalseData("参数缺失", ReturnCodeUtils.Error_Parameter.Default.getCode());
		}
		mapQuery.put(ArgumentsUtils.sIDKey, goodId);
		List<AuctionRecords> LstAuction = auctionService.LstSearchMessage(mapQuery);
        return ResultUtil.sharedInstance().TrueData(LstAuction, ReturnMessageUtils.trueReturnMessageKey, ReturnCodeUtils.Code.OK.getCode());
	}
	
}
