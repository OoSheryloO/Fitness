package com.huban.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.disk.DiskFileItem;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.huban.Utils.ArgumentsUtils;
import com.huban.Utils.ConstantUtils;
import com.huban.Utils.ReturnCodeUtils;
import com.huban.construct.LstDeptModel;
import com.huban.construct.TeacherModel;
import com.huban.pojo.Comment;
import com.huban.pojo.LikeStatus;
import com.huban.pojo.Reward;
import com.huban.pojo.User;
import com.huban.pojo.Video;
import com.huban.pojo.VideoWithBLOBs;
import com.huban.util.IdWorker;
import com.huban.util.ResultUtil;
import com.huban.util.UploadAliYunFile;
import com.huban.util.VideoReadUtil;
//import com.sun.jdi.connect.Connector.Argument;

/**
 * @author GeJiangbo
 * @date 2017年5月20日
 */
@Controller
@RequestMapping("/video")
public class VideoController extends BaseController{
    
	
	/**
	 * 用户上传教学视频
	 * @param
	 * @throws IOException 
	 * 
	 * */
	@RequestMapping(value="/addvideo",method=RequestMethod.POST)
	public 
	@ResponseBody
	Map<String,Object> addvideoMethod(@RequestParam(value="file") MultipartFile file,HttpServletRequest request) throws IOException{
		Map<String, Object> result=new HashMap<String, Object>();
		//获取request传过来的参数
		String param;
		param=(String) request.getAttribute("param");
		if(param==null||param.equals("")){
			param=request.getParameter(param);
		}
		JSONObject jsonObject=JSONObject.parseObject(param);
		String userString=jsonObject.getString("user");
		String videoTitle=jsonObject.getString("title");
		String videoContent=jsonObject.getString("content");
		String videoUseMoney = jsonObject.getString("videoMoney");
		User user=JSON.parseObject(userString,User.class);
		//视频传到阿里云
				InputStream inputStream = file.getInputStream();
				String fileType="Video";
				String url=UploadAliYunFile.UploadAliYunFileService(inputStream, file, fileType);
		//MultipartFile file转换为 File file 获取视频时长只支持File类型的文件
		CommonsMultipartFile cf= (CommonsMultipartFile)file; 
        DiskFileItem fi = (DiskFileItem)cf.getFileItem(); 
        File f = fi.getStoreLocation();
		//获取视频时长
		long videotime=VideoReadUtil.videotime(f);//毫秒
		if(f.exists()){
			f.delete();
		}
		
		//添加视频信息到数据库
		VideoWithBLOBs video=new VideoWithBLOBs();
		video.setVideoId(IdWorker.CreateNewID());
		video.setVideoUrl(url);
		video.setVideoUserid(user.getUserId());
		video.setVideoTitle(videoTitle);
		video.setVideoContent(videoContent);
		video.setVideoFiletime(videotime);
		video.setVideoUseMoney(videoUseMoney);
		int flag=videoSerive.addVideo(video);
		if(flag==1){
			result.put("message", "上传成功。");
			result.put("status", true);
		}else{
			result.put("message", "上传失败,数据库插入数据不成功。");
			result.put("status", false);
		}
		return result;
	}
	/**
	 * 所有视频列表
	 * @param request
	 * */
	@RequestMapping(value="/videolist",method={RequestMethod.GET,RequestMethod.POST})
	public
	@ResponseBody
	Map<String, Object> videolistMethod(@RequestParam String page,String size,HttpServletRequest request){
		Map<String, Object> result=new HashMap<String, Object>();
		int pageCount=Integer.parseInt(size);
		Map<String, Object> queryMap=new HashMap<String, Object>();
		queryMap.put("start", pageCount*(Integer.parseInt(page)-1));
		queryMap.put("size", pageCount);
		try {
		List<VideoWithBLOBs> videolist=videoSerive.queryVideos(queryMap);
//		result.put("videolist", JSON.toJSON(videolist));
		result.put("videolist", videolist);
		result.put("status", true);
		} catch (Exception e) {
			// TODO: handle exception
			result.put("message", "数据异常，请联系客服");
			result.put("status", false);
		}
		return result;
	}
	/**
	 * 个人视频列表
	 * @param request
	 * 
	 * */
	@RequestMapping(value="/myvideo",method={RequestMethod.GET,RequestMethod.POST})
	public
	@ResponseBody
	Map<String, Object> myvideoMethod(HttpServletRequest request){
		Map<String, Object> result=new HashMap<String, Object>();
		String param;
		param=(String) request.getAttribute("param");
		if(param==null||param.equals("")){
			param=request.getParameter("param");
		}
		JSONObject jsonObject=JSONObject.parseObject(param);
		String page=jsonObject.getString("page");
		String size=jsonObject.getString("size");
		String userString=jsonObject.getString("user");
		User user=JSON.parseObject(userString,User.class);
		if(page == null || "".equals(page) || size == null || "".equals(size)){
			result.put("message", "页码未传");
			result.put("status", false);
			return result;
		}
		int pageCount=Integer.parseInt(size);
		Map<String, Object> queryMap=new HashMap<String, Object>();
		queryMap.put("start", pageCount*(Integer.parseInt(page)-1));
		queryMap.put("size", pageCount);
		queryMap.put("userId", user.getUserId());
		try {
		List<VideoWithBLOBs> list=videoSerive.queryVideos(queryMap);
		result.put("video", JSON.toJSON(list));
		result.put("status", true);
		} catch (Exception e) {
			// TODO: handle exception
			result.put("message", "数据异常，请联系客服");
			result.put("status", false);
		}
		return result;
	}
	/**
	 * 视频详情
	 * @param request
	 * */
	@RequestMapping(value="/videodetail",method={RequestMethod.GET,RequestMethod.POST})
	public
	@ResponseBody
	Map<String, Object> videodetailMethod(@RequestParam String videoId,String userId,HttpServletRequest request){
		Map<String, Object> result=new HashMap<String, Object>();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("videoId", videoId);
		map.put("userId", userId);
//		try {
		int count = payrecordsService.payVideo(map);
		if (count < 0 || count > 1) {
			result.put("status", false);
			result.put("message", "系统故障请联系客服！");
			return result;
		}
		if (count == 0) {
			result.put("tfPay", false);
		}else {
			result.put("tfPay", true);
		}
		VideoWithBLOBs video=videoSerive.selectVideo(Long.parseLong(videoId));
		if ("0" == video.getVideoUseMoney() || "0".equals(video.getVideoUseMoney())) {
			result.put("tfPay", true);
		}
		if (userId == null || "".equals(userId) || 0 == Long.parseLong(userId)) {
			result.put("message", "您是游客身份");
			result.put("clickLike", false);
		}else {
			//当前用户点赞状态(新用户点赞的记录的生成)
			LikeStatus like = new LikeStatus();
			like.setColuId(Long.parseLong(videoId));
			like.setFromUserid(Long.parseLong(userId));
			like.setLikeType(1);
			int tf = likeStatusService.findLike(like);
			if (0 == tf) {
				likeStatusService.addLike(like);
				result.put("clickLike", false);
			}else {
				LikeStatus likeStatu = likeStatusService.selectLike(like);
				if (0 == likeStatu.getLikeStatus() || null == likeStatu.getLikeStatus()) {
					result.put("clickLike",false);
				}else {
					result.put("clickLike", true);	
				}
			}	
		}
		result.put("video",JSON.toJSON(video));
		result.put("status", true);
//		} catch (Exception e) {
//			// TODO: handle exception
//			result.put("message", "数据异常，请联系客服");
//			result.put("status", false);
//		}
		return result;
	}
	
