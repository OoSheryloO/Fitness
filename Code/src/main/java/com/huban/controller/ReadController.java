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
import com.huban.Utils.ConstantUtils;
import com.huban.Utils.RandomUtil;
import com.huban.Utils.ReturnCodeUtils;
import com.huban.construct.TimeDate;
import com.huban.pojo.Department;
import com.huban.pojo.DepartmentWithBLOBs;
import com.huban.pojo.Integration;
import com.huban.pojo.SaveRead;
import com.huban.pojo.User;
import com.huban.pojo.UserInfo;
import com.huban.util.BaseUtil;
import com.huban.util.IdWorker;
import com.huban.util.ResultUtil;

/**
 * <p>Title: ReadController.java</p>
 * <p>Description: 控制器</p>
 * <p>Company: huban </p>
 * @author Sheryl
 * @created 2017年9月22日 下午5:43:42
 */
@Controller
@RequestMapping("/Read")
public class ReadController extends BaseController {
	
	
	/**
	 * 增加阅读储蓄 way 1:电子 2:纸质 type 0 支票 3 存折 4:古诗文类 5:成/谚/俗语 6:对联/笑话/歇后语 7:好词/佳句/其他
	 * 说明，只支持对于新增和草稿的修改保存，不支持对于已保存的修改，会造成阅读数目变化
	 * @throws ParseException  
	 */
	@RequestMapping(value = "/Interface/V2/AddReadSave", method = { RequestMethod.POST })
	public @ResponseBody Map<String, Object> AddReadSaveMethod(HttpServletRequest request) throws ParseException {
		Map<String, Object> mapReturn = new HashMap<String, Object>();
		Map<String, Object> mapQuery = new HashMap<String, Object>();
		Map<String, Object> mapResult = new HashMap<String, Object>();
		String param;
		param = (String) request.getAttribute(BaseUtil.paramKey);
		if (param == null || param.equals("")) {
			param = request.getParameter(BaseUtil.paramKey);
		}
		JSONObject jsonObject = JSON.parseObject(param);
		User pjUser = JSON.parseObject(jsonObject.getString(User.sUserClass), User.class);
		SaveRead pjSaveRead = JSON.parseObject(jsonObject.getString(SaveRead.sSaveReadClass),SaveRead.class);
		pjSaveRead.setRsBelongid(pjUser.getUserId());
		
		boolean flag = false; double number = 0.0; String message = "保存成功";
		if (pjSaveRead.getRsId() == null) {//新增
			pjSaveRead.setRsId(IdWorker.CreateNewID());
			if (pjSaveRead.getRsType() < 3) {// =1/=2
				pjSaveRead.setRsType(0);
				saveReadService.AddNewMessage(pjSaveRead);
			}else {
				mapResult = new HashMap<String, Object>();
				mapQuery.put(ArgumentsUtils.sIDKey, pjUser.getUserId());
				mapQuery.put(ArgumentsUtils.sRandomKey, RandomUtil.generateString(6));
				mapResult = userInfoService.QuerySomeMessage(mapQuery);
				String sDeptId = mapResult.get("deptId").toString();
				
				if (pjSaveRead.getRsMold() == 2) {//保存->计算字数 变成完成状态 
					if (pjSaveRead.getRsType() > 3) {
						number = getSplitNumber(pjSaveRead.getRsTitle(), number, null);
						number = getSplitNumber(pjSaveRead.getRsAnswerQuiz(), number, null);
						number = getSplitNumber(pjSaveRead.getRsReason(), number, null);
						number = getSplitNumber(pjSaveRead.getRsDiyIssue(), number, null);
						pjSaveRead.setRsNumber(new BigDecimal(number).divide(new BigDecimal(10000)).setScale(4));	
					}else {
						number = pjSaveRead.getRsNumber().doubleValue() * 10000;
					}
					saveReadService.AddNewMessage(pjSaveRead);
					flag = true;
				}else {//执行草稿表的新增
					saveReadManuscriptService.AddNewManuscriptMessage(pjSaveRead);
				}
				
				if (sDeptId != null && !"".equals(sDeptId) && !"0".equals(sDeptId) && sDeptId != "0") {
					pjSaveRead.setRsBelongdept(Long.parseLong(sDeptId));
					
					mapQuery.clear();
					mapQuery.put(ArgumentsUtils.sDeptIDKey, sDeptId);
					mapQuery.put(ArgumentsUtils.sIdentityKey, 2);//只对userinfoService有用
					int iDeptMomberNum = userInfoService.QueryExist(mapQuery);
					mapResult = departmentService.QuerySomeMessage(mapQuery);
					//简单判定 2018-04-10 14:12 BUG 未知
					if(mapResult.get("status").toString() == null || mapResult.get("status").toString().isEmpty()) {
						mapResult.put("status", "10");
					}
					int iStatus = Integer.parseInt(mapResult.get("status").toString());
					DepartmentWithBLOBs pjDepartmentWithBLOBs = new DepartmentWithBLOBs();
					pjDepartmentWithBLOBs.setDepartmentId(Long.parseLong(sDeptId));
					pjDepartmentWithBLOBs.setDepartmentAmount(pjSaveRead.getRsNumber());
					if (iStatus < 10) {//未成立判定
						if (iDeptMomberNum >= 5) {
							if ((new BigDecimal(mapResult.get("DeptRead").toString())).compareTo(new BigDecimal(ConstantUtils.lFirstDetermineForDeptStatus)) > -1 && iStatus < 1) {
								pjDepartmentWithBLOBs.setDepartmentLevel((float) 0.1);
								pjDepartmentWithBLOBs.setDepartmentStatus(11);//奖励成立
							}else {
								pjDepartmentWithBLOBs.setDepartmentLevel((float) 0.0);
								pjDepartmentWithBLOBs.setDepartmentStatus(10);//普通成立
							}
							departmentService.UpdateMessage(pjDepartmentWithBLOBs);
						}
					} else {//例行增加等级
						long lLimitLevel = 0; boolean FlagStatus = false;
						if (10 == iStatus) {
							lLimitLevel = (new BigDecimal(mapResult.get("DeptRead").toString()).longValue())/ConstantUtils.lDeptLevelLimit;
							pjDepartmentWithBLOBs.setDepartmentLevel(Float.valueOf(Integer.toOctalString((int)(lLimitLevel)))/10);
						}
						if (11 == iStatus) {FlagStatus = true;
							lLimitLevel = (new BigDecimal(mapResult.get("DeptRead").toString()).longValue() - 100)/ConstantUtils.lDeptLevelLimit;
							pjDepartmentWithBLOBs.setDepartmentLevel(Float.valueOf(Integer.toOctalString((int)(lLimitLevel + 1)))/10);
						}
						if (FlagStatus) {
							if (lLimitLevel >= ConstantUtils.iTopLevel_Status11_Limit) {
								pjDepartmentWithBLOBs.setDepartmentLevel((float)7.7);
							}
						}else {
							if (lLimitLevel >= ConstantUtils.iTopLevel_Status10_Limit) {
								pjDepartmentWithBLOBs.setDepartmentLevel((float)7.7);
							}
						}
						departmentService.UpdateMessage(pjDepartmentWithBLOBs);
					}
				}else {
					pjSaveRead.setRsBelongdept(0);
				}
			}
			mapReturn.put("rsId", pjSaveRead.getRsId());
		}else {//更新/修改
			if (pjSaveRead.getRsMold() == 2) {// 保存->计算字数
				number = pjSaveRead.getRsAnswerQuiz().length() + pjSaveRead.getRsReason().length();
				if (pjSaveRead.getRsType() < 3) {
					number = getSplitNumber(pjSaveRead.getRsTitle(), number, null);
					number = getSplitNumber(pjSaveRead.getRsAnswerQuiz(), number, null);
					number = getSplitNumber(pjSaveRead.getRsReason(), number, null);
					number = getSplitNumber(pjSaveRead.getRsDiyIssue(), number, null);
					pjSaveRead.setRsNumber(new BigDecimal(number).divide(new BigDecimal(10000)).setScale(4));
				} else {
					number = pjSaveRead.getRsNumber().doubleValue() * 10000;
				}
				saveReadService.AddNewMessage(pjSaveRead);
				pjSaveRead.setRsDelete(1);
//				flag = true;
			} /*else {// 执行草稿表的更新 和 记录的更新
			}*/
			saveReadManuscriptService.UpdateManuscriptMessageByRsId(pjSaveRead);
		}
		if (flag) {
			BigDecimal IntertionNumber = new BigDecimal(number).divide(new BigDecimal(ConstantUtils.WordsNumber)).multiply(new BigDecimal(ConstantUtils.score));
			
			UserInfo pjUserInfo = new UserInfo();
			pjUserInfo.setUserinfoReadcount(new BigDecimal(number).divide(new BigDecimal(10000)));
			pjUserInfo.setUserinfoUserid(pjUser.getUserId());
			userInfoService.UpdateBindingMessage(pjUserInfo);
			
			mapQuery.clear();
			mapQuery.put("userId", pjUser.getUserId());
			mapQuery.put("userIntegral", IntertionNumber);
			mapQuery.put(ArgumentsUtils.sMoneyKey, IntertionNumber);
			userService.addIntegral(mapQuery);
			
			Integration integration = new Integration();
			integration.setIntegrationId(IdWorker.CreateNewID());
			integration.setIntegrationStatus(0);
			integration.setIntegrationNum(IntertionNumber);
			integration.setIntegrationStudentid(pjUser.getUserId());
			integration.setIntegrationType(pjSaveRead.getRsType() + 10 - 1);//12:存储单 3 存折 4:古诗文类 5:成/谚/俗语 6:对联/笑话/歇后语 7:好词/佳句/其他 对应 12 13 14 15 16
			integration.setIntegrationSrcid((long) 1);//0:支出 1:收入
			integration.setIntegrationReason(ConstantUtils.ReadSaveSingleIntegration);
			integrationService.addmessage(integration);
			
			message = "存储增加成功";
		}
		return ResultUtil.sharedInstance().TrueData(mapReturn, message, ReturnCodeUtils.Code.OK.getCode());
	}
	
	
	/**
	 * 回答问题 
	 * @throws ParseException 
	 */
	@RequestMapping(value = "/Interface/V2/Questions", method = {RequestMethod.POST})
	public @ResponseBody Map<String, Object> AnswerQuestionMethod(HttpServletRequest request) throws ParseException {
		Map<String, Object> mapQuery = new HashMap<String, Object>();
		Map<String, Object> mapResult = new HashMap<String, Object>();
		String param;
		param = (String) request.getAttribute(BaseUtil.paramKey);
		if (param == null || param.equals("")) {
			param = request.getParameter(BaseUtil.paramKey);
		}
		JSONObject jsonObject = JSON.parseObject(param);
		String sUserId = jsonObject.getString("userId");
		String sSaveReadId = jsonObject.getString("saveReadId");
		String sMessage = jsonObject.getString("message");
		String sType = jsonObject.getString("type");//1:回答问题 2:url
		String sSetMessage = jsonObject.getString("setMessage");
		String sGrade = jsonObject.getString("grade");
		
		long lUserId = Long.parseLong(sUserId);
		long lSaceReadId = Long.parseLong(sSaveReadId);
		int lType = Integer.parseInt(sType);
		SaveRead pjSaveRead = new SaveRead();
		pjSaveRead.setRsAnswerQuiz(sMessage);
		pjSaveRead.setRsDiyIssue(sSetMessage);
		pjSaveRead.setRsGrade(sGrade);
		pjSaveRead.setRsId(lSaceReadId);
		pjSaveRead.setRsType(lType);
		
		BigDecimal bigDecimal = null;
		
		Map<String, Object> mapSavaReadResult = new HashMap<String, Object>();
		mapSavaReadResult = saveReadService.QuerySaveReadNumber(lSaceReadId);
		pjSaveRead.setRsBelongdept(Long.parseLong(mapSavaReadResult.get("rsBelongdept").toString()));//相当于set0 
		mapQuery.put(ArgumentsUtils.sIDKey, lUserId);
		mapQuery.put(ArgumentsUtils.sRandomKey, RandomUtil.generateString(6));
		mapResult = userInfoService.QuerySomeMessage(mapQuery);
		String sDeptId = mapResult.get("deptId").toString();
		if (sDeptId != null || !"".equals(sDeptId)) {
			pjSaveRead.setRsBelongdept(Long.parseLong(sDeptId));
		}
		saveReadService.UpdateMessageByRsId(pjSaveRead);
		
		long lDeptId = Long.parseLong(sDeptId);
		if (lDeptId != 0) {
			mapQuery.clear();
			mapQuery.put(ArgumentsUtils.sDeptIDKey, lDeptId);
			mapResult = departmentService.QuerySomeMessage(mapQuery);
			int iStatus = Integer.parseInt(mapResult.get("status").toString());
			int iDeptMomberNum = userInfoService.QueryExist(mapQuery);
			
			Date time = ConstantUtils.sdfDateTime.parse(mapResult.get("time").toString());
//	System.out.println(ConstantUtils.sdfDate.format(gc.getTime()));
			
			mapQuery.put(ArgumentsUtils.sTimeMonthKey, ConstantUtils.sdfDate.format(time));
// 	System.out.println(ConstantUtils.sdfDate.format(time));
			long lReadNumberMonth = saveReadService.QueryReadNumberByOneMonth(mapQuery);
			DepartmentWithBLOBs pjDepartmentWithBLOBs = new DepartmentWithBLOBs();
			if (iDeptMomberNum >= 5 && iStatus != 11 && lReadNumberMonth >= ConstantUtils.lFirstDetermineForDeptStatus) {
				if (iStatus < 1 ) {
					pjDepartmentWithBLOBs.setDepartmentLevel((float) 0.1);
				}
				pjDepartmentWithBLOBs.setDepartmentStatus(11);
				pjDepartmentWithBLOBs.setDepartmentId(lDeptId);
				departmentService.UpdateMessage(pjDepartmentWithBLOBs);
			}
			if (iStatus == 11) {
				time = ConstantUtils.sdfDateTime.parse(mapResult.get("modifytime").toString());
				GregorianCalendar gc = new GregorianCalendar();
				gc.setTime(time);
				gc.add(2, +1);
				mapQuery.clear();
				mapQuery.put(ArgumentsUtils.sDeptIDKey, lDeptId);
				mapQuery.put(ArgumentsUtils.sDateKey, ConstantUtils.sdfDate.format(gc.getTime()));
				lReadNumberMonth = saveReadService.QueryReadNumberByOneMonth(mapQuery);
				long lNum = lReadNumberMonth / ConstantUtils.lDeptLevelLimit;
				int iLevel = (int) (Float.parseFloat(mapResult.get("level").toString()) * 10);
				pjDepartmentWithBLOBs = new DepartmentWithBLOBs();
				pjDepartmentWithBLOBs.setDepartmentLevel(Float.valueOf(Integer.toOctalString((int)(lNum + iLevel)))/10);
				pjDepartmentWithBLOBs.setDepartmentId(lDeptId);
				departmentService.UpdateMessage(pjDepartmentWithBLOBs);
			}
		}
		
		Integration integration = new Integration();
		integration.setIntegrationId(IdWorker.CreateNewID());
		integration.setIntegrationStatus(0);
		integration.setIntegrationNum(bigDecimal);
		integration.setIntegrationStudentid(lUserId);
		
		if (lType == 1) {//书目字数×0.9，每100字积3分  (int) (iReadNumber * 10000 * 0.9 / 100 * 3);//新版存储1000字：1积分
//			bigDecimal = (new BigDecimal(Long.parseLong(mapSavaReadResult.get("rsNumber").toString()) * ConstantUtils.ReadWordsRatio * 10000).setScale(3).divide(new BigDecimal(ConstantUtils.ReadWordsNumber).setScale(3))).multiply(new BigDecimal(ConstantUtils.ReadScore).setScale(3));
			bigDecimal = new BigDecimal(Long.parseLong(mapSavaReadResult.get("rsNumber").toString()) * 10000).setScale(4).divide(new BigDecimal(ConstantUtils.WordsNumber)).multiply(new BigDecimal(ConstantUtils.score));
			integration.setIntegrationType(10);//读后感奖励积分
			integration.setIntegrationReason(ConstantUtils.ReadWordsIntegration);
		}
		if (lType == 2) {//书目字数×0.85，每1000字积3分  (int) (iReadNumber * 10000 * 0.85 / 1000 * 3);
//			bigDecimal = (new BigDecimal(Long.parseLong(mapSavaReadResult.get("rsNumber").toString()) * ConstantUtils.MindMapWordsRatio * 10000).setScale(3).divide(new BigDecimal(ConstantUtils.MindMapWordsNumber).setScale(3))).multiply(new BigDecimal(ConstantUtils.MindMapScore).setScale(3));
			bigDecimal = new BigDecimal(Long.parseLong(mapSavaReadResult.get("rsNumber").toString()) * 10000).setScale(4).divide(new BigDecimal(ConstantUtils.WordsNumber)).multiply(new BigDecimal(ConstantUtils.score));
			integration.setIntegrationType(11);//思维导图奖励积分
			integration.setIntegrationReason(ConstantUtils.MindMapIntegration);
		}
		integration.setIntegrationSrcid((long) 1);//0:支出 1:收入
		mapQuery.clear();
		mapQuery.put("userId", lUserId);
		mapQuery.put("userIntegral", bigDecimal);
		mapQuery.put(ArgumentsUtils.sMoneyKey, bigDecimal);
		userService.addIntegral(mapQuery);
		
		UserInfo pjUserInfo = new UserInfo();
		pjUserInfo.setUserinfoReadcount(new BigDecimal(mapSavaReadResult.get("rsNumber").toString()).setScale(4));
		pjUserInfo.setUserinfoUserid(lUserId);
		userInfoService.UpdateBindingMessage(pjUserInfo);
		
		integrationService.addmessage(integration);
		return ResultUtil.sharedInstance().TrueData(null, "发表成功", ReturnCodeUtils.Code.OK.getCode());
	}
	
	
	/**
	 * 关于问题 
	 * @throws ParseException type 1:回答问题 2:url
	 */
	@RequestMapping(value = "/Interface/V2/AboutQuestion", method = {RequestMethod.POST})
	public @ResponseBody Map<String, Object> AboutQuestionsMethod(HttpServletRequest request) throws ParseException {
		Map<String, Object> mapQuery = new HashMap<String, Object>();
		Map<String, Object> mapResult = new HashMap<String, Object>();
		String param;
		param = (String) request.getAttribute(BaseUtil.paramKey);
		if (param == null || param.equals("")) {
			param = request.getParameter(BaseUtil.paramKey);
		}
		JSONObject jsonObject = JSON.parseObject(param);
		User pjUser = JSON.parseObject(jsonObject.getString(User.sUserClass), User.class);
		SaveRead pjSaveRead = JSON.parseObject(jsonObject.getString(SaveRead.sSaveReadClass),SaveRead.class);
		
		BigDecimal bigDecimal = null; /*double number = 0.0;*/ boolean flag = false; String message = "保存成功"; String arr[];
		Map<String, Object> mapSavaReadResult = new HashMap<String, Object>();
		mapSavaReadResult = saveReadService.QuerySaveReadNumber(pjSaveRead.getRsId());
		pjSaveRead.setRsBelongdept(Long.parseLong(mapSavaReadResult.get("rsBelongdept").toString()));//把set 0 包含进去了？
		mapQuery.put(ArgumentsUtils.sIDKey, pjUser.getUserId());
		mapQuery.put(ArgumentsUtils.sRandomKey, RandomUtil.generateString(6));
		mapResult = userInfoService.QuerySomeMessage(mapQuery);
		String sDeptId = mapResult.get("deptId").toString();
		if (sDeptId != null || !"".equals(sDeptId)) {
			pjSaveRead.setRsBelongdept(Long.parseLong(sDeptId));
		}
//		number = getSplitNumber(pjSaveRead.getRsAnswerQuiz(), number, null);
//		number = getSplitNumber(pjSaveRead.getRsReason(), number, null);
//		number = getSplitNumber(pjSaveRead.getRsDiyIssue(), number, null);
//		pjSaveRead.setRsNumber(new BigDecimal(number).divide(new BigDecimal(10000)).setScale(4));
		if (pjSaveRead.getRsMold() == 2) {// 保存->计算字数
			flag = true;
		} 
		
		if (flag) {
		long lDeptId = Long.parseLong(sDeptId);
		if (lDeptId != 0) {
			pjSaveRead.setRsBelongdept(Long.parseLong(sDeptId));
			
			mapQuery.clear();
			mapQuery.put(ArgumentsUtils.sDeptIDKey, sDeptId);
			mapQuery.put(ArgumentsUtils.sIdentityKey, 2);//只对userinfoService有用
			int iDeptMomberNum = userInfoService.QueryExist(mapQuery);
			mapResult = departmentService.QuerySomeMessage(mapQuery);
			int iStatus = Integer.parseInt(mapResult.get("status").toString());
			DepartmentWithBLOBs pjDepartmentWithBLOBs = new DepartmentWithBLOBs();
			pjDepartmentWithBLOBs.setDepartmentId(Long.parseLong(sDeptId));
			pjDepartmentWithBLOBs.setDepartmentAmount(pjSaveRead.getRsNumber());
			if (iStatus < 10) {//未成立判定
				if (iDeptMomberNum >= 5) {
					if ((new BigDecimal(mapResult.get("DeptRead").toString())).compareTo(new BigDecimal(ConstantUtils.lFirstDetermineForDeptStatus)) > -1 && iStatus < 1) {
						pjDepartmentWithBLOBs.setDepartmentLevel((float) 0.1);
						pjDepartmentWithBLOBs.setDepartmentStatus(11);//奖励成立
					}else {
						pjDepartmentWithBLOBs.setDepartmentLevel((float) 0.0);
						pjDepartmentWithBLOBs.setDepartmentStatus(10);//普通成立
					}
					departmentService.UpdateMessage(pjDepartmentWithBLOBs);
				}
			} else {//例行增加等级
				long lLimitLevel = 0; boolean FlagStatus = false;
				if (10 == iStatus) {
					lLimitLevel = (new BigDecimal(mapResult.get("DeptRead").toString()).longValue())/ConstantUtils.lDeptLevelLimit;
					pjDepartmentWithBLOBs.setDepartmentLevel(Float.valueOf(Integer.toOctalString((int)(lLimitLevel)))/10);
				}
				if (11 == iStatus) {FlagStatus = true;
					lLimitLevel = (new BigDecimal(mapResult.get("DeptRead").toString()).longValue() - 100)/ConstantUtils.lDeptLevelLimit;
					pjDepartmentWithBLOBs.setDepartmentLevel(Float.valueOf(Integer.toOctalString((int)(lLimitLevel + 1)))/10);
				}
				if (FlagStatus) {
					if (lLimitLevel >= ConstantUtils.iTopLevel_Status11_Limit) {
						pjDepartmentWithBLOBs.setDepartmentLevel((float) 7.7);
					}
				}else {
					if (lLimitLevel >= ConstantUtils.iTopLevel_Status10_Limit) {
						pjDepartmentWithBLOBs.setDepartmentLevel((float) 7.7);
					}
				}
				departmentService.UpdateMessage(pjDepartmentWithBLOBs);
			}
		}
		
		Integration integration = new Integration();
		integration.setIntegrationId(IdWorker.CreateNewID());
		integration.setIntegrationStatus(0);
		integration.setIntegrationStudentid(pjUser.getUserId());
		
		if (pjSaveRead.getRsType() == 1) {//书目字数×0.9，每100字积3分  (int) (iReadNumber * 10000 * 0.9 / 100 * 3);//新版存储1000字：1积分
//			bigDecimal = (new BigDecimal(Long.parseLong(mapSavaReadResult.get("rsNumber").toString()) * ConstantUtils.ReadWordsRatio * 10000).setScale(3).divide(new BigDecimal(ConstantUtils.ReadWordsNumber).setScale(3))).multiply(new BigDecimal(ConstantUtils.ReadScore).setScale(3));
			bigDecimal = new BigDecimal(mapSavaReadResult.get("rsNumber").toString()).setScale(4).multiply(new BigDecimal(10000)).divide(new BigDecimal(ConstantUtils.WordsNumber)).multiply(new BigDecimal(ConstantUtils.score));
			integration.setIntegrationType(10);//读后感奖励积分
			integration.setIntegrationReason(ConstantUtils.ReadWordsIntegration);
		}
		if (pjSaveRead.getRsType() == 2) {//书目字数×0.85，每1000字积3分  (int) (iReadNumber * 10000 * 0.85 / 1000 * 3);
//			bigDecimal = (new BigDecimal(Long.parseLong(mapSavaReadResult.get("rsNumber").toString()) * ConstantUtils.MindMapWordsRatio * 10000).setScale(3).divide(new BigDecimal(ConstantUtils.MindMapWordsNumber).setScale(3))).multiply(new BigDecimal(ConstantUtils.MindMapScore).setScale(3));
			bigDecimal = new BigDecimal(mapSavaReadResult.get("rsNumber").toString()).setScale(4).multiply(new BigDecimal(10000)).divide(new BigDecimal(ConstantUtils.WordsNumber)).multiply(new BigDecimal(ConstantUtils.score));
			integration.setIntegrationType(11);//思维导图奖励积分
			integration.setIntegrationReason(ConstantUtils.MindMapIntegration);
		}
		integration.setIntegrationSrcid((long) 1);//0:支出 1:收入
		mapQuery.clear();
		mapQuery.put("userId", pjUser.getUserId());
		mapQuery.put("userIntegral", bigDecimal);
		mapQuery.put(ArgumentsUtils.sMoneyKey, bigDecimal);
		userService.addIntegral(mapQuery);
		
		UserInfo pjUserInfo = new UserInfo();
		pjUserInfo.setUserinfoReadcount(new BigDecimal(mapSavaReadResult.get("rsNumber").toString()).setScale(4));
		pjUserInfo.setUserinfoUserid(pjUser.getUserId());
		userInfoService.UpdateBindingMessage(pjUserInfo);
		
		integration.setIntegrationNum(bigDecimal);
		integrationService.addmessage(integration);
		message = "存储成功";
		}
		saveReadService.UpdateMessageByRsId(pjSaveRead);
		return ResultUtil.sharedInstance().TrueData(null, message, ReturnCodeUtils.Code.OK.getCode());
	}
	
