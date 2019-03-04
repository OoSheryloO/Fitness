package com.huban.controller;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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
import com.huban.Utils.Constant;
import com.huban.Utils.ConstantUtils;
import com.huban.Utils.RandomUtil;
import com.huban.Utils.ReturnCodeUtils;
import com.huban.Utils.ReturnMessageUtils;
import com.huban.Utils.PushBaidu.PushUtil;
import com.huban.construct.JournalModel;
import com.huban.construct.LstDeptMemberModel;
import com.huban.construct.LstDeptSave;
import com.huban.construct.TimeDate;
import com.huban.construct.UserInfoModel;
import com.huban.pojo.Activity;
import com.huban.pojo.Activitypart;
import com.huban.pojo.DepartmentWithBLOBs;
import com.huban.pojo.Dict;
import com.huban.pojo.Integration;
import com.huban.pojo.Onlines;
import com.huban.pojo.Payrecords;
import com.huban.pojo.SaveRead;
import com.huban.pojo.User;
import com.huban.pojo.UserInfo;
import com.huban.publicway.UserWay;
import com.huban.util.BaseUtil;
import com.huban.util.IdWorker;
import com.huban.util.ResultUtil;

/**
 * <p>Title: AgentController.java</p>
 * <p>Description: 代理商控制器</p>
 * <p>Company: huban</p>
 * @author Sheryl
 * @created 2017年10月9日 下午6:47:36
 */
@Controller
@RequestMapping("/Agent")
public class AgentController extends BaseController {
	
