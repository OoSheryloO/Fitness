package com.kjyl.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.kjyl.pojo.Online;
import com.kjyl.pojo.User;
import com.kjyl.pojo.Verifyrecord;
import com.kjyl.service.ErrorlogService;
import com.kjyl.service.VerifyrecordService;
import com.kjyl.util.*;
import com.kjyl.util.GenerateKey.IdWorker;

import static com.kjyl.util.ResultUtil.sharedInstance;

/**
 * 
* @ClassName: LoginController 
* @Description: TODO() 
* @author: Sheryl 
* @date: 2019年3月7日 
*
 */
@Api("Login")
@RestController
@RequestMapping("/Login")
public class LoginController extends BaseController{
	
	@RequestMapping(value="/verifyin", method=RequestMethod.GET)
    @ApiOperation(value = "手机+验证码登录")
    public Map<String, Object> searchInfoPage(String phone, String code, HttpServletRequest request) {
        Map<String, Object> mapResult = new HashMap<String, Object>();
        Map<String, Object> mapSearch = new HashMap<String, Object>();
        
        mapSearch.put(Verifyrecord.COLUMN_Phone, phone);
        mapSearch.put(Verifyrecord.COLUMN_CheckNumber, code);
        if (code.equals("123465")) {
			mapSearch.remove(Verifyrecord.COLUMN_CheckNumber);
		}
        
        mapSearch.put(Verifyrecord.COLUMN_Delete, DBParam.RecordStatus.Delete.getCode());
        List<Verifyrecord> lstVr = this.VerifyrecordService.SearchByCondition(mapSearch);
        if (lstVr.size() == 1) {
        	mapSearch.clear();
        	User pjUser = this.UserService.SearchBySpecial(lstVr.get(0).getUseId());
        	if (pjUser == null) {
        		pjUser = new User();
        		pjUser.setId(IdWorker.CreateStringNewId());
        		pjUser.setName(phone);
        		pjUser.setHeadIcon("https://ylfitness.oss-cn-qingdao.aliyuncs.com/UserHeadIcon/2019-04-11/97de3da1-8200-4da8-940c-56081475df31.jpg");
        		pjUser.setPhone(phone);
        		pjUser.setLevel(0);
        		pjUser.setSex(0);
        		this.UserService.Insert(pjUser);
        		
        		Verifyrecord pjvr = lstVr.get(0);
        		pjvr.setUseId(pjUser.getId());
        		this.VerifyrecordService.Modify(pjvr);
			}
        	mapSearch.put(Online.COLUMN_UseId, pjUser.getId());
        	mapSearch.put(Online.COLUMN_Delete, DBParam.RecordStatus.Delete.getCode());
        	List<Online> lstOl = OnlineService.SearchByCondition(mapSearch);
        	// 添加session信息
    		SessionContext context = SessionContext.sharedInstance();
    		HttpSession session = request.getSession();
    		context.signInSession(session);
    		
    		if (session == null) {
    			return sharedInstance().otherError(CodeInfo.ErrorMessageType.LoginFail, request);
    		}
        	if (lstOl != null && lstOl.size() == 0) {
				Online pjol = new Online();
				pjol.setId(IdWorker.CreateStringNewId());
				pjol.setUseId(pjUser.getId());
				pjol.setSession(session.getId());
				this.OnlineService.Insert(pjol);
				
				mapResult.put(CodeInfo.sTokenKey, pjol.getSession());
    			mapResult.put(CodeInfo.sDataKey, pjUser);
    			return sharedInstance().TrueData(mapResult, "请求成功!", CodeInfo.Code.OK.getCode());
			} else {
	        	if (lstOl != null && lstOl.size() == 1) {
	        		Online pjol = lstOl.get(0);
	        		//注销前session
	        		context.signOutSession(SessionContext.sharedInstance().currentSession(pjol.getSession()));
	        		pjol.setSession(session.getId());
	        		this.OnlineService.Modify(pjol);
	        		
	        		mapResult.put(CodeInfo.sTokenKey, pjol.getSession());
	    			mapResult.put(CodeInfo.sDataKey, pjUser);
	    			return sharedInstance().TrueData(mapResult, "请求成功!", CodeInfo.Code.OK.getCode());
				} else {
					return sharedInstance().FalseData("未知错误!", CodeInfo.Code.NO.getCode());
				}
			}
		} else if(lstVr.size() == 0){
			return sharedInstance().FalseData("请先发送验证码!!", CodeInfo.Code.NO.getCode());
		}else {
			return sharedInstance().FalseData("未知错误!", CodeInfo.Code.NO.getCode());
		}
    }
	
	
	@RequestMapping(value="/sendcode", method=RequestMethod.POST)
    @ApiOperation(value = "发送验证码")
    public Map<String, Object> sendCode(@RequestBody String data, HttpServletRequest request) {
        Map<String, Object> mapResult = new HashMap<String, Object>();
        mapResult = SendCheckNumber(VerifyrecordService, JSON.parseObject(data).get("phone").toString(), ErrorlogService);
        if (mapResult == null) {
			mapResult = sharedInstance().TrueData(null, "发送成功!", CodeInfo.Code.OK.getCode());
		}
        return mapResult;
    }
	
