package com.kjyl.util.AliPay;

import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.Map;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import com.kjyl.config.Ali.Constant;
import com.kjyl.pojo.Order;

public class aliPaySign {
	/**
	 * 沙箱环境加签参数
	 * 
	 * @param amount
	 * @param body
	 * @return
	 */
	public static String aliPaySandBoxSignPrams(Order pojo) {
//		Map<String,String> bizModel = new LinkedHashMap<String,String>(); 
//		 //1.商户appid
//		bizModel.put("app_id", AlipayConfig.APPID);
//		//2.请求网关地址
//		bizModel.put("method",AlipayConfig.URL);
//		//3.请求格式
//		bizModel.put("format",AlipayConfig.FORMAT);
//		//4.回调地址
//		bizModel.put("return_url",AlipayConfig.return_url);
//		//5.私钥
//		bizModel.put("private_key",AlipayConfig.RSA_PRIVATE_KEY);
//		//6.商家id
//		bizModel.put("seller_id","2088102170411333");
//		//7.加密格式
//		bizModel.put("sign_type",AlipayConfig.SIGNTYPE+"");
		// 实例化客户端
		AlipayClient alipayClient = new DefaultAlipayClient(Constant.Alipay_SandBox_Webway_Key, Constant.Alipay_SandBox_Appid_Key,
				Constant.Alipay_SandBox_Private_Key, "json", Constant.Alipay_SandBox_Charset_Key, Constant.Alipay_SandBox_Public_Key, "RSA2");
//		AlipayClient alipayClient = new DefaultAlipayClient(SystemValue.ALIPAY_WEBWAY, SystemValue.ALIPAY_APPID,
//				SystemValue.ALIPAY_PRIVATE_KEY, "json", SystemValue.ALIPAY_CHARSET, SystemValue.ALIPAY_PUBLIC_KEY,
//				"RSA2");
		// 实例化具体API对应的request类,类名称和接口名称对应,当前调用接口名称：alipay.trade.app.pay
		AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
		// SDK已经封装掉了公共参数，这里只需要传入业务参数。以下方法为sdk的model入参方式(model和biz_content同时存在的情况下取biz_content)。
		AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
//		model.setPassbackParams(URLEncoder.encode((String)orderMap.get("body").toString()));;  //描述信息  添加附加数据 
//		model.setBody(orderMap.get("body"));                        //商品信息
//		model.setSubject(orderMap.get("subject"));                  //商品名称
//		model.setOutTradeNo(orderMap.get("out_trade_no"));          //商户订单号(自动生成)
//		model.setTimeoutExpress(orderMap.get("timeout_express"));     //交易超时时间
//		model.setTotalAmount(orderMap.get("total_amount"));         //支付金额
//		model.setProductCode(orderMap.get("product_code"));         //销售产品码
//		model.setSellerId("20881021********");                        //商家id 沙箱2088102177282800 正式 2088431997031441
		 
		model.setSubject("测试支付标题"); // 商品标题
		model.setOutTradeNo(pojo.getId()); // 商家订单编号
		// model.setTimeoutExpress("525600m"); //超时关闭该订单时间，默认15天
		model.setTotalAmount("测试支付总金额"); // 订单总金额
		model.setProductCode("QUICK_MSECURITY_PAY"); // 销售产品码，商家和支付宝签约的产品码，为固定值QUICK_MSECURITY_PAY
		request.setBizModel(model);
		request.setNotifyUrl(Constant.Alipay_SandBox_Notify_URL_Key); // 异步回调地址
//		request.setReturnUrl(Constant.Alipay_SandBox_Return_URL_Key); // 同步回调地址 两种说法 1.调用支付宝成功之后的跳转，2.支付成功之后的跳转 不建议使用 会有异常状况卡住
		String orderStr = "";
		try {
			// 这里和普通的接口调用不同，使用的是sdkExecute
			AlipayTradeAppPayResponse response = alipayClient.sdkExecute(request);
			orderStr = response.getBody();
			System.out.println("沙箱环境 订单str：" + orderStr);
//			logger.info("订单str：" + orderStr);
		} catch (AlipayApiException e) {
			e.printStackTrace();
		}
		return orderStr;
	}
	
	
	/**
	 *  正式环境 加签参数
	 * 
	 * @param amount
	 * @param body
	 * @return
	 */
	public static String aliPaySignPrams(Order pojo) {
//		Map<String,String> bizModel = new LinkedHashMap<String,String>(); 
//		 //1.商户appid
//		bizModel.put("app_id", AlipayConfig.APPID);
//		//2.请求网关地址
//		bizModel.put("method",AlipayConfig.URL);
//		//3.请求格式
//		bizModel.put("format",AlipayConfig.FORMAT);
//		//4.回调地址
//		bizModel.put("return_url",AlipayConfig.return_url);
//		//5.私钥
//		bizModel.put("private_key",AlipayConfig.RSA_PRIVATE_KEY);
//		//6.商家id
//		bizModel.put("seller_id","2088102170411333");
//		//7.加密格式
//		bizModel.put("sign_type",AlipayConfig.SIGNTYPE+"");
		// 实例化客户端
		AlipayClient alipayClient = new DefaultAlipayClient(Constant.Alipay_Webway_Key, Constant.Alipay_Appid_Key,
				Constant.Alipay_Private_Key, "json", Constant.Alipay_Charset_Key, Constant.Alipay_Public_Key, "RSA2");
//		AlipayClient alipayClient = new DefaultAlipayClient(SystemValue.ALIPAY_WEBWAY, SystemValue.ALIPAY_APPID,
//				SystemValue.ALIPAY_PRIVATE_KEY, "json", SystemValue.ALIPAY_CHARSET, SystemValue.ALIPAY_PUBLIC_KEY,
//				"RSA2");
		// 实例化具体API对应的request类,类名称和接口名称对应,当前调用接口名称：alipay.trade.app.pay
		AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
		// SDK已经封装掉了公共参数，这里只需要传入业务参数。以下方法为sdk的model入参方式(model和biz_content同时存在的情况下取biz_content)。
		AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
//		model.setPassbackParams(URLEncoder.encode((String)orderMap.get("body").toString()));;  //描述信息  添加附加数据 
//		model.setBody(orderMap.get("body"));                        //商品信息
//		model.setSubject(orderMap.get("subject"));                  //商品名称
//		model.setOutTradeNo(orderMap.get("out_trade_no"));          //商户订单号(自动生成)
//		model.setTimeoutExpress(orderMap.get("timeout_express"));     //交易超时时间
//		model.setTotalAmount(orderMap.get("total_amount"));         //支付金额
//		model.setProductCode(orderMap.get("product_code"));         //销售产品码
//		model.setSellerId("20881021********");    
 
		model.setSubject("标题未写"); // 商品标题
		model.setOutTradeNo(pojo.getId()); // 商家订单编号
		// model.setTimeoutExpress("525600m"); //超时关闭该订单时间，默认15天
		model.setTotalAmount(pojo.getPrize()); // 订单总金额
		model.setProductCode("QUICK_MSECURITY_PAY"); // 销售产品码，商家和支付宝签约的产品码，为固定值QUICK_MSECURITY_PAY
		request.setBizModel(model);
		request.setNotifyUrl(Constant.Alipay_Notify_URL_Key); // 异步回调地址
//		request.setReturnUrl(Constant.Alipay_Return_URL_Key); // 同步回调地址 两种说法 1.调用支付宝成功之后的跳转，2.支付成功之后的跳转 不建议使用 会有异常状况卡住
		String orderStr = "";
		try {
			// 这里和普通的接口调用不同，使用的是sdkExecute
			AlipayTradeAppPayResponse response = alipayClient.sdkExecute(request);
			orderStr = response.getBody();
			System.out.println("正式环境 订单str：" + orderStr);
//			logger.info("订单str：" + orderStr);
		} catch (AlipayApiException e) {
			e.printStackTrace();
		}
		return orderStr;
	}
}