	/**
	 * 视频点赞（包括改变点赞状态）
	 * @param request
	 * */
	@RequestMapping(value="/likeclick",method={RequestMethod.POST,RequestMethod.GET})
	public 
	@ResponseBody
	Map<String, Object> likeclickMethod(HttpServletRequest request){
		Map<String,Object> result=new HashMap<String, Object>();
		String param;
		param=(String) request.getAttribute("param");
		if(param==null||param.equals("")){
			param=request.getParameter("param");
		}
		JSONObject jsonObject=JSONObject.parseObject(param);
		String videoId=jsonObject.getString("videoId");
		String userId = jsonObject.getString("userId");
		//String userString =jsonObject.getString("user");
		//User user=JSON.parseObject(userString,User.class);
		LikeStatus likeStatus=new LikeStatus();
		likeStatus.setColuId(Long.parseLong(videoId));
		likeStatus.setFromUserid(Long.parseLong(userId));
		likeStatus.setLikeType(1);
		//查询出当前用户该视频的点赞状态
		//保险，不经过获取视频详情会报null错
		try {
		int num = likeStatusService.findLike(likeStatus);
		if (0 == num) {
			likeStatusService.addLike(likeStatus);
		}
		LikeStatus like = likeStatusService.selectLike(likeStatus);
		Video video = new Video();
		if (0 == like.getLikeStatus()) {
			likeStatus.setLikeStatus(1);
			video.setVideoId(Long.parseLong(videoId));
			videoSerive.addlike(video);
			result.put("clickLike", true);
		}else {
			likeStatus.setLikeStatus(0);
			video.setVideoId(Long.parseLong(videoId));
			videoSerive.cutlike(video);
			result.put("clickLike", false);
		}
		likeStatusService.updateLike(likeStatus);
		result.put("message", "处理成功");
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
	@RequestMapping(value="/getreward",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> rewardmethod(@RequestParam String videoId, HttpServletRequest request){
		Map<String, Object> result = new HashMap<String,Object>();
		try {
		List<Reward> rewardlist = rewardService.FindUserByRId(Long.parseLong(videoId));
		List<User> returnUsers = new ArrayList<User>();
		for (int i = 0; i < rewardlist.size(); i++) {
			User user = new User();
			user.setUserId(rewardlist.get(i).getRewardFromuserid());
			User e = userService.selectUser(user);
			returnUsers.add(e);
		}
		result.put("userIcon", JSON.toJSON(returnUsers));
		result.put("status", true);
		} catch (Exception e) {
			// TODO: handle exception
			result.put("message", "数据异常，请联系客服");
			result.put("status", false);
		}
		return result;
	}
	
	
	/**
	 * 评论详情
	 * @param request
	 * *//*
	@RequestMapping(value="/videocomments",method={RequestMethod.GET,RequestMethod.POST})
	public 
	@ResponseBody
	Map<String, Object> videocommentsMethod(@RequestParam String page, String size, String videoId,HttpServletRequest request){
		Map<String,Object> result=new HashMap<String, Object>();
		String param;
		param=(String) request.getAttribute("param");
		if(param==null||param.equals("")){
			param=request.getParameter("param");
		}
		JSONObject jsonObject=JSONObject.parseObject(param);
		String videoId=jsonObject.getString("videoId");
		String page=jsonObject.getString("page");
		String size=jsonObject.getString("size");
		int pageCount=Integer.parseInt(size);
		Map<String, Object> queryMap=new HashMap<String, Object>();
		queryMap.put("videoId", videoId);
		queryMap.put("start", pageCount*(Integer.parseInt(page)-1));
		queryMap.put("size", pageCount);
		List<Comment> list=commentService.querycomment(queryMap);
		
		List<User> userlist=new ArrayList<User>();//用户信息
		List<String> commentlist = new ArrayList<String>();//评论内容
		List<Object> time = new ArrayList<Object>();//评论时间
		for(int i=0;i<list.size();i++){
			User u1=new User();
			u1.setUserId(list.get(i).getCommentFromuserid());
			User u=userService.selectUser(u1);
			userlist.add(u);	
			commentlist.add(list.get(i).getCommentContent().toString());
			time.add(list.get(i).getCommentCreatetime());
		}
		
		result.put("time", time);
		result.put("comment", commentlist);
		result.put("user", JSON.toJSON(userlist));
		result.put("comments", JSON.toJSON(list));
		result.put("status", true);
		return result;
	}*/
	
	
	/**
	 * 视频播放次数增加
	 * */
	@RequestMapping("/addclick")
	public 
	@ResponseBody
	Map<String,Object> addclickMethod(HttpServletRequest request){
		Map<String, Object> result=new HashMap<String, Object>();
		String param;
		param=(String) request.getAttribute("param");
		if(param==null||param.equals("")){
			param=request.getParameter("param");
		}
		String videoId=JSONObject.parseObject(param).getString("videoId");
		Video video=new Video();
		video.setVideoId(Long.parseLong(videoId));
		try {
		videoSerive.addclick(video);
		result.put("message", "处理成功");
		result.put("status", true);
		} catch (Exception e) {
			// TODO: handle exception
			result.put("message", "数据异常，请联系客服");
			result.put("status", false);
		}
		return result;
	}
	
	/**
	 * 视频评论
	 * @param request
	 * */
	@RequestMapping("/addcomment")
	public
	@ResponseBody
	Map<String, Object> addcommentMethod(HttpServletRequest request){
		Map<String, Object> result=new HashMap<String, Object>();
		String param;
		param=(String) request.getAttribute("param");
		if(param==null||param.equals("")){
			param=request.getParameter("param");
		}
		JSONObject jsonObject=JSONObject.parseObject(param);
		String comment=jsonObject.getString("comment");
		String videoId=jsonObject.getString("videoId");
		String userString =jsonObject.getString("user");
		User user=JSON.parseObject(userString,User.class);
		Comment c=new Comment();
		c.setCommentId(IdWorker.CreateNewID());
		c.setCommentFromuserid(user.getUserId());
		c.setCommentVideoid(Long.parseLong(videoId));
		c.setCommentContent(comment.getBytes());
		try {
		commentService.addcomment(c);
		result.put("message", "添加成功");
		result.put("status", true);
		} catch (Exception e) {
			// TODO: handle exception
			result.put("message", "数据异常，请联系客服");
			result.put("status", false);
		}
		return result;
	}
	
	/**
	 * @Title: SearchDeptMethod
	 * @Description: TODO( 视频分类 )
	 * @param userId
	 * @param page
	 * @param size
	 * @param type =null:全部
	 * @param condition 大条件
	 * @param request
	 * @return  参数
	 * @return Map<String,Object>  返回类型
	 * @throws
	 */
	@RequestMapping(value = "/Interface/V2/Classify", method = { RequestMethod.GET })
	public @ResponseBody Map<String, Object> VideoClassifyMethod(@RequestParam(value="userId") long userId,@RequestParam(value="page") int page,@RequestParam(value="size") int size,@RequestParam(required=false) Integer type,@RequestParam(required=false) Integer condition, HttpServletRequest request) {
		Map<String, Object> mapQuery = new HashMap<String, Object>();
		mapQuery.put(ArgumentsUtils.sStartKey, size * (page - 1));
		mapQuery.put(ArgumentsUtils.sSizeKey, size);
		mapQuery.put(ArgumentsUtils.STypeKey, type);
		mapQuery.put(ArgumentsUtils.sConditionKey, condition);
		if (condition != null) {
			if (0 == condition || -1 == condition) {//课内课堂
				List<TeacherModel> LstTeacher = videoSerive.SearchLstTeacher(mapQuery);
				return ResultUtil.sharedInstance().TrueData(LstTeacher, "获取成功", ReturnCodeUtils.Code.OK.getCode());
			}
		}
		mapQuery.put(ArgumentsUtils.sUseIDKey, userId);
		List<VideoWithBLOBs> LstVideo = videoSerive.queryVideos(mapQuery);
		return ResultUtil.sharedInstance().TrueData(LstVideo, "获取成功", ReturnCodeUtils.Code.OK.getCode());
	}
	
	
	/**
	 * @Title: TeacherVideoMethod
	 * @Description: TODO( 获取某个老师的视频 )
	 * @param userId
	 * @param page
	 * @param size
	 * @param teacherId
	 * @param request
	 * @return  参数
	 * @return Map<String,Object>  返回类型
	 * @throws
	 */
	@RequestMapping(value = "/Interface/V2/TeacherVideo", method = { RequestMethod.GET })
	public @ResponseBody Map<String, Object> TeacherVideoMethod(@RequestParam long userId, int page, int size,@RequestParam(required=false) Long teacherId, HttpServletRequest request) {
		Map<String, Object> mapQuery = new HashMap<String, Object>();
		mapQuery.put(ArgumentsUtils.sStartKey, size * (page - 1));
		mapQuery.put(ArgumentsUtils.sSizeKey, size);
		if (null != teacherId) {
			mapQuery.put(ArgumentsUtils.sDBIDKey, teacherId);
		}
		mapQuery.put(ArgumentsUtils.sUseIDKey, userId);
		List<VideoWithBLOBs> LstVideo = videoSerive.queryVideos(mapQuery);
		return ResultUtil.sharedInstance().TrueData(LstVideo, "获取成功", ReturnCodeUtils.Code.OK.getCode());
	}
	
}