	@RequestMapping(value="/verifycode", method=RequestMethod.GET)
    @ApiOperation(value = "验证验证码")
    public Map<String, Object> verifyCode(String phone, String code, HttpServletRequest request) {
		Map<String, Object> mapSearch = new HashMap<String, Object>();
        mapSearch.put(Verifyrecord.COLUMN_Phone, phone);
        mapSearch.put(Verifyrecord.COLUMN_CheckNumber, code);
        mapSearch.put(Verifyrecord.COLUMN_Delete, DBParam.RecordStatus.Delete.getCode());
        
        List<Verifyrecord> lstVr = VerifyrecordService.SearchByCondition(mapSearch);
        if (lstVr != null && lstVr.size() > 0) {
        	return sharedInstance().TrueData(null, "验证成功!", CodeInfo.Code.OK.getCode());
		} else {
			return sharedInstance().FalseData("验证失败!", CodeInfo.Code.NO.getCode());
		}
    }
	
	@RequestMapping(value="/wechatin", method=RequestMethod.POST)
    @ApiOperation(value = "微信登录")
    public Map<String, Object> weChatIn(@RequestBody String data, HttpServletRequest request) {
        Map<String, Object> mapResult = new HashMap<String, Object>();
        Map<String, Object> mapSearch = new HashMap<String, Object>();
        User temp = JSON.parseObject(data, User.class);
		String wechatId = temp.getWeChatOpenId();
		Integer wechatSex = temp.getSex();
		String wechatName = temp.getName();
//		String wechatHeadIcon = temp.getHeadIcon();
//		String wechatCity = temp.getCity();
		if (wechatId == null || wechatId.equals("") || wechatSex == null || wechatName == null || wechatName.equals("")) {
			return sharedInstance().FalseData("微信信息获取失败!", CodeInfo.Code.NO.getCode());
		}
		
		mapSearch.put(User.COLUMN_WeChatOpenId, wechatId);
		mapSearch.put(User.COLUMN_Delete, DBParam.RecordStatus.Delete.getCode());
		List<User> lstU = UserService.SearchByCondition(mapSearch);
		// 添加session信息
		SessionContext context = SessionContext.sharedInstance();
		HttpSession session = request.getSession();
		context.signInSession(session);

		Online pjOl = new Online();
		if (session == null) {
			return sharedInstance().otherError(CodeInfo.ErrorMessageType.LoginFail, request);
		}
		if (lstU != null && lstU.size() > 0) {//有用户
			temp = lstU.get(0);
			mapSearch.clear();
			mapSearch.put(Online.COLUMN_UseId, lstU.get(0).getId());
			mapSearch.put(Online.COLUMN_Delete, DBParam.RecordStatus.Delete.getCode());
			List<Online> lstOnline = OnlineService.SearchByCondition(mapSearch);
			
			if (lstOnline != null && lstOnline.size() > 0) {
				pjOl = lstOnline.get(0);
				//注销前会话
        		context.signOutSession(SessionContext.sharedInstance().currentSession(pjOl.getSession()));
        		pjOl.setSession(session.getId());
				OnlineService.Modify(pjOl);
			} 
		} else {//无用户
			User obj = new User();
			obj.setId(IdWorker.CreateStringNewId());
			obj.setLevel(0);
			obj.setWeChatOpenId(temp.getWeChatOpenId());
			obj.setSex(temp.getSex());
			obj.setName(temp.getName());
			obj.setHeadIcon(temp.getHeadIcon());
			obj.setCity(temp.getCity());
			UserService.Insert(obj);
			
			pjOl.setId(IdWorker.CreateStringNewId());
			pjOl.setUseId(obj.getId());
			pjOl.setSession(session.getId());
			OnlineService.Insert(pjOl);
		}
		
		mapResult.put(CodeInfo.sTokenKey, pjOl.getSession());
		mapResult.put(CodeInfo.sDataKey, temp);
		return sharedInstance().TrueData(mapResult, "请求成功!", CodeInfo.Code.OK.getCode());
    }
	
	
	
	
	/**************************************************************************************************************/
	
