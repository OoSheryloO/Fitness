package com.huban.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.hadoop.hbase.protobuf.generated.HBaseProtos.ServerName;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.huban.Utils.ArgumentsUtils;
import com.huban.Utils.ConstantUtils;
import com.huban.Utils.ForumComment;
import com.huban.Utils.RandomUtil;
import com.huban.Utils.ReturnCodeUtils;
import com.huban.Utils.ReturnMessageUtils;
import com.huban.Utils.ReturnParameterUtils;
import com.huban.construct.AreaRetrunModel;
import com.huban.construct.ComShowMore;
import com.huban.construct.FirstComList;
import com.huban.construct.FirstShowMore;
import com.huban.construct.NameAndComment;
import com.huban.construct.PictrueModel;
import com.huban.construct.ShowTaskModel;
import com.huban.construct.TimeAndBookName;
import com.huban.construct.TimeDate;
import com.huban.construct.TotalWealthShowModel;
import com.huban.construct.VideoBookComment;
import com.huban.construct.area;
import com.huban.construct.areapart;
import com.huban.pojo.Article;
import com.huban.pojo.Comment;
import com.huban.pojo.Devices;
import com.huban.pojo.Dict;
import com.huban.pojo.Feedbacks;
import com.huban.pojo.Information;
import com.huban.pojo.Integration;
import com.huban.pojo.Lesson;
import com.huban.pojo.LikeStatus;
import com.huban.pojo.New;
import com.huban.pojo.Payrecords;
import com.huban.pojo.ReadBook;
import com.huban.pojo.Regions;
import com.huban.pojo.User;
import com.huban.pojo.UserJournal;
import com.huban.pojo.UserPlan;
import com.huban.pojo.Version;
import com.huban.pojo.Voice;
import com.huban.util.BaseUtil;
import com.huban.util.IdWorker;
import com.huban.util.ResultUtil;

import scala.annotation.serializable;
import scala.collection.generic.BitOperations.Int;

/**
* <p>Title: PayController</p>
* <p>Description: </p>
* <p>Company: </p>
* @author Administrator
* @date 2017-6-26 下午2:48:15
*/

@Controller
@RequestMapping(value={"/message","/Message"})
public class MessageController extends BaseController{
	
	
	/*
	 * 授权码使用 授权码产生规则 
	 */
	@RequestMapping(value = "/Interface/V3/EmbodyAccreditKey", method = {RequestMethod.POST })
	public @ResponseBody Map<String, Object> EmbodyAccreditKeyMethod(@RequestParam(required=false) Long userId, @RequestParam(required=false) String code,
			@RequestParam(required=false) Integer type, @RequestParam(required=false) String phone, @RequestParam(required=false) Integer page, 
			@RequestParam(required=false) Integer size, @RequestParam(required=false) Long belongId, HttpServletRequest request) {
		Map<String, Object> mapSearch = new HashMap<String, Object>();
//		Map<String, Object> mapResult = new HashMap<String, Object>();
//		boolean flag = false;
		String param;
        param = (String)request.getAttribute(BaseUtil.paramKey);
        if(param==null || param.equals("")){
            param=request.getParameter(BaseUtil.paramKey);
        }
        JSONObject jsonObject=JSON.parseObject(param);
        userId = Long.parseLong(jsonObject.getString("userId"));
        code = jsonObject.getString("code");
		User pjUser = userService.selectUserByUserId(userId);
		mapSearch.put(ArgumentsUtils.sNumberKey, pjUser.getUserPhone());
		mapSearch.put(ArgumentsUtils.sValueKey, code);
		mapSearch.put(ArgumentsUtils.sTypeKey, ConstantUtils.iReadBank_DictType_Authorization_Code);
		mapSearch.put(ArgumentsUtils.sStartKey, 0);
		mapSearch.put(ArgumentsUtils.sSizeKey, 1);
		
		List<Dict> LstDict = dictService.SearchLstDict(mapSearch);
		if (LstDict.size() > 0) {
			Dict pjDict = LstDict.get(0);
			if (pjDict.getDictStatus() == 1) {
				return ResultUtil.sharedInstance().FalseData("该授权码已使用", ReturnCodeUtils.Code.NO.getCode());
			}
			pjDict.setDictValue(code);
			pjDict.setDictType(ConstantUtils.iReadBank_DictType_Authorization_Code);
			pjDict.setDictNumber(Long.parseLong(pjUser.getUserPhone()));
			pjDict.setDictStatus(1);
			int iNum = dictService.ModifyMessage(pjDict);
			if (iNum == 1) {
				return ResultUtil.sharedInstance().TrueData(null, "使用成功", ReturnCodeUtils.Code.OK.getCode());
			}
		}
		return ResultUtil.sharedInstance().FalseData("使用异常", ReturnCodeUtils.Code.NO.getCode());
	}
	
	/*
	 * 检测用户是否已使用授权码   某一授权码使用情况
	 */
	@RequestMapping(value = "/Interface/V3/Detection", method = {RequestMethod.GET })
	public @ResponseBody Map<String, Object> DetectionMethod(@RequestParam(required=false) Long userId, @RequestParam(required=false) String code,
			@RequestParam(required=false) Integer type, @RequestParam(required=false) String phone, @RequestParam(required=false) Integer page, 
			@RequestParam(required=false) Integer size, @RequestParam(required=false) Long belongId, HttpServletRequest request) {
		Map<String, Object> mapSearch = new HashMap<String, Object>();
		Map<String, Object> mapResult = new HashMap<String, Object>();
		boolean flag = false;
		User pjUser = userService.selectUserByUserId(userId);
		mapSearch.put(ArgumentsUtils.sNumberKey, pjUser.getUserPhone());
		mapSearch.put(ArgumentsUtils.sValueKey, code);
		mapSearch.put(ArgumentsUtils.sTypeKey, ConstantUtils.iReadBank_DictType_Authorization_Code);
		mapSearch.put(ArgumentsUtils.sStartKey, 0);
		mapSearch.put(ArgumentsUtils.sSizeKey, 1);
		List<Dict> LstDicts = dictService.SearchLstDict(mapSearch);
		if (LstDicts.size() > 0) {
			if (LstDicts.get(0).getDictStatus() == ConstantUtils.iDefaultOneValue) {
				flag = true;
			}
		}else {
			mapResult.put(ReturnParameterUtils.sStatusValueForExist, false);
		}
		mapResult.put(ReturnParameterUtils.sStatusValueForUse, flag);
		return ResultUtil.sharedInstance().TrueData(mapResult, "获取成功", ReturnCodeUtils.Code.OK.getCode());
	}
	
	/** Sheryl \u65e5\u5fd7
	 * Mac \u7535\u8111\u6570\u636e\u4e22\u5931\u4e4b\u540e \u2191
	 */
	
