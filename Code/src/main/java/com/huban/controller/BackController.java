package com.huban.controller;


//import org.activiti.engine.impl.util.json.JSONArray;
//import org.activiti.engine.impl.util.json.JSONML;
//import org.activiti.engine.impl.util.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.huban.util.ConstantUtil;
import com.huban.Utils.ConstantUtils;
import com.huban.pojo.Errorlogs;
//import com.alibaba.fastjson.JSONException;
//import com.huban.MyUtils.SmBaseGlobal;
import com.huban.pojo.Payrecords;
import com.huban.pojo.User;
import com.huban.util.BaseUtil;
import com.huban.util.ClientResponseHandler;
import com.huban.util.IdWorker;
import com.huban.util.IdentityWorker;
import com.huban.util.ResultUtil;
import com.huban.util.TenpayHttpClient;
import com.huban.util.WXPayUtil;
import com.huban.util.WeChatPayRequestHandler;
import com.huban.util.WeChatPayResponseHandler;
import com.huban.util.XMLUtil;
//import com.util.LogUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import java.io.BufferedReader;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Random;

/**
 * Created by MeetLucky on 16/5/27.
 */
@Controller
@RequestMapping("/back")
public class BackController extends BaseController{
	/**
	 * 处理微信回调
	 * total_fee金额
	 * bank_type银行类别
	 * out_trade_no订单号
	 * result_code返回状态
	 * appid 
	 * attach
	 * @param request
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
//	@RequestMapping(value = "/payCallBack",method ={RequestMethod.POST,RequestMethod.GET})  
//	public @ResponseBody String payCallBack(HttpServletRequest request,HttpServletResponse response) {
//		System.out.println("微信服务号支付回调");
//		String inputLine;
//		StringBuilder notityXml = new StringBuilder();
//		BufferedReader bufferedReader = null;
//		try {
//			bufferedReader = request.getReader();
//			while ((inputLine = bufferedReader.readLine()) != null)
//				notityXml.append(inputLine);
//			System.out.println(inputLine.toString()+"inputLine");
//			System.out.println(JSON.toJSON(inputLine)+"1");
//			if (bufferedReader != null)
//				bufferedReader.close();
//			if (notityXml.length() < 10) {
//				System.out.println(notityXml.toString()+"notityXml");
//				System.out.println(JSON.toJSON(notityXml)+"2");
//				return "fail";
//			}
//			String total_fee = "", bank_type = "" ;
//			String out_trade_no = "",  result_code = "",appid="";
//			String attach = "";
//			JSONArray result = JSONML.toJSONObject(notityXml.toString()).getJSONArray("childNodes");
//			System.out.println(result+"result");
//			for (int i = 0; i < result.length(); i++) {
//				JSONObject js = result.getJSONObject(i);
//				Object tagName = js.get("tagName");
//				if (tagName.equals("bank_type")) {
//					bank_type = js.getJSONArray("childNodes").getString(0);
//				}  else if (tagName.equals("out_trade_no")) {
//					out_trade_no = js.getJSONArray("childNodes").getString(0);
//				} else if (tagName.equals("result_code")) {
//					result_code = js.getJSONArray("childNodes").getString(0);
//				} else  if (tagName.equals("attach")) {
//					attach = js.getJSONArray("childNodes").getString(0);
//				}else  if (tagName.equals("total_fee")) {
//					total_fee = js.getJSONArray("childNodes").getString(0);
//				}else  if (tagName.equals("appid")) {
//					appid = js.getJSONArray("childNodes").getString(0);
//				}
//			}		
//			System.out.println(total_fee+"+"+bank_type+"+"+out_trade_no+"+"+result_code+"+"+appid+"+"+attach);
//			if (result_code.equals("SUCCESS") &&result_code.equals("SUCCESS")) {
//				PrecessPayCallBackResult(total_fee, attach, out_trade_no,appid, bank_type, request, response);
//				return "<xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[OK]]></return_msg></xml>";//成功后给微信返回数据
//			} else {
//				System.out.println("输出fail一");
//				return "fail";
//			}		
//			
//		} catch (JSONException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		System.out.println("输出fail二");
//		return "fail";
//	}
//	
//	private void PrecessPayCallBackResult(String total_fee,String attach,String out_trade_no,String appid,String bank_type,
//			HttpServletRequest request,HttpServletResponse response){
//		com.alibaba.fastjson.JSONObject jsonobj=com.alibaba.fastjson.JSONObject.parseObject(attach);
//		total_fee = new java.text.DecimalFormat("#0.00").format(Double.parseDouble(total_fee) / 100);// 支付金额以分为单位
//		Payrecords payRecord=new Payrecords();
//		System.out.println("加入方法！！！！！！！！！！");
//		String type = jsonobj.getString("type");
//		String userId = jsonobj.getString("userId");
//		if(type.equals(SmBaseGlobal.PayUse.Recharge.getid())){
////			payRecord.setPayReason("微米充值");
//			payRecord.setPayrecordPayreason(StatusUtil.WeChatshop);//充值金币
//		}else if(type.equals(SmBaseGlobal.PayUse.ActivityPay.getid())){
////			payRecord.setPayReason("活动付款");
//		}else if(type.equals(SmBaseGlobal.PayUse.MatchPay.getid())){
////			payRecord.setPayReason("比赛付款");
//		}else{
////			payRecord.setPayReason("微新闻社付款");
//		}
//		Map<String, Object> map = new HashMap<String, Object>();
////		String moneyNum = jsonObject.getString("money");
////		String userId = jsonObject.getString("userId");
////		String type = jsonObject.getString("type");
////		map.put("userId", userId);
//		map.put("moneyNum", Integer.parseInt(total_fee) * 100);
//		map.put("convert", StatusUtil.convert);
////		payrecords.setPayrecordUserid(Long.parseLong(userId));
//		payRecord.setPayrecordUserid(Long.parseLong(userId));
//		payRecord.setPayrecordMoney(BigDecimal.valueOf(Long.parseLong(total_fee)*100));
//		payRecord.setPayrecordVersion(0);
//		payRecord.setPayrecordStatus((byte)0);
//		if (type == "1" || "1".equals(type)) {//支付
//			userService.addMoney(map);//金币增加
//			payRecord.setPayrecordTarde((byte) StatusUtil.ShopOut);
//			payrecordsService.addPayMessage(payRecord);//添加消费记录
////			result.put("message", "金币增加，记录增加");
//		}
////		payRecord.setStatus(SmBaseGlobal.CheckStatus.Effective.getid());
////		payRecord.setUserID((jsonobj!=null&&jsonobj.containsKey("uid"))?jsonobj.getString("uid"):"0");
////PayRecordService.addPayRecord(payRecord);
////		if(attach!=null && !attach.isEmpty()){
////			ProcessWeChatPayResult(request,response,jsonobj,appid);
////		}
//	}
//	
//	public static void ProcessWeChatPayResult(HttpServletRequest request,HttpServletResponse response,
//			com.alibaba.fastjson.JSONObject attach,String appid){
//		if(attach!=null){
//			if(attach.containsKey("type") && attach.getInteger("type")==SmBaseGlobal.PayUse.Recharge.getid()){
//				String userid=attach.getString("uid");
//				String pirce =attach.getString("pir");
//				String type =attach.getString("type");
//				String text="";
//				if(type.equals(SmBaseGlobal.PayUse.Recharge.getid())){
//					text="微信充值";
//				}
//			}else if(attach.containsKey("type") && attach.getInteger("type")==SmBaseGlobal.PayUse.ActivityPay.getid()){
//			}
//		}
//	}
//	
	
	/**
     * 微信支付 Webhooks通知
     * 订单付款完成后返回的支付通知
     * @return  处理结果的 JSON 字符串
     *          处理成功
     *          {
     *              "status":true,
     *              "data":{}
     *          }
     *
     *          处理失败
     *          {
     *              "status":false,
     *              "code":错误代码,请参见 https://pay.weixin.qq.com/wiki/doc/api/app/app.php?chapter=9_1 最后面的错误码
     *              "message":"错误信息"
     *          }
     *
     */
    @RequestMapping(value = "/payCallBack", method = RequestMethod.POST)
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
						changeOrderFlag = payrecordsService.embodyquery(map);
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
		Payrecords payRecord = new Payrecords();
//System.out.println("加入方法！！！！！！！！！！");
		payRecord.setPayrecordPayreason(ConstantUtils.WeChatRecharge);// 充值金币
		map.put("userId", userID);
		map.put("moneyNum", money/1);
		payRecord.setPayrecordUserid(userID);
		payRecord.setPayrecordBelongid((long)0);
		payRecord.setPayrecordOrderid((long)0);
		payRecord.setPayrecordMoney(BigDecimal.valueOf(money));
		payRecord.setPayrecordVersion(0);
		payRecord.setPayrecordStatus((byte) 0);
		payRecord.setPayrecordFromuserid((long) 0);
		payRecord.setPayrecordTypeid((long)5);
		try {
		/*int num = */userService.addMoney(map);// 金币增加
		payRecord.setPayrecordPaymethod(ConstantUtils.sPayForGoldCoin);
		payRecord.setPayrecordTarde((byte) ConstantUtils.ShopIn);
		payrecordsService.addPayMessage(payRecord);// 添加消费记录
		} catch (Exception e) {
			// TODO: handle exception
			Properties props=System.getProperties(); //系统属性
			Errorlogs errorlogs = new Errorlogs();
			errorlogs.setErrorlogId(IdWorker.CreateNewID());
			errorlogs.setErrorlogUserid(userID);
			errorlogs.setErrorlogNote("WechatPay Fail");
			errorlogs.setErrorlogTypeid((long)6);
			errorlogs.setErrorlogVersion(0);
			errorlogs.setErrorlogStatus((byte)0);
			errorlogs.setErrorlogDeleted((byte)0);
			errorlogs.setErrorlogDevice(props.getProperty("os.name")+props.getProperty("os.arch")+props.getProperty("os.version"));
			errorsLogService.addErrorLog(errorlogs);
		}
  //System.out.println(num+"num!!!!!!!!!!!");
    	return 1;
    }
    /**
     * 处理订单付款后的状态修改
     * @param order
     * @param bank_type
     * @return
     */