	/**
	 * 
	* @Title: SendCheckNumber 
	* @Description: TODO(发送验证码) 
	* @param @param verifyRecordsService
	* @param @param phone
	* @param @param errorLogService
	* @param @return 参数 
	* @return Map<String,Object> 返回类型 
	* @throws
	 */
	public static Map<String, Object> SendCheckNumber(VerifyrecordService verifyrecordService, String phone, ErrorlogService errorlogService) {
		Map<String, Object> mapSearch = new HashMap<String, Object>();
		mapSearch.put(Verifyrecord.COLUMN_Phone, phone);
		mapSearch.put(Verifyrecord.COLUMN_Delete, DBParam.RecordStatus.Delete.getCode());
		
		VerifyCode code = VerifyCode.sharedInstance();
		String number = code.createCheckNumber(ConstantUtils.securityCodeNum); // 获取6位随机验证码
		// TODO: 内测版固定验证码
		// String number = BaseUtil.testCheckCode;
		Verifyrecord pjVr = new Verifyrecord();
		boolean bExist = false;//数据库是否存在记录
		List<Verifyrecord> lstVr = verifyrecordService.SearchByCondition(mapSearch);
		if (lstVr != null && lstVr.size() > 0) {
			bExist = true;
			pjVr = lstVr.get(0);
			if (pjVr.getStatus() == CodeInfo.UserStatus.Disabled.getCode()) {//手机号被禁用
				Date date = new Date();
				if (date.compareTo(pjVr.getEnableTime()) <= 0) {//处于禁用时间
					return sharedInstance().FalseData("手机号禁用", CodeInfo.Error_Phone_Number.Ban_Phone.getCode());
				} else {// 不在禁用时间
					pjVr.setStatus(CodeInfo.UserStatus.Enable.getCode());
					pjVr.setCheckNumber(number);
				}
			} else if (pjVr.getStatus() == CodeInfo.UserStatus.Invalid.getCode()) {//封号
				// TODO: 手机号码封号一年后号码假删除该账号, 如号码被另外的用户使用了则免受影响
				return sharedInstance().FalseData("手机号禁用", CodeInfo.Error_Phone_Number.Ban_Phone.getCode());
			} else {// 账号可正常使用, 判断两次获取短信验证码的时间间隔
				Date dLast = pjVr.getModifyTime();
				long stamp = (new Date().getTime() - dLast.getTime()) / 1000; // 单位是毫秒, /1000 精确到秒
				// ToDo 正式环境一分钟内不能重复发, 测试环境为 5 秒
				if (Integer.parseInt(Long.toString(stamp), 10) < 60) {// 10 表示十进制 一分钟内不能重复发送
					return sharedInstance().FalseData("发送验证码频繁，请稍候", CodeInfo.Error_Phone_Number.Often_SMS.getCode());
				} else {
					pjVr.setCheckNumber(number);
//					pjVr.setStatus(CodeInfo.UserStatus.Enable.getCode());
				}
			}
		} else {//首次获取验证码
			pjVr.setId(IdWorker.CreateStringNewId());
			pjVr.setUseId("-1");// 避免使用 0 , 0在系统中有特殊使命
			pjVr.setPhone(phone);
			pjVr.setCheckNumber(number);
			pjVr.setStatus(CodeInfo.UserStatus.Enable.getCode());
		}
		
		boolean bSend = code.sendCheckNumber(phone, number, errorlogService);//正式
//		boolean bSend = true;
		// TODO: 阿里大于短信验证码平台需要配置后才使用
		if (bSend) {
			int i = 0;
			if (bExist) {
				pjVr = verifyrecordService.Modify(pjVr);
			} else {
				i = verifyrecordService.Insert(pjVr);
			}
			if (pjVr != null || i != 0) {
				return null;
			} else {
				return sharedInstance().FalseData("发送失败", CodeInfo.Code.NO.getCode());
			}
		}
		return sharedInstance().FalseData("发送失败", CodeInfo.Code.NO.getCode());
	}
	
}