	/**
	 * 意见反馈
	 */
	@RequestMapping(value="/opinion",method={RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public Map<String, Object> opinion(HttpServletRequest request){
		Map<String, Object> result = new HashMap<String,Object>();
		String param;
        param = (String)request.getAttribute(BaseUtil.paramKey);
        if(param==null || param.equals("")){
            param=request.getParameter(BaseUtil.paramKey);
        }
        JSONObject jsonObject=JSON.parseObject(param);
        String txt = jsonObject.getString("title");
        String userId = jsonObject.getString("userId");
		
		Feedbacks feedback = new Feedbacks();
		//feedback.setFeedbackId(IdWorker.CreateNewID());
		feedback.setFeedbackUserid(Long.parseLong(userId));
		feedback.setFeedbackContent(txt.getBytes());
		feedback.setFeedbackDriveid((long) 0);
		feedback.setFeedbackVersion(0);
		feedback.setFeedbackTypeid((byte) 0);
		try {
		feedbacksService.addFeedback(feedback);
		result.put("message", "操作成功");
		result.put("status", true);
		} catch (Exception e) {
			// TODO: handle exception
			result.put("message", "数据异常，请联系客服");
			result.put("status", false);
		}
		return result;
	}
	
	
	/**
	 * APP设备信息收集
	 */
	@RequestMapping(value="/appmessage",method={RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public Map<String, Object> appMessage(HttpServletRequest request){
		Map<String, Object> result = new HashMap<String,Object>();
//		Map<String, Object> mapQuery = new HashMap<String,Object>();
		String param;
        param = (String)request.getAttribute(BaseUtil.paramKey);
        if(param==null || param.equals("")){
            param=request.getParameter(BaseUtil.paramKey);
        }
        JSONObject jsonObject=JSON.parseObject(param);
        
        String deviceUserId = jsonObject.getString("userId");
        String deviceTypeID = jsonObject.getString("typeId");
        String UDID = jsonObject.getString("UDID");
        String deviceName = jsonObject.getString("deviceName");
        String model = jsonObject.getString("Model");
        String System = jsonObject.getString("System");
        
        long lUserId = Long.parseLong(deviceUserId);
        long lTypeId = Long.parseLong(deviceTypeID);
        
//        mapQuery.put(ArgumentsUtils.sUserIDKey, lUserId);
//        mapQuery.put(ArgumentsUtils.sRandomKey, RandomUtil.generateString(6));
        
        Devices pjDevices = new Devices();
        pjDevices.setDeviceUserid(lUserId);
		pjDevices.setDeviceTypeid(lTypeId);
		pjDevices.setDeviceUdid(UDID);
		pjDevices.setDeviceName(deviceName);
		pjDevices.setDeviceModel(model);
		pjDevices.setDeviceSystem(System);
		pjDevices.setDeviceStatus((byte) 1);
	    devicesService.addAPPmessage(pjDevices);
	    result.put("driveId", pjDevices.getDeviceId());
		result.put("message", "添加成功");
		result.put("status", true);
		return result;
	}
	
	
	/**
	 * APP版本信息
	 */
	@RequestMapping(value="/appversion",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> appVersion(@RequestParam Integer bulid,String type,HttpServletRequest request){
		Map<String, Object> result = new HashMap<String,Object>();
		Map<String, Object> map = new HashMap<String, Object>();
//		String param;
//        param = (String)request.getAttribute(BaseUtil.paramKey);
//        if(param==null || param.equals("")){
//            param=request.getParameter(BaseUtil.paramKey);
//        }
//        JSONObject jsonObject=JSON.parseObject(param);
//		if (null == bulid || "".equals(bulid)) {
//			bulid = jsonObject.getString("bulid");
//		}
//		if (null == type || "".equals(type)) {
//			type = jsonObject.getString("type");
//		}
		map.put("type", Integer.parseInt(type));
		Version version = new Version();
//		try {
		if ("1" == type || "1".equals(type)) {
			version = versionService.MAX(map);
			boolean flag = false;
			if (bulid == version.getVersionBuild() || bulid.equals(version.getVersionBuild())) {
				if (1 == version.getVersionIspublish()) {
					flag = true;
				}
			}else {
				map.put("bulid", bulid);
				Integer ispublish = versionService.querypublish(map);
				if (null != ispublish && 1 == ispublish) {
					flag = true;
				}	
			}
			result.put("ispublish", flag);
		}
		if ("0" == type || "0".equals(type)) {
			version = versionService.MAX(map);
		}
		result.put("version", JSON.toJSON(version));
		result.put("message", "获取成功");
		result.put("status", true);
//		} catch (Exception e) {
//			// TODO: handle exception
//			result.put("status", false);
//			result.put("message", "数据异常，请联系客服");
//			return result;
//		}
		return result;
	}
	
	
	/**
	 * 支付信息
	 */
	@RequestMapping(value="/paymessage",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> payMessage(@RequestParam String page,String size,String userId,HttpServletRequest request){
		Map<String, Object> result = new HashMap<String,Object>();
		int pageCount=Integer.parseInt(size);
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("start", pageCount*(Integer.parseInt(page)-1));
		map.put("size", pageCount);
		map.put("userId", Long.parseLong(userId));
		try {
		List<Payrecords> payrecords = payrecordsService.payMessage(map);
		
		result.put("payMessage", JSON.toJSON(payrecords));
		result.put("message", "获取成功");
		result.put("status", true);} catch (Exception e) {
			// TODO: handle exception
			result.put("status", false);
			result.put("message", "数据异常，请联系客服");
			return result;
		}
		return result;
	}
	
	/**
	 * 商城banner
	 * @param type 广告位: 0:首页 1:商城 2:论坛帖图 3:登录启动 4:启动弹窗 5:视频
	 */
	@RequestMapping(value="/banner",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> banner(@RequestParam String type,HttpServletRequest request){
		Map<String, Object> result = new HashMap<String,Object>();
		try {
		List<String> LstPictureUrl = pictureService.selectBanner(Long.parseLong(type));
		
		result.put("pictures", JSON.toJSON(LstPictureUrl));
		result.put("message", "获取成功");
		result.put("status", true);
		} catch (Exception e) {
			// TODO: handle exception
			result.put("status", false);
			result.put("message", "数据异常，请联系客服");
			return result;
		}
		return result;
	}
	
	/**
	 * @param type 广告位: 0:首页 1:商城 2:论坛帖图 3:登录启动 4:启动弹窗 5:视频
	 */
	@RequestMapping(value="/Interface/V2/PictureType",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> bannerMethod(@RequestParam(value="type") String type,HttpServletRequest request){
		Map<String, Object> mapQuery = new HashMap<String,Object>();
		mapQuery.put(ArgumentsUtils.STypeKey, type);
		List<PictrueModel> LstPictureModel = pictureService.SearchBannerByCondition(mapQuery);
		return ResultUtil.sharedInstance().TrueData(LstPictureModel, ReturnMessageUtils.trueReturnMessageKey, ReturnCodeUtils.Code.OK.getCode());
	}
	
	/**
	 * 评论详情
	 * @param request
	 * */
	@RequestMapping(value="/comments",method={RequestMethod.GET,RequestMethod.POST})
	public 
	@ResponseBody
	Map<String, Object> commentsMethod(@RequestParam String page, String size, String bookId, String videoId,HttpServletRequest request){
		Map<String,Object> result=new HashMap<String, Object>();
		int pageCount=Integer.parseInt(size);
		Map<String, Object> queryMap=new HashMap<String, Object>();
		queryMap.put("start", pageCount*(Integer.parseInt(page)-1));
		queryMap.put("size", pageCount);
		
		List<VideoBookComment> comments =  new ArrayList<VideoBookComment>();
		User u = new User();
		User uu = new User();
		try {
		if (bookId != null && !"".equals(bookId)) {
			queryMap.put("bookId", bookId);
			List<Comment> bookComments = commentService.bookComment(queryMap);
			for (int i = 0; i < bookComments.size(); i++) {
				if (bookComments.get(i).getCommentUserid() != null && !"".equals(bookComments.get(i).getCommentUserid()) && bookComments.get(i).getCommentUserid() != 0) {
					u.setUserId(bookComments.get(i).getCommentUserid());
					uu = userService.selectUser(u);
				}else {
					uu = new User();
				}
				VideoBookComment firstComment = new VideoBookComment();
					firstComment.setUserId(uu.getUserId());
				if (uu.getUserId() == null ||"".equals(uu.getUserId()) || 0 == uu.getUserId() || "0".equals(uu.getUserId())) {
					firstComment.setUserName("游客");
					firstComment.setUserHeadicon(null);
				}else {
					firstComment.setUserName(uu.getUserName());
					firstComment.setUserHeadicon(uu.getUserHeadicon());
				}
				firstComment.setCommentCreatetime(bookComments.get(i).getCommentCreatetime());
				firstComment.setCommentContent(new String(bookComments.get(i).getCommentContent()));
				comments.add(firstComment);
			}
		}
		if (videoId != null && !"".equals(videoId)) {
			queryMap.put("videoId", videoId);
			List<Comment> videoComments = commentService.videocomment(queryMap);
			for (int i = 0; i < videoComments.size(); i++) {
				
				if (videoComments.get(i).getCommentUserid() != null && !"".equals(videoComments.get(i).getCommentUserid())) {
					u.setUserId(videoComments.get(i).getCommentUserid());
					uu = userService.selectUser(u);
				}else {
					uu = new User();
				}
				VideoBookComment firstComment = new VideoBookComment();
					firstComment.setUserId(uu.getUserId());
				if (uu.getUserId() == null ||"".equals(uu.getUserId())) {
					firstComment.setUserName("游客");
					firstComment.setUserHeadicon(null);
				}else {
					firstComment.setUserName(uu.getUserName());
					firstComment.setUserHeadicon(uu.getUserHeadicon());
				}
				firstComment.setUserHeadicon(uu.getUserHeadicon());
				firstComment.setCommentCreatetime(videoComments.get(i).getCommentCreatetime());
				firstComment.setCommentContent(new String(videoComments.get(i).getCommentContent()));
				comments.add(firstComment);
			}
		}
		result.put("comments", JSON.toJSON(comments));
		result.put("status", true);
		} catch (Exception e) {
			// TODO: handle exception
			result.put("status", false);
			result.put("message", "数据异常，请联系客服");
			return result;
		}
		return result;
	}
	
	/**
	 * 发表评论
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/addcomment",method={RequestMethod.POST,RequestMethod.GET})
	public 
	@ResponseBody
	Map<String, Object> addComment(HttpServletRequest request){
		Map<String,Object> result=new HashMap<String, Object>();
		String param;
        param = (String)request.getAttribute(BaseUtil.paramKey);
        if(param==null || param.equals("")){
            param=request.getParameter(BaseUtil.paramKey);
        }
        JSONObject jsonObject=JSON.parseObject(param);
        String userId = jsonObject.getString("userId");
        String bookId = jsonObject.getString("bookId").trim();
        String videoId = jsonObject.getString("videoId").trim();
        String txt = jsonObject.getString("comment");
        Comment comment = new Comment();
        comment.setCommentUserid(null);
        if (null != userId && !"".equals(userId)) {
        	comment.setCommentUserid(Long.parseLong(userId));
		}
        if ( (bookId != null && !"".equals(bookId)) && (videoId != null && !"".equals(videoId)) ) {//都输入了
			result.put("message", "输入有误");
			result.put("status", false);
			return result;
		}
        if ( (bookId == null || "".equals(bookId)) && (videoId == null || "".equals(videoId)) ) {//都没输入
			result.put("message", "再试一下打不死你");
			result.put("status", false);
			return result;
		}
        if ((bookId != null && !"".equals(bookId)) && (videoId == null || "".equals(videoId)) ) {
        	comment.setCommentGoodsid(Long.parseLong(bookId));
        	comment.setCommentType(2);
        	bookService.addcommentcount(Long.parseLong(bookId));
		}
        if ((videoId != null && !"".equals(videoId)) && (bookId == null || "".equals(bookId)) ) {
			comment.setCommentVideoid(Long.parseLong(videoId));
			comment.setCommentType(1);
			videoSerive.addcommentcount(Long.parseLong(videoId));
		}
        try {
        comment.setCommentContent(txt.getBytes());
        commentService.addcomment(comment);
        result.put("message", "评论发表成功");
        result.put("status", true);
        } catch (Exception e) {
			// TODO: handle exception
			result.put("message", "数据异常，请联系客服");
			result.put("status", false);
		}
		return result;
	}
	
	
	/**
	 * 分享
	 */
	@RequestMapping(value="/share",method={RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public Map<String, Object> share(HttpServletRequest request){
		Map<String, Object> result = new HashMap<String,Object>();
		String param;
		param = (String) request.getAttribute(BaseUtil.paramKey);
		if (param == null || "".equals(param)) {
			param = request.getParameter(BaseUtil.paramKey);
		}
		JSONObject jsonObject = JSONObject.parseObject(param);
		String activityId = jsonObject.getString("activityId");
		String videoId = jsonObject.getString("videoId");
		String userId = jsonObject.getString("userId");
		String type = jsonObject.getString("type");
		Map<String, Object> map = new HashMap<String,Object>();
		Integration integration = new Integration();
		try {
		if (type == "0" || "0".equals(type)) {//活动分享
			map.put("tfShare", ConstantUtils.TFshare);
			map.put("activityId", activityId);
			activityService.updateNum(map);
			map.clear();
			map.put("userId", Long.parseLong(userId));
			map.put("userIntegral", ConstantUtils.ActivityShareIntegral);
		//	CommonController.addIntegrationMessage(Long.parseLong(userId), , , StatusUtil.ActivityShare);
			
			integration.setIntegrationNum(new BigDecimal(ConstantUtils.VideoShareIntegral));
			integration.setIntegrationType(1);
			integration.setIntegrationReason(ConstantUtils.ActivityShare);
			result.put("message", "活动分享");
			result.put("Intrgral", JSON.toJSON(ConstantUtils.ActivityShareIntegral));
		}
		if (type == "1" || "1".equals(type)) {//视频分享
			videoSerive.VideoShare(Long.parseLong(videoId));			
			map.put("userId", Long.parseLong(userId));
			map.put("userIntegral", ConstantUtils.VideoShareIntegral);
//			CommonController.addIntegrationMessage(Long.parseLong(userId), , 2, );
			integration.setIntegrationNum(new BigDecimal(ConstantUtils.ActivityShareIntegral));
			integration.setIntegrationType(2);
			integration.setIntegrationReason(ConstantUtils.VideoShare);
			
			result.put("message", "视频分享");
			result.put("Intrgral", JSON.toJSON(ConstantUtils.VideoShareIntegral));
		}
		integration.setIntegrationId(IdWorker.CreateNewID());
		integration.setIntegrationStatus(0);
		integration.setIntegrationSrcid((long) 1);//0:支出 1:收入
		integration.setIntegrationStudentid(Long.parseLong(userId));
		integrationService.addmessage(integration);//积分记录增加
		userService.addIntegral(map);//积分增加
		
		result.put("status", true);
		} catch (Exception e) {
			// TODO: handle exception
			result.put("message", "数据异常，请联系客服");
			result.put("status", false);
		}
		return result;
	}
	
	
	/**
	 * 阅读账单分月份
	 */
	@RequestMapping(value="/readmessage",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> readmessage(@RequestParam String userId, String type,HttpServletRequest request){
		Map<String, Object> result = new HashMap<String,Object>();
//		String param;
//		param = (String) request.getAttribute(BaseUtil.paramKey);
//		if (param == null || "".equals(param)) {
//			param = request.getParameter(BaseUtil.paramKey);
//		}
//		JSONObject jsonObject = JSONObject.parseObject(param);
//		String userId = jsonObject.getString("userId");
//		String type = jsonObject.getString("type");
		Map<String, Object> map = new HashMap<String,Object>();
		
		Date time = new Date();//当前时间
		Date dBefore = new Date();
		Calendar calendar = Calendar.getInstance(); //得到日历
		
		try {
		List<TimeDate> timeDates = new ArrayList<TimeDate>();
		for (int i = 0; i <= Integer.parseInt(type)+1; i++) {
			calendar.setTime(time);//把当前时间赋给日历
			calendar.add(calendar.MONTH, -i);  //设置为前几月
			dBefore = calendar.getTime();   //得到前几月的时间
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //设置时间格式
			String oldtime = sdf.format(dBefore);    //格式化前几月的时间
			map.put("time", oldtime);
			map.put("userId", Long.parseLong(userId));
			int num = dateService.readbillcount(map);
			TimeDate timeDate = new TimeDate();
			timeDate.setCount(num);
			timeDate.setTime(dBefore.getMonth()+1);
			timeDates.add(timeDate);
		}
		result.put("date", JSON.toJSON(timeDates));		
		result.put("message", "获取成功");
		result.put("status", true);
		} catch (Exception e) {
			// TODO: handle exception
			result.put("message", "数据异常，请联系客服");
			result.put("status", false);
		}
		return result;
	}
	
	
	/**
	 * 阅读账单一个星期以内
	 */
	@RequestMapping(value="/readweek",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> readweek(@RequestParam String userId, String page, String size, HttpServletRequest request){
		Map<String, Object> result = new HashMap<String,Object>();
		Map<String, Object> map = new HashMap<String,Object>();
		int pageCount=Integer.parseInt(size);
		map.put("userId", Long.parseLong(userId));
		map.put("start", (Integer.parseInt(page)-1)*pageCount);
		map.put("size", pageCount);
		try {
		List<com.huban.pojo.Date> book = dateService.readbill(map);
		List<TimeAndBookName> timeAndnames =  new ArrayList<TimeAndBookName>();
		for (int i = 0; i < book.size(); i++) {
			TimeAndBookName timeAndname = new TimeAndBookName(); 
			String name = bookService.ShowBookName(book.get(i).getDateBelongid());
			timeAndname.setTime(book.get(i).getDateCreatetime());
			timeAndname.setBookname(name);
			timeAndnames.add(timeAndname);
		}
		result.put("timeandname", JSON.toJSON(timeAndnames));
		result.put("message", "获取成功");
		result.put("status", true);
		} catch (Exception e) {
			// TODO: handle exception
			result.put("message", "数据异常，请联系客服");
			result.put("status", false);
		}
		return result;
	}
	
	/**
	 * 阅读账单某天
	 */
	@RequestMapping(value="/readmore",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> readmore(@RequestParam String userId, String page, String size, String date,HttpServletRequest request){
		Map<String, Object> result = new HashMap<String,Object>();
		Map<String, Object> map = new HashMap<String,Object>();
		int pageCount=Integer.parseInt(size);
		map.put("userId", Long.parseLong(userId));
		map.put("start", (Integer.parseInt(page)-1)*pageCount);
		map.put("size", pageCount);
		//SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd"); //设置时间格式
		//String oldtime = sdf.format(date);
		map.put("time", date);
		try {
		List<com.huban.pojo.Date> dates = dateService.readmore(map);
		List<TimeAndBookName> timeAndBookNames = new ArrayList<TimeAndBookName>();
		for (int i = 0; i < dates.size(); i++) {
			TimeAndBookName timeAndBookName  =  new TimeAndBookName();
			timeAndBookName.setTime(dates.get(i).getDateCreatetime());
			String name = bookService.ShowBookName(dates.get(i).getDateBelongid());
			timeAndBookName.setBookname(name);
			timeAndBookNames.add(timeAndBookName);
		}
		result.put("timeandname", JSON.toJSON(timeAndBookNames));
		result.put("message", "获取成功");
		result.put("status", true);
		} catch (Exception e) {
			// TODO: handle exception
			result.put("message", "数据异常，请联系客服");
			result.put("status", false);
		}
		return result;
	}
	/**
	 * 阅读账单所有
	 */
	@RequestMapping(value="/allread",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> allread(@RequestParam String userId, String page, String size,HttpServletRequest request){
		Map<String, Object> result = new HashMap<String,Object>();
		Map<String, Object> map = new HashMap<String,Object>();
		int pageCount=Integer.parseInt(size);
		map.put("userId", Long.parseLong(userId));
		map.put("start", (Integer.parseInt(page)-1)*pageCount);
		map.put("size", pageCount);
		//SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd"); //设置时间格式
		//String oldtime = sdf.format(date);
		//map.put("time", date);
		try {
		List<com.huban.pojo.Date> dates = dateService.allread(map);
		List<TimeAndBookName> timeAndBookNames = new ArrayList<TimeAndBookName>();
		for (int i = 0; i < dates.size(); i++) {
			TimeAndBookName timeAndBookName  =  new TimeAndBookName();
			timeAndBookName.setTime(dates.get(i).getDateCreatetime());
			String name = bookService.ShowBookName(dates.get(i).getDateBelongid());
			timeAndBookName.setBookname(name);
			timeAndBookNames.add(timeAndBookName);
		}
		result.put("timeandname", JSON.toJSON(timeAndBookNames));
		result.put("message", "获取成功");
		result.put("status", true);
		} catch (Exception e) {
			// TODO: handle exception
			result.put("message", "数据异常，请联系客服");
			result.put("status", false);
		}
		return result;
	}
	
	/**
	 * 阅读账单百分比
	 */
	@RequestMapping(value="/readpoint",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> readpoint(@RequestParam String userId,HttpServletRequest request){
		Map<String, Object> result = new HashMap<String,Object>();
		Map<String, Object> map = new HashMap<String,Object>();
		Date time = new Date();//当前时间
		Date dBefore = new Date();
		Calendar calendar = Calendar.getInstance(); //得到日历
		
		calendar.setTime(time);//把当前时间赋给日历
		calendar.add(calendar.MONTH, -1);  //设置为前1月
		dBefore = calendar.getTime();   //得到前1月的时间
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //设置时间格式
		String oldtime = sdf.format(dBefore);    //格式化前1月的时间

		try {
		map.put("time", time);
		map.put("userId", Long.parseLong(userId));
		int nowcount = dateService.readbillcount(map);
		map.put("time", oldtime);
		int oldcount = dateService.readbillcount(map);
		int num = 0;
		String point = "";
		num = nowcount - oldcount;
		if (0 == oldcount) {
			if (nowcount - oldcount > oldcount) {
				point = "100.00%";
			}
//			point = nowcount * 100 +".00%";
		}else {
			if (num <= 0) {
				point = "0.00%";
			}else {
				if (nowcount - oldcount > oldcount) {
					point = "100.00%";
				}else {
					point = myPercent(num,oldcount);
				}
			}
		}
				
		result.put("point", point);
		result.put("nowread", JSON.toJSON(nowcount));
		result.put("message", "获取成功");
		result.put("status", true);
		} catch (Exception e) {
			// TODO: handle exception
			result.put("message", "数据异常，请联系客服");
			result.put("status", false);
		}
		return result;
	}
	
	@RequestMapping(value="/integral",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> integral(@RequestParam String userId,HttpServletRequest request){
		Map<String, Object> result = new HashMap<String,Object>();
		try {
		int integral = userService.integral(Long.parseLong(userId)); 
		result.put("integral", JSON.toJSON(integral));
		result.put("message", "获取成功");
		result.put("status", true);
		} catch (Exception e) {
			// TODO: handle exception
			result.put("message", "数据异常，请联系客服");
			result.put("status", false);
		}
		return result;
	}
	
	/**
	 * 评测
	 * @param userId
	 * @param request
	 * @return
	 */
	
	@RequestMapping(value="/measurelevel",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> measure(@RequestParam String userId,String page,String size ,HttpServletRequest request){
		Map<String, Object> result = new HashMap<String,Object>();
		Map<String, Object> map = new HashMap<String,Object>();
		try {
		int integral = userService.integral(Long.parseLong(userId));
		//设置为已设置过user_status = 7
		userService.changestatus(Long.parseLong(userId));
		int type = 1;String level = "";
		List<Dict> dicts = dictService.querylevel(type);
		for (int i = 0; i < dicts.size(); i++) {
			if (integral > dicts.get(dicts.size()-1).getDictNumber()) {
				level = dicts.get(dicts.size()-1).getDictValue();
				i = dicts.size();//控制跳出循环
			}else {
				if (integral <= dicts.get(i).getDictNumber()) {
					level = dicts.get(i).getDictValue();
					map.put("level", i+1);
					map.put("userId", Long.parseLong(userId));
					userService.updatelevel(map);
					i = dicts.size();//控制跳出循环
				}
			}
		}
		result.put("level", JSON.toJSON(level));
		result.put("message", "获取成功");
		result.put("status", true);
		return result;
		
		} catch (Exception e) {
			// TODO: handle exception
			result.put("message", "数据异常，请联系客服或许管理员");
			result.put("status", false);
			return result;
		}
	}
	
	/**
	 * 地区检索
	 */
	@RequestMapping(value="/regions",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> regions(@RequestParam /*String page,String size,*/String typeId, HttpServletRequest request){
		Map<String, Object> result = new HashMap<String,Object>();
//		boolean flag = false;
		int typeid = dictService.querycount();
		if (Integer.parseInt(typeId) < typeid) {
//			flag = true;
			try {
			Map<String, Object> map = new HashMap<String, Object>();
//			int pageCount=Integer.parseInt(size);
//			map.put("start", (Integer.parseInt(page)-1) * pageCount);
//			map.put("size", pageCount);
			List<area> oneAreas = new ArrayList<area>();
			List<Regions> firstRegions = regionsService.firstLevel(map);		
			for (int i = 0; i < firstRegions.size(); i++) {
				area firstarea = new area();
				firstarea.setRegionsId(firstRegions.get(i).getRegionId());
				firstarea.setRegionsParentId(firstRegions.get(i).getRegionParentid());
				firstarea.setRegions(firstRegions.get(i).getRegionName());
				List<Regions> secondRegions = regionsService.secondLevel(firstRegions.get(i).getRegionId());
				List<area> twoAreas = new ArrayList<area>();
				for (int j = 0; j < secondRegions.size(); j++) {
					area secondarea = new area();
					secondarea.setRegionsId(secondRegions.get(j).getRegionId());
					secondarea.setRegionsParentId(secondRegions.get(j).getRegionParentid());
					secondarea.setRegions(secondRegions.get(j).getRegionName());
					List<Regions> thirdRegions = regionsService.thirdLevel(secondRegions.get(j).getRegionId());
					List<area> threeAreas = new ArrayList<area>();
					for (int k = 0; k < thirdRegions.size(); k++) {
						area thirdarea = new area();
						thirdarea.setRegionsId(thirdRegions.get(k).getRegionId());
						thirdarea.setRegionsParentId(thirdRegions.get(k).getRegionParentid());
						thirdarea.setRegions(thirdRegions.get(k).getRegionName());
						threeAreas.add(thirdarea);
					}
					secondarea.setAreas(threeAreas);
					twoAreas.add(secondarea);
				}
				firstarea.setAreas(twoAreas);
				oneAreas.add(firstarea);
			}
			result.put("regions", JSON.toJSON(oneAreas));
			
			} catch (Exception e) {
				// TODO: handle exception
				result.put("message", "数据请求失败");
				result.put("status", false);
				return result;
			}
		}
		result.put("typeId", typeid);
//		result.put("tfupdate", flag);
		result.put("message", "获取成功");
		result.put("status", true);
		return result;
		
//		Map<String, Object> result = new HashMap<String,Object>();
//		boolean flag = false;
//		int num = dictService.querycount();
//		if (num == 1) {
//			flag = true;
//		}
//		result.put("tfupdate", flag);
//		
//		
//		result.put("message", "获取成功");
//		result.put("status", true);
//		return result;
	}
	
	/**
	 * 地区分级检索(NO 版本号)
	 *type:1一级检索 2二级检索 3 三级检索
	 */
	@RequestMapping(value="/Interface/V1/Regions",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> tfregions(@RequestParam String regionId, int type, HttpServletRequest request){
		Map<String, Object> result = new HashMap<String,Object>();
		List<areapart> oneAreas = new ArrayList<areapart>();
		List<Regions> Regions = new ArrayList<Regions>();
//		int iType = Integer.parseInt(type);
		try {
		if ( 1 == type || "1".equals(type)) {
			Map<String, Object> map = new HashMap<String, Object>();
//			int pageCount=Integer.parseInt(size);
//			map.put("start", (Integer.parseInt(page)-1) * pageCount);
//			map.put("size", pageCount);
			Regions = regionsService.firstLevel(map);
			for (int i = 0; i < Regions.size(); i++) {
				areapart aa = new areapart();
				aa.setRegionsId(Regions.get(i).getRegionId());
				aa.setRegionsParentId(Regions.get(i).getRegionParentid());
				aa.setRegions(Regions.get(i).getRegionName());
				oneAreas.add(aa);
			}
		}
		if ( 2 == type || "2".equals(type)) {
			Regions = regionsService.secondLevel(Long.parseLong(regionId));
			for (int i = 0; i < Regions.size(); i++) {
				areapart aa = new areapart();
				aa.setRegionsId(Regions.get(i).getRegionId());
				aa.setRegionsParentId(Regions.get(i).getRegionParentid());
				aa.setRegions(Regions.get(i).getRegionName());
				oneAreas.add(aa);
			}
		}
		if ( 3 == type || "3".equals(type) || 4 == type || "4".equals(type)) {
			Regions = regionsService.thirdLevel(Long.parseLong(regionId));
			for (int i = 0; i < Regions.size(); i++) {
				areapart aa = new areapart();
				aa.setRegionsId(Regions.get(i).getRegionId());
				aa.setRegionsParentId(Regions.get(i).getRegionParentid());
				aa.setRegions(Regions.get(i).getRegionName());
				oneAreas.add(aa);
			}
		}
		result.put("message", "获取成功");
		result.put("regions", JSON.toJSON(oneAreas));
		result.put("status", true);
		} catch (Exception e) {
			// TODO: handle exception
			result.put("message", "数据异常，请联系客服");
			result.put("status", false);
		}
		return result;		
	}
	
	/**
	 * 打赏人列表详情
	 * @param request
	 * */
	@RequestMapping(value="/Interface/V1/getreward",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> rewardmethod(@RequestParam String Id, String type, HttpServletRequest request){
		Map<String, Object> result = new HashMap<String,Object>();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("Id", Long.parseLong(Id));
		if ("0" == type || "0".equals(type) || 0 == Integer.valueOf(type)) {//视频
			map.put("typeId", (int)0);
		}
		if ("1" == type || "1".equals(type) || 1 == Integer.valueOf(type)) {//帖子
			map.put("typeId", (int)1);
		}
		try {
		List<Long> useridlist = rewardService.FindUserIdByRId(map);
		List<String> icons = new ArrayList<String>();
		for (int i = 0; i < useridlist.size(); i++) {
			String icon = userService.selecticon(useridlist.get(i));
			icons.add(icon);
		}
		result.put("userIcon", JSON.toJSON(icons));
		result.put("message", "获取成功");
		result.put("status", true);
		} catch (Exception e) {
			// TODO: handle exception
			result.put("message", "数据异常，请联系客服");
			result.put("status", false);
		}
		return result;
	}
	
	/*--------------------------V2---------------------------*/
	/**
	 * 地区分级检索(NO 版本号) 有地区编码
	 *type:1一级检索 2二级检索 3 三级检索
	 */
	@RequestMapping(value="/Interface/V2/ChooseBranch",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> ChooseBranchMethod(@RequestParam(required=false) Integer regionId,@RequestParam Integer level, HttpServletRequest request){
		Map<String, Object> mapQuery = new HashMap<String,Object>();
		mapQuery.put(ArgumentsUtils.sArea_LevelKey, level);
		if (request.getParameter("regionId") != null || request.getAttribute("regionId") != null) {
			mapQuery.put(ArgumentsUtils.sArea_ParentId, regionId);
		}
		
		List<AreaRetrunModel> lstAreaRetrunModels = areaService.QuerySomeMessage(mapQuery);
		return ResultUtil.sharedInstance().TrueData(lstAreaRetrunModels, "获取成功", ReturnCodeUtils.Code.OK.getCode());		
	}
	
	/** V2.1 地区检索 全部学校 
	 *type:1一级检索 2二级检索 3 三级检索
	 */
	@RequestMapping(value="/Interface/V2/SearchRegion",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> SearchRegionMethod(@RequestParam(required=false) Integer regionId, @RequestParam(required=false) Integer level, HttpServletRequest request){
		Map<String, Object> mapQuery = new HashMap<String,Object>();
		if (regionId != null) {
			mapQuery.put(ArgumentsUtils.sArea_ParentId, regionId);
		}
		List<areapart> lstAreaparts = new ArrayList<areapart>();
		if (level <= 3) {
			mapQuery.put(ArgumentsUtils.sArea_LevelKey, level);
			lstAreaparts = cityService.QuerySomeMessage(mapQuery);
		}else {
			lstAreaparts = schoolService.QuerySomeMessage(mapQuery);
		}
		return ResultUtil.sharedInstance().TrueData(lstAreaparts, "获取成功", ReturnCodeUtils.Code.OK.getCode());		
	}
	
	/**
	 * 智慧日志及修改日志显示
	 */
	@RequestMapping(value="/Interface/V2/ShowJournal",method={RequestMethod.GET})
	@ResponseBody
	public Map<String, Object> ShowJournalMethod(@RequestParam String userId, String page, String size,@RequestParam(required=false) Integer type, @RequestParam(required=false) Long jouranlId, HttpServletRequest request){
		Map<String, Object> mapQuery = new HashMap<String,Object>();
		int pageCount=Integer.parseInt(size);
		mapQuery.put(ArgumentsUtils.sStartKey, pageCount*(Integer.parseInt(page)-1));
		mapQuery.put(ArgumentsUtils.sSizeKey, pageCount);
		List<UserJournal> lstUserJournals = new ArrayList<UserJournal>();
		if (null == type) {
			return ResultUtil.sharedInstance().FalseData("缺少必要参数，请联系客服", ReturnCodeUtils.Error_Parameter.Default.getCode());	
		}
		if (0 == type) {
			mapQuery.put(ArgumentsUtils.sUIDKey, Long.parseLong(userId));
			lstUserJournals = userJournalService.QueryList(mapQuery);
		}
		if (1 == type) {
			mapQuery.put(ArgumentsUtils.sDBIDKey, jouranlId);
			lstUserJournals = userJournalRecordService.SearchPastRecord(mapQuery);
		}
		return ResultUtil.sharedInstance().TrueData(lstUserJournals, "获取成功", ReturnCodeUtils.Code.OK.getCode());		
	}
	
	/**
	 * 计划显示
	 */
	@RequestMapping(value="/Interface/V2/ShowPlan",method={RequestMethod.GET})
	@ResponseBody
	public Map<String, Object> ShowPlanMethod(@RequestParam String userId, HttpServletRequest request){
		Map<String, Object> mapQuery = new HashMap<String,Object>();
		
		mapQuery.put(ArgumentsUtils.sIDKey, Long.parseLong(userId));
		mapQuery.put(ArgumentsUtils.sDateKey, new Date());
		List<UserPlan> lstUserPlans = userPlanService.QueryNowList(mapQuery);
		return ResultUtil.sharedInstance().TrueData(lstUserPlans, "获取成功", ReturnCodeUtils.Code.OK.getCode());		
	}
	
	/**
	 * 任务列表
	 */
	@RequestMapping(value="/Interface/V2/ShowTask",method={RequestMethod.GET})
	@ResponseBody
	public Map<String, Object> ShowTaskMethod(@RequestParam String userId, HttpServletRequest request){
		Map<String, Object> mapQuery = new HashMap<String, Object>();
		mapQuery.put(ArgumentsUtils.sIDKey, Long.parseLong(userId));
		ShowTaskModel pjShowTaskModel = saveReadService.QueryShowTaskModel(mapQuery);
		return ResultUtil.sharedInstance().TrueData(pjShowTaskModel, "获取成功", ReturnCodeUtils.Code.OK.getCode());		
	}
	
	/**
	 * 阅读总财富 
	 */
	@RequestMapping(value="/Interface/V2/TotalWealthShow",method={RequestMethod.GET})
	@ResponseBody
	public Map<String, Object> TotalWealthShowMethod(@RequestParam String userId, HttpServletRequest request){
		Map<String, Object> mapQuery = new HashMap<String, Object>();
		mapQuery.put(ArgumentsUtils.sIDKey, Long.parseLong(userId));
		TotalWealthShowModel pjTotalWealthShowModel = userService.ShowTotalWealth(mapQuery);
		return ResultUtil.sharedInstance().TrueData(pjTotalWealthShowModel, "获取成功", ReturnCodeUtils.Code.OK.getCode());		
	}
	
	/**
	 * 视频分类接口 
	 */
	@RequestMapping(value="/Interface/V2/VideoClassify",method={RequestMethod.GET})
	@ResponseBody
	public Map<String, Object> VideoClassifyMethod(@RequestParam String userId,@RequestParam(required=false) Integer level,@RequestParam(required=false) Integer parentId, HttpServletRequest request){
		Map<String, Object> mapQuery = new HashMap<String, Object>();
		mapQuery.put(ArgumentsUtils.sArea_LevelKey, level);
		mapQuery.put(ArgumentsUtils.sArea_ParentId, parentId);
		List<Lesson> LstLesson = lessonService.SearchMessageByCondition(mapQuery);
		return ResultUtil.sharedInstance().TrueData(LstLesson, "获取成功", ReturnCodeUtils.Code.OK.getCode());		
	}
	
	
	/**
	 * 推荐书籍列表
	 */
	@RequestMapping(value="/Interface/V2/BookRecommend",method={RequestMethod.GET})
	@ResponseBody
	public Map<String, Object> BookRecommendMethod(@RequestParam(required=false) String userId, @RequestParam(required=false) String keyword, @RequestParam(required=false) String label, @RequestParam(required=false) Integer type, @RequestParam(required=false) Integer page, @RequestParam(required=false) Integer size, HttpServletRequest request){
		Map<String, Object> mapQuery = new HashMap<String, Object>();
		if (page != null && size != null) {
			mapQuery.put(ArgumentsUtils.sStartKey, (page - 1) * size);
			mapQuery.put(ArgumentsUtils.sSizeKey, size);
		}
		mapQuery.put(ArgumentsUtils.sUIDKey, userId);
		mapQuery.put(ArgumentsUtils.sKeyWordKey, keyword);
		mapQuery.put(ArgumentsUtils.STypeKey, type);
		mapQuery.put(ArgumentsUtils.sScreenStringKey, label);
		List<ReadBook> LstReadBooks = readBookService.SearchLstByCondition(mapQuery);
		return ResultUtil.sharedInstance().TrueData(LstReadBooks, "获取成功", ReturnCodeUtils.Code.OK.getCode());		
	}
	
	
	/**
	 * @Title: addpraisemethod
	 * @Description: TODO( 改变状态  判断是否已读 按类型 type(不知道传入String=name怎么解决)  未读时调用 )
	 * @param request
	 * @return  参数
	 * @return Map<String,Object>  返回类型
	 * @throws
	 */
	@RequestMapping(value="/Interface/V3/ChangeStatus",method={RequestMethod.POST,RequestMethod.GET})
	public
	@ResponseBody
	Map<String,Object> addpraisemethod(@RequestParam(required=false)Long userId, @RequestParam(required=false)Long belongId, @RequestParam(required=false)Integer type, @RequestParam(required=false)Integer useType, HttpServletRequest request){
		String param;
		param = (String) request.getAttribute(BaseUtil.paramKey);
		if (param == null || param.equals("")) {
			param = request.getParameter(BaseUtil.paramKey);
		}
		JSONObject jsonObject = JSON.parseObject(param);
		Long lUserId = null;
		Long lBelongId = null;
		Integer iType = null;//iType == 1:新闻 2:家长大课堂信息 3:音频 4:美文赏析
		Integer iUseType = null;//使用场景 1:投票(未启用 +1/未投票) 2:点赞(判断) 3:分享(+1/每次调用) 4:阅读(+1/未读)
		if (null != userId) {
			lUserId = userId;
		} else {
			lUserId = Long.parseLong(jsonObject.getString(ArgumentsUtils.sUserIDKey));
		}
		
		if (null != belongId) {
			lBelongId = belongId;
		} else {
			lBelongId = Long.parseLong(jsonObject.getString(ArgumentsUtils.sBelongIDKey));
		}
		
		if (null != type) {
			iType = type;
		} else {
			iType = Integer.parseInt(jsonObject.getString(ArgumentsUtils.sTypeKey));
		}
		
		if (null != useType) {
			iUseType = useType;
		} else {
			iUseType = Integer.parseInt(jsonObject.getString(ArgumentsUtils.sUseTypeKey));
		}
		
		boolean flag = false;
		
		LikeStatus pjLikeStatus = new LikeStatus();
		pjLikeStatus.setFromUserid(lUserId);
		pjLikeStatus.setColuId(lBelongId);
		pjLikeStatus.setLikeType(iType+1);
		int iNum = likeStatusService.findLike(pjLikeStatus);
		switch (iType) {
			case 1://新闻 new
				New pjNew = new New();
				pjNew.setNews_Id(lBelongId);
				
				switch (iUseType) {
				case 1:
				
				break;
			case 2:
				if (iNum >= 1) {
					LikeStatus l = likeStatusService.selectLike(pjLikeStatus);
					if (l.getLikeStatus() == 1) {
						pjLikeStatus.setLikeStatus(0);
					} else {
						pjLikeStatus.setLikeStatus(1);
						flag = true;
					}
					likeStatusService.updateLike(pjLikeStatus);
				} else {
					pjLikeStatus.setLikeType(iType+1);//iType+1 == 2:新闻 3:家长大课堂信息 4:音频 5:美文赏析
					likeStatusService.addLike(pjLikeStatus);
				}
				break;
			case 3:
				pjNew.setNews_ShareNum(1);
			    newService.UpdateNews(pjNew);
				break;
			case 4:
					pjNew.setNews_ReadNum(1);
				    newService.UpdateNews(pjNew);
				if (iNum < 1) {
				    pjLikeStatus.setLikeType(iType+1);//iType+1 == 2:新闻 3:家长大课堂信息 4:音频 5:美文赏析
					likeStatusService.addLike(pjLikeStatus);
				}
				break;
			default:
				break;
			}
			break;
			
		case 2://家长大课堂 information
			Information pjInformation = new Information();
			pjInformation.setInformation_Id(lBelongId);
			
			switch (iUseType) {
			case 1:
				
				break;
			case 2:
				if (iNum >= 1) {
					LikeStatus l = likeStatusService.selectLike(pjLikeStatus);
					if (l.getLikeStatus() == 1) {
						pjLikeStatus.setLikeStatus(0);
					} else {
						pjLikeStatus.setLikeStatus(1);
						flag = true;
					}
					likeStatusService.updateLike(pjLikeStatus);
				} else {
					pjLikeStatus.setLikeType(iType+1);//iType+1 == 2:新闻 3:家长大课堂信息 4:音频 5:美文赏析
					likeStatusService.addLike(pjLikeStatus);
				}
				break;
			case 3:
				pjInformation.setInformation_ShareNum(1);
			    informationService.UpdateInformation(pjInformation);
				break;
			case 4:
					pjInformation.setInformation_ReadNum(1);
					informationService.UpdateInformation(pjInformation);
				if (iNum < 1) {
				    pjLikeStatus.setLikeType(iType+1);//iType+1 == 2:新闻 3:家长大课堂信息 4:音频 5:美文赏析
					likeStatusService.addLike(pjLikeStatus);
				}
				break;
			default:
				break;
			}
			break;
			
		case 3://音频 voice
			Voice pjVoice = new Voice();
			pjVoice.setVoice_Id(lBelongId);
			
			switch (iUseType) {
			case 1:
				
				break;
			case 2:
				if (iNum >= 1) {
					LikeStatus l = likeStatusService.selectLike(pjLikeStatus);
					if (l.getLikeStatus() == 1) {
						pjLikeStatus.setLikeStatus(0);
					} else {
						pjLikeStatus.setLikeStatus(1);
						flag = true;
					}
					likeStatusService.updateLike(pjLikeStatus);
				} else {
					pjLikeStatus.setLikeType(iType+1);//iType+1 == 2:新闻 3:家长大课堂信息 4:音频 5:美文赏析
					likeStatusService.addLike(pjLikeStatus);
				}
				break;
			case 3:
				pjVoice.setVoice_ShareNum(1);
			    voiceService.UpdateVoice(pjVoice);
				break;
			case 4:
					pjVoice.setVoice_ReadNum(1);
					voiceService.UpdateVoice(pjVoice);
				if (iNum < 1) {
				    pjLikeStatus.setLikeType(iType+1);//iType+1 == 2:新闻 3:家长大课堂信息 4:音频 5:美文赏析
					likeStatusService.addLike(pjLikeStatus);
				}
				break;
			default:
				break;
			}
			break;
			
		case 4://美文赏析 Article
			Article pjArticle = new Article();
			pjArticle.setArticle_Id(lBelongId);
			
			switch (iUseType) {
			case 1:
				
				break;
			case 2:
				if (iNum >= 1) {
					LikeStatus l = likeStatusService.selectLike(pjLikeStatus);
					if (l.getLikeStatus() == 1) {
						pjLikeStatus.setLikeStatus(0);
					} else {
						pjLikeStatus.setLikeStatus(1);
						flag = true;
					}
					likeStatusService.updateLike(pjLikeStatus);
				} else {
					pjLikeStatus.setLikeType(iType+1);//iType+1 == 2:新闻 3:家长大课堂信息 4:音频
					likeStatusService.addLike(pjLikeStatus);
				}
				break;
			case 3:
				pjArticle.setArticle_ShareNum(1);
			    articleService.UpdateArticle(pjArticle);
				break;
			case 4:
					pjArticle.setArticle_ReadNum(1);
					articleService.UpdateArticle(pjArticle);
				if (iNum < 1) {
				    pjLikeStatus.setLikeType(iType+1);//iType+1 == 2:新闻 3:家长大课堂信息 4:音频
					likeStatusService.addLike(pjLikeStatus);
				}
				break;
			default:
				break;
			}
			break;

		default:
			break;
		}
		return ResultUtil.sharedInstance().TrueData(flag, "请求成功", ReturnCodeUtils.Code.OK.getCode());
	}
	
	/**
	 * @Title: IncreaseCommentMethod
	 * @Description: TODO( 发表评论 )
	 * @param request
	 * @return  参数
	 * @return Map<String,Object>  返回类型
	 * @throws
	 */
	@RequestMapping(value="/Interface/V3/IncreaseComment",method={RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public Map<String, Object> IncreaseCommentMethod(HttpServletRequest request){
		String param;
        param = (String)request.getAttribute(BaseUtil.paramKey);
        if(param==null || param.equals("")){
            param=request.getParameter(BaseUtil.paramKey);
        }
        JSONObject jsonObject=JSON.parseObject(param);
        String noteId = jsonObject.getString(ArgumentsUtils.sBelongIDKey).trim();
        String userId = jsonObject.getString(ArgumentsUtils.sUseIDKey).trim();
        String txt = jsonObject.getString("content");
        
//	      String toId = jsonObject.getString("toId").trim();
        String Replydateid = jsonObject.getString("Replydateid");//对某个评论评论,某个评论的ID
        Comment comment = new Comment();
        comment.setCommentId(IdWorker.CreateNewID());
        comment.setCommentUserid(Long.parseLong(userId));
        comment.setCommentNoticesid(Long.parseLong(noteId));//所在帖子的ID
        if (null != Replydateid && !"".equals(Replydateid) && 0 != Long.parseLong(Replydateid)) {//对人
//	        	commentReplydataid,commentFromuserid
//				comment.setCommentFromuserid(Long.parseLong(toId));//对谁评论
        	comment.setCommentReplydataid(Long.parseLong(Replydateid));
        	long UId = commentService.RedateIdToUserId(Long.parseLong(Replydateid));
        	comment.setCommentFromuserid(UId);
			comment.setCommentComment(txt.getBytes());
		}else {//对贴
			comment.setCommentContent(txt.getBytes());
		}
        comment.setCommentType(0);
        try {
        commentService.addcomment(comment);
        noteService.addcomnum(Long.parseLong(noteId));
        return ResultUtil.sharedInstance().TrueData(true, "评论成功", ReturnCodeUtils.Code.OK.getCode());
        } catch (Exception e) {
			// TODO: handle exception
        		return ResultUtil.sharedInstance().FalseData("数据异常", ReturnCodeUtils.Code.NO.getCode());
		}
	}
		
	/**
	 * @Title: SearchCommentMethod
	 * @Description: TODO( 查询评论 )
	 * @param userId
	 * @param noteId
	 * @param page
	 * @param size
	 * @param request
	 * @return  参数
	 * @return Map<String,Object>  返回类型
	 * @throws
	 */
	@RequestMapping(value="/Interface/V3/SearchComment",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> SearchCommentMethod(@RequestParam Long userId, Long belongId, Integer type,String page,String size,HttpServletRequest request){
		Map<String, Object> result = new HashMap<String,Object>();
		Map<String, Object> map = new HashMap<String,Object>();
		boolean flag = false;
		try {
		if (null != userId) {
			LikeStatus likeStatu = new LikeStatus();
			likeStatu.setColuId(belongId);
			likeStatu.setFromUserid(userId);
			likeStatu.setLikeType(type);
			int num = likeStatusService.findLike(likeStatu);
			if (num <= 0) {
				likeStatusService.addLike(likeStatu);
			}
			LikeStatus likeStatus = likeStatusService.selectLike(likeStatu);
			
			if (1 ==likeStatus.getLikeStatus()) {
				flag = true;
			}	
		}
		map.put(ArgumentsUtils.sUserIDKey, userId);
		map.put(ArgumentsUtils.sBelongIDKey, belongId);
		int pageCount=Integer.parseInt(size);
		map.put(ArgumentsUtils.sStartKey, (Integer.parseInt(page)-1)*pageCount);
		map.put(ArgumentsUtils.sSizeKey, pageCount);
		List<ForumComment> forumComments = new ArrayList<ForumComment>();
		List<Comment> commentlist = commentService.forumcomment(map);
		for (int i = 0; i < commentlist.size(); i++) {
			
			ForumComment forumComment = new ForumComment();
			forumComment.setReplydateid(commentlist.get(i).getCommentId());
			forumComment.setFirstcommenttime(commentlist.get(i).getCommentCreatetime());
//				String headicon = userService.selecticon(commentlist.get(i).getCommentUserid());
			User user = userService.seletename(commentlist.get(i).getCommentUserid());
			forumComment.setUserheadicon(user.getUserHeadicon());
			forumComment.setUsername(user.getUserName());
			forumComment.setComment(new String(commentlist.get(i).getCommentContent()));
			map.put(ArgumentsUtils.sStartKey, (int)0);
			map.put(ArgumentsUtils.sSizeKey, (int)2);
			List<Comment> comments = commentService.ComToMe(map);
			List<NameAndComment> nameandcomment = new ArrayList<NameAndComment>();
			for (int j = 0; j < comments.size(); j++) {
				NameAndComment NC = new NameAndComment();
				User username = userService.seletename(comments.get(j).getCommentUserid());
				NC.setUserName(new String(username.getUserName()));
				NC.setReplyDateId(comments.get(j).getCommentId());
// System.out.println(new String(username.getUserName()));
				NC.setComment(new String(comments.get(j).getCommentComment()));
				nameandcomment.add(NC);
				forumComment.setNameandcomment(nameandcomment);
			}
			forumComments.add(forumComment);
		}
		result.put("comList", JSON.toJSON(forumComments));
		result.put("likestatus", flag);
		return ResultUtil.sharedInstance().TrueData(result, "获取成功", ReturnCodeUtils.Code.OK.getCode());
		} catch (Exception e) {
			// TODO: handle exception
			return ResultUtil.sharedInstance().FalseData("数据异常", ReturnCodeUtils.Code.NO.getCode());
		}
	}
	

	/**
	 * @Title: forumshowmore
	 * @Description: TODO( 论坛评论显示查看更多 )
	 * @param Replydateid
	 * @param noteId
	 * @param page
	 * @param size
	 * @param request
	 * @return  参数
	 * @return Map<String,Object>  返回类型
	 * @throws
	 */
	@RequestMapping(value="/Interface/V3/ShowMoreComment",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> ShowMoreCommentMethod(@RequestParam Long Replydateid, Long belongId,String page,String size,HttpServletRequest request){
		Map<String, Object> map = new HashMap<String, Object>();
		int pageCount = Integer.parseInt(size);
		map.put(ArgumentsUtils.sStartKey, (Integer.parseInt(page) - 1) * pageCount);
		map.put(ArgumentsUtils.sSizeKey, pageCount);
		map.put(ArgumentsUtils.sUserIDKey, Replydateid);
		map.put(ArgumentsUtils.sBelongIDKey, belongId);
		try {
		ComShowMore csm = new ComShowMore();
		List<Comment> comments = commentService.ComToMe(map);
		List<NameAndComment> nameandcomment = new ArrayList<NameAndComment>();
		for (int j = 0; j < comments.size(); j++) {
			NameAndComment NC = new NameAndComment();
			User username = userService.seletename(comments.get(j).getCommentUserid());
			NC.setUserName(new String(username.getUserName()));
			NC.setReplyDateId(comments.get(j).getCommentId());
// System.out.println(new String(username.getUserName()));
			NC.setComment(new String(comments.get(j).getCommentComment()));
			nameandcomment.add(NC);
		}
//			firstComList.setNoteId(noids.get(i));
//			firstComList.setNameandcomment(nameandcomment);
//			firstComList.setFirstcommenttime(comments.get(i).getCommentCreatetime());
//			firstComList.setNotename(noteName);
		csm.setNameandcomment(nameandcomment);
//			csm.setFirstcommenttime(firstcommenttime);
		User user = userService.seletename(Replydateid);
		csm.setUsername(user.getUserName());
		csm.setheadicon(user.getUserHeadicon());
		return ResultUtil.sharedInstance().TrueData(csm, "获取成功", ReturnCodeUtils.Code.OK.getCode());
		} catch (Exception e) {
			// TODO: handle exception
			return ResultUtil.sharedInstance().FalseData("数据异常", ReturnCodeUtils.Code.NO.getCode());
		}
	}
		
	
		
//		response.setContentType("application/json");
//	response.setHeader("Cache-Control", "no-store");
//	PrintWriter out = response.getWriter();
//	out.write(reJSON);
//		List<List<List<Regions>>> regiones = new ArrayList<List<List<Regions>>>();//省份
//		List<List<Regions>> cityes = new ArrayList<List<Regions>>();//城市
//		List<Regions> areaes = new ArrayList<Regions>();//地区
//		
//		for (int i = 0; i < provinces.size(); i++) {
//			List<Regions> citys = regionsService.secondLevel(provinces.get(i).getRegionId());//城市
//			//cityes.add(i, citys);
//			cityes.add(citys);
//			for (int j = 0; j < citys.size(); j++) {
//				areaes = regionsService.thirdLevel(citys.get(j).getRegionId());//地区
//				cityes.add(areaes);
//			}//过滤不要的id
//			regiones.add(cityes);
//		}
//		result.put("pictures", JSON.toJSON(regiones));
//		result.put("pic", JSON.toJSON(cityes));
//		result.put("p", JSON.toJSON(areaes));
//		result.put("message", "获取成功");
//		result.put("status", true);
//		return result;
//		}
	
//	/**注释备份
//	 * 地区检索
//	 */
//	@RequestMapping(value="/regions",method={RequestMethod.GET,RequestMethod.POST})
//	@ResponseBody
//	public Map<String, Object> regions(@RequestParam String page,String size,HttpServletRequest request){
//		Map<String, Object> result = new HashMap<String,Object>();
//		Map<String, Object> map = new HashMap<String, Object>();
//		int pageCount=Integer.parseInt(size);
//		map.put("start", (Integer.parseInt(page)-1)*pageCount);
//		map.put("size", pageCount);
//		List<List<List<Regions>>> regiones = new ArrayList<List<List<Regions>>>();//省份
//		List<List<Regions>> cityes = new ArrayList<List<Regions>>();//城市
//		List<Regions> areaes = new ArrayList<Regions>();//地区
//		
//		
//		List<Regions> provinces = regionsService.firstLevel();
//		for (int i = 0; i < provinces.size(); i++) {
//			List<Regions> citys = regionsService.secondLevel(provinces.get(i).getRegionId());//城市
//			//cityes.add(i, citys);
//			cityes.add(citys);
//			for (int j = 0; j < citys.size(); j++) {
//				areaes = regionsService.thirdLevel(citys.get(j).getRegionId());//地区
//				cityes.add(areaes);
//			}//过滤不要的id
//			regiones.add(cityes);
//		}
//		result.put("pictures", JSON.toJSON(regiones));
//		result.put("pic", JSON.toJSON(cityes));
//		result.put("p", JSON.toJSON(areaes));
//		result.put("message", "获取成功");
//		result.put("status", true);
//		return result;
//		}
		
		/*List<List<Regions>> p1 = new ArrayList<List<Regions>>();
		for (int i = 0; i < p1.size(); i++) {
			regions.add(p1.get(i).get(i).getRegionName())
		}
		p1.add(provinces);
		List<List<List<Regions>>> p2 = new ArrayList<List<List<Regions>>>();
		p2.add(p1);
		List<String> province = new ArrayList<String>();
		//城市
//		List<String> citys = new ArrayList<String>();
//		List<String> city = new ArrayList<String>();
		//地区
//		List<String> areas = new ArrayList<String>();
//		List<String> area = new ArrayList<String>();
		
		for (int i = 0; i < provinces.size(); i++) {
			List<List<String>> q = new ArrayList<List<String>>();
			
			q.add(e);
			regions.add(provinces.get(i).getRegionName());
			List<Regions> citys = regionsService.secondLevel(provinces.get(i).getRegionId());
			response.setContentType("application/json"); 
			response.setHeader("Cache-Control", "no-store"); 
			PrintWriter out = response.getWriter(); 
			out.write(reJSON);
		}*/
	
		
	/**----------------------接口内调用方法集合----------------------*/
	/**
	 * 计算百分比
	 * @param y
	 * @param z
	 * @return
	 */
	public static String myPercent(int y, int z) { 
	    String baifenbi = "";// 接受百分比的值 
	    double baiy = y * 1.0; 
	    double baiz = z * 1.0; 
	    double fen = baiy / baiz; 
	// NumberFormat nf = NumberFormat.getPercentInstance();注释掉的也是一种方法
	// nf.setMinimumFractionDigits( 2 ); 保留到小数点后几位
	    DecimalFormat df1 = new DecimalFormat("##.00%");
	// ##.00% 
	// 百分比格式，后面不足2位的用0补齐 
	// baifenbi=nf.format(fen); 
	    baifenbi = df1.format(fen); 
	    System.out.println(baifenbi); 
	    return baifenbi; 
	  } 
//	  public static void main(String[] args) {
//	   myPercent(10,10);
//	  }
	
	
	public static String UseTypeMethod(int y, int z) { 
	    String baifenbi = "";// 接受百分比的值 
	    double baiy = y * 1.0; 
	    double baiz = z * 1.0; 
	    double fen = baiy / baiz; 
	// NumberFormat nf = NumberFormat.getPercentInstance();注释掉的也是一种方法
	// nf.setMinimumFractionDigits( 2 ); 保留到小数点后几位
	    DecimalFormat df1 = new DecimalFormat("##.00%");
	// ##.00% 
	// 百分比格式，后面不足2位的用0补齐 
	// baifenbi=nf.format(fen); 
	    baifenbi = df1.format(fen); 
	    System.out.println(baifenbi); 
	    return baifenbi; 
	  } 
//	  public static void main(String[] args) {
//	   myPercent(10,10);
//	  }
	
	
}