//    private int FinishOrder( Order order,String bank_type){
//        order = orderService.searchOrder(order);
//        order.setUser(order.getRealUser());
//        order.setUserID(0);
//        order.setReceive(order.getRealReceive());
//        order.setReceiveID(0);
//        order.setPolling(BaseUtil.CloseStatus.End.getCode());
//        Map<String, Object> pushInfo = new HashMap<String, Object>();
//        pushInfo.put(User.attributeUserID, order.getRealUser());
//        List<User> lsuser = userService.queryUser(pushInfo);
//
//        if (order.getStatus() == BaseUtil.OrderStatus.Finish.getCode() || order.getStatus() == BaseUtil.OrderStatus.ReceiveComomit.getCode()) {
//            //  订单已完成未付款, 需要付款
//            //需要判断接单人是否已经评价,如若是已经评价则变为发单人已付款未评价,接单人已评价状态
//            if (order.getStatus() == BaseUtil.OrderStatus.ReceiveComomit.getCode()) {
//                order.setStatus(BaseUtil.OrderStatus.PayMentNotComment.getCode());
//            } else {
//                order.setStatus(BaseUtil.OrderStatus.Payment.getCode());
//            }
//        }
//        return  orderService.changeOrder(order);
//
//    }
    
    /**
     * 微信提现
     */
    @RequestMapping(value = "/embody", method = {RequestMethod.POST,RequestMethod.GET})
    public
    @ResponseBody
	Map<String, Object> embody(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> result = new HashMap<String, Object>();
		String param;
		param = (String) request.getAttribute(BaseUtil.paramKey);
		if (param == null || param.equals("")) {
			param = request.getParameter(BaseUtil.paramKey);
		}
		Boolean isPass = true;
		JSONObject jsonObject = JSON.parseObject(param);

		String userid = jsonObject.getString("userId");
		// String userString = jsonObject.getString("user");
		// User user = JSON.parseObject(userString, User.class);
		// final Long userID=user.getUserId();
		final Long userID = Long.parseLong(userid);
		User findUser = userService.seletename(userID);

		if (findUser.getUserId() == null || findUser == null || "".equals(findUser)) {
			result = ResultUtil.sharedInstance().otherError(3, "当前用户不存在");
		} else {
			// if(findUser.getUserWechatopenid()==null ||
			// user.getUserWechatopenid().isEmpty()){
			if (findUser.getUserWechatopenid() == null || findUser.getUserWechatopenid().isEmpty()) {
				result = ResultUtil.sharedInstance().otherError(8, "未绑定微信,请先进行绑定在提现");
			} else {
				// Map<String, Object> bankCardQuery = new HashMap<String,
				// Object>();

				String money = jsonObject.getString("money");// 提现金额 单位 元
				Payrecords payrecords = new Payrecords();
				payrecords.setPayrecordId(IdWorker.CreateNewID());
				payrecords.setPayrecordUserid(userID);
				payrecords.setPayrecordMoney(BigDecimal.valueOf(Long.parseLong(money) * ConstantUtils.ebody));
				payrecords.setPayrecordVersion(0);
				payrecords.setPayrecordStatus((byte) 0);
				payrecords.setPayrecordTarde((byte) ConstantUtils.ShopOut);
				payrecords.setPayrecordBelongid((long) 0);
				payrecords.setPayrecordOrderid((long) 0);
				payrecords.setPayrecordFromuserid((long) 0);
				payrecords.setPayrecordTypeid((long) 6);

				int changeBalanceFlag = 0;
				// 根据IOS的发布状态来判断是否正式环境 测试环境不允许提现
				// Map<String, Object> querymap = new HashMap<String, Object>();
				// querymap.put(Balance.attributeBalanceUserID, user.getID());
				// List<Balance> Balances =
				// balanceService.queryBalance(querymap);
				// if (Balances.size() > 0) {
				Long usermoney = userService.selectMoney(userID);
				if ((Integer.parseInt(money) * ConstantUtils.ebody) < (ConstantUtils.ebodyGodCoin / 2000)) {
					result = ResultUtil.sharedInstance().otherError(6, "最少提现金额为20元!");// 测试为0.01元，正式待定
					isPass = false;
				} else if (usermoney - (Integer.parseInt(money) * ConstantUtils.ebody) < 0) {
					result = ResultUtil.sharedInstance().otherError(5, "余额不足,提现失败!");
					isPass = false;
				} else {
					/* 处理提现,金额每两小时提现一次 */
					try {
						String orderNo = String.valueOf(new IdentityWorker(new Random().nextInt(31), 0).nextIdentity());
						// //判断是否实名认证
						// Map<String, Object> querycertif = new HashMap<String,
						// Object>();
						// querycertif.put("userId",findUser.getUserId());
						// List<Certification>
						// certification=certificationService.queryCertification(querycertif);
						// if(certification.size()<=0 || (certification.size()>0 && certification.get(0).getResult()!= BaseUtil.CertificationFlag.Success.getCode())){
						// result = ResultUtil.sharedInstance().otherError(9,
						// "未通过实名认证,请先通过实名认证在进行提现操作");
						// }else {
						// user.setName(certification.get(0).getName());//暂时把实名认证的真实名字赋值给user对象,用于实名认证
						User wayUser = new User();
						wayUser.setUserWechatopenid(findUser.getUserWechatopenid());
						wayUser.setUserName(findUser.getUserName());
						String respcontent = WXPayUtil.pushClient(request, response, wayUser,(int) (Integer.parseInt(money) * Double.parseDouble("100")), orderNo);// 提现金额写死1元钱
																										// 正式发布后修改
						Map<String, Object> mapContent = (Map<String, Object>) XMLUtil.doXMLParse(respcontent);
						if (mapContent.get("return_code").equals("SUCCESS")
								&& mapContent.get("result_code").equals("SUCCESS")) {
							Map<String, Object> map = new HashMap<String, Object>();
							map.put("userId", userID);
							map.put("moneyNum", money);// 这里的money是钱
							map.put("convert", ConstantUtils.ebody);
					System.out.println("okokokokokokokokkokok");
							// Balances.get(0).setMoney(Balances.get(0).getMoney()
							// - payRecord.getMoney());
							changeBalanceFlag = userService.delMoney(map);// 金币减少

							payrecords.setPayrecordPaymethod(orderNo);// 记录提现的微信单号
							payrecords.setPayrecordPayreason(ConstantUtils.embody);
							int addRePayRecordFlag = payrecordsService.addPayMessage(payrecords);// 添加消费记录
							result.put("Id", payrecords.getPayrecordId());
							if (addRePayRecordFlag == 0 && changeBalanceFlag == 0) {
								result = ResultUtil.sharedInstance().otherError(BaseUtil.ErrorMessageType.WithdrawFail,request);
								isPass = false;
							}
							// } else {
							// result =
							// ResultUtil.sharedInstance().otherError(7,
							// (String) mapContent.get("return_msg"));
							// isPass = false;
							// }

						} else {// SENDNUM_LIMIT
							if (((String) mapContent.get("err_code")).equals("SENDNUM_LIMIT")) {
								result = ResultUtil.sharedInstance().otherError(BaseUtil.ErrorMessageType.SendNum_limit,
										request);
							} else {
								result = ResultUtil.sharedInstance()
										.otherErrorCusomer((String) mapContent.get("return_msg"), request);
							}
							isPass = false;
						}

						// }
					} catch (Exception e) {
						e.printStackTrace();
						result = ResultUtil.sharedInstance().otherError(7, e.getMessage());
					}
				}

			}
			if (isPass) {
				result.put(BaseUtil.statusKey, true);
				// UserController.addUserLog(userLogService,LogUtil.ActionType.WalletWithdraw,userID,"提现:"+payRecord.getMoney()
				// ,logger);

				Thread thread = new Thread() {
					public void run() {
						// 发单人取消付款
						Errorlogs userLog = new Errorlogs();
						// 4 表示 提现
						userLog.setErrorlogTypeid(
								(long) /*
										 * LogUtil.ActionType.WalletWithdraw.
										 * getCode()
										 */BaseUtil.TradeType.Withdraw.getCode());
						userLog.setErrorlogNote("提现取消");
						int addUserLogFlag = errorsLogService.addErrorLog(userLog);
						logger.info("用户日志:" + addUserLogFlag + JSON.toJSONString(userLog));
					}
				};
				thread.start();
			}

		}
		return result;
	}
       

	
}
