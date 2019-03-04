package com.huban.util;

import javax.servlet.http.HttpServletRequest;

import com.huban.Utils.ErrorUtil;
import com.huban.pojo.AccessActive;
import com.huban.pojo.User;
import com.huban.service.AccessActiveService;

import net.sf.json.JSONObject;
import sun.net.util.IPAddressUtil;

public class SmBaseUtil {
	public static void addAccessActivity(final HttpServletRequest arg0 ,final String requestUrl,final AccessActiveService AccessActiveService){
		try{
		Thread t = new Thread() {
			public void run() {
				//增加访问量
				try{
					User pjUser = null;
					if(arg0.getSession()!=null && arg0.getSession().getAttribute("StudentName")!=null){
						try{
							pjUser = (User) arg0.getSession().getAttribute("StudentName");
						}catch (Exception e) {
							
						}
					}
//					String type=arg0.getParameter("type");
//					if(type==null || type.isEmpty()){
						String type = "1";
//					}
					String userid="0";
					String ipaddress=getIpAddr(arg0);
					if(ipaddress==null){
						ipaddress="";
					}
					if(ipaddress.length()>50){
						ipaddress = ipaddress.substring(0,50);
					}
					
						if(pjUser != null){
							userid = String.valueOf(pjUser.getUserId());
						}else{
							userid=ipaddress;
						}
						AccessActive accessActive=new AccessActive();
						accessActive.setID(String.valueOf(IdWorker.CreateNewID()));
						accessActive.setAccessUrl(requestUrl);
						accessActive.setIPAddress(ipaddress);
						accessActive.setUserID(userid);
						accessActive.setType(Integer.parseInt(type));
						accessActive.setStatus(1);
//		System.err.println(ipaddress);
						if(ipaddress!=null && !ipaddress.isEmpty() && !internalIp(ipaddress)){
							JSONObject obj=NetUtil.doGet("http://ip.taobao.com/service/getIpInfo.php?ip="+ipaddress);
							if(obj!=null && obj.containsKey("data") && !obj.getString("data").isEmpty() && obj.getString("data").contains("region")){
								JSONObject data=obj.getJSONObject("data");
								if(data.containsKey("region") && data.containsKey("city")){
									accessActive.setAccessArea(data.getString("region")+data.getString("city"));
								}
							}
						}else{
							accessActive.setAccessArea("内网IP");
						}
						if(AccessActiveService!=null){
							try{
								AccessActiveService.addAccessActive(accessActive);
							}catch (Exception e) {
								AccessActiveService.updateAccessActive(accessActive);
							}
						}
				
					
				}catch (Exception e) {
					try{
						AccessActive accessActive=new AccessActive();
						accessActive.setID(String.valueOf(IdWorker.CreateNewID()));
						accessActive.setAccessUrl(requestUrl);
						accessActive.setIPAddress("");
						accessActive.setUserID("0");
						accessActive.setType(1);
						accessActive.setStatus(1);
						accessActive.setAccessArea("无法识别");
						if(AccessActiveService!=null){
							AccessActiveService.addAccessActive(accessActive);
						}
					}catch (Exception e1) {
						ErrorUtil.HandleError(null, "com.huban.util.SmBaseUtil.addAccessActivity", e1);
					}
					
				}
			
		}};
		t.start();
		}catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public static boolean internalIp(String ip) {
		if(ip==null || ip.isEmpty() || ip.equals("127.0.0.1")) {
			return true;
		}
	    byte[] addr = IPAddressUtil.textToNumericFormatV4(ip);
	    return internalIp(addr);
	}
	
	public static boolean internalIp(byte[] addr) {
		if(addr==null || addr.length>1){
			return false;
		}
	    final byte b0 = addr[0];
	    final byte b1 = addr[1];
	    //10.x.x.x/8
	    final byte SECTION_1 = 0x0A;
	    //172.16.x.x/12
	    final byte SECTION_2 = (byte) 0xAC;
	    final byte SECTION_3 = (byte) 0x10;
	    final byte SECTION_4 = (byte) 0x1F;
	    //192.168.x.x/16
	    final byte SECTION_5 = (byte) 0xC0;
	    final byte SECTION_6 = (byte) 0xA8;
	    switch (b0) {
	        case SECTION_1:
	            return true;
	        case SECTION_2:
	            if (b1 >= SECTION_3 && b1 <= SECTION_4) {
	                return true;
	            }
	        case SECTION_5:
	            switch (b1) {
	                case SECTION_6:
	                    return true;
	            }
	        default:
	            return false;

	    }
	}
	
	/*
	 * 获取ip地址
	 */
		public static String getIpAddr(HttpServletRequest request) {

			String ip = request.getHeader("x-forwarded-for");
			try {
				if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
					ip = request.getHeader("Proxy-Client-IP");
				}
				if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
					ip = request.getHeader("WL-Proxy-Client-IP");
				}
				if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
					ip = request.getRemoteAddr();
				}
			} catch (Exception e) {
//				ErrorUtil.HandleError(null, "wtb.smUtil.SmBaseUtil.getIpAddr", e);
			}
			if(ip!=null && ip.length()>100){
				ip=ip.split(",")[0];
			}
			return ip;
		}
}
