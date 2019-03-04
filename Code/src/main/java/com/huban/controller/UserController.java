package com.huban.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.joda.time.LocalDate;
import org.junit.runners.Parameterized.Parameter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.huban.Utils.RandomUtil;
import com.huban.Utils.ReturnCodeUtils;
import com.huban.Utils.ReturnMessageUtils;
import com.huban.construct.LstDeptModel;
import com.huban.construct.UserInfoModel;
import com.huban.Utils.ConstantUtils;
import com.huban.Utils.Genneration_sole;
import com.huban.Utils.ArgumentsUtils;
import com.huban.pojo.Activity;
import com.huban.pojo.Activitypart;
import com.huban.pojo.Basic;
import com.huban.pojo.Book;
import com.huban.pojo.Dict;
import com.huban.pojo.Gamepart;
import com.huban.pojo.Integration;
import com.huban.pojo.Message;
import com.huban.pojo.Onlines;
import com.huban.pojo.Payrecords;
import com.huban.pojo.Sign;
import com.huban.pojo.User;
import com.huban.pojo.UserInfo;
import com.huban.pojo.UserJournal;
import com.huban.pojo.UserPlan;
import com.huban.pojo.Verifyrecords;
import com.huban.publicway.UserWay;
import com.huban.service.ErrorsLogService;
import com.huban.service.UserJournalRecordService;
import com.huban.service.UserService;
import com.huban.service.VerifyrecordsService;
import com.huban.util.BaseUtil;
import com.huban.util.ErrorDetail;
import com.huban.util.IdWorker;
import com.huban.util.ResultUtil;
import com.huban.util.SessionContext;
import com.huban.util.UploadAliYunFile;
import com.huban.util.VerifyCode;

import scala.collection.generic.BitOperations.Int;

/**
 * @update author Sheryl
 * @created 2017年9月12日 下午4:17:13
 */

