/**
 * 
 */
package com.huban.controller;

import static org.hamcrest.CoreMatchers.nullValue;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.huban.Utils.ArgumentsUtils;
import com.huban.Utils.ForumComment;
import com.huban.construct.ComShowMore;
import com.huban.construct.FirstComList;
import com.huban.construct.FirstShowMore;
import com.huban.construct.NameAndComment;
import com.huban.pojo.Comment;
import com.huban.pojo.LikeStatus;
import com.huban.pojo.Note;
import com.huban.pojo.Picture;
import com.huban.pojo.User;
import com.huban.util.BaseUtil;
import com.huban.util.Constant;
import com.huban.util.IdWorker;
import com.huban.util.UploadAliYunFile;



/**
 * @author GeJiangbo
 * @date 2017年6月3日
 */
@Controller
@RequestMapping("/forum")
public class ForumController extends BaseController{
	
	/**
	 * 论坛列表(comment表的noticeID作为论坛的id)
	 * @param page
	 * @param size
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/forumlist",method={RequestMethod.GET,RequestMethod.POST})
	public
	@ResponseBody
	Map<String,Object> forumlistmethod(@RequestParam String page,String size,String bookId,HttpServletRequest request){
		Map<String, Object> result=new HashMap<String, Object>();
		Map<String, Object> map=new HashMap<String, Object>();
		try {
//		List<Integer> likeNum = new ArrayList<Integer>();
//		List<Integer> commentNum	= new ArrayList<Integer>();
		int pageCount=Integer.parseInt(size);
		map.put("start", (Integer.parseInt(page)-1)*pageCount);
		map.put("size", pageCount);
		
		if (bookId != null && !"".equals(bookId) ) {
			map.put("bookId", Long.parseLong(bookId));
		}
		List<Note> list=noteService.queryList(map);
		List<List<String>> twoList = new ArrayList<List<String>>();
		for (int i = 0; i < list.size(); i++) {
			List<String> picture = pictureService.selectBy(Long.valueOf(list.get(i).getNoteId()));
			twoList.add(picture);
//			int likenum = likeStatusService.queryexist(list.get(i).getNoteId());
//			int comnum = commentService.commentNum(list.get(i).getNoteId());
//			commentNum.add(comnum);
//			likeNum.add(likenum);
		}
		result.put("pictures", twoList);
//    	result.put("likeNum", JSON.toJSON(likeNum));
//		result.put("commentNum", JSON.toJSON(commentNum));
		result.put("noteList",JSON.toJSON(list));
		result.put("status", true);
		} catch (Exception e) {
			// TODO: handle exception
			result.put("message", "数据异常，请联系客服");
			result.put("status", false);
		}
		return result;
	}
	
	//发帖图片的上传接口（picture表的picture_userId就当做note的Id）
	@RequestMapping(value="/uploadforum",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> uploadforum(@RequestParam(value="file")MultipartFile file, HttpServletRequest request){
		Map<String, Object> result=new HashMap<String, Object>();
    	String param;
        param = (String)request.getAttribute(BaseUtil.paramKey);
        if(param == null || param.equals("")){
            param=request.getParameter(BaseUtil.paramKey);
        }
        try {
       // JSONObject jsonObject=JSON.parseObject(param);
    	Picture picture = new Picture();
    	picture.setPictureId(IdWorker.CreateNewID());
    	InputStream inputStream = file.getInputStream();
    	String fileType="Picture";
			String url=UploadAliYunFile.UploadAliYunFileService(inputStream, file, fileType);
			picture.setPictureUrl(url);
			picture.setPictureBelongid((long)2);
			pictureService.notePicture(picture);
			result.put("pictureURL", url);
			result.put("pictureId", picture.getPictureId());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	//picture.setPictureName(file.getOriginalFilename());
    	/*InputStream inputStream = file.getInputStream();
    	String fileType="Picture";
    	String url=UploadAliYunFile.UploadAliYunFileService(inputStream, file, fileType);
    	URL url2 = new URL(url);
    	picture.setPictureProtocol(url2.getProtocol());//协议
    	picture.setPictureIpaddress(url2.getHost());//主机
    	picture.setPicturePort(String.valueOf(url2.getPort()));//端口
    	picture.setPictureName(url2.getFile());//文件名*/
        result.put("message", "上传成功");
        result.put("status", true);
    	return result;
    }	
	
	
	private List<String> saveFile(HttpServletRequest request,
            MultipartFile file, List<String> list) {
        // 判断文件是否为空
        if (!file.isEmpty()) {
            try {
                // 保存的文件路径(如果用的是Tomcat服务器，文件会上传到\\%TOMCAT_HOME%\\webapps\\YourWebProject\\upload\\文件夹中
                // )
            	Picture picture = new Picture();
    	    	picture.setPictureId(IdWorker.CreateNewID());
    	    	InputStream inputStream = file.getInputStream();
    	    	String fileType="Picture";
    				String url=UploadAliYunFile.UploadAliYunFileService(inputStream, file, fileType);
    				picture.setPictureUrl(url);
    				picture.setPictureBelongid((long)2);
    				pictureService.notePicture(picture);
    				list.add(picture.getPictureId().toString());
    			} catch (Exception e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
//                String filePath = request.getSession().getServletContext()
//                        .getRealPath("/")
//                        + "upload/" + file.getOriginalFilename();
//                list.add(file.getOriginalFilename());
//                File saveDir = new File(filePath);
//                if (!saveDir.getParentFile().exists())
//                    saveDir.getParentFile().mkdirs();
//
//                // 转存文件
//                file.transferTo(saveDir);
//                return list;
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
        }
        return list;
    }
	
	//(多图片)发帖图片的上传接口（picture表的picture_userId就当做note的Id）
		@RequestMapping(value="/uploadpics",method={RequestMethod.GET,RequestMethod.POST})
		@ResponseBody
		public Map<String, Object> uploadforum(@RequestParam(value="files") MultipartFile[] files/*, @RequestParam(value="uploadList") MultipartFile[] uploadList*/, HttpServletRequest request, HttpServletResponse response){
			// 允许跨域访问的域名：若有端口需写全（协议+域名+端口），若没有端口末尾不用加'/'
//			response.setHeader("Access-Control-Allow-Origin", "http://192.168.31.189:8020/"); 

			// 允许前端带认证cookie：启用此项后，上面的域名不能为'*'，必须指定具体的域名，否则浏览器会提示
//			response.setHeader("Access-Control-Allow-Credentials", "true"); 
//			if(uploadList.length > 0) {
//				System.out.println(21836);
//			}
			Map<String, Object> result=new HashMap<String, Object>();
//	    	String param;
//	        param = (String)request.getAttribute(BaseUtil.paramKey);
//	        if(param==null || param.equals("")){
//	            param=request.getParameter(BaseUtil.paramKey);
//	        }
//	        try {
//	       // JSONObject jsonObject=JSON.parseObject(param);
			List<String> list = new ArrayList<String>();
	        if (files != null && files.length > 0) {
	            for (int i = 0; i < files.length; i++) {
	                MultipartFile file = files[i];
	                // 保存文件
	                list = saveFile(request, file, list);
	            }
	        }
//	        //写着测试，删了就可以
//	        for (int i = 0; i < list.size(); i++) {
//	            System.out.println("集合里面的数据" + list.get(i));
//	        }
	    	//picture.setPictureName(file.getOriginalFilename());
	    	/*InputStream inputStream = file.getInputStream();
	    	String fileType="Picture";
	    	String url=UploadAliYunFile.UploadAliYunFileService(inputStream, file, fileType);
	    	URL url2 = new URL(url);
	    	picture.setPictureProtocol(url2.getProtocol());//协议
	    	picture.setPictureIpaddress(url2.getHost());//主机
	    	picture.setPicturePort(String.valueOf(url2.getPort()));//端口
	    	picture.setPictureName(url2.getFile());//文件名*/
	        result.put("pictures", JSON.toJSON(list));
//	        System.out.println(JSON.toJSON(list));
	        result.put("message", "上传成功");
	        result.put("status", true);
	    	return result;
	    }	
	
	//发帖
	@RequestMapping(value="/addforum",method={RequestMethod.POST,RequestMethod.GET})
	public
	@ResponseBody
	Map<String,Object> addforummethod(HttpServletRequest request){
		Map<String, Object> result=new HashMap<String, Object>();
		Map<String, Object> map = new HashMap<String, Object>();
		String param;
        param = (String)request.getAttribute(BaseUtil.paramKey);
        if(param==null || param.equals("")){
            param=request.getParameter(BaseUtil.paramKey);
        }
        JSONObject jsonObject=JSON.parseObject(param);
        String userId = jsonObject.getString("userId");
        String title = jsonObject.getString("title");
        String noteinfo = jsonObject.getString("noteInfo");
        String pictureString = jsonObject.getString("pictures");
        String bookId = jsonObject.getString("bookId");
        //验证
        if (userId == null || "".equals(userId)) {
			result.put("message", "您是游客，不能发帖，请登录");
			result.put("status",false);
			return result;
		}
        if ( title == null || "".equals(title) || noteinfo == null || "".equals(noteinfo)) {
        	result.put("message", "参数未全");
			result.put("status",false);
			return result;
		}
        try {
//		HttpSession session=request.getSession();
//    	User user=(User)session.getAttribute("user");
        User user = userService.selectUserByUserId(Long.parseLong(userId));
		Note newnote=new Note();
		if (null != bookId && !"".equals(bookId)) {
			newnote.setNoteTypeid(Long.parseLong(bookId));
		}
		newnote.setNoteTitle(title);
		newnote.setNoteId(IdWorker.CreateNewID());
		newnote.setNoteContent(noteinfo);
		newnote.setNoteUserName(user.getUserName());
		newnote.setNoteUserid(user.getUserId());
		newnote.setNoteUserImg(user.getUserHeadicon());
		newnote.setNoteDelete(0);
		newnote.setNoteStatus(0);
		newnote.setNoteLaudnumber("0");
		newnote.setNoteCommentnumber("0");
		newnote.setNoteModifytime(new Date());
		newnote.setNoteCreatetime(new Date());
		noteService.addforum(newnote);
		
		if (pictureString.length() >= 4) {
			String[] pictures = pictureString.split("\\[\"|\",\"|\"\\]");//    "\\[\"|\",\"|\"\\]"    "\\[\'|\',\'|\'\\]"
			if (pictures.length > 7) {
				result.put("message", "sorry!只能上传六张图片");
				result.put("status",false);
				return result;
			}
			 map.put("noteId", newnote.getNoteId());
				for (int i = 1; i < pictures.length; i++) {//第一个字符串是空
					map.put("pictureId", Long.parseLong(pictures[i]));
					pictureService.AddLink(map);
				}
		}
		result.put("noteId", JSON.toJSON(newnote.getNoteId()));
		result.put("status", true);
		result.put("message", "发帖成功");
        } catch (Exception e) {
			// TODO: handle exception
			result.put("message", "数据异常，请联系客服");
			result.put("status", false);
		}
		return result;
	}
	
	//点赞
	@RequestMapping(value="/addpraise",method={RequestMethod.POST,RequestMethod.GET})
	public
	@ResponseBody
	Map<String,Object> addpraisemethod(HttpServletRequest request){
		Map<String, Object> result = new HashMap<String, Object>();
		String param;
		param = (String) request.getAttribute(BaseUtil.paramKey);
		if (param == null || param.equals("")) {
			param = request.getParameter(BaseUtil.paramKey);
		}
		JSONObject jsonObject = JSON.parseObject(param);
		String noteId = jsonObject.getString("noteId");
		String userId = jsonObject.getString("userId");

		// HttpSession session=request.getSession();
		// User user=(User)session.getAttribute("user");
		// long fromuserId=user.getUserId();
		Note note = new Note();
		note.setNoteId(Long.parseLong(noteId));
		boolean flag = false;
		try {
			LikeStatus likestatus = new LikeStatus();
			likestatus.setColuId(Long.parseLong(noteId));
			likestatus.setFromUserid(Long.parseLong(userId));
			likestatus.setLikeType(1);//帖子区分
			int num = likeStatusService.findlikenote(likestatus);
			if (num >= 1) {
				LikeStatus l = likeStatusService.selectLikenote(likestatus);
				if (l.getLikeStatus() == 1) {
					likestatus.setLikeStatus(0);
					likeStatusService.updatelikeStatusnote(likestatus);
					noteService.cutpraise(note);
					flag = false;
				} else {
					likestatus.setLikeStatus(1);
					likeStatusService.updatelikeStatusnote(likestatus);
					noteService.addpraise(note);
					flag = true;
				}
			} else {
				likestatus.setLikeDelete(0);
				likeStatusService.addforumlike(likestatus);
				noteService.addpraise(note);
				flag = true;
			}
			result.put("likestatus", flag);
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
	* @author Administrator
	* @date 2017-6-23 上午9:18:54
	*/
	@RequestMapping(value="/myforum",method={RequestMethod.GET,RequestMethod.POST})
	public
	@ResponseBody
	Map<String,Object> myfourmmethod(@RequestParam String page,String size, String userId,String noteId,String type,HttpServletRequest request){
		Map<String, Object> result = new HashMap<String,Object>();
		Map<String, Object> map = new HashMap<String,Object>();
//		List<Integer> likenumber = new ArrayList<Integer>();
//		List<Integer> commnetnumber = new ArrayList<Integer>();
		int pageCount=Integer.parseInt(size);
		map.put("start", (Integer.parseInt(page)-1)*pageCount);
		map.put("size", pageCount);
		try {
		if ("0".equals(type) || "0"==type) {//查看个人贴
			map.put("userId", Long.parseLong(userId));
			List<Note> myNotes = noteService.selectByUserId(map);
			List<List<String>> pictures = new ArrayList<List<String>>();
			for(int i = 0; i < myNotes.size(); i++) {
				List<String> picture = pictureService.selectBy(Long.valueOf(myNotes.get(i).getNoteId()));
				pictures.add(picture);
//				int likenum = likeStatusService.queryexist(myNotes.get(i).getNoteId());
//				int comnum = commentService.commentNum(myNotes.get(i).getNoteId());
//			commnetnumber.add(comnum);
//			likenumber.add(likenum);
			}
			result.put("pictures", pictures);
//			result.put("likeNum", likenumber);
//			result.put("commentNum", commnetnumber);
			result.put("message", "获取个人贴成功");
			result.put("myforums",myNotes);
			result.put("status", true);
		}else {//查看回帖
			map.put("replydataId",Long.parseLong(userId));
			List<Long> noids = commentService.selectnoteid(map);
//			List<Note> notes = noteService.selectnoteid(map);
			List<FirstComList> comLists = new ArrayList<FirstComList>();
			for (int i = 0; i < noids.size(); i++) {
				List<FirstShowMore> firstShowMores = new ArrayList<FirstShowMore>();
				FirstComList firstComList = new FirstComList();
				FirstShowMore firstShowMore = new FirstShowMore();
//				Note note = noteService.queryforumbyid(noids.get(i));
//				System.out.println(note.getNoteTitle());
				Map<String, Object> nm = noteService.selectnotename(noids.get(i));
//				Object object = noteService.selectnotename(noids.get(i));
//				System.out.println(object.toString());
//				String noteName = (String) nm.get("noteTitle");
				map.clear();
				map.put("userId", Long.parseLong(userId));
				map.put("noteId", noids.get(i));
				map.put("start", (int)0);
				map.put("size", (int)2);
				List<Comment> comments = commentService.ComToMe(map);
				List<NameAndComment> nameandcomment = new ArrayList<NameAndComment>();
//				firstShowMore.setOwnerContent(ownerContent);
				for (int j = 0; j < comments.size(); j++) {
					if (null == comments.get(j).getCommentContent()) {
						firstShowMore.setOwnerContent(new String(comments.get(j).getCommentComment()));
					}else {
						firstShowMore.setOwnerContent(new String(comments.get(j).getCommentContent()));
					}
					List<Comment> min = commentService.selectByReplyDateId(comments.get(j).getCommentReplydataid());
					for (int k = 0; k < min.size(); k++) {
						NameAndComment NC = new NameAndComment();
						User username = userService.seletename(comments.get(j).getCommentUserid());
						NC.setReplyDateId(comments.get(j).getCommentId());
						NC.setUserName(new String(username.getUserName()));
		// System.out.println(new String(username.getUserName()));
						NC.setComment(new String(comments.get(j).getCommentComment()));
						nameandcomment.add(NC);
					}
					firstShowMore.setNameandcomment(nameandcomment);
					firstShowMores.add(firstShowMore);
				}
				
				firstComList.setNoteId(noids.get(i));
//				firstComList.setNameandcomment(nameandcomment);
				firstComList.setFirstcommenttime(comments.get(0).getCommentCreatetime());
				firstComList.setNotename((String) nm.get("noteTitle"));
				firstComList.setFirstShowMores(firstShowMores);
				comLists.add(firstComList);
			}
			
			result.put("commentList", JSON.toJSON(comLists));
			result.put("message", "获取回贴成功");
			result.put("status", true);
		}
		} catch (Exception e) {
			// TODO: handle exception
			result.put("message", "数据异常，请联系客服");
			result.put("status", false);
		}
		return result;
	}
	/**
	 * @param noteId
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/delforum",method={RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public Map<String, Object> delforum(HttpServletRequest request){
		Map<String, Object> result = new HashMap<String,Object>();
		String param;
        param = (String)request.getAttribute(BaseUtil.paramKey);
        if(param==null || param.equals("")){
            param=request.getParameter(BaseUtil.paramKey);
        }
        JSONObject jsonObject=JSON.parseObject(param);
        String noteId = jsonObject.getString("noteId");
        try {
		  noteService.delforum(Long.parseLong(noteId));                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      
		result.put("status", true);
		result.put("message", "删除成功");
        } catch (Exception e) {
			// TODO: handle exception
			result.put("message", "数据异常，请联系客服");
			result.put("status", false);
		}
		return result;
	}
	
	/**
	 * 论坛评论接口
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/forumcomment",method={RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public Map<String, Object> forumcomment(HttpServletRequest request){
		Map<String, Object> result = new HashMap<String,Object>();
		String param;
        param = (String)request.getAttribute(BaseUtil.paramKey);
        if(param==null || param.equals("")){
            param=request.getParameter(BaseUtil.paramKey);
        }
        JSONObject jsonObject=JSON.parseObject(param);
        String noteId = jsonObject.getString("noteId").trim();
        String userId = jsonObject.getString("userId").trim();
        String txt = jsonObject.getString("content");
        
//      String toId = jsonObject.getString("toId").trim();
        String Replydateid = jsonObject.getString("Replydateid");//对某个评论评论,某个评论的ID
        Comment comment = new Comment();
        comment.setCommentId(IdWorker.CreateNewID());
        comment.setCommentUserid(Long.parseLong(userId));
        comment.setCommentNoticesid(Long.parseLong(noteId));//所在帖子的ID
        if (null != Replydateid && !"".equals(Replydateid) && 0 != Long.parseLong(Replydateid)) {//对人
//        	commentReplydataid,commentFromuserid
//			comment.setCommentFromuserid(Long.parseLong(toId));//对谁评论
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
		result.put("status", true);
		result.put("message", "评论成功");
        } catch (Exception e) {
			// TODO: handle exception
			result.put("message", "数据异常，请联系客服");
			result.put("status", false);
		}
		return result;
	}
	
	/**
	 * 论坛评论显示
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/showcomment",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> showcomment(@RequestParam String userId, String noteId,String page,String size,HttpServletRequest request){
		Map<String, Object> result = new HashMap<String,Object>();
		Map<String, Object> map = new HashMap<String,Object>();
		boolean flag = false;
//		try {
		if (null != userId && !"".equals(userId)) {
			LikeStatus likeStatu = new LikeStatus();
			likeStatu.setColuId(Long.parseLong(noteId));
			likeStatu.setFromUserid(Long.parseLong(userId));
			likeStatu.setLikeType(1);
			int num = likeStatusService.findlikenote(likeStatu);
			if (num <= 0) {
				likeStatusService.addforumlike(likeStatu);
			}
			LikeStatus likeStatus = likeStatusService.selectLikenote(likeStatu);
			
			if (1 ==likeStatus.getLikeStatus()) {
				flag = true;
			}	
		}
		map.put("userId", Long.parseLong(userId));
		map.put(ArgumentsUtils.sBelongIDKey, Long.parseLong(noteId));
		int pageCount=Integer.parseInt(size);
		map.put("start", (Integer.parseInt(page)-1)*pageCount);
		map.put("size", pageCount);
		List<ForumComment> forumComments = new ArrayList<ForumComment>();
		List<Comment> commentlist = commentService.forumcomment(map);
		for (int i = 0; i < commentlist.size(); i++) {
			
			ForumComment forumComment = new ForumComment();
			forumComment.setReplydateid(commentlist.get(i).getCommentId());
			forumComment.setFirstcommenttime(commentlist.get(i).getCommentCreatetime());
//			String headicon = userService.selecticon(commentlist.get(i).getCommentUserid());
			User user = userService.seletename(commentlist.get(i).getCommentUserid());
			forumComment.setUserheadicon(user.getUserHeadicon());
			forumComment.setUsername(user.getUserName());
			forumComment.setComment(new String(commentlist.get(i).getCommentContent()));
			map.put("start", (int)0);
			map.put("size", (int)2);
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
		result.put("status", true);
		result.put("message", "获取成功");
//		} catch (Exception e) {
//			// TODO: handle exception
//			result.put("message", "数据异常，请联系客服");
//			result.put("status", false);
//		}
		return result;
	}
	
	/**
	 * 我的回帖显示更多
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/showmore", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Map<String, Object> showmore(@RequestParam String userId, String noteId, String page, String size,
			HttpServletRequest request) {
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> map = new HashMap<String, Object>();
		int pageCount = Integer.parseInt(size);
		map.put("start", (Integer.parseInt(page) - 1) * pageCount);
		map.put("size", pageCount);
		map.put("userId", Long.parseLong(userId));
		map.put(ArgumentsUtils.sBelongIDKey, Long.parseLong(noteId));
		try {
			long NID = Long.parseLong(noteId);
		FirstComList firstComList = new FirstComList();
//	String noteName = noteService.selectnotename(Long.parseLong(noteId));
		Map<String, Object> nm = noteService.selectnotename(NID);
		
		List<Comment> comments = commentService.ComToMe(map);
		FirstShowMore firstShowMore = new FirstShowMore();
		List<NameAndComment> nameandcomment = new ArrayList<NameAndComment>();
//		firstShowMore.setOwnerContent(ownerContent);
		for (int i = 0; i < comments.size(); i++) {
			NameAndComment NC = new NameAndComment();
			User username = userService.seletename(comments.get(i).getCommentUserid());
			NC.setUserName(username.getUserName());
			NC.setReplyDateId(comments.get(i).getCommentId());
	// System.out.println(new String(username.getUserName()));
			NC.setComment(new String(comments.get(i).getCommentComment()));
			nameandcomment.add(NC);
		}
		firstComList.setNoteId(NID);
		firstShowMore.setNameandcomment(nameandcomment);
//		firstComList.setNameandcomment(nameandcomment);
	    firstComList.setFirstcommenttime(comments.get(0).getCommentCreatetime());
		firstComList.setNotename((String) nm.get("noteTitle"));
//		firstComList.setFirstShowMores(firstShowMore);
		result.put("commentList", JSON.toJSON(firstComList));
		
		result.put("status", true);
		result.put("message", "获取成功");
		} catch (Exception e) {
			// TODO: handle exception
			result.put("message", "数据异常，请联系客服");
			result.put("status", false);
		}
		return result;
	}

	/**
	 * 论坛评论显示查看更多
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/forumshowmore",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> forumshowmore(@RequestParam String Replydateid, String noteId,String page,String size,HttpServletRequest request){
		Map<String, Object> result = new HashMap<String,Object>();
		Map<String, Object> map = new HashMap<String, Object>();
		int pageCount = Integer.parseInt(size);
		map.put("start", (Integer.parseInt(page) - 1) * pageCount);
		map.put("size", pageCount);
		map.put("userId", Long.parseLong(Replydateid));
		map.put(ArgumentsUtils.sBelongIDKey, Long.parseLong(noteId));
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
//		firstComList.setNoteId(noids.get(i));
//		firstComList.setNameandcomment(nameandcomment);
//		firstComList.setFirstcommenttime(comments.get(i).getCommentCreatetime());
//		firstComList.setNotename(noteName);
		csm.setNameandcomment(nameandcomment);
//		csm.setFirstcommenttime(firstcommenttime);
		User user = userService.seletename(Long.parseLong(Replydateid));
		csm.setUsername(user.getUserName());
		csm.setheadicon(user.getUserHeadicon());
		result.put("showmore", JSON.toJSON(csm));
		result.put("status", true);
		result.put("message", "获取成功");
		} catch (Exception e) {
			// TODO: handle exception
			result.put("message", "数据异常，请联系客服");
			result.put("status", false);
		}
		return result;
	}
	
	/**
	 * 显示某个论坛
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/forumdetail",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> forumdetail(@RequestParam(required=false) String noteId, @RequestParam(required=false) String userId, HttpServletRequest request){
		Map<String, Object> result = new HashMap<String,Object>();
		try {
			Map<String, Object> mapSearch = new HashMap<String,Object>();
			mapSearch.put(BaseUtil.userIdKey, userId);
			mapSearch.put(BaseUtil.typeKey, 1);//1:论坛点赞分辨
			mapSearch.put("noteId", noteId);
			Note note = noteService.SearchNoteDetail(mapSearch);
//		Note note = noteService.queryforumbyid(Long.parseLong(noteId));
		List<String> picture = pictureService.selectBy(Long.parseLong(noteId));
		result.put("pictures", picture);
		result.put("note", JSON.toJSON(note));
		result.put("status", true);
		result.put("message", "获取成功");
		} catch (Exception e) {
			// TODO: handle exception
			result.put("message", "数据异常，请联系客服");
			result.put("status", false);
		}
		return result;
	}
	
}
