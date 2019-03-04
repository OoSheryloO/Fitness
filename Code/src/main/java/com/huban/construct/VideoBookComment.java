package com.huban.construct;

import java.io.Serializable;
import java.util.Date;

/**单行评论
* <p>Title: StatusUtil</p>
* <p>Description: </p>
* <p>Company: </p>
* @author zhangchao
* @date 2017-6-19 下午12:47:51
*/
@SuppressWarnings("serial")
public class VideoBookComment implements Serializable{
	
	private Long userId;
	
	private String userName;
	
	private String userHeadicon;
	
	private String commentContent; 
	
	private Date commentCreatetime;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserHeadicon() {
		return userHeadicon;
	}

	public void setUserHeadicon(String userHeadicon) {
		this.userHeadicon = userHeadicon;
	}

	public String getCommentContent() {
		return commentContent;
	}

	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}

	public Date getCommentCreatetime() {
		return commentCreatetime;
	}

	public void setCommentCreatetime(Date commentCreatetime) {
		this.commentCreatetime = commentCreatetime;
	}
	
	
}
