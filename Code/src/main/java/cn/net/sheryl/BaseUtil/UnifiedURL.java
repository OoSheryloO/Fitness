//package wtb.smUtil;
//
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.util.List;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.xml.parsers.ParserConfigurationException;
//
//import org.xml.sax.SAXException;
//
//import wtb.core.controller.UserController;
//
//public class UnifiedURL {
//	//120.26.206.54 127.0.0.1
//	private static String TestAddress="http://120.26.206.54/WeNewsAgency/";
//	/**
//	 * 发布新闻统一过这个接口
//	 * NoticeID 新闻ID
//	 * imageIDList 图片组ID
//	 * title 标题
//	 * content 内容
//	 * studentID 
//	 * @param request
//	 * @return
//	 * @throws ParserConfigurationException 
//	 * @throws IOException 
//	 * @throws SAXException 
//	 * @throws FileNotFoundException 
//	 */
//	public static String AddNoticesUrl(HttpServletRequest request) throws FileNotFoundException, SAXException, IOException, ParserConfigurationException{
//		String basePath="";
//	  	List<String> configList=SmBaseUtil.initServersList(request.getServletContext());
//		if(configList.size()>=4 && configList.get(3)!=null && configList.get(3).equals("true")){
//			basePath=SmBaseUtil.getWeNewInterFace(request);
//		}else{
//			basePath=TestAddress;
//		}
//		return basePath+"Notices/addphoneNotices";
//	};
//	
//	/**
//	 * 增加阅读量 接口
//	 * noticeID 新闻ID
//	 * @param request
//	 * @return
//	 * @throws ParserConfigurationException 
//	 * @throws IOException 
//	 * @throws SAXException 
//	 * @throws FileNotFoundException 
//	 */
//	public static String AddNoticesClickCount(HttpServletRequest request) throws FileNotFoundException, SAXException, IOException, ParserConfigurationException{
//		String basePath="";
//	  	List<String> configList=SmBaseUtil.initServersList(request.getServletContext());
//			if(configList.size()>=4 && configList.get(3)!=null && configList.get(3).equals("true")){
//			basePath=SmBaseUtil.getWeNewInterFace(request);
//		}else{
//			basePath=TestAddress;
//		}
//		return basePath+"Product/AddNoticesClickCount";
//	};
//	
//	
//	/**
//	 * 增加阅读量 接口
//	 * noticeID 新闻ID
//	 * num 增加积分数
//	 * title 原因
//	 * @param request
//	 * @return
//	 * @throws ParserConfigurationException 
//	 * @throws IOException 
//	 * @throws SAXException 
//	 * @throws FileNotFoundException 
//	 */
//	public static String AddLevel(HttpServletRequest request) throws FileNotFoundException, SAXException, IOException, ParserConfigurationException{
//		String basePath="";
//	  	List<String> configList=SmBaseUtil.initServersList(request.getServletContext());
//			if(configList.size()>=4 && configList.get(3)!=null && configList.get(3).equals("true")){
//			basePath=SmBaseUtil.getWeNewInterFace(request);
//		}else{
//			basePath=TestAddress;
//		}
//		return basePath+"Notices/AddLevel";
//	};
//	
//
//	/**
//	 * 发布新闻统一过这个接口(新版)
//	 * @throws ParserConfigurationException 
//	 * @throws IOException 
//	 * @throws SAXException 
//	 * @throws FileNotFoundException 
//	 */
//	public static String AddNewNoticesUrl(HttpServletRequest request) throws FileNotFoundException, SAXException, IOException, ParserConfigurationException{
//		String basePath="";
//	  	List<String> configList=SmBaseUtil.initServersList(request.getServletContext());
//			if(configList.size()>=4 && configList.get(3)!=null && configList.get(3).equals("true")){
//			basePath=SmBaseUtil.getWeNewInterFace(request);
//		}else{
//			basePath=TestAddress;
//		}
//		return basePath+"Notices/addphoneNewNotices";
//	};
//	
//	/**
//	 * 增加访问量(新版)
//	 * @throws ParserConfigurationException 
//	 * @throws IOException 
//	 * @throws SAXException 
//	 * @throws FileNotFoundException 
//	 */
//	public static String SendAccessActive(HttpServletRequest request) throws FileNotFoundException, SAXException, IOException, ParserConfigurationException{
//		String basePath="";
//	  	List<String> configList=SmBaseUtil.initServersList(request.getServletContext());
//			if(configList.size()>=4 && configList.get(3)!=null && configList.get(3).equals("true")){
//			basePath=SmBaseUtil.getWeNewInterFace(request);
//		}else{
//			basePath=TestAddress;
//		}
//		return basePath+"AccessActivity/sendAccessActive";
//	};
//	
//	/**
//	 * 
//		 * 
//		 * @Author 作者：马健
//		 * @Phone  联系qq：1039510196
//		 * @CreateTime 创建时间：2017年9月16日 上午8:21:36 
//		 * @Details 金钱打赏接口
//	 */
//	public static String MoneyReward(HttpServletRequest request) throws FileNotFoundException, SAXException, IOException, ParserConfigurationException {
//		String basePath="";
//	  	List<String> configList=SmBaseUtil.initServersList(request.getServletContext());
//		if(configList.size()>=4 && configList.get(3)!=null && configList.get(3).equals("true")){
//			basePath=SmBaseUtil.getWeNewInterFace(request);
//		}else{
//			basePath=TestAddress;
//		}
//		return basePath+"Students/makeMoney";
//	};
//	
//	/**
//	 * 
//		 * 
//		 * @Author 作者：马健
//		 * @Phone  联系qq：1039510196
//		 * @CreateTime 创建时间：2017年9月16日 下午5:07:44 
//		 * @Details 对新闻的驳回操作 等等
//	 */
//	public static String MakeNotices(HttpServletRequest request) throws FileNotFoundException, SAXException, IOException, ParserConfigurationException {
//		String basePath="";
//	  	List<String> configList=SmBaseUtil.initServersList(request.getServletContext());
//		if(configList.size()>=4 && configList.get(3)!=null && configList.get(3).equals("true")){
//			basePath=SmBaseUtil.getWeNewInterFace(request);
//		}else{
//			basePath=TestAddress;
//		}
//		return basePath+"Notices/deleteNotices";
//	};
//	
//	/**
//	 * 
//		 * 
//		 * @Author 作者：马健
//		 * @Phone  联系qq：1039510196
//		 * @CreateTime 创建时间：2017年9月16日 下午8:51:52 
//		 * @Details 社长点评
//	 */
//	public static String CaptainComment(HttpServletRequest request) throws FileNotFoundException, SAXException, IOException, ParserConfigurationException {
//		String basePath="";
//	  	List<String> configList=SmBaseUtil.initServersList(request.getServletContext());
//		if(configList.size()>=4 && configList.get(3)!=null && configList.get(3).equals("true")){
//			basePath=SmBaseUtil.getWeNewInterFace(request);
//		}else{
//			basePath=TestAddress;
//		}
//		return basePath+"Notices/addCaptainComment";
//	};
//	
//	
//	
//	
//	
//	
//}
