package cn.net.sheryl.Test;

import java.util.Date;

public class testtime {
	public static void main(String[] args) {
		String str = "2018-03-03 18:38:37";
		str = str.replace("/-/g", "/");
		Date time = new Date(str);
		System.out.println(time.getYear());
	}
}
