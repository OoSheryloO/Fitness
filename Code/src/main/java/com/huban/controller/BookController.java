/**
 * 
 */
package com.huban.controller;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
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
import com.huban.Utils.ArgumentsUtils;
import com.huban.Utils.ConstantUtils;
import com.huban.pojo.Book;
import com.huban.pojo.Date;
import com.huban.pojo.Integration;
import com.huban.util.IdWorker;

/**
 * @author GeJiangbo
 * @date 2017年6月1日
 */
@Controller
@RequestMapping("/book")
public class BookController extends BaseController {

	/**
	 * @param request
	 * @throws UnsupportedEncodingException 
	 * */
	@RequestMapping(value="/booklist",method={RequestMethod.GET,RequestMethod.POST})
	public
	@ResponseBody
	Map<String, Object> booklistMethod(@RequestParam String page, String size, String name, String hot, @RequestParam(required=false)Integer type, HttpServletRequest request) throws UnsupportedEncodingException{
		Map<String, Object> result=new HashMap<String, Object>();
		int pageCount=Integer.parseInt(size);
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("start", (Integer.parseInt(page)-1)*pageCount);
		map.put("size", pageCount);
		System.out.println(type);
		if (type != null) {
			map.put(ArgumentsUtils.sPublisherKey, 1);
		}else {
			map.put(ArgumentsUtils.sPublisherKey, 0);
		}
		//String bn = new String(name.getBytes("ISO8859-1"),"UTF-8");
		if("".equals(name) || null == name){
			map.put("name", "");
			if (hot != null || !"".equals(hot)) {
				map.put("type", Integer.parseInt(hot));
			}else {
				map.put("type", 0);
			}
		}else{
			map.put("name", name);
			map.put("type", 0);
		}
		List<Book> list=bookService.queryBooks(map);
		result.put("bookList",JSON.toJSON(list));
		result.put("status", true);
		return result;
	}
	
	//详情
	@RequestMapping(value="/bookdetail",method={RequestMethod.GET,RequestMethod.POST})
	public 
	@ResponseBody
	Map<String, Object> activitydetailmethod(@RequestParam String bookId,String userId,HttpServletRequest request){
		Map<String, Object> result=new HashMap<String, Object>();
		try {
		Book book = bookService.queryBook(Long.parseLong(bookId));
		int count =  0;
		if (null != userId && !"".equals(userId) && 0 != Long.parseLong(bookId)) {
			count = shopService.shopcarcount(Long.parseLong(userId));
		}
		if (null != userId && !"".equals(userId) && ("0" != userId && !"0".equals(userId)) && ("-1" != userId && !"-1".equals(userId))) {
			if(ConstantUtils.bFirstReadIntegral) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("userId", Long.parseLong(userId));
				map.put("bookId", Long.parseLong(bookId));
				int num = dateService.querycount(map); 
				if (num <= 0) {
					Date date = new Date();
					date.setDateId(IdWorker.CreateNewID());
					date.setDateUserid(Long.parseLong(userId));
					date.setDateBelongid(Long.parseLong(bookId));
					date.setDateType(1);//原值为(int)1
					date.setDateStatus(0);
					dateService.addmessage(date);
					bookService.changereadcount(Long.parseLong(bookId));
					
					Integration integration = new Integration();
					integration.setIntegrationId(IdWorker.CreateNewID());
					integration.setIntegrationStatus(0);
					integration.setIntegrationNum(new BigDecimal(ConstantUtils.ReadIntegral));
					integration.setIntegrationStudentid(Long.parseLong(userId));
					integration.setIntegrationType(5);
					integration.setIntegrationSrcid((long) 1);//0:支出 1:收入
					integration.setIntegrationReason(ConstantUtils.FirstRead);
					integrationService.addmessage(integration);
					map.clear();
					map.put("userId", Long.parseLong(userId));
					map.put("userIntegral", (ConstantUtils.ReadIntegral));
					userService.addIntegral(map);// 积分增加
				}
			}
		}
		Integer readnumber = bookService.queryreadnumber(Long.parseLong(bookId));;
		/*Map<String, Object> map = new HashMap<String,Object>();
		map.put("userId", userId);
		map.put("bookId", bookId);*/
		result.put("bookReadNumber", readnumber);
		result.put("count", count);
		result.put("detail", JSON.toJSON(book));
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
	@RequestMapping(value="/bookcomments",method={RequestMethod.GET,RequestMethod.POST})
	public 
	@ResponseBody
	Map<String, Object> videocommentsMethod(@RequestParam String page, String size, String bookId,HttpServletRequest request){
		Map<String,Object> result=new HashMap<String, Object>();
		int pageCount=Integer.parseInt(size);
		Map<String, Object> queryMap=new HashMap<String, Object>();
		queryMap.put("bookId", bookId);
		queryMap.put("start", pageCount*(Integer.parseInt(page)-1));
		queryMap.put("size", pageCount);
		List<FirstComment> comments =  new ArrayList<FirstComment>();
		
		List<Comment> bookComments = commentService.bookComment(queryMap);
		for (int i = 0; i < bookComments.size(); i++) {
			User u = new User();
			u.setUserId(bookComments.get(i).getCommentUserid());
			User uu = userService.selectUser(u);
			
			FirstComment firstComment = new FirstComment();
			firstComment.setUserName(uu.getUserName());
			firstComment.setUserHeadicon(uu.getUserHeadicon());
			firstComment.setCommentCreatetime(bookComments.get(i).getCommentCreatetime());
			firstComment.setCommentContent(new String(bookComments.get(i).getCommentContent()));
			comments.add(firstComment);
		}
		result.put("comments", JSON.toJSON(comments));
		result.put("status", true);
		return result;
	}
	
	
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
        String bookId = jsonObject.getString("bookId");
        String txt = jsonObject.getString("comment");
        Comment comment = new Comment();
        comment.setCommentUserid(Long.parseLong(userId));
        comment.setCommentGoodsid(Long.parseLong(bookId));
        comment.setCommentContent(txt.getBytes());
        commentService.addcomment(comment);
        result.put("message", "评论发表success");
        result.put("status", true);
		return result;
	}*/
	
	
}