	/**
	 * 草稿检测+附带功能:详情 
	 */
	@RequestMapping(value = "/Interface/V2/ReadSaveMessage", method = { RequestMethod.GET })
	public @ResponseBody Map<String, Object> ReadSaveMessageMethod(@RequestParam(required=false) Long userId, @RequestParam(required=false) Long saveReadId, @RequestParam(required=false) Integer type, HttpServletRequest request) {
		Map<String, Object> mapQuery = new HashMap<String, Object>();
		List<SaveRead> lstSaveReads = new ArrayList<SaveRead>();
		mapQuery.put(ArgumentsUtils.sRandomKey, RandomUtil.generateString(6));
		if (null != saveReadId) {
			mapQuery.put(ArgumentsUtils.sDBIDKey, saveReadId);
			lstSaveReads = saveReadService.QueryLackMessage(mapQuery);
		}else {
			mapQuery.put(ArgumentsUtils.STypeKey, type);
			mapQuery.put(ArgumentsUtils.sIDKey, userId);
			lstSaveReads = saveReadManuscriptService.QueryLackMessage(mapQuery);
		}
		return ResultUtil.sharedInstance().TrueData(lstSaveReads, "获取成功", ReturnCodeUtils.Code.OK.getCode());
	}
	
	/**
	 * 个人存储记录 即存储账单
	 */
	@RequestMapping(value = "/Interface/V2/ReadSaveRecord", method = { RequestMethod.GET })
	public @ResponseBody Map<String, Object> ReadSaveRecordMethod(@RequestParam(required=false) long userId, @RequestParam(required=false) String page, @RequestParam(required=false) String size, @RequestParam(required=false) Integer type, @RequestParam(required=false) String date, HttpServletRequest request) {
		Map<String, Object> mapQuery = new HashMap<String, Object>();
		if (type <= 0) {
		int pageCount=Integer.parseInt(size);
		mapQuery.put("start", (Integer.parseInt(page)-1)*pageCount);
		mapQuery.put("size", pageCount);
		}
		List<SaveRead> lstSaveReads = new ArrayList<SaveRead>();
		
		if (type == -1) {
			if (date != null) {//查询某天的记录 type=-1 date=时间
				mapQuery.put(ArgumentsUtils.sRandomKey, RandomUtil.generateString(6));
				mapQuery.put(ArgumentsUtils.sIDKey, userId);
				mapQuery.put(ArgumentsUtils.sDayTimeKey, date);
			}else {//查询总的记录 type=-1 参数无date
				mapQuery.put(ArgumentsUtils.sRandomKey, RandomUtil.generateString(6));
				mapQuery.put(ArgumentsUtils.sIDKey, userId);
			}
			lstSaveReads = saveReadService.QuerySaveReadRecord(mapQuery);
		} if (type == 0) {//查询一周的记录 type=0 参数无date
			mapQuery.put(ArgumentsUtils.sRandomKey, RandomUtil.generateString(6));
			mapQuery.put(ArgumentsUtils.sIDKey, userId);
			mapQuery.put(ArgumentsUtils.sWeekTimeKey, 1);
			lstSaveReads = saveReadService.QuerySaveReadRecord(mapQuery);
		}else if (type > 0) {//查询分月份
			Date time = new Date();//当前时间
			Date dBefore = new Date();
			Calendar calendar = Calendar.getInstance(); //得到日历
			
			List<TimeDate> timeDates = new ArrayList<TimeDate>();
			for (int i = 0; i <= type+1; i++) {
				calendar.setTime(time);//把当前时间赋给日历
				calendar.add(calendar.MONTH, -i);  //设置为前几月
				dBefore = calendar.getTime();   //得到前几月的时间
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //设置时间格式
				String oldtime = sdf.format(dBefore);    //格式化前几月的时间
				mapQuery.put(ArgumentsUtils.sRandomKey, RandomUtil.generateString(6));
				mapQuery.put(ArgumentsUtils.sIDKey, userId);
				mapQuery.put(ArgumentsUtils.sMonthlysTimeKey, oldtime);
				long num= saveReadService.QueryReadNumberByOneMonth(mapQuery);
				TimeDate timeDate = new TimeDate();
				timeDate.setCount((int) num);
				timeDate.setTime(dBefore.getMonth()+1);
				timeDates.add(timeDate);
			}
			return ResultUtil.sharedInstance().TrueData(timeDates, "获取成功", ReturnCodeUtils.Code.OK.getCode());
//			lstSaveReads = saveReadService.QuerySaveReadRecord(mapQuery);
		}
		return ResultUtil.sharedInstance().TrueData(lstSaveReads, "获取成功", ReturnCodeUtils.Code.OK.getCode());
	}
	
