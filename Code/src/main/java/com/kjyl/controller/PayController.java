package com.kjyl.controller;

import io.swagger.annotations.Api;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import com.kjyl.util.ResultUtil;
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
    public int withdrawMethod(Long userID,double money) {
//        Boolean isPass=true;
//        PayRecord payRecord = new PayRecord();
//        payRecord.setMoney(money);
//        payRecord.setUserID(userID);
//        payRecord.setPayReason("充值记录");
//        payRecord.setTarde(BaseUtil.TradeType.Credit.getCode());
//        int addRePayRecordFlag = payRecordService.addPayRecord(payRecord);
//        int changeBalanceFlag=0;
//        Map<String, Object> querymap = new HashMap<String, Object>();
//        querymap.put(Balance.attributeBalanceUserID, userID);
//        List<Balance> Balances = balanceService.queryBalance(querymap);
//        if (Balances.size() > 0){
//            Balances.get(0).setMoney(Balances.get(0).getMoney() + payRecord.getMoney());
//            changeBalanceFlag=balanceService.changeBalance(Balances.get(0));
//        }
//        UserLog receiveUserLog = new UserLog(userID);
//        receiveUserLog.setTypeID(LogUtil.ActionType.UserLogin.getCode());
//        receiveUserLog.setNote("充值"+ payRecord.getMoney() +"元");
//        return  userLogService.addUserLog(receiveUserLog);
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
