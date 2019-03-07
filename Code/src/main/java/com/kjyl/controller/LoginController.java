package com.kjyl.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;


/**
 * 
* @ClassName: LoginController 
* @Description: TODO() 
* @author: Sheryl 
* @date: 2019年3月7日 
*
 */
@Controller
@RequestMapping("/Login")
public class LoginController extends BaseController{
 
	@RequestMapping("/login")
	public ModelAndView loginMethod(){	
		return new ModelAndView("login");
	}
	@RequestMapping("/index")
	public ModelAndView indexMethod(HttpServletResponse response, HttpServletRequest request, HttpSession session, Model model){
		return new ModelAndView("index");
	}
	@RequestMapping("/userinfo")
	public ModelAndView userinfoMethod(HttpServletResponse response, HttpServletRequest request, HttpSession session, Model model){
		return new ModelAndView("users/pc");
	}
	@RequestMapping("/rank")
	public ModelAndView rankMethod(HttpServletResponse response, HttpServletRequest request, HttpSession session, Model model){
		return new ModelAndView("users/pc_rank");
	}
	@RequestMapping(value="/ranklist",method={RequestMethod.GET,RequestMethod.POST})
	public 
	@ResponseBody
	Map<String,Object> ranklistMethod(@RequestParam String flag,HttpServletRequest request){
		Map<String,Object> result=new HashMap<String, Object>();
	    Map<String, Object> map=new HashMap<String, Object>();
	    map.put("mytime",flag);
	    List<User> lists=userService.queryAll(map);
	    result.put("lists", JSON.toJSON(lists));
	    result.put("status", true);
		return result;
	}	
	@RequestMapping(value="/readlist",method={RequestMethod.GET,RequestMethod.POST})
	public 
	@ResponseBody
	Map<String,Object> readlistMethod(@RequestParam String flag,HttpServletRequest request){
		Map<String,Object> result=new HashMap<String, Object>();
	    Map<String, Object> map=new HashMap<String, Object>();
	    map.put("mytime",flag);
	    List<User> lists=userService.queryRead(map);
	    result.put("lists", JSON.toJSON(lists));
	    result.put("status", true);
		return result;
	}	
}