	/**
	 * 发布活动
	 * @throws ParseException 
	 */
	@RequestMapping(value = "/Interface/V2/LaunchEvent", method = {RequestMethod.POST})
	public @ResponseBody Map<String, Object> LaunchEventMethod(HttpServletRequest request) throws ParseException {
		Map<String, Object> mapResult = new HashMap<String, Object>();
		Map<String, Object> mapQuery = new HashMap<String, Object>();
		String param;
		param = (String) request.getAttribute(BaseUtil.paramKey);
		if (param == null || param.equals("")) {
			param = request.getParameter(BaseUtil.paramKey);
		}
		JSONObject jsonObject = JSON.parseObject(param);
		String sUserId = jsonObject.getString("userId");
		String sTitle = jsonObject.getString("title");
		String sActivityImage = jsonObject.getString("activityImage");
		String sPlace = jsonObject.getString("place");
		String sSponsor = jsonObject.getString("sponsor");
		String sSbrief = jsonObject.getString("sbrief");
		String sIconUrl = jsonObject.getString("iconUrl");
		String sApplyLimit = jsonObject.getString("applyLimit");
		String sApplyMoney = jsonObject.getString("applyMoney");
		String sContent = jsonObject.getString("content");
		String sType = jsonObject.getString("type");
		String sStartTime = jsonObject.getString("startTime");
		String sEndTime = jsonObject.getString("endTime");
		String sLaunchTime = jsonObject.getString("launchTime");
		
		Date TimeStart = ConstantUtils.sdfDateTime.parse(sStartTime);
		Date TimeEnd = ConstantUtils.sdfDateTime.parse(sEndTime);
		Date TimeLaunch = ConstantUtils.sdfDateTime.parse(sLaunchTime);
		
		if (TimeLaunch.getTime() < TimeStart.getTime() || TimeEnd.getTime() < TimeStart.getTime()) {
			return ResultUtil.sharedInstance().FalseData("时间错误", ReturnCodeUtils.Error_Time_Sequence.Default.getCode());
		}
		
		mapQuery.put(ArgumentsUtils.sIDKey, Long.parseLong(sUserId));
		mapQuery.put(ArgumentsUtils.sRandomKey, RandomUtil.generateString(6));
		mapResult = userInfoService.QuerySomeMessage(mapQuery);
		String sNumber = mapResult.get("Number").toString();/*.replaceAll(" +","")*///去空格
		String[] arrString = sNumber.split(ConstantUtils.sSeparator);
		
		
		long lMoney = Long.parseLong(sApplyMoney);
		Activity pjActivity = new Activity();
		pjActivity.setActivityId(IdWorker.CreateNewID());
		pjActivity.setActivityAgentID(Long.parseLong(sUserId));
		pjActivity.setActivityTitle(sTitle);
		pjActivity.setActivityImageid(sActivityImage);//活动头像
		pjActivity.setActivityWechatid((long) 0);
		pjActivity.setActivityAreacode(arrString[0]);
		pjActivity.setActivityPlace(sPlace);//活动地址
		pjActivity.setActivitySponsor(sSponsor);//主办方
		pjActivity.setActivitySbrief(sSbrief);//主办方简介
		pjActivity.setActivitySimage(sIconUrl);//主办方头像
		pjActivity.setActivityApplylimit(Integer.parseInt(sApplyLimit));//报名人数限制 -1为无限制
		pjActivity.setActivityApplymoney(sApplyMoney);//报名的钱
		if ( lMoney > 0 ) {
			pjActivity.setActivityPaytype((byte)15);//14免费 15人民币
		}else {
			pjActivity.setActivityPaytype((byte)14);//14免费 15人民币
		}
		pjActivity.setActivityMoney((long) 0);
		pjActivity.setActivityContent(sContent);//内容描述
		pjActivity.setActivityType(Integer.parseInt(sType));//活动类型 0精选 1赛事 2特训 3免费
		pjActivity.setActivityStarttime(TimeStart);
		pjActivity.setActivityEndtime(TimeEnd);
		pjActivity.setActivityLaunchtime(TimeLaunch);//活动开始时间
		activityService.AddNewMessage(pjActivity);
		
//		mapQuery.put(ArgumentsUtils.sIDKey, Long.parseLong(sUserId));
		mapResult = userInfoService.QuerySomeMessage(mapQuery);
		String[] sBelongNumber = (mapResult.get("Number").toString()).split(ConstantUtils.sSeparator);
		
		mapQuery.clear();
		mapQuery.put(ArgumentsUtils.sConditionKey, 1);//
		mapQuery.put(ArgumentsUtils.sExcludeKey, Long.parseLong(sUserId));//排除条件 组合使用
		mapQuery.put(ArgumentsUtils.sArea_NumberLikeKey, sBelongNumber[0] + ConstantUtils.sSeparator);
		List<UserInfo> LstUserInfo = userInfoService.SearchLstUserInfoByCondition(mapQuery);
		JSONObject Childjsobj=new JSONObject();
		Childjsobj.put("Status", "ActivityIssue");//发布活动 代理商
		Childjsobj.put("Type", "AppView");
		Childjsobj.put("Url", Constant.getProjectBaseUrl(request));
		for (int i = 0; i < LstUserInfo.size(); i++) {
			PushUtil.sendPushInfo(ConstantUtils.sReadBank_Activity_Issue, "您所在的地区的代理商发布了"+sTitle+"活动",Childjsobj, Long.parseLong(sUserId), LstUserInfo.get(i).getUserinfoUserid(), 1, true, ConstantUtils.iReadBank_PushType_ActivityValue, pjActivity.getActivityId(), pushDrivesService,devicesService,pushRecordsService);
		}
		return ResultUtil.sharedInstance().TrueData(null, "发布成功", ReturnCodeUtils.Code.OK.getCode());
	}
	
	
	@RequestMapping(value = "/Interface/V2/PushTest", method = {RequestMethod.POST})
	public @ResponseBody Map<String, Object> PushTestMethod(HttpServletRequest request) throws ParseException {
		String param;
		param = (String) request.getAttribute(BaseUtil.paramKey);
		if (param == null || param.equals("")) {
			param = request.getParameter(BaseUtil.paramKey);
		}
		JSONObject jsonObject = JSON.parseObject(param);
		String sUserId = jsonObject.getString("userId");
		
		return ResultUtil.sharedInstance().TrueData(null, "发布成功", ReturnCodeUtils.Code.OK.getCode());
	}
	
	/**
	 * 发布的活动列表
	 */
	@RequestMapping(value = "/Interface/V2/Launchs", method = {RequestMethod.GET})
	public 
	@ResponseBody Map<String, Object> LaunchsMethod(@RequestParam String userId, int page, int size, HttpServletRequest request) {
		Map<String, Object> mapQuery = new HashMap<String, Object>();
		mapQuery.put(ArgumentsUtils.sStartKey, size * (page - 1));
		mapQuery.put(ArgumentsUtils.sSizeKey, size);
		mapQuery.put(ArgumentsUtils.sUIDKey, Long.parseLong(userId));
		List<Activity> lstActivities = activityService.queryList(mapQuery);
		return ResultUtil.sharedInstance().TrueData(lstActivities, "获取成功", ReturnCodeUtils.Code.OK.getCode());
	}
	
	/**
	 * 活动报名人列表
	 */
	@RequestMapping(value = "/Interface/V2/ApplyDetails", method = {RequestMethod.GET})
	public 
	@ResponseBody Map<String, Object> ApplyDetailsMethod(@RequestParam long activityId, String userId, String page, String size, HttpServletRequest request) {
		Map<String, Object> mapQuery = new HashMap<String, Object>();
		int pageCount = Integer.parseInt(size);
		mapQuery.put("start", pageCount * (Integer.parseInt(page) - 1));
		mapQuery.put("size", pageCount);
		mapQuery.put(ArgumentsUtils.sDBIDKey, activityId);
		mapQuery.put(ArgumentsUtils.sConditionKey, 1);
		List<LstDeptMemberModel> lsdDeptMemberModels = activitypartService.QueryLstUser(mapQuery);
		return ResultUtil.sharedInstance().TrueData(lsdDeptMemberModels, "获取成功", ReturnCodeUtils.Code.OK.getCode());
	}
	