	/**(无用)
	 * 阅读账单分月份
	 */
	@RequestMapping(value="/Interface/V2/MonthlyBills",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> MonthlyBillsMethod(@RequestParam(required=false) String userId, @RequestParam(required=false) String type,HttpServletRequest request){
		Map<String, Object> mapResult = new HashMap<String,Object>();
		Map<String, Object> map = new HashMap<String,Object>();
		
		Date time = new Date();//当前时间
		Date dBefore = new Date();
		Calendar calendar = Calendar.getInstance(); //得到日历
		
		List<TimeDate> timeDates = new ArrayList<TimeDate>();
		for (int i = 0; i <= Integer.parseInt(type)+1; i++) {
			calendar.setTime(time);//把当前时间赋给日历
			calendar.add(calendar.MONTH, -i);  //设置为前几月
			dBefore = calendar.getTime();   //得到前几月的时间
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //设置时间格式
			String oldtime = sdf.format(dBefore);    //格式化前几月的时间
			map.put("time", oldtime);
			map.put("userId", Long.parseLong(userId));
			int num = dateService.readbillcount(map);
			TimeDate timeDate = new TimeDate();
			timeDate.setCount(num);
			timeDate.setTime(dBefore.getMonth()+1);
			timeDates.add(timeDate);
		}
		mapResult.put("date", JSON.toJSON(timeDates));		
		mapResult.put("message", "获取成功");
		mapResult.put("status", true);
		return mapResult;
	}
	
	/**
	 * 获取拼接字符串的文字长度
	 */
	public static double getSplitNumber(String sSplitString, double number, String arr[]) {
		if (sSplitString != null) {
			arr = sSplitString.split(ConstantUtils.sSeparator);
			for (int i = 0; i < arr.length; i++) {
				number += arr[i].length();
			}
		}
		return number;
	}
}
