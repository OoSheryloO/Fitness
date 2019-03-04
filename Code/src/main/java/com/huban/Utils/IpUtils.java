package com.huban.Utils;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

public class IpUtils {
	
	static final String  VISIT_COUNT ="visitCnt"; //连续访问次数
    static final int  VISIT_COUNT_MAX =200; //最多访问次数
    static final String VISIT_DATE = "visitDate"; //访问日期值
    private static List<String> ipList = new ArrayList<String>();
    
    static{
    	 ipList.add("10.11.2.1");
	     ipList.add("10.11.2.2");
	     ipList.add("10.11.2.3");
    }
   
    
	//用户访问次数控制
	@SuppressWarnings("unchecked")
	public static String control(ServletContext application, String accT, String date) {//accT中含有标记值，date为日期值
		String message = null;
		if (application.getAttribute(accT)==null) {
			HashMap<String, Long> cMap = new HashMap<String, Long>();
			cMap.put(VISIT_COUNT, 1L);
			cMap.put(VISIT_DATE, Long.valueOf(date).longValue());
			application.setAttribute(accT, cMap);
		} else {
			HashMap<String,Long> map = (HashMap<String,Long>)application.getAttribute(accT);
			if (map.get(VISIT_COUNT) >= VISIT_COUNT_MAX
					&& map.get(VISIT_DATE) == Long.valueOf(date).longValue()) {
				message = "当前可访问次数超限，请明天再访问！";
				return message;
			} else {
				if (map.get(VISIT_DATE) == Long.valueOf(date).longValue()) {
					Long count = map.get(VISIT_COUNT) + 1 ;
					map.put(VISIT_COUNT, count);
				} else {//在24小时外访问，重置计数及时间
					map.put(VISIT_COUNT, 1L);
					map.put(VISIT_DATE, Long.valueOf(date).longValue());
				}
			}
		}
		return message;
	}
	
	/**
	 * IP 白名单
	 * 
	 * 判断IP是否在白名单中
	 * @param ipAddress 
	 * @return
	 */
//	public static String IpWhiteList(String ipAddress){
//		 if(!StringUtil.isNull(ipAddress)){
//			 if(!ipList.contains(ipAddress))
//				return "您的IP不在访问白名单中!";
//		 }
//		 return null;
//	}
//	
//	public static void main(String [] agres){
//		
// 
//		System.out.println("============:"+IpWhiteList("10.11.2.1"));
//	}
	/** 
     * 获取当前网络ip 
     * @param request 
     * @return 
     */ 
	public String getIpAddrOne(HttpServletRequest request){  
        String ipAddress = request.getHeader("x-forwarded-for");  
            if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {  
                ipAddress = request.getHeader("Proxy-Client-IP");  
            }  
            if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {  
                ipAddress = request.getHeader("WL-Proxy-Client-IP");  
            }  
            if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {  
                ipAddress = request.getRemoteAddr();  
                if(ipAddress.equals("127.0.0.1") || ipAddress.equals("0:0:0:0:0:0:0:1")){  
                    //根据网卡取本机配置的IP  
                    InetAddress inet=null;  
                    try {  
                        inet = InetAddress.getLocalHost();  
                    } catch (UnknownHostException e) {  
                        e.printStackTrace();  
                    }  
                    ipAddress= inet.getHostAddress();  
                }  
            }  
            //对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割  
            if(ipAddress!=null && ipAddress.length()>15){ //"***.***.***.***".length() = 15  
                if(ipAddress.indexOf(",")>0){  
                    ipAddress = ipAddress.substring(0,ipAddress.indexOf(","));  
                }  
            }  
            return ipAddress;   
    }
	
	private String getIpAddrTwo(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for"); 
        System.out.println("x-forwarded-for ip: " + ip);
        if (ip != null && ip.length() != 0 && !"unknown".equalsIgnoreCase(ip)) {  
            // 多次反向代理后会有多个ip值，第一个ip才是真实ip
            if( ip.indexOf(",")!=-1 ){
                ip = ip.split(",")[0];
            }
        }  
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
            ip = request.getHeader("Proxy-Client-IP");  
            System.out.println("Proxy-Client-IP ip: " + ip);
        }  
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
            ip = request.getHeader("WL-Proxy-Client-IP");  
            System.out.println("WL-Proxy-Client-IP ip: " + ip);
        }  
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
            ip = request.getHeader("HTTP_CLIENT_IP");  
            System.out.println("HTTP_CLIENT_IP ip: " + ip);
        }  
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");  
            System.out.println("HTTP_X_FORWARDED_FOR ip: " + ip);
        }  
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
            ip = request.getHeader("X-Real-IP");  
            System.out.println("X-Real-IP ip: " + ip);
        }  
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
            ip = request.getRemoteAddr();  
            System.out.println("getRemoteAddr ip: " + ip);
        } 
        System.out.println("获取客户端ip: " + ip);
        return ip;  
    }
	
	
	public static String getIpAddress(HttpServletRequest request){
		 
		          String ipAddress = request.getHeader("x-forwarded-for");
		          
		          if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
		              ipAddress = request.getHeader("Proxy-Client-IP");
		          }
		          if (ipAddress == null || ipAddress.length() == 0 || "unknow".equalsIgnoreCase(ipAddress)) {
		              ipAddress = request.getHeader("WL-Proxy-Client-IP");
		         }
		         if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
		             ipAddress = request.getRemoteAddr();
		             
		             if(ipAddress.equals("127.0.0.1") || ipAddress.equals("0:0:0:0:0:0:0:1")){
		                 //根据网卡获取本机配置的IP地址
		                 InetAddress inetAddress = null;
		                 try {
		                     inetAddress = InetAddress.getLocalHost();
		                 } catch (UnknownHostException e) {
		                     e.printStackTrace();
		                 }
		                 ipAddress = inetAddress.getHostAddress();
		             }
		         }
	                  //对于通过多个代理的情况，第一个IP为客户端真实的IP地址，多个IP按照','分割
		         if(null != ipAddress && ipAddress.length() > 15){
		             //"***.***.***.***".length() = 15
		             if(ipAddress.indexOf(",") > 0){
		                 ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
		             }
		         }
		         return ipAddress;
		     }

}
