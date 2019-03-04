package com.huban.controller;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
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
import com.huban.Utils.PushBaidu.PushUtil;
import com.huban.construct.LstDeptMemberModel;
import com.huban.construct.LstDeptModel;
import com.huban.construct.LstDeptSave;
import com.huban.construct.UserInfoModel;
import com.huban.pojo.Activity;
import com.huban.pojo.Activitypart;
import com.huban.pojo.Department;
import com.huban.pojo.DepartmentWithBLOBs;
import com.huban.pojo.Integration;
import com.huban.pojo.Order;
import com.huban.pojo.Payrecords;
import com.huban.pojo.Reward;
import com.huban.pojo.SaveRead;
import com.huban.pojo.User;
import com.huban.pojo.UserInfo;
import com.huban.publicway.ConstantWay;
import com.huban.publicway.UserWay;
import com.huban.util.BaseUtil;
import com.huban.util.IdWorker;
import com.huban.util.ResultUtil;
/**
 * <p>Title: DepartmentController.java</p>
 * <p>Description: 部门控制器 </p>
 * <p>Company: </p>
 * @author Sheryl
 * @created 2017年9月19日 下午7:13:31
 */
@Controller
@RequestMapping("/Department")
public class DepartmentController extends BaseController{
	/**创建支行／加入支行
	 * @created 2017年9月19日 下午7:14:57
	 * @param userId 用户ID name 名称 region 地区 picture logo intro 介绍
	 * @return
	 * @throws ParseException 
	 */
	@RequestMapping(value="/Interface/V2/JoinDept",method={RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public Map<String, Object> establishDept(HttpServletRequest request) throws ParseException{
		Map<String, Object> mapReturn = new HashMap<String, Object>();
		Map<String, Object> mapQuery = new HashMap<String, Object>();
		Map<String, Object> mapResult = new HashMap<String, Object>();
		String param;
		param = (String) request.getAttribute(BaseUtil.paramKey);
		if (param == null || "".equals(param)) {
			param = request.getParameter(BaseUtil.paramKey);
		}
		JSONObject jsonObject = JSONObject.parseObject(param);
		String sUserId = jsonObject.getString("userId");
		String sType = jsonObject.getString("type");//0:add 1:join
		
		long lUserId = Long.parseLong(sUserId);
		int iType = Integer.parseInt(sType);
		if (0 == iType) {
			String sName = jsonObject.getString("name");//分行名称
			String sPicture = jsonObject.getString("picture");//logo
			String sIntro = jsonObject.getString("intro");//介绍
			
			mapQuery.put(ArgumentsUtils.sIDKey, lUserId);
			String sUserNumber = userInfoService.QuerySerialNumber(mapQuery);
			String[] arrUserNumber = sUserNumber.split(ConstantUtils.sSeparator);
			//判断用户是否是其他行长，如果是，
			mapResult = userInfoService.QuerySomeMessage(mapQuery);
			if (Integer.parseInt(mapResult.get("position").toString()) == 1) {
				return ResultUtil.sharedInstance().FalseData("请先退出原支行", ReturnCodeUtils.Code.NO.getCode());
			}
			
			mapQuery.clear();
			mapQuery.put(ArgumentsUtils.sArea_NumberLikeKey, "%" + arrUserNumber[0] + ConstantUtils.sSeparator + "%");
			String sDeptNumber = departmentService.QuerySerialNumber(mapQuery);
			
			if (sDeptNumber == null || "".equals(sDeptNumber)) {
				sDeptNumber = arrUserNumber[0] + ConstantUtils.sSeparator + RandomUtil.toFixdLengthString(1, 15);
			}else {
				String[] arrDeptNumber = sDeptNumber.split(ConstantUtils.sSeparator);
				long lNumber = Long.parseLong(arrDeptNumber[1]) + 1;
				sDeptNumber = arrUserNumber[0] + ConstantUtils.sSeparator + String.valueOf(RandomUtil.toFixdLengthString(lNumber, 15));
			}
			
			DepartmentWithBLOBs pjdepartment = new DepartmentWithBLOBs();
			pjdepartment.setDepartmentId(IdWorker.CreateNewID());
//	System.out.println(pjdepartment.getDepartmentId());
			pjdepartment.setDepartmentNumber(sDeptNumber);
			pjdepartment.setDepartmentLogourl(sPicture);
			pjdepartment.setDepartmentName(sName);
			pjdepartment.setDepartmentNotice(sIntro);
			pjdepartment.setDepartmentFounder(lUserId);
			departmentService.AddNewMessage(pjdepartment);
			
			UserInfo pjuseriInfo = new UserInfo();
			pjuseriInfo.setUserinfoUserid(lUserId);
			pjuseriInfo.setUserinfoBelongdept(pjdepartment.getDepartmentId().toString());
			pjuseriInfo.setUserinfoPosition(1);//1:行长 2:副行长 3:课长 4:副课长 5:领读者 6:同读者
			userInfoService.UpdateBindingMessage(pjuseriInfo);
			//判断用户是否是自由人 不是退出分行	
			
			mapReturn = ResultUtil.sharedInstance().TrueData(pjdepartment.getDepartmentId(), "创建成功", ReturnCodeUtils.Code.OK.getCode());
		}
		if (1 == iType) {//加入支行
			String sDeptId = jsonObject.getString("deptId");//支行id
			long lDeptId = Long.parseLong(sDeptId);
			
			mapQuery.put(ArgumentsUtils.sIDKey, lUserId);
			mapResult = userInfoService.QuerySomeMessage(mapQuery);
			if (Integer.parseInt(mapResult.get("position").toString()) == 1) {
				return ResultUtil.sharedInstance().FalseData("你是行长，请先退出原支行", ReturnCodeUtils.Code.NO.getCode());
			}
			
			UserInfo pjUserInfo = new UserInfo();
			pjUserInfo.setUserinfoUserid(lUserId);
			pjUserInfo.setUserinfoBelongdept(sDeptId);
			pjUserInfo.setUserinfoPosition(0);
			userInfoService.UpdateBindingMessage(pjUserInfo);
			mapQuery.clear();
			mapQuery.put(ArgumentsUtils.sDeptIDKey, lDeptId);
			mapQuery.put(ArgumentsUtils.sIdentityKey, 2);
			int iDeptMomberNum = userInfoService.QueryExist(mapQuery);
			mapResult = departmentService.QuerySomeMessage(mapQuery);
			int iStatus = Integer.parseInt(mapResult.get("status").toString());
			if (iDeptMomberNum >= 5 && iStatus < 10) {
//				DepartmentWithBLOBs pjDepartmentWithBLOBs = new DepartmentWithBLOBs();
//				Date ti = new Date();
//				Date time = ConstantUtils.sdfDateTime.parse(mapResult.get("time").toString());
//				GregorianCalendar gc = new GregorianCalendar();
//				gc.setTime(time);
//				gc.add(2, +1);
//				mapQuery.put(ArgumentsUtils.sTimeMonthKey, ConstantUtils.sdfDate.format(time));
//				long lReadNumberMonth = saveReadService.QueryReadNumberByOneMonth(mapQuery);
//				if (lReadNumberMonth >= ConstantUtils.lFirstDetermineForDeptStatus && iStatus < 1 && (ti.getTime() <= gc.getTime().getTime())) {
//					pjDepartmentWithBLOBs.setDepartmentLevel((float) 0.1);
//				}
//				pjDepartmentWithBLOBs.setDepartmentStatus(11);
//				pjDepartmentWithBLOBs.setDepartmentId(lDeptId);
//				departmentService.UpdateMessage(pjDepartmentWithBLOBs);
				
				DepartmentWithBLOBs pjDepartmentWithBLOBs = new DepartmentWithBLOBs();
				if ((new BigDecimal(mapResult.get("DeptRead").toString())).compareTo(new BigDecimal(ConstantUtils.lFirstDetermineForDeptStatus)) > -1 && iStatus < 1) {
					pjDepartmentWithBLOBs.setDepartmentLevel((float) 0.1);
					pjDepartmentWithBLOBs.setDepartmentStatus(11);//奖励成立
				}else {
					pjDepartmentWithBLOBs.setDepartmentLevel((float) 0.0);
					pjDepartmentWithBLOBs.setDepartmentStatus(10);//普通成立
				}
				pjDepartmentWithBLOBs.setDepartmentId(lDeptId);
				departmentService.UpdateMessage(pjDepartmentWithBLOBs);
				
			}
			mapReturn = ResultUtil.sharedInstance().TrueData(null, "加入成功", ReturnCodeUtils.Code.OK.getCode());
		}
		if (2 == iType) {//退出支行  Department表的modifytime 要用来判断正式成立的时间，不要轻易修改
			mapQuery.put(ArgumentsUtils.sIDKey, lUserId);
			mapQuery.put(ArgumentsUtils.sRandomKey, RandomUtil.generateString(6));
			mapResult = userInfoService.QuerySomeMessage(mapQuery);
			String sDeptId = mapResult.get("deptId").toString();
			long lDeptId = Long.parseLong(mapResult.get("deptId").toString());
			int iPosition = Integer.parseInt(mapResult.get("position").toString());
			mapQuery.clear();
			mapQuery.put(ArgumentsUtils.sDeptIDKey, lDeptId);
			mapQuery.put(ArgumentsUtils.sIdentityKey, 2);
			mapQuery.put(ArgumentsUtils.sRandomKey, RandomUtil.generateString(6));
			int iDeptMomberNum = userInfoService.QueryExist(mapQuery);
			
			UserInfo pjUserInfo = new UserInfo();
			pjUserInfo.setUserinfoUserid(lUserId);
			pjUserInfo.setUserinfoBelongdept("0");
			pjUserInfo.setUserinfoPosition(0);
			userInfoService.UpdateBindingMessage(pjUserInfo);
			
			//教师不算支行成员，所以解散支行的时候需要把 支行老师放生
			if (iDeptMomberNum <= 1) {//如果退出之前支行人数<=1 证明最多只有一个人了，之前退出了，所以现在支行的人数应该是<=0 ，所以此处等于1进行判断
				mapQuery.clear();
				mapQuery.put(ArgumentsUtils.sDeptIDKey, lDeptId);
				mapQuery.put(ArgumentsUtils.sIdentityKey, 1);
				mapQuery.put(ArgumentsUtils.sRandomKey, RandomUtil.generateString(6));
				if (userInfoService.QueryExist(mapQuery) >= 1) {
					mapResult = userInfoService.QuerySomeMessage(mapQuery);
					
					String sDeptIds = mapResult.get("deptId").toString();
					sDeptIds = ConstantWay.JointString(sDeptIds, ConstantUtils.sSeparator,sDeptId);
					
					mapQuery.clear();
					mapQuery.put(ArgumentsUtils.sDeptIDKey, lDeptId);
					mapQuery.put(ArgumentsUtils.sPositionKey, 7);//以支行角色为参考
//					mapQuery.put(ArgumentsUtils.sIdentityKey, 1);//以用户身份为参考  二选一即可
					mapQuery.put(ArgumentsUtils.sRandomKey, RandomUtil.generateString(6));
					List<LstDeptMemberModel> lstDeptMemberModels = userInfoService.QueryLstDeptMember(mapQuery);
					for (int i = 0; i < lstDeptMemberModels.size(); i++) {
						pjUserInfo = new UserInfo();
						pjUserInfo.setUserinfoUserid(lstDeptMemberModels.get(i).getUserId());
						if (sDeptIds == null || sDeptIds == "" || "".equals(sDeptIds)) {
							pjUserInfo.setUserinfoBelongdept("0");
							pjUserInfo.setUserinfoPosition(0);//还原初始设置
						}else {
							pjUserInfo.setUserinfoBelongdept(sDeptIds);
						}
						userInfoService.UpdateBindingMessage(pjUserInfo);//修改老师id对于的分行id消息
						//通知老师支行解散
						JSONObject Childjsobj=new JSONObject();
		                Childjsobj.put("Status", "DisbandDepartment");
		                Childjsobj.put("Type", "AppView");
		                Childjsobj.put("Url", Constant.getProjectBaseUrl(request));
		                PushUtil.sendPushInfo(ConstantUtils.sReadBank_Department_Disband, "支行:"+mapResult.get("DeptName").toString()+"学生人数少于规定人数(0人)，被迫解散", Childjsobj, lUserId, lstDeptMemberModels.get(i).getUserId(), 1, true, null, null, pushDrivesService, devicesService, pushRecordsService);
					}
				}
				
				DepartmentWithBLOBs pjDepartmentWithBLOBs = new DepartmentWithBLOBs();
				pjDepartmentWithBLOBs.setDepartmentDelete(1);
				pjDepartmentWithBLOBs.setDepartmentId(lDeptId);
				departmentService.UpdateMessage(pjDepartmentWithBLOBs);
				
			}else if (1 == iPosition) {//顺位继承//或者正常退出
					long lOtherUserId = userInfoService.QueryInheritSequence(lDeptId);
					pjUserInfo = new UserInfo();
					pjUserInfo.setUserinfoUserid(lOtherUserId);
					pjUserInfo.setUserinfoPosition(1);
					userInfoService.UpdateBindingMessage(pjUserInfo);
					
					JSONObject Childjsobj=new JSONObject();
	                Childjsobj.put("Status", "ChangeGovernor");
	                Childjsobj.put("Type", "AppView");
	                Childjsobj.put("Url", Constant.getProjectBaseUrl(request));
	                PushUtil.sendPushInfo(ConstantUtils.sReadBank_Department_President, "由于前行长退位，您已成为"+mapResult.get("DeptName").toString()+"支行的行长", Childjsobj, lUserId, lOtherUserId, 1, true, null, null, pushDrivesService, devicesService, pushRecordsService);
			}
			mapReturn = ResultUtil.sharedInstance().TrueData(null, "退出成功", ReturnCodeUtils.Code.OK.getCode());
		}
		if (3 == iType) {//教师版加入支行 自动成为阅读导师
			String sDeptId = jsonObject.getString("deptId");//介绍
			
			if (sDeptId == null || "".equals(sDeptId)) {
				return ResultUtil.sharedInstance().FalseData("参数错误，请联系客服", ReturnCodeUtils.Missing_Parameter.Dept.getCode());
			}
			
			mapQuery.put(ArgumentsUtils.sDeptIDKey, sDeptId);
			mapQuery.put(ArgumentsUtils.sIdentityKey, 1);
			if (userInfoService.QueryExist(mapQuery) >= ConstantUtils.iDepartmentTeacherLimit) {
				return ResultUtil.sharedInstance().FalseData("该支行指导老师已达上限，不能指导该支行", ReturnCodeUtils.Missing_Parameter.Dept.getCode());
			}
			mapQuery.clear();
			mapQuery.put(ArgumentsUtils.sIDKey, lUserId);
			mapQuery.put(ArgumentsUtils.sRandomKey, RandomUtil.generateString(6));
			mapResult = userInfoService.QuerySomeMessage(mapQuery);
			String sDeptIds = mapResult.get("deptId").toString();
			String[] arrDeptId = sDeptIds.split(ConstantUtils.sSeparator);
			if (arrDeptId.length >= ConstantUtils.iTeacherTeachLimit) {
				return ResultUtil.sharedInstance().FalseData("指导的支行已达上限，不能指导该支行", ReturnCodeUtils.Code.NO.getCode());
			}else if ("0".equals(sDeptIds)) {
				sDeptIds = sDeptId;
			}else {
				sDeptIds += (ConstantUtils.sSeparator + sDeptId);
			}
			UserInfo pjUserInfo = new UserInfo();
			pjUserInfo.setUserinfoUserid(Long.parseLong(sUserId));
			pjUserInfo.setUserinfoBelongdept(sDeptIds);
			pjUserInfo.setUserinfoPosition(7);
			userInfoService.UpdateBindingMessage(pjUserInfo);
			
			mapReturn = ResultUtil.sharedInstance().TrueData(null, "加入成功", ReturnCodeUtils.Code.OK.getCode());
		}
		if (4 == iType) {//教师退出支行
			String sDeptId = jsonObject.getString("deptId");
			
			mapQuery.put(ArgumentsUtils.sIDKey, Long.parseLong(sUserId));
			mapQuery.put(ArgumentsUtils.sRandomKey, RandomUtil.generateString(6));
			mapResult = userInfoService.QuerySomeMessage(mapQuery);
			String sDeptIds = mapResult.get("deptId").toString();
			sDeptIds = ConstantWay.JointString(sDeptIds, ConstantUtils.sSeparator,sDeptId);
			
			UserInfo pjUserInfo = new UserInfo();
			pjUserInfo.setUserinfoUserid(Long.parseLong(sUserId));
			if (sDeptIds == null || sDeptIds == "" || "".equals(sDeptIds)) {
				pjUserInfo.setUserinfoBelongdept("0");
			}else {
				pjUserInfo.setUserinfoBelongdept(sDeptIds);
			}
			userInfoService.UpdateBindingMessage(pjUserInfo);
			
			return ResultUtil.sharedInstance().TrueData(null, "退出成功", ReturnCodeUtils.Code.OK.getCode());
		}
		return mapReturn;
	}
	
	/**检索用户绑定地区的支行
	 * @created 2017年9月29日 下午4:13:48
	 * @param userId
	 * @param page
	 * @param size
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/Interface/V2/SearchDept", method = { RequestMethod.GET })
	public @ResponseBody Map<String, Object> SearchDeptMethod(@RequestParam long userId, int page, int size, HttpServletRequest request) {
		Map<String, Object> mapQuery = new HashMap<String, Object>();
		mapQuery.put(ArgumentsUtils.sIDKey, userId);
		String sUserNumber = userInfoService.QuerySerialNumber(mapQuery);
		String[] arrUserNumber = sUserNumber.split(ConstantUtils.sSeparator);
		
		mapQuery.clear();
		mapQuery.put(ArgumentsUtils.sStartKey, size * (page - 1));
		mapQuery.put(ArgumentsUtils.sSizeKey, size);
		mapQuery.put(ArgumentsUtils.sArea_NumberLikeKey, "%" + arrUserNumber[0] + ConstantUtils.sSeparator + "%");
//		String sDeptNumber = departmentService.QuerySerialNumber(mapQuery);
		List<LstDeptModel> lstDeptModels = departmentService.QueryDeptList(mapQuery);
		return ResultUtil.sharedInstance().TrueData(lstDeptModels, "获取成功", ReturnCodeUtils.Code.OK.getCode());
	}
	
	
	/**成员列表
	 * @created 2017年9月30日 下午12:52:04
	 * @param userId
	 * @param page
	 * @param size
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/Interface/V2/MemberList", method = { RequestMethod.GET })
	public @ResponseBody Map<String, Object> MemberListMethod(@RequestParam long deptId, HttpServletRequest request) {
		Map<String, Object> mapQuery = new HashMap<String, Object>();
//		mapQuery.put(ArgumentsUtils.sStartKey, size * (page - 1));
//		mapQuery.put(ArgumentsUtils.sSizeKey, size);
		mapQuery.put(ArgumentsUtils.sDeptIDKey, deptId);
		List<LstDeptMemberModel> lstDeptMemberModels = userInfoService.QueryLstDeptMember(mapQuery);
		return ResultUtil.sharedInstance().TrueData(lstDeptMemberModels, "获取成功", ReturnCodeUtils.Code.OK.getCode());
	}
	
	
	/**存储明细
	 * @created 2017年9月30日 下午12:52:27
	 * @param userId
	 * @param page
	 * @param size
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/Interface/V2/SaveReadDetail", method = { RequestMethod.GET })
	public @ResponseBody Map<String, Object> SaveReadDetailMethod(@RequestParam int page, int size, long deptId, HttpServletRequest request) {
		Map<String, Object> mapQuery = new HashMap<String, Object>();
		mapQuery.put(ArgumentsUtils.sStartKey, size * (page - 1));
		mapQuery.put(ArgumentsUtils.sSizeKey, size);
		mapQuery.put(ArgumentsUtils.sDeptIDKey, deptId);
		List<LstDeptSave> lstsSaveReads = saveReadService.QueryLstSaveReadDetail(mapQuery);
		return ResultUtil.sharedInstance().TrueData(lstsSaveReads, "获取成功", ReturnCodeUtils.Code.OK.getCode());
	}
	
	/**修改某人职位
	 * @created 2017年10月9日 上午10:40:26
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/Interface/V2/ChancePosition", method = { RequestMethod.POST })
	public @ResponseBody Map<String, Object> dismissPositionMethod(HttpServletRequest request) {
		Map<String, Object> mapQuery = new HashMap<String, Object>();
		Map<String, Object> mapResult = new HashMap<String, Object>();
		String param;
		param = (String) request.getAttribute(BaseUtil.paramKey);
		if (param == null || "".equals(param)) {
			param = request.getParameter(BaseUtil.paramKey);
		}
		JSONObject jsonObject = JSONObject.parseObject(param);
		String sUserId = jsonObject.getString(ArgumentsUtils.sUseIDKey);
		String sOtherUserId = jsonObject.getString(ArgumentsUtils.sOtherUserIdKey);
		String sIdentity = jsonObject.getString(ArgumentsUtils.sIdentityKey);
		long lUserId = Long.parseLong(sUserId);
		long lOtherUserId = Long.parseLong(sOtherUserId);
		int iIdentity = Integer.parseInt(sIdentity);
		
		mapQuery.put(ArgumentsUtils.sIDKey, lUserId);
		mapResult = userInfoService.QuerySomeMessage(mapQuery);
		int iPosition = Integer.parseInt(mapResult.get("position").toString());
		String sDeptId = mapResult.get("deptId").toString();
		if (iPosition != 1) {
			return ResultUtil.sharedInstance().FalseData("您不是行长，权限不足", ReturnCodeUtils.Error_User_Status.Identity.getCode());
		}
		if (Integer.parseInt(sIdentity) == 7) {
			return ResultUtil.sharedInstance().FalseData("支行指导老师只能通过邀请，请勿重试", ReturnCodeUtils.Error_User_Status.Identity.getCode());
		}
		UserInfo pjUserInfo = new UserInfo();
		boolean flag = false;
		if (iIdentity == -1) {//-1:踢人  分辨支行踢出老师的处理
			mapQuery.clear();
			mapQuery.put(ArgumentsUtils.sIDKey, lOtherUserId);
			mapResult = userInfoService.QuerySomeMessage(mapQuery);
			String sDeptIds = "0";int iPositin = 0;
			if (Integer.parseInt(mapResult.get("position").toString()) == 1) {
				iPositin = 7;
				sDeptIds = mapResult.get("deptId").toString();
				sDeptIds = ConstantWay.JointString(sDeptIds, ConstantUtils.sSeparator,sDeptId);
				if (sDeptIds == null || sDeptIds == "" || "".equals(sDeptIds)) {
					sDeptIds = "0";
					iPositin = 0;
				}
			}
			
			pjUserInfo.setUserinfoUserid(lOtherUserId);
			pjUserInfo.setUserinfoBelongdept(sDeptIds);
			pjUserInfo.setUserinfoPosition(iPositin);
		}else {//改变职位
			pjUserInfo.setUserinfoUserid(lOtherUserId);
			pjUserInfo.setUserinfoPosition(iIdentity);
			flag = true;
		}
		userInfoService.UpdateBindingMessage(pjUserInfo);
		
		JSONObject Childjsobj=new JSONObject(); String message = ""; String title = "";
		if (flag) {
            Childjsobj.put("Status", "StudentChangePositon");//学生被改变职位
            message = "您加入的行长改变了您的职位，请注意";title = ConstantUtils.sReadBank_Department_Identity;
		}else {
			Childjsobj.put("Status", "OutDeptPositon");//被踢
			message = "您已被行长踢出"+"支行";title = ConstantUtils.sReadBank_Department_Retreats;
		}
		Childjsobj.put("Type", "AppView");
        Childjsobj.put("Url", Constant.getProjectBaseUrl(request));
		PushUtil.sendPushInfo(title, message, Childjsobj, Long.parseLong(sUserId), lOtherUserId, 1, true, null, null, pushDrivesService,devicesService,pushRecordsService);
		return ResultUtil.sharedInstance().TrueData(null, "操作成功", ReturnCodeUtils.Code.OK.getCode());
	}
	
	/**
	 * 邀请老师
	 */
	@RequestMapping(value = "/Interface/V2/InviteTeacher", method = { RequestMethod.POST })
	public @ResponseBody Map<String, Object> InviteTeacherMethod(HttpServletRequest request) {
		Map<String, Object> mapQuery = new HashMap<String, Object>();
		Map<String, Object> mapResult = new HashMap<String, Object>();
		String param;
		param = (String) request.getAttribute(BaseUtil.paramKey);
		if (param == null || param.equals("")) {
			param = request.getParameter(BaseUtil.paramKey);
		}
		JSONObject jsonObject = JSON.parseObject(param);
		String sUserId = jsonObject.getString("userId");
		String sTeacherId = jsonObject.getString("teacherId");
		
		long lUserId = Long.parseLong(sUserId);
		long lTeacherId = Long.parseLong(sTeacherId);
		
		mapQuery.put(ArgumentsUtils.sRandomKey, RandomUtil.generateString(6));
		
		mapQuery.put(ArgumentsUtils.sIDKey, lUserId);
		mapResult = userInfoService.QuerySomeMessage(mapQuery);
		if (Integer.parseInt(mapResult.get("position").toString()) != 1) {
			return ResultUtil.sharedInstance().FalseData("你不是支行行长，不能邀请老师", ReturnCodeUtils.Code.NO.getCode());
		}
		
		String sDeptId = mapResult.get("deptId").toString();
		mapQuery.put(ArgumentsUtils.sIDKey, lTeacherId);
		mapResult = userInfoService.QuerySomeMessage(mapQuery);
		String sDeptIds = mapResult.get("deptId").toString();
		String[] arrDeptId = sDeptIds.split(ConstantUtils.sSeparator);
		for (int i = 0; i < arrDeptId.length; i++) {
			if (arrDeptId[i].equals(sDeptId)) {
				return ResultUtil.sharedInstance().FalseData("该老师已加入本支行", ReturnCodeUtils.Code.NO.getCode());
			}
		}
		mapQuery.clear();
		mapQuery.put(ArgumentsUtils.sDeptIDKey, sDeptId);
		mapQuery.put(ArgumentsUtils.sIdentityKey, 1);
		
		if (userInfoService.QueryExist(mapQuery) >= ConstantUtils.iDepartmentTeacherLimit) {
			return ResultUtil.sharedInstance().FalseData("支行只能有一位指导老师", ReturnCodeUtils.Code.NO.getCode());
		}
		if (arrDeptId.length >= ConstantUtils.iTeacherTeachLimit) {
			return ResultUtil.sharedInstance().FalseData("该老师指导的支行已达上限，不能指导本支行", ReturnCodeUtils.Code.NO.getCode());
		}else if ("0".equals(sDeptIds)) {
			sDeptIds = sDeptId;
		}else {
			sDeptIds += (ConstantUtils.sSeparator + sDeptId);
		}
		
		UserInfo pjUserInfo = new UserInfo();
		pjUserInfo.setUserinfoUserid(lTeacherId);
		pjUserInfo.setUserinfoBelongdept(sDeptIds);
		pjUserInfo.setUserinfoPosition(7);
		userInfoService.UpdateBindingMessage(pjUserInfo);
		return ResultUtil.sharedInstance().TrueData(null, "邀请成功", ReturnCodeUtils.Code.OK.getCode());
	}
	
	/**
	 * 老师列表 
	 */
	@RequestMapping(value = "/Interface/V2/TeachersList", method = { RequestMethod.GET })
	public @ResponseBody Map<String, Object> TeachersListMethod(@RequestParam String userId, int page, int size, HttpServletRequest request) {
		Map<String, Object> mapQuery = new HashMap<String, Object>();
		Map<String, Object> mapResult = new HashMap<String, Object>();
		mapQuery.put(ArgumentsUtils.sIDKey, Long.parseLong(userId));
		mapResult = userInfoService.QuerySomeMessage(mapQuery);
		String sSchool = mapResult.get("School").toString();
		String sDeptId = mapResult.get("deptId").toString();
		mapQuery.clear();
		mapQuery.put(ArgumentsUtils.sDeptIDKey, Long.parseLong(sDeptId));
		mapQuery.put(ArgumentsUtils.sStartKey, (page-1) * size);
		mapQuery.put(ArgumentsUtils.sSizeKey, size);
		mapQuery.put(ArgumentsUtils.sStatusKey, (int) 1);
		mapQuery.put(ArgumentsUtils.sSchoolKey, sSchool);
		mapQuery.put(ArgumentsUtils.sRandomKey, RandomUtil.generateString(6));
		List<UserInfoModel> lstUserInfoModels = userService.QueryTeacherInfo(mapQuery);
		return ResultUtil.sharedInstance().TrueData(lstUserInfoModels, "获取成功", ReturnCodeUtils.Code.OK.getCode());
	}
	
}