	/**
	 * 退款
	 */
	@RequestMapping(value = "/Interface/V2/RefundApply", method = {RequestMethod.POST})
	public 
	@ResponseBody Map<String, Object> RefundApplyMethod(HttpServletRequest request) {
		Map<String, Object> mapQuery= new HashMap<String, Object>();
		String param;
		param = (String) request.getAttribute(BaseUtil.paramKey);
		if (param == null || "".equals(param)) {
			param = request.getParameter(BaseUtil.paramKey);
		}
		JSONObject jsonObject = JSONObject.parseObject(param);
		String sUserId = jsonObject.getString("userId");
		String sActivityId = jsonObject.getString("activityId");
		String sStudentId = jsonObject.getString("studentId");
		
		long lUserId = Long.parseLong(sUserId);
		long lActivityId = Long.parseLong(sActivityId);
		long lStudentId = Long.parseLong(sStudentId);
		
		Activitypart pjActivitypart = new Activitypart();
		pjActivitypart.setActivitypartActivityid(lActivityId);
		pjActivitypart.setActivitypartStudentid(lStudentId);
		pjActivitypart.setActivitypartStatus((int) 1);
		activitypartService.ChanceMessage(pjActivitypart);
		String sMoney = activityService.selectapplymoney(lActivityId);
		mapQuery.put("userId", lStudentId);
		mapQuery.put("moneyNum", Long.parseLong(sMoney));
		userService.addMoney(mapQuery);//金币增加
		
		Payrecords payrecords = new Payrecords();
		payrecords.setPayrecordUserid(lStudentId);
		payrecords.setPayrecordBelongid(lActivityId);
		payrecords.setPayrecordOrderid((long) 0);
		payrecords.setPayrecordPaymethod(ConstantUtils.sPayForGoldCoin);
		payrecords.setPayrecordPayreason(ConstantUtils.payActRefund);
		payrecords.setPayrecordMoney(BigDecimal.valueOf(Long.parseLong(sMoney)));
		payrecords.setPayrecordTarde((byte) ConstantUtils.ShopIn);
		payrecords.setPayrecordFromuserid((long) 0);
		payrecords.setPayrecordVersion(0);
		payrecords.setPayrecordStatus((byte) 0);
		payrecords.setPayrecordTypeid((long) 2);// 2表示付费活动
		payrecordsService.addPayMessage(payrecords);// 添加消费记录
		
		mapQuery.clear();
		mapQuery.put(ArgumentsUtils.sIDKey, lUserId);
		mapQuery.put(ArgumentsUtils.sRandomKey, RandomUtil.generateString(6));
		User pjUser = userService.verifyPseudonymAndPwd(mapQuery);
		
		Activity pjActivity = activityService.selectbyid(lActivityId);
		activityService.ReduceJoinCount(lActivityId);
		
		JSONObject Childjsobj=new JSONObject();
        Childjsobj.put("Status", "ActivityFunds");//活动退款
        Childjsobj.put("Type", "AppView");
        Childjsobj.put("Url", Constant.getProjectBaseUrl(request));
        PushUtil.sendPushInfo(ConstantUtils.sReadBank_Activity_Refunds, "代理商"+pjUser.getUserName()+"取消了您"+"对于"+pjActivity.getActivityTitle()+"活动的报名",Childjsobj, lUserId, lStudentId, 1, true, null, null, pushDrivesService, devicesService, pushRecordsService);
		return ResultUtil.sharedInstance().TrueData(null, "退款成功", ReturnCodeUtils.Code.OK.getCode());
	}
	
