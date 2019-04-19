package com.kjyl.util.AliPay;

import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import com.kjyl.config.Ali.Constant;
import com.kjyl.pojo.Order;
import com.kjyl.pojo.User;
import com.kjyl.service.UserService;
import com.kjyl.util.WeChatPay.ConstantUtil;
import com.kjyl.util.WeChatPay.Md5Util;
import com.kjyl.util.WeChatPay.PrepayIdRequestHandler;
import com.kjyl.util.WeChatPay.WXUtil;

public class WeChatPaySign {
	/**
	 * weChat微信加签
	 * @param orders
	 * @param request
	 * @param response
	 * @return
	 */
		public static String weChatSignPrams(Order pojo, UserService userService, HttpServletRequest request, HttpServletResponse response) {
System.out.println("进入微信加签方法");
			Map<String, Object> map = new HashMap<String, Object>();
			// 获取生成预支付订单的请求类
			PrepayIdRequestHandler prepayReqHandler = new PrepayIdRequestHandler(request, response);
			String totalFee = pojo.getPrize() + "";
//System.out.println(pojo.getPrize());
//System.out.println(totalFee);

			int total_fee = (int) (Float.valueOf(totalFee) * 100);
			prepayReqHandler.setParameter("appid", ConstantUtil.APP_ID);
			prepayReqHandler.setParameter("body", ConstantUtil.BODY);
			prepayReqHandler.setParameter("mch_id", ConstantUtil.MCH_ID);
			String nonce_str = WXUtil.getNonceStr();
			prepayReqHandler.setParameter("nonce_str", nonce_str);
			prepayReqHandler.setParameter("notify_url", ConstantUtil.NOTIFY_URL);
			String out_trade_no = pojo.getId();
			prepayReqHandler.setParameter("out_trade_no", out_trade_no);
			prepayReqHandler.setParameter("spbill_create_ip", request.getRemoteAddr());
			String timestamp = WXUtil.getTimeStamp();
			prepayReqHandler.setParameter("time_start", timestamp);
System.out.println(String.valueOf(total_fee));
			prepayReqHandler.setParameter("total_fee", String.valueOf(total_fee));
			prepayReqHandler.setParameter("trade_type", "APP");
			/**
			 * 注意签名（sign）的生成方式，具体见官方文档（传参都要参与生成签名，且参数名按照字典序排序，最后接上APP_KEY,转化成大写）
			 */
			prepayReqHandler.setParameter("sign", prepayReqHandler.createMD5Sign());
			prepayReqHandler.setGateUrl(ConstantUtil.GATEURL);
			String prepayid;
			try {
				prepayid = prepayReqHandler.sendPrepay();
				// 若获取prepayid成功，将相关信息返回客户端
				if (prepayid != null && !prepayid.equals("")) {
					String signs = "appid=" + ConstantUtil.APP_ID + "&noncestr=" + nonce_str
							+ "&package=Sign=WXPay&partnerid=" + ConstantUtil.PARTNER_ID + "&prepayid=" + prepayid
							+ "×tamp=" + timestamp + "&key=" + ConstantUtil.APP_KEY;
					map.put("code", 200);
					map.put("info", "success");
					map.put("prepayid", prepayid);
					try {
System.out.println("userid  "+pojo.getUseId());
						User user = userService.SearchBySpecial(pojo.getUseId());
//						String oldPhone = (String) request.getAttribute("oldPhone");
//						String openid = userService.findWeChatIdByPhone(oldPhone,"type");
System.out.println("openid" + user.getWeChatOpenId());
						map.put("openid", user.getWeChatOpenId());
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					/**
					 * 签名方式与上面类似
					 */
					map.put("sign", Md5Util.MD5Encode(signs, "utf8").toUpperCase());
					map.put("appid", ConstantUtil.APP_ID);
					map.put("timestamp", timestamp); // 等于请求prepayId时的time_start
					map.put("noncestr", nonce_str); // 与请求prepayId时值一致
					map.put("package", "Sign=WXPay"); // 固定常量
					map.put("partnerid", ConstantUtil.PARTNER_ID);
				} else {
					map.put("code", 400);
					map.put("info", "获取prepayid失败");
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				map.put("code", 405);
				map.put("info", "系统异常");
			}
			return JSON.toJSONString(map);
		}
		
		public static void main(String[] args) {
			String aString = "100";
			System.out.println( (int) (Float.valueOf(Integer.valueOf(aString)) * 100));
		}
}
