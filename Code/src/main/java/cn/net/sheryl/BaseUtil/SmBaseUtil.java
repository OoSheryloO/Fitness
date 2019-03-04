//package cn.net.sheryl.BaseUtil;
//
//import java.io.BufferedReader;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.io.UnsupportedEncodingException;
//import java.net.InetAddress;
//import java.net.NetworkInterface;
//import java.net.SocketException;
//import java.net.URL;
//import java.net.URLConnection;
//import java.net.URLDecoder;
//import java.net.URLEncoder;
//import java.net.UnknownHostException;
//import java.security.GeneralSecurityException;
//import java.security.MessageDigest;
//import java.security.NoSuchAlgorithmException;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Calendar;
//import java.util.Date;
//import java.util.Enumeration;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.Properties;
//import java.util.Random;
//import java.util.UUID;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
//import javax.mail.Authenticator;
//import javax.mail.Message.RecipientType;
//import javax.mail.MessagingException;
//import javax.mail.Multipart;
//import javax.mail.PasswordAuthentication;
//import javax.mail.Session;
//import javax.mail.Transport;
//import javax.mail.internet.InternetAddress;
//import javax.mail.internet.MimeBodyPart;
//import javax.mail.internet.MimeMessage;
//import javax.mail.internet.MimeMultipart;
//import javax.servlet.ServletContext;
//import javax.servlet.http.Cookie;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import javax.xml.parsers.DocumentBuilder;
//import javax.xml.parsers.DocumentBuilderFactory;
//import javax.xml.parsers.ParserConfigurationException;
//
//import org.activiti.engine.impl.util.json.JSONArray;
//import org.activiti.engine.impl.util.json.JSONML;
//import org.apache.http.HttpRequest;
//import org.jsoup.Jsoup;
//import org.springframework.web.multipart.MultipartFile;
//import org.w3c.dom.Document;
//import org.w3c.dom.NodeList;
//import org.xml.sax.SAXException;
//
//import read.core.service.ReadHonorRecordService;
//import read.core.service.ReadRegionService;
//import read.core.service.ReadStudentStatService;
//import sun.misc.BASE64Decoder;
//
//import net.sf.json.JSONObject;
//import net.sourceforge.pinyin4j.PinyinHelper;
//import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
//import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
//import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
//import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
//import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
//
//import wtb.core.model.ClickList;
//import wtb.core.model.ErrorSend;
//import wtb.core.model.HonorRecord;
//import wtb.core.model.Region;
//import wtb.core.model.StudentStat;
//import wtb.core.model.Students;
//import wtb.core.model.WeChatUser;
//import wtb.core.model.XBUser;
//import wtb.core.service.ClickListService;
//import wtb.core.service.RegionService;
//import wtb.sessions.MySessionContext;
//
//import com.aliyun.oss.OSSClient;
//import com.sun.mail.util.MailSSLSocketFactory;
//import com.taobao.api.ApiException;
//import com.taobao.api.DefaultTaobaoClient;
//import com.taobao.api.TaobaoClient;
//import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
//import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;
//
//
//import java.*;
//public class SmBaseUtil {
//	private static SmBaseUtil instance;
//	private static int size = 20;
//	private static int page = 1;
//	
//    private static final long ONE_MINUTE = 60000L;
//    private static final long ONE_HOUR = 3600000L;
//    private static final long ONE_DAY = 86400000L;
//    private static final long ONE_WEEK = 604800000L;
//
//    private static final String ONE_SECOND_AGO = "秒前";
//    private static final String ONE_MINUTE_AGO = "分钟前";
//    private static final String ONE_HOUR_AGO = "小时前";
//    private static final String ONE_DAY_AGO = "天前";
//    private static final String ONE_MONTH_AGO = "月前";
//    private static final String ONE_YEAR_AGO = "年前";
//
//    
//    public static byte[] GenerateImage(String imgStr) throws IOException {// 对字节数组字符串进行Base64解码并生成图片
//
//		String[] b = imgStr.split(",");
//		BASE64Decoder decoder = new BASE64Decoder();
//		byte[] bytes = decoder.decodeBuffer(b[1]);
//		return bytes;
//	}
//    
//    public static String format(Date date) {
//    	
//		
//        long delta = new Date().getTime() - date.getTime();
//        if (delta < 1L * ONE_MINUTE) {
//            long seconds = toSeconds(delta);
//            return (seconds <= 0 ? 1 : seconds) + ONE_SECOND_AGO;
//        }
//        if (delta < 60L * ONE_MINUTE) {
//            long minutes = toMinutes(delta);
//            return (minutes <= 0 ? 1 : minutes) + ONE_MINUTE_AGO;
//        }
//        if (delta < 24L * ONE_HOUR) {
//            long hours = toHours(delta);
//            return (hours <= 0 ? 1 : hours) + ONE_HOUR_AGO;
//        }
//        if (delta < 48L * ONE_HOUR) {
//            return "昨天";
//        }
//        if (delta < 30L * ONE_DAY) {
//            long days = toDays(delta);
//            return (days <= 0 ? 1 : days) + ONE_DAY_AGO;
//        }
//        if (delta < 12L * 4L * ONE_WEEK) {
//            long months = toMonths(delta);
//            return (months <= 0 ? 1 : months) + ONE_MONTH_AGO;
//        } else {
//            long years = toYears(delta);
//            return (years <= 0 ? 1 : years) + ONE_YEAR_AGO;
//        }
//    }
//
//	    private static long toSeconds(long date) {
//	        return date / 1000L;
//	    }
//	
//	    private static long toMinutes(long date) {
//	        return toSeconds(date) / 60L;
//	    }
//	
//	    private static long toHours(long date) {
//	        return toMinutes(date) / 60L;
//	    }
//	
//	    private static long toDays(long date) {
//	        return toHours(date) / 24L;
//	    }
//	
//	    private static long toMonths(long date) {
//	        return toDays(date) / 30L;
//	    }
//	
//	    private static long toYears(long date) {
//	        return toMonths(date) / 365L;
//	    }
//	    
//	    
//		public static String getRandomString(int length) {
//			String base = "abcdefghijklmnopqrstuvwxyz0123456789";
//			Random random = new Random();
//			StringBuffer sb = new StringBuffer();
//			for (int i = 0; i < length; i++) {
//				int number = random.nextInt(base.length());
//				sb.append(base.charAt(number));
//			}
//			return sb.toString();
//		}
//	/**
//	 * 获取count个随机数
//	 * 
//	 * @param count
//	 *            随机数个敿
//	 * @return
//	 */
//	public String getCheckNu(int count) {
//		StringBuffer sb = new StringBuffer();
//
//			String str = "0123456789";
//			Random r = new Random();
//			for (int i = 0; i < count; i++) {
//				int num = r.nextInt(str.length());
//				sb.append(str.charAt(num));
//				str = str.replace((str.charAt(num) + ""), "");
//			}
//		return sb.toString();
//	}
//
//	public static SmBaseUtil getInstance() {
//
//			if (instance == null) {
//				instance = new SmBaseUtil();
//			}
//		return instance;
//	}
//	
//	/**
//	 * 获得  WebContent 下面
//	 * @param request
//	 * @return
//	 */
//	public static String getProjectPath(HttpServletRequest request){
//		String ProjectPath=request.getSession().getServletContext().getRealPath("/");
//		return ProjectPath;
//	}
//	
//	public static String getPositionIcon(HttpServletRequest request, Students student) {
//		String baseUrl=getProjectBaseUrl(request);
//		String iconUrl="";
//		if(student!=null && student.getChief()==SmBaseGlobal.ChiefType.Chief.getid()){
//			iconUrl=baseUrl+SmBaseGlobal.Chief;
//		}else if(student!=null && student.getChief()==SmBaseGlobal.ChiefType.DeputyChief.getid()){
//			iconUrl=baseUrl+SmBaseGlobal.DeputyChief;
//		}else if(student!=null && student.getChief()==SmBaseGlobal.ChiefType.Editorial.getid()){
//			iconUrl=baseUrl+SmBaseGlobal.Editorial;
//		}else if(student!=null && student.getOfficial()==1){
//			iconUrl=baseUrl+SmBaseGlobal.Normal;
//		}else if(student!=null && student.getPhone()!=null && !student.getPhone().isEmpty()){
//			iconUrl=baseUrl+SmBaseGlobal.UnNormal;
//		}
//		return iconUrl;
//	}
//	
//	public static List<String> getHonourIcon(HttpServletRequest request, Students student,ReadHonorRecordService ReadHonorRecordService ) {
//		String baseUrl=getProjectBaseUrl(request);
//		List<String> urls=new ArrayList<String>();
//		Map<String, Object> responseMap = new HashMap<String, Object>();
//		responseMap.put("CustomerTime", SmBaseGlobal.sdfDateTime.format(new Date()));
//		responseMap.put("StudentID", student.getID());
//		List<HonorRecord>honorRecords=ReadHonorRecordService.getHonorRecordList(responseMap);
//		String iconUrl="";
//		int Write=0,Spread=0,Excellent=0;
//		
//		for (HonorRecord honorRecord : honorRecords) {
//			iconUrl="";
//			if(honorRecord.getType()==SmBaseGlobal.HonerType.Write.getid() && Write==0){
//				iconUrl=baseUrl+"images/weixinwenshe/xzdr.png";
//				Write=1;
//			}else if(honorRecord.getType()==SmBaseGlobal.HonerType.Spread.getid() && Spread==0){
//				iconUrl=baseUrl+"images/weixinwenshe/cbdr.png";
//				Spread=1;
//			}else if(honorRecord.getType()==SmBaseGlobal.HonerType.Excellent.getid() && Excellent==0){
//				iconUrl=baseUrl+"images/weixinwenshe/xwdr.png";
//				Excellent=1;
//			}
//			if(!iconUrl.isEmpty()){
//				urls.add(iconUrl);
//			}
//		}
//		return urls;
//		
//	}
//	
//
//	public static String getLevelIcon(HttpServletRequest request, Students student,ReadStudentStatService ReadStudentStatService) {
//		Map<String, Object> responseMap = new HashMap<String, Object>();
//		String baseUrl=getProjectBaseUrl(request);
//		String iconUrl=baseUrl+"img/star0.png";
//		int DiamondLevel=Integer.parseInt(student.getDiamondLevel());
//		if(student!=null ){
//			student.setLevel(student.getLevel()!=null?student.getLevel():"0");
//			if(DiamondLevel>0){
//				Calendar calendar = Calendar.getInstance();
//				calendar.add(Calendar.MONTH, -1);
//				responseMap = new HashMap<String, Object>();
//				responseMap.put("StatMonth", calendar.get(Calendar.MONTH) + 1);
//				responseMap.put("StatYear", calendar.get(Calendar.YEAR));
//				responseMap.put("Sina", SmBaseUtil.Random());
//				responseMap.put("StudentID", student.getID());
//				List<StudentStat> result=ReadStudentStatService.getStudentStatList(responseMap);
//				int Glamour=0;
//				if(result.size()>0){
//					Glamour = Integer.parseInt(result.get(0).getGlamour());
//				}
//				if(DiamondLevel==1){
//					if( Glamour >=200){
//						iconUrl=baseUrl+"images/weixinwenshe/d-star"+ DiamondLevel +".png";
//					}else{
//						iconUrl=baseUrl+"images/weixinwenshe/d-starb"+ DiamondLevel +".png";
//					}
//				}
//				if(DiamondLevel==2){
//					if( Glamour >=300){
//						iconUrl=baseUrl+"images/weixinwenshe/d-star"+ DiamondLevel +".png";
//					}else{
//						iconUrl=baseUrl+"images/weixinwenshe/d-starb"+ DiamondLevel +".png";
//					}
//				}
//				if(DiamondLevel==3){
//					if( Glamour >=500){
//						iconUrl=baseUrl+"images/weixinwenshe/d-star"+ DiamondLevel +".png";
//					}else{
//						iconUrl=baseUrl+"images/weixinwenshe/d-starb"+ DiamondLevel +".png";
//					}
//				}
//				if(DiamondLevel==4){
//					if( Glamour >=1000){
//						iconUrl=baseUrl+"images/weixinwenshe/d-star"+ DiamondLevel +".png";
//					}else{
//						iconUrl=baseUrl+"images/weixinwenshe/d-starb"+ DiamondLevel +".png";
//					}
//				}
//				if(DiamondLevel==5){
//					if( Glamour >=5000){
//						iconUrl=baseUrl+"images/weixinwenshe/d-star"+ DiamondLevel +".png";
//					}else{
//						iconUrl=baseUrl+"images/weixinwenshe/d-starb"+ DiamondLevel +".png";
//					}
//				}
//				iconUrl=baseUrl+"images/weixinwenshe/d-star"+ DiamondLevel +".png";
//			}else{
//				iconUrl=baseUrl+"img/star"+ student.getLevel() +".png";
//			}
//		}
//		
//		return iconUrl;
//	}
//
//	
//	
//	/**
//	 * 生成动态姓名
//	 * 
//	 * @param srcName
//	 * @throws UnsupportedEncodingException 
//	 */
//	public static String getRandOtherName(String srcName) throws UnsupportedEncodingException {
//		StringBuffer otherName = new StringBuffer();
//		String nameChild = "";
//		int Namelen = new Random().nextInt(2) + 4;
//		for (int i = 0; i < Namelen; i++) {
//			int charChild = new Random().nextInt(srcName.length());
//			int charChildend = charChild + 1;
//			if (charChildend >= srcName.length()) {
//				charChildend = charChild;
//				charChild = charChild - 1;
//			}
//			nameChild = srcName.substring(charChild, charChildend);
//			otherName.append(nameChild);
//		}
//			return new String(otherName.toString().getBytes(), "utf-8");
//	}
//
//	/**
//	 * 发ꀧ߭信
//	 * 
//	 * @param 电话号码
//	 *            tel 验证砿checkNum
//	 * @return
//	 * @throws ApiException 
//	 */
//	public Boolean sent(String tel, String checkNum) throws ApiException {
//		TaobaoClient client = new DefaultTaobaoClient("http://gw.api.taobao.com/router/rest", "23270090", "c23a2a3742b8f6d851b127d33ce40c37");
//		AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
//		req.setExtend("123456");
//		req.setSmsType("normal");
//		req.setSmsFreeSignName("注册验证");
//		req.setSmsParam("{\"code\":\""+checkNum+"\",\"product\":\"才子\"}");
//		req.setRecNum(tel);
//		req.setSmsTemplateCode("SMS_2455093");
//		AlibabaAliqinFcSmsNumSendResponse rsp;
//			rsp = client.execute(req);
//			System.out.println(rsp.getBody());
//			return rsp.getResult().getSuccess();
//	}
//
//	public static String getConfigList(HttpServletRequest request,int index) throws FileNotFoundException, SAXException, IOException, ParserConfigurationException{
//		List<String> configList=SmBaseUtil.initServersList(request.getServletContext());
//		return configList.get(index);
//	}
//	
//
//	
//	/**
//	 * 发邮件
//	 * @return
//	 * @throws MessagingException 
//	 */
//	public void sendMail2(String mail,String message) throws MessagingException 
//	{
//		
//	    // 创建Properties 类用于记录邮箱的一些属性
//        final Properties props = new Properties();
//        // 表示SMTP发送邮件，必须进行身份验证
//        props.put("mail.smtp.auth", "true");
//        //此处填写SMTP服务器
//        props.put("mail.smtp.host", "smtp.exmail.qq.com");
//        //端口号，QQ邮箱给出了两个端口，但是另一个我一直使用不了，所以就给出这一个587
//        props.put("mail.smtp.port", "465");
//        // 此处填写你的账号
//        props.put("mail.user", "wenews@whohelp.cc");
//        // 此处的密码就是前面说的16位STMP口令
//        props.put("mail.password", "2016WeNews");
//
//        
//        
//        // 构建授权信息，用于进行SMTP进行身份验证
//        Authenticator authenticator = new Authenticator() {
//
//            protected PasswordAuthentication getPasswordAuthentication() {
//                // 用户名、密码
//                String userName = props.getProperty("mail.user");
//                String password = props.getProperty("mail.password");
//                return new PasswordAuthentication(userName, password);
//            }
//        };
//        
//        MailSSLSocketFactory sf = null;
//        try {
//             sf = new MailSSLSocketFactory();
//             sf.setTrustAllHosts(true);
//         } catch (GeneralSecurityException e1) {
//             e1.printStackTrace();
//         }
//         props.put("mail.smtp.ssl.enable", "true");
//         props.put("mail.smtp.ssl.socketFactory", sf);
// 
//        // 使用环境属性和授权信息，创建邮件会话
//        Session mailSession = Session.getInstance(props, authenticator);
//        mailSession.setDebug(true);
//        // 创建邮件消息
//        MimeMessage message1 = new MimeMessage(mailSession);
//        // 设置发件人
//      
//
//       
//        	  InternetAddress form = new InternetAddress(
//                      props.getProperty("mail.user"));
//              message1.setFrom(form);
//
//              // 设置收件人的邮箱
//              InternetAddress to = new InternetAddress(mail);
//              message1.setRecipient(RecipientType.TO, to);
//
//              // 设置邮件标题
//              message1.setSubject("微新闻社报警");
//
//              String path = getClass().getResource("/").getFile().replace("WEB-INF/classes/", "")+"include/mailTemp.html";  
//              String newMessage= readfile(path);
//              newMessage=newMessage.replace("${content}", message);
//              //System.err.println(newMessage);
//              Multipart mainPart = new MimeMultipart();  
//              MimeBodyPart messageBodyPart = new MimeBodyPart();
//              messageBodyPart.setContent(newMessage,"text/html; charset=utf-8");  
//              mainPart.addBodyPart(messageBodyPart); 
//              message1.setContent(mainPart);
//              
//			Transport.send(message1);
//		
//	}
//	
//	
//	
//	
//	
//	
//	
//	/**
//	 * 发邮件
//	 * @return
//	 * @throws MessagingException 
//	 */
//	public void  sendMail(String mail,String message,ErrorSend errorSend) throws MessagingException 
//	{
//		System.err.println("发送短信");
//	    // 创建Properties 类用于记录邮箱的一些属性
//        final Properties props = new Properties();
//        // 表示SMTP发送邮件，必须进行身份验证
//        props.put("mail.smtp.auth", "true");
//        //此处填写SMTP服务器
//        props.put("mail.smtp.host", errorSend.getEmailHost());
//        System.err.println(errorSend.getEmailHost());
//        //端口号，QQ邮箱给出了两个端口，但是另一个我一直使用不了，所以就给出这一个587
//        props.put("mail.smtp.port", errorSend.getEmailPort());
//        System.err.println(errorSend.getEmailPort());
//        // 此处填写你的账号
//        props.put("mail.user", errorSend.getAccount());
//        System.err.println(errorSend.getAccount());
//        // 此处的密码就是前面说的16位STMP口令
//        props.put("mail.password", errorSend.getPassWord());
//        System.err.println(errorSend.getPassWord());
//
//        
//        
//        
//        // 构建授权信息，用于进行SMTP进行身份验证
//        Authenticator authenticator = new Authenticator() {
//
//            protected PasswordAuthentication getPasswordAuthentication() {
//                // 用户名、密码
//                String userName = props.getProperty("mail.user");
//                String password = props.getProperty("mail.password");
//                return new PasswordAuthentication(userName, password);
//            }
//        };
//        
//        MailSSLSocketFactory sf = null;
//        try {
//             sf = new MailSSLSocketFactory();
//             sf.setTrustAllHosts(true);
//         } catch (GeneralSecurityException e1) {
//             e1.printStackTrace();
//         }
//         props.put("mail.smtp.ssl.enable", "true");
//         props.put("mail.smtp.ssl.socketFactory", sf);
// 
//        // 使用环境属性和授权信息，创建邮件会话
//        Session mailSession = Session.getInstance(props, authenticator);
//      
//        // 创建邮件消息
//        MimeMessage message1 = new MimeMessage(mailSession);
//        // 设置发件人
//      
//
//       
//        	  InternetAddress form = new InternetAddress(
//                      props.getProperty("mail.user"));
//              message1.setFrom(form);
//
//              // 设置收件人的邮箱
//              InternetAddress to = new InternetAddress(mail);
//              message1.setRecipient(RecipientType.TO, to);
//
//              // 设置邮件标题
//              message1.setSubject("微新闻社报警");
//              String path = getClass().getResource("/").getFile().replace("WEB-INF/classes/", "")+"include/mailTemp.html";  
//              String newMessage= readfile(path);
//              newMessage=newMessage.replace("${content}", message);
//             
//              message1.setContent(newMessage, "text/html;charset=UTF-8");
//          
//			  Transport.send(message1);
//			
//			
//			
////        // 构建授权信息，用于进行SMTP进行身份验证
////        Authenticator authenticator = new Authenticator() {
////
////            protected PasswordAuthentication getPasswordAuthentication() {
////                // 用户名、密码
////                String userName = props.getProperty("mail.user");
////                String password = props.getProperty("mail.password");
////                return new PasswordAuthentication(userName, password);
////            }
////        };
////        
////        MailSSLSocketFactory sf = null;
////        try {
////             sf = new MailSSLSocketFactory();
////             sf.setTrustAllHosts(true);
////         } catch (GeneralSecurityException e1) {
////             e1.printStackTrace();
////         }
////         props.put("mail.smtp.ssl.enable", "true");
////         props.put("mail.smtp.ssl.socketFactory", sf);
////        
////        // 使用环境属性和授权信息，创建邮件会话
////        Session mailSession = Session.getInstance(props, authenticator);
////        // 创建邮件消息
////        MimeMessage message1 = new MimeMessage(mailSession);
////        // 设置发件人
////      
////
////        // 最后当然就是发送邮件啦
////        	  InternetAddress form = new InternetAddress(
////                      props.getProperty("mail.user"));
////              message1.setFrom(form);
////
////              // 设置收件人的邮箱
////              InternetAddress to = new InternetAddress(mail);
////              message1.setRecipient(RecipientType.TO, to);
////
////              // 设置邮件标题
////              message1.setSubject("微新闻社后台管理异常系统");
////
////              // 设置邮件的内容体
////              
////              String path = getClass().getResource("/").getFile().replace("WEB-INF/classes/", "")+"include/mailTemp.html";  
////              String newMessage= readfile(path);
////             // newMessage=newMessage.replace("${content}", message);
////              message1.setContent("aaa", "text/html;charset=UTF-8");
////			Transport.send(message1);
//		
//	}
//
//	
//	public static String readfile(String filePath){
//        File file = new File(filePath);  
//        InputStream input = null;
//        try {
//            input = new FileInputStream(file);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }  
//        StringBuffer buffer = new StringBuffer();  
//        byte[] bytes = new byte[1024];
//        try {
//            for(int n ; (n = input.read(bytes))!=-1 ; ){  
//                buffer.append(new String(bytes,0,n,"utf-8"));  
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
////        System.out.println(buffer);
//        return buffer.toString();  
//    }
//
//
//	/*
//	 * 获取前台参数
//	 * 
//	 * @param http请求
//	 *            request
//	 * @return
//	 */
//	public Map<String, Object> getParamsMap(HttpServletRequest request) {
//		Map<String, Object> paramMap = new HashMap<String, Object>();
//		Enumeration<String> pNames = request.getParameterNames();
//			while (pNames.hasMoreElements()) {
//				String pName = pNames.nextElement();
//				if (pName.equals("page")) {
//					if (request.getParameter(pName) != null && Integer.valueOf(request.getParameter(pName)) > 0) {
//						page = Integer.valueOf(request.getParameter(pName));
//					}
//				} else if (pName.equals("size")) {
//					if (request.getParameter(pName) != null && Integer.valueOf(request.getParameter(pName)) > 0) {
//						size = Integer.valueOf(request.getParameter(pName));
//					}
//				} else {
//					paramMap.put(pName, URLDecoderString(request.getParameter(pName)));
//				}
//			}
//			int start = page * size - size;
//			paramMap.put("start", start);
//			paramMap.put("limit", size);
//		return paramMap;
//	}
//
//	/**
//	 * 返回客户端信息错误信息
//	 * 
//	 * @param request
//	 * @return
//	 */
//	public Map<String, Object> getresponseMap(int status) {
//		Map<String, Object> responseMap = new HashMap<String, Object>();
//		String Msg = "";
//			if (status == 0) {
//				Msg = "session不存在";
//			} else if (status == -1) {
//				Msg = "ID不能为空";
//			} else if (status == -2) {
//				Msg = "记录已存在";
//			} else if (status == -3) {
//				Msg = "网络异常,请稍后再试!";
//			}
//			responseMap.put("status", status);
//			responseMap.put("msg", Msg);
//		return responseMap;
//	}
//
//	
//	public final static String MD5(String s) throws NoSuchAlgorithmException {
//		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
//
//			byte[] btInput = s.getBytes();
//			// 获得MD5摘要算法的 MessageDigest 对象
//			MessageDigest mdInst = MessageDigest.getInstance("MD5");
//			// 使用指定的字节更新摘要
//			mdInst.update(btInput);
//			// 获得密文
//			byte[] md = mdInst.digest();
//			// 把密文转换成十六进制的字符串形式
//			int j = md.length;
//			char str[] = new char[j * 2];
//			int k = 0;
//			for (int i = 0; i < j; i++) {
//				byte byte0 = md[i];
//				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
//				str[k++] = hexDigits[byte0 & 0xf];
//			}
//			return new String(str);
//	}
//
//	public static File rename(File file) {
//			String body = "";
//			String ext = "";
//			Date date = new Date();
//			int pot = file.getName().lastIndexOf(".");// 取得文件名和后缀分割点
//			if (pot != -1) {// 说明后缀存在
//				body = date.getTime() + "";// 采用时间的形式命名
//				ext = file.getName().substring(pot);// 截取后缀名
//			} else {
//				body = (new Date()).getTime() + "";
//				ext = "";
//			}
//			String newName = body + ext;
//			file = new File(file.getParent(), newName);// 对文件进行重命名
//		return file;
//
//	}
//
//	// 获取ip地址
//	public static String getIpAddr(HttpServletRequest request) {
//
//		String ip = request.getHeader("x-forwarded-for");
//			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
//				ip = request.getHeader("Proxy-Client-IP");
//			}
//			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
//				ip = request.getHeader("WL-Proxy-Client-IP");
//			}
//			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
//				ip = request.getRemoteAddr();
//			}
//		if(ip.length()>100){
//			ip=ip.substring(0,100);
//		}else{
//			
//		}
//		return ip;
//	}
//
//	
//
//	public static int getClickInfo(HttpServletRequest req, String EntryID,ClickListService clickListService) throws IOException, InterruptedException {
//		
//		int result=0;
//		String ip = getIpAddr(req);
//		
//		Map<String, Object> responseMap =new HashMap<String, Object>();
//			responseMap.put("IPAddress", ip);
//			responseMap.put("Type", 1);
//			responseMap.put("BeLongID", EntryID);
//			int count=clickListService.CheckExistByIdAddress(responseMap);
//			if(count<=0){
//				String macip = MacAddress.getMacAddress(ip);
//				ClickList clicklist=new ClickList();
//				clicklist.setBeLongID(Long.parseLong(EntryID));
//				clicklist.setCreateTime(new Date());
//				clicklist.setID(CreateNewID());
//				clicklist.setIPAddress(ip);
//				clicklist.setMacAddress(macip);
//				clicklist.setStatus(SmBaseGlobal.CheckStatus.Effective.getid());
//				clicklist.setType(1);
//				result=clickListService.addClickList(clicklist);
//			}
//			
//		return result;
//
//	}
//	/**
//	 * 添加分页参数信息
//	 * @param request
//	 * @return
//	 */
//	public static Map<String, Object> AddPageParam(HttpServletRequest request){
//		Map<String, Object> checkParammap = new HashMap<String, Object>();
//		String pageSize=request.getParameter("pageSize");
//		String pageNumber=request.getParameter("pageNumber");
//		String Submit=request.getParameter("submitType");
//		if(pageSize==null || pageSize.isEmpty()){
//			pageSize="10";
//		}
//		if(pageNumber==null){
//			pageNumber="1";
//		}
//		
//		
//		if (pageSize != null && !pageSize.isEmpty()) {
//			checkParammap.put("limit", pageSize);
//		}else{
//			checkParammap.put("limit", 10);
//		}
//		if (pageNumber != null && !pageNumber.isEmpty()) {
//			if(Submit!=null && Submit.equals("phone")){
//				checkParammap.put("start", Integer.parseInt(pageNumber)*Integer.parseInt(pageSize)-Integer.parseInt(pageSize));
//			}else{
//				checkParammap.put("start", pageNumber);
//			}
//			
//		}else{
//			checkParammap.put("start", 0);
//		}
//		return checkParammap;
//	}
//	
//	/**
//	 * 添加手机分页参数信息
//	 * @param pageSize 页数量
//	 * @param pageNumber 页
//	 * @param request
//	 * @return
//	 */
//	public static Map<String, Object> AddPhonePageParam(String pageSize,String pageNumber){
//		Map<String, Object> checkParammap = new HashMap<String, Object>();
//		if(pageSize==null || pageSize.isEmpty()){
//			pageSize="10";
//		}
//		if(pageNumber==null){
//			pageNumber="1";
//		}
//		if (pageSize != null && !pageSize.isEmpty()) {
//			checkParammap.put("limit", pageSize);
//		}else{
//			checkParammap.put("limit", 10);
//		}
//		if (pageNumber != null && !pageNumber.isEmpty()) {
//				checkParammap.put("start", Integer.parseInt(pageNumber)*Integer.parseInt(pageSize)-Integer.parseInt(pageSize));
//		}else{
//			checkParammap.put("start", 0);
//		}
//		return checkParammap;
//	}
//	
//	
//	/**
//	 * 判断是否是一个中文汉字
//	 * 
//	 * @param c
//	 *            字符
//	 * @return true表示是中文汉字，false表示是英文字母
//	 * @throws UnsupportedEncodingException
//	 *             使用了JAVA不支持的编码格式
//	 */
//	public static boolean isChineseChar(char c)
//			throws UnsupportedEncodingException {
//		// 如果字节数大于1，是汉字
//		if((c >= 0x0391 && c <= 0xFFE5)){ //中文字符
//			return true;
//		}
//		return false;
//				
//	}
//
//	/**
//	 * 按字节截取字符串
//	 * 
//	 * @param orignal
//	 *            原始字符串
//	 * @param count
//	 *            截取位数
//	 * @return 截取后的字符串
//	 * @throws UnsupportedEncodingException
//	 *             使用了JAVA不支持的编码格式
//	 */
//	public static String substring(String orignal, int count)
//			throws UnsupportedEncodingException {
//		// 原始字符不为null，也不是空字符串
//		if (orignal != null && !"".equals(orignal) ) {
//			// 将原始字符串转换为GBK编码格式
//			orignal = new String(orignal.getBytes());
//			int byteLen=orignal.getBytes().length;
//			// 要截取的字节数大于0，且小于原始字符串的字节数
//			if ( count < byteLen) {
//				StringBuffer buff = new StringBuffer();
//				char c;
//				for (int i = 0; i < count; i++) {
//					c = orignal.charAt(i);
//					buff.append(c);
//					if (isChineseChar(c)) {
//						// 遇到中文汉字，截取字节总数减1
//						count-=2;
//					}
//				}
//				return buff.toString()+"...";
//			}
//		}
//		return orignal;
//	}
///**
// * 检测验证码是否正确
// * @param session
// * @param CurrentAuthCode
// * @return
// */
//	public static String CheckAuthCode(HttpSession session,String CurrentAuthCode){
//		String authCode=null;
//		if(session.getAttribute("AuthenCode")==null){
//			return "验证码已失效，请重新获取";
//		}else{
//			authCode=(String)session.getAttribute("AuthenCode");
//			if(!CurrentAuthCode.equals(authCode)){
//				return "验证码不正确，请重新输入验证码！";
//			}
//		}
//		return null;
//	}
//	
//	
//	
//	
//	
//	// 将汉字转换为全拼  
//    public static String getPingYin(String src) throws BadHanyuPinyinOutputFormatCombination {  
//  
//        char[] t1 = null;  
//        t1 = src.toCharArray();  
//        String[] t2 = new String[t1.length];  
//        HanyuPinyinOutputFormat t3 = new HanyuPinyinOutputFormat();  
//          
//        t3.setCaseType(HanyuPinyinCaseType.LOWERCASE);  
//        t3.setToneType(HanyuPinyinToneType.WITHOUT_TONE);  
//        t3.setVCharType(HanyuPinyinVCharType.WITH_V);  
//        String t4 = "";  
//        int t0 = t1.length;  
//            for (int i = 0; i < t0; i++) {  
//                // 判断是否为汉字字符  
//                if (java.lang.Character.toString(t1[i]).matches(  
//                        "[\\u4E00-\\u9FA5]+")) {  
//                    t2 = PinyinHelper.toHanyuPinyinStringArray(t1[i], t3);  
//                    t4 += t2[0];  
//                } else  
//                    t4 += java.lang.Character.toString(t1[i]);  
//            }  
//            // System.out.println(t4);  
//            return t4;  
//    }  
//  
//    // 返回中文的首字母  
//    public static String getPinYinHeadChar(String str) {  
//  
//        String convert = "";  
//        for (int j = 0; j < str.length(); j++) {  
//            char word = str.charAt(j);  
//            String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(word);  
//            if (pinyinArray != null) {  
//                convert += pinyinArray[0].charAt(0);  
//            } else {  
//                convert += word;  
//            }  
//        }  
//        return convert;  
//    }  
//    /**
//     * 获取本项目的根URL 如 www.whohelp.cc/WebContentName
//     * @param request
//     * @return
//     */
//    public static String getProjectBaseUrl(HttpServletRequest request){
//    	String basePath=request.getScheme() + "://"
//    			+ request.getServerName() + ":" + request.getServerPort()
//    			+ request.getContextPath() + "/";
//    
//    	return basePath;
//    }
//    
//    /**
//     * 
//     * 获取微新闻社的的根URL 如 www.whohelp.cc/WeNewsAgency
//     * @param request
//     * @return
//     */
//    public static String getWeNewInterFace(HttpServletRequest request){
//    	String basePath=request.getScheme() + "://"
//    			+ request.getServerName() + ":" + request.getServerPort()
//    			+ "/WeNewsAgency" + "/";
//    
//    	return basePath;
//    }
//	/**
//	 * 转换校播的用户类为自己的学生类
//	 * @param user
//	 * @return
//	 */
//    public static Students XBUserParseStudent(XBUser user,Students stu,ReadRegionService regionService){
//    	//是否有改变
//    	Boolean isModify=false;
//    	
//    	if(stu==null){
//    		stu=new Students();
//    	}
//    	if(stu.getSex()==null || stu.getSex().isEmpty()){
//    		stu.setSex("0");
//    	}
//    	stu.setIschange(0);
//    	if(user.getUID()!=stu.getID()){
//    		stu.setID(user.getUID());
//    		stu.setPKID(String.valueOf(user.getUID()));
//    		isModify=true;
//    	}
//    	if(!user.getChild_Name().equals(stu.getName())){
//    		stu.setName(user.getChild_Name());
//    		isModify=true;
//    	}
//    	if(user.getChild_Birth_Time()!=null && !user.getChild_Birth_Time().isEmpty() && user.getChild_Birth_Time()!=stu.getAge()){
//    		stu.setAge(user.getChild_Birth_Time());
//    		isModify=true;
//    	}else{
//    		stu.setAge(null);
//    	}
//    	if(!user.getPhone().equals(stu.getPhone())){
//    		stu.setPhone(user.getPhone());
//    		isModify=true;
//    	}
//    	if(user.getOpenID()!=null && !user.getOpenID().equals(stu.getOpenID())&&(stu.getOpenID()==null || stu.getOpenID().isEmpty())){
//    		stu.setOpenID(user.getOpenID());
//    		isModify=true;
//    	}
//    	if(user.getWBID()!= stu.getWBID()){
//    		stu.setWBID(user.getWBID());
//    		isModify=true;
//    	}
//    	if(!user.getChild_School().equals(stu.getSchool()) ){
//    		stu.setSchool(user.getChild_School());
//    		isModify=true;
//    	}
//    	if(stu.getImage()==null){
//    		stu.setImageUrl(user.getChild_Headimg());
//    		//isModify=true;
//    	}
//    	if(!user.getName().equals(stu.getParentName()) ){
//    		stu.setParentName(user.getName());
//    		isModify=true;
//    	}
//    	if(user.getEmail()!=null && !user.getEmail().equals(stu.getEmail()) ){
//    		stu.setEmail(user.getEmail());
//    		isModify=true;
//    	}
//    	if(user.getChild_Grade()!=null && !user.getChild_Grade().equals(stu.getGrade()) ){
//    		stu.setGrade(user.getChild_Grade());
//    		isModify=true;
//    	}
//    	if(user.getChild_Habit()!=null && !user.getChild_Habit().equals(stu.getHabit()) ){
//    		stu.setHabit(user.getChild_Habit());
//    		isModify=true;
//    	}
//    	if(stu.getSchool()==null && stu.getSchool().isEmpty() ){
//    		Map<String, Object> checkParammap=new HashMap<String, Object>();
//    		checkParammap.put("Name", stu.getSchool());
//			List<Region> regions=regionService.getRegionList(checkParammap);
//			if(regions.size()>0){
//				stu.setAreaID(regions.get(0).getID());
//				stu.setArea(regions.get(0));
//				stu.setSchool(regions.get(0).getName());
//				isModify=true;
//			}
//		}
//    	if(isModify){
//    		stu.setIschange(1);
//    	}
//    	
//    	return stu;
//    }
//    /**
//     * 将xbUser对象转换为学生类
//     * @param xbuser
//     * @param stu
//     * @param regionService
//     * @return
//     */
//    public static Students parseXBUserForStudent(WeChatUser xbuser,Students stu,RegionService regionService){
//		Map<String, Object> checkParammap=new HashMap<String, Object>();
//		if(stu==null){
//			stu=new Students();
//		}
//		if(xbuser.getNickName()!=null){
//			stu.setName(xbuser.getNickName());
//			stu.setOpenID(xbuser.getOpenID());
//			stu.setSex(xbuser.getSex());
//			stu.setImageUrl(xbuser.getHeadImgUrl());
//			stu.setCreateTime(SmBaseGlobal.sdfDate.format(new Date()));
//			stu.setID(SmBaseUtil.CreateNewID());
//			stu.setLevel("0");
//			stu.setStatus(SmBaseGlobal.CheckStatus.Effective.getid());
//			if(stu.getSchool()!=null && !stu.getSchool().isEmpty()){
//				checkParammap = new HashMap<String, Object>();
//				checkParammap.put("Name", stu.getSchool());
//				List<Region> regions=regionService.getRegionList(checkParammap);
//				if(regions.size()>0){
//					stu.setAreaID(regions.get(0).getID());
//					stu.setArea(regions.get(0));
//				}
//			}
//		}
//		return stu;
//	}
//
//    /**
//     * 获取当前项目的url,用户外链的跳转
//     * @param req
//     * @return
//     */
//    public static String getCurrentWebUrl(HttpServletRequest req){
//    	String basePath = req.getScheme() + "://"
//				+ "www.whohelp.cc"+req.getContextPath();
//    	return basePath;
//    }
//    /**
//     * 用户登录的状态 snsapi_userinfo表示 手工授权登录 snsapi_base 表示自动授权登录
//     * @param api
//     * @return 返回登录成功后的url
//     * @throws UnsupportedEncodingException 
//     */
//    public static String getWeChatLoginUrl(String api,String returlUrl) throws UnsupportedEncodingException{
//    	String islogin="";
//			islogin = SmBaseGlobal.WeChatAPIURL+"WeChatAPI/directUrl.jsp?type=setp1&&api="+ api +"&&aid="+URLEncoder.encode(returlUrl, "utf-8");
//		return islogin;
//    }
//	/**
//	 * 创建一个session对象
//	 * @param SessionName
//	 * @param obj
//	 * @param req
//	 * @param session
//	 */
//    public static void CreateSession(String SessionName,Object obj,HttpServletRequest req, HttpSession session,HttpServletResponse resp){
//		session = req.getSession();
//		MySessionContext myc= MySessionContext.getInstance();  
//		myc.DelSession(session);
//		myc.AddSession(session);
//		session.removeAttribute("StudentName");
//		session.setAttribute("StudentName", obj);
//		Cookie cookie = new Cookie("StudentSID", session.getId());
//		cookie.setMaxAge(3600*24*7);
//		resp.addCookie(cookie);
//    }
//    public static Cookie getCookies(HttpServletRequest resp,String CookieName){
//    	Cookie Result=null;
//    	Cookie[] cookies = resp.getCookies();//这样便可以获取一个cookie数组
//    	if(cookies!=null){
//			for(Cookie cookie : cookies){
//				if(cookie.getName().equals(CookieName)){
//					Result= cookie;
//				}
//			}
//    	}
//		return Result;
//    }
//    public static Object getSessionValueForCookie(Object student,HttpServletRequest req,String SessionName,HttpServletResponse resp){
//	    	if(student==null){
//	    		Cookie cookie=SmBaseUtil.getCookies(req,"StudentSID");
//	    		if(cookie!=null){
//	    			String SID=cookie.getValue();
//	    			if(SID!=null && !SID.isEmpty()){
//	    				MySessionContext myc= MySessionContext.getInstance();
//	    				HttpSession session = myc.getSession(SID);
//	    				if(session!=null && session.getId()!=null && !session.getId().isEmpty()){
//	    					student=session.getAttribute(SessionName);
//	    					CreateSession("StudentName", student, req, session,resp);
//	    				}
//	    			}
//	    		}
//	    	}
//	    	return student;
//    }
//    @SuppressWarnings("finally")
//   	public static JSONObject SendGetRequestURL(String url) throws IOException{
//       	String result="";
//       	URL realUrl;
//       	JSONObject ErrorMessage=new JSONObject();
//   			realUrl = new URL(url);
//   		
//       	  // 打开和URL之间的连接
//           URLConnection connection = realUrl.openConnection();
//           // 设置通用的请求属性
//           connection.setRequestProperty("accept", "*/*");
//           connection.setRequestProperty("connection", "Keep-Alive");
//           connection.setRequestProperty("user-agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
//           //connection.set
//           connection.connect();
//           System.err.print("connect");
//           // 定义 BufferedReader输入流来读取URL的响应
//           BufferedReader in = new BufferedReader(new InputStreamReader(
//                   connection.getInputStream()));
//           String line;
//           System.err.print("readLine");
//           while ((line = in.readLine()) != null) {
//               result += line;
//           }
//           System.err.print("readLineed");
//           System.err.print(result);
//           if(result.contains("{") || result.contains("}")){
//        	   ErrorMessage=SmBaseUtil.PaseJsonToJsonObject(result);
//           }
//           //	ErrorMessage=JSONObject.fromObject(result);
//            if(ErrorMessage==null){
//            	ErrorMessage=new JSONObject();
//            }
//           	ErrorMessage.accumulate("Data", result);
//           	System.err.print(ErrorMessage.toString());
//           	
//   			return ErrorMessage;
//       }
//    /**
//	 * 初始化多服务器更新索引的接口列表
//     * @throws IOException 
//     * @throws SAXException 
//     * @throws FileNotFoundException 
//     * @throws ParserConfigurationException 
//	 * */
//	public static List<String> initServersList(ServletContext event) throws FileNotFoundException, SAXException, IOException, ParserConfigurationException {
//		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
//		List<String> lsStr=new ArrayList<String>();
//		String path=event.getRealPath("/WEB-INF/config.xml");
//		path.replaceAll("/", File.separator);
//		DocumentBuilder db = dbf.newDocumentBuilder();
//		Document doc = db.parse(new FileInputStream(path));
//		// Element root = doc.getDocumentElement();
//		NodeList serverslist = doc.getElementsByTagName("item");
//		if (serverslist != null) {
//			for (int i = 0; i < serverslist.getLength(); i++) {
//				String updateIndexUrl = doc
//						.getElementsByTagName("value").item(i)
//						.getFirstChild().getNodeValue();
//				
//				lsStr.add(updateIndexUrl);
//			}
//		}
//			
//		return lsStr;
//	}
//	public static long CreateNewID(){
//    	return new IdWorker(1, new Random().nextInt(31)).nextId();
//    }
//	public static JSONObject PaseJsonToJsonObject(String json){
//		JSONObject jsonObj=null;
//		if(json!=null && !json.isEmpty()){
//				if(!json.contains("-")){
//					json=json.replace('-', '%');
//				}
//				json=URLDecoderString(json);
//				if(json.endsWith("]")){
//					json+="]}";
//				}
//				if(!json.endsWith("}")){
//					json+="\"}";
//				}
//				if(!json.contains("nickname\":\"")){
//					json=json.replaceFirst("nickname\"", "nickname\":\"");
//				}
//				if(!json.contains(",\"sex\"")){
//					json=json.replaceFirst("sex\"", ",\"sex\"");
//				}
//				if(!json.contains("\",\"sex\"")){
//					json=json.replaceFirst(",\"sex\"", "\",\"sex\"");
//				}
//				if(!json.contains(",\"}")){
//					json=json.replaceFirst(",\"}", "}");
//				}
//	    		jsonObj=JSONObject.fromObject(json);
//		}
//		return jsonObj;
//	}
//	
//	public static String  URLDecoderString(String string) {
//		String newString="";
//		if(string==null){
//			string="";
//		}
//		try {
//			newString = URLDecoder.decode(string, "utf-8");
//		} catch (Exception e) {
//			string = string.replaceAll("%", "%25");
//			try {
//				newString = URLDecoder.decode(string, "utf-8");
//			} catch (UnsupportedEncodingException e1) {
//				newString= string;	
//			}
//		}
//		return newString;
//	}
//	
//	/**
//	 * 返回带A标签的ImgHtml代码
//	 * @return
//	 */
//	public static String getClickImageHtml(String url){
//		String[] strs=url.split(",");
//		String a_url="";
//		if(strs.length>=3){
//			a_url=strs[0];
//		}else{
//			a_url=strs[0];
//		}
//		String html="<a href='"+ a_url +"' title='图片' data-gallery=''><img alt='image' style='width:70px;height:70px;' onerror='nofind()' src='"+ a_url +"'></a>";
//		return html;
//	}
//	/**
//	 * 获取微信的个人信息
//	 * @param openid
//	 * @return
//	 * @throws IOException 
//	 */
//	public static String getWeChat_token(String openid) throws IOException{
//		String WeChat_token=SmBaseGlobal.WeChatAPIURL+ "WeChatAPI/wechat.do?getToken=true";
//		JSONObject jsobject=SendGetRequestURL(WeChat_token);
//		String access_key="";
//		if(jsobject.containsKey("Data")){
//			access_key=jsobject.getString("Data");
//		}
//		if(!access_key.isEmpty()){
//			String WeChatAPI_UsrInfo="https://api.weixin.qq.com/cgi-bin/user/info?access_token="+ access_key +"&openid="+ openid +"&lang=zh_CN";
//			JSONObject jsuserinfo=SendGetRequestURL(WeChatAPI_UsrInfo);
//			if(jsuserinfo.containsKey("Data")){
//				return jsuserinfo.getString("Data");
//			}
//		}
//		return null;
//	}
//	public static boolean isNumeric(String str){ 
//	   if(str==null || str.isEmpty())return false;
//	   Pattern pattern = Pattern.compile("[0-9]*"); 
//	   Matcher isNum = pattern.matcher(str);
//	   if( !isNum.matches() ){
//	       return false; 
//	   } 
//	   return true; 
//	}
//
//	public static int compareDate(Date d1,Date d2){
//    if (d1.getTime() > d2.getTime()) {
//        System.out.println("dt1 在dt2前");
//        return 1;
//    } else if (d1.getTime() < d2.getTime()) {
//        System.out.println("dt1在dt2后");
//        return -1;
//    } else {//相等
//        return 0;
//    }
//	}
//	public static String getLocalMac() throws SocketException, UnknownHostException {
//	//获取网卡，获取地址
//	InetAddress ia=InetAddress.getLocalHost();
//	byte[] mac = NetworkInterface.getByInetAddress(ia).getHardwareAddress();
//	//System.out.println("mac数组长度："+mac.length);
//	StringBuffer sb = new StringBuffer("");
//	for(int i=0; i<mac.length; i++) {
//		if(i!=0) {
//			sb.append("-");
//		}
//		//字节转换为整数
//		int temp = mac[i]&0xff;
//		String str = Integer.toHexString(temp);
//		//System.out.println("每8位:"+str);
//		if(str.length()==1) {
//			sb.append("0"+str);
//		}else {
//			sb.append(str);
//		}
//	}
//		return sb.toString().toUpperCase();
//	}
//	public static String Random() {
//		return getRandomString(8);
//	}
//	
//	public static String getDefHeadImg(HttpServletRequest request){
//		String path = request.getContextPath();
//		String basePath = request.getScheme() + "://"
//				+ request.getServerName() + ":" + request.getServerPort()
//				+ path + "/";
//		return basePath+SmBaseGlobal.DefHeadImg;
//	}
//	
//	public static String getDefXiaoBoImg(HttpServletRequest request){
//		String path = request.getContextPath();
//		String basePath = request.getScheme() + "://"
//				+ request.getServerName() + ":" + request.getServerPort()
//				+ path + "/";
//		return basePath+SmBaseGlobal.XiaoBoLogo;
//	}
//	
//	
//	public static String getErrorImg(HttpServletRequest request){
//		String path = request.getContextPath();
//		String basePath = request.getScheme() + "://"
//				+ request.getServerName() + ":" + request.getServerPort()
//				+ path + "/img/errorpic.jpg";
//		return basePath;
//	}
//	public static boolean compareDate(Date voteStart,Date voteEnd,Date noticeTime){
//		if (noticeTime.getTime()>voteStart.getTime()&&noticeTime.getTime()<voteEnd.getTime()) {
//			return true;
//		}else{
//			return false;
//		}
//	}
//	
//	/**
//	 * 文件上传
//	 * @param is
//	 * @param file
//	 * @return
//	 */
//	public static String UploadAliYunFileService(InputStream is,MultipartFile file,String fileType){
//		String name=file.getOriginalFilename();
//		String prefix=name.substring(name.lastIndexOf(".")+1);
//		Date date=new Date();
//		String path=fileType+"/"+SmBaseGlobal.sdfDate.format(date)+"/";
//		UUID uuid = UUID.randomUUID();
//		String fileName=uuid.randomUUID().toString()+"."+prefix;
//		path= path+fileName;
//		// endpoint以杭州为例，其它region请按实际情况填写
//		String endpoint = "http://oss-cn-hangzhou.aliyuncs.com";
//		// accessKey请登录https://ak-console.aliyun.com/#/查看
//		String accessKeyId = "LTAIIQkISE7PpDu1";
//		String accessKeySecret = "j5Fd1wNx5w5J3Ox3ntouL8HqWwh3Vs";
//		// 创建OSSClient实例
//		OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
//		// 上传文件流
//		InputStream inputStream = is;
//		//       Images/2017-03-15/----------------cb21aa0a-cceb-49d6-a507-77f3dd71ee5c_800.PNG
//		ossClient.putObject("wenews", path, inputStream);
//		// 关闭client
//		ossClient.shutdown();
//		System.out.println("http://wenews.oss-cn-hangzhou.aliyuncs.com/"+path);
//		return "http://wenews.oss-cn-hangzhou.aliyuncs.com/"+path;
//	}
//	
//	
//	/**
//	 * 文件上传 
//	 * @param is
//	 * @param file
//	 * @return
//	 * @throws IOException 
//	 */
//	public static void UploadAliYunVideoService(InputStream is,String path) throws IOException{
//		// endpoint以杭州为例，其它region请按实际情况填写
//		final String endpoint = "http://oss-cn-hangzhou.aliyuncs.com";
//		// accessKey请登录https://ak-console.aliyun.com/#/查看
//		final String accessKeyId = "LTAIIQkISE7PpDu1";
//		final String accessKeySecret = "j5Fd1wNx5w5J3Ox3ntouL8HqWwh3Vs";
//		// 创建OSSClient实例
//		OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
//		// 上传文件流
//		InputStream inputStream = is;
//		//       Images/2017-03-15/----------------cb21aa0a-cceb-49d6-a507-77f3dd71ee5c_800.PNG
//		ossClient.putObject("wenews", path, inputStream);
//		// 关闭client
//		ossClient.shutdown();
//	}
//	
//	
//	/**
//	 * 文件上传 
//	 * @param is
//	 * @param file
//	 * @return
//	 * @throws IOException 
//	 */
//	public static void UploadAliYunVideoService2(File file,String path) throws IOException{
//		// endpoint以杭州为例，其它region请按实际情况填写
//		 String endpoint = "http://oss-cn-hangzhou.aliyuncs.com";
//		// accessKey请登录https://ak-console.aliyun.com/#/查看
//		String accessKeyId = "LTAIIQkISE7PpDu1";
//		String accessKeySecret = "j5Fd1wNx5w5J3Ox3ntouL8HqWwh3Vs";
//		// 创建OSSClient实例
//		OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
//		// 上传文件流
//		//       Images/2017-03-15/----------------cb21aa0a-cceb-49d6-a507-77f3dd71ee5c_800.PNG
//		ossClient.putObject("wenews", path, file);
//		// 关闭client
//		ossClient.shutdown();
//	}
//	
//	
//	/**
//	 * 目前解析只支持腾讯视频
//	 * @param vid 视频ID
//	 * @return
//	 * @throws IOException 
//	 */
//	public static String AnalysisUrl(String vid) throws IOException{
//		String mp4Url=null;
//		String analysisUrl="http://vv.video.qq.com/geturl?vid="+vid+"&otype=xml&platform=1&ran=0%2E9652906153351068";//解析地址
//        org.jsoup.nodes.Document doc = Jsoup.connect(analysisUrl).get();
//        JSONArray result = JSONML.toJSONObject(doc.toString()).getJSONArray("childNodes");
//		int len = result.length();
//		for (int i = 0; i < len; i++) {
//			org.activiti.engine.impl.util.json.JSONObject js = result.getJSONObject(i);
//			Object tagName = js.get("tagName");
//			if (tagName.equals("vd")) {
//				JSONArray vi=js.getJSONArray("childNodes");
//				for (int j = 0; j < vi.length(); j++) {
//					js = vi.getJSONObject(j);
//					tagName = js.get("tagName");
//					if (tagName.equals("vi")) {
//						JSONArray urla=js.getJSONArray("childNodes");
//						for (int k = 0; k < urla.length(); k++) {
//							js = urla.getJSONObject(k);
//							tagName = js.get("tagName");
//							if (tagName.equals("url")) {
//								mp4Url=js.getJSONArray("childNodes").get(0).toString();
//							}
//						}
//					}
//				}
//			}
//		}
//		return mp4Url;
//	}
//	
//	/**
//	 * 现在的时间 （格式：2017-04-24 09:21:10）
//	 * @param sdf
//	 * @return
//	 */
//	public static String NowTime(SimpleDateFormat sdf){
//		return sdf.format(new Date());
//	} 
//	
//	public static String getSystemInfo(){
//		Properties p=System.getProperties();
//		return p.getProperty("os.name", "linux ")+p.getProperty("os.version", "");
//	}
//	/**
//	 * 
//	 * @Author 作者：马健
//	 * @Phone  联系qq：1039510196
//	 * @CreateTime 创建时间：2017年7月3日 下午5:12:08
//	 * @Details 获取项目的绝对路径 形如D:\java\apache-tomcat-8.0.38\WeNewsInterface\
//	 */
//	public static String getAbsolutelyPath(HttpServletRequest request){
//		return request.getServletContext().getRealPath("/");
//	}
//	
//	public static String FormatTime(String ftime) {
//		String time=null;
//		if (ftime!=null) {
//			time=ftime.substring(0,19);
//		}else {
//			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
//			time = sdf.format(new Date());
//		}
//		return time;
//	}
//	
//	//单个值的
//	public static Map<String, Object> resultMap(int wh_code,String message,Object data){
//		Map<String, Object> result=new HashMap<>();
//		result.put("wh_code", wh_code);
//		result.put("message", message);
//		result.put("data", data);
//		return result;
//	}
//	
//	//用于列表
//	public static Map<String, Object> resultListMap(int wh_code,String message,Object data,long Count){
//		Map<String, Object> result=new HashMap<>();
//		result.put("wh_code", wh_code);
//		result.put("message", message);
//		result.put("count", Count);
//		result.put("data", data);
//		return result;
//	}
//	//监测是否为空
//	public static boolean CheckIsNull(String str){
//		boolean flag=true;
//		if (str!=null) {
//		if (!str.trim().isEmpty()) {
//			if (str.equals(null)||str.equals("")||str.equals("null")||str.equals("undefined")||str.equals("\"\"")) {
//				flag=false;
//			}
//		}else {
//			flag=false;
//		}}else {
//			flag=false;
//		}
//		return flag;
//	}
//
//}
