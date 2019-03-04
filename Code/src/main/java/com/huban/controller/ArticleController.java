package com.huban.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
import com.alibaba.fastjson.JSONObject;
import com.huban.Utils.ArgumentsUtils;
import com.huban.Utils.Constant;
import com.huban.Utils.ConstantUtils;
import com.huban.Utils.RandomUtil;
import com.huban.Utils.ReturnCodeUtils;
import com.huban.Utils.ReturnMessageUtils;
import com.huban.Utils.PushBaidu.PushUtil;
import com.huban.construct.JournalModel;
import com.huban.construct.LstDeptMemberModel;
import com.huban.construct.LstDeptModel;
import com.huban.construct.LstDeptSave;
import com.huban.construct.TimeDate;
import com.huban.pojo.Book;
import com.huban.pojo.Article;
import com.huban.pojo.New;
import com.huban.pojo.PushDrives;
import com.huban.pojo.PushRecords;
import com.huban.pojo.SaveRead;
import com.huban.pojo.User;
import com.huban.pojo.UserInfo;
import com.huban.publicway.UserWay;
import com.huban.util.BaseUtil;
import com.huban.util.IdWorker;
import com.huban.util.ResultUtil;

/**
 * @ClassName: ArticleController
 * @Description: TODO( 信息 For 家长大课堂数据 )
 * @author Sheryl
 * @date 2018年1月8日 上午11:03:05
 *
 */
@Controller
@RequestMapping("/Article")
public class ArticleController extends BaseController {
	
//	@RequestMapping(value = "/Interface/V3/SearchNews", method = {RequestMethod.POST, RequestMethod.GET})
//	public @ResponseBody Map<String, Object> changeMessageMethod(HttpServletRequest request) throws ParseException {
//		String param;
//		param = (String) request.getAttribute(BaseUtil.paramKey);
//		if (param == null || param.equals("")) {
//			param = request.getParameter(BaseUtil.paramKey);
//		}
//		JSONObject jsonObject = JSON.parseObject(param);
//		String sSchool = jsonObject.getString("school");
//		String sGrade = jsonObject.getString("grade");
//		String sClass = jsonObject.getString("class");
//		String sUserId = jsonObject.getString("userId");
//		
//		long lUserId = Long.parseLong(sUserId);
//		UserInfo pjUserInfo = new UserInfo();
//		pjUserInfo.setUserinfoUserid(lUserId);
//		if (sSchool != null && !"".equals(sSchool)) {
//			pjUserInfo.setUserinfoAddress(sSchool);
//		}
//		if (sGrade != null && !"".equals(sGrade)) {
//			pjUserInfo.setUserinfoClass(Integer.parseInt(sClass));
//		}
//		if (sClass != null && !"".equals(sClass)) {
//			pjUserInfo.setUserinfoGrade(Integer.parseInt(sGrade));
//		}
//		userInfoService.UpdateBindingMessage(pjUserInfo);
//		
//		return ResultUtil.sharedInstance().TrueData(null, ReturnMessageUtils.trueChangePwdMessageKey, ReturnCodeUtils.Code.OK.getCode());
//	}
	
