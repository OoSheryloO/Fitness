package com.huban.controller;

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
import com.huban.Utils.ReturnCodeUtils;
import com.huban.construct.RankUserModel;
import com.huban.pojo.User;
import com.huban.util.ResultUtil;


@Controller
@RequestMapping(value="/rankings")
public class RankingController extends BaseController{
	
	/**Top排行榜
	* @author zhangchao
	* @date 2017-6-19 上午10:14:28
	*/
	@RequestMapping(value="/toprankings",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> ranklist(HttpServletRequest request){
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("start",0);
		map.put("size",5);
//		map.put("mytime", "1");
//		List<User> readList = userService.toprankings(map);
		List<User> readList = new ArrayList<User>();
		try {
		List<Long> userId = dateService.ReadBillCountTop();
		for (int i = 0; i < userId.size(); i++) {
			User user = userService.selectUserByUserId(userId.get(i));
			readList.add(user);
		}
		result.put("readList", JSON.toJSON(readList));
		map.put("mytime", "2");
		List<User> leveList = userService.toprankings(map);
		result.put("levelList", JSON.toJSON(leveList));
		result.put("status", true);
		} catch (Exception e) {
			// TODO: handle exception
			result.put("message", "数据异常，请联系客服");
			result.put("status", false);
		}
		return result;
	}
	
	/**排行榜——详细
	 * timeType表示榜单类别timeType(日:2，周:3，总:4)way(1:等级,0:阅读时间)
	* @author zhangchao
	* @date 2017-6-19 上午10:14:28
	*/
	@RequestMapping(value="/rankings",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> rankingslist(@RequestParam String page,String size,String timeType,String way,HttpServletRequest request){
		Map<String, Object> result = new HashMap<String,Object>();
		try {
		if (way == null && "".equals(way) && timeType == null && "".equals(timeType)) {
			result.put("message", "请求异常请重试！");
			result.put("status", false);
		}else if (Integer.parseInt(way) == 0) {//阅读排行
			if ("2" == timeType || "2".equals(timeType)) {
				int pageCount=Integer.parseInt(size);
				Map<String, Object> map=new HashMap<String, Object>();
				map.put("start", (Integer.parseInt(page)-1)*pageCount);
				map.put("size", pageCount);
				map.put("timeType", "2");
				List<User> userList = new ArrayList<User>();
				List<Long> userIds = dateService.ranklist(map);
				List<Integer> counts = dateService.ranklistcount(map);
				for (int i = 0; i < userIds.size(); i++) {
					User user = userService.seletename(userIds.get(i));
					userList.add(user);
				}
				result.put("numList", JSON.toJSON(counts));
				result.put("rankslist", userList);
				result.put("status", true);
				return result;
			}else if ("3" == timeType || "3".equals(timeType)) {
				int pageCount=Integer.parseInt(size);
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("start", (Integer.parseInt(page)-1)*pageCount);
				map.put("size", pageCount);
				map.put("timeType", "3");
				List<User> userList = new ArrayList<User>();
				List<Long> userIds = dateService.ranklist(map);
				List<Integer> counts = dateService.ranklistcount(map);
				for (int i = 0; i < userIds.size(); i++) {
					User user = userService.seletename(userIds.get(i));
					userList.add(user);
				}
				result.put("numList", JSON.toJSON(counts));
				result.put("rankslist", userList);
				result.put("status", true);
				return result;
			}else {
				int pageCount=Integer.parseInt(size);
				Map<String, Object> map=new HashMap<String, Object>();
				map.put("start", (Integer.parseInt(page)-1)*pageCount);
				map.put("size", pageCount);
				map.put("timeType", "4");
				List<User> userList = new ArrayList<User>();
				List<Long> userIds = dateService.ranklist(map);
				List<Integer> counts = dateService.ranklistcount(map);
				for (int i = 0; i < userIds.size(); i++) {
					User user = userService.seletename(userIds.get(i));
					userList.add(user);
				}
				result.put("numList", JSON.toJSON(counts));
				result.put("rankslist", userList);
				result.put("status", true);
				return result;
			}
		}else {//等级排行
			if ("2" == timeType || "2".equals(timeType)) {
				int pageCount=Integer.parseInt(size);
				Map<String, Object> map=new HashMap<String, Object>();
				map.put("start", (Integer.parseInt(page)-1)*pageCount);
				map.put("size", pageCount);
				map.put("way", "1");
				map.put("timeType", "2");
				List<User> userList = userService.ranklist(map);
				List<Integer> numberList = new ArrayList<Integer>();
				for (int i = 0; i < userList.size(); i++) {
					Integer number = dateService.QueryCountById(userList.get(i).getUserId());
					numberList.add(number);
				}
				result.put("numberList", numberList);
				result.put("rankslist", JSON.toJSON(userList));
				result.put("status", true);
				return result;
			}else if ("3" == timeType || "3".equals(timeType)) {
				int pageCount=Integer.parseInt(size);
				Map<String, Object> map=new HashMap<String, Object>();
				map.put("start", (Integer.parseInt(page)-1)*pageCount);
				map.put("size", pageCount);
				map.put("way", "1");
				map.put("timeType", "3");
				List<User> userList = userService.ranklist(map);
				List<Integer> numberList = new ArrayList<Integer>();
				for (int i = 0; i < userList.size(); i++) {
					Integer number = dateService.QueryCountById(userList.get(i).getUserId());
					numberList.add(number);
				}
				result.put("numberList", numberList);
				result.put("rankslist", JSON.toJSON(userList));
				result.put("status", true);
				return result;
			}else {
				int pageCount=Integer.parseInt(size);
				Map<String, Object> map=new HashMap<String, Object>();
				map.put("start", (Integer.parseInt(page)-1)*pageCount);
				map.put("size", pageCount);
				map.put("way", "1");
				map.put("timeType", "4");
				List<User> userList = userService.ranklist(map);
				List<Integer> numberList = new ArrayList<Integer>();
				for (int i = 0; i < userList.size(); i++) {
					Integer number = dateService.QueryCountById(userList.get(i).getUserId());
					numberList.add(number);
				}
				result.put("numberList", numberList);
				result.put("rankslist", JSON.toJSON(userList));
				result.put("status", true);
				return result;
			}
		}
	} catch (Exception e) {
		// TODO: handle exception
		result.put("message", "数据异常，请联系客服");
		result.put("status", false);
	}
		return result;
	}
	
	/*-------------------------------------------------V2-----------------------------------------------*/
	
	/** 排行榜——详细
	 * @author Sheryl
	 * @created 2017年9月27日 上午9:22:50
	 * @created 2017年9月27日 上午9:22:32
	 * @param page
	 * @param size
	 * @param timeType 表示榜单类别(日:2，周:3，总:4)
	 * @param way 1:等级,0:阅读时间
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/Interface/V2/Rankings",method={RequestMethod.GET})
	@ResponseBody
	public Map<String, Object> RankingsMethod(@RequestParam String page, String size, String timeType, HttpServletRequest request){
		Map<String, Object> mapQuery = new HashMap<String,Object>();
		int pageCount=Integer.parseInt(size);
		mapQuery.put("start", (Integer.parseInt(page)-1)*pageCount);
		mapQuery.put("size", pageCount);
		mapQuery.put("timeType", timeType);
		List<RankUserModel> lstRankUserModels = userService.QueryRankList(mapQuery);
		return ResultUtil.sharedInstance().TrueData(lstRankUserModels, "获取成功", ReturnCodeUtils.Code.OK.getCode());
	}
	
	
	/**排行榜——详细 (备份)
	 * timeType表示榜单类别timeType(日:2，周:3，总:4)way(1:等级,0:阅读时间)
	* @author zhangchao
	* @date 2017-6-19 上午10:14:28
	*//*
	@RequestMapping(value="/rankings",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> rankingslist(@RequestParam String page,String size,String timeType,String way,HttpServletRequest request){
		Map<String, Object> result = new HashMap<String,Object>();
		if (way == null && "".equals(way) && timeType == null && "".equals(timeType)) {
			result.put("message", "请求异常请重试！");
			result.put("status", false);
		}else if (Integer.parseInt(way) == 0) {//阅读排行
			if (timeType=="2") {
				int pageCount=Integer.parseInt(size);
				Map<String, Object> map=new HashMap<String, Object>();
				map.put("start", (Integer.parseInt(page)-1)*pageCount);
				map.put("size", pageCount);
				map.put("way", "0");
				map.put("timeType", "2");
				List<User> userList = userService.ranklist(map);
				result.put("rankslist", userList);
				result.put("status", true);
				return result;
			}else if (timeType=="3") {
				int pageCount=Integer.parseInt(size);
				Map<String, Object> map=new HashMap<String, Object>();
				map.put("start", (Integer.parseInt(page)-1)*pageCount);
				map.put("size", pageCount);
				map.put("way", "0");
				map.put("timeType", "3");
				List<User> userList = userService.ranklist(map);
				result.put("rankslist", userList);
				result.put("status", true);
				return result;
			}else {
				int pageCount=Integer.parseInt(size);
				Map<String, Object> map=new HashMap<String, Object>();
				map.put("start", (Integer.parseInt(page)-1)*pageCount);
				map.put("size", pageCount);
				map.put("way", "0");
				map.put("timeType", "4");
				List<User> userList = userService.ranklist(map);
				result.put("rankslist", userList);
				result.put("status", true);
				return result;
			}
		}else {//等级排行
			if (timeType=="2") {
				int pageCount=Integer.parseInt(size);
				Map<String, Object> map=new HashMap<String, Object>();
				map.put("start", (Integer.parseInt(page)-1)*pageCount);
				map.put("size", pageCount);
				map.put("way", "1");
				map.put("timeType", "2");
				List<User> userList = userService.ranklist(map);
				result.put("rankslist", userList);
				result.put("status", true);
				return result;
			}else if (timeType=="3") {
				int pageCount=Integer.parseInt(size);
				Map<String, Object> map=new HashMap<String, Object>();
				map.put("start", (Integer.parseInt(page)-1)*pageCount);
				map.put("size", pageCount);
				map.put("way", "0");
				map.put("timeType", "3");
				List<User> userList = userService.ranklist(map);
				result.put("rankslist", userList);
				result.put("status", true);
				return result;
			}else {
				int pageCount=Integer.parseInt(size);
				Map<String, Object> map=new HashMap<String, Object>();
				map.put("start", (Integer.parseInt(page)-1)*pageCount);
				map.put("size", pageCount);
				map.put("way", "1");
				map.put("timeType", "4");
				List<User> userList = userService.ranklist(map);
				result.put("rankslist", userList);
				result.put("status", true);
				return result;
			}
		}
		return result;*/
	
}
	
	