package com.kjyl.controller;

import io.swagger.annotations.Api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeAppMergePayModel;
import com.alipay.api.domain.AlipayTradeWapPayModel;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import com.alipay.api.response.AlipayTradeWapPayResponse;
import com.kjyl.util.ResultUtil;
import com.kjyl.util.H5Pay.MD5Utils;
import com.kjyl.util.H5Pay.StringUtils;
import com.kjyl.util.H5Pay.TimeUtils;
import com.kjyl.util.H5Pay.WebUtils;
import com.kjyl.util.H5Pay.XmlUtils;
import com.kjyl.util.WeChatPay.Md5Util;
import com.kjyl.util.util.ClientResponseHandler;
import com.kjyl.util.util.ConstantUtil;
import com.kjyl.util.util.TenpayHttpClient;
import com.kjyl.util.util.WeChatPayRequestHandler;
import com.kjyl.util.util.WeChatPayResponseHandler;
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
	 * @throws IOException 
	 * 支付宝支付 沙箱
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
	public void AlipaySandBox(@RequestBody String data, HttpServletRequest request, HttpServletResponse response) throws AlipayApiException, IOException {
		logger.info("进入支付宝沙箱支付");
		Order temp = JSON.parseObject(data, Order.class);
		AlipayClient alipayClient = new DefaultAlipayClient(Constant.Alipay_SandBox_Webway_Key, Constant.Alipay_SandBox_Appid_Key,
				Constant.Alipay_SandBox_Private_Key, "json", Constant.Alipay_SandBox_Charset_Key, Constant.Alipay_SandBox_Public_Key, Constant.Alipay_SandBox_Sign_Type_Key);
		AlipayTradeWapPayRequest alipaywap = new AlipayTradeWapPayRequest();
		AlipayTradeWapPayModel model = new AlipayTradeWapPayModel();
		alipaywap.setReturnUrl(Constant.Alipay_SandBox_Return_URL_Key);
		alipaywap.setNotifyUrl(Constant.Alipay_SandBox_Notify_URL_Key);
		model.setBody("可选-测试描述文字");
		model.setSubject("必选-测试标题");
		model.setOutTradeNo(temp.getId());
		model.setTotalAmount("0.01");//必选
		model.setQuitUrl("http://www.taobao.com/product/113714.html");
		model.setProductCode("QUICK_WAP_WAY");
		
//		alipaywap.setBizContent("{"
//				+ "\"out_trade_no\":\"" + 1 + "\","
//				+ "\"total_amount\":\"" + 2 + "\","
//				+ "\"subject\":\"" + 3 + "\","
//				+ "\"product_code\":\"QUICK_WAP_WAY\"" 
//				+ "}");
		alipaywap.setBizModel(model);
//		setBizContent("{" +
//				"\"body\":\"对一笔交易的具体描述信息。如果是多种商品，请将商品描述字符串累加传给body。\"," +
//				"\"subject\":\"大乐透\"," +
//				"\"out_trade_no\":\"70501111111S001111119\"," +
//				"\"timeout_express\":\"90m\"," +
//				"\"time_expire\":\"2016-12-31 10:05\"," +
//				"\"total_amount\":9.00," +
//				"\"auth_token\":\"appopenBb64d181d0146481ab6a762c00714cC27\"," +
//				"\"goods_type\":\"0\"," +
//				"\"passback_params\":\"merchantBizType%3d3C%26merchantBizNo%3d2016010101111\"," +
//				"\"quit_url\":\"http://www.taobao.com/product/113714.html\"," +
//				"\"product_code\":\"QUICK_WAP_WAY\"," +
//				"\"promo_params\":\"{\\\"storeIdType\\\":\\\"1\\\"}\"," +
//				"\"extend_params\":{" +
//				"\"sys_service_provider_id\":\"2088511833207846\"," +
//				"\"hb_fq_num\":\"3\"," +
//				"\"hb_fq_seller_percent\":\"100\"," +
//				"\"industry_reflux_info\":\"{\\\\\\\"scene_code\\\\\\\":\\\\\\\"metro_tradeorder\\\\\\\",\\\\\\\"channel\\\\\\\":\\\\\\\"xxxx\\\\\\\",\\\\\\\"scene_data\\\\\\\":{\\\\\\\"asset_name\\\\\\\":\\\\\\\"ALIPAY\\\\\\\"}}\"," +
//				"\"card_type\":\"S0JP0000\"" +
//				"    }," +
//				"\"enable_pay_channels\":\"pcredit,moneyFund,debitCardExpress\"," +
//				"\"disable_pay_channels\":\"pcredit,moneyFund,debitCardExpress\"," +
//				"\"store_id\":\"NJ_001\"," +
//				"\"specified_channel\":\"pcredit\"," +
//				"\"business_params\":\"{\\\"data\\\":\\\"123\\\"}\"," +
//				"\"ext_user_info\":{" +
//				"\"name\":\"李明\"," +
//				"\"mobile\":\"16587658765\"," +
//				"\"cert_type\":\"IDENTITY_CARD\"," +
//				"\"cert_no\":\"362334768769238881\"," +
//				"\"min_age\":\"18\"," +
//				"\"fix_buyer\":\"F\"," +
//				"\"need_check_info\":\"F\"" +
//				"    }" +
//				"  }");
		String form = alipayClient.pageExecute(alipaywap).getBody();
		logger.info("1：" + form);
		AlipayTradeWapPayResponse getResponse = alipayClient.pageExecute(alipaywap);
		form = getResponse.getBody();
		logger.info("2：" + form);
		response.setContentType("text/html;charset=" + Constant.Alipay_Charset_Key);
		response.getWriter().write(form);
		response.getWriter().flush();
	}
	
	/**
	 * @throws AlipayApiException 
	 * @throws IOException 
	 * 支付宝支付 正式
	* @Title: Alipay 
	* @Description: TODO() 
	* @param @param request
	* @param @param response 参数 
	* @return void 返回类型 
	* @throws
	 */
	@RequestMapping(value = "/Alipay")
	public void Alipay(@RequestBody String data, HttpServletRequest request, HttpServletResponse response) throws IOException, AlipayApiException {
		logger.info("进入支付宝正式支付");
		Order temp = JSON.parseObject(data, Order.class);
		AlipayClient alipayClient = new DefaultAlipayClient(Constant.Alipay_Webway_Key, Constant.Alipay_Appid_Key,
				Constant.Alipay_Private_Key, "json", Constant.Alipay_Charset_Key, Constant.Alipay_Public_Key, Constant.Alipay_Sign_Type_Key);
		AlipayTradeWapPayRequest alipaywap = new AlipayTradeWapPayRequest();
		AlipayTradeWapPayModel model = new AlipayTradeWapPayModel();
		alipaywap.setReturnUrl(Constant.Alipay_Return_URL_Key);
		alipaywap.setNotifyUrl(Constant.Alipay_Notify_URL_Key);
		model.setBody("可选-测试描述文字");
		model.setSubject("必选-测试标题");
		model.setOutTradeNo(temp.getId());
		model.setTotalAmount("0.01");//必选
		model.setQuitUrl("http://www.taobao.com/product/113714.html");
		model.setProductCode("QUICK_WAP_WAY");
//		alipaywap.setBizContent("{"
//				+ "\"out_trade_no\":\"" + 1 + "\","
//				+ "\"total_amount\":\"" + 2 + "\","
//				+ "\"subject\":\"" + 3 + "\","
//				+ "\"product_code\":\"QUICK_WAP_WAY\"" 
//				+ "}");	
		alipaywap.setBizModel(model);
		String form = alipayClient.pageExecute(alipaywap).getBody();
		logger.info("1：" + form);
		AlipayTradeWapPayResponse getResponse = alipayClient.pageExecute(alipaywap);
		form = getResponse.getBody();
		logger.info("2：" + form);
		response.setContentType("text/html;charset=" + Constant.Alipay_SandBox_Charset_Key);
		response.getWriter().write(form);
		response.getWriter().flush();
	}
	
	/**
	 * 支付宝 正式环境 异步回调接口 一般来说是Post 80端口 外网环境
	* @Title: Alipay 
	* @Description: TODO() 
	* @param @param request
	* @param @param response 参数 
	* @return void 返回类型 
	* @throws
	 */
	@RequestMapping(value = "/AliPayBack1")
	public String AliPayBack1(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("进入同步回调");
		logger.info("进入同步回调");
		return "sucess";
	}
	
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
	@RequestMapping(value = "/AliPayBackSandBox")
	public String AliPayBackSandBox(HttpServletRequest request, HttpServletResponse response) throws AlipayApiException {
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
	 * 支付宝 正式环境 异步回调接口 一般来说是Post 80端口 外网环境
	* @Title: Alipay 
	* @Description: TODO() 
	* @param @param request
	* @param @param response 参数 
	* @return void 返回类型 
	* @throws
	 */
	@RequestMapping(value = "/AliPayBack")
	public String AliPayBack(HttpServletRequest request, HttpServletResponse response) {
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
	
	
	@RequestMapping(value = "/WeChatPay", method = RequestMethod.POST)
    public
    @ResponseBody
    Map<String, Object> PaymentNotify(HttpServletRequest request, HttpServletResponse response ) throws Exception {
        Map<String, Object> result = new HashMap<String, Object>();
//        String ErrorMessage="";
//        String ErrorCode="";
        //---------------------------------------------------------
        //创建支付应答对象
        WeChatPayResponseHandler resHandler = new WeChatPayResponseHandler(request, response);
        resHandler.setKey(ConstantUtil.PARTNER_KEY);
        //判断签名
        if(resHandler.isTenpaySign()) {
            //通知id
            String appid=resHandler.getParameter("appid");
            String mch_id = resHandler.getParameter("mch_id");
            String transaction_id = resHandler.getParameter("transaction_id");

            String nonce_str=resHandler.getParameter("nonce_str");
            //创建请求对象
            WeChatPayRequestHandler queryReq = new WeChatPayRequestHandler(request, response);
            //通信对象
            TenpayHttpClient httpClient = new TenpayHttpClient();
            //应答对象
            ClientResponseHandler queryRes = new ClientResponseHandler();

            //通过通知ID查询，确保通知来至财付通
            queryReq.init();
            queryReq.setKey(ConstantUtil.PARTNER_KEY);
            queryReq.setGateUrl("https://api.mch.weixin.qq.com/pay/orderquery");
            queryReq.setParameter("appid", appid);
            queryReq.setParameter("mch_id", mch_id);
            queryReq.setParameter("nonce_str", nonce_str);
            queryReq.setParameter("transaction_id", transaction_id);
            //生成Sign并保存在Parameter中
            queryReq.getSign();
            String XML="<xml><appid>"+ appid +"</appid><mch_id>"+ mch_id +"</mch_id><nonce_str>"+ nonce_str +"</nonce_str><transaction_id>"+ transaction_id +"</transaction_id><sign>"+ queryReq.getParameter("sign") +"</sign></xml>";
           // queryReq.setParameter("xml", XML);

            //通信对象
            httpClient.setTimeOut(5);
            httpClient.setPostData(XML);
            //设置请求内容
            httpClient.setReqContent(queryReq.getRequestURL());
            System.out.println("queryReq:" + queryReq.getRequestURL());
            //后台调用
            if(httpClient.call()) {
                //设置结果参数
                queryRes.setContent(httpClient.getResContent());
                System.out.println("queryRes:" + queryRes.getContent());
                queryRes.setKey(ConstantUtil.PARTNER_KEY);
                //获取返回参数
                String retcode = queryRes.getParameter("return_code");
                String trade_state = queryRes.getParameter("trade_state");
                String rltcode = queryRes.getParameter("result_code");

                System.out.println("订单查询成功");
                System.err.print(queryRes.getParameter("attach"));
                //判断签名及结果
                int changeOrderFlag=0;

                com.alibaba.fastjson.JSONObject json = new com.alibaba.fastjson.JSONObject();
//               if(!"order".equals(queryRes.getParameter("attach"))){
                    json = JSON.parseObject(queryRes.getParameter("attach"));
           System.err.print(json.getString("type"));
//                }

                if(queryRes.isTenpaySign()&& "SUCCESS".equals(retcode) && "SUCCESS".equals(trade_state) && "SUCCESS".equals(rltcode) ) {
                	
                    if(/*"order".equals(queryRes.getParameter("attach")) ||*/ (json.containsKey("type") && "order".equals(json.getString("type")))) {//订单付款
                      //containsKey方法——判断是否包含指定的键名 
//                    	  Order order = new Order();
//                        order.setID(Long.parseLong(queryRes.getParameter("out_trade_no")));
//                        order=orderService.searchOrder(order);
//                        if(order !=null) {
//                            Map<String, Object> pushInfo = new HashMap<String, Object>();
//                            pushInfo.put(User.attributeUserID, order.getRealUser());
//                            List<User> lsuser = userService.queryUser(pushInfo);
//                            changeOrderFlag = FinishOrder(order, queryRes.getParameter("bank_type"));
//                            result = ProcPaymentState(order, lsuser.get(0), 1, null, ErrorDetail.getWeChatPayBankType(queryRes.getParameter("bank_type")));
//                        }
                    }else if("1".equals(json.getString("type")) /*|| 1 == Integer.valueOf(json.getString("type"))*/){//充值 原来的值：cerdit
                        String userID=json.getString("user");
                        String money=json.getString("money");
System.out.println(userID+"userid"+money+"money");
                        changeOrderFlag= withdrawMethod(Long.parseLong(userID),Double.parseDouble(money));
                    }else if ("2".equals(json.getString("type"))) {//提现
                    	Map<String, Object> map = new HashMap<String, Object>();
                    	map.put("Id", Long.parseLong(json.getString("Id")));
//						changeOrderFlag = payrecordsService.embodyquery(map);
					}
                    if (changeOrderFlag > 0) {
                        resHandler.sendToCFT("Success");
                        result.put("message", "操作成功");
                        result.put("status", true);
                    } else {
                        result = ResultUtil.sharedInstance().otherError(7, "订单付款失败");
                    }
                }else{
                    //错误时，返回结果未签名，记录retcode、retmsg看失败详情。
                    System.out.println("查询验证签名失败或业务错误");
                    System.out.println("retcode:" + queryRes.getParameter("retcode")+
                            " retmsg:" + queryRes.getParameter("retmsg"));
                }

            } else {
                System.out.println("后台调用通信失败");

                System.out.println(httpClient.getResponseCode());
                System.out.println(httpClient.getErrInfo());
               // 有可能因为网络原因，请求已经处理，但未收到应答。
            }
        }
        else{
            System.out.println("通知签名验证失败");
        }

        return result;
    }
	
    
    @RequestMapping(value = "/WeChatH5Pay", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
    public
    @ResponseBody
    String WeChatH5Pay(@RequestBody String data, HttpServletRequest request, HttpServletResponse response, ModelMap model) {
    	Order temp = JSON.parseObject(data, Order.class);
    	String APPID = "wxc1c75c803e37063d";//微信支付分配的公众账号ID（企业号corpid即为此appId）
        String MERID = "1532253961";//微信支付分配的商户号
        String SIGNKEY = "woshichenliangfeng33032619961129";
        String RequestHeader = "sf1938.com";
        String NotifyUrl = "http://" + RequestHeader + "/Fitness/Pay/WeChatH5PayBack";
		String spbill_create_ip = getIpAddr(request);//生产
System.out.println("spbill_create_ip="+spbill_create_ip);
		//String spbill_create_ip = "";//测试地址，也就是本地真实ip，用于本地测试用
		String scene_info = "{\"h5_info\": {\"type\":\"Wap\",\"wap_url\": \"" + RequestHeader + "\",\"wap_name\": \"信息认证\"}}";//我这里是网页入口，app入口参考文档的安卓和ios写法
		String tradeType = "MWEB";//H5支付标记
		String MD5 = "MD5";//虽然官方文档不是必须参数，但是不送有时候会验签失败
		JSONObject result = new JSONObject();
		String subject = "测试到时候改";//request.getParameter("subject");//前端上送的支付主题
System.out.println("支付标题" + subject);		
		String total_amount = temp.getPrize();//request.getParameter("totalAmount");//前端上送的支付金额
System.out.println("支付金额" + total_amount);		
		String timestamp = String.valueOf(System.currentTimeMillis() / 1000);
		//金额转化为分为单位 微信支付以分为单位
		String finalmoney = StringUtils.getMoney(total_amount);
		int randomNum  = (int) (Math.random() * 1999+5000);
		String out_trade_no = TimeUtils.getSysTime("yyyyMMddHHmmss") + randomNum;
		//随机数 
		String nonce_str= MD5Utils.getMessageDigest(String.valueOf(new Random().nextInt(10000)).getBytes());
		//签名数据
		StringBuilder sb = new StringBuilder();
		sb.append("appid="+APPID);
		sb.append("&body="+subject);
		sb.append("&mch_id="+MERID);
		sb.append("&nonce_str="+nonce_str);
		sb.append("&notify_url=" + NotifyUrl);
		sb.append("&out_trade_no="+out_trade_no);
		sb.append("&scene_info="+scene_info);
		sb.append("&sign_type="+"MD5");
		sb.append("&spbill_create_ip="+spbill_create_ip);
		sb.append("&total_fee="+finalmoney);
		sb.append("&trade_type="+tradeType);
		sb.append("&key="+SIGNKEY);
System.out.println("sb="+sb);
		//签名MD5加密
//"把sb.toString()做MD5操作并且toUpperCase()一下,至于怎么MD5,百度一下或者看官方文档";
		String sign = Md5Util.MD5Encode(sb.toString(), "utf8").toUpperCase();
System.out.println("sign="+sign);
logger.info("签名数据:"+sign);
		//封装xml报文
		String xml="<xml>"+
		        "<appid>"+ APPID+"</appid>"+
		        "<mch_id>"+ MERID+"</mch_id>"+
		        "<nonce_str>"+nonce_str+"</nonce_str>"+
		        "<sign>"+sign+"</sign>"+
		        "<body>"+subject+"</body>"+//
		        "<out_trade_no>"+out_trade_no+"</out_trade_no>"+
		        "<total_fee>"+finalmoney+"</total_fee>"+//
		        "<trade_type>"+tradeType+"</trade_type>"+
		        "<notify_url>"+ NotifyUrl +"</notify_url>"+
		        "<sign_type>MD5</sign_type>"+
		        "<scene_info>"+scene_info+"</scene_info>"+
		        "<spbill_create_ip>"+spbill_create_ip+"</spbill_create_ip>"+
		        "</xml>";
		
		String createOrderURL = "https://api.mch.weixin.qq.com/pay/unifiedorder";//微信统一下单接口
		String mweb_url = "";
		Map map = new HashMap();
		try {
			//预下单 获取接口地址
			map = WebUtils.getMwebUrl(createOrderURL, xml);
			String return_code  = (String) map.get("return_code");
		    String return_msg = (String) map.get("return_msg");
		    if("SUCCESS".equals(return_code) && "OK".equals(return_msg)){
		    	 mweb_url = (String) map.get("mweb_url");//调微信支付接口地址
System.out.println("mweb_url="+mweb_url);
		    }else{
System.out.println("统一支付接口获取预支付订单出错");
		    	result.put("msg", "支付错误");
		    	return result.toString();
		    }
		} catch (Exception e) {
System.out.println("统一支付接口获取预支付订单出错");
		    result.put("msg", "支付错误");
		    return result.toString();
		}
		result.put("mwebUrl",mweb_url);
		
		//添加微信支付记录日志等操作
		result.put("msg", "success");
		return result.toString();
    }
    
	@RequestMapping(value = "/WeChatH5PayBack")
	public void weixinPayNotify(HttpServletRequest request, HttpServletResponse response) throws Exception {
		BufferedReader reader = request.getReader();
        String line = "";
        Map map = new HashMap();
        String xml = "<xml><return_code><![CDATA[FAIL]]></xml>";;
        JSONObject dataInfo = new JSONObject();
        StringBuffer inputString = new StringBuffer();
        while ((line = reader.readLine()) != null) {
            inputString.append(line);
        }
        request.getReader().close();
System.out.println("----接收到的报文---"+inputString.toString());
        if(inputString.toString().length()>0){
        	 map = XmlUtils.parseXmlToList(inputString.toString());
        }else{
System.out.println("接受微信报文为空");
        }
System.out.println("map="+map);
        if(map!=null && "SUCCESS".equals(map.get("result_code"))){
            //成功的业务。。。
        	xml = "<xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[OK]]></return_msg></xml>";
        }else{
        	//失败的业务。。。
        }
        //告诉微信端已经确认支付成功
        response.getWriter().write(xml);
	}
	
    
    /**
	 * 获取用户实际ip
	 * @param request
	 * @return
	 */
	 public String getIpAddr(HttpServletRequest request){  
        String ipAddress = request.getHeader("x-forwarded-for");  
            if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {  
                ipAddress = request.getHeader("Proxy-Client-IP");  
            }  
            if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {  
                ipAddress = request.getHeader("WL-Proxy-Client-IP");  
            }  
            if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {  
                ipAddress = request.getRemoteAddr();  
                if(ipAddress.equals("127.0.0.1") || ipAddress.equals("0:0:0:0:0:0:0:1")){  
                    //根据网卡取本机配置的IP  
                    InetAddress inet=null;  
                    try {  
                        inet = InetAddress.getLocalHost();  
                    } catch (UnknownHostException e) {  
                        e.printStackTrace();  
                    }  
                    ipAddress= inet.getHostAddress();  
                }  
            }  
            //对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割  
            if(ipAddress!=null && ipAddress.length()>15){ //"***.***.***.***".length() = 15  
                if(ipAddress.indexOf(",")>0){  
                    ipAddress = ipAddress.substring(0,ipAddress.indexOf(","));  
                }  
            }  
            return ipAddress;   
	  }
    
    public int withdrawMethod(Long userID,double money) {
//      Boolean isPass=true;
//      PayRecord payRecord = new PayRecord();
//      payRecord.setMoney(money);
//      payRecord.setUserID(userID);
//      payRecord.setPayReason("充值记录");
//      payRecord.setTarde(BaseUtil.TradeType.Credit.getCode());
//      int addRePayRecordFlag = payRecordService.addPayRecord(payRecord);
//      int changeBalanceFlag=0;
//      Map<String, Object> querymap = new HashMap<String, Object>();
//      querymap.put(Balance.attributeBalanceUserID, userID);
//      List<Balance> Balances = balanceService.queryBalance(querymap);
//      if (Balances.size() > 0){
//          Balances.get(0).setMoney(Balances.get(0).getMoney() + payRecord.getMoney());
//          changeBalanceFlag=balanceService.changeBalance(Balances.get(0));
//      }
//      UserLog receiveUserLog = new UserLog(userID);
//      receiveUserLog.setTypeID(LogUtil.ActionType.UserLogin.getCode());
//      receiveUserLog.setNote("充值"+ payRecord.getMoney() +"元");
//      return  userLogService.addUserLog(receiveUserLog);
		Map<String, Object> map = new HashMap<String, Object>();
//		Payrecords payRecord = new Payrecords();
System.out.println("加入方法！！！！！！！！！！");
//		payRecord.setPayrecordPayreason(ConstantUtils.WeChatRecharge);// 充值金币
//		map.put("userId", userID);
//		map.put("moneyNum", money/1);
//		payRecord.setPayrecordUserid(userID);
//		payRecord.setPayrecordBelongid((long)0);
//		payRecord.setPayrecordOrderid((long)0);
//		payRecord.setPayrecordMoney(BigDecimal.valueOf(money));
//		payRecord.setPayrecordVersion(0);
//		payRecord.setPayrecordStatus((byte) 0);
//		payRecord.setPayrecordFromuserid((long) 0);
//		payRecord.setPayrecordTypeid((long)5);
		try {
//		/*int num = */userService.addMoney(map);// 金币增加
//		payRecord.setPayrecordPaymethod(ConstantUtils.sPayForGoldCoin);
//		payRecord.setPayrecordTarde((byte) ConstantUtils.ShopIn);
//		payrecordsService.addPayMessage(payRecord);// 添加消费记录
		} catch (Exception e) {
			// TODO: handle exception
			Properties props=System.getProperties(); //系统属性
//			Errorlogs errorlogs = new Errorlogs();
//			errorlogs.setErrorlogId(IdWorker.CreateNewID());
//			errorlogs.setErrorlogUserid(userID);
//			errorlogs.setErrorlogNote("WechatPay Fail");
//			errorlogs.setErrorlogTypeid((long)6);
//			errorlogs.setErrorlogVersion(0);
//			errorlogs.setErrorlogStatus((byte)0);
//			errorlogs.setErrorlogDeleted((byte)0);
//			errorlogs.setErrorlogDevice(props.getProperty("os.name")+props.getProperty("os.arch")+props.getProperty("os.version"));
//			errorsLogService.addErrorLog(errorlogs);
		}
//System.out.println(num+"num!!!!!!!!!!!");
  	return 1;
  }
	
	
}
