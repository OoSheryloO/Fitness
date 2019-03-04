package cn.net.sheryl.Demo;

import java.util.Random;

//package com.core.controller;
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONException;
//import com.alibaba.fastjson.JSONObject;
//import com.core.model.Admin;
//import com.core.model.OnLine;
//import com.core.model.User;
//import com.core.service.OnLineService;
//import com.core.service.UserService;
//import com.util.BaseUtil;
//import com.util.HostUtil;
//import com.util.SessionContext;
//import org.apache.commons.io.IOUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.servlet.HandlerInterceptor;
//import org.springframework.web.servlet.ModelAndView;
//
//import javax.servlet.ServletInputStream;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.PrintWriter;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import net.sf.ehcache.CacheManager;
//
//import static org.jgroups.util.Util.readLine;
//
///**
// * Created by MeetLucky on 16/5/21.
// */
//public class AuthenticationController implements HandlerInterceptor {
//    @Autowired
//    private OnLineService onLineService;
//    @Autowired
//    private UserService userService;
//
//    private List<String> passList;  //  静态资源放行单
//    private List<String> whiteList; //  动态资源白名单
//    private List<String> backWhiteList; //  h后台动态资源白名单
//
//    public List<String> getPassList() {
//        return passList;
//    }
//
//    public void setPassList(List<String> passList) {
//        this.passList = passList;
//    }
//
//    public List<String> getWhiteList() {
//        return whiteList;
//    }
//
//    public void setWhiteList(List<String> whiteList) {
//        this.whiteList = whiteList;
//    }
//
//    public List<String> getBackWhiteList() {
//        return backWhiteList;
//    }
//
//    public void setBackWhiteList(List<String> backWhiteList) {
//        this.backWhiteList = backWhiteList;
//    }
//
//    //  预处理
////    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {
//        String uri = request.getRequestURI().replace(request.getContextPath(), "");
//        HttpSession session=request.getSession();
//        Map<String, String> map = HostUtil.getHeadersInfo(request);
//        if(uri.contains("PaymentNotify")){
//            return true;
//        }
//        if(backWhiteList.contains(uri)){
//            if(session!=null && ((Admin)session.getAttribute("AdminInfo"))==null) {
//                request.getRequestDispatcher("/Index/login").forward(request, response);
//                return false;
//            }else{
//                return true;
//            }
//        }
//        String param ="";
//        try {
//            param = HostUtil.getPostData(request);
//        }catch (Exception e){}
//        if(param==null || param.equals("")) {
//            param = request.getParameter(BaseUtil.paramKey);
//        }
//        request.setAttribute(BaseUtil.paramKey,param);
//        if (passList.contains(uri) || whiteList.contains(uri)) {
//            return true;
//        } else {
//            String token = map.get(BaseUtil.tokenKey);
//            if(token==null || token.equals("")) {
//                token = request.getParameter(BaseUtil.tokenKey);
//            }
//            if (token == null || token.equals("")) {
//                if(uri.contains("V2")){
//                    request.getRequestDispatcher("/Error/Interface/ParameterV2").forward(request, response);
//                }else {
//                    request.getRequestDispatcher("/Error/Interface/Parameter").forward(request, response);
//                }
//                return false;
//            } else {
//                if (param == null || param.equals("")) {
//                    if(uri.contains("V2")){
//                        request.getRequestDispatcher("/Error/Interface/ParameterV2").forward(request, response);
//                    }else {
//                        request.getRequestDispatcher("/Error/Interface/Parameter").forward(request, response);
//                    }
//                    return false;
//                } else {
//                    request.setAttribute(BaseUtil.tokenKey,token);
//                    try {
//                        JSONObject jsonObject = JSON.parseObject(param);
//                        if (jsonObject == null) {
//                            if(uri.contains("V2")){
//                                request.getRequestDispatcher("/Error/Interface/UnknownV2").forward(request, response);
//                            }else {
//                                request.getRequestDispatcher("/Error/Interface/Unknown").forward(request, response);
//                            }
//
//                            return false;
//                        } else {
//                            User user=null;
//                            String Session="";
//                            if(session!=null && session.getAttribute("UserID")!=null){
//                                Session=(String)session.getAttribute("UserID");
//                                if(!Session.equals(token)){
//                                    Session="";
//                                }
//                            }
//                            if (Session.equals("")) {
//                                //  内存中不存在,去数据库查询用户的在线状态
//                                Map<String, Object> onLineQuery = new HashMap<String, Object>();
//                                onLineQuery.put(OnLine.attributeOnLineSession, token);
//                                onLineQuery.put(OnLine.attributeOnLineStatus, BaseUtil.SessionStatus.OnLine.getCode());
//                                List<OnLine> onLineList = onLineService.queryOnLine(onLineQuery);
//
//                                Boolean isSessionAuthPass=true;//默认表示Session通过
//                                try{
//                                    String userString = jsonObject.getString(User.userClass);
//                                    if(userString!=null && !userString.equals("")){
//                                        user = JSON.parseObject(userString, User.class);
//                                    }
//                                }catch (Exception e){
//                                    e.printStackTrace();
//                                }
//                                if(onLineList.size()>0){
//                                    //如果Session数据库存在,且url里面带上了user值,经比较他们的userId不同,则提示用户无法验证通过
//                                    if(user!=null && user.getID()>0 &&user.getID()!=onLineList.get(0).getUserID()){
//                                        isSessionAuthPass=false;
//                                    }
//                                    if(user!=null && user.getID()>0){
//                                        //检测当前操作的用户是否存在
//                                        Object temp=UserController.CheckUserIsExist(userService,user.getID(),request);
//                                        if (!(temp instanceof  User)) {
//                                            if(request.getMethod().equals("POST")){
//                                                request.getRequestDispatcher("/Error/Interface/UserNoExistV2").forward(request, response);
//                                            }else {
//                                                request.getRequestDispatcher("/Error/Interface/UserNoExist").forward(request, response);
//                                            }
//                                            return false;
//                                        }
//                                    }
//                                }
//                                if (onLineList == null || onLineList.size() == 0 || isSessionAuthPass==false) {
//                                    if(request.getMethod().equals("POST")){
//                                        request.getRequestDispatcher("/Error/Interface/AuthorizedV2").forward(request, response);
//                                    }else {
//                                        request.getRequestDispatcher("/Error/Interface/Authorized").forward(request, response);
//                                    }
//
//                                    return false;
//                                }
//                            }else {
//                                try {
//                                    String userString = jsonObject.getString(User.userClass);
//                                    if (userString != null && !userString.equals("")) {
//                                        user = JSON.parseObject(userString, User.class);
//                                    }
//                                } catch (Exception e) {
//                                    e.printStackTrace();
//                                }
//                                if (user != null && user.getID() > 0) {
//                                    //检测当前操作的用户是否存在
//                                    Object temp = UserController.CheckUserIsExist(userService, user.getID(),request);
//                                    if (!(temp instanceof User)) {
//                                        if(request.getMethod().equals("POST")){
//                                            request.getRequestDispatcher("/Error/Interface/UserNoExistV2").forward(request, response);
//                                        }else {
//                                            request.getRequestDispatcher("/Error/Interface/UserNoExist").forward(request, response);
//                                        }
//                                        return false;
//                                    }
//                                }
//                            }
//                        }
//                    }catch (JSONException e){
//                        if(request.getMethod().equals("POST")){
//                            request.getRequestDispatcher("/Error/Interface/JsonErrorV2").forward(request, response);
//                        }else {
//                            request.getRequestDispatcher("/Error/Interface/JsonError").forward(request, response);
//                        }
//
//                        return false;
//                    }
//                }
//            }
//        }
//
//        return true;
//    }
//
//    //  返回处理
////    @Override
//    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object object, ModelAndView modelAndView) throws Exception {
//
//    }
//
//    //  之后处理
////    @Override
//    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object object, Exception exception) throws Exception {
//
//    }
//}

//public class AuthenticationController{
//public static void main(String[] args) {
//    	int numcount = 10;
//        String sRandom = "";
//        
//        Random random = new Random(); 
//        int count = 1; int num = 0;
//        while (count == 1) {
//        	String resultrandom="";  
//        	for (int i=0;i<numcount;i++)  
//        	{  
//        		resultrandom+=random.nextInt(10);  
//        	}
//        	sRandom = resultrandom;
//        	count = 0;
//        	num++;
//        	while (num == numcount) {
//        		numcount += 2;
//			}
//		}
//        System.out.println(sRandom.substring(0,numcount/2));
//        System.out.println(sRandom.substring(numcount/2,numcount));
//        System.out.println(sRandom.substring(0,numcount/2) + 1 + sRandom.substring(numcount/2,numcount));
//	}
//}
