package com.kjyl.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;


import com.kjyl.pojo.User;
import com.kjyl.util.BaseUtil;
import com.kjyl.util.CodeInfo;
import com.kjyl.util.DBParam;
import com.kjyl.util.HostUtil;

public class AuthenticationController implements HandlerInterceptor {

    private List<String> passList;  //  静态资源放行单
    private List<String> whiteList; //  动态资源白名单
    private List<String> backWhiteList; //  后台动态资源白名单
    
    public List<String> getPassList() {
        return passList;
    }

    public void setPassList(List<String> passList) {
        this.passList = passList;
    }

    public List<String> getWhiteList() {
        return whiteList;
    }

    public void setWhiteList(List<String> whiteList) {
        this.whiteList = whiteList;
    }

    public List<String> getBackWhiteList() {
        return backWhiteList;
    }

    public void setBackWhiteList(List<String> backWhiteList) {
        this.backWhiteList = backWhiteList;
    }
    
    //  预处理
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {
		// 指定允许其他域名访问
		response.setHeader("Access-Control-Allow-Origin", "*");
		// 响应类型
		response.setHeader("Access-Control-Allow-Methods", "POST, GET, DELETE, OPTIONS, DELETE");
		response.setHeader("Access-Control-Allow-Headers", "Content-Type, x-requested-with, X-Custom-Header, HaiYi-Access-Token");
		
		
		request.getHeader(CodeInfo.sTokenKey);
		System.out.println(request.getHeader(CodeInfo.sTokenKey));
//		String uri = request.getRequestURI().replace(request.getContextPath(), "");
//		Map<String, String> map = HostUtil.getHeadersInfo(request);
//		if (uri.contains("payCallBack")) {
//			return true;
//		}
//		if (backWhiteList.contains(uri)) {
//			return true;
//		}
//		if (passList.contains(uri) || whiteList.contains(uri)) {
//			return true;
//		} else {
//			String userId = map.get("userId");
//			if (userId == null || userId.equals("")) {
//				userId = request.getParameter("userId");
//			}
//			String token = map.get(BaseUtil.tokenKey);
//			if (token == null || token.equals("")) {
//				token = request.getParameter(BaseUtil.tokenKey);
//			}
//			if (userId == null || userId.equals("")) {
//				ErrorRecords.addErrorRecords(Long.parseLong(userId), (long) 1, ErrorStatus.loginerror);
//				return false;
//			}
//			if (token == null || token.equals("")) {
//				ErrorRecords.addErrorRecords(Long.parseLong(userId), (long) 1, ErrorStatus.tokenerror);
//				return false;
//			} else {
//				try {
//					// 数据库查询用户的在线状态
//					Map<String, Object> onLineQuery = new HashMap<String, Object>();
//					onLineQuery.put(Onlines.attributeOnLineSession, token);
//					onLineQuery.put(Onlines.attributeOnLineStatus, BaseUtil.SessionStatus.OnLine.getCode());
//					List<Onlines> onLineList = onLineService.queryList(onLineQuery);
//
//					Boolean isSessionAuthPass = true;// 默认表示Session通过
//					if (onLineList.size() > 0) {
//						// 如果Session数据库存在,且url里面带上了user值,经比较他们的userId不同,则提示用户无法验证通过
//						if (Long.parseLong(userId) != onLineList.get(0).getOnlineUserid().longValue()) {
//							isSessionAuthPass = false;
//						}
//					}
//					if (onLineList == null || onLineList.size() == 0 || isSessionAuthPass == false) {
//						ErrorRecords.addErrorRecords(Long.parseLong(userId), (long) 1, ErrorStatus.notmarry);
//						return false;
//					}
//				} catch (JSONException e) {
//					ErrorRecords.addErrorRecords(Long.parseLong(userId), (long) 1, ErrorStatus.tryerror);
//					return false;
//				}
//			}
//		}
//		return true;
	    boolean flag = false;    
        String uri = request.getRequestURI().replace(request.getContextPath(), "");
        HttpSession session=request.getSession();
        Map<String, String> map = HostUtil.getHeadersInfo(request);
        if(uri.contains("payCallBack")){
            return true;
        }
        if(backWhiteList.contains(uri)){
            if(session!=null && ((User)session.getAttribute("user"))==null) {
                request.getRequestDispatcher("/Index/login").forward(request, response);
                return false;
            }else{
                return true;
            }
        }
        String param ="";
        try {
            param = HostUtil.getPostData(request);
        }catch (Exception e){}
		String mothod = request.getMethod();
		if ("GET" == mothod || "GET".equals(mothod)) {
//			Map<String, String> m = new HashMap<String, String>();
//			Enumeration<?> headerNames = request.getParameterNames();
//			while (headerNames.hasMoreElements()) {
//				String key = (String) headerNames.nextElement();
//				String value = request.getParameter(key);
//				m.put(key, value);
//			}
			map.remove("cookie");
			map.remove("accept-encoding");
			map.remove("user-agent");
			map.remove("postman-token");
			map.remove("accept");
			map.remove("host");
			map.remove("connection");
			map.remove("cache-control");
			map.remove("content-type");
			param = JSON.toJSONString(map);
		}
//		if ("GET" == mothod || "GET".equals(mothod)) {
//			System.out.println("URLL[GET]:" + request.getRequestURL() +"?" + request.getQueryString());
//		} else {
//			System.out.println("URLL[POST]:" + request.getRequestURL() + "??" + param);
//		}
		if ("/user/updateheadicon".equals(uri)) {
			map.remove("cookie");
			map.remove("accept-encoding");
			map.remove("user-agent");
			map.remove("postman-token");
			map.remove("accept");
			map.remove("host");
			map.remove("connection");
			map.remove("cache-control");
			map.remove("content-type");
			map.remove("content-length");
			param = JSON.toJSONString(map);
		}
//        if(param==null || param.equals("")) {
//            param = request.getParameter(BaseUtil.paramKey);
//        }
//        if(param==null || param.equals("")) {
//            param = (String) request.getAttribute(BaseUtil.paramKey);
//        }
        request.setAttribute(BaseUtil.dataKey, param);
        if (passList.contains(uri)) {
          	 return true;
   		}
        //前面过滤掉(写入异常)的请求，后台请求
//        SmBaseUtil.addAccessActivity(request, uri, accessActiveService);
        String token = map.get(BaseUtil.tokenKey);
        Map<String, Object> onLineQuery = new HashMap<String, Object>();
        
        return true;
        
        
        
        
        
    }

    //  返回处理
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object object, ModelAndView modelAndView) throws Exception {
    	
    }

    //  之后处理
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object object, Exception exception) throws Exception {
              
    }
}
