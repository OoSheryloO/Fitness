package com.kjyl.controller;

import io.swagger.annotations.Api;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import com.kjyl.config.Ali.Constant;
import com.kjyl.pojo.Order;


/**
 * 
* @ClassName: PayController 
* @Description: TODO() 
* @author: Sheryl 
* @date: 2019年4月12日 
*
 */
@Api("Pay")
@RestController
@RequestMapping("/Pay")
public class PayController extends BaseController{
	
	/**
	 * 支付成功 沙箱环境 异步回调接口 一般来说是Post 80端口 外网环境
	* @Title: AlipaySandBox 
	* @Description: TODO() 
	* @param @param request
	* @param @param response
	* @param @return
	* @param @throws AlipayApiException 参数 
	* @return String 返回类型 
	* @throws
	 */
	@RequestMapping(value = "/AlipaySandBox")
	public String AlipaySandBox(HttpServletRequest request, HttpServletResponse response) throws AlipayApiException {
		logger.info("进入支付宝沙箱环境 异步回调");
		// 获取支付宝GET过来反馈信息
		String reqWay = "";
		if ("GET".equals(request.getMethod())) {
			reqWay = "GET";
		}
		Map<String, String> params = new HashMap<String, String>();
		Map<?, ?> requestParams = request.getParameterMap();
		for (Iterator<?> iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
			}
			// 乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
//			valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
			if ("GET".equals(reqWay)) {
				try {
					valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
				} catch (UnsupportedEncodingException e) {
					logger.info("不支持的编码：" + e.getMessage());
					e.printStackTrace();
				}
			}
			params.put(name, valueStr);
		}
		String trade_no = request.getParameter("trade_no"); // 支付宝交易号
		String trade_status = request.getParameter("trade_status"); // 支付状态
		String out_trade_no = request.getParameter("out_trade_no"); // 系统订单号
		logger.info("支付宝交易号：" + trade_no + ", 返回状态:" + trade_status + ",订单号  :" + out_trade_no);
 
//		//调用SDK验证签名
//		boolean signVerified = AlipaySignature.rsaCheckV1(params, Constant.Alipay_SandBox_Public_Key, Constant.Alipay_SandBox_Charset_Key, Constant.Alipay_SandBox_Sign_Type_Key);
//		/* 实际验证过程建议商户务必添加以下校验：
//		1、需要验证该通知数据中的out_trade_no是否为商户系统中创建的订单号，
//		2、判断total_amount是否确实为该订单的实际金额（即商户订单创建时的金额），
//		3、校验通知中的seller_id（或者seller_email) 是否为out_trade_no这笔单据的对应的操作方（有的时候，一个商户可能有多个seller_id/seller_email）
//		4、验证app_id是否为该商户本身。
//		*/
//		String result = "";
//		if(signVerified) {//验证成功
//			//商户订单号
//			String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");
//			//支付宝交易号
//			String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");
//			//交易状态
//			String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"),"UTF-8");
//			
//			if(trade_status.equals("TRADE_FINISHED")){
//				//判断该笔订单是否在商户网站中已经做过处理
//				//如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
//				//如果有做过处理，不执行商户的业务程序
//					
//				//注意：
//				//退款日期超过可退款期限后（如三个月可退款），支付宝系统发送该交易状态通知
//			}else if (trade_status.equals("TRADE_SUCCESS")){
//				//判断该笔订单是否在商户网站中已经做过处理
//				//如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
//				//如果有做过处理，不执行商户的业务程序
//				
//				//注意：
//				//付款完成后，支付宝系统发送该交易状态通知
//			}
//			result = "success";
//		}else {//验证失败
//			result = "fail";
//		
//			//调试用，写文本函数记录程序运行情况是否正常
//			//String sWord = AlipaySignature.getSignCheckContentV1(params);
//			//AlipayConfig.logResult(sWord);
//		}
		
//		Map<String, Object> map = new HashMap<String, Object>();
		String result = "fail";
		if ("TRADE_SUCCESS".equals(trade_status)) {
			synchronized (this) {
 
				try {//保存订单信息
					Order pojo = this.OrderService.SearchBySpecial(out_trade_no);
					pojo.setStatus(87);//订单完成状态
					this.OrderService.Modify(pojo);
					result = "success";

				} catch (Exception e) {
					e.printStackTrace();
				}
 
			}
		}
		return result;
//		teturn void时
//		map.put(result, result);
//		ResponseUtils.renderText(response, result);
	}
	
	/**
	 * 支付宝 沙箱环境 异步回调接口 一般来说是Post 80端口 外网环境
	* @Title: Alipay 
	* @Description: TODO() 
	* @param @param request
	* @param @param response 参数 
	* @return void 返回类型 
	* @throws
	 */
	@RequestMapping(value = "/Alipay")
	public String Alipay(HttpServletRequest request, HttpServletResponse response) {
		logger.info("进入支付宝正式环境 异步回调");
		// 获取支付宝GET过来反馈信息
		String reqWay = "";
		if ("GET".equals(request.getMethod())) {
			reqWay = "GET";
		}
		Map<String, String> params = new HashMap<String, String>();
		Map<?, ?> requestParams = request.getParameterMap();
		for (Iterator<?> iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
			}
			// 乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
//			valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
			if ("GET".equals(reqWay)) {
				try {
					valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
				} catch (UnsupportedEncodingException e) {
					logger.info("不支持的编码：" + e.getMessage());
					e.printStackTrace();
				}
			}
			params.put(name, valueStr);
		}
		
		String trade_no = request.getParameter("trade_no"); // 支付宝交易号
		String trade_status = request.getParameter("trade_status"); // 支付状态
		String out_trade_no = request.getParameter("out_trade_no"); // 系统订单号
		logger.info("支付宝交易号：" + trade_no + ", 返回状态:" + trade_status + ",订单号  :" + out_trade_no);
 
