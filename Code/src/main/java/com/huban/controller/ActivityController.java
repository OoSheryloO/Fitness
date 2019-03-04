/**
 * 
 */
package com.huban.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
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
import com.huban.Utils.ArgumentsUtils;
import com.huban.Utils.ConstantUtils;
import com.huban.Utils.RandomUtil;
import com.huban.Utils.ReturnCodeUtils;
import com.huban.construct.LstDeptModel;
import com.huban.pojo.Activity;
import com.huban.util.ResultUtil;
/**
 * @ClassName: ActivityController
 * @Description: TODO()
 * @author Sheryl
 * @date 2017年11月21日 下午3:07:10
 */
@Controller
@RequestMapping("/activity")
public class ActivityController extends BaseController{
     
	/**
	 * 活动列表type(0:精选, 1:赛事, 2:特效, 3:免费)+address(地区)
	 * @param request
	 * @throws UnsupportedEncodingException 
	 * */
	@RequestMapping(value="/activitylist",method={RequestMethod.GET,RequestMethod.POST})
	public
	@ResponseBody
	Map<String, Object> activitylistMethod(@RequestParam String page,String size,String type,String address,HttpServletRequest request) throws UnsupportedEncodingException{
		Map<String, Object> result=new HashMap<String, Object>();
		int pageCount=Integer.parseInt(size);
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("start", (Integer.parseInt(page)-1)*pageCount);
		map.put("size", pageCount);
		map.put(ArgumentsUtils.sTypeKey, type);
		//String addr = new String(address.getBytes("ISO8859-1"),"UTF-8");
		String[] arrAddress = address.split(ConstantUtils.sSeparator);
		map.put(ArgumentsUtils.sAddressKey, arrAddress[0]);
		List<Activity> list = activityService.queryList(map);
		result.put("actList",JSON.toJSON(list));
		result.put("status", true);
		return result;
	}
	
	//活动详情
	@RequestMapping(value="/activitydetail",method={RequestMethod.GET,RequestMethod.POST})
	public 
	@ResponseBody
	Map<String, Object> activitydetailmethod(@RequestParam String activityId,String userId,HttpServletRequest request){
		Map<String, Object> result=new HashMap<String, Object>();
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("tfSign", ConstantUtils.TFsign);
		map.put("activityId", Long.parseLong(activityId));
		Activity activity = null;
		try {
		activityService.updateNum(map);
		map.clear();
		map.put(ArgumentsUtils.sScreenStringKey, ConstantUtils.payActRefund);
		map.put(ArgumentsUtils.sDBIDKey, userId);
		map.put(ArgumentsUtils.sIDKey, Long.parseLong(activityId));
		activity = activityService.selectActivity(map);
		map.clear();
		if (userId == null || "".equals(userId)) {
			result.put("join", false);
			result.put("message", "游客");
		}else{
			map.put("userId", Long.parseLong(userId));
			map.put("activityId", Long.parseLong(activityId));
			int num = activitypartService.queryById(map);
			if (num == 0) {
				result.put("join", false);
			}else {
				result.put("join", true);
			}
		}
		result.put("detail", JSON.toJSON(activity));
		result.put("status", true);
		return result;
		} catch (Exception e) {
			// TODO: handle exception
			result.put("status", false);
			result.put("message", "数据异常，请联系客服");
			return result;
		}
	}
	
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
	
	/**
	 * @Title: SearchDeptMethod
	 * @Description: TODO( 活动分类 )
	 * @param userId
	 * @param page
	 * @param size
	 * @param type =0 :活动首页数据 =1总行活动 =2本地分行活动 =3外地分行活动
	 * @param request
	 * @return  参数
	 * @return Map<String,Object>  返回类型
	 * @throws
	 */
	@RequestMapping(value = "/Interface/V2/Classify", method = { RequestMethod.GET })
	public @ResponseBody Map<String, Object> ActivityClassifyMethod(@RequestParam long userId,@RequestParam(required=false) Integer page,@RequestParam(required=false) Integer size, Integer type, HttpServletRequest request) {
		Map<String, Object> mapQuery = new HashMap<String, Object>();
		Map<String, Object> mapResult = new HashMap<String, Object>();
		mapQuery.put(ArgumentsUtils.sIDKey, userId);
		mapQuery.put(ArgumentsUtils.sRandomKey, RandomUtil.generateString(6));
	
		boolean flag = false;String[] arrString = null;
		if (page == null || size == null) {
			page = 0;size = 1;
		}
		if (1 !=  type) {
			mapResult = userInfoService.QuerySomeMessage(mapQuery);
//			String sAddress = mapResult.get("Address").toString();/*.replaceAll(" +","")*///去空格
			String sNumber = mapResult.get("Number").toString();
			arrString = sNumber.split(ConstantUtils.sSeparator);
//			mapQuery.put(ArgumentsUtils.sAddressKey, sAddress);
			flag = true;
		}
		mapQuery.clear();
		if (flag) {
			if (1 != type) {
				mapQuery.put(ArgumentsUtils.sArea_CodeKey, arrString[0]);
			}else {
				mapQuery.put(ArgumentsUtils.sArea_CodeKey, ArgumentsUtils.sHead_QuerterKey);
			}
		}
		mapQuery.put(ArgumentsUtils.sStartKey, size * (page - 1));
		mapQuery.put(ArgumentsUtils.sSizeKey, size);
		mapQuery.put(ArgumentsUtils.sConditionKey, type);
		mapQuery.put(ArgumentsUtils.sScreenStringKey, ConstantUtils.payActRefund);
		mapQuery.put(ArgumentsUtils.sDBIDKey, userId);
		if ( 0 == type ) {
			mapQuery.put(ArgumentsUtils.sStartKey, 0);
			mapQuery.put(ArgumentsUtils.sSizeKey, 1);
		}
		
		List<Activity> lstActivity = activityService.queryList(mapQuery);
		return ResultUtil.sharedInstance().TrueData(lstActivity, "获取成功", ReturnCodeUtils.Code.OK.getCode());
	}
	
}
