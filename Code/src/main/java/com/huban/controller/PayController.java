package com.huban.controller;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import com.huban.Utils.ConstantUtils;
import com.huban.Utils.ReturnCodeUtils;
import com.huban.pojo.Activity;
import com.huban.pojo.Activitypart;
import com.huban.pojo.Book;
import com.huban.pojo.Integration;
import com.huban.pojo.Order;
import com.huban.pojo.Payrecords;
import com.huban.pojo.ReadBook;
import com.huban.pojo.Reward;
import com.huban.pojo.User;
import com.huban.pojo.Video;
import com.huban.util.BaseUtil;
import com.huban.util.IdWorker;
import com.huban.util.ResultUtil;

/**
* <p>Title: PayController</p>
* <p>Description: </p>
* <p>Company: </p>
* @author Administrator
* @date 2017-6-26 下午2:48:15
*/

@Controller
@RequestMapping("/pay")
public class PayController extends BaseController{
	/**支付密码验证
	 * 2017年7月13日 下午3:07
	 */
	@RequestMapping(value="/confirmpwd",method={RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public Map<String, Object> confirmpwd(@RequestParam String userId,String pwd,HttpServletRequest request){
		Map<String, Object> result = new HashMap<String, Object>();
//		String param;
//		param = (String) request.getAttribute(BaseUtil.paramKey);
//		if (param == null || "".equals(param)) {
//			param = request.getParameter(BaseUtil.paramKey);
//		}
//		JSONObject jsonObject = JSONObject.parseObject(param);
//		String userId = jsonObject.getString("userId");
//		String pwd = jsonObject.getString("password");
//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("pwd", pwd);
//		map.put("userId", Long.parseLong(userId));
		boolean flag = false;
		String jdbcpwd = "";
		try {
		jdbcpwd = userService.selectPayPass(Long.parseLong(userId));
		if (0 == pwd.compareTo(jdbcpwd)) {
			flag = true;
			result.put("message", "密码验证成功");
		}else {
			result.put("message", "密码验证失败！密码错误");
		}
		result.put("status", flag);
		} catch (Exception e) {
			// TODO: handle exception
			result.put("message", "数据异常，请联系客服");
			result.put("status", false);
		}
		return result;
	}
	
	
	/**
	 * 支付信息
	 */
	@RequestMapping(value="/paymessage",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> payMessage(@RequestParam String page,String size,String userId,HttpServletRequest request){
		Map<String, Object> result = new HashMap<String,Object>();
		int pageCount=Integer.parseInt(size);
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("start", pageCount*(Integer.parseInt(page)-1));
		map.put("size", pageCount);
		map.put("userId", Long.parseLong(userId));
		try {
		List<Payrecords> payrecords = payrecordsService.payMessage(map);
		for (int i = 0; i < payrecords.size(); i++) {
			long formuserid = payrecords.get(i).getPayrecordFromuserid();
			if (0 != formuserid) {
				User user = userService.QueryOpenIdByUserId(formuserid);
				payrecords.get(i).setPayrecordPayreason(ConstantUtils.addname(user.getUserName()));
			}
		}
		result.put("payMessage", JSON.toJSON(payrecords));
		result.put("message", "获取成功");
		result.put("status", true);
		} catch (Exception e) {
			// TODO: handle exception
			result.put("message", "数据异常，请联系客服");
			result.put("status", false);
		}
		return result;
	}
	
	
	/**微信支付回调接口(无用)
	 * 2017年7月10日 上午10:10
	 * type = 0 支付 1 体现
	 */
	@RequestMapping(value="/buygoldcoin" ,method={RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public Map<String, Object> buyGoldCoin(HttpServletRequest request){
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> map = new HashMap<String, Object>();
		String param;
		param = (String) request.getAttribute(BaseUtil.paramKey);
		if (param == null || "".equals(param)) {
			param = request.getParameter(BaseUtil.paramKey);
		}
		JSONObject jsonObject = JSONObject.parseObject(param);
		String moneyNum = jsonObject.getString("money");
		String userId = jsonObject.getString("userId");
		String type = jsonObject.getString("type");
		map.put("userId", Long.parseLong(userId));
		map.put("moneyNum", Long.parseLong(moneyNum));
		Payrecords payrecords = new Payrecords();
		payrecords.setPayrecordUserid(Long.parseLong(userId));
		payrecords.setPayrecordPayreason(ConstantUtils.WeChatRecharge);
		payrecords.setPayrecordMoney(BigDecimal.valueOf(Long.parseLong(moneyNum)));
		payrecords.setPayrecordVersion(0);
		payrecords.setPayrecordStatus((byte)0);
		try {
		if (type == "0" || "0".equals(type)) {//支付
			userService.addMoney(map);//金币增加
			payrecords.setPayrecordTarde((byte) ConstantUtils.ShopOut);
			payrecordsService.addPayMessage(payrecords);//添加消费记录
			result.put("message", "金币增加，记录增加");
			result.put("status", true);
			return result;
		}
		if (type == "1" || "1".equals(type)) {//体现
			long money = userService.selectMoney(Long.parseLong(userId));
			if (money < Long.parseLong(moneyNum)*ConstantUtils.convert) {
				result.put("message", "钱不够了，孩子先充钱吧");
				result.put("status", false);
				return result;
			}
			userService.delMoney(map);//金币减少
			payrecords.setPayrecordTarde((byte) ConstantUtils.ShopIn);
			payrecordsService.addPayMessage(payrecords);//添加消费记录
			result.put("message", "金币减少，记录增加");
			result.put("status", true);
			
			return result;
		}
		} catch (Exception e) {
			// TODO: handle exception
			result.put("message", "数据异常，请联系客服");
			result.put("status", false);
		}
		return result;
	}

	/**
	 * 活动报名接口(可能是免费活动)0:免费,1:收费 2017年7月12日 上午10:57
	 */
	@RequestMapping(value = "/actjoin", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public Map<String, Object> joinActivity(HttpServletRequest request) {
		Map<String, Object> result = new HashMap<String, Object>();
		String param;
		param = (String) request.getAttribute(BaseUtil.paramKey);
		if (param == null || "".equals(param)) {
			param = request.getParameter(BaseUtil.paramKey);
		}
		JSONObject jsonObject = JSONObject.parseObject(param);
		String userId = jsonObject.getString("userId");
		String activityId = jsonObject.getString("activityId");
		String money = jsonObject.getString("money");
//		try {
		Activity activity = activityService.ApplyLimit(Long.parseLong(activityId));
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = dateFormat.format(date);
		String OneTime = dateFormat.format(activity.getActivityStarttime());
		String TwoTime = dateFormat.format(activity.getActivityEndtime());

		try {
			Date nowTime = dateFormat.parse(time);
			Date startTime = dateFormat.parse(OneTime);
			Date endTime = dateFormat.parse(TwoTime);
			if (nowTime.before(startTime) || nowTime.after(endTime)) {
				result.put("message", "不在报名时间段，请核实");
				result.put("status", false);
				return result;
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (activity.getActivityApplylimit() != -1 && (activity.getActivityApplylimit() <= activity.getActivityApplycount())) {
			result.put("message", "报名人数已达上限");
			result.put("status", false);
		} else {
			money = activityService.selectapplymoney(Long.parseLong(activityId));
			if (0 != Integer.parseInt(money)) {
				long onlymoney = userService.selectMoney(Long.parseLong(userId));
				if (onlymoney < Long.parseLong(money)) {
					result.put("message", "钱不够了，孩子先充钱吧");
					result.put("status", false);
					return result;
				}
				Payrecords payrecords = new Payrecords();
				payrecords.setPayrecordUserid(Long.parseLong(userId));
				payrecords.setPayrecordBelongid(Long.parseLong(activityId));
				payrecords.setPayrecordOrderid((long) 0);
				payrecords.setPayrecordPaymethod(ConstantUtils.sPayForGoldCoin);
				payrecords.setPayrecordPayreason(ConstantUtils.payAct);
				payrecords.setPayrecordMoney(BigDecimal.valueOf(Long.parseLong(money)));
				payrecords.setPayrecordTarde((byte) ConstantUtils.ShopOut);
				payrecords.setPayrecordFromuserid((long) 0);
				payrecords.setPayrecordVersion(0);
				payrecords.setPayrecordStatus((byte) 0);
				payrecords.setPayrecordTypeid((long) 2);// 2表示付费活动
				payrecordsService.addPayMessage(payrecords);// 添加消费记录
				result.put("message1", "金币减少，记录增加");

				Map<String, Object> map = new HashMap<String, Object>();
				map.put("userId", Long.parseLong(userId));
				map.put("moneyNum", Long.parseLong(money));// 金币
				map.put("convert", ConstantUtils.delGodCoin);
				userService.delMoney(map);// 金币减少
				
				map.clear();
				map.put("userId", Long.parseLong(userId));
				map.put("userIntegral", Long.parseLong(money));	
				userService.addIntegral(map);//积分增加
				
				Integration integration = new Integration();//积分记录
				integration.setIntegrationId(IdWorker.CreateNewID());
				integration.setIntegrationStatus(0);
				integration.setIntegrationNum(new BigDecimal(Integer.parseInt(money)));
				integration.setIntegrationStudentid(Long.parseLong(userId));
				integration.setIntegrationType(2);
				integration.setIntegrationSrcid((long) 1);//0:支出 1:收入
				integration.setIntegrationReason(ConstantUtils.Conversion);
				integrationService.addmessage(integration);
			}
			activityService.modifyJoinCount(Long.parseLong(activityId));
			// Activity act = activityService.selectbyid(Long.parseLong(activityId));
			Activitypart activitypart = new Activitypart();
			activitypart.setActivitypartId(IdWorker.CreateNewID());
			activitypart.setActivitypartStudentid(Long.parseLong(userId));
			activitypart.setActivitypartActivityid(Long.parseLong(activityId));
			activitypartService.addActivity(activitypart);

		}
		result.put("message", "报名成功");
		result.put("status", true);
//		} catch (Exception e) {
//			// TODO: handle exception
//			result.put("message", "数据异常，请联系客服");
//			result.put("status", false);
//		}
		return result;
	}
	
	
	/**视频解锁
	 * money 是金币
	 * 2017年7月13日 上午11:26
	 */
	@RequestMapping(value="/payvideo",method={RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public Map<String, Object> payVideo(HttpServletRequest request){
		Map<String, Object> result = new HashMap<String, Object>();
		String param;
		param = (String) request.getAttribute(BaseUtil.paramKey);
		if (param == null || "".equals(param)) {
			param = request.getParameter(BaseUtil.paramKey);
		}
		JSONObject jsonObject = JSONObject.parseObject(param);
		String userId = jsonObject.getString("userId");
		String videoId = jsonObject.getString("videoId");
		String money = jsonObject.getString("money");
		try {
		money = videoSerive.selectusermoney(Long.parseLong(videoId));
		Long Money = Long.parseLong(money);
		if (0 == Money) {
			result.put("message", "免费视频，不用解锁");
			result.put("status", false);
			return result;
		}
		long onlymoney = userService.selectMoney(Long.parseLong(userId));
		if (onlymoney < Long.parseLong(money)) {
			result.put("message", "金币不足");
			result.put("status", false);
			return result;
		}
		if (0 != Money) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("userId", Long.parseLong(userId));
			map.put("moneyNum", Money);// 金币
			map.put("convert", ConstantUtils.delGodCoin);
			userService.delMoney(map);// 金币减少
			
			Payrecords payrecords = new Payrecords();
			payrecords.setPayrecordUserid(Long.parseLong(userId));
			payrecords.setPayrecordBelongid(Long.parseLong(videoId));
			payrecords.setPayrecordOrderid((long) 0);
			payrecords.setPayrecordPaymethod(ConstantUtils.sPayForGoldCoin);
			payrecords.setPayrecordPayreason(ConstantUtils.payVideo);
			payrecords.setPayrecordMoney(BigDecimal.valueOf(Long.parseLong(money)));
			payrecords.setPayrecordTarde((byte) ConstantUtils.ShopOut);
			payrecords.setPayrecordFromuserid((long) 0);
			payrecords.setPayrecordVersion(0);
			payrecords.setPayrecordStatus((byte) 0);
			payrecords.setPayrecordTypeid((long) 0);// 2表示付费活动
			payrecordsService.addPayMessage(payrecords);// 添加消费记录
			result.put("message1", "金币减少，记录增加");	
			
			map.clear();
			map.put("userId", Long.parseLong(userId));
			map.put("userIntegral", Long.parseLong(money));	
			userService.addIntegral(map);//积分增加
			
			Integration integration = new Integration();//积分记录
			integration.setIntegrationId(IdWorker.CreateNewID());
			integration.setIntegrationStatus(0);
			integration.setIntegrationNum(new BigDecimal(Integer.parseInt(money)));
			integration.setIntegrationStudentid(Long.parseLong(userId));
			integration.setIntegrationType(2);
			integration.setIntegrationSrcid((long) 1);//0:支出 1:收入
			integration.setIntegrationReason(ConstantUtils.Conversion);
			integrationService.addmessage(integration);
		}
		result.put("message", "视频解锁");
		result.put("status", true);
		} catch (Exception e) {
			// TODO: handle exception
			result.put("message", "数据异常，请联系客服");
			result.put("status", false);
		}
		return result;
	}
	
	
	/**商品购买
	 * 2017年7月13日 上午11:26
	 * 1待付款,2,待收货
	 */
	@RequestMapping(value="/payshop",method={RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public Map<String, Object> payshop(HttpServletRequest request){
		Map<String, Object> result = new HashMap<String, Object>();
		String param;
		param = (String) request.getAttribute(BaseUtil.paramKey);
		if (param == null || "".equals(param)) {
			param = request.getParameter(BaseUtil.paramKey);
		}
		JSONObject jsonObject = JSONObject.parseObject(param);
		String goods = jsonObject.getString("goods");
		String userId = jsonObject.getString("userId");
		String money = jsonObject.getString("money");
		String type = jsonObject.getString("type");
		if (null == goods || "".equals(goods) || null == userId || "".equals(userId) || null == type || "".equals(type) || Long.parseLong(type) < 0 ||Long.parseLong(type) > 2) {
			result.put("message", "参数未全或者错误");
			result.put("status",false);
			return result;
		}

		try {
		long onlymoney = userService.selectMoney(Long.parseLong(userId));
		Order order = new Order();
		order.setOrderId(IdWorker.CreateNewID());
		order.setOrderGoodsid(goods);
		order.setOrderUserid(Long.parseLong(userId));
		order.setOrderAddressid((long)0);
		
		int Money = 0;
		
		// 购物车减少
		String[] goodid = goods.split("\\[\'|\',\'|\'\\]");
		for (int i = 1; i < goodid.length; i = i + 2) {// 第一个字符串是空//循环购物车id，查bookid，再查book信息
			for (int j = 0; j < Integer.parseInt(goodid[i + 1]); j++) {
				long shopId = shopService.BookIdToShopId(Long.parseLong(goodid[i]));
				shopService.deleteShop(shopId);
				if ("2" == type || "2".equals(type)) {
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("bookId", Long.parseLong(goodid[i]));
					map.put("num", Long.parseLong(goodid[i + 1]));
					bookService.changepaycount(map);
				}
			}
			
			String moneynum = bookService.ShowBookMoney(Long.parseLong(goodid[i]));
			Money = Integer.parseInt(moneynum)*Integer.parseInt(goodid[i+1]) + Money;
		}
//	System.out.println(Money);
		order.setOrderMoney((long) Money);
		if ("2" == type || "2".equals(type)) {
			if (onlymoney < Money) {
				order.setOrderStatus(1);
				orderService.addorder(order);
				result.put("orderId", JSON.toJSON(order.getOrderId()));
				result.put("message", "金币不足，请核实");
				result.put("status", false);
				return result;
			}
			Payrecords payrecords = new Payrecords();
			payrecords.setPayrecordUserid(Long.parseLong(userId));
			payrecords.setPayrecordOrderid(order.getOrderId());
			payrecords.setPayrecordBelongid((long)0);
			payrecords.setPayrecordPaymethod(ConstantUtils.sPayForIntegration);
			payrecords.setPayrecordPayreason(ConstantUtils.payGoods);
			payrecords.setPayrecordMoney(BigDecimal.valueOf(Long.parseLong(money)));
			payrecords.setPayrecordTarde((byte) ConstantUtils.ShopOut);
			payrecords.setPayrecordFromuserid((long) 0);
			payrecords.setPayrecordVersion(0);
			payrecords.setPayrecordStatus((byte) 0);
			payrecords.setPayrecordTypeid((long) 1);// 2表示付费活动
			payrecordsService.addPayMessage(payrecords);// 添加消费记录

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("userId", Long.parseLong(userId));
			map.put("moneyNum", (long)Money);// 金币
			map.put("convert", ConstantUtils.delGodCoin);
			userService.delMoney(map);// 金币减少
			result.put("message1", "金币减少，记录增加");
			
			map.clear();
			map.put("userId", Long.parseLong(userId));
			map.put("userIntegral", (long)Money);	
			userService.addIntegral(map);//积分增加
			
			Integration integration = new Integration();//积分记录
			integration.setIntegrationId(IdWorker.CreateNewID());
			integration.setIntegrationStatus(0);
			integration.setIntegrationNum(new BigDecimal(Money));
			integration.setIntegrationStudentid(Long.parseLong(userId));
			integration.setIntegrationType(2);
			integration.setIntegrationSrcid((long) 1);//0:支出 1:收入
			integration.setIntegrationReason(ConstantUtils.payGoodsBack);
			integrationService.addmessage(integration);
		}
		order.setOrderStatus(Integer.valueOf(type));
		
		//增加订单
		orderService.addorder(order);
		
		result.put("orderId", JSON.toJSON(order.getOrderId()));
		result.put("message", "操作成功");
		result.put("status", true);
		} catch (Exception e) {
			// TODO: handle exception
			result.put("message", "数据异常，请联系客服");
			result.put("status", false);
		}
		return result;
	}
	
	/**确认支付
	 * 2017年7月13日 下午3:07
	 */
	@RequestMapping(value="/confirmpay",method={RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public Map<String, Object> confirmpay(HttpServletRequest request){
		Map<String, Object> result = new HashMap<String, Object>();
		String param;
		param = (String) request.getAttribute(BaseUtil.paramKey);
		if (param == null || "".equals(param)) {
			param = request.getParameter(BaseUtil.paramKey);
		}
		JSONObject jsonObject = JSONObject.parseObject(param);
		String userId = jsonObject.getString("userId");
		String orderId = jsonObject.getString("orderId");
		String money = jsonObject.getString("money");
		if (null == orderId || "".equals(orderId) || null == money || "".equals(money)) {
			result.put("message", "参数未全");
			result.put("status", false);
			return result;
		}
		if (0 == Long.parseLong(money) ) {
			result.put("message", "订单金额为0，不需要支付");
			result.put("status", false);
			return result;
		}
		long onlymoney = userService.selectMoney(Long.parseLong(userId));
		if (onlymoney < Long.parseLong(money)) {
			result.put("message", "钱不够了，孩子先充钱吧");
			result.put("status", false);
			return result;
		}
		try {
		String goods = orderService.selectgoods(Long.parseLong(orderId));
		String[] goodid = goods.split("\\[\'|\',\'|\'\\]");
		for (int i = 1; i < goodid.length; i=i+2) {//第一个字符串是空//循环购物车id，查bookid，再查book信息
			for (int j = 0; j < Integer.parseInt(goodid[i+1]); j++) {
//				long shopId = shopService.BookIdToShopId(Long.parseLong(goodid[i]));//购物车减少就不需要了
//				shopService.deleteShop(shopId);
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("bookId", Long.parseLong(goodid[i]));
					map.put("num", Long.parseLong(goodid[i+1]));
					bookService.changepaycount(map);
			}
		}
		orderService.updateStatus(Long.parseLong(orderId));
		
		Payrecords payrecords = new Payrecords();
		payrecords.setPayrecordUserid(Long.parseLong(userId));
		payrecords.setPayrecordOrderid(Long.parseLong(orderId));
		payrecords.setPayrecordBelongid((long)0);
		payrecords.setPayrecordPayreason(ConstantUtils.payGoods);
		payrecords.setPayrecordMoney(BigDecimal.valueOf(Long.parseLong(money)));
		payrecords.setPayrecordTarde((byte) ConstantUtils.ShopOut);
		payrecords.setPayrecordFromuserid((long) 0);
		payrecords.setPayrecordVersion(0);
		payrecords.setPayrecordStatus((byte) 0);
		payrecords.setPayrecordTypeid((long) 1);// 1表示书籍
		payrecordsService.addPayMessage(payrecords);// 添加消费记录

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", Long.parseLong(userId));
		map.put("moneyNum", Long.parseLong(money));// 金币
		map.put("convert", ConstantUtils.delGodCoin);
		userService.delMoney(map);// 金币减少
		
		map.clear();
		map.put("userId", Long.parseLong(userId));
		map.put("userIntegral", Long.parseLong(money));	
		userService.addIntegral(map);//积分增加
		
		Integration integration = new Integration();//积分记录
		integration.setIntegrationId(IdWorker.CreateNewID());
		integration.setIntegrationStatus(0);
		integration.setIntegrationNum(new BigDecimal(Integer.parseInt(money)));
		integration.setIntegrationStudentid(Long.parseLong(userId));
		integration.setIntegrationType(2);
		integration.setIntegrationSrcid((long) 1);//0:支出 1:收入
		integration.setIntegrationReason(ConstantUtils.payGoodsBack);
		integrationService.addmessage(integration);
		result.put("message1", "金币减少，记录增加");
		result.put("message", "操作成功");
		result.put("status", true);
		} catch (Exception e) {
			// TODO: handle exception
			result.put("message", "数据异常，请联系客服");
			result.put("status", false);
		}
		return result;
	}
	
	/**确认收货
	 * 2017年7月13日 下午3:07 1待付款,2,待收货
	 */
	@RequestMapping(value="/confirm",method={RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public Map<String, Object> confirm(HttpServletRequest request){
		Map<String, Object> result = new HashMap<String, Object>();
		String param;
		param = (String) request.getAttribute(BaseUtil.paramKey);
		if (param == null || "".equals(param)) {
			param = request.getParameter(BaseUtil.paramKey);
		}
		JSONObject jsonObject = JSONObject.parseObject(param);
		String userId = jsonObject.getString("userId");
		String orderId = jsonObject.getString("orderId");
		if (null == orderId || "".equals(orderId) || null == userId || "".equals(userId)) {
			result.put("message", "参数未全");
			result.put("status", false);
			return result;
		}
		int status = orderService.orderStatus(Long.parseLong(orderId));
		if (2 != status) {
			result.put("message", "该订单不需要确认收货");
			result.put("status", false);
			return result;
		}
		try {
		orderService.updateStatu(Long.parseLong(orderId));
		result.put("message", "操作成功");
		result.put("status", true);
		} catch (Exception e) {
			// TODO: handle exception
			result.put("message", "数据异常，请联系客服");
			result.put("status", false);
		}
		return result;
	}
	
	/**积分兑换
	 * 2017年7月13日 下午3:07
	 */
	@RequestMapping(value="/conversion",method={RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public Map<String, Object> conversion(HttpServletRequest request){
		Map<String, Object> result = new HashMap<String, Object>();
		String param;
		param = (String) request.getAttribute(BaseUtil.paramKey);
		if (param == null || "".equals(param)) {
			param = request.getParameter(BaseUtil.paramKey);
		}
		JSONObject jsonObject = JSONObject.parseObject(param);
		String userId = jsonObject.getString("userId");
		String intgral = jsonObject.getString("intgral");
		try {
		long num = userService.integral(Long.parseLong(userId));
		if (num < Long.parseLong(intgral)) {
			result.put("message", "积分不足");
			result.put("status", false);
			return result;
		}

		Payrecords payrecords = new Payrecords();
		payrecords.setPayrecordUserid(Long.parseLong(userId));
		payrecords.setPayrecordBelongid((long)0);
		payrecords.setPayrecordOrderid((long)0);
		payrecords.setPayrecordPaymethod(ConstantUtils.sPayForIntegration);
		payrecords.setPayrecordPayreason(ConstantUtils.Conversion);
		payrecords.setPayrecordMoney(BigDecimal.valueOf(Integer.parseInt(intgral)/ConstantUtils.Conver));
		payrecords.setPayrecordTarde((byte) ConstantUtils.ShopIn);
		payrecords.setPayrecordFromuserid((long) 0);
		payrecords.setPayrecordVersion(0);
		payrecords.setPayrecordStatus((byte) 0);
		payrecords.setPayrecordTypeid((long) 3);// 3表示积分兑换
		payrecordsService.addPayMessage(payrecords);// 添加消费记录
		
		Integration integration = new Integration();//积分记录
		integration.setIntegrationId(IdWorker.CreateNewID());
		integration.setIntegrationStatus(0);
		integration.setIntegrationNum(new BigDecimal(Integer.parseInt(intgral)));
		integration.setIntegrationStudentid(Long.parseLong(userId));
		integration.setIntegrationType(2);
		integration.setIntegrationSrcid((long) 1);//0:支出 1:收入
		integration.setIntegrationReason(ConstantUtils.Conversion);
		integrationService.addmessage(integration);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", Long.parseLong(userId));
		map.put("userRemainIntgral", Long.parseLong(intgral));
		map.put("moneyNum", Long.parseLong(intgral)/100);
		userService.conversion(map);
		result.put("message", "兑换成功");
		result.put("status", true);
	} catch (Exception e) {
		// TODO: handle exception
		result.put("message", "数据异常，请联系客服");
		result.put("status", false);
	}
		return result;
		
//		boolean flag = false;
//		String jdbcpwd = userService.selectPayPass(Long.parseLong(userId));
//		if (0 == pwd.compareTo(jdbcpwd)) {
//			flag = true;
//			result.put("message", "支付验证成功！");
//		}else {
//			result.put("message", "支付验证失败！密码错误！");
//		}
//		result.put("status", flag);
	}

	
	/**打赏
	 * 2017年7月13日 下午3:07        金币不足，打赏失败
	 */
	@RequestMapping(value="/Interface/V1/reward",method={RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public Map<String, Object> reward(HttpServletRequest request){
		Map<String, Object> result = new HashMap<String, Object>();
		String param;
		param = (String) request.getAttribute(BaseUtil.paramKey);
		if (param == null || "".equals(param)) {
			param = request.getParameter(BaseUtil.paramKey);
		}
		JSONObject jsonObject = JSONObject.parseObject(param);
		String userId = jsonObject.getString("userId");
		String type = jsonObject.getString("type");
		String id = jsonObject.getString("Id");
		String money = jsonObject.getString("money");
		try {
		long onlymoney = userService.selectMoney(Long.parseLong(userId));
		if (onlymoney < Long.parseLong(money)) {
			result.put("message", "钱不够了，孩子先充钱吧");
			result.put("status", false);
			return result;
		}
		
		Reward reward = new Reward();
		reward.setRewardId(Long.parseLong(id));	
		reward.setRewardFromuserid(Long.parseLong(userId));
		reward.setRewardMoney(new BigDecimal(money));
		reward.setRewardStatus((int)0);
		
		Payrecords payrecords = new Payrecords();
		Integration integration = new Integration();//积分记录
		User user = new User();
		if ("0" == type || "0".equals(type) || 0 == Integer.valueOf(type)) {//视频
			long UserId6 = videoSerive.selectVideoUserId(Long.parseLong(id));
			user = userService.seletename(UserId6);
			if (null != user && !"".equals(user)) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("userId", UserId6);
				map.put("moneyNum", Integer.parseInt(money));
				/*int num = */userService.addMoney(map);// 金币增加
				
				payrecords.setPayrecordUserid(UserId6);
				payrecords.setPayrecordOrderid((long)0);
				payrecords.setPayrecordBelongid(Long.parseLong(id));
				payrecords.setPayrecordPaymethod(ConstantUtils.sPayForGoldCoin);
				payrecords.setPayrecordPayreason(ConstantUtils.reward);
				payrecords.setPayrecordMoney(BigDecimal.valueOf(Long.parseLong(money)));
				payrecords.setPayrecordTarde((byte) ConstantUtils.ShopIn);
				payrecords.setPayrecordFromuserid(Long.parseLong(userId));
				payrecords.setPayrecordVersion(0);
				payrecords.setPayrecordTypeid((long) 6);// 6表示打赏视频 7表示帖子
				payrecords.setPayrecordStatus((byte) 0);
				payrecordsService.addPayMessage(payrecords);// 添加消费记录
				
				reward.setRewardUserid(UserId6);
			}
			payrecords.setPayrecordTypeid((long) 6);// 6表示打赏视频 7表示帖子
			payrecords.setPayrecordPayreason(ConstantUtils.videoreward);
			reward.setRewardType(0);
			integration.setIntegrationType(6);//6打赏视频 7打赏帖子
		}
		if ("1" == type || "1".equals(type) || 1 == Integer.valueOf(type)) {//帖子
			long UserId7 = noteService.queryuseridbyid(Long.parseLong(id));
			user = userService.seletename(UserId7);
			if (null != user && !"".equals(user)) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("userId", UserId7);
				map.put("moneyNum", Integer.parseInt(money));
				/*int num = */userService.addMoney(map);// 金币增加
				
				payrecords.setPayrecordUserid(UserId7);
				payrecords.setPayrecordOrderid((long)0);
				payrecords.setPayrecordBelongid(Long.parseLong(id));
				payrecords.setPayrecordPaymethod(ConstantUtils.sPayForGoldCoin);
				payrecords.setPayrecordPayreason(ConstantUtils.reward);
				payrecords.setPayrecordMoney(BigDecimal.valueOf(Long.parseLong(money)));
				payrecords.setPayrecordTarde((byte) ConstantUtils.ShopIn);
				payrecords.setPayrecordFromuserid(Long.parseLong(userId));
				payrecords.setPayrecordVersion(0);
				payrecords.setPayrecordTypeid((long)7);// 6表示打赏视频 7表示帖子
				payrecords.setPayrecordStatus((byte)0);
				payrecordsService.addPayMessage(payrecords);// 添加消费记录
				
				reward.setRewardUserid(UserId7);
			}
			payrecords.setPayrecordTypeid((long)7);// 6表示打赏视频 7表示帖子
			payrecords.setPayrecordPayreason(ConstantUtils.notereward);
			reward.setRewardType(1);
			integration.setIntegrationType(7);//6打赏视频 7打赏帖子
		}
		int num = rewardService.insert(reward);
		if (num != 1) {
			result.put("message", "打赏失败，请联系客服");
			result.put("status", false);
			return result;
		}
		
		payrecords.setPayrecordUserid(Long.parseLong(userId));
		payrecords.setPayrecordOrderid((long)0);
		payrecords.setPayrecordBelongid(Long.parseLong(id));
		payrecords.setPayrecordPaymethod(ConstantUtils.sPayForGoldCoin);
		payrecords.setPayrecordMoney(BigDecimal.valueOf(Long.parseLong(money)));
		payrecords.setPayrecordTarde((byte) ConstantUtils.ShopOut);
		payrecords.setPayrecordFromuserid((long) 0);
		payrecords.setPayrecordVersion(0);
		payrecords.setPayrecordStatus((byte) 0);
		payrecordsService.addPayMessage(payrecords);// 添加消费记录

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", Long.parseLong(userId));
		map.put("moneyNum", Long.parseLong(money));// 金币
		map.put("convert", ConstantUtils.delGodCoin);
		userService.delMoney(map);// 金币减少
		
		map.clear();
		map.put("userId", Long.parseLong(userId));
		map.put("userIntegral", Long.parseLong(money));	
		userService.addIntegral(map);//积分增加
		
		integration.setIntegrationId(IdWorker.CreateNewID());
		integration.setIntegrationStatus(0);
		integration.setIntegrationNum(new BigDecimal(Integer.parseInt(money)));
		integration.setIntegrationStudentid(Long.parseLong(userId));
		integration.setIntegrationReason(ConstantUtils.reward);
		integrationService.addmessage(integration);
		
		result.put("message", "打赏成功");
		result.put("status", true);
		} catch (Exception e) {
			// TODO: handle exception
			result.put("message", "数据异常，请联系客服");
			result.put("status", false);
		}
		return result;
	}
	
	/** 扣积分接口
	 * @created 2017年10月8日 上午11:04:33
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/Interface/V2/DeductIntegral",method={RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public Map<String, Object> DeductIntegralMethod(HttpServletRequest request){
		Map<String, Object> mapReturn = new HashMap<String, Object>();
		String param;
		param = (String) request.getAttribute(BaseUtil.paramKey);
		if (param == null || "".equals(param)) {
			param = request.getParameter(BaseUtil.paramKey);
		}
		JSONObject jsonObject = JSONObject.parseObject(param);
		String sUserId = jsonObject.getString("userId");
		String sIntegral = jsonObject.getString("integral");
		String sReason = jsonObject.getString("reason");
		
		return mapReturn;
	}
	
	
	
	/**
	 * @Title: GoldTransact
	 * @Description: TODO( 纯增加金币 )
	 * @param request
	 * @return  参数
	 * @return Map<String,Object>  返回类型
	 * @throws
	 */
	@RequestMapping(value="/Interface/V2/GoldTransact",method={RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public Map<String, Object> GoldTransactMethod(HttpServletRequest request){
		Map<String, Object> mapQuery = new HashMap<String, Object>();
		String param;
		param = (String) request.getAttribute(BaseUtil.paramKey);
		if (param == null || "".equals(param)) {
			param = request.getParameter(BaseUtil.paramKey);
		}
		JSONObject jsonObject = JSONObject.parseObject(param);
		User pjUser = JSON.parseObject(jsonObject.getString(User.sUserClass), User.class);
		
		if (pjUser.getUserMoney() == null || pjUser.getUserMoney() < 0) {
			return ResultUtil.sharedInstance().FalseData("参数错误，请重试", ReturnCodeUtils.Code.NO.getCode());
		}
		
		Payrecords payrecords = new Payrecords();
		payrecords.setPayrecordUserid(pjUser.getUserId());
		payrecords.setPayrecordOrderid((long)0);
		payrecords.setPayrecordPaymethod(ConstantUtils.AppleRecharge);
		payrecords.setPayrecordPayreason(ConstantUtils.AppleRechargeGold);
		payrecords.setPayrecordMoney(BigDecimal.valueOf(pjUser.getUserMoney()));
		payrecords.setPayrecordTarde((byte) ConstantUtils.ShopIn);
		payrecords.setPayrecordFromuserid((long) 0);
		payrecords.setPayrecordVersion(0);
		payrecords.setPayrecordStatus((byte) 0);
		payrecords.setPayrecordTypeid((long) 5);
		payrecordsService.addPayMessage(payrecords);// 添加消费记录

		mapQuery.put("userId", pjUser.getUserId());
		mapQuery.put("userIntegral", pjUser.getUserMoney());	
		userService.addIntegral(mapQuery);//积分增加
		
		Integration integration = new Integration();//积分记录
		integration.setIntegrationId(IdWorker.CreateNewID());
		integration.setIntegrationStatus(0);
		integration.setIntegrationNum(new BigDecimal(pjUser.getUserMoney()));
		integration.setIntegrationStudentid(pjUser.getUserId());
		integration.setIntegrationReason(ConstantUtils.AppleRecharge_Integration);
		integration.setIntegrationSrcid((long) ConstantUtils.ShopIn);
		integration.setIntegrationType(20);
		integrationService.addmessage(integration);
		
		return ResultUtil.sharedInstance().TrueData(null, "充值成功", ReturnCodeUtils.Code.OK.getCode());
	}
	
	
	/**
	 * @Title: JoinActMethod
	 * @Description: TODO( 一切为了测试 )
	 * @param request
	 * @return  参数
	 * @return Map<String,Object>  返回类型
	 * @throws
	 */
	@RequestMapping(value = "/Interface/V2/JoinCollect", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public Map<String, Object> JoinCollecttMethod(HttpServletRequest request) {
		String param;
		param = (String) request.getAttribute(BaseUtil.paramKey);
		if (param == null || "".equals(param)) {
			param = request.getParameter(BaseUtil.paramKey);
		}
		JSONObject jsonObject = JSONObject.parseObject(param);
		String userId = jsonObject.getString("userId");
		String activityId = jsonObject.getString("activityId");

		Activitypart activitypart = new Activitypart();
		activitypart.setActivitypartId(IdWorker.CreateNewID());
		activitypart.setActivitypartStudentid(Long.parseLong(userId));
		activitypart.setActivitypartActivityid(Long.parseLong(activityId));
		activitypartService.addActivity(activitypart);

		return ResultUtil.sharedInstance().TrueData(null, "收藏成功", ReturnCodeUtils.Code.OK.getCode());
	}
	
	/**
	 * @Title: ViewChargesMethod
	 * @Description: TODO( 查看收费(阅读书目-训练集) )
	 * @param request
	 * @return  参数
	 * @return Map<String,Object>  返回类型
	 * @throws
	 */
	@RequestMapping(value="/Interface/V2/ViewCharges",method={RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public Map<String, Object> ViewChargesMethod(HttpServletRequest request){
		String param;
		param = (String) request.getAttribute(BaseUtil.paramKey);
		if (param == null || "".equals(param)) {
			param = request.getParameter(BaseUtil.paramKey);
		}
		JSONObject jsonObject = JSONObject.parseObject(param);
		User pjUser = JSON.parseObject(jsonObject.getString(User.sUserClass), User.class);
		ReadBook pjReadBook = JSON.parseObject(jsonObject.getString(ReadBook.sReadBookClass), ReadBook.class);
		pjReadBook.setPrice("10");//收费十金币一次
		if (pjReadBook == null || pjUser == null) {
			return ResultUtil.sharedInstance().FalseData("参数错误，请重试", ReturnCodeUtils.Code.NO.getCode());
		}
		
		Payrecords payrecords = new Payrecords();
		payrecords.setPayrecordUserid(pjUser.getUserId());
		payrecords.setPayrecordBelongid(pjReadBook.getBookId());
		payrecords.setPayrecordOrderid((long)0);
		payrecords.setPayrecordPaymethod(ConstantUtils.sViewChargesBook);
		payrecords.setPayrecordPayreason(ConstantUtils.sViewChargesBook);
		payrecords.setPayrecordMoney(BigDecimal.valueOf(Long.parseLong(pjReadBook.getPrice())));
		payrecords.setPayrecordTarde((byte) ConstantUtils.ShopOut);
		payrecords.setPayrecordFromuserid((long) 0);
		payrecords.setPayrecordVersion(0);
		payrecords.setPayrecordStatus((byte) 0);
		payrecords.setPayrecordTypeid((long) 9);//9:查看付费书籍
		payrecordsService.addPayMessage(payrecords);// 添加消费记录
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", pjUser.getUserId());
		map.put("moneyNum", pjReadBook.getPrice());// 金币
		map.put("convert", ConstantUtils.delGodCoin);
		userService.delMoney(map);// 金币减少
		
		return ResultUtil.sharedInstance().TrueData(null, "付费成功", ReturnCodeUtils.Code.OK.getCode());
	}
	
	
}