//		//调用SDK验证签名
//		boolean signVerified = AlipaySignature.rsaCheckV1(params, Constant.Alipay_Public_Key, Constant.Alipay_Charset_Key, Constant.Alipay_Sign_Type_Key);
//		/* 实际验证过程建议商户务必添加以下校验：
//		1、需要验证该通知数据中的out_trade_no是否为商户系统中创建的订单号，
//		2、判断total_amount是否确实为该订单的实际金额（即商户订单创建时的金额），
//		3、校验通知中的seller_id（或者seller_email) 是否为out_trade_no这笔单据的对应的操作方（有的时候，一个商户可能有多个seller_id/seller_email）
//		4、验证app_id是否为该商户本身。
//		*/
//		String result = "";
//		if(signVerified) {//验证成功
//			//商户订单号
//			String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");
//			//支付宝交易号
//			String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");
//			//交易状态
//			String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"),"UTF-8");
//			
//			if(trade_status.equals("TRADE_FINISHED")){
//				//判断该笔订单是否在商户网站中已经做过处理
//				//如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
//				//如果有做过处理，不执行商户的业务程序
//					
//				//注意：
//				//退款日期超过可退款期限后（如三个月可退款），支付宝系统发送该交易状态通知
//			}else if (trade_status.equals("TRADE_SUCCESS")){
//				//判断该笔订单是否在商户网站中已经做过处理
//				//如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
//				//如果有做过处理，不执行商户的业务程序
//				
//				//注意：
//				//付款完成后，支付宝系统发送该交易状态通知
//			}
//			result = "success";
//		}else {//验证失败
//			result = "fail";
//		
//			//调试用，写文本函数记录程序运行情况是否正常
//			//String sWord = AlipaySignature.getSignCheckContentV1(params);
//			//AlipayConfig.logResult(sWord);
//		}
		
//		Map<String, Object> map = new HashMap<String, Object>();
		String result = "fail";
		if ("TRADE_SUCCESS".equals(trade_status)) {
			synchronized (this) {
 
				try {//保存订单信息
					Order pojo = this.OrderService.SearchBySpecial(out_trade_no);
					pojo.setStatus(87);//订单完成状态
					this.OrderService.Modify(pojo);
					result = "success";
				} catch (Exception e) {
					e.printStackTrace();
				}
 
			}
		} 
		return result;
//		teturn void时
//		map.put(result, result);
//		ResponseUtils.renderText(response, result);
	}
	
	
	
	
}