	/**
	 * 获取某些信息
	 */
	@RequestMapping(value = "/Interface/V2/ShowMessage", method = {RequestMethod.GET})
	public 
	@ResponseBody Map<String, Object> ShowMessageMethod(@RequestParam String userId, @RequestParam(required=false)String code, int type, int page, int size, @RequestParam(required=false) String date, HttpServletRequest request) {
		Map<String, Object> mapQuery = new HashMap<String, Object>();
		long lUserId = Long.parseLong(userId);
		mapQuery.put(ArgumentsUtils.sIDKey, lUserId);
		String sUserNumber = userInfoService.QuerySerialNumber(mapQuery);
		String[] arrUserNumber = sUserNumber.split(ConstantUtils.sSeparator);
		
//		//输入授权码时 进行授权码判定
//		int ilevel = 0;
//		User pjUser = userService.selectUserByUserId(Long.parseLong(userId));
//			mapQuery.clear();
//			
//			mapQuery.put(ArgumentsUtils.sNumberKey, pjUser.getUserPhone());
//			mapQuery.put(ArgumentsUtils.sValueKey, code);
//			mapQuery.put(ArgumentsUtils.sTypeKey, ConstantUtils.iReadBank_DictType_Authorization_Code);
//			mapQuery.put(ArgumentsUtils.sStatusKey, ConstantUtils.iDefaultOneValue);
//			mapQuery.put(ArgumentsUtils.sStartKey, 0);
//			mapQuery.put(ArgumentsUtils.sSizeKey, 1);
//			List<Dict> LstDicts = dictService.SearchLstDict(mapQuery);
//			if (LstDicts.size() == 1) {
//				String sCode = LstDicts.get(0).getDictValue();
//				Long iCode = Long.parseLong(sCode);
//				if (sCode.length() % 2 == 1) {
//					double dDigit = Math.floor(Double.valueOf(sCode.length()/2)); 
//					double dNum = Math.pow(10, dDigit);
//					dNum = iCode/dNum;
//					String sLevel = String.valueOf((int)dNum);
//					ilevel = Integer.valueOf(sLevel.substring(sLevel.length()-1));
//				} else {
//					//授权码 位数不对
//				}
//			}
//		
		mapQuery.clear();
		mapQuery.put(ArgumentsUtils.sStartKey, size * (page - 1));
		mapQuery.put(ArgumentsUtils.sSizeKey, size);
		mapQuery.put(ArgumentsUtils.sArea_NumberLikeKey, "%" + arrUserNumber[0] + ConstantUtils.sSeparator + "%");
//		if (ilevel == 1) {
//			mapQuery.remove(ArgumentsUtils.sArea_NumberLikeKey);
//		}
		
		if (type == 1) {//阅读趋势情况，同时显示最新几条阅读记录 date表示分月份
			int iDate = Integer.parseInt(date);
			Date time = new Date();//当前时间
			Date dBefore = new Date();
			Calendar calendar = Calendar.getInstance(); //得到日历
			
			List<TimeDate> timeDates = new ArrayList<TimeDate>();
			
			List<LstDeptSave> lstSaveReads = saveReadService.QueryLstSaveReadForAgent(mapQuery);
			for (int i = 0; i <= iDate+1; i++) {
				calendar.setTime(time);//把当前时间赋给日历
				calendar.add(calendar.MONTH, -i);  //设置为前几月
				dBefore = calendar.getTime();   //得到前几月的时间
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //设置时间格式
				String oldtime = sdf.format(dBefore);    //格式化前几月的时间
				mapQuery.put(ArgumentsUtils.sRandomKey, RandomUtil.generateString(6));
				mapQuery.put(ArgumentsUtils.sMonthlysTimeKey, oldtime);
				long num= saveReadService.QueryReadCountByMonth(mapQuery);
				TimeDate timeDate = new TimeDate();
				timeDate.setCount((int) num);
				timeDate.setTime(dBefore.getMonth()+1);
				timeDates.add(timeDate);
			}
			mapQuery.clear();
			mapQuery.put("datecount", timeDates);
			mapQuery.put("lstSaveReads", lstSaveReads);
			return ResultUtil.sharedInstance().TrueData(mapQuery, "获取成功", ReturnCodeUtils.Code.OK.getCode());
			
		}
		if (type == 2) {//所属的学生每天提交的日志 date表示某天
			mapQuery.put(ArgumentsUtils.sDayTimeKey, date);
			List<JournalModel> lstJournalModels = userJournalService.QueryLstJournalModel(mapQuery);
			return ResultUtil.sharedInstance().TrueData(lstJournalModels, "获取成功", ReturnCodeUtils.Code.OK.getCode());
		}
		if (type == 3) {
			List<LstDeptSave> lstSaveReads = saveReadService.QueryLstSaveReadForAgent(mapQuery);
			return ResultUtil.sharedInstance().TrueData(lstSaveReads, "获取成功", ReturnCodeUtils.Code.OK.getCode());
		}
		return null;
	}
	

/*-----------------接口内调用方法集合-----------------*/
public static void main(String[] args) {
		String sCode = "8761634";
		Long iCode = Long.parseLong(sCode);
		if (sCode.length() % 2 == 1) {
			double dDigit = Math.floor(Double.valueOf(sCode.length()/2)); 
			double dNum = Math.pow(10, dDigit);
			dNum = iCode/dNum;
			String sLevel = String.valueOf((int)dNum);
			int ilevel = Integer.valueOf(sLevel.substring(sLevel.length()-1));
			System.out.println(ilevel);
		} else {
			//授权码 位数不对
		}
}
}