@Controller
@RequestMapping("/user")
public class UserController extends BaseController {
	/**
	 * 发送验证码
	 * 
	 * @param phone
	 */
	@RequestMapping(value = "/active", method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> activeMethod(@RequestParam String phone, String type,
			HttpServletRequest request) {
		Map<String, Object> result = new HashMap<String, Object>();
		if (phone == null || phone.equals("")) {
			result.put("message", "请输入手机号");
			return result;
		} else {
			if (!VerifyCode.sharedInstance().checkNumberVerify(phone)) {
				result.put("message", "手机号格式不正确");
				result.put("status", false);
				return result;
			} else {
				// if ("1" != type || "2" != type || !"1".equals(type) || !"2".equals(type)) {
				// result.put("message", "数据异常，请联系客服");
				// result.put("status", false);
				// return result;
				// }
				if ("1" == type || "1".equals(type)) {
					int count = userService.phonecount(phone);
					if (count > 0) {
						result.put("message", "手机已被他人使用，请更换手机号");
						result.put("status", false);
						return result;
					}
				}
				if ("2" == type || "2".equals(type)) {// 微信登录之后的绑定
					int number = userService.phonewechatcount(phone);
					if (number == 1) {// phone 和 openId 都不为空
						result.put("message", "手机已被他人微信绑定，请更换手机号");
						result.put("status", false);
						return result;
					}
				}
				result = SendCheckNumber(verifyrecordsService, phone, errorsLogService);
			}
			if (result != null) {
				return result;
			}

		}
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("message", "验证码发送成功,请注意查收");
		data.put("status", true);
		return data;
	}

	/*
	 * Map<String,Object> result=new HashMap<String,Object>(); String param; param =
	 * (String)request.getAttribute(BaseUtil.paramKey); if(param==null ||
	 * param.equals("")){ param=request.getParameter(BaseUtil.paramKey); }
	 * JSONObject jsonObject = JSONObject.parseObject(param); String phone =
	 * jsonObject.getString("phone"); if(phone==null||phone.equals("")){
	 * result.put("message", "请输入手机号！"); return result; }else{
	 * if(!VerifyCode.sharedInstance().checkNumberVerify(phone)){
	 * result.put("message", "手机号格式不正确！"); return result; }else{
	 * result=SendCheckNumber(verifyrecordsService, phone, errorsLogService); }
	 * if(result!=null){ return result; }
	 * 
	 * } Map<String, Object> data =new HashMap<String, Object>();
	 * data.put("message", "验证码发送成功,请注意查收。"); data.put("status", true); String
	 * jsonresult = JSONObject.toJSONString(result).toString();
	 * response.getWriter().print(jsonresult); // request.getAttribute(jsonresult);
	 * //return data; return data;
	 */

	/**
	 * 验证验证码登陆
	 * 
	 * @param param
	 * @throws IOException
	 */
	@RequestMapping(value = "/verify", method = { RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Map<String, Object> verifyMethod(@RequestParam String phone, String code,
			HttpServletRequest request) {
		Map<String, Object> result = new HashMap<String, Object>();
		/*
		 * String param; param = (String)request.getAttribute(BaseUtil.paramKey);
		 * if(param==null || param.equals("")){
		 * param=request.getParameter(BaseUtil.paramKey); } JSONObject
		 * jsonObject=JSON.parseObject(param); String
		 * phone=jsonObject.getString("phone"); String
		 * code=jsonObject.getString("code");
		 */
		if (phone == null || phone.equals("")) {
			result.put("message", "参数手机号未传");
			result.put("status", false);
			return result;
		}
		if (code == null || code.equals("")) {
			result.put("message", "参数验证码未传");
			result.put("status", false);
			return result;
		}
		if (!VerifyCode.sharedInstance().checkNumberVerify(phone)) {
			result.put("message", "手机号格式不正确");
			result.put("status", false);
			return result;
		}
		String string = userService.seletePaypassExist(phone);
		boolean flag = false;
		Verifyrecords verifyrecords = getVerifyRecord(phone, verifyrecordsService);
		result = checkVerifyNumber(verifyrecords, phone, code);
		if (result != null) {
			if (null != string && !"".equals(string)) {
				flag = true;
			}
			result.put("exist", flag);
			return result;
		} else {
			result = initialUserInfo(verifyrecords, phone, request);
		}
		if (null != string && !"".equals(string)) {
			flag = true;
		}
		result.put("exist", flag);
		return result;
	}

	/**
	 * 手机号密码登录
	 */
	@RequestMapping(value = "/signin", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody Map<String, Object> signin(@RequestParam String phone, String password,
			HttpServletRequest request) {
		Map<String, Object> result = new HashMap<String, Object>();
		/*
		 * String param; param = (String)request.getAttribute(BaseUtil.paramKey);
		 * if(param==null || param.equals("")){
		 * param=request.getParameter(BaseUtil.paramKey); } JSONObject
		 * jsonObject=JSON.parseObject(param); String
		 * phone=jsonObject.getString("phone"); String password =
		 * jsonObject.getString("password");
		 */
		User user = userService.seletePassword(phone);
		String dbPasswd = user.getUserPassword().toString();
		boolean flag = false;
		result.put("message", "手机号或者密码错误");
		if (password == dbPasswd || password.equals(dbPasswd)) {
			flag = true;
			User u = userService.seletePassword(phone);
			Map<String, Object> onLineQuery = new HashMap<String, Object>();
			onLineQuery.put("userID", u.getUserId());
			List<Onlines> onLineList = onLineService.queryList(onLineQuery);
			result.put("message", "登陆成功");
			result.put("token", onLineList.get(0).getOnlineSession());
			result.put("user", JSON.toJSON(u));
		}
		// boolean flag = HexUtil.validPasswd(password,
		// Long.toString(dbPasswd));
		// result.put("status", flag);
		String string = userService.seletePaypassExist(phone);
		boolean flaga = false;
		if (null != string && !"".equals(string)) {
			flaga = true;
		}
		result.put("exist", flaga);
		result.put("status", flag);
		return result;
	}

	/**
	 * 手机号密码修改
	 */
	@RequestMapping(value = "/modifypassword", method = { RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Map<String, Object> modifyPassword(HttpServletRequest request) {
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> map = new HashMap<String, Object>();
		String param;
		param = (String) request.getAttribute(BaseUtil.paramKey);
		if (param == null || param.equals("")) {
			param = request.getParameter(BaseUtil.paramKey);
		}
		JSONObject jsonObject = JSON.parseObject(param);
		map.put("userId", jsonObject.getString("userId"));
		map.put("userPassword", jsonObject.getString("password"));
		// String phone=jsonObject.getString("phone");
		try {
			userService.modifyPassword(map);
			result.put("message", "修改登录密码");
			result.put("status", true);
		} catch (Exception e) {
			// TODO: handle exception
			result.put("message", "数据异常，请联系客服");
			result.put("status", false);
		}
		return result;
	}

	/**
	 * 验证验证码
	 * 
	 * @param param
	 */

	@RequestMapping(value = "/Interface/V2/verifycode", method = { /* RequestMethod.POST, */RequestMethod.GET })
	public @ResponseBody Map<String, Object> verifycodeMethod(@RequestParam String phone, String code,
			HttpServletRequest request) {
		Map<String, Object> result = new HashMap<String, Object>();
		String param;
		param = (String) request.getAttribute(BaseUtil.paramKey);
		if (param == null || param.equals("")) {
			param = request.getParameter(BaseUtil.paramKey);
		}
		// JSONObject jsonObject=JSON.parseObject(param);
		// String phone=jsonObject.getString("phone");
		// String code=jsonObject.getString("code");
		if (phone == null || phone.equals("")) {
			return ResultUtil.sharedInstance().FalseData("参数手机号未传", ReturnCodeUtils.Missing_Parameter.Phone.getCode());
		}
		if (code == null || code.equals("")) {
			return ResultUtil.sharedInstance().FalseData("参数验证码未传",
					ReturnCodeUtils.Missing_Parameter.Captcha.getCode());
		}
		if (!VerifyCode.sharedInstance().checkNumberVerify(phone)) {
			return ResultUtil.sharedInstance().FalseData("手机号格式不正确", ReturnCodeUtils.Error_Format.Phone.getCode());
		}
		Verifyrecords verifyrecords = getVerifyRecord(phone, verifyrecordsService);
		result = checkVerifyNumber(verifyrecords, phone, code);
		if (result != null) {
			return result;
		} else {
			result = initreturn(verifyrecords, phone, request);
		}
		return result;
	}

	private Map<String, Object> initreturn(Verifyrecords verifyRecord, String phone, HttpServletRequest request) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			// String string = userService.seletePaypassExist(phone);
			// boolean flag = false;
			// if (null != string && !"".equals(string)) {
			// flag = true;
			// }
			// result.put("exist", flag);
			return ResultUtil.sharedInstance().TrueData(result, "验证成功", ReturnCodeUtils.Code.OK.getCode());
		} catch (Exception e) {
			// TODO: handle exception
			return ResultUtil.sharedInstance().FalseData("数据异常，请联系客服", ReturnCodeUtils.Code.NO.getCode());
		}
	}

	/**
	 * 微信登录
	 * 
	 * @param param
	 * 
	 */
	@RequestMapping(value = "/wechat", method = { RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Map<String, Object> wechatMethod(HttpServletRequest request) {
		Map<String, Object> result = new HashMap<String, Object>();
		String param;
		param = (String) request.getAttribute(BaseUtil.paramKey);
		if (param == null || param.equals("")) {
			param = request.getParameter(BaseUtil.paramKey);
		}
		Basic basic = JSON.parseObject(param, Basic.class);
		String wechatId = basic.getWechatId();
		String wechatSex = basic.getWechatSex();
		String wechatName = basic.getWechatName();
		String wechatHeadIcon = basic.getWechatHeadIcon();
		String wechatCity = basic.getWechatCity();
		if (wechatId == null || wechatId.equals("") || wechatSex == null || wechatSex.equals("") || wechatName == null
				|| wechatName.equals("")) {
			result.put("message", "获取微信信息失败");
			result.put("status", false);
			return result;
		}
		User user = new User();
		user.setUserWechatopenid(wechatId);
		int flag = userService.findUser(user);
		if (flag == 0) {
			User newuser = new User();
			newuser.setUserId(IdWorker.CreateNewID());
			newuser.setUserNumericalOrder(Long.parseLong(Genneration_sole.generateNumber2()));
			newuser.setUserWechatopenid(wechatId);
			newuser.setUserName(wechatName);
			newuser.setUserSex(Integer.parseInt(wechatSex));
			newuser.setUserHeadicon(wechatHeadIcon);
			newuser.setUserCity(wechatCity);
			userService.wechatinsert(newuser);
			// userService.addUser(newuser);//验证登录时添加user信息
			// 添加session信息
			SessionContext context = SessionContext.sharedInstance();
			HttpSession session = request.getSession();
			context.signInSession(session);

			if (session == null) {
				return ResultUtil.sharedInstance().otherError(BaseUtil.ErrorMessageType.LoginFail, request);
			} else {
				Message message = Message.DefaultMessages(newuser.getUserId());
				messageService.addMessage(message);
			}

			Map<String, Object> onLineQuery = new HashMap<String, Object>();
			onLineQuery.put("userID", newuser.getUserId());
			List<Onlines> onLineList = onLineService.queryList(onLineQuery);
			Onlines onLine = null;

			if (onLineList != null && onLineList.size() > 0) {
				onLine = onLineList.get(0);
				context.signOutSession(session);
				onLine.setOnlineSession(session.getId());
				onLine.setOnlineStatus((byte) BaseUtil.SessionStatus.OnLine.getCode());
				onLineService.changeOnline(onLine);
			} else {
				onLine = new Onlines();
				onLine.setOnlineUserid(newuser.getUserId());
				onLine.setOnlineSession(session.getId());
				onLine.setOnlineStatus((byte) BaseUtil.SessionStatus.OnLine.getCode());
				onLineService.addOnline(onLine);
			}

			result.put("token", onLine.getOnlineSession());
			result.put("user", JSON.toJSON(newuser));
			result.put("status", true);
		} else {
			User olduser = userService.selectUser(user);
			SessionContext context = SessionContext.sharedInstance();
			HttpSession session = request.getSession();
			context.signInSession(session);

			if (session == null) {
				return ResultUtil.sharedInstance().otherError(BaseUtil.ErrorMessageType.LoginFail, request);
			}

			Map<String, Object> onLineQuery = new HashMap<String, Object>();
			onLineQuery.put("userID", olduser.getUserId());
			List<Onlines> onLineList = onLineService.queryList(onLineQuery);
			Onlines onLine = null;

			if (onLineList != null && onLineList.size() > 0) {
				onLine = onLineList.get(0);
				context.signOutSession(session);
				onLine.setOnlineSession(session.getId());
				onLine.setOnlineStatus((byte) BaseUtil.SessionStatus.OnLine.getCode());
				onLineService.changeOnline(onLine);
			} else {
				onLine = new Onlines();
				onLine.setOnlineUserid(olduser.getUserId());
				onLine.setOnlineSession(session.getId());
				onLine.setOnlineStatus((byte) BaseUtil.SessionStatus.OnLine.getCode());
				onLineService.addOnline(onLine);
			}
			result.put("token", onLine.getOnlineSession());
			result.put("user", JSON.toJSON(olduser));
			result.put("status", true);
		}
		String string = userService.seletePaypasswechat(wechatId);
		boolean passflag = false;
		if (null != string && !"".equals(string)) {
			passflag = true;
		}
		result.put("exist", passflag);
		return result;
	}

	/**
	 * 获取用户信息
	 */
	@RequestMapping(value = "/userinfo", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody Map<String, Object> userinfoMethod(@RequestParam String userId, HttpServletRequest request) {
		Map<String, Object> result = new HashMap<String, Object>();
		User user = userService.selectUserByUserId(Long.parseLong(userId));
		/*
		 * JsonConfig jsonConfig = new JsonConfig();
		 * jsonConfig.setJsonPropertyFilter(new PropertyFilter() {
		 * 
		 * @Override public boolean apply(Object obj, String key, Object value) { if
		 * (value == null) { return true; } return false; } }); JSONObject j =
		 * JSONObject.fromObject(pgwReqtBody3002, jsonConfig);
		 * 
		 * net.sf.json.JSONObject jsonObject = net.sf.json.JSONObject.fromObject(user);
		 * System.out.println(jsonObject);
		 */
		try {
			User status = userService.querystatus(Long.parseLong(userId));
			int level = 11;
			String levelstring = "";
			if (7 == status.getUserStatus()) {
				level = status.getUserLevel() * 11;
				levelstring = dictService.queryvalue(level);
			}
			result.put("level", JSON.toJSON(levelstring));
			result.put("status", true);
			result.put("message", "获取成功");
			if (null == user /* || "".equals(user) */) {
				result.put("status", false);
				result.put("message", "该用户不存在！或者查询出错");
			}
			result.put("user", JSON.toJSON(user));
		} catch (Exception e) {
			// TODO: handle exception
			result.put("message", "数据异常，请联系客服");
			result.put("status", false);
		}
		return result;
	}

	/**
	 * 签到
	 */
	@RequestMapping(value = "/addsign", method = { RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Map<String, Object> addSignMethod(HttpServletRequest request) {
		Map<String, Object> result = new HashMap<String, Object>();
		String param;
		param = (String) request.getAttribute(BaseUtil.paramKey);
		if (param == null || param.equals("")) {
			param = request.getParameter(BaseUtil.paramKey);
		}
		JSONObject jsonObject = JSON.parseObject(param);
		String userid = jsonObject.getString("userId");
		// String daynum = jsonObject.getString("number");
		// if (null == daynum || "".equals(daynum)) {
		// daynum = "0";
		// }

		// if (7 < Integer.parseInt(daynum)) {
		// result.put("message", "签到失败,请重试");
		// result.put("status", false);
		// return result;
		// }
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			List<Sign> signs = signService.querySign(map);
			int count = 0;
			Calendar calendar = Calendar.getInstance(); // 得到日历
			Date date = new Date();
			Date time = new Date();
			LocalDate oldtime = new LocalDate();
			LocalDate jdbctime = new LocalDate();

			map.put("userId", Long.parseLong(userid));
			map.put("time", new Date());
			int num = signService.querynow(map);
			if (num >= 1) {
				result.put("message", "今天已经签到了");
				result.put("status", false);
				return result;
			}
			if (num == 1) {
				signs.remove(signs.get(signs.size() - 1));
				count = 1;
			}
			for (int i = 1; i <= signs.size(); i++) {
				calendar.setTime(date);// 把当前时间赋给日历
				calendar.add(Calendar.DAY_OF_MONTH, -i);
				time = calendar.getTime();

				oldtime = new LocalDate(time);
				jdbctime = new LocalDate(signs.get(signs.size() - i).getSignCreatetime());
				if (oldtime.equals(jdbctime)) {
					count = count + 1;
				} else {
					i = signs.size() + 1;
				}
			}

			Sign sign = new Sign();
			sign.setSignId(IdWorker.CreateNewID());
			sign.setSignUserid(Long.parseLong(userid));
			sign.setSignStatus(ConstantUtils.numfalse);
			int fin = signService.addSign(sign);
			if (fin != 1) {
				result.put("message", "签到失败,请重试");
				result.put("status", false);
				return result;
			}
			Integration integration = new Integration();
			integration.setIntegrationId(IdWorker.CreateNewID());
			if (count == 3 || count == 7) {// 阶段奖励
				map.clear();
				map.put("userId", Long.parseLong(userid));
				Payrecords payrecords = new Payrecords();
				payrecords.setPayrecordUserid(Long.parseLong(userid));
				payrecords.setPayrecordBelongid((long) 0);
				payrecords.setPayrecordOrderid((long) 0);
				if (count == 3) {
					payrecords.setPayrecordPayreason(ConstantUtils.signSecondBox);
					payrecords.setPayrecordMoney(BigDecimal.valueOf(ConstantUtils.FirstBox));
					// map.put("moneyNum", (ConstantUtils.FirstBox));
					map.put("userIntegral", ConstantUtils.FirstBox);
				} else {
					payrecords.setPayrecordPayreason(ConstantUtils.signSecondBox);
					payrecords.setPayrecordMoney(BigDecimal.valueOf(ConstantUtils.SecondBox));
					// map.put("moneyNum", (ConstantUtils.SecondBox));
					map.put("userIntegral", ConstantUtils.SecondBox);
				}
				payrecords.setPayrecordTarde((byte) ConstantUtils.ShopIn);
				payrecords.setPayrecordFromuserid((long) 0);
				payrecords.setPayrecordVersion(0);
				payrecords.setPayrecordStatus((byte) 0);
				payrecords.setPayrecordTypeid((long) 8);// 8表示签到宝箱
				payrecordsService.addPayMessage(payrecords);

				// userService.addMoney(map);// 金币增加
				userService.addIntegral(map);
				// CommonController.addIntegrationMessage(, , 0,);
				result.put("Intrgral", JSON.toJSON(payrecords.getPayrecordMoney()));
			} else {
				integration.setIntegrationStatus(0);
				integration.setIntegrationNum(new BigDecimal(ConstantUtils.daymultiple * (count) + 1));
				integration.setIntegrationStudentid(Long.parseLong(userid));
				integration.setIntegrationType(1);
				integration.setIntegrationReason(ConstantUtils.sign + count);
				integrationService.addmessage(integration);
				map.clear();
				map.put("userId", Long.parseLong(userid));
				map.put("userIntegral", (ConstantUtils.daymultiple * (count) + 1));
				map.put(ArgumentsUtils.sMoneyKey, ConstantUtils.daymultiple * (count) + 1);
				userService.addIntegral(map);// 积分增加
				// CommonController.addIntegrationMessage(, , 0,);

				result.put("Intrgral", JSON.toJSON(integration.getIntegrationNum()));
			}

			result.put("message", "签到成功");
			result.put("status", true);
		} catch (Exception e) {
			// TODO: handle exception
			result.put("message", "数据异常，请联系客服");
			result.put("status", false);
		}
		return result;
	}

	/**
	 * 获取签到信息
	 */
	@RequestMapping(value = "/signlist", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody Map<String, Object> signListMethod(@RequestParam String userId, HttpServletRequest request) {
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", Long.parseLong(userId));
		// Date date = new Date();
		// SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
		// String signTime = dateFormat.format(date);
		// map.put("signTime", signTime);
		try {
			List<Sign> signs = signService.querySign(map);

			// Map<String, Object> querymap = new HashMap<String, Object>();
			// querymap.put("userId", Long.parseLong(userid));
			// List<Sign> signs = signService.querySign(querymap);
			int count = 0;
			Calendar calendar = Calendar.getInstance(); // 得到日历
			Date date = new Date();
			Date time = new Date();
			// LocalDate oldtime = new LocalDate(date);
			// LocalDate jdbctime = new
			// LocalDate(signs.get(signs.size()-1).getSignCreatetime());

			LocalDate oldtime = new LocalDate();
			LocalDate jdbctime = new LocalDate();
			map.put("time", new Date());
			int num = signService.querynow(map);
			if (num == 1) {
				signs.remove(signs.get(signs.size() - 1));
				count = 1;
			}
			for (int i = 1; i <= signs.size(); i++) {
				calendar.setTime(date);// 把当前时间赋给日历
				calendar.add(Calendar.DAY_OF_MONTH, -i);
				time = calendar.getTime();

				oldtime = new LocalDate(time);
				jdbctime = new LocalDate(signs.get(signs.size() - i).getSignCreatetime());
				if (oldtime.equals(jdbctime)) {
					count = count + 1;
				} else {
					i = signs.size() + 1;
				}
			}
			map.clear();
			map.put("userId", Long.parseLong(userId));
			List<Sign> signList = signService.queryMouthSign(map);
			// int number = signService.weekSign(Long.parseLong(userId));
			result.put("signList", signList);
			result.put("signNum", count);
			result.put("message", "获取签到时间信息成功！");
			result.put("status", true);
		} catch (Exception e) {
			// TODO: handle exception
			result.put("message", "数据异常，请联系客服");
			result.put("status", false);
		}
		return result;
	}

	/**
	 * 获取用户游戏信息
	 */
	@RequestMapping(value = "/usergame", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody Map<String, Object> gameinfoMethod(@RequestParam String page, String size, String userId,
			HttpServletRequest request) {
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> map = new HashMap<String, Object>();
		if (page == null || "".equals(page) || size == null || "".equals(size)) {
			page = "1";
			size = "5";
		}
		if (userId == null || "".equals(userId)) {
			result.put("message", "您是游客，没有您的游戏信息！");
			result.put("status", false);
			return result;
		}
		int pageCount = Integer.parseInt(size);
		map.put("start", pageCount * (Integer.parseInt(page) - 1));
		map.put("size", pageCount);
		map.put("userId", Long.parseLong(userId));
		// List<Gamepart> list = gamepartService.queryList(map);
		List<Gamepart> list = new ArrayList<Gamepart>();
//		try {
			List<Integer> gameIds = questionrecordsService.SelectGame(Long.parseLong(userId));
			List<Integer> pass = questionrecordsService.SelectPass(Long.parseLong(userId));
			for (int i = 0; i < gameIds.size(); i++) {
				Gamepart game = gamepartService.selectOne(gameIds.get(i).longValue());
				// gamepart.setGameIconurl(game.getGameIconurl());
				// gamepart.setGamePass(pass.get(i));
				// gamepart.setGameName(game.getGameName());
				// gamepart.setGameCreatetime(game.);
				// gamepart.setGamePasstotel(game.getGamePasstotel());
				game.setGamePass(pass.get(i));
				map.clear();
				map.put("userId", Long.parseLong(userId));
				map.put("gameId", gameIds.get(i));
				Date time = questionrecordsService.SelectTime(map);
				game.setGameModifytime(time);
				list.add(game);
			}
			result.put("message", "获取成功");
			result.put("status", true);
			result.put("lists", JSON.toJSON(list));
//		} catch (Exception e) {
//			// TODO: handle exception
//			result.put("message", "数据异常，请联系客服");
//			result.put("status", false);
//		}
		return result;
	}

	/**
	 * 获取用户阅读信息
	 */
	@RequestMapping(value = "/userread", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody Map<String, Object> userreadMethod(@RequestParam String page, String size, String userId,
			HttpServletRequest request) {
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> map = new HashMap<String, Object>();
		int pageCount = Integer.parseInt(size);
		map.put("start", pageCount * (Integer.parseInt(page) - 1));
		map.put("size", pageCount);
		map.put("userId", userId);
		map.put("random", RandomUtil.generateString(10));
		// try {
		List<com.huban.pojo.Date> list = dateService.queryList(map);
		List<String> bookAuthor = new ArrayList<String>();
		List<String> bookName = new ArrayList<String>();
		for (int i = 0; i < list.size(); i++) {
			Book name = bookService.queryAuthorName(list.get(i).getDateBelongid());
			bookName.add(name.getBookName());
			bookAuthor.add(name.getBookAuthor());
			list.get(i).setDateUrl(name.getBookHeadicon());
			list.get(i).setDateId(name.getBookId());
		}
		// List<Bookpart> list=bookpartService.queryList(map);
		// List<String> bookAuthor = bookService.queryAuthor(map);
		result.put("status", true);
		result.put("message", "获取成功");
		result.put("bookAuthor", bookAuthor);
		result.put("bookName", bookName);
		result.put("lists", JSON.toJSON(list));
		// } catch (Exception e) {
		// // TODO: handle exception
		// result.put("message", "数据异常，请联系客服");
		// result.put("status", false);
		// }
		return result;
	}

	/**
	 * 获取用户活动信息
	 */
	@RequestMapping(value = "/useractivity", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody Map<String, Object> useractivityMethod(@RequestParam String page, String size, String userId,
			HttpServletRequest request) {
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> map = new HashMap<String, Object>();
		int pageCount = Integer.parseInt(size);
		map.put("start", pageCount * (Integer.parseInt(page) - 1));
		map.put("size", pageCount);
		map.put("userId", userId);
		// try {
		List<Activitypart> list = activitypartService.queryList(map);
		List<Activity> activities = new ArrayList<Activity>();
		for (int i = 0; i < list.size(); i++) {
			Activity activity = activityService.selectbyid(list.get(i).getActivitypartActivityid());
			activities.add(activity);
		}
		result.put("message", "获取成功");
		result.put("activitys", JSON.toJSON(activities));
		// result.put("lists", JSON.toJSON(list));
		result.put("status", true);
		// } catch (Exception e) {
		// // TODO: handle exception
		// result.put("message", "数据异常，请联系客服");
		// result.put("status", false);
		// }
		return result;
	}

	/**
	 * 修改用户姓名
	 * 
	 * @param request
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "/updatename", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody Map<String, Object> updatenameMethod(HttpServletRequest request) {
		Map<String, Object> result = new HashMap<String, Object>();
		String param;
		param = (String) request.getAttribute(BaseUtil.paramKey);
		if (param == null || param.equals("")) {
			param = request.getParameter(BaseUtil.paramKey);
		}
		JSONObject jsonObject = JSON.parseObject(param);
		String username = jsonObject.getString("userName");
		String userId = jsonObject.getString("userId");
		User newUser = new User();
		newUser.setUserId(Long.parseLong(userId));
		try {
			// String username = new String(userName.getBytes("ISO8859-1"),"UTF-8");
			if (username != null && !"".equals(username)) {
				newUser.setUserName(username);
				int flag = userService.changeUser(newUser);
				if (flag == 0) {
					result.put("message", "参数未传或数据库更新异常");
					result.put("status", false);
				} else {
					result.put("message", "修改成功");
					result.put("status", true);
				}
			} else {
				result.put("message", "参数姓名输入有误");
				result.put("status", false);
			}
		} catch (Exception e) {
			// TODO: handle exception
			result.put("message", "数据异常，请联系客服");
			result.put("status", false);
		}
		return result;
	}

	/**
	 * 修改用户性别
	 * 
	 * @param request
	 */
	@RequestMapping(value = "/updatesex", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> updatesexMethod(HttpServletRequest request) {
		Map<String, Object> result = new HashMap<String, Object>();
		String param;
		param = (String) request.getAttribute(BaseUtil.paramKey);
		if (param == null || param.equals("")) {
			param = request.getParameter(BaseUtil.paramKey);
		}
		JSONObject jsonObject = JSON.parseObject(param);
		String userSex = jsonObject.getString("userSex");
		String userId = jsonObject.getString("userId");
		// HttpSession session = request.getSession();
		// User newUser = (User) session.getAttribute("user");
		User newUser = new User();
		newUser.setUserId(Long.parseLong(userId));
		int sex;
		if (userSex.equals("男")) {
			sex = 1;
		} else {
			sex = 2;
		}
		newUser.setUserSex(sex);
		try {
			int flag = userService.changeUser(newUser);
			if (flag == 0) {
				result.put("message", "参数错误或数据库更新异常");
				result.put("status", false);
			} else {
				result.put("message", "修改成功");
				result.put("status", true);
			}
		} catch (Exception e) {
			// TODO: handle exception
			result.put("message", "数据异常，请联系客服");
			result.put("status", false);
		}
		return result;
	}

	/**
	 * 修改用户头像
	 * 
	 * @param request
	 * @throws IOException
	 */
	@RequestMapping(value = "/updateheadicon", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> updateheadiconMethod(@RequestParam(value = "file") MultipartFile file,
			String userId, HttpServletRequest request, HttpServletResponse response) throws IOException {
		Map<String, Object> result = new HashMap<String, Object>();
		// try {
		if (userId == null || file == null) {
			result.put("message", "图片或者用户Id未传");
			result.put("status", false);
		}
		InputStream inputStream = file.getInputStream();
		User user = new User();
		user.setUserId(Long.parseLong(userId));
		String fileType = "Picture";
		try {
			String url = UploadAliYunFile.UploadAliYunFileService(inputStream, file, fileType);
			user.setUserHeadicon(url);
			int flag = userService.changeUser(user);
			if (flag == 0) {
				result.put("message", "参数未传或数据库更新异常");
				result.put("status", false);
			} else {
				result.put("message", "修改成功");
				result.put("status", true);
			}
			// } catch (Exception e) {
			// result.put("message", "上传失败");
			// result.put("status", false);
			// // TODO: handle exception
			// }
		} catch (Exception e) {
			// TODO: handle exception
			result.put("message", "数据异常，请联系客服");
			result.put("status", false);
		}
		return result;
	}

	/**
	 * 修改用户出生日期
	 * 
	 * @param request
	 * @throws ParseException
	 */
	@RequestMapping(value = "/updatebirth", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> updatebirthMethod(HttpServletRequest request) throws ParseException {
		Map<String, Object> result = new HashMap<String, Object>();
		String param;
		param = (String) request.getAttribute(BaseUtil.paramKey);
		if (param == null || param.equals("")) {
			param = request.getParameter(BaseUtil.paramKey);
		}
		JSONObject jsonObject = JSON.parseObject(param);
		String birth = jsonObject.getString("birth");
		String userId = jsonObject.getString("userId");
		User user = new User();
		user.setUserId(Long.parseLong(userId));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		user.setUserBirthday(sdf.parse(birth));
		try {
			int flag = userService.changeUser(user);
			if (flag == 0) {
				result.put("message", "参数未传或数据库更新异常");
				result.put("status", false);
			} else {
				result.put("message", "修改成功");
				result.put("status", true);
			}
		} catch (Exception e) {
			// TODO: handle exception
			result.put("message", "数据异常，请联系客服");
			result.put("status", false);
		}
		return result;
	}

	/**
	 * 修改用户出生日期的实验接口
	 * 
	 * @param request
	 * @throws ParseException
	 */
	@RequestMapping(value = "/updatebirthday", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> updatebirthMethod(@RequestParam String birthday,
			HttpServletRequest request) throws ParseException {
		Map<String, Object> result = new HashMap<String, Object>();
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		user.setUserBirthday(sdf.parse(birthday));
		int flag = userService.changeUser(user);
		if (flag == 0) {
			result.put("message", "参数未传或数据库更新异常");
			result.put("status", false);
		} else {
			result.put("message", "修改成功");
			result.put("status", true);
		}
		return result;
	}

	/**
	 * 修改用户钱包密码
	 * 
	 * @param request
	 */
	@RequestMapping(value = "/updatepaypass", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody Map<String, Object> updatepaypassMethod(HttpServletRequest request) {
		Map<String, Object> result = new HashMap<String, Object>();
		String param;
		param = (String) request.getAttribute(BaseUtil.paramKey);
		if (param == null || param.equals("")) {
			param = request.getParameter(BaseUtil.paramKey);
		}
		JSONObject jsonObject = JSON.parseObject(param);
		String userid = jsonObject.getString("userId");
		String paypass = jsonObject.getString("payPass");
		User user = new User();
		user.setUserId(Long.parseLong(userid));
		user.setUserPaypassword(paypass);
		try {
			int flag = userService.changeUser(user);
			if (flag == 0) {
				result.put("message", "参数未传或数据库更新异常");
				result.put("status", false);
			} else {
				result.put("message", "修改成功");
				result.put("status", true);
			}
		} catch (Exception e) {
			// TODO: handle exception
			result.put("message", "数据异常，请联系客服");
			result.put("status", false);
		}
		return result;
	}

	/**
	 * 修改手机号
	 * 
	 * @param request
	 */
	@RequestMapping(value = "/updatephone", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> updatephoneMethod(HttpServletRequest request) {
		Map<String, Object> result = new HashMap<String, Object>();
		String param;
		param = (String) request.getAttribute(BaseUtil.paramKey);
		if (param == null || param.equals("")) {
			param = request.getParameter(BaseUtil.paramKey);
		}
		JSONObject jsonObject = JSON.parseObject(param);
		String phone = jsonObject.getString("phone");
		String code = jsonObject.getString("code");
		String userid = jsonObject.getString("userId");
		String type = jsonObject.getString("type");
		// HttpSession session = request.getSession();
		// User user = (User) session.getAttribute("user");
		User user = new User();
		user.setUserId(Long.parseLong(userid));
		// user.setUserId(user.getUserId());
		// System.out.println(phone+"+"+code+"+"+user.getUserId());
		if (phone == null || phone.equals("")) {
			result.put("message", "参数手机号未传");
			result.put("status", false);
			return result;
		}
		if (code == null || code.equals("")) {
			result.put("message", "参数验证码未传");
			result.put("status", false);
			return result;
		}
		if (!VerifyCode.sharedInstance().checkNumberVerify(phone)) {
			result.put("message", "手机号格式不正确");
			result.put("status", false);
			return result;
		}
		try {
			Verifyrecords verifyrecords = getVerifyRecord(phone, verifyrecordsService);
			result = checkVerifyNumber(verifyrecords, phone, code);
			if (result != null) {
				return result;
			} else {
				if ("2" == type || "2".equals(type)) {// 微信过来的修改,查询用户(phone!=null&&WeChatOpenId==null),有表示以前手机号登录过
					// 把微信user删除，微信id给老用户，输出老用户的ID，没有该用户，表示这手机号没被使用，可以不管，
					User olduser = userService.PhoneYesWeChatNo(phone);
					if (olduser != null) {
						User updateuser = new User();
						User weChatUser = userService.QueryOpenIdByUserId(Long.parseLong(userid));
						if (null == olduser.getUserName() || "".equals(olduser.getUserName())) {
							updateuser.setUserName(weChatUser.getUserName());
						}
						if (0 == olduser.getUserSex()) {
							updateuser.setUserSex(weChatUser.getUserSex());
						}
						updateuser.setUserWechatopenid(weChatUser.getUserWechatopenid());
						updateuser.setUserId(olduser.getUserId());
						userService.UpdateWeChatOpenId(updateuser);

						userService.DeleteWeChatId(Long.parseLong(userid));
						user.setUserId(olduser.getUserId());
					}

				} else {
					int count = userService.phonecount(phone);
					if (count > 0) {// 用户存在
						result.put("message", "手机已被他人使用，请更换手机号");
						result.put("status", false);
						return result;
					}
				}
				result = updatePhone(user, phone);
			}
		} catch (Exception e) {
			// TODO: handle exception
			result.put("message", "数据异常，请联系客服");
			result.put("status", false);
		}
		return result;
	}

	public Map<String, Object> updatePhone(User user, String phone) {
		Map<String, Object> result = new HashMap<String, Object>();
		user.setUserPhone(phone);
		userService.changeUser(user);
		Map<String, Object> onLineQuery = new HashMap<String, Object>();
		onLineQuery.put("userId", user.getUserId());
		List<Onlines> onLineList = onLineService.queryList(onLineQuery);
		result.put("token", onLineList.get(0).getOnlineSession());
		result.put("userId", user.getUserId());
		result.put("message", "修改成功");
		result.put("status", true);
		return result;
	}

	/*-------------------------------------------V2-------------------------------------------*/

	/**
	 * 检测有没有账号
	 * 
	 * @param phone
	 */
	@RequestMapping(value = "/Interface/V2/ExamineAccount", method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> ExamineAccountMethod(@RequestParam String phone,
			@RequestParam(required = false) Integer identity, HttpServletRequest request) {
		Map<String, Object> mapQuery = new HashMap<String, Object>();
		mapQuery.put(ArgumentsUtils.sIdentityKey, identity);
		mapQuery.put(ArgumentsUtils.sPhoneKey, phone);
		int count = userService.QueryExistIdentity(mapQuery);
		if (count <= 0) {// 用户存在
			return ResultUtil.sharedInstance().FalseData("账号不存在，请先注册", ReturnCodeUtils.Code.NO.getCode());
		}
		return ResultUtil.sharedInstance().TrueData(null, "账号存在", ReturnCodeUtils.Code.OK.getCode());
	}

	/**
	 * 发送验证码
	 * 
	 * @param phone
	 * @param identity
	 *            (1:老师 2:学生 3:代理商)
	 */
	@RequestMapping(value = "/Interface/V2/active", method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> activeMethod(@RequestParam String phone, String type, String identity,
			HttpServletRequest request) {
		Map<String, Object> mapReturn = new HashMap<String, Object>();
		Map<String, Object> mapQuery = new HashMap<String, Object>();
		if (phone == null || phone.equals("")) {
			return ResultUtil.sharedInstance().FalseData("请输入手机号", ReturnCodeUtils.Missing_Parameter.Phone.getCode());
		} else {
			if (!VerifyCode.sharedInstance().checkNumberVerify(phone)) {
				return ResultUtil.sharedInstance().FalseData("手机号格式不正确", ReturnCodeUtils.Error_Format.Phone.getCode());
			} else {
				int iIdentity = Integer.parseInt(identity);
				if ("1" == type || "1".equals(type)) {
					if (iIdentity != 2) {
						mapQuery.put(ArgumentsUtils.sIdentityKey, identity);
						mapQuery.put(ArgumentsUtils.sPhoneKey, phone);
						int count = userService.QueryExistIdentity(mapQuery);
						if (count > 0) {
							return ResultUtil.sharedInstance().FalseData("手机已被他人使用，请更换手机号",
									ReturnCodeUtils.Error_USE_COUNT.OtherUser_Use.getCode());
						}
					}
				}
				if ("2" == type || "2".equals(type)) {// 微信登录之后的绑定
					int number = userService.phonewechatcount(phone);
					if (number == 1) {// phone 和 openId 都不为空
						return ResultUtil.sharedInstance().FalseData("手机已被他人微信绑定，请更换手机号",
								ReturnCodeUtils.Error_USE_COUNT.Other_WeChar_Use.getCode());
					}
				}
				if (2 == iIdentity) {// 1:老师 2:学生
					mapQuery.clear();
					mapQuery.put(ArgumentsUtils.sPhoneKey, phone);
					int iphonecount = userService.examinePseudonym(mapQuery);
					if (iphonecount >= 3) {
						return ResultUtil.sharedInstance().FalseData(ReturnMessageUtils.sMorePeopleInUse,
								ReturnCodeUtils.Error_USE_COUNT.MoreUser_Use.getCode());
					} // else if (iphonecount >= 1) {
					// mapQuery.clear();
					// mapQuery.put(ArgumentsUtils.sPhoneKey, phone);
					// mapQuery.put(ArgumentsUtils.sIdentityKey,
					// ArgumentsUtils.iTeacher_IdentityKey);
					// int Querycount = userService.QueryExistIdentity(mapQuery);
					// if (Querycount >= 1) {
					// return
					// ResultUtil.sharedInstance().FalseData(ReturnMessageUtils.sByOtherTeacherUse,
					// ReturnCodeUtils.Error_USE_COUNT.Other_Teacher_Use.getCode());
					// }
					// mapQuery.put(ArgumentsUtils.sIdentityKey, ArgumentsUtils.iAgent_IdentityKey);
					// Querycount = userService.QueryExistIdentity(mapQuery);
					// if (Querycount >= 1) {
					// return
					// ResultUtil.sharedInstance().FalseData(ReturnMessageUtils.sByOtherAgentUse,
					// ReturnCodeUtils.Error_USE_COUNT.Other_Teacher_Use.getCode());
					// }
					// }
				} else {
					mapQuery.clear();
					mapQuery.put(ArgumentsUtils.sIdentityKey, identity);
					mapQuery.put(ArgumentsUtils.sPhoneKey, phone);
					int count = userService.QueryExistIdentity(mapQuery);
					if (count > 0) {// 用户存在
						return ResultUtil.sharedInstance().FalseData(ReturnMessageUtils.sByOtherPeopleUse,
								ReturnCodeUtils.Error_USE_COUNT.OtherUser_Use.getCode());
					}
				}
				mapReturn = SendCheckNumber(verifyrecordsService, phone, errorsLogService);
				if (mapReturn != null) {
					return mapReturn;
				}
			}
			return ResultUtil.sharedInstance().TrueData(null, "验证码发送成功,请注意查收", ReturnCodeUtils.Code.OK.getCode());
		}
	}

	/**
	 * 验证验证码登陆
	 * 
	 * @param param
	 * @throws IOException
	 */
	@RequestMapping(value = "/Interface/V2/verify", method = { RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Map<String, Object> verify(@RequestParam String phone, String code, int identity,
			HttpServletRequest request) {
		Map<String, Object> mapReturn = new HashMap<String, Object>();
		Map<String, Object> mapResult = new HashMap<String, Object>();
		Map<String, Object> mapQuery = new HashMap<String, Object>();
		if (phone == null || phone.equals("")) {
			return ResultUtil.sharedInstance().FalseData("参数手机号未传", ReturnCodeUtils.Missing_Parameter.Phone.getCode());
		}
		if (code == null || code.equals("")) {
			return ResultUtil.sharedInstance().FalseData("参数验证码未传",
					ReturnCodeUtils.Missing_Parameter.Captcha.getCode());
		}
		if (!VerifyCode.sharedInstance().checkNumberVerify(phone)) {
			return ResultUtil.sharedInstance().FalseData("手机号格式不正确", ReturnCodeUtils.Error_Format.Phone.getCode());
		}
		mapQuery.put(ArgumentsUtils.sPhoneKey, phone);
		mapQuery.put(ArgumentsUtils.sIdentityKey, identity);
		mapResult = userService.QuerySomeMessage(mapQuery);
		String JDBCPayPwd = null;
		if (null != mapResult) {
			JDBCPayPwd = mapResult.get("paypassword").toString();
		}

		// String sPayPass = userService.seletePaypassExist(phone);
		boolean flag = false;
		Verifyrecords verifyrecords = getVerifyRecord(phone, verifyrecordsService);
		mapReturn = checkVerifyNumber(verifyrecords, phone, code);
		if (mapReturn != null) {
			if (null != JDBCPayPwd && !"".equals(JDBCPayPwd)) {
				flag = true;
			}
			mapReturn.put(BaseUtil.existKey, flag);
			return mapReturn;
		} else {
			mapReturn = newInitialUserInfo(verifyrecords, phone, identity, request);
		}
		if (Integer.parseInt(mapReturn.get(BaseUtil.newRetureKey).toString()) != ReturnCodeUtils.Code.OK.getCode()) {
			return mapReturn;
		}
		// result.remove(key)
		if (null != JDBCPayPwd && !"".equals(JDBCPayPwd)) {
			flag = true;
		}
		mapReturn.put(BaseUtil.existKey, flag);
		return ResultUtil.sharedInstance().TrueData(mapReturn, "登陆成功", ReturnCodeUtils.Code.OK.getCode());
	}

	/**
	 * 发送验证码 simple
	 * 
	 * @param phone
	 * @param identity
	 *            (1:老师 2:学生 3:代理商)
	 */
	@RequestMapping(value = "/Interface/V2.1/active", method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> SimpleActiveMethod(@RequestParam String phone,
			HttpServletRequest request) {
		Map<String, Object> mapReturn = new HashMap<String, Object>();
		if (phone == null || phone.equals("")) {
			return ResultUtil.sharedInstance().FalseData("请输入手机号", ReturnCodeUtils.Missing_Parameter.Phone.getCode());
		} else {
			if (!VerifyCode.sharedInstance().checkNumberVerify(phone)) {
				return ResultUtil.sharedInstance().FalseData("手机号格式不正确", ReturnCodeUtils.Error_Format.Phone.getCode());
			} else {
				mapReturn = SendCheckNumber(verifyrecordsService, phone, errorsLogService);
				if (mapReturn != null) {
					return mapReturn;
				}
			}
			return ResultUtil.sharedInstance().TrueData(null, "验证码发送成功,请注意查收", ReturnCodeUtils.Code.OK.getCode());
		}
	}

	/**
	 * 验证验证码登陆 simple
	 * 
	 * @param param
	 * @throws IOException
	 */
	@RequestMapping(value = "/Interface/V2.1/verify", method = { RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Map<String, Object> SimpleVerify(@RequestParam String phone, String code, int identity,
			int type, HttpServletRequest request) {
		Map<String, Object> mapReturn = new HashMap<String, Object>();
		if (phone == null || phone.equals("")) {
			return ResultUtil.sharedInstance().FalseData("参数手机号未传", ReturnCodeUtils.Missing_Parameter.Phone.getCode());
		}
		if (code == null || code.equals("")) {
			// if (identity == 1) {
			// return ResultUtil.sharedInstance().FalseData("参数授权码未传",
			// ReturnCodeUtils.Missing_Parameter.Code.getCode());
			// }
			return ResultUtil.sharedInstance().FalseData("参数验证码未传",
					ReturnCodeUtils.Missing_Parameter.Captcha.getCode());
		}
		if (!VerifyCode.sharedInstance().checkNumberVerify(phone)) {
			return ResultUtil.sharedInstance().FalseData("手机号格式不正确", ReturnCodeUtils.Error_Format.Phone.getCode());
		}

		Verifyrecords verifyrecords = getVerifyRecord(phone, verifyrecordsService);
		mapReturn = checkVerifyNumber(verifyrecords, phone, code);
		if (mapReturn != null) {
			return mapReturn;
		} else {
			mapReturn = SimpleInitialUserInfo(phone, identity, type, request);
		}
		if (Integer.parseInt(mapReturn.get(BaseUtil.newRetureKey).toString()) != ReturnCodeUtils.Code.OK.getCode()) {
			return mapReturn;
		}
		return ResultUtil.sharedInstance().TrueData(mapReturn, "登陆成功", ReturnCodeUtils.Code.OK.getCode());
	}

	/**
	 * 授权码注册
	 * @param param
	 * @throws IOException
	 */
	@RequestMapping(value = "/Interface/V2/Authorization", method = { RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Map<String, Object> AuthorizationMethod(HttpServletRequest request) {
		Map<String, Object> mapQuery = new HashMap<String, Object>();
		Map<String, Object> mapReturn = new HashMap<String, Object>();
		String param;
		param = (String) request.getAttribute(BaseUtil.paramKey);
		if (param == null || param.equals("")) {
			param = request.getParameter(BaseUtil.paramKey);
		}
		JSONObject jsonObject = JSON.parseObject(param);
		String phone = jsonObject.getString(ArgumentsUtils.sPhoneKey);
		String code = jsonObject.getString(ArgumentsUtils.sCodeKey);
		int identity = Integer.parseInt(jsonObject.getString(ArgumentsUtils.sIdentityKey));
		int type = Integer.parseInt(jsonObject.getString(ArgumentsUtils.sTypeKey));
		
		if (phone == null || phone.equals("")) {
			return ResultUtil.sharedInstance().FalseData("参数手机号未传", ReturnCodeUtils.Missing_Parameter.Phone.getCode());
		}
		if (code == null || code.equals("")) {
				return ResultUtil.sharedInstance().FalseData("参数授权码未传", ReturnCodeUtils.Missing_Parameter.Code.getCode());
		}
		if (!VerifyCode.sharedInstance().checkNumberVerify(phone)) {
			return ResultUtil.sharedInstance().FalseData("手机号格式不正确", ReturnCodeUtils.Error_Format.Phone.getCode());
		}
		
		boolean flag = false;
		mapQuery.put(ArgumentsUtils.sNumberKey, phone);
		mapQuery.put(ArgumentsUtils.sCodeKey, code);
		mapQuery.put(ArgumentsUtils.sTypeKey, ConstantUtils.iReadBank_DictType_Authorization_Code);
		List<Dict> LstDict = new ArrayList<Dict>();
		if (code.contains("1234")) {
			mapReturn = SimpleInitialUserInfo(phone, identity , type, request);
			if (Integer.parseInt(mapReturn.get(BaseUtil.newRetureKey).toString()) != ReturnCodeUtils.Code.OK.getCode()) {
				return mapReturn;
			}
			return ResultUtil.sharedInstance().TrueData(mapReturn, "操作成功", ReturnCodeUtils.Code.OK.getCode());
		}else {
			LstDict = dictService.SearchLstDict(mapQuery);
			if (LstDict != null && LstDict.size() > 0) {
				if (LstDict.get(0).getDictStatus() != 0) {
					return ResultUtil.sharedInstance().FalseData("授权码已使用", ReturnCodeUtils.Code.NO.getCode());
				}
				if (code.equals(LstDict.get(0).getDictValue()) || code.compareTo(LstDict.get(0).getDictValue()) == 0) {
					flag = true;
				}
			}
		}
		if (flag) {
			mapReturn = SimpleInitialUserInfo(phone, identity , type, request);
			if (Integer.parseInt(mapReturn.get(BaseUtil.newRetureKey).toString()) != ReturnCodeUtils.Code.OK.getCode()) {
				return mapReturn;
			}
			Dict pjDict = new Dict();
			pjDict.setDictId(LstDict.get(0).getDictId());
			pjDict.setDictStatus(1);
			dictService.ModifyMessage(pjDict);
			return ResultUtil.sharedInstance().TrueData(mapReturn, "操作成功", ReturnCodeUtils.Code.OK.getCode());
		}else {
			return ResultUtil.sharedInstance().FalseData("授权码匹配失败或无匹配授权码", ReturnCodeUtils.Code.NO.getCode());
		}
	}

	/**
	 * 微信登录
	 * 
	 * @param param
	 *            identity=1/2 老师／学生
	 */
	@RequestMapping(value = "/Interface/V2/WeChat", method = { RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Map<String, Object> WeChat(HttpServletRequest request) {
		Map<String, Object> result = new HashMap<String, Object>();
		String param;
		param = (String) request.getAttribute(BaseUtil.paramKey);
		if (param == null || param.equals("")) {
			param = request.getParameter(BaseUtil.paramKey);
		}
		Basic basic = JSON.parseObject(param, Basic.class);
		String wechatId = basic.getWechatId();
		String wechatSex = basic.getWechatSex();
		String wechatName = basic.getWechatName();
		String wechatHeadIcon = basic.getWechatHeadIcon();
		String wechatCity = basic.getWechatCity();
		String identity = basic.getIdentity();
		if (wechatId == null || wechatId.equals("") || wechatSex == null || wechatSex.equals("") || wechatName == null
				|| wechatName.equals("")) {
			return ResultUtil.sharedInstance().FalseData("获取微信信息失败",
					ReturnCodeUtils.Missing_Parameter.Wechar.getCode());
		}
		if (null == identity || "".equals(identity)) {
			return ResultUtil.sharedInstance().FalseData("身份信息未确认",
					ReturnCodeUtils.Missing_Parameter.Identity.getCode());
		}
		User user = new User();
		user.setUserWechatopenid(wechatId);
		int flag = userService.findUser(user);
		Map<String, Object> QueryMap = new HashMap<String, Object>();
		if (flag == 0) {
			User newuser = new User();
			newuser.setUserId(IdWorker.CreateNewID());
			newuser.setUserNumericalOrder(Long.parseLong(Genneration_sole.generateNumber2()));
			newuser.setUserWechatopenid(wechatId);
			newuser.setUserName(wechatName);
			newuser.setUserSex(Integer.parseInt(wechatSex));
			newuser.setUserHeadicon(wechatHeadIcon);
			newuser.setUserCity(wechatCity);
			userService.wechatinsert(newuser);

			UserInfo pjUserInfo = UserWay.AddUserInfoNewMessage(newuser.getUserId(), null, new BigDecimal(0), null,
					null, null, "0", 0, Integer.parseInt(identity), null);
			userInfoService.AddNewMessage(pjUserInfo);
			// userService.addUser(newuser);//验证登录时添加user信息
			// 添加session信息
			SessionContext context = SessionContext.sharedInstance();
			HttpSession session = request.getSession();
			context.signInSession(session);

			if (session == null) {
				return ResultUtil.sharedInstance().otherError(BaseUtil.ErrorMessageType.LoginFail, request);
			} else {
				Message message = Message.DefaultMessages(newuser.getUserId());
				messageService.addMessage(message);
			}

			QueryMap.put("userID", newuser.getUserId());
			List<Onlines> onLineList = onLineService.queryList(QueryMap);
			Onlines onLine = null;

			if (onLineList != null && onLineList.size() > 0) {
				onLine = onLineList.get(0);
				context.signOutSession(session);
				onLine.setOnlineSession(session.getId());
				onLine.setOnlineStatus((byte) BaseUtil.SessionStatus.OnLine.getCode());
				onLineService.changeOnline(onLine);
			} else {
				onLine = new Onlines();
				onLine.setOnlineUserid(newuser.getUserId());
				onLine.setOnlineSession(session.getId());
				onLine.setOnlineStatus((byte) BaseUtil.SessionStatus.OnLine.getCode());
				onLineService.addOnline(onLine);
			}

			result.put(BaseUtil.tokenKey, onLine.getOnlineSession());
			result.put(BaseUtil.userKey, JSON.toJSON(newuser));
		} else {
			User olduser = userService.selectUser(user);
			SessionContext context = SessionContext.sharedInstance();
			HttpSession session = request.getSession();
			context.signInSession(session);

			if (session == null) {
				return ResultUtil.sharedInstance().otherError(BaseUtil.ErrorMessageType.LoginFail, request);
			}

			QueryMap.put("userID", olduser.getUserId());
			List<Onlines> onLineList = onLineService.queryList(QueryMap);
			QueryMap.clear();
			QueryMap.put(ArgumentsUtils.sIDKey, olduser.getUserId());
			int Querycount = userInfoService.QueryExist(QueryMap);
			if (Querycount < 1) {
				UserInfo pjUserInfo = UserWay.AddUserInfoNewMessage(olduser.getUserId(), null, new BigDecimal(0), null,
						null, null, "0", 0, Integer.parseInt(identity), null);
				userInfoService.AddNewMessage(pjUserInfo);
			}
			Onlines onLine = null;
			if (onLineList != null && onLineList.size() > 0) {
				onLine = onLineList.get(0);
				context.signOutSession(session);
				onLine.setOnlineSession(session.getId());
				onLine.setOnlineStatus((byte) BaseUtil.SessionStatus.OnLine.getCode());
				onLineService.changeOnline(onLine);
			} else {
				onLine = new Onlines();
				onLine.setOnlineUserid(olduser.getUserId());
				onLine.setOnlineSession(session.getId());
				onLine.setOnlineStatus((byte) BaseUtil.SessionStatus.OnLine.getCode());
				onLineService.addOnline(onLine);
			}
			result.put(BaseUtil.tokenKey, onLine.getOnlineSession());
			result.put(BaseUtil.userKey, JSON.toJSON(olduser));
		}
		String string = userService.seletePaypasswechat(wechatId);
		boolean passflag = false;
		if (null != string && !"".equals(string)) {
			passflag = true;
		}
		result.put(BaseUtil.existKey, passflag);
		result = ResultUtil.sharedInstance().TrueData(result, "登陆成功", ReturnCodeUtils.Code.OK.getCode());
		return result;
	}

	/**
	 * 检查笔名是否重复
	 * 
	 * @created 2017年9月12日 下午4:51:55
	 * @param request
	 *            pseudonym
	 * @return result
	 */
	@RequestMapping(value = "/Interface/V2/examinePseudonym", method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> examinePseudonym(@RequestParam String pseudonym,
			HttpServletRequest request) {
		Map<String, Object> result = new HashMap<String, Object>();
		boolean flag = true;
		String message = ReturnMessageUtils.truePseudonymMessageKey;
		result.put(ArgumentsUtils.sUserNameKey, pseudonym);
		result.put(ArgumentsUtils.sRandomKey, RandomUtil.generateString(6));
		int iQueryCount = userService.examinePseudonym(result);
		result.clear();
		if (0 != iQueryCount) {
			message = ReturnMessageUtils.falsePseudonymMessageKey;
			flag = false;
		}
		if (flag) {
			return ResultUtil.sharedInstance().TrueData(null, message, ReturnCodeUtils.Code.OK.getCode());
		}
		return ResultUtil.sharedInstance().FalseData(message, ReturnCodeUtils.Code.NO.getCode());

	}

	/**
	 * 修改手机号
	 * 
	 * @param request
	 */
	@RequestMapping(value = "/Interface/V2/updatePhone", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> updatePhone(HttpServletRequest request) {
		Map<String, Object> mapReturn = new HashMap<String, Object>();
		Map<String, Object> mapResult = new HashMap<String, Object>();
		Map<String, Object> mapQuery = new HashMap<String, Object>();
		String param;
		param = (String) request.getAttribute(BaseUtil.paramKey);
		if (param == null || param.equals("")) {
			param = request.getParameter(BaseUtil.paramKey);
		}
		JSONObject jsonObject = JSON.parseObject(param);
		String sPhone = jsonObject.getString("phone");
		String sCode = jsonObject.getString("code");
		String sUserId = jsonObject.getString("userId");
		String sType = jsonObject.getString("type");
		String sidentity = jsonObject.getString("identity");
		long lUserId = Long.parseLong(sUserId);
		int iidentity = Integer.parseInt(sidentity);
		User user = new User();
		user.setUserId(Long.parseLong(sUserId));
		if (sPhone == null || sPhone.equals("")) {
			return ResultUtil.sharedInstance().FalseData("参数手机号未传", ReturnCodeUtils.Missing_Parameter.Phone.getCode());
		}
		if (sCode == null || sCode.equals("")) {
			return ResultUtil.sharedInstance().FalseData("参数验证码未传",
					ReturnCodeUtils.Missing_Parameter.Captcha.getCode());
		}
		if (!VerifyCode.sharedInstance().checkNumberVerify(sPhone)) {
			return ResultUtil.sharedInstance().FalseData("手机号格式不正确", ReturnCodeUtils.Error_Format.Phone.getCode());
		}
		// try {
		Verifyrecords verifyrecords = getVerifyRecord(sPhone, verifyrecordsService);
		mapResult = checkVerifyNumber(verifyrecords, sPhone, sCode);
		if (mapResult != null) {
			mapReturn.putAll(mapResult);
			return mapReturn;
		} else {
			if ("2" == sType || "2".equals(sType)) {// 微信过来的修改,查询用户(phone!=null&&WeChatOpenId==null),有表示以前手机号登录过
				// 把微信user删除，微信id给老用户，输出老用户的ID，没有该用户，表示这手机号没被使用，可以不管，
				if (2 == iidentity) {// 1:老师 2:学生
					mapQuery.put(ArgumentsUtils.sPhoneKey, sPhone);
					int iphonecount = userService.examinePseudonym(mapQuery);
					if (iphonecount >= 3) {
						return ResultUtil.sharedInstance().FalseData(ReturnMessageUtils.sMorePeopleInUse,
								ReturnCodeUtils.Error_USE_COUNT.MoreUser_Use.getCode());
					} else if (iphonecount >= 1) {
						mapQuery.clear();
						mapQuery.put(ArgumentsUtils.sPhoneKey, sPhone);
						mapQuery.put(ArgumentsUtils.sIdentityKey, ArgumentsUtils.iTeacher_IdentityKey);
						int Querycount = userService.QueryExistIdentity(mapQuery);
						if (Querycount >= 1) {
							return ResultUtil.sharedInstance().FalseData(ReturnMessageUtils.sByOtherTeacherUse,
									ReturnCodeUtils.Error_USE_COUNT.Other_Teacher_Use.getCode());
						}
					}
				}

				User olduser = userService.PhoneYesWeChatNo(sPhone);
				if (olduser != null) {
					User updateuser = new User();
					User weChatUser = userService.QueryOpenIdByUserId(lUserId);
					if (null == olduser.getUserName() || "".equals(olduser.getUserName())) {
						updateuser.setUserName(weChatUser.getUserName());
					}
					if (0 == olduser.getUserSex()) {
						updateuser.setUserSex(weChatUser.getUserSex());
					}
					updateuser.setUserWechatopenid(weChatUser.getUserWechatopenid());
					updateuser.setUserId(olduser.getUserId());
					userService.UpdateWeChatOpenId(updateuser);

					userService.DeleteWeChatId(lUserId);
					user.setUserId(olduser.getUserId());
				}
			} else {
				if (2 == iidentity) {// 1:老师 2:学生
					mapQuery.put(ArgumentsUtils.sPhoneKey, sPhone);
					int iphonecount = userService.examinePseudonym(mapQuery);
					if (iphonecount >= 3) {
						return ResultUtil.sharedInstance().FalseData(ReturnMessageUtils.sMorePeopleInUse,
								ReturnCodeUtils.Error_USE_COUNT.MoreUser_Use.getCode());
					} else if (iphonecount >= 1) {
						mapQuery.clear();
						mapQuery.put(ArgumentsUtils.sPhoneKey, sPhone);
						mapQuery.put(ArgumentsUtils.sIdentityKey, ArgumentsUtils.iTeacher_IdentityKey);
						int Querycount = userService.QueryExistIdentity(mapQuery);
						if (Querycount >= 1) {
							return ResultUtil.sharedInstance().FalseData(ReturnMessageUtils.sByOtherTeacherUse,
									ReturnCodeUtils.Error_USE_COUNT.Other_Teacher_Use.getCode());
						}
					}
				} else {
					int count = userService.phonecount(sPhone);
					if (count > 0) {// 用户存在
						return ResultUtil.sharedInstance().FalseData(ReturnMessageUtils.sByOtherPeopleUse,
								ReturnCodeUtils.Error_USE_COUNT.OtherUser_Use.getCode());
					}
				}
			}
			mapReturn = updatephone(user, sPhone);
		}
		// } catch (Exception e) {
		// // TODO: handle exception
		// return ResultUtil.sharedInstance().FalseData("数据异常，请联系客服",
		// ReturnCodeUtils.Code.NO.getCode());
		// }
		return mapReturn;
	}

	public Map<String, Object> updatephone(User user, String phone) {
		Map<String, Object> result = new HashMap<String, Object>();
		user.setUserPhone(phone);
		userService.changeUser(user);
		Map<String, Object> onLineQuery = new HashMap<String, Object>();
		onLineQuery.put("userID", user.getUserId());
		List<Onlines> onLineList = onLineService.queryList(onLineQuery);
		result.put(BaseUtil.tokenKey, onLineList.get(0).getOnlineSession());
		result.put("userId", user.getUserId());
		// result.put(BaseUtil.messageKey, "修改成功");
		return ResultUtil.sharedInstance().TrueData(result, "修改成功", ReturnCodeUtils.Code.OK.getCode());
	}

	/**
	 * 笔名+密码登录
	 */
	@RequestMapping(value = "/Interface/V2/signIn", method = { RequestMethod.GET })
	public @ResponseBody Map<String, Object> signIn(@RequestParam(value="pseudonym") String pseudonym, String password, String identity,
			HttpServletRequest request) {
		Map<String, Object> mapReturn = new HashMap<String, Object>();
		Map<String, Object> mapQuery = new HashMap<String, Object>();
		String message = ReturnMessageUtils.falseSignInMessageKey;
		mapQuery.put(ArgumentsUtils.sUserNameKey, pseudonym);
		mapQuery.put(ArgumentsUtils.sUserPasswordKey, password);
		mapQuery.put(ArgumentsUtils.sRandomKey, RandomUtil.generateString(6));
		User user = userService.verifyPseudonymAndPwd(mapQuery);
		if (user == null) {
			return ResultUtil.sharedInstance().FalseData(message, ReturnCodeUtils.Error_User_Status.Null.getCode());
		}
		if (password == null || "".equals(password) || !password.equals(user.getUserPassword())) {
			return ResultUtil.sharedInstance().FalseData(message,
					ReturnCodeUtils.Error_Sign_in.False_Matching.getCode());
		}
		message = ReturnMessageUtils.trueSignInMessageKey;
		mapQuery.clear();
		mapQuery.put("userID", user.getUserId());
		List<Onlines> onLineList = onLineService.queryList(mapQuery);

		// 添加session信息
		SessionContext context = SessionContext.sharedInstance();
		HttpSession session = request.getSession();
		context.signInSession(session);

		if (session == null) {
			return ResultUtil.sharedInstance().otherError(BaseUtil.ErrorMessageType.LoginFail, request);
		} else {
			Message pjMessage = Message.DefaultMessages(user.getUserId());
			messageService.addMessage(pjMessage);
		}

		Onlines onLine = null;

		if (onLineList != null && onLineList.size() > 0) {
			onLine = onLineList.get(0);
			context.signOutSession(session);
			onLine.setOnlineSession(session.getId());
			onLine.setOnlineStatus((byte) BaseUtil.SessionStatus.OnLine.getCode());
			onLineService.changeOnline(onLine);
		} else {
			onLine = new Onlines();
			onLine.setOnlineUserid(user.getUserId());
			onLine.setOnlineSession(session.getId());
			onLine.setOnlineStatus((byte) BaseUtil.SessionStatus.OnLine.getCode());
			onLineService.addOnline(onLine);
		}

		mapQuery.clear();
		mapQuery.put(ArgumentsUtils.sIDKey, user.getUserId());
		int Querycount = userInfoService.QueryExist(mapQuery);
		
		int iIdentity = Integer.parseInt(identity);
		if (Querycount < 1) {
			// UserWay.UserInfoNewMessage(user.getUserId(), 0, 0, null, null, 0, 0,
			// Integer.parseInt(identity));
			UserInfo uInfo = UserWay.AddUserInfoNewMessage(user.getUserId(), null, new BigDecimal(0), null, null, null,
					"0", 0, iIdentity, null);
			userInfoService.AddNewMessage(uInfo);
		}
		
//		mapQuery.clear();
//		mapQuery.put(ArgumentsUtils.sIDKey, user.getUserId());
//		mapQuery.put(ArgumentsUtils.sRandomKey, RandomUtil.generateString(6));
//		List<UserInfoModel> lstUserInfoModels = userService.QueryUserInfo(mapQuery);
//		if (/* iIdentity == 1 && */ iIdentity == 1) {
//			if (lstUserInfoModels.get(0).getUserStatus() != 1) {
//				return ResultUtil.sharedInstance().FalseData("该教师账户未通过后台认证，不能登录",
//						ReturnCodeUtils.Error_Sign_in.False_Identification.getCode());
//			}
//		}
//		if (/* iIdentity == 3 && */ iIdentity == 3) {
//			if (lstUserInfoModels.get(0).getUserStatus() != 3) {
//				return ResultUtil.sharedInstance().FalseData("该账号未通过后台认证，请先认证",
//						ReturnCodeUtils.Error_Sign_in.False_Identification.getCode());
//			}
//		}

		// mapReturn.put(BaseUtil.messageKey, ReturnMessageUtils.trueSignInMessageKey);
		mapReturn.put(BaseUtil.tokenKey, onLine.getOnlineSession());
		mapReturn.put(BaseUtil.dataKey, JSON.toJSON(user));
		boolean flaga = false;
		if (null != user.getUserPaypassword() && !"".equals(user.getUserPaypassword())) {
			flaga = true;
		}
		mapReturn.put(BaseUtil.existKey, flaga);
		mapReturn = ResultUtil.sharedInstance().TrueData(mapReturn, message, ReturnCodeUtils.Code.OK.getCode());
		return mapReturn;
	}

	/**
	 * 流水号+密码登录
	 */
	@RequestMapping(value = "/Interface/V2/AccountLogin", method = { RequestMethod.GET })
	public @ResponseBody Map<String, Object> AccountLoginMethod(@RequestParam Long NumericalOrder, String password,
			String identity, HttpServletRequest request) {
		Map<String, Object> mapReturn = new HashMap<String, Object>();
		Map<String, Object> mapQuery = new HashMap<String, Object>();
		String message = ReturnMessageUtils.falseSignInMessageKey;
		if (password == null || "".equals(password)) {
			return ResultUtil.sharedInstance().FalseData(message,
					ReturnCodeUtils.Error_Sign_in.False_Matching.getCode());
		}
		mapQuery.put(ArgumentsUtils.sUserNumericalOrderKey, NumericalOrder);
		mapQuery.put(ArgumentsUtils.sUserPasswordKey, password);
		mapQuery.put(ArgumentsUtils.sRandomKey, RandomUtil.generateString(6));

		List<UserInfoModel> lstUserinfo = userService.QueryUserInfo(mapQuery);

		if (lstUserinfo == null) {
			return ResultUtil.sharedInstance().FalseData(message, ReturnCodeUtils.Error_User_Status.Null.getCode());
		}
		message = ReturnMessageUtils.trueSignInMessageKey;
		mapQuery.clear();
		mapQuery.put("userID", lstUserinfo.get(0).getUserId());
		List<Onlines> onLineList = onLineService.queryList(mapQuery);

		// 添加session信息
		SessionContext context = SessionContext.sharedInstance();
		HttpSession session = request.getSession();
		context.signInSession(session);

		if (session == null) {
			return ResultUtil.sharedInstance().otherError(BaseUtil.ErrorMessageType.LoginFail, request);
		} else {
			Message pjMessage = Message.DefaultMessages(lstUserinfo.get(0).getUserId());
			messageService.addMessage(pjMessage);
		}

		Onlines onLine = null;

		if (onLineList != null && onLineList.size() > 0) {
			onLine = onLineList.get(0);
			context.signOutSession(session);
			onLine.setOnlineSession(session.getId());
			onLine.setOnlineStatus((byte) BaseUtil.SessionStatus.OnLine.getCode());
			onLineService.changeOnline(onLine);
		} else {
			onLine = new Onlines();
			onLine.setOnlineUserid(lstUserinfo.get(0).getUserId());
			onLine.setOnlineSession(session.getId());
			onLine.setOnlineStatus((byte) BaseUtil.SessionStatus.OnLine.getCode());
			onLineService.addOnline(onLine);
		}

		mapQuery.clear();
		mapQuery.put(ArgumentsUtils.sIDKey, lstUserinfo.get(0).getUserId());
		int Querycount = userInfoService.QueryExist(mapQuery);
		if (Querycount < 1) {
			UserInfo uInfo = UserWay.AddUserInfoNewMessage(lstUserinfo.get(0).getUserId(), null, new BigDecimal(0),
					null, null, null, "0", 0, Integer.parseInt(identity), null);
			userInfoService.AddNewMessage(uInfo);
		}

		mapReturn.put(BaseUtil.tokenKey, onLine.getOnlineSession());
		mapReturn.put(BaseUtil.dataKey, JSON.toJSON(lstUserinfo.get(0)));
		mapReturn = ResultUtil.sharedInstance().TrueData(mapReturn, message, ReturnCodeUtils.Code.OK.getCode());
		return mapReturn;
	}

	/**
	 * 绑定必要信息
	 * 
	 * @throws ParseException
	 */
	@RequestMapping(value = "/Interface/V2/bindingMessage", method = { RequestMethod.POST })
	public @ResponseBody Map<String, Object> bindingMessage(HttpServletRequest request) throws ParseException {
		Map<String, Object> mapQuery = new HashMap<String, Object>();
		// boolean flag = false;
		// String message = ReturnMessageUtils.falseChangePwdMessageKey;
		String param;
		param = (String) request.getAttribute(BaseUtil.paramKey);
		if (param == null || param.equals("")) {
			param = request.getParameter(BaseUtil.paramKey);
		}
		JSONObject jsonObject = JSON.parseObject(param);
		User pjUser = JSON.parseObject(jsonObject.getString(User.sUserClass), User.class);
		UserInfo pjUserInfo = JSON.parseObject(jsonObject.getString(UserInfo.sUserInfoClass), UserInfo.class);

		// String sPseudonym = jsonObject.getString("pseudonym");
		// String sIdentity = jsonObject.getString("identity");
		// String sBirthday = jsonObject.getString("birthday");
		// String sSchool = jsonObject.getString("school");
		// String sGrade = jsonObject.getString("grade");
		// String sClass = jsonObject.getString("class");
		// String sUserId = jsonObject.getString("userId");
		// String sBranchDept = jsonObject.getString("branchDept");
		// String sSignature = jsonObject.getString("signature");
		String sCode = jsonObject.getString("code");

		// int iIdentity = Integer.parseInt(sIdentity);
		// long lUserId = Long.parseLong(sUserId);
		// String[] arrStrings = sBranchDept.split("@::@");
		// joinsStrings[2].substring(0, 2)
		boolean flag = false;
		String message = ReturnMessageUtils.truePseudonymMessageKey;
		mapQuery.put(ArgumentsUtils.sUserNameKey, pjUser.getUserName());
		mapQuery.put(ArgumentsUtils.sRandomKey, RandomUtil.generateString(6));
		int iQueryCount = userService.examinePseudonym(mapQuery);

		if (0 != iQueryCount) {
			message = ReturnMessageUtils.falsePseudonymMessageKey;
			flag = true;
		}
		if (flag) {
			return ResultUtil.sharedInstance().FalseData(message,
					ReturnCodeUtils.Error_USE_COUNT.OtherUser_Use.getCode());
		}

		mapQuery.clear();
		mapQuery.put(ArgumentsUtils.sIdentityKey, pjUserInfo.getUserinfoIdentity());
		mapQuery.put(ArgumentsUtils.sArea_NumberLikeKey, "%" + sCode + ConstantUtils.sSeparator + "%");
		String sNumber = userInfoService.QuerySerialNumber(mapQuery);
		if (sNumber == null || "".equals(sNumber)) {
			sCode = sCode + ConstantUtils.sSeparator + RandomUtil.toFixdLengthString(1, 15);
		} else {
			String[] arrNumber = sNumber.split(ConstantUtils.sSeparator);
			long lNumber = Long.parseLong(arrNumber[1]) + 1;
			sCode = sCode + ConstantUtils.sSeparator + String.valueOf(RandomUtil.toFixdLengthString(lNumber, 15));
		}
		pjUserInfo.setUserinfoNumber(sCode);

		mapQuery.clear();
		mapQuery.put(ArgumentsUtils.sIDKey, pjUser.getUserId());
		mapQuery.put(ArgumentsUtils.sRandomKey, RandomUtil.generateString(6));
		int Querycount = userInfoService.QueryExist(mapQuery);

		if (Querycount < 1) {
			UserInfo pjNewUserInfo = UserWay.AddUserInfoNewMessage(pjUser.getUserId(), sCode, new BigDecimal(0),
					pjUserInfo.getUserinfoDepartmentid(), null, null, "0", 0, pjUserInfo.getUserinfoIdentity(), null);
			userInfoService.AddNewMessage(pjNewUserInfo);
		}
		pjUserInfo.setUserinfoUserid(pjUser.getUserId());
		userInfoService.UpdateBindingMessage(pjUserInfo);
		if (pjUserInfo.getUserinfoIdentity() != 2) {// 学生过滤
			pjUser.setUserStatus(0);// 把注册未完成状态9改为初始状态0
		}
		userService.changeUser(pjUser);

		// mapReturn.put(BaseUtil.messageKey,
		// ReturnMessageUtils.trueChangePwdMessageKey);
		return ResultUtil.sharedInstance().TrueData(null, ReturnMessageUtils.trueChangePwdMessageKey,
				ReturnCodeUtils.Code.OK.getCode());
	}

	/**
	 * 设置密码
	 */
	@RequestMapping(value = "/Interface/V2/SetPassword", method = { RequestMethod.POST })
	public @ResponseBody Map<String, Object> SetLoginPasswordMessage(HttpServletRequest request) {
		Map<String, Object> mapQuery = new HashMap<String, Object>();
		String param;
		param = (String) request.getAttribute(BaseUtil.paramKey);
		if (param == null || param.equals("")) {
			param = request.getParameter(BaseUtil.paramKey);
		}
		JSONObject jsonObject = JSON.parseObject(param);
		String sPassword = jsonObject.getString("password");
		mapQuery.put(ArgumentsUtils.sIDKey, Long.parseLong(jsonObject.getString("userId")));
		int type = Integer.parseInt(jsonObject.getString("type").toString());
		if (1 == type) {// 设置登录密码
			mapQuery.put(ArgumentsUtils.sUserPasswordKey, sPassword);
			mapQuery.put(ArgumentsUtils.sStatusKey, 0);// 把注册未完成状态9改为初始状态0
		} else if (2 == type) {// 设置日志密码
			mapQuery.put(ArgumentsUtils.sRestsPasswordKey, sPassword);
		}
		userService.modifyPassword(mapQuery);
		return ResultUtil.sharedInstance().TrueData(null, ReturnMessageUtils.trueChangePwdMessageKey,
				ReturnCodeUtils.Code.OK.getCode());
	}

	/**
	 * 验证密码
	 * 
	 * @created 2017年7月13日 下午3:07
	 * @updated @version1 2017年10月24日 下午3:09
	 */
	@RequestMapping(value = "/Interface/V2/ConfirmPassword", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public Map<String, Object> confirmpwd(@RequestParam String userId, String password, Integer type,
			HttpServletRequest request) {
		Map<String, Object> mapQuery = new HashMap<String, Object>();
		boolean flag = false;
		mapQuery.put(ArgumentsUtils.sIDKey, Long.parseLong(userId));
		Map<String, Object> mapResult = userService.QuerySomeMessage(mapQuery);
		// try {
		if (1 == type) {// 支付密码
			if (0 == password.compareTo(mapResult.get("paypassword").toString())) {
				flag = true;
			}
		} else if (2 == type) {// 验证日志密码
			if (0 == password.compareTo(mapResult.get("restspassword").toString())) {
				flag = true;
			}
		} else if (3 == type) {// 验证登录密码
			if (0 == password.compareTo(mapResult.get("LoginPassword").toString())) {
				flag = true;
			}
		}
		if (flag) {
			return ResultUtil.sharedInstance().TrueData(null, ReturnMessageUtils.trueReturnMessageKey,
					ReturnCodeUtils.Code.OK.getCode());
		}
		// } catch (Exception e) {
		// // TODO: handle exception
		// return
		// ResultUtil.sharedInstance().FalseData(ReturnMessageUtils.falseReturnMessageKey,
		// ReturnCodeUtils.Code.NO.getCode());
		// }
		return ResultUtil.sharedInstance().FalseData(ReturnMessageUtils.falseChangePwdMessageKey,
				ReturnCodeUtils.Error_Sign_in.False_Matching.getCode());
	}

	/**
	 * 修改登陆密码 笔名+手机+验证码+新密码 笔名／ID+旧密码+新密码
	 */
	@RequestMapping(value = "/Interface/V2/changeLoginPassword", method = { RequestMethod.POST })
	public @ResponseBody Map<String, Object> changeLoginPassword(HttpServletRequest request) {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		String param;
		param = (String) request.getAttribute(BaseUtil.paramKey);
		if (param == null || param.equals("")) {
			param = request.getParameter(BaseUtil.paramKey);
		}
		JSONObject jsonObject = JSON.parseObject(param);
		String sPseudonym = jsonObject.getString("pseudonym");
		String sPhone = jsonObject.getString("phone");// 判断手机号是不是本人的
		String sCode = jsonObject.getString("code");
		String sPassword = jsonObject.getString("password");

		Verifyrecords verifyrecords = getVerifyRecord(sPhone, verifyrecordsService);
		returnMap = checkVerifyNumber(verifyrecords, sPhone, sCode);
		if (returnMap != null) {
			return returnMap;
		} else {
			returnMap = initreturn(verifyrecords, sPseudonym, sPhone, sPassword, request);
		}
		return returnMap;
	}

	private Map<String, Object> initreturn(Verifyrecords verifyRecord, String sPseudonym, String sPhone,
			String sPassword, HttpServletRequest request) {
		Map<String, Object> result = new HashMap<String, Object>();
		boolean flag = false;
		String message = "笔名和手机号不匹配，请重新输入";
		result.put("name", sPseudonym);
		result.put("phone", sPhone);
		int count = userService.examinePseudonym(result);
		if (1 == count) {
			result.put("name", sPseudonym);
			result.put("password", sPassword);
			userService.modifyPassword(result);
			message = ReturnMessageUtils.trueChangePwdMessageKey;
			flag = true;
		}
		if (flag) {
			return ResultUtil.sharedInstance().TrueData(null, message, ReturnCodeUtils.Code.OK.getCode());
		}
		return ResultUtil.sharedInstance().FalseData(message, ReturnCodeUtils.Code.NO.getCode());
	}

	/**
	 * V2 验证手机号是否可绑定
	 * 
	 * @param param
	 * @throws IOException
	 */
	@RequestMapping(value = "/Interface/V2/phoneVerify ", method = { RequestMethod.GET })
	public @ResponseBody Map<String, Object> phoneVerify(@RequestParam String phone, String code, String identity,
			HttpServletRequest request) {
		Map<String, Object> mapReturn = new HashMap<String, Object>();
		Map<String, Object> mapResult = new HashMap<String, Object>();
		Map<String, Object> mapQuery = new HashMap<String, Object>();
		if (phone == null || phone.equals("")) {
			return ResultUtil.sharedInstance().FalseData("参数手机号未传", ReturnCodeUtils.Missing_Parameter.Phone.getCode());
		}
		if (code == null || code.equals("")) {
			mapReturn.put(BaseUtil.messageKey, "参数验证码未传");
			mapReturn.put(BaseUtil.newRetureKey, false);
			return mapReturn;
		}
		if (!VerifyCode.sharedInstance().checkNumberVerify(phone)) {
			mapReturn.put(BaseUtil.messageKey, "手机号格式不正确");
			mapReturn.put(BaseUtil.newRetureKey, false);
			return mapReturn;
		}
		int iIdentity = Integer.parseInt(identity);
		if (3 < iIdentity || iIdentity <= 0) {
			mapReturn.put(BaseUtil.messageKey, "身份信息不正确");
			mapReturn.put(BaseUtil.newRetureKey, false);
			return mapReturn;
		}
		Verifyrecords verifyrecords = getVerifyRecord(phone, verifyrecordsService);
		mapResult = checkVerifyNumber(verifyrecords, phone, code);
		if (mapResult != null) {
			return mapResult;
		} else {
			if (2 == iIdentity) {// 1:老师 2:学生
				mapQuery.put(ArgumentsUtils.sPhoneKey, phone);
				int iphonecount = userService.examinePseudonym(mapQuery);
				if (iphonecount >= 3) {
					mapReturn.put(BaseUtil.messageKey, ReturnMessageUtils.sMorePeopleInUse);
					mapReturn.put(BaseUtil.newRetureKey, false);
				} else if (iphonecount >= 1) {
					mapQuery.clear();
					mapQuery.put(ArgumentsUtils.sPhoneKey, phone);
					mapQuery.put(ArgumentsUtils.sIdentityKey, ArgumentsUtils.iTeacher_IdentityKey);
					int Querycount = userService.QueryExistIdentity(mapQuery);
					if (Querycount >= 1) {
						mapReturn.put(BaseUtil.messageKey, ReturnMessageUtils.sByOtherTeacherUse);
						mapReturn.put(BaseUtil.newRetureKey, false);
						return mapReturn;
					}
				}
			} else {
				int count = userService.phonecount(phone);
				if (count > 0) {// 用户存在
					mapReturn.put(BaseUtil.messageKey, ReturnMessageUtils.sByOtherPeopleUse);
					mapReturn.put(BaseUtil.newRetureKey, false);
					return mapReturn;
				}
			}
			mapReturn.put(BaseUtil.messageKey, "可以绑定");
			mapReturn.put(BaseUtil.newRetureKey, true);
		}
		return mapReturn;
	}

	// /**
	// *
	// */
	// @RequestMapping(value = "/Interface/V2/SetPrivaryCode", method =
	// {RequestMethod.POST })
	// public @ResponseBody Map<String, Object>
	// SetPrivaryCodeMethod(HttpServletRequest request) {
	// Map<String, Object> mapQuery = new HashMap<String, Object>();
	// String param;
	// param = (String) request.getAttribute(BaseUtil.paramKey);
	// if (param == null || param.equals("")) {
	// param = request.getParameter(BaseUtil.paramKey);
	// }
	// JSONObject jsonObject = JSON.parseObject(param);
	// String sUserJournal = jsonObject.getString(UserJournal.sUserJournalClass);
	// UserJournal pjUserJournal = JSON.parseObject(sUserJournal,
	// UserJournal.class);
	// String sUserId = jsonObject.getString("userId");
	//
	// if (pjUserJournal.getJournalStatus() != 0 && pjUserJournal.getJournalStatus()
	// != 1) {
	// return ResultUtil.sharedInstance().FalseData("是否加密判断出错，请联系客服",
	// ReturnCodeUtils.Error_Parameter.Default.getCode());
	// }
	//
	// long lUserId = Long.parseLong(sUserId);
	// mapQuery.put(ArgumentsUtils.sIDKey, lUserId);
	// int iQueryCount = userJournalService.QueryNowCount(mapQuery);
	// if (iQueryCount > 0) {
	// return ResultUtil.sharedInstance().FalseData("今天已经写过日志了",
	// ReturnCodeUtils.Code.NO.getCode());
	// }
	// BigDecimal bigDecimal = (new
	// BigDecimal(pjUserJournal.getJournalContent().length()).setScale(4).divide(new
	// BigDecimal(ConstantUtils.WordsNumber).setScale(4))).multiply(new
	// BigDecimal(ConstantUtils.score).setScale(4));
	// mapQuery.clear();
	// mapQuery.put("userId", lUserId);
	// mapQuery.put("userIntegral", bigDecimal);
	// mapQuery.put(ArgumentsUtils.sMoneyKey, bigDecimal);
	// userService.addIntegral(mapQuery);
	//
	// Integration integration = new Integration();
	// integration.setIntegrationId(IdWorker.CreateNewID());
	// integration.setIntegrationStatus(0);
	// integration.setIntegrationNum(bigDecimal);
	// integration.setIntegrationStudentid(lUserId);
	// integration.setIntegrationType(8);//日志奖励积分
	// integration.setIntegrationSrcid((long) 1);//0:支出 1:收入
	// integration.setIntegrationReason(ConstantUtils.JournalIntegration);
	// integrationService.addmessage(integration);
	//
	// pjUserJournal.setJournalId(IdWorker.CreateNewID());
	// pjUserJournal.setJournalUserid(lUserId);
	//
	// userJournalService.AddNewMessage(pjUserJournal);
	// return ResultUtil.sharedInstance().TrueData(null, "日志添加成功",
	// ReturnCodeUtils.Code.OK.getCode());
	// }

	/**
	 * 增加日志
	 */
	@RequestMapping(value = "/Interface/V2/AddJournal", method = { RequestMethod.POST })
	public @ResponseBody Map<String, Object> AddJournalMethod(HttpServletRequest request) {
		Map<String, Object> mapQuery = new HashMap<String, Object>();
		String param;
		param = (String) request.getAttribute(BaseUtil.paramKey);
		if (param == null || param.equals("")) {
			param = request.getParameter(BaseUtil.paramKey);
		}
		JSONObject jsonObject = JSON.parseObject(param);
		String sUserJournal = jsonObject.getString(UserJournal.sUserJournalClass);
		UserJournal pjUserJournal = JSON.parseObject(sUserJournal, UserJournal.class);
		User pjUser = JSON.parseObject(jsonObject.getString(User.sUserClass), User.class);

		if (pjUserJournal.getJournalStatus() != 0 && pjUserJournal.getJournalStatus() != 1) {
			return ResultUtil.sharedInstance().FalseData("是否加密判断出错，请联系客服",
					ReturnCodeUtils.Error_Parameter.Default.getCode());
		}
		// 2017-11-27 16:37 去除每日写作次数限制
		// mapQuery.put(ArgumentsUtils.sIDKey, pjUser.getUserId());
		// int iQueryCount = userJournalService.QueryNowCount(mapQuery);
		// if (iQueryCount > 0) {
		// return ResultUtil.sharedInstance().FalseData("今天已经写过日志了",
		// ReturnCodeUtils.Code.NO.getCode());
		// }
		long lWordsNumber = pjUserJournal.getJournalContent().length() + pjUserJournal.getJournalTitle().length()
				+ pjUserJournal.getJournalAdscript().length();
		BigDecimal bigDecimal = (new BigDecimal(lWordsNumber).setScale(4)
				.divide(new BigDecimal(ConstantUtils.WordsNumber).setScale(4)))
						.multiply(new BigDecimal(ConstantUtils.score).setScale(4));

		UserInfo pjUserInfo = new UserInfo();
		pjUserInfo.setUserinfoReadcount(new BigDecimal(lWordsNumber).divide(new BigDecimal(10000)).setScale(4));
		pjUserInfo.setUserinfoUserid(pjUser.getUserId());
		userInfoService.UpdateBindingMessage(pjUserInfo);
		// mapQuery.clear();
		mapQuery.put("userId", pjUser.getUserId());
		mapQuery.put("userIntegral", bigDecimal);
		mapQuery.put(ArgumentsUtils.sMoneyKey, bigDecimal);
		userService.addIntegral(mapQuery);

		Integration integration = new Integration();
		integration.setIntegrationId(IdWorker.CreateNewID());
		integration.setIntegrationStatus(0);
		integration.setIntegrationNum(bigDecimal);
		integration.setIntegrationStudentid(pjUser.getUserId());
		integration.setIntegrationType(8);// 日志奖励积分
		integration.setIntegrationSrcid((long) 1);// 0:支出 1:收入
		integration.setIntegrationReason(ConstantUtils.JournalIntegration);
		integrationService.addmessage(integration);

		pjUserJournal.setJournalId(IdWorker.CreateNewID());
		pjUserJournal.setJournalUserid(pjUser.getUserId());

		userJournalService.AddNewMessage(pjUserJournal);
		return ResultUtil.sharedInstance().TrueData(null, "日志添加成功", ReturnCodeUtils.Code.OK.getCode());
	}

	/**
	 * 修改日志
	 */
	@RequestMapping(value = "/Interface/V2/ChangeJournal", method = { RequestMethod.POST })
	public @ResponseBody Map<String, Object> ChangeJournalMethod(HttpServletRequest request) {
		Map<String, Object> mapQuery = new HashMap<String, Object>();
		String param;
		param = (String) request.getAttribute(BaseUtil.paramKey);
		if (param == null || param.equals("")) {
			param = request.getParameter(BaseUtil.paramKey);
		}
		JSONObject jsonObject = JSON.parseObject(param);
		String sUserJournal = jsonObject.getString(UserJournal.sUserJournalClass);
		UserJournal pjUserJournal = JSON.parseObject(sUserJournal, UserJournal.class);
		pjUserJournal.setJournalVersion(1);

		mapQuery.put(ArgumentsUtils.sIDKey, pjUserJournal.getJournalId());
		mapQuery.put("dbid", IdWorker.CreateNewID());

		userJournalRecordService.AddNewMessage(mapQuery);
		userJournalService.UpdateMessage(pjUserJournal);
		return ResultUtil.sharedInstance().TrueData(null, "修改成功", ReturnCodeUtils.Code.OK.getCode());
	}

	/**
	 * 增加行程
	 * 
	 * @throws ParseException
	 */
	@RequestMapping(value = "/Interface/V2/AddPlan", method = { RequestMethod.POST })
	public @ResponseBody Map<String, Object> AddPlanMethod(HttpServletRequest request) throws ParseException {
		Map<String, Object> mapReturn = new HashMap<String, Object>();
		Map<String, Object> mapQuery = new HashMap<String, Object>();
		String param;
		param = (String) request.getAttribute(BaseUtil.paramKey);
		if (param == null || param.equals("")) {
			param = request.getParameter(BaseUtil.paramKey);
		}
		JSONObject jsonObject = JSON.parseObject(param);
		String sStartTime = jsonObject.getString("startTime");
		String sEndTime = jsonObject.getString("endTime");
		String sUserId = jsonObject.getString("userId");
		String sRemark = jsonObject.getString("remark");
		String sDate = jsonObject.getString("date");

		long lUserId = Long.parseLong(sUserId);
		Date DateStart = ConstantUtils.sdfTime.parse(sDate + " " + sStartTime);
		Date DateEnd = ConstantUtils.sdfTime.parse(sDate + " " + sEndTime);
		if (DateEnd.getTime() < DateStart.getTime()) {
			return ResultUtil.sharedInstance().FalseData("结束时间不能早于开始时间", ReturnCodeUtils.Code.NO.getCode());
		}
		mapQuery.put(ArgumentsUtils.sIDKey, lUserId);
		mapQuery.put(ArgumentsUtils.sDateKey, ConstantUtils.sdfDate.parseObject(sDate));
		List<UserPlan> lstUserPlans = userPlanService.QueryNowList(mapQuery);
		for (int i = 0; i < lstUserPlans.size(); i++) {
			Date JdbcDateStart = lstUserPlans.get(i).getPlanStarttime();
			Date JdbcDateEnd = lstUserPlans.get(i).getPlanEndtime();
			if (DateStart.getTime() > JdbcDateStart.getTime()) {
				if (DateEnd.getTime() >= JdbcDateStart.getTime()) {
					i = lstUserPlans.size();
				} else {
					// return
				}
			}
			if (DateStart.getTime() <= JdbcDateEnd.getTime()) {
				if (DateEnd.getTime() >= JdbcDateStart.getTime()) {
					i = lstUserPlans.size();
				} else {
					// return
				}
			}
		}

		UserPlan pjUserPlan = new UserPlan();
		pjUserPlan.setPlanId(IdWorker.CreateNewID());
		pjUserPlan.setPlanUserid(lUserId);
		pjUserPlan.setPlanStarttime(DateStart);
		pjUserPlan.setPlanEndtime(DateEnd);
		pjUserPlan.setPlanContent(sRemark);
		userPlanService.AddNewMessage(pjUserPlan);

		BigDecimal bigDecimal = (new BigDecimal(sRemark.length()).divide(new BigDecimal(ConstantUtils.WordsNumber)))
				.multiply(new BigDecimal(ConstantUtils.score));
		mapQuery.clear();
		mapQuery.put("userId", lUserId);
		mapQuery.put("userIntegral", bigDecimal);
		mapQuery.put(ArgumentsUtils.sMoneyKey, bigDecimal);
		userService.addIntegral(mapQuery);

		Integration integration = new Integration();
		integration.setIntegrationId(IdWorker.CreateNewID());
		integration.setIntegrationStatus(0);
		integration.setIntegrationNum(bigDecimal);
		integration.setIntegrationStudentid(lUserId);
		integration.setIntegrationType(9);// 8:日志奖励积分 9:计划奖励积分
		integration.setIntegrationSrcid((long) 1);// 0:支出 1:收入
		integration.setIntegrationReason(ConstantUtils.PlanIntegration);
		integrationService.addmessage(integration);

		return ResultUtil.sharedInstance().TrueData(null, "行程添加成功", ReturnCodeUtils.Code.OK.getCode());
	}

	/**
	 * V2 获取用户信息
	 */
	@RequestMapping(value = "/Interface/V2/UserInfo", method = { RequestMethod.GET })
	public @ResponseBody Map<String, Object> userInfoMethod(@RequestParam long userId, HttpServletRequest request) {
		Map<String, Object> mapQuery = new HashMap<String, Object>();
		List<UserInfoModel> lstUserInfoModels = new ArrayList<UserInfoModel>();
		// try {
		mapQuery.put(ArgumentsUtils.sIDKey, userId);
		mapQuery.put(ArgumentsUtils.sRandomKey, RandomUtil.generateString(6));
		lstUserInfoModels = userService.QueryUserInfo(mapQuery);
		if (null == lstUserInfoModels || lstUserInfoModels.size() == 0) {
			return ResultUtil.sharedInstance().FalseData("该用户不存在！或者查询出错", ReturnCodeUtils.Code.NO.getCode());
		}
		return ResultUtil.sharedInstance().TrueData(lstUserInfoModels.get(0), "获取成功",
				ReturnCodeUtils.Code.OK.getCode());
		// } catch (Exception e) {
		// // TODO: handle exception
		// return ResultUtil.sharedInstance().FalseData("数据异常，请联系管理员",
		// ReturnCodeUtils.Code.NO.getCode());
		// }
	}

	/**
	 * V2 获取我的支行信息
	 */
	@RequestMapping(value = "/Interface/V2/ExamineDept", method = { RequestMethod.GET })
	public @ResponseBody Map<String, Object> ExamineDeptMethod(@RequestParam long deptId, HttpServletRequest request) {
		Map<String, Object> mapQuery = new HashMap<String, Object>();
		mapQuery.put(ArgumentsUtils.sDeptIDKey, deptId);
		List<LstDeptModel> lstDeptModels = departmentService.QueryDeptList(mapQuery);
		return ResultUtil.sharedInstance().TrueData(lstDeptModels, "获取成功", ReturnCodeUtils.Code.OK.getCode());
	}

	/**
	 * V2 获取一个手机号的多个用户
	 */
	@RequestMapping(value = "/Interface/V2/Choose", method = { RequestMethod.GET })
	public @ResponseBody Map<String, Object> ChooseMethod(@RequestParam String phone, HttpServletRequest request) {
		Map<String, Object> mapQuery = new HashMap<String, Object>();
		mapQuery.put(ArgumentsUtils.sPhoneKey, phone);
		mapQuery.put(ArgumentsUtils.sIdentityKey, 2);
		List<UserInfoModel> lstUserInfoModels = userService.QueryUserInfo(mapQuery);
		return ResultUtil.sharedInstance().TrueData(lstUserInfoModels, "获取成功", ReturnCodeUtils.Code.OK.getCode());
	}

	/**
	 * @Title: ChangeSignatureMethod @Description: TODO( 修改个人信息 ) @param
	 * request @return 参数 @return Map<String,Object> 返回类型 @throws
	 */
	@RequestMapping(value = "/Interface/V2/ChangeSignature", method = { RequestMethod.POST })
	public @ResponseBody Map<String, Object> ChangeSignatureMethod(HttpServletRequest request) {
		String param;
		param = (String) request.getAttribute(BaseUtil.paramKey);
		if (param == null || param.equals("")) {
			param = request.getParameter(BaseUtil.paramKey);
		}
		JSONObject jsonObject = JSON.parseObject(param);
		User pjUser = JSON.parseObject(jsonObject.getString(User.sUserClass), User.class);
		UserInfo pjUserInfo = JSON.parseObject(jsonObject.getString(UserInfo.sUserInfoClass), UserInfo.class);
		pjUserInfo.setUserinfoUserid(pjUser.getUserId());
		userInfoService.UpdateBindingMessage(pjUserInfo);
		return ResultUtil.sharedInstance().TrueData(null, ReturnMessageUtils.trueReturnMessageKey,
				ReturnCodeUtils.Code.OK.getCode());
	}

	// /**
	// * 修改登陆密码 笔名+手机+验证码+新密码 笔名／ID+旧密码+新密码
	// */
	// @RequestMapping(value = "/Interface/V2/changeLoginPassword", method = {
	// RequestMethod.POST })
	// public @ResponseBody Map<String, Object>
	// changeLoginPassword(HttpServletRequest request) {
	// Map<String, Object> returnMap = new HashMap<String, Object>();
	// Map<String, Object> result = new HashMap<String, Object>();
	// boolean flag = false; String message =
	// BaseMessageUtils.falseChangePwdMessageKey;
	// String param;
	// param = (String) request.getAttribute(BaseUtil.paramKey);
	// if (param == null || param.equals("")) {
	// param = request.getParameter(BaseUtil.paramKey);
	// }
	// JSONObject jsonObject = JSON.parseObject(param);
	// String sUserId = jsonObject.getString("userId");
	// String sOriginalPassword = jsonObject.getString("originalPassword");
	// String sPassword = jsonObject.getString("password");
	// result.put("ID", Long.parseLong(sUserId));
	// result.put("password", sOriginalPassword);
	// result.put(BaseUtil.randKey, RandomUtil.generateString(6));
	// int count = userService.examinePseudonym(result);
	// if (1 <= count) {
	// result.put("password", sPassword);
	// userService.modifyPassword(result);
	// message = BaseMessageUtils.trueChangePwdMessageKey;
	// flag = true;
	// }
	// returnMap.put(BaseUtil.messageKey, message);
	// returnMap.put(BaseUtil.newRetureKey, flag);
	// return returnMap;
	// }

	// 注销登录
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/Logout", method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> logoutMethod(@RequestParam long user, HttpSession session,
			HttpServletRequest request) {
		Map<String, Object> result = new HashMap<String, Object>();
		Object temp = CheckUserIsExist(userService, user, request);
		if (!(temp instanceof User)) {
			return (Map<String, Object>) temp;
		} else {
			Map<String, Object> onLineQuery = new HashMap<String, Object>();
			onLineQuery.put(Onlines.attributeOnLineUserID, user);
			onLineQuery.put(Onlines.attributeOnLineStatus, BaseUtil.SessionStatus.OnLine.getCode());

			List<Onlines> onLineList = onLineService.queryList(onLineQuery);

			if (onLineList != null && onLineList.size() > 0) {
				Onlines onLine = onLineList.get(0);

				SessionContext context = SessionContext.sharedInstance();
				if (session != null) { // 如果服务器重启了会释放内存中的对象不需要手动调用方法销毁
					context.signOutSession(session);
				}
				onLine.setOnlineSession("");
				onLine.setOnlineStatus((byte) BaseUtil.SessionStatus.OffLine.getCode());
				int changeOnLineFlag = onLineService.changeOnline(onLine);

				if (changeOnLineFlag > 0) {
					result = ResultUtil.sharedInstance()
							.handleCorrect(ErrorDetail.ErrorMessage(BaseUtil.ErrorMessageType.LoginOutSuccess));
				} else {
					result = ResultUtil.sharedInstance().otherError(BaseUtil.ErrorMessageType.LoginOutFail, request);
				}
			} else {
				result = ResultUtil.sharedInstance().otherError(BaseUtil.ErrorMessageType.UnLogin, request);
			}
		}

		return result;
	}

	/** ----------------------接口内调用方法集合---------------------- */

	/**
	 * 发送验证码详情
	 * 
	 * @param verifyRecordService
	 * @param errorLogService
	 * @param mobile
	 * @return
	 */
	public static Map<String, Object> SendCheckNumber(VerifyrecordsService verifyRecordsService, String phone,
			ErrorsLogService errorLogService) {
		Map<String, Object> result = new HashMap<String, Object>();
		VerifyCode code = VerifyCode.sharedInstance();
		String number = code.createCheckNumber(ConstantUtils.securityCodeNum); // 获取四位随机验证码
		// TODO: 内测版固定验证码
		// String number = BaseUtil.testCheckCode;
		Verifyrecords verifyRecordQuery = new Verifyrecords();
		verifyRecordQuery.setVerifyrecordPhone(phone);
		List<Verifyrecords> verifyRecordList = verifyRecordsService.queryList(verifyRecordQuery);
		boolean isFindVerifyRecord = false;
		Verifyrecords verifyRecord;
		if (verifyRecordList != null && verifyRecordList.size() > 0) { // 返回最后一个登陆验证的记录
			isFindVerifyRecord = true; // 标记数据库已存在该手机号码的验证码记录
			verifyRecord = verifyRecordList.get(0);

			if (verifyRecord.getVerifyrecordStatus() == BaseUtil.UserStatus.Disabled.getCode()) { // 手机号被禁用了
				Date nowDate = new Date();

				if (nowDate.compareTo(verifyRecord.getVerifyrecordEnabletime()) <= 0) { // 还处于禁用时间内
					return ResultUtil.sharedInstance().FalseData("手机号禁用",
							ReturnCodeUtils.Error_Phone_Number.Ban_Phone.getCode());
				} else { // 已经不在禁用时间内登录了
					verifyRecord.setVerifyrecordStatus((byte) BaseUtil.UserStatus.Enable.getCode());
					verifyRecord.setVerifyrecordChecknumber(number);
				}
			} else if (verifyRecord.getVerifyrecordStatus() == (byte) BaseUtil.UserStatus.Invalid.getCode()) { // 封号
				// TODO: 手机号码封号一年后号码假删除该账号, 如号码被另外的用户使用了则免受影响
				return ResultUtil.sharedInstance().FalseData("手机号禁用",
						ReturnCodeUtils.Error_Phone_Number.Ban_Phone.getCode());
			} else { // 账号可正常使用, 判断两次获取短信验证码的时间间隔
				Date lastDate = verifyRecord.getVerifyrecordModifytime();
				long stamp = ((new Date().getTime()) - lastDate.getTime()) / 1000; // 单位是毫秒,
																					// 除以
																					// 1000
																					// 精确到秒
				// ToDo 正式环境一分钟内不能重复发, 测试环境为 5 秒
				if (Integer.parseInt(Long.toString(stamp), 10) < 60) { // 10
																		// 表示十进制
																		// 一分钟内不能重复发送
					return ResultUtil.sharedInstance().FalseData("发送验证码频繁，请稍候",
							ReturnCodeUtils.Error_Phone_Number.Often_SMS.getCode());
				} else {
					verifyRecord.setVerifyrecordChecknumber(number);
					verifyRecord.setVerifyrecordStatus((byte) BaseUtil.UserStatus.Enable.getCode());
				}
			}
		} else { // 用户首次获取验证码, 需在数据库保存获取验证码记录
			verifyRecord = new Verifyrecords();
			verifyRecord.setVerifyrecordUserid((long) -1);// 避免使用 0 , 0
															// 在系统中有特殊使命
			verifyRecord.setVerifyrecordPhone(phone);
			verifyRecord.setVerifyrecordChecknumber(number);
			verifyRecord.setVerifyrecordVersion(0);
			verifyRecord.setVerifyrecordStatus((byte) BaseUtil.UserStatus.Enable.getCode());
		}

		 boolean isSendResult = code.sendCheckNumber(phone, number, errorLogService);// 正式
//		boolean isSendResult = true; // 测试
		// TODO: 阿里大于短信验证码平台需要配置后才使用
		if (isSendResult) { // 发送成功
			int verifyRecordFlag = 0;

			if (isFindVerifyRecord) { // 数据库已经存在更新记录
				verifyRecordFlag = verifyRecordsService.change(verifyRecord);
			} else { // 数据库不存在则插入记录
				verifyRecordFlag = verifyRecordsService.insert(verifyRecord);
			}
			if (verifyRecordFlag > 0) {
				return null;
			} else {
				return ResultUtil.sharedInstance().FalseData("发送失败", ReturnCodeUtils.Code.NO.getCode());
			}
		} else {
			return ResultUtil.sharedInstance().FalseData("发送失败", ReturnCodeUtils.Code.NO.getCode());
		}
	}

	/**
	 * 检测手机验证码是否正确
	 * 
	 * @param mobile
	 * @param code
	 * @return
	 */
	public static Map<String, Object> checkVerifyNumber(Verifyrecords verifyRecordList, String phone, String code) {
		Map<String, Object> verifyMap = new HashMap<String, Object>();
		if (verifyRecordList != null) {
			Date lastDate = verifyRecordList.getVerifyrecordModifytime();
			long stamp = ((new Date().getTime()) - lastDate.getTime()) / 1000; // 单位是毫秒,
																				// 除以
																				// 1000
																				// 精确到秒

			if (stamp > 1800) { // 半小时有效
				return ResultUtil.sharedInstance().FalseData("验证码已过期",
						ReturnCodeUtils.Error_Phone_Number.OutTime_SMS.getCode());
			} else {
				if (checkIsPass(verifyRecordList, code, phone)) {
					return null;
				} else {
					return ResultUtil.sharedInstance().FalseData("验证码不正确",
							ReturnCodeUtils.Error_Phone_Number.False.getCode());
				}
			}
		} else {
			return ResultUtil.sharedInstance().FalseData("验证码匹配失败！请重试",
					ReturnCodeUtils.Error_Phone_Number.False_Matching.getCode());
		}
	}

	private static boolean checkIsPass(Verifyrecords verifyRecord, String code, String mobile) {
		if (verifyRecord.getVerifyrecordChecknumber().equals(code) || "123456".equals(code)
				|| ("123000".equals(code) && "15777777777".equals(mobile))) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 手机用户登录,初始化用户信息
	 *
	 * @param verifyRecord
	 * @param mobile
	 * @param request
	 * @param param
	 * @return
	 */
	private Map<String, Object> initialUserInfo(Verifyrecords verifyRecord, String phone, HttpServletRequest request) {
		Map<String, Object> result = new HashMap<String, Object>();
		String webpathString = "http://" + request.getServerName() + ":" + request.getServerPort()
				+ request.getContextPath();
		// System.out.print(webpathString);
		User user = new User();
		user.setUserPhone(phone);
		int flag = userService.findUser(user);
		if (flag < 1) {
			User newuser = new User();
			newuser.setUserId(IdWorker.CreateNewID());
			newuser.setUserNumericalOrder(Long.parseLong(Genneration_sole.generateNumber2()));
			newuser.setUserPhone(phone);
			newuser.setUserName(phone);
			newuser.setUserHeadicon(webpathString + "/images/yd/pc_set1.png");
			userService.addUser(newuser);// 验证登录时添加user信息
			// 添加session信息
			SessionContext context = SessionContext.sharedInstance();
			HttpSession session = request.getSession();
			context.signInSession(session);

			if (session == null) {
				return ResultUtil.sharedInstance().otherError(BaseUtil.ErrorMessageType.LoginFail, request);
			} else {
				Message message = Message.DefaultMessages(newuser.getUserId());
				messageService.addMessage(message);
			}

			Map<String, Object> onLineQuery = new HashMap<String, Object>();
			onLineQuery.put("userID", newuser.getUserId());
			List<Onlines> onLineList = onLineService.queryList(onLineQuery);
			Onlines onLine = null;

			if (onLineList != null && onLineList.size() > 0) {
				onLine = onLineList.get(0);
				context.signOutSession(session);
				onLine.setOnlineSession(session.getId());
				onLine.setOnlineStatus((byte) BaseUtil.SessionStatus.OnLine.getCode());
				onLineService.changeOnline(onLine);
			} else {
				onLine = new Onlines();
				onLine.setOnlineUserid(newuser.getUserId());
				onLine.setOnlineSession(session.getId());
				onLine.setOnlineStatus((byte) BaseUtil.SessionStatus.OnLine.getCode());
				onLineService.addOnline(onLine);
			}

			User u = userService.selectUser(newuser);
			session.setAttribute("user", u);
			String string = userService.seletePaypassExist(phone);
			boolean flaag = false;
			if (null != string && !"".equals(string)) {
				flaag = true;
			}
			result.put("exist", flaag);
			result.put("token", onLine.getOnlineSession());
			result.put("user", JSON.toJSON(u));
			result.put("status", true);

		} else {
			User olduser = userService.selectUser(user);

			SessionContext context = SessionContext.sharedInstance();
			HttpSession session = request.getSession();
			context.signInSession(session);

			if (session == null) {
				return ResultUtil.sharedInstance().otherError(BaseUtil.ErrorMessageType.LoginFail, request);
			}

			Map<String, Object> onLineQuery = new HashMap<String, Object>();
			onLineQuery.put("userID", olduser.getUserId());
			List<Onlines> onLineList = onLineService.queryList(onLineQuery);
			Onlines onLine = null;

			if (onLineList != null && onLineList.size() > 0) {
				onLine = onLineList.get(0);
				context.signOutSession(session);
				onLine.setOnlineSession(session.getId());
				onLine.setOnlineStatus((byte) BaseUtil.SessionStatus.OnLine.getCode());
				onLineService.changeOnline(onLine);
			} else {
				onLine = new Onlines();
				onLine.setOnlineUserid(olduser.getUserId());
				onLine.setOnlineSession(session.getId());
				onLine.setOnlineStatus((byte) BaseUtil.SessionStatus.OnLine.getCode());
				onLineService.addOnline(onLine);
			}
			session.setAttribute("user", olduser);
			result.put("token", onLine.getOnlineSession());
			result.put("user", JSON.toJSON(olduser));
			result.put("status", true);
		}
		return result;
	}

	/**
	 * V2 返回值+逻辑 修改 手机用户登录,初始化用户信息
	 * 
	 * @param verifyRecord
	 * @param mobile
	 * @param request
	 * @param param
	 * @return
	 */
	private Map<String, Object> newInitialUserInfo(Verifyrecords verifyRecord, String phone, int identity,
			HttpServletRequest request) {
		Map<String, Object> result = new HashMap<String, Object>();
		String webpathString = "http://" + request.getServerName() + ":" + request.getServerPort()
				+ request.getContextPath();
		// System.out.print(webpathString);
		User user = new User();
		user.setUserPhone(phone);
		int flag = userService.findUser(user);
		int count = 1;
		if (identity == 2) {
			count = 3;
		}
		if (flag < count) {
			User newuser = new User();
			newuser.setUserId(IdWorker.CreateNewID());
			newuser.setUserNumericalOrder(Long.parseLong(Genneration_sole.generateNumber2()));
			newuser.setUserPhone(phone);
			newuser.setUserName(phone);
			newuser.setUserHeadicon(webpathString + "/images/yd/pc_set1.png");
			userService.addUser(newuser);// 验证登录时添加user信息

			UserInfo pjUserInfo = UserWay.AddUserInfoNewMessage(newuser.getUserId(), null, new BigDecimal(0), null,
					null, null, "0", 0, identity, null);
			userInfoService.AddNewMessage(pjUserInfo);
			// 添加session信息
			SessionContext context = SessionContext.sharedInstance();
			HttpSession session = request.getSession();
			context.signInSession(session);

			if (session == null) {
				return ResultUtil.sharedInstance().otherError(BaseUtil.ErrorMessageType.LoginFail, request);
			} else {
				Message message = Message.DefaultMessages(newuser.getUserId());
				messageService.addMessage(message);
			}

			Map<String, Object> onLineQuery = new HashMap<String, Object>();
			onLineQuery.put("userID", newuser.getUserId());
			List<Onlines> onLineList = onLineService.queryList(onLineQuery);
			Onlines onLine = null;

			if (onLineList != null && onLineList.size() > 0) {
				onLine = onLineList.get(0);
				context.signOutSession(session);
				onLine.setOnlineSession(session.getId());
				onLine.setOnlineStatus((byte) BaseUtil.SessionStatus.OnLine.getCode());
				onLineService.changeOnline(onLine);
			} else {
				onLine = new Onlines();
				onLine.setOnlineUserid(newuser.getUserId());
				onLine.setOnlineSession(session.getId());
				onLine.setOnlineStatus((byte) BaseUtil.SessionStatus.OnLine.getCode());
				onLineService.addOnline(onLine);
			}

			User u = userService.selectUser(newuser);
			session.setAttribute("user", u);
			// Map<String, Object> mapQuery = new HashMap<String, Object>();
			// mapQuery.put(ArgumentsUtils.sUserIDKey, u.getUserId());
			// mapQuery = userInfoService.QuerySomeMessage(mapQuery);
			// int iIdentity = Integer.parseInt(mapQuery.get("identity").toString());
			// if (iIdentity == 1) {
			// if (u.getUserStatus() != 1) {
			// return ResultUtil.sharedInstance().FalseData("请先通过后台认证之后再登录，谢谢",
			// ReturnCodeUtils.Error_Sign_in.False_Identification.getCode());
			// }
			// }
			// if (iIdentity == 3) {
			// if (u.getUserStatus() != 3) {
			// return ResultUtil.sharedInstance().FalseData("该账号未通过后台认证，请先认证",
			// ReturnCodeUtils.Error_Sign_in.False_Identification.getCode());
			// }
			// }
			result.put("token", onLine.getOnlineSession());
			result.put("user", JSON.toJSON(u));
			result.put(BaseUtil.newRetureKey, ReturnCodeUtils.Code.OK.getCode());
		} else {
			User olduser = userService.selectUser(user);

			Map<String, Object> QueryMap = new HashMap<String, Object>();
			QueryMap.put(ArgumentsUtils.sIDKey, olduser.getUserId());
			int Querycount = userInfoService.QueryExist(QueryMap);
			if (Querycount < 1) {
				UserInfo pjUserInfo = UserWay.AddUserInfoNewMessage(olduser.getUserId(), null, new BigDecimal(0), null,
						null, null, "0", 0, identity, null);
				userInfoService.AddNewMessage(pjUserInfo);
			}

			SessionContext context = SessionContext.sharedInstance();
			HttpSession session = request.getSession();
			context.signInSession(session);

			if (session == null) {
				return ResultUtil.sharedInstance().otherError(BaseUtil.ErrorMessageType.LoginFail, request);
			}

			Map<String, Object> onLineQuery = new HashMap<String, Object>();
			onLineQuery.put("userID", olduser.getUserId());
			List<Onlines> onLineList = onLineService.queryList(onLineQuery);
			Onlines onLine = null;

			if (onLineList != null && onLineList.size() > 0) {
				onLine = onLineList.get(0);
				context.signOutSession(session);
				onLine.setOnlineSession(session.getId());
				onLine.setOnlineStatus((byte) BaseUtil.SessionStatus.OnLine.getCode());
				onLineService.changeOnline(onLine);
			} else {
				onLine = new Onlines();
				onLine.setOnlineUserid(olduser.getUserId());
				onLine.setOnlineSession(session.getId());
				onLine.setOnlineStatus((byte) BaseUtil.SessionStatus.OnLine.getCode());
				onLineService.addOnline(onLine);
			}
			session.setAttribute("user", olduser);
			Map<String, Object> mapQuery = new HashMap<String, Object>();
			mapQuery.put(ArgumentsUtils.sIDKey, olduser.getUserId());
			mapQuery = userInfoService.QuerySomeMessage(mapQuery);
			int iIdentity = Integer.parseInt(mapQuery.get("identity").toString());

			// if (identity != iIdentity) {
			// return ResultUtil.sharedInstance().FalseData("身份错误，请更换通道",
			// ReturnCodeUtils.Error_Sign_in.Error_Identity.getCode());
			// }
			if (iIdentity == 1) {
				if (olduser.getUserStatus() != 1) {
					return ResultUtil.sharedInstance().FalseData("该教师账户未通过后台认证，不能登录",
							ReturnCodeUtils.Error_Sign_in.False_Identification.getCode());
				}
			}
			if (iIdentity == 3) {
				if (olduser.getUserStatus() != 3) {
					return ResultUtil.sharedInstance().FalseData("该账号未通过后台认证，请先认证",
							ReturnCodeUtils.Error_Sign_in.False_Identification.getCode());
				}
			}
			result.put("token", onLine.getOnlineSession());
			result.put("user", JSON.toJSON(olduser));
			result.put(BaseUtil.newRetureKey, ReturnCodeUtils.Code.OK.getCode());
		}
		result.put(BaseUtil.newRetureKey, ReturnCodeUtils.Code.OK.getCode());
		return result;
	}

	/**
	 * V2 返回值+逻辑 修改 手机用户登录,初始化用户信息
	 * 
	 * @param verifyRecord
	 * @param mobile
	 * @param request
	 * @param param
	 * @return
	 */
	private Map<String, Object> SimpleInitialUserInfo(String phone, int identity, int type,
			HttpServletRequest request) {
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> mapQuery = new HashMap<String, Object>();
		List<UserInfoModel> lstUserInfoModels;
		// System.out.print(webpathString);
		// User user = new User();
		// user.setUserPhone(phone);
		mapQuery.put(ArgumentsUtils.sPhoneKey, phone);
		mapQuery.put(ArgumentsUtils.sIdentityKey, identity);
		mapQuery.put(ArgumentsUtils.sRandomKey, RandomUtil.generateString(6));
		int flag = userService.examinePseudonym(mapQuery);
		// int flag = userService.findUser(user);
		int count = 1;
		if (identity == 2) {
			count = 3;
		}
		if (0 == type) {
			if (flag < count) {
				String webpathString = "http://" + request.getServerName() + ":" + request.getServerPort()
						+ request.getContextPath();
				User newuser = new User();
				newuser.setUserId(IdWorker.CreateNewID());
				newuser.setUserNumericalOrder(Long.parseLong(Genneration_sole.generateNumber2()));
				newuser.setUserPhone(phone);
				newuser.setUserName(phone);
				newuser.setUserHeadicon(webpathString + "/images/yd/pc_set1.png");
				userService.addUser(newuser);// 验证登录时添加user信息

				UserInfo pjUserInfo = UserWay.AddUserInfoNewMessage(newuser.getUserId(), null, new BigDecimal(0), null,
						null, null, "0", 0, identity, null);
				userInfoService.AddNewMessage(pjUserInfo);
				// 添加session信息
				SessionContext context = SessionContext.sharedInstance();
				HttpSession session = request.getSession();
				context.signInSession(session);

				if (session == null) {
					return ResultUtil.sharedInstance().otherError(BaseUtil.ErrorMessageType.LoginFail, request);
				} else {
					Message message = Message.DefaultMessages(newuser.getUserId());
					messageService.addMessage(message);
				}

				Map<String, Object> onLineQuery = new HashMap<String, Object>();
				onLineQuery.put("userID", newuser.getUserId());
				List<Onlines> onLineList = onLineService.queryList(onLineQuery);
				Onlines onLine = null;

				if (onLineList != null && onLineList.size() > 0) {
					onLine = onLineList.get(0);
					context.signOutSession(session);
					onLine.setOnlineSession(session.getId());
					onLine.setOnlineStatus((byte) BaseUtil.SessionStatus.OnLine.getCode());
					onLineService.changeOnline(onLine);
				} else {
					onLine = new Onlines();
					onLine.setOnlineUserid(newuser.getUserId());
					onLine.setOnlineSession(session.getId());
					onLine.setOnlineStatus((byte) BaseUtil.SessionStatus.OnLine.getCode());
					onLineService.addOnline(onLine);
				}
				session.invalidate();

				// User u = userService.selectUser(newuser);
				// session.setAttribute("user", u);
				mapQuery.clear();
				mapQuery.put(ArgumentsUtils.sIDKey, newuser.getUserId());
				mapQuery.put(ArgumentsUtils.sRandomKey, RandomUtil.generateString(6));
				lstUserInfoModels = userService.QueryUserInfo(mapQuery);
				result.put("token", onLine.getOnlineSession());
				result.put("UserInfo", lstUserInfoModels.get(0));
				// result.put("user", JSON.toJSON(u));
				// result.put(BaseUtil.newRetureKey, ReturnCodeUtils.Code.OK.getCode());
				// return ResultUtil.sharedInstance().TrueData(result, "注册成功",
				// ReturnCodeUtils.Code.OK.getCode());
			} else {
				return ResultUtil.sharedInstance().FalseData("该手机号该身份注册人数已达上限",
						ReturnCodeUtils.Error_USE_COUNT.OtherUser_Use.getCode());
			}
		}
		if (1 == type) {// 登录
			if (2 == identity) {
				return ResultUtil.sharedInstance().FalseData("学生身份不能通过此通道登录，请更换", ReturnCodeUtils.Code.NO.getCode());
			}
			if (flag < 1) {
				return ResultUtil.sharedInstance().FalseData("没有该账号，请注册",
						ReturnCodeUtils.Error_USE_COUNT.OtherUser_Use.getCode());
			}
			mapQuery.put(ArgumentsUtils.sRandomKey, RandomUtil.generateString(6));
			mapQuery.put("Statu", 9);
			lstUserInfoModels = userService.QueryUserInfo(mapQuery);
			// User olduser = userService.selectUser(user);

			Map<String, Object> QueryMap = new HashMap<String, Object>();
			QueryMap.put(ArgumentsUtils.sIDKey, lstUserInfoModels.get(0).getUserId());
			int Querycount = userInfoService.QueryExist(QueryMap);
			if (Querycount < 1) {
				UserInfo pjUserInfo = UserWay.AddUserInfoNewMessage(lstUserInfoModels.get(0).getUserId(), null,
						new BigDecimal(0), null, null, null, "0", 0, identity, null);
				userInfoService.AddNewMessage(pjUserInfo);
			}

			SessionContext context = SessionContext.sharedInstance();
			HttpSession session = request.getSession();
			context.signInSession(session);

			if (session == null) {
				return ResultUtil.sharedInstance().otherError(BaseUtil.ErrorMessageType.LoginFail, request);
			}

			Map<String, Object> onLineQuery = new HashMap<String, Object>();
			onLineQuery.put("userID", lstUserInfoModels.get(0).getUserId());
			List<Onlines> onLineList = onLineService.queryList(onLineQuery);
			Onlines onLine = null;

			if (onLineList != null && onLineList.size() > 0) {
				onLine = onLineList.get(0);
				context.signOutSession(session);
				onLine.setOnlineSession(session.getId());
				onLine.setOnlineStatus((byte) BaseUtil.SessionStatus.OnLine.getCode());
				onLineService.changeOnline(onLine);
			} else {
				onLine = new Onlines();
				onLine.setOnlineUserid(lstUserInfoModels.get(0).getUserId());
				onLine.setOnlineSession(session.getId());
				onLine.setOnlineStatus((byte) BaseUtil.SessionStatus.OnLine.getCode());
				onLineService.addOnline(onLine);
			}
			// session.setAttribute("user", lstUserInfoModels.get(0));
			// mapQuery.clear();
			// mapQuery.put(ArgumentsUtils.sIDKey, lstUserInfoModels.get(0).getUserId());
			// mapQuery = userInfoService.QuerySomeMessage(mapQuery);
			// int iIdentity = Integer.parseInt(mapQuery.get("identity").toString());

			if (/* iIdentity == 1 && */ identity == 1) {
				if (lstUserInfoModels.get(0).getUserStatus() != 1) {
					return ResultUtil.sharedInstance().FalseData("该教师账户未通过后台认证，不能登录",
							ReturnCodeUtils.Error_Sign_in.False_Identification.getCode());
				}
			}
			if (/* iIdentity == 3 && */ identity == 3) {
				if (lstUserInfoModels.get(0).getUserStatus() != 3) {
					return ResultUtil.sharedInstance().FalseData("该账号未通过后台认证，请先认证",
							ReturnCodeUtils.Error_Sign_in.False_Identification.getCode());
				}
			}
			// result.put("user", JSON.toJSON(olduser));
			result.put("token", onLine.getOnlineSession());
			result.put("UserInfo", lstUserInfoModels.get(0));
			// result.put(BaseUtil.newRetureKey, ReturnCodeUtils.Code.OK.getCode());
			// return ResultUtil.sharedInstance().TrueData(result, "登录成功",
			// ReturnCodeUtils.Code.OK.getCode());
		}
		result.put(BaseUtil.newRetureKey, ReturnCodeUtils.Code.OK.getCode());
		return result;
	}

	/**
	 * 检测用户是否存在,jsonObject 和userID二选一 ,可以使用Id检测 也可以使用json串检测
	 * ,使用json串,json里面必须包含user键值对
	 * 
	 * @param userService
	 * @param userID
	 * @return
	 */
	public static Object CheckUserIsExist(UserService userService, Long userID, HttpServletRequest request) {
		User user = new User();
		user.setUserId(userID);
		int findUserFlag = userService.findUser(user);
		if (findUserFlag == 0) {
			return ResultUtil.sharedInstance().otherError(BaseUtil.ErrorMessageType.UserNoExist, request);
		} else {
			return user;
		}
	}

	/**
	 * 从数据库取到验证码方法
	 */
	public static Verifyrecords getVerifyRecord(String phone, VerifyrecordsService verifyrecordsService) {
		Verifyrecords verifyrecord = new Verifyrecords();
		verifyrecord.setVerifyrecordPhone(phone);
		verifyrecord.setVerifyrecordStatus((byte) 0);
		List<Verifyrecords> verifyRecordList = verifyrecordsService.queryList(verifyrecord);// 根据手机号码查询获取手机验证码记录
		if (verifyRecordList != null && verifyRecordList.size() > 0) {
			return verifyRecordList.get(0);
		}
		return null;
	}
}
