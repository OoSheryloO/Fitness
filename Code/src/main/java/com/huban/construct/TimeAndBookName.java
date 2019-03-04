package com.huban.construct;

import java.io.Serializable;
import java.util.Date;

/**账单图信息
* <p>Title: StatusUtil</p>
* <p>Description: </p>
* <p>Company: </p>
* @author zhangchao
* @date 2017-6-19 下午12:47:51
*/
@SuppressWarnings("serial")
public class TimeAndBookName implements Serializable{
	
	private String bookname;
	
	private Date time;

	
	public String getBookname() {
		return bookname;
	}

	public void setBookname(String bookname) {
		this.bookname = bookname;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	
}
