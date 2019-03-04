/**
 * 
 */
package com.huban.controller;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.huban.Utils.ReturnCodeUtils;
import com.huban.pojo.Evaluation;
import com.huban.Utils.Constant;
import com.huban.util.ResultUtil;

/**
 * @author GeJiangbo
 * @date 2017年5月23日
 */
@Controller
@RequestMapping("/evaluation")
public class EvaluationController extends BaseController{

	@RequestMapping(value="/evaluationlist",method={RequestMethod.GET,RequestMethod.POST})
	public
	@ResponseBody
	Map<String, Object> activitylistMethod(@RequestParam String page,@RequestParam(required=false)String parent,HttpServletRequest request) throws UnsupportedEncodingException{
		Map<String, Object> result=new HashMap<String, Object>();
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("start", (Integer.parseInt(page)-1)*10);
		map.put("size", 10);
		if (parent!=null) {
			map.put("parent", parent);
		}else {
			map.put("parentIsNull", 1);
		}
		List<Evaluation> list = EvaluationService.getEvaluationList(map);
		if (list.size()>0) {
			for (int i = 0; i < list.size(); i++) {
				list.get(i).setUrl(Constant.getProjectBaseUrl(request)+list.get(i).getUrl());
			}
			result=ResultUtil.sharedInstance().TrueData(list, "获取数据成功", ReturnCodeUtils.Code.OK.getCode());
		}else {
			result=ResultUtil.sharedInstance().FalseData("获取数据为空", ReturnCodeUtils.Code.NO.getCode());
		}
		return result;
	}
	
}