	/**
	 * @Title: SearchArticlesMethod
	 * @Description: TODO( 查询信息 For 家长大课堂 )
	 * @param userId
	 * @param type
	 * @param belong
	 * @param page
	 * @param size
	 * @param request
	 * @return  参数
	 * @return Map<String,Object>  返回类型
	 * @throws
	 */
	@RequestMapping(value = "/Interface/V3/SearchArticles", method = {RequestMethod.GET })
	public @ResponseBody Map<String, Object> SearchArticlesMethod(@RequestParam(required=false) Long userId, @RequestParam(required=false) Integer type,@RequestParam(required=false) Integer belong, @RequestParam(required=false) int page, @RequestParam(required=false) int size, HttpServletRequest request) {
		Map<String, Object> mapQuery = new HashMap<String, Object>();
		mapQuery.put(ArgumentsUtils.sStartKey, size * (page - 1));
		mapQuery.put(ArgumentsUtils.sSizeKey, size);
		mapQuery.put(ArgumentsUtils.sUserIDKey, userId);
		mapQuery.put(ArgumentsUtils.sTypeKey, 5);
		mapQuery.put(Article.COLUMN_Article_Type, type);
		mapQuery.put(Article.COLUMN_Article_Belong, belong);
		mapQuery.put(Article.COLUMN_Article_Delete, 88);
		List<Article> LstArticles = articleService.FindArticlesByCondition(mapQuery);
		return ResultUtil.sharedInstance().TrueData(LstArticles, "获取成功", ReturnCodeUtils.Code.OK.getCode());
	}
	
	
	@RequestMapping(value = "/Interface/V3/ReadCookie", method = {RequestMethod.POST })
	public @ResponseBody Map<String, Object> ReadCookieMethod(@RequestParam(required=false) String userId, @RequestParam(required=false) Integer type,@RequestParam(required=false) Integer belongId, HttpServletRequest request) {
		Map<String, Object> mapQuery = new HashMap<String, Object>();
		mapQuery.put(ArgumentsUtils.sTypeKey, 5);
		mapQuery.put(Article.COLUMN_Article_Type, type);
		mapQuery.put(Article.COLUMN_Article_Belong, belongId);
		mapQuery.put(Article.COLUMN_Article_Delete, 88);
		List<Article> LstArticles = articleService.FindArticlesByCondition(mapQuery);
		return ResultUtil.sharedInstance().TrueData(LstArticles, "获取成功", ReturnCodeUtils.Code.OK.getCode());
	}
	
//	/**
//	 * 推送读书 多学生
//	 */
//	@RequestMapping(value = "/Interface/V2/SponsorRead", method = { RequestMethod.POST })
//	public @ResponseBody Map<String, Object> SponsorReadMethod(HttpServletRequest request) {
//		String param;
//		param = (String) request.getAttribute(BaseUtil.paramKey);
//		if (param == null || param.equals("")) {
//			param = request.getParameter(BaseUtil.paramKey);
//		}
//		JSONObject jsonObject = JSON.parseObject(param);
//		User pjUser = JSON.parseObject(jsonObject.getString(User.sUserClass), User.class);
//		Book pjBook = JSON.parseObject(jsonObject.getString(Book.sBookClass), Book.class);
//		String sPointString = ConstantUtils.sReadBank_Teacher_Recommend;
//		if (pjBook != null && pjBook.getBookName() != null && pjUser.getUserName() != null) {
//			sPointString = ""+"支行指导老师"+pjUser.getUserName()+"向您推荐了"+pjBook.getBookName();
//		}
//		JSONObject jsobjTemp = new JSONObject();
//        jsobjTemp.put("BookId",pjBook.getBookId());
//		String[] pjStudentId = new String[]{jsonObject.getString(User.sUserIDClass)};
//		for (int i = 0; i < pjStudentId.length; i++) {
//			//推送消息增加
//			for (int j = 0; j < pjStudentId.length; j++) {//循环推送 增加消息
//				  JSONObject Childjsobj=new JSONObject();
//                  Childjsobj.put("Status", "RecommendBook");
//                  Childjsobj.put("Type", "AppView");
//                  Childjsobj.put("Url", Constant.getProjectBaseUrl(request));
//                  Childjsobj.put("Data", jsobjTemp);
//                  PushUtil.sendPushInfo(ConstantUtils.sReadBank_Teacher_Recommend, sPointString,Childjsobj,pjUser.getUserId(),Long.parseLong(pjStudentId[j]), 1, true, ConstantUtils.iReadBank_PushType_BookValue, pjBook.getBookId(), pushDrivesService,devicesService,pushRecordsService);
//			}
//		}
//		return ResultUtil.sharedInstance().TrueData(null, ReturnMessageUtils.trueReturnMessageKey, ReturnCodeUtils.Code.OK.getCode());
//	}
//	
//	/**
//	 * 教师版指导支行列表
//	 */
//	@RequestMapping(value = "/Interface/V2/DeptList", method = { RequestMethod.GET })
//	public @ResponseBody Map<String, Object> DeptListMethod(@RequestParam String userId, HttpServletRequest request) {
//		Map<String, Object> mapQuery = new HashMap<String, Object>();
//		Map<String, Object> mapResult = new HashMap<String, Object>();
//		mapQuery.put(ArgumentsUtils.sIDKey, Long.parseLong(userId));
//		mapQuery.put(ArgumentsUtils.sRandomKey, RandomUtil.generateString(6));
//		mapResult = userInfoService.QuerySomeMessage(mapQuery);
//		String sDeptId = mapResult.get("deptId").toString();
//		String[] arrDeptId = sDeptId.split(ConstantUtils.sSeparator);
//		List<LstDeptModel> lstDeptModels = new ArrayList<LstDeptModel>();
//		if (sDeptId != "0" && !"0".equals(sDeptId) && sDeptId != ConstantUtils.sSeparator && !ConstantUtils.sSeparator.equals(sDeptId)) {
//			for (int i = 0; i < arrDeptId.length; i++) {
//				mapQuery.clear();
//				mapQuery.put(ArgumentsUtils.sDeptIDKey, arrDeptId[i]);
//				lstDeptModels.add(departmentService.QueryDeptList(mapQuery).get(0));
//			}
//		}
//		return ResultUtil.sharedInstance().TrueData(lstDeptModels, "获取成功", ReturnCodeUtils.Code.OK.getCode());
//	}
//	
//	
//	/**
//	 * 教师版退出支行
//	 */
//	@RequestMapping(value = "/Interface/V2/QuitDept", method = { RequestMethod.POST })
//	public @ResponseBody Map<String, Object> QuitDeptMethod(HttpServletRequest request) {
//		Map<String, Object> mapQuery = new HashMap<String, Object>();
//		Map<String, Object> mapResult = new HashMap<String, Object>();
//		String param;
//		param = (String) request.getAttribute(BaseUtil.paramKey);
//		if (param == null || param.equals("")) {
//			param = request.getParameter(BaseUtil.paramKey);
//		}
//		JSONObject jsonObject = JSON.parseObject(param);
//		String sUserId = jsonObject.getString("userId");
//		String sDeptId = jsonObject.getString("deptId");
//		
//		mapQuery.put(ArgumentsUtils.sIDKey, Long.parseLong(sUserId));
//		mapQuery.put(ArgumentsUtils.sRandomKey, RandomUtil.generateString(6));
//		mapResult = userInfoService.QuerySomeMessage(mapQuery);
//		String sDeptIds = mapResult.get("deptId").toString();
//		String[] arrDeptId = sDeptIds.split(ConstantUtils.sSeparator);
//		
//		sDeptIds = "";
//		for (int i = 0; i < arrDeptId.length; i++) {
//			if (!arrDeptId[i].equals(sDeptId)) {
//				sDeptIds += arrDeptId[i] + ConstantUtils.sSeparator;
//			}
//		}
//		
//		arrDeptId = sDeptIds.split(ConstantUtils.sSeparator);
//		sDeptIds = "";
//		for (int i = 0; i < arrDeptId.length - 1; i++) {
//			sDeptIds += arrDeptId[i] + ConstantUtils.sSeparator;
//		}
//		sDeptIds += arrDeptId[arrDeptId.length - 1];
//		
//		UserInfo pjUserInfo = new UserInfo();
//		pjUserInfo.setUserinfoUserid(Long.parseLong(sUserId));
//		if (sDeptIds == null || sDeptIds == "" || "".equals(sDeptIds)) {
//			pjUserInfo.setUserinfoBelongdept("0");
//		}else {
//			pjUserInfo.setUserinfoBelongdept(sDeptIds);
//		}
//		userInfoService.UpdateBindingMessage(pjUserInfo);
//		
//		return ResultUtil.sharedInstance().TrueData(null, "退出成功", ReturnCodeUtils.Code.OK.getCode());
//	}
//	
//	
//	/**
//	 * 获取某些信息
//	 */
//	@RequestMapping(value = "/Interface/V2/ShowMessage", method = {RequestMethod.GET})
//	public 
//	@ResponseBody Map<String, Object> ShowMessageMethod(@RequestParam String userId, int type, int page, int size, @RequestParam(required=false) String date, HttpServletRequest request) {
//		Map<String, Object> mapQuery = new HashMap<String, Object>();
//		Map<String, Object> mapResult = new HashMap<String, Object>();
//		long lUserId = Long.parseLong(userId);
//		mapQuery.put(ArgumentsUtils.sIDKey, lUserId);
////		String sUserNumber = userInfoService.QuerySerialNumber(mapQuery);
////		String[] arrUserNumber = sUserNumber.split(ConstantUtils.sSeparator);
//		mapResult = userInfoService.QuerySomeMessage(mapQuery);
//		mapQuery.clear();
//		mapQuery.put(ArgumentsUtils.sStartKey, size * (page - 1));
//		mapQuery.put(ArgumentsUtils.sSizeKey, size);
//		mapQuery.put(ArgumentsUtils.sSchoolKey, mapResult.get(ArgumentsUtils.sSchoolKey).toString());
//		mapQuery.put(ArgumentsUtils.sGradeKey, Integer.parseInt(mapResult.get(ArgumentsUtils.sGradeKey).toString()));
//		mapQuery.put(ArgumentsUtils.sClassKey, Integer.parseInt(mapResult.get(ArgumentsUtils.sClassKey).toString()));
//		if (type == 1) {//阅读趋势情况，同时显示最新几条阅读记录 date表示分月份
//			int iDate = Integer.parseInt(date);
//			Date time = new Date();//当前时间
//			Date dBefore = new Date();
//			Calendar calendar = Calendar.getInstance(); //得到日历
//			
//			List<TimeDate> timeDates = new ArrayList<TimeDate>();
//			List<LstDeptSave> lstSaveReads = saveReadService.QueryLstSaveReadForAgent(mapQuery);
//			for (int i = 0; i <= iDate+1; i++) {
//				calendar.setTime(time);//把当前时间赋给日历
//				calendar.add(calendar.MONTH, -i);  //设置为前几月
//				dBefore = calendar.getTime();   //得到前几月的时间
//				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //设置时间格式
//				String oldtime = sdf.format(dBefore);    //格式化前几月的时间
//				mapQuery.put(ArgumentsUtils.sRandomKey, RandomUtil.generateString(6));
//				mapQuery.put(ArgumentsUtils.sMonthlysTimeKey, oldtime);
//				long num= saveReadService.QueryReadCountByMonth(mapQuery);
//				TimeDate timeDate = new TimeDate();
//				timeDate.setCount((int) num);
//				timeDate.setTime(dBefore.getMonth()+1);
//				timeDates.add(timeDate);
//			}
//			mapQuery.clear();
//			mapQuery.put("datecount", timeDates);
//			mapQuery.put("lstSaveReads", lstSaveReads);
//			return ResultUtil.sharedInstance().TrueData(mapQuery, "获取成功", ReturnCodeUtils.Code.OK.getCode());
//			
//		}
//		if (type == 2) {//所属的学生每天提交的日志 date表示某天
//			mapQuery.put(ArgumentsUtils.sDayTimeKey, date);
//			List<JournalModel> lstJournalModels = userJournalService.QueryLstJournalModel(mapQuery);
//			return ResultUtil.sharedInstance().TrueData(lstJournalModels, "获取成功", ReturnCodeUtils.Code.OK.getCode());
//		}
//		if (type == 3) {
//			List<LstDeptSave> lstSaveReads = saveReadService.QueryLstSaveReadForAgent(mapQuery);
//			return ResultUtil.sharedInstance().TrueData(lstSaveReads, "获取成功", ReturnCodeUtils.Code.OK.getCode());
//		}
//		return null;
//	}
//	
//	/**
//	 * 推荐书籍之增加书籍
//	 */
//	@RequestMapping(value = "/Interface/V2/Recommend", method = { RequestMethod.POST })
//	public @ResponseBody Map<String, Object> RecommendMethod(HttpServletRequest request) {
//		String param;
//		param = (String) request.getAttribute(BaseUtil.paramKey);
//		if (param == null || param.equals("")) {
//			param = request.getParameter(BaseUtil.paramKey);
//		}
//		JSONObject jsonObject = JSON.parseObject(param);
//		String sUserId =  jsonObject.getString("userId");
//		long lUserId = Long.parseLong(sUserId);
//		String sBookIcon = jsonObject.getString("bookIcon");//书头像
//	String sBookNum = jsonObject.getString("bookNum");//编号
//		String sBookName = jsonObject.getString("bookName");//书名
//		String sBookAuther = jsonObject.getString("bookAuther");//作者
//		String sPulish = jsonObject.getString("bookPulish");//出版社
//		String sBookNumber = jsonObject.getString("bookNumber");//字数
//		String sBookContent = jsonObject.getString("bookContent");//内容/简介
//		String sBookUrl = jsonObject.getString("bookUrl");//书Url
//		String sBookMoney = jsonObject.getString("bookMoney");//花费
//		String sBookType = jsonObject.getString("bookType");//1:人物传记,2:小说,3:童话寓言,4:科普,5:绘本,6:文学名著,7:其他,:,:,:,
//		
//		Book pjBook =new Book();
//		pjBook.setBookId(IdWorker.CreateNewID());
//		pjBook.setBookHeadicon(sBookIcon);
//		if (null == sBookNum || "".equals(sBookNum)) {
//			pjBook.setBookNum(null);
//		}else {
//			pjBook.setBookNum(sBookNum);
//		}
//		pjBook.setBookName(sBookName);
//		pjBook.setBookAuthor(sBookAuther);
//		pjBook.setBookPress(sPulish);
//		pjBook.setBookCount(Float.valueOf(sBookNumber));
//		pjBook.setBookStatus((int) 0);
//		pjBook.setBookComment(sBookContent);//简介/介绍
//		if (sBookUrl == null || "".equals(sBookUrl)) {
//			pjBook.setBookUrl(sBookIcon);
//		}else {
//			pjBook.setBookUrl(sBookUrl);
//		}
//		pjBook.setBookMoney(sBookMoney);
//		pjBook.setBookFamous(Integer.parseInt(sBookType));
//		pjBook.setBookChild(0);
//		pjBook.setBookPublisher(lUserId);
//		bookService.AddNewMessage(pjBook);
//		
//		Map<String, Object> mapReturn = new HashMap<String, Object>();
//		mapReturn.put("bookId", pjBook.getBookId());
//		return ResultUtil.sharedInstance().TrueData(mapReturn, "发布成功", ReturnCodeUtils.Code.OK.getCode());
//	}
	
/*-----------------接口内调用方法集合-----------------*/

}
