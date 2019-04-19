package com.kjyl.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.kjyl.pojo.Dict;
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
 * @ClassName: ShiroLoginController 
 * @Description: TODO() 
 * @author: Sheryl 
 * @date: 2019年4月16日 
 *
 */
@Api("ShiroLogin")
@RestController
@RequestMapping("/ShiroLogin")
public class ShiroLoginController extends BaseController{
	
	 @RequestMapping("/login")
	    public String login(
	            @RequestParam(value = "username", required = false) String username,
	            @RequestParam(value = "password", required = false) String password, Model model) {
	        String error = null;
	        if (username != null && password != null) {
	            //初始化
	            Subject subject = SecurityUtils.getSubject();
	            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
	            try {
	                //登录，即身份校验，由通过Spring注入的UserRealm会自动校验输入的用户名和密码在数据库中是否有对应的值
	                subject.login(token);
	                return "redirect:index.do";
	            }catch (Exception e){
	                e.printStackTrace();
	                error = "未知错误，错误信息：" + e.getMessage();
	            }
	        } else {
	            error = "请输入用户名和密码";
	        }
	        //登录失败，跳转到login页面，这里不做登录成功的处理，由
	        model.addAttribute("error", error);
	        return "login";
	    }
	 
	 @RequestMapping(value="/sendcode", method=RequestMethod.POST)
	    @ApiOperation(value = "发送验证码")
	    public Map<String, Object> sendCode(@RequestBody String data, HttpServletRequest request) {
	        Dict pojo = new Dict();
	        pojo.setId("1");
	        pojo = this.DictService.SearchByModel(pojo);
	        return sharedInstance().TrueData(pojo, "请求成功!", CodeInfo.Code.OK.getCode());
	    }
}
