package com.huban.construct;

import java.io.Serializable;

/**账单图信息
* <p>Title: StatusUtil</p>
* <p>Description: </p>
* <p>Company: </p>
* @author zhangchao
* @date 2017-6-19 下午12:47:51
*/
@SuppressWarnings("serial")
public class TimeDate implements Serializable{
	
	private long count;
	
	private int time;

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

//	public Date getTime() {
//		return time;
//	}
//
//	public void setTime(Date time) {
//		this.time = time;
//	}

	
}
